package com.creditharmony.fortune.utils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.creditharmony.core.role.type.FortuneRole;
import com.creditharmony.core.users.entity.Role;
import com.creditharmony.core.users.entity.User;
import com.creditharmony.core.users.util.UserUtils;
import com.creditharmony.fortune.common.entity.SunIASFileInfo;

/**
 * 映像插件使用工具类
 * 配合配置文件 sunIAS.properties 使用
 * 
 * @author 王鹏飞
 *
 */
public enum SunIASUtil {

	INSTANCE;

	private final Properties prop;

	// ip 地址
	private final String ip;
	// 端口
	private final String port;
	// 路径
	private final String path;
	// 登录用户名
	private final String uid;
	// 登录密码
	private final String pwd;
	// 业务应用号
	private final String appId;
	// 操作员ID
	private final String userId;
	// 操作员名称
	private final String userName;
	// 机构ID
	private final String orgId;
	// 机构名称
	private final String orgName;
	// 对象名
	private final String objectName;
	// 文件级别
	private final String fileLevel;
	// 基础请求路径
	private final String bastUrl;

	private SunIASUtil() {

		prop = new Properties();
		try {
			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("sunIAS.properties");
			prop.load(in);
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.ip = prop.getProperty("sunIAS.ip");
		this.port = prop.getProperty("sunIAS.port");
		this.path = prop.getProperty("sunIAS.path");
		this.uid = prop.getProperty("sunIAS.UID");
		this.pwd = prop.getProperty("sunIAS.PWD");
		this.appId = prop.getProperty("sunIAS.AppID");
		this.userId = prop.getProperty("sunIAS.UserID");
		this.userName = prop.getProperty("sunIAS.UserName");
		this.orgId = prop.getProperty("sunIAS.OrgID");
		this.orgName = prop.getProperty("sunIAS.OrgName");

		this.objectName = prop.getProperty("sunIAS.info.OBJECT_NAME");
		this.fileLevel = prop.getProperty("sunIAS.info.FILELEVEL");

		StringBuffer br = new StringBuffer();
		br.append(this.ip);
		br.append(":");
		br.append(this.port);
		br.append(this.path);
		br.append("?UID").append("=").append(this.uid);
		br.append("&PWD").append("=").append(this.pwd);
		br.append("&AppID").append("=").append(this.appId);
		br.append("&UserID").append("=").append(this.userId);
		br.append("&UserName").append("=").append(this.userName);
		br.append("&OrgID").append("=").append(this.orgId);
		br.append("&OrgName").append("=").append(this.orgName);
		this.bastUrl = br.toString();
	}

	Properties getProp() {
		return prop;
	}

	String getIp() {
		return ip;
	}

	String getPort() {
		return port;
	}

	String getPath() {
		return path;
	}

	String getUid() {
		return uid;
	}

	String getPwd() {
		return pwd;
	}

	String getAppId() {
		return appId;
	}

	String getUserId() {
		return userId;
	}

	String getUserName() {
		return userName;
	}

	String getOrgId() {
		return orgId;
	}

	String getOrgName() {
		return orgName;
	}

	String getBastUrl() {
		return bastUrl;
	}

	String getObjectName() {
		return objectName;
	}

	String getFileLevel() {
		return fileLevel;
	}

	/**
	 * 获取SUNIAS的请求URL 索引格式 uuid:日期:创建者ID(userID) 单个索引列子
	 * 878c2319efd14ee7905802baba72ab80:20160122 所以集合例子
	 * 878c2319efd14ee7905802baba72ab80
	 * :20160122,878c2319efd14ee7905802baba72ab80:20160122
	 * 
	 * @param indexStr
	 *            文件索引
	 * @param module
	 *            查询模块
	 */
	public String getRequstURL(String indexStr, String module) {
		User user = UserUtils.getUser();
		String right = this.getRights(user, module);
		List<SunIASFileInfo> list = new ArrayList<SunIASFileInfo>();
		String[] indexs = indexStr.split(",");
		for (int i = 0, k = indexs.length; i < k; i++) {
			String[] str = indexs[i].split(":");
			SunIASFileInfo d = new SunIASFileInfo();
			d.setBusiSerialNo(str[0]);
			d.setQueryTime(str[1]);
			d.setRight(right);
			list.add(d);
		}
		StringBuffer br = new StringBuffer(this.bastUrl);
		for (int i = 0, k = list.size(); i < k; i++) {
			SunIASFileInfo info = list.get(i);
			br.append(this.getInfo(i, info.getBusiSerialNo(),
					info.getQueryTime(), info.getRight()));
		}
		return br.toString();
	}

	/**
	 * 获取用户权限 根据获取用户的全部权限
	 * 
	 * @param user
	 * @return
	 */
	private String getRights(User user, String module) {
		List<Role> roles = user.getRoleList();
		List<String> rights = new ArrayList<String>();
		for (int i = 0, k = roles.size(); i < k; i++) {
			String role = getConfigRight(roles.get(i).getId());
			if (role != null) {
				rights.add(role);
			}
		}
		// 默认权限
		if (rights.size() == 0) {
			return prop.getProperty("RIGHT." + module + ".OTHER");
		}
		return this.mergeRight(rights);
	};

	/**
	 * 权限合并 采用位或的概念将权限进行比较。如 1000000 与 0100000 结果 1100000
	 * 
	 * @param rights
	 *            权限集合
	 * @return
	 */
	private String mergeRight(List<String> rights) {
		StringBuffer b = new StringBuffer();
		boolean flag = false;
		for (int i = 0; i < 7; i++) {
			flag = false;
			for (int k = 0, n = rights.size(); k < n; k++) {
				char right = rights.get(k).charAt(i);
				if (right == '1') {
					flag = true;
					b.append(1);
					break;
				}
			}
			if (!flag) {
				b.append(0);
			}
		}
		return b.toString();
	}

	/**
	 * 获取配置文件中的权限
	 * 
	 * @return
	 */
	private String getConfigRight(String roleId) {
		String right = null;
		Set<Object> set = prop.keySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			String key = (String) i.next();
			if (key.startsWith("RIGHT.") && key.indexOf("OTHER") == -1) {
				String pk = key.substring(key.lastIndexOf(".") + 1);
				String strId = FortuneRole.valueOf(pk).id;
				if (roleId.equals(strId)) {
					right = prop.getProperty(key);
				}
			}
		}
		return right;
	}

	/**
	 * 文件信息参数
	 * 
	 * @param index
	 *            Info 下标1开始
	 * @param BUSI_SERIAL_NO
	 *            索引
	 * @param QUERY_TIME
	 *            查询日期
	 * @param RIGHT
	 *            权限
	 * @return
	 */
	private String getInfo(int index, String BUSI_SERIAL_NO, String QUERY_TIME,
			String RIGHT) {
		StringBuffer br = new StringBuffer("&info");
		br.append(++index);
		br.append("=");
		br.append("BUSI_SERIAL_NO:");
		br.append(BUSI_SERIAL_NO);
		br.append(";");
		br.append("OBJECT_NAME:");
		br.append(this.getObjectName());
		br.append(";");
		br.append("QUERY_TIME:");
		br.append(QUERY_TIME);
		br.append(";");
		br.append("FILELEVEL:");
		br.append(this.getFileLevel());
		br.append(";");
		br.append("RIGHT:");
		br.append(RIGHT);
		return br.toString();
	}

}
