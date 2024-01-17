package com.weavus.studyweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weavus.studyweb.entity.StudyApplication;
import com.weavus.studyweb.entity.Study;
import com.weavus.studyweb.repository.StudyApplicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudyApplicationService {
    
    @Autowired
    private StudyApplicationRepository studyApplicationRepository;

    public StudyApplication addStudyApplication(StudyApplication studyApplication) {
        return studyApplicationRepository.save(studyApplication);
    }

    public List<StudyApplication> findByStudy(Study study){
        return studyApplicationRepository.findByStudy(study);
    }
}
