package com.weavus.studyweb.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weavus.studyweb.entity.Study;
import com.weavus.studyweb.repository.StudyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyService {
    
    @Autowired
    private StudyRepository studyRepository;

    //create Study
    public Study createStudy(Study study){
        return studyRepository.save(study);
    }

    public List<Study> findAll(){
        return studyRepository.findAll();
    } 

    //募集中のリスト取得
    public List<Study> findRecruiting(){
        Timestamp now = new Timestamp(new Date().getTime());

        return studyRepository.findByStartDateGreaterThanEqual(now);
    }

    public Study findById(Long id) {
        return studyRepository.findById(id).get();
    }

    
}
