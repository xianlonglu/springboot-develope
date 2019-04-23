package com.fileclient;

import java.io.File;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com" })
public class FileClientApp implements CommandLineRunner {
	private static final Logger logger = LoggerFactory.getLogger(FileClientApp.class);

	@Value("${fileclient.szPath}")
	private String szPath;

	@Value("${fileclient.bakPath}")
	private String bakPath;

	@Value("${fileclient.host}")
	private String host;

	@Value("${fileclient.port}")
	private int port;

	/**
	 * 启动类
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(FileClientApp.class, args);
	}

	/**
	 * 覆盖父类方法
	 */
	public void run(String[] args) throws Exception {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		File monthBakPath = new File(this.bakPath + File.separator + sdf.format(d));

		FileTransferClient ftf = new FileTransferClient();
		Socket client = null;
		File file = new File(this.szPath);
		File[] fs = file.listFiles();
		for (File f : fs) {
			if ((!f.isFile()) || (f.getName().lastIndexOf(".zip") == -1))
				continue;
			// 传输文件
			client = new Socket(this.host, this.port);
			ftf.sendFile(f, client);
			client.close();

			// 备份文件
			File bakFile = new File(monthBakPath + File.separator + f.getName());

			if (bakFile.exists()) {
				logger.info("备份文件：" + bakFile.getAbsolutePath() + " 存在！备份覆盖！");
				bakFile.delete();
			}
			FileUtils.moveFileToDirectory(f, monthBakPath, true);
		}
	}
}