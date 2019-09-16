package com.rest.example.greeting.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

  @GetMapping("/")
  @ResponseBody
  public String index(@PathVariable(required = false) String name) {
    String strTemp =  "get - " + getHostInfo();

    return strTemp;
  }

  @GetMapping("/greeting")
  @ResponseBody
  public String greeting() {
    String strTemp = "";
    strTemp = "<h2>" + "Hello!" + "</h2><br>" + "get - " + getHostInfo();
    return strTemp;
  }

  @PutMapping("/greeting")
  @ResponseBody
  public String greetingPut(@RequestParam(value = "name") String name) {
    String strTemp = "";
    if (name.isEmpty() || name == null)
      strTemp = "<h2>" + "Hello world!" + "</h2><br>" + "put - " + getHostInfo();
    else
      strTemp = "<h2>" + "Hello " + name + "!" + "</h2><br>" + "put - " + getHostInfo();

    return strTemp;
  }

  @PostMapping("/greeting")
  @ResponseBody
  public String greetingPost(@RequestParam(value = "name") String name) {
    String strTemp = "";
    if (name.isEmpty() || name == null)
      strTemp = "<h2>" + "Hello world!" + "</h2><br>" + "post - " + getHostInfo();
    else
      strTemp = "<h2>" + "Hello " + name + "!" + "</h2><br>" + "post - " + getHostInfo();

    return strTemp;
  }

  public static String getHostInfo() {
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
    return "HostName : " + hostname + getAddress(hostname);
  }

  public static String getAddress(String hostname) {
    String ipaddress = "";
      try {
        InetAddress address = InetAddress.getByName(hostname);
        ipaddress = " Address: " + address.getHostAddress();         
    } catch (UnknownHostException e1) {
        e1.printStackTrace();
        System.out.println("error");  
        ipaddress = "";
    } 
    return ipaddress;
  }
}