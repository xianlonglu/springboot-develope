package com.example.demo.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.example.demo.service.MailService;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * Created by Administrator on 2017/12/13.
 */

@Service
public class MailserviceImpl implements MailService {
    //邮件的发送者
    @Value("${spring.mail.username}")
    private String from;

    @Value("${spring.mail.setTo}")
    private String setTo;

    //注入MailSender
    @Autowired
    private JavaMailSender mailSender;

    //发送邮件的模板引擎
    @Autowired
    private FreeMarkerConfigurer configurer;

    /**
     * @param params       发送邮件的主题对象 object
     * @param title        邮件标题
     * @param templateName 模板名称
     */
    @Override
    public void sendMessageMail(Object params, String title, String templateName) {

        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);//发送者
            helper.setTo(setTo);//发送给谁
            helper.setSubject("【" + title + "-" + LocalDate.now() + " " + LocalTime.now().withNano(0) + "】");//邮件标题

            Map<String, Object> model = new HashMap<>();
            model.put("params", params);
            try {
                Template template = configurer.getConfiguration().getTemplate(templateName);
                try {
                    String text = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);

                    helper.setText(text, true);
                    mailSender.send(mimeMessage);
                 // 8. 将该邮件保存到本地
                    OutputStream out = new FileOutputStream("MyEmail.eml");
                    mimeMessage.writeTo(out);
                    out.flush();
                    out.close();
                } catch (TemplateException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

}
