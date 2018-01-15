package com.ydw.service;

import com.ydw.function.TwoParamConsumer;
import com.ydw.model.es_model.TimuDocument;
import com.ydw.model.para.Make_File;
import com.ydw.repository.es_repository.TimuDocumentRepository;
import com.ydw.repository.jap_repository.BaseTimuSearchRepository;
import com.ydw.utils.baidu.BaituUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

/**
 * @author HYL
 * @create 2018-01-15 上午10:13
 **/
@Service
public class MakeService {

    @Autowired
    BaseTimuSearchRepository baseTimuSearchRepository;

    @Autowired
    BaituUtils baituUtils;

    @Autowired
    TimuDocumentRepository timuDocumentRepository;

    /**
     * 更新索引信息
     * @return
     */
    public boolean makeDocumentInfo(Make_File make_file,String imgPath){
        Boolean flag=false;
        //第一步:解析图片,然后保存图片
        MultipartFile fist = make_file.getFist();
        MultipartFile second = make_file.getSecond();
        MultipartFile third = make_file.getThird();
        String timuId = make_file.getTimuId();
        //第二步:使用百度文字识别接口,获取搜索内容
        //第三步:更新索引内容
        try {
            if (fist!=null) {
                dealFile(fist,timuId,imgPath,(x,y)->y.setFirstContent(x));
            }
            if (second!=null) {
                dealFile(second,timuId,imgPath,(x,y)->y.setSecondContent(x));
            }
            if (third!=null) {
                dealFile(third,timuId,imgPath,(x,y)->y.setThirdContent(x));
            }
            flag=true;
        } catch (IOException e) {
            e.printStackTrace();
            flag=false;
        }

        return flag;
    }

    private void dealFile(MultipartFile file, String timuId, String templePath, TwoParamConsumer<String,TimuDocument> step1) throws IOException {
        if (file == null) {
            return;
        }
        String filename = file.getOriginalFilename();
            File templeFile = new File(templePath + filename);
            file.transferTo(templeFile);
            String contentByImgPath = baituUtils.getContentByImgPath(templeFile.getAbsolutePath());
            Optional<TimuDocument> byId = timuDocumentRepository.findById(timuId);
            if (byId.isPresent()) {
                //如果题目存在则更新第一个搜索内容
                TimuDocument timuDocument = byId.get();
                step1.accept(contentByImgPath, timuDocument);
                timuDocumentRepository.save(timuDocument);
            }else {
                //如果这个文档不存在,先去数据库查询所有的信息,完善Document然后执行保存
                TimuDocument timuDocument = new TimuDocument(timuId);
                step1.accept(contentByImgPath,timuDocument);
                timuDocumentRepository.save(timuDocument);
            }
    }

}
