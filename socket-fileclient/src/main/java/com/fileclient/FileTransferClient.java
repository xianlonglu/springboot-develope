package com.fileclient;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTransferClient {
	private static final Logger logger = LoggerFactory.getLogger(FileTransferClient.class);

	/**
	 * 向服务端传文件
	 * @param file 文件
	 * @param client socket客户端
	 */
	public void sendFile(File file, Socket client) {
		FileInputStream fis = null;
		DataOutputStream dos = null;
		try {
			if (file.exists()) {
				fis = new FileInputStream(file);
				dos = new DataOutputStream(client.getOutputStream());

				dos.writeUTF(file.getName());
				dos.flush();
				dos.writeLong(file.length());
				dos.flush();

				byte[] bytes = new byte[1024];
				int length = 0;
				long progress = 0L;
				while ((length = fis.read(bytes, 0, bytes.length)) != -1) {
					dos.write(bytes, 0, length);
					dos.flush();
					progress += length;
					logger.debug("| " + 100L * progress / file.length() + "% |");
				}
				logger.info("========" + file.getName() + "文件传输成功！ ========");
			}
		} catch (Exception e) {
			logger.error("========" + file.getName() + "文件传输失败！ ========", e);
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
				if (dos != null)
					dos.close();
			} catch (Exception e) {
				logger.error("自愿释放失败！！", e);
			}
		}
	}
}