package com.weavus.studyweb.utility;

import org.springframework.stereotype.Component;

@Component
public class CommonUtility {
    public String nl2br(String text) {
        return text.replace("\n", "<br>");
    }
}
