package uploadftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * java 上传文件到ftp
 * 
 * @author Administrator
 *
 */
public class FtpUtil {

	private static FTPClient ftp;

	/**
	 * 获取ftp连接
	 *
	 * @param f
	 * @return
	 * @throws Exception
	 */
	public static boolean connectFtp(Ftp f) throws Exception {
		ftp = new FTPClient();
		boolean flag = false;
		int reply;
		if (f.getPort() == null) {
			ftp.connect(f.getIpAddr(), 21);
		} else {
			ftp.connect(f.getIpAddr(), f.getPort());
		}
		ftp.login(f.getUserName(), f.getPwd());
		ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			return flag;
		}
		ftp.changeWorkingDirectory(f.getPath());
		flag = true;
		return flag;
	}

	/**
	 * 关闭ftp连接
	 */
	public static void closeFtp() {
		if (ftp != null && ftp.isConnected()) {
			try {
				// 退出
				ftp.logout();
				// 断开连接
				ftp.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * ftp上传文件或是文件夹
	 *
	 * @param f
	 * @throws Exception
	 */
	public static void upload(File f) throws Exception {
		if (f.isDirectory()) {
			ftp.makeDirectory(f.getName());
			ftp.changeWorkingDirectory(f.getName());
			String[] files = f.list();
			for (String fstr : files) {
				File file1 = new File(f.getPath() + "/" + fstr);
				if (file1.isDirectory()) {
					upload(file1);
					ftp.changeToParentDirectory();
				} else {
					File file2 = new File(f.getPath() + "/" + fstr);
					FileInputStream input = new FileInputStream(file2);
					ftp.storeFile(file2.getName(), input);
					input.close();
				}
			}
		} else {
			File file2 = new File(f.getPath());
			FileInputStream input = new FileInputStream(file2);
			ftp.storeFile(file2.getName(), input);
			input.close();
		}
	}

	/**
	 * ftp上传文件到指定目录
	 *
	 * @param f
	 * @throws Exception
	 */
	public static void upload(File f, String path) {
		FileInputStream input = null;
		try {
			String[] paths = path.split("/");
			for (int i = 0; i < paths.length; i++) {
				// 创建上传的路径 该方法只能创建一级目录
				ftp.makeDirectory(paths[i]);
				// 指定上传路径
				ftp.changeWorkingDirectory(paths[i]);
			}
			// 读取本地文件
			input = new FileInputStream(f);
			// 第一个参数是文件名
			ftp.storeFile(f.getName(), input);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭文件流
				if (input != null) {
					input.close();

				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			closeFtp();
		}
	}

	public static void main(String[] args) throws Exception {
		Ftp f = new Ftp();
		f.setIpAddr("10.10.10.2");
		f.setUserName("file");
		f.setPwd("file");
		connectFtp(f);
		File file = new File("d:/git1/1.txt");
		// upload(file);// 把文件上传在ftp上
		System.out.println("上传文件完成。。。。");

		upload(file, "/111/git222/git");// 把文件上传在ftp上
		/*
		 * File f = new File("d:/git/1.txt"); if(f.isDirectory()){
		 * System.out.println("路径"); }else{ System.out.println("文件");
		 * System.out.println(f.getName()); }
		 */

	}
}
