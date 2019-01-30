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
		
		//sendmails(fromPos, subject);
		sendmails2(fromPos, subject);
		
	}
	private void sendmails(String fromPos, String subject){

		StringBuffer stringBuffer  = new StringBuffer();
		stringBuffer.append("<span>你好:</span><br/>");
		String content = "根据已完成的换肤方案，经过查看筛选，决定取消换肤中的倒数第二个背景，保留其余的几种方案。需要取消的背景，参考下面图示。";
		stringBuffer.append("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + content + "</p>");
		stringBuffer.append("<p>祝</p>");
		stringBuffer.append("<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;工作顺利！</p>");
		
		String [] toPoss = {"408030514@qq.com", "40803051411@qq.com","408030514111@qq.com"};
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
	
	

	
	private void sendmails2(String fromPos, String subject){

		StringBuffer stringBuffer  = new StringBuffer();
		stringBuffer.append("<div id='contentDiv' onmouseover='getTop().stopPropagation(event);' onclick='getTop().preSwapLink(event, 'html', 'ZC2815-fkyZ~PVpazkEHARYtVTsW91');' style='position:relative;font-size:14px;height:auto;padding:15px 15px 10px 15px;z-index:1;zoom:1;line-height:1.7;' class='body'>");
		stringBuffer.append("<div id='qm_con_body'>");
		stringBuffer.append("<div id='mailContentContainer' class='qmbox qm_con_body_content qqmail_webmail_only' style=''>");
		stringBuffer.append("<meta http-equiv='Content-Type' content='text/html; charset=GB18030'>");
		stringBuffer.append("<style>body { line-height: 1.5; }blockquote { margin-top: 0px; margin-bottom: 0px; margin-left: 0.5em; }p { margin-top: 0px; margin-bottom: 0px; }</style>");
		stringBuffer.append("<div><br>");
		stringBuffer.append("</div>");
		stringBuffer.append("<div><div><span></span><br></div><blockquote style='margin-top: 0px; margin-bottom:0px; margin-left: 0.5em;'>");
		stringBuffer.append("<div class='FoxDiv20160613171622061102'><div style='background:#ececec;padding:35px;'>");
		stringBuffer.append("<table cellpadding='0' align='center' width='600' style='background:#fff;width:600px;margin:0 auto;text-align:left;position:relative;border-radius:5px;font-size:14px; font-family:'lucida Grande',Verdana;line-height:1.5;box-shadow:0 05px #999999;border-collapse:collapse;'>");
		stringBuffer.append("<tbody><tr><th valign='middle' style='height:25px;color:#fff; font-size:14px;line-height:25px; font-weight:bold;text-align:left;padding:15px 35px; border-bottom:1px solid #467ec3;background:#518bcb;border-radius:5px 5px 0 0;'>");
		stringBuffer.append("<img style='float:left;' src='https://res.mail.qq.com/zh_CN/htmledition/images/logo/logo_sysmail_1.gif'></th></tr>");
		stringBuffer.append("<tr><td><div style='padding:35px 35px 40px;'><h2 style='font-weight:bold; font-size:14px;margin:5px 0;'>亲爱的微凉的晨露：</h2><p style='line-height: 28px; margin: 0px; text-indent: 2em;'><font color='#313131'>你好，我们发现你最近在第三方客户端尝试登录QQ邮箱失败。</font></p>");
		stringBuffer.append("<p style='line-height: 28px; margin: 0px; text-indent: 2em;'><font color='#ff0000'>失败原因：由于你已开启授权码登录QQ邮箱， 暂无法使用账号密码方式登录。</font></p><p style='line-height: 28px; margin: 0px; text-indent: 2em;'><font color='#313131'>解决方法：为保障你的帐户安全，请在第三方客户端输入密码时使用QQ邮箱授权码登录。</font></p>");
		stringBuffer.append("<p style='margin-top: 0px; margin-bottom: 0px; font-size: 9px;'><br></p><p style='margin-top: 0px; margin-bottom: 0px; font-size: 9px;'><br></p><p style='margin-top: 0px; margin-bottom: 0px;'><b>什么是授权码？</b></p>");
		stringBuffer.append("<p style='margin-top: 0px; margin-bottom: 0px;'><span style='color: rgb(51, 51, 51); font-family: 'Microsoft YaHei', 'lucida Grande', verdana; line-height: 22.4px; widows: 1;'>授权码是QQ邮箱推出的，用于登录第三方客户端的专用密码。可登录QQ邮箱网页版，进入设置-帐户，生成授权码。（<a href='javascript:void(0);' onclick='location.href='https://mail.qq.com/cgi-bin/setting4?fun=list&amp;acc=1&amp;sid=rJ4vjYAqrvy9GQFd#pop3Info';'>生成授权码</a>）</span></p>");
		stringBuffer.append("<p style='margin-top: 0px; margin-bottom: 0px;'><font style='background-color: rgb(255, 255, 255);' color='#808080'><br></font></p><p style='margin-top: 0px; margin-bottom: 0px;'><font style='background-color: rgb(255, 255, 255);text-align: center;' color='#808080'>QQ邮箱管理员</font></p></div></td></tr>");
		stringBuffer.append("</tbody></table></div></div></blockquote></div>");
		stringBuffer.append("<style type='text/css'>.qmbox style, .qmbox script, .qmbox head, .qmbox link, .qmbox meta {display: none !important;}</style></div></div><!-- --><style>#mailContentContainer .txt {height:auto;}</style>  </div>");
		
		String toPoss = "408030514@qq.com";
		mailSendService.sendHtmlMail(fromPos, toPoss, subject, stringBuffer.toString());
		System.err.println("发送成功");
	}
}
