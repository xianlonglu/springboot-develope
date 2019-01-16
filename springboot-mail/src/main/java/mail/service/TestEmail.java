package mail.service;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class TestEmail {
	//luxianlonga@163.com a123456 smtp.163.com 465
	//408030514@qq.com fjhwomsiamhtbjbe smtp.qq.com 25
	//xianlong.lu@sprixin.com 6kW8SwxcJpHQcFUd smtp.qiye.163.com 25
	/** ALIYUN*/
	//public static final String ALIYUN_SMTP_HOST_NAME = "smtp.aliyun.com";
	//public static final String ALIYUN_SMTP_HOST_NAME = "smtp.163.com";
	//public static final String ALIYUN_SMTP_HOST_NAME = "smtp.qq.com";
	public static final String ALIYUN_SMTP_HOST_NAME = "smtp.qiye.163.com";
	/** ALIYUN*/
	public static final int ALIYUN_SMTP_HOST_PORT = 25;
	/** ALIYUN*/
	public static final String ALIYUN_MAIL_ACCOUNT = "xianlong.lu@sprixin.com";
	/** ALIYUN*/
	public static final String ALIYUN_MAIL_PASSWORD = "6kW8SwxcJpHQcFUd";
	/** 邮件主题*/
	public static final String ALIYUN_MAIL_SUBJECT_FINDPASSWORD = "探果网找回密码";
	/** 邮箱找回密码前缀*/
	public static final String ALIYUN_MAIL_FIND_PASSWORD_PREFIX = "EMAIL_FIND_PASSWORD_";
	
	public static final String email = "408030514@qq.com";
	
	public static void main(String[] args) throws EmailException {
		
		String subject = "subject";
		HtmlEmail mail = new HtmlEmail();
		mail.setHostName(ALIYUN_SMTP_HOST_NAME);
		//mail.setSmtpPort(ALIYUN_SMTP_HOST_PORT);
		mail.setAuthentication(ALIYUN_MAIL_ACCOUNT, ALIYUN_MAIL_PASSWORD);
		mail.setSSLOnConnect(true);
		mail.setFrom(ALIYUN_MAIL_ACCOUNT, "探果网");
		mail.addTo(email);
		mail.setSubject(subject);
		mail.setCharset("UTF-8");
		Integer vcode = (int) ((Math.random()*9+1)*100000);  
		//mail.setHtmlMsg("探果网找回密码验证码："+vcode);
		mail.setHtmlMsg("领导，您好：\n     附件中内容为本期统计平台测试数据总结，\n 1     请查收。");
		mail.send();
		System.err.println("发送成功");
	}

}
