package com.hangaries.repository;

import com.hangaries.model.CSSMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CSSRepository extends JpaRepository<CSSMaster, Long> {

    @Modifying
    @Transactional
    @Query(value = "update CSS_MASTER set status=:status where id=:id", nativeQuery = true)
    void saveCSSStatus(long id, String status);
}
