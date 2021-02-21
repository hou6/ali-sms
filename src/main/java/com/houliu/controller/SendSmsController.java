package com.houliu.controller;

import com.houliu.service.impl.SendSmsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author houliu
 * @created 2020-07-19  22:37
 */

@Controller
//@CrossOrigin
public class SendSmsController {

    @Autowired
    private SendSmsServiceImpl sendSms;

    @GetMapping("/sendSms/{phoneNumber}")
    public boolean sendSms(@PathVariable("phoneNumber") String phoneNumber){
        return sendSms.sendSms(phoneNumber);
    }

}
