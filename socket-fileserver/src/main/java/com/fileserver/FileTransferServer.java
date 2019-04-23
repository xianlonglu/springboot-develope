package com.fileserver;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.RoundingMode;
import java.net.Socket;
import java.text.DecimalFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileTransferServer {
	private static final Logger logger = LoggerFactory.getLogger(FileTransferServer.class);

	private static DecimalFormat df = null;
	public String newUploadPath;
	public String oldUploadPath;

	public FileTransferServer(String newUploadPath, String oldUploadPath) {
		this.newUploadPath = newUploadPath;
		this.oldUploadPath = oldUploadPath;
	}

	/**
	 * 接收保存文件
	 * 
	 * @param socket
	 */
	public void saveFile(Socket socket) {
		DataInputStream dis = null;
		FileOutputStream fos = null;
		try {
			dis = new DataInputStream(socket.getInputStream());
			// 文件名和长度
			String fileName = dis.readUTF();
			long fileLength = dis.readLong();

			File directory = null;
			if (fileName.startsWith("SP"))
				directory = new File(this.newUploadPath);
			else {
				directory = new File(this.oldUploadPath);
			}

			if (!directory.exists()) {
				directory.mkdir();
			}

			File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
			fos = new FileOutputStream(file);

			// 开始接收文件
			byte[] bytes = new byte[1024];
			int length = 0;
			while ((length = dis.read(bytes, 0, bytes.length)) != -1) {
				fos.write(bytes, 0, length);
				fos.flush();
			}
			logger.info("=== 文件接收成功 [File Name：" + fileName + "] [Size：" + getFormatFileSize(fileLength) + "] ===");
		} catch (Exception e) {
			logger.error("文件接收失败！！", e);
			e.printStackTrace();
		} finally {
			try {
				if (fos != null) {
					fos.close();
				}
				if (dis != null)
					dis.close();
			} catch (Exception e) {
				logger.error("自愿释放失败！！", e);
			}
		}
	}

	/**
	 * 获得格式化后的文件大小
	 * 
	 * @param length
	 * @return
	 */
	private String getFormatFileSize(long length) {
		double size = length / 1073741824.0D;
		if (size >= 1.0D) {
			return df.format(size) + "GB";
		}
		size = length / 1048576.0D;
		if (size >= 1.0D) {
			return df.format(size) + "MB";
		}
		size = length / 1024.0D;
		if (size >= 1.0D) {
			return df.format(size) + "KB";
		}
		return length + "B";
	}

	static {
		df = new DecimalFormat("#0.0");
		df.setRoundingMode(RoundingMode.HALF_UP);
		df.setMinimumFractionDigits(1);
		df.setMaximumFractionDigits(1);
	}
}