package com.ydw.service.make;

import com.ydw.function.TwoParamConsumer;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.model.jpa_model.Machine;
import com.ydw.model.para.Make_File;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
import com.ydw.repository.jap_repository.MachineRepository;
import com.ydw.utils.baidu.BaiduUtils;
import com.ydw.utils.es_query.EsQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author HYL
 * @create 2018-01-15 上午10:13
 **/
@Service
@Transactional
public class MakeService {

    @Autowired
    BaseTimuSearchRepository baseTimuSearchRepository;

    @Autowired
    BaiduUtils baituUtils;

    @Autowired
    TimuDocumentRepository timuDocumentRepository;

    @Autowired
    EsQueryService esQueryService;

    @Autowired
    MachineRepository machineRepository;

    /**
     * 更新索引信息
     *
     * @return
     */
    public boolean makeDocumentInfo(Make_File make_file, String imgPath) throws InstantiationException, IllegalAccessException {
        Boolean flag = false;
        //第一步:解析图片,然后保存图片
        MultipartFile fist = make_file.getFist();
        MultipartFile second = make_file.getSecond();
        MultipartFile third = make_file.getThird();
        String timuId = make_file.getTimuId();
        //第二步:使用百度文字识别接口,获取搜索内容
        //第三步:更新索引内容
        //第四步:更新Machine表中的各个字段
        final Machine[] machines = {machineRepository.newInstance(new Machine(),timuId)};
        try {
            if (fist != null) {
                dealFile(fist, timuId, imgPath, (x, y) -> {
                    y.setFirstContent(x);
                    machines[0].setFirstcontent(x);
                    machines[0].setFirstpic(imgPath+fist.getOriginalFilename());
                });
            }
            if (second != null) {
                dealFile(second, timuId, imgPath, (x, y) -> {
                    y.setSecondContent(x);
                    machines[0].setSecondcontent(x);
                    machines[0].setSecondpic(imgPath+second.getOriginalFilename());
                });
            }
            if (third != null) {
                dealFile(third, timuId, imgPath, (x, y) -> {
                    y.setThirdContent(x);
                    machines[0].setThirdcontent(x);
                    machines[0].setThirdpic(imgPath+third.getOriginalFilename());
                });
            }
            machineRepository.save(machines[0]);
            flag = true;
        } catch (IOException e) {
            e.printStackTrace();
            flag = false;
        }

        return flag;
    }

    private void dealFile(MultipartFile file, String timuId, String templePath, TwoParamConsumer<String, TimuDocument> step1) throws IOException {
        if (file == null) {
            return;
        }
        String filename = file.getOriginalFilename();
        File templeFile = new File(templePath + filename);
        file.transferTo(templeFile);
        String contentByImgPath = baituUtils.getContentByImgPath(templeFile.getAbsolutePath());
        Optional<TimuDocument> byId = timuDocumentRepository.findById(timuId);
        //题目识别内容需要非空校验
        if (contentByImgPath != null) {
            if (byId.isPresent()) {
                //如果题目存在则更新第一个搜索内容
                TimuDocument timuDocument = byId.get();
                step1.accept(contentByImgPath, timuDocument);
                timuDocumentRepository.save(timuDocument);
            } else {
                //如果这个文档不存在,先去数据库查询所有的信息,完善Document然后执行保存
                TimuDocument timuDocument = new TimuDocument(timuId);
                step1.accept(contentByImgPath, timuDocument);
                timuDocumentRepository.save(timuDocument);
            }
        } else {
            throw new RuntimeException(filename + "图片识别为空");
        }
    }

    /**
     * 根据图片返回对应的搜搜结果
     * @param file
     * @param imgPath
     * @return
     */
    public List<TimuDocument> findByPic(MultipartFile file, String imgPath) {
        List<TimuDocument> list=new ArrayList<>();
        String imgRealPath = getFile(file, imgPath).getAbsolutePath();
        String contentByImgPath = baituUtils.getContentByImgPath(imgRealPath);
        System.out.println("图片识别的内容:"+contentByImgPath);
        if (contentByImgPath != null&&!contentByImgPath.equals("")) {
           list= esQueryService.findFinalRestult(contentByImgPath);
        }

        return list;
    }

    public static File getFile(MultipartFile file,String path){
        String originalFilename = file.getOriginalFilename();
        File templeFile = new File(path + originalFilename);
        try {
            file.transferTo(templeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return templeFile;
    }

}
