package com.uterm.web;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;

/** 
 * Monitor
 * 
 * describe the host server information
*/
class Monitor {

    @Getter private String message;
    @Getter private String host;
    @Getter private String ip;

    public Monitor(String msg) throws UnknownHostException {
        String[] hostInfo = InetAddress.getLocalHost().toString().split("/");
        this.host = hostInfo[0];
        this.ip = hostInfo[1];
        this.message = msg;
    }
}

@RestController
public class MonitorController {

    private static final String welcomeMessage = "Check the server";

    @RequestMapping("/monitor")
    public Monitor monitor() throws UnknownHostException {
        return new Monitor(welcomeMessage);
    }
}