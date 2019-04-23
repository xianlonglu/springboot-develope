package com.fileserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com" })
public class FileServerApp implements CommandLineRunner {

	@Value("${fileserver.port}")
	private int port;

	@Value("${fileserver.newUploadPath}")
	public String newUploadPath;

	@Value("${fileserver.oldUploadPath}")
	public String oldUploadPath;

	/**
	 * 启动类
	 */
	public static void main(String[] args) {
		SpringApplication.run(FileServerApp.class, args);
	}

	/**
	 * 文件传输执行方法	
	 */
	public void run(String[] args) throws IOException {
		ServerSocket ss = new ServerSocket(this.port);
		FileTransferServer fts = new FileTransferServer(this.newUploadPath, this.oldUploadPath);
		while (true) {
			Socket socket = ss.accept();
			fts.saveFile(socket);
			socket.close();
		}
	}
}