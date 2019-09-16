package com.docker.example.hello.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @GetMapping({ "/", "/{greeting}" })
  @ResponseBody
  public String defaul(@PathVariable(required = false) String greeting) {
    String strTemp = "";
    if (greeting == null)
      strTemp = "Hello Docker!" + "<br>" + getHostName1();
    else
      strTemp = "Hello" + " " + greeting + "!" + "<br>" + getHostName1();

    return strTemp;
  }

  public static String getHostName() {
    String hostname = System.getenv("HOSTNAME");
    if (hostname != null)
      return hostname;

    String lineStr = "";
    try {
      Process process = Runtime.getRuntime().exec("hostname");
      BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
      while ((lineStr = br.readLine()) != null) {
        hostname = lineStr;
      }
    } catch (IOException e) {
      e.printStackTrace();
      hostname = "";
    }
    return "HostName : " + hostname;
  }

  public static String getHostName1() {
    String hostName = "";
    String hostIP = "";
    try {
      hostName = InetAddress.getLocalHost().getHostName();
      hostIP = InetAddress.getLocalHost().getHostAddress();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return "HostName : " + hostName + ", " + "HostIP : " + hostIP;
  }
}