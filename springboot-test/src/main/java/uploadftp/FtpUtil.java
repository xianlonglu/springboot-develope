package uploadftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * java 上传文件到ftp
 * 
 * @author Administrator
 *
 */
public class FtpUtil {

	private static Logger logger = LoggerFactory.getLogger(FtpUtil.class);

	private static final String errMsg = "创建FTPClient连接失败";


	/**
	 * 创建FTPClient连接
	 *
	 * @param ipAddr ip
	 * @param userName 用户
	 * @param pwd 密码
	 * @return FTPClient
	 * @throws Exception
	 */
	private static FTPClient getConnectFtp(String ipAddr, String userName,
			String pwd) throws Exception {
		return getConnectFtp(ipAddr, 21, userName, pwd);
	}

	/**
	 * 创建FTPClient连接
	 *
	 * @param ipAddr ip
	 * @param port 端口
	 * @param userName 用户
	 * @param pwd 密码
	 * @return FTPClient
	 * @throws Exception
	 */
	private static FTPClient getConnectFtp(String ipAddr, Integer port,
			String userName, String pwd) throws Exception {
		FTPClient ftpClient = new FTPClient();
		int reply;
		ftpClient.connect(ipAddr, 21);
		ftpClient.login(userName, pwd);
		ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
		reply = ftpClient.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftpClient.disconnect();
			logger.error("创建FTPClient连接。");
			throw new RuntimeException(errMsg);
		}
		return ftpClient;
	}

	/**
	 * 关闭FTPClient连接
	 *
	 * @param ftp
	 *            FTPClient
	 */
	public static void closeFtpClient(FTPClient ftpClient) {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				// 退出
				ftpClient.logout();
				// 断开连接
				ftpClient.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭流
	 *
	 * @param input
	 *            FileInputStream
	 */
	public static void closeInput(FileInputStream input) {
		try {
			// 关闭文件流
			if (input != null) {
				input.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 上传某文件文件到ftp
	 * 
	 * @param ftpClient
	 *            ftp连接
	 * @param filePath
	 *            file路径
	 */
	private static void storeFile(FTPClient ftpClient, String filePath) {
		File file2 = new File(filePath);
		FileInputStream input = null;
		try {
			input = new FileInputStream(file2);
			ftpClient.storeFile(file2.getName(), input);
		} catch (IOException e) {
			logger.error("上传某文件文件到ftp失败。", e);
			e.printStackTrace();
		} finally {
			closeInput(input);
		}
	}

	/**
	 * 
	 * @param ftpClient
	 *            ftp连接
	 * @param ftpPath
	 *            ftp路径
	 * @throws Exception
	 */
	private static void createFilePath(FTPClient ftpClient, String ftpPath)
			throws Exception {
		String[] paths = ftpPath.split("/");
		for (int i = 0; i < paths.length; i++) {
			// 创建上传的路径 该方法只能创建一级目录
			ftpClient.makeDirectory(paths[i]);
			// 指定上传路径
			ftpClient.changeWorkingDirectory(paths[i]);
		}
	}

	/**
	 * ftp上传文件或是文件夹
	 *
	 * @param file
	 *            File
	 * @throws Exception
	 */
	public static void uploadDirectory(FTPClient ftpClient, File file)
			throws Exception {
		if (file.isDirectory()) {
			ftpClient.makeDirectory(file.getName());
			ftpClient.changeWorkingDirectory(file.getName());
			String[] files = file.list();
			for (String fstr : files) {
				File file1 = new File(file.getPath() + "/" + fstr);
				if (file1.isDirectory()) {
					uploadDirectory(ftpClient, file1);
					ftpClient.changeToParentDirectory();
				} else {
					storeFile(ftpClient, file.getPath() + "/" + fstr);
				}
			}
		} else {
			storeFile(ftpClient, file.getPath());
		}
	}

	/**
	 * ftp上传文件到指定目录
	 *
	 * @param ftp
	 *            Ftp
	 * @param filePathList
	 *            上传文件的路径集合
	 * @param ftpPath
	 *            ftp上文件夹路径
	 * @throws Exception
	 */
	public static void uploadFile(FTPClient ftpClient,
			List<String> filePathList, String ftpPath) {
		FileInputStream input = null;
		try {
			createFilePath(ftpClient, ftpPath);
			for (String filePath : filePathList) {
				File file = new File(filePath);
				// 读取本地文件
				input = new FileInputStream(file);
				// 第一个参数是文件名
				ftpClient.storeFile(file.getName(), input);
			}
		} catch (Exception e) {
			logger.error("上传ftp失败。", e);
			throw new RuntimeException("上传ftp失败", e);
		} finally {
			try {
				// 关闭文件流
				if (input != null) {
					input.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws Exception {
		testFiles();

		testFileDirectory();
	}

	private static void testFiles() {
		List<String> filePathList = new ArrayList<String>();
		filePathList.add("d:/1.jpg");
		filePathList.add("d:/1.txt");
		filePathList.add("d:/lgg.jpg");

		String ftpPath = "/lxl/123/222";
		FTPClient ftpClient = null;
		try {
			ftpClient = getConnectFtp("10.10.10.2", "file", "file");
			uploadFile(ftpClient, filePathList, ftpPath);// 把文件上传在ftp上
		} catch (Exception e) {
			throw new RuntimeException("上传ftp失败", e);
		} finally {
			closeFtpClient(ftpClient);
		}
	}

	private static void testFileDirectory() {
		FTPClient ftpClient = null;
		try {
			ftpClient = getConnectFtp("10.10.10.2", "file", "file");
			String ftpPath = "/lxl/123/222";
			createFilePath(ftpClient, ftpPath);
			File file = new File("E:/123/2/logs");
			uploadDirectory(ftpClient, file);
		} catch (Exception e) {
			throw new RuntimeException("上传ftp失败", e);
		} finally {
			closeFtpClient(ftpClient);
		}
	}
}
