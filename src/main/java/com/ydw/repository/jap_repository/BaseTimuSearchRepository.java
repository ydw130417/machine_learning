package com.ydw.repository.jap_repository;

import com.ydw.model.jpa_model.Base_timu_search;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by ydw on 2018/1/10.
 */
public interface BaseTimuSearchRepository extends JpaRepository<Base_timu_search,String> {

     Page<Base_timu_search> findBase_timu_searchesBySubjectIdEquals(Long subjectId, Pageable pageable);
}
