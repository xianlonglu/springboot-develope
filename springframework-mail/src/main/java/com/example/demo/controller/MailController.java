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
        list2.add("data:image/png;base64,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");
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
