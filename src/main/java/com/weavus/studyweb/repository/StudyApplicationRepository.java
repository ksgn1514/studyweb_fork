package com.weavus.studyweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.entity.User;

import java.util.List;


public interface StudyApplicationRepository extends JpaRepository<StudyApplication, Long> {
    List<StudyApplication> findByStudy(Studys study);

    List<StudyApplication> findByUser(User user);
}
