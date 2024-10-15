package com.bupjangsa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
@RequestMapping(value = "/api/v1/system")
public class SystemController {

    @GetMapping(value = "/check")
    public String getSystemStatus() throws UnknownHostException {
        return "api application is Run! Host : " + InetAddress.getLocalHost().getHostName();
    }
}
