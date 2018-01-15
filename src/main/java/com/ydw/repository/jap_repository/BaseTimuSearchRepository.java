package com.ydw.repository.jap_repository;

import com.ydw.model.jpa_model.Base_timu_search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by ydw on 2018/1/10.
 */
public interface BaseTimuSearchRepository extends JpaRepository<Base_timu_search,String> {

     /**
      * 分页查找不同科目的题目
      * @param subjectId 科目的id
      * @param pageable 分页对象
      * @return
      */
     Page<Base_timu_search> findBase_timu_searchesBySubjectIdEquals(Long subjectId, Pageable pageable);

     /**
      * 根据题干信息查找
      * @param keyWords
      * @return
      */
     Base_timu_search findFirstByTrunkLike(String keyWords);

     Base_timu_search findFirstByTrunkContains(String keywords);

}
