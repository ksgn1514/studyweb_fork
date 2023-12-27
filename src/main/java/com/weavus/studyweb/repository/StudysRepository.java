package com.weavus.studyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weavus.studyweb.entity.Studys;
import java.util.List;
import java.util.Optional;
import java.sql.Timestamp;


@Repository
public interface StudysRepository extends JpaRepository<Studys, Long>  {
    
    Optional<Studys> findById(Long id);

    //募集中のリスト取得のため
    List<Studys> findByStartDateGreaterThanEqual(Timestamp startDate);

}
