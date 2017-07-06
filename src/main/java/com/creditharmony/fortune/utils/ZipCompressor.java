package com.creditharmony.fortune.utils;

import java.io.BufferedInputStream;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

public class ZipCompressor {
	static final int BUFFER = 8192;

	public static void compress(HttpServletResponse response, String... pathName) {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", String.format("attachment; filename=\"data.zip\""));
		ZipOutputStream out = null;
		try {
			CheckedOutputStream cos = new CheckedOutputStream(response.getOutputStream(), new CRC32());
			out = new ZipOutputStream(cos);
			String basedir = "";
			ZipCompressor zipCompressor = new ZipCompressor();
			for (int i = 0; i < pathName.length; i++) {
				zipCompressor.compressFile(pathName[i], out, basedir);
			}
			out.flush();
			out.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/** 压缩一个文件 */
	private void compressFile(String pathName, ZipOutputStream out, String basedir) {
		try {
			BufferedInputStream bis = new BufferedInputStream(ZipCompressor.class.getResourceAsStream("/todjr/" + pathName));
			ZipEntry entry = new ZipEntry(basedir + pathName);
			out.putNextEntry(entry);
			int count;
			byte data[] = new byte[BUFFER];
			while ((count = bis.read(data, 0, BUFFER)) != -1) {
				out.write(data, 0, count);
			}
			bis.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}