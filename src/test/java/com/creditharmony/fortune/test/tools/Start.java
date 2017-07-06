package com.creditharmony.fortune.test.tools;

import org.mortbay.jetty.Server;

/**
 * 使用Jetty运行调试Web应用
 * 在Console输入回车停止服务
 * @Class Name Start
 * @author zhangyongsheng
 * @Create In 2015年11月11日
 */
public class Start {

	public static final int PORT = 8082;
	public static final String CONTEXT = "/fortune";
	public static final String BASE_URL = "http://localhost:8082/fortune";

	public static void main(String[] args) throws Exception {
		
		System.setProperty("java.awt.headless", "true");
		Server server = JettyUtils.buildDebugServer(PORT, CONTEXT);
		server.start();

		System.out.println("Hit Enter in console to stop server");
		
		if (System.in.read() != 0) {
			server.stop();
			System.out.println("Server stopped");
		}
		
	}
}
