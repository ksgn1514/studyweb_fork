package com.weavus.studyweb.Interceptor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import com.weavus.studyweb.auth.PrincipalDetails;

public class UserInterceptor implements WebRequestInterceptor {

    @Override
    public void preHandle(WebRequest request) throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if (auth != null && auth.getPrincipal() instanceof PrincipalDetails) {
            PrincipalDetails loginUserDetails = (PrincipalDetails) auth.getPrincipal();
            request.setAttribute("user", loginUserDetails.getUser(), WebRequest.SCOPE_REQUEST);
        }
    }

    @Override
    public void postHandle(WebRequest request, ModelMap model) throws Exception {
        
    }

    @Override
    public void afterCompletion(WebRequest request, Exception ex) throws Exception {
        
    }
    
}
