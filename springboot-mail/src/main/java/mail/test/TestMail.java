/**
 * 
 */
package mail.test;

import java.io.File;

import mail.service.MailSendService;

import org.apache.commons.lang3.StringUtils;
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
		String fromPos = "408030514@qq.com";
		String toPos = "408030514@qq.com";
		String subject = "今天的会议通知";
		String text = "你好撒地方是个地方官 ";
		//1.测试发送简单内容邮件
		//mailSendService.sendSimpleMail(fromPos, toPos, subject, text);
		
		FileSystemResource file = new FileSystemResource(new File("D:\\1.jpg"));
		//2.测试发送附件邮件
		//mailSendService.sendAttachFileMail(fromPos, toPos, subject, text, file);
		
		//3.测试发送内置静态资源邮件
		//mailSendService.sendInlineMail(fromPos, toPos, subject,  file);
		
		sendmails(fromPos, subject);
		
	}
	private void sendmails(String fromPos, String subject){

		StringBuffer stringBuffer  = new StringBuffer();
		stringBuffer.append("<span>你好:</span><br/>");
		String content = "根据已完成的换肤方案，经过查看筛选，决定取消换肤中的倒数第二个背景，保留其余的几种方案。需要取消的背景，参考下面图示。";
		stringBuffer.append("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + content + "</p>");
		stringBuffer.append("<p>祝</p>");
		stringBuffer.append("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工作顺利！</p>");
		
		String [] toPoss = {"408030514222@qq.com", "40803051411@qq.com","408030514111@qq.com"};
		String msg = "";
		for (int i = 0; i < toPoss.length; i++) {
			if(!mailSendService.sendHtmlMail(fromPos, toPoss[i], subject, stringBuffer.toString())){
				msg += toPoss[i] + ",";
			}
		}
		if (!StringUtils.isEmpty(msg)) {
			msg = msg.substring(0, msg.length() - 1);
		}
		System.err.println(msg);
	}
	
}
