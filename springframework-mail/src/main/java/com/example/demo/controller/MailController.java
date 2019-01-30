package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.JfreeChartService;
import com.example.demo.service.MailService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/12/13.
 */

@RestController
public class MailController {

    @Autowired
    private MailService mailService;
    @Autowired
    private JfreeChartService jfreeChartService;

    /**
     * @throws IOException 
     *
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public void sendMailMessage() throws IOException {
        Message message = new Message();

        message.setMessageCode("MissingParameter");
        message.setMessageStatus("Failed");
        message.setCause("缺少参数,请确认");
        
        String imgStr = jfreeChartService.getImg();
        message.setImg("data:image/png;base64," + imgStr);
        
        mailService.sendMessageMail(message, "测试消息通知", "message.ftl");
    }
}
