package com.es.cxp.domainservices.order.service;

import org.springframework.stereotype.Service;

@Service
public class SecurityService {
    private String loginUserName;

    public String getLoginUserName() {
        return loginUserName != null ? loginUserName : "user";
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }
}
