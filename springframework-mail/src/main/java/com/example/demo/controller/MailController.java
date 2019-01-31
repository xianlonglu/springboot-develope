package com.example.demo.controller;

import com.example.demo.model.Message;
import com.example.demo.service.JfreeChartService;
import com.example.demo.service.MailService;
import com.example.demo.service.impl.JfreeLineChart;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        
        //mailService.sendMessageMail(message, "测试消息通知", "message.ftl");
        //mailService.sendMessageMail(message, "测试消息通知", "message.html");
        
        Map<String, Object> map = new HashMap<String, Object>(); 
        map.put("messageCode", "MissingParameter1");
        map.put("messageStatus", "Failed");
        map.put("cause", "缺少参数,请确认");
        map.put("img", "data:image/png;base64," + imgStr);
        
        List<String> list1 = new ArrayList<String>();
        list1.add("a");
        list1.add("b");
        list1.add("c");
        map.put("list1", list1);
        List<String> list2 = new ArrayList<String>();
        list2.add("data:image/png;base64," + imgStr);
        list2.add("data:image/png;base64," + JfreeLineChart.getImg());
        map.put("list2", list2);
        map.put("module1", "module1");
        map.put("module2", "module2");
        map.put("module3", "module3");
        mailService.sendMessageMail(map, "测试消息通知", "message.ftl");
        //mailService.sendMessageMail(map, "测试消息通知", "message.html");
    }
}
