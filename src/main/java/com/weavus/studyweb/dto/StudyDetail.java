package com.weavus.studyweb.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.weavus.studyweb.entity.StudyApplication;

import lombok.Data;

@Data
public class StudyDetail {
    // Study 정보와 참가자 리스트, 참가자 수를 담는 DTO

    private StudyDTO study;
    private List<StudyApplication> applications;
    private int applicantCount;

    // startDate로부터 D-day 계산 메소드
    public String calculateDday() {
        LocalDate start = LocalDate.parse(study.getStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate today = LocalDate.now();
        long daysBetween = ChronoUnit.DAYS.between(today, start);

        if (daysBetween > 0) {
            return "D-" + daysBetween; // 미래 날짜
        } else if (daysBetween == 0) {
            return "D-Day"; // 당일
        } else {
            return "D+" + Math.abs(daysBetween); // 과거 날짜
        }
    }

}
