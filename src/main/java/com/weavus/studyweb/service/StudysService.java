package com.weavus.studyweb.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weavus.studyweb.entity.Studys;
import com.weavus.studyweb.repository.StudysRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudysService {
    
    @Autowired
    private StudysRepository studysRepository;

    //create Study
    public Studys createStudy(Studys study){
        return studysRepository.save(study);
    }

    public List<Studys> findAll(){
        return studysRepository.findAll();
    } 

    //募集中のリスト取得
    public List<Studys> findRecruiting(){
        Timestamp now = new Timestamp(new Date().getTime());

        return studysRepository.findByStartDateGreaterThanEqual(now);
    }
}
