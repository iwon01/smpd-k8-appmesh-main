package com.smpd.appmeshmain;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Log4j2
@SpringBootApplication
@EnableScheduling
public class AppmeshMainApplication {

    @Value("${appmesh.sub.url}")
    private String subUrl;

    @Value("${appmesh.outer.url}")
    private String outerUrl;

    @Autowired
    private RestTemplate restTemplate;

    public static void main(String[] args) {
        SpringApplication.run(AppmeshMainApplication.class, args);
    }

    @Scheduled(fixedDelay = 5000)
    public void logCalls() {
        Response responseSub = restTemplate.getForObject(subUrl, Response.class);
        Response responseOuter = restTemplate.getForObject(outerUrl, Response.class);
        log.info(responseSub);
        log.info(responseOuter);
    }


}
