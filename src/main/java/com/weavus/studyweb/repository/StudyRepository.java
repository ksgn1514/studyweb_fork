package com.weavus.studyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weavus.studyweb.entity.Study;
import java.util.List;
import java.sql.Timestamp;


@Repository
public interface StudyRepository extends JpaRepository<Study, Long>  {

    //募集中のリスト取得のため
    List<Study> findByStartDateGreaterThanEqual(Timestamp startDate);

}
