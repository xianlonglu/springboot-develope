/**
 * 
 */
package mail.test;

import java.io.File;

import mail.service.MailSendService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author ignore1992
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestMail
{
	@Autowired
	private MailSendService mailSendService;
	
	@Test
	public void test()throws Exception
	{
		String fromPos = "xianlong.lu@sprixin.com";
		String toPos = "luxianlonga@163.com";
		String subject = "今天的会议通知";
		String text = "你好撒地方是个地方官 ";
		//1.测试发送简单内容邮件
		//mailSendService.sendSimpleMail(fromPos, toPos, subject, text);
		
		FileSystemResource file = new FileSystemResource(new File("D:\\1.jpg"));
		//2.测试发送附件邮件
		mailSendService.sendAttachFileMail(fromPos, toPos, subject, text, file);
		
		//3.测试发送内置静态资源邮件
		//mailSendService.sendInlineMail(fromPos, toPos, subject,  file);
	}
	
}
