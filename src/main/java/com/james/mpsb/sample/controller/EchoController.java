package com.james.mpsb.sample.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/echo")
public class EchoController {

    private final Logger logger = LoggerFactory.getLogger(EchoController.class);

    @GetMapping(path = "/ping")
    @ResponseBody
    public String ping(HttpServletRequest request) {
        logger.info("{} {} @ " + System.currentTimeMillis(),
                this.getClass().getSimpleName(), request.getRequestURL());

        return "pong";
    }
}
