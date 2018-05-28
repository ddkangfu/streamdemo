package com.zc3u.streamdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.integration.support.MutableMessageBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    @Autowired
    private Processor processor;

    @RequestMapping(value = "/push/{msg}/", method = RequestMethod.GET)
    public String send(@PathVariable("msg") String msg){
        processor.output().send(MutableMessageBuilder.withPayload(msg).build());
        return "success";
    }
}