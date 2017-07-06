package com.creditharmony.fortune.app.webservice.ocr.utils;

public abstract class DataTypeUtils {

    /**
     * error 服务异常
     */
    public static final String ERROR_SERVER_ERROR = "500";
    
    /**
     * error jsonStr 为空
     */
    public static final String ERROR_JSONSTR_BLANK = "1";

    /**
     * error 对象转换异常
     */
    public static final String ERROR_OBJECT_CONVERT_ERROR = "2";
    
    /**
     * error 必要参数为空
     */
    public static final String ERROR_NECESSARY_PARAM_BLANK = "3";
    
    /**
     * memo 服务异常
     */
    public static final String MEMO_SERVER_ERROR = "服务异常";
    
    /**
     * memo 用户名或密码错误
     */
    public static final String MEMO_USERNAME_OR_PASSWORD_ERROR = "用户名或密码错误";
    
    /**
     * memo 身份证无效，不是合法的身份证号码
     */
    public static final String MEMO_IDCARD_INVALID = "身份证无效，不是合法的身份证号码";
    
    /**
     * memo 银行卡信息不全
     */
    public static final String MEMO_BANKINFO_INCOMPLETE  = "银行卡信息不全";

    /**
     * 编码格式  UTF-8
     */
    public static final String ENCODING_UTF8 = "UTF-8";
    
    /**
     * 配置文件名称
     */
    public static final String FILENAME_CONFIG_PROPERTIES = "config.properties";
    
    /**
     * 配置文件key wsdl url 汇金
     */
    public static final String KEY_WSDL_URL_LOAN = "wsdl_url_loan";
    
    /**
     * 配置文件key wsdl url 财富
     */
    public static final String KEY_WSDL_URL_LEND = "wsdl_url_lend";
    
    /**
     * 配置文件key 财富投资模式文件路径
     */
    public static final String KEY_LEND_PRODUCTS_FILE_PATH = "lend_products_file_path";
    
    /**
     * 配置文件key app版本信息文件路径
     */
    public static final String KEY_APP_VERSION_FILE_PATH = "app_version_file_path";
    
    /**
     * 配置文件key 汇金图片存放根目录
     */
    public static final String KEY_PIC_ROOT_PATH = "pic_root_path";
    
    /**
     * 配置文件key 汇金图片存放根目录
     */
    public static final String KEY_SHAREFILE_ROOT_PATH = "shareFile_root_path";
    
    /**
     * 配置文件key 汇金图片存放根目录
     */
    public static final String KEY_PIC_ROOT_PATH_CF = "pic_root_path_cf";
    
    /**
     * 配置文件key 汇金文件服务器FTP的IP
     */
    public static final String KEY_FTP_IP = "ftp_ip";
    
    /**
     * 配置文件key 汇金文件服务器FTP的端口
     */
    public static final String KEY_FTP_PORT = "ftp_port";
    
    /**
     * 配置文件key 汇金文件服务器FTP的用户名
     */
    public static final String KEY_FTP_USERNAME = "ftp_username";
    
    /**
     * 配置文件key 汇金文件服务器FTP的密码
     */
    public static final String KEY_FTP_PASSWORD = "ftp_password";
    
    /**
     * 手机端参数名称 jsonStr
     */
    public static final String PARAM_JSONSTR = "jsonStr";
    
    /**
     * 手机端参数名称 namePic 姓名图片名称
     */
    public static final String PARAM_NAME_PIC = "namePic";
    
    /**
     * 手机端参数名称 certNumPic 身份证号图片名称
     */
    public static final String PARAM_CERTNUM_PIC = "certNumPic";
    
    /**
     * 手机端参数名称 accountidPic 银行卡号图片名称
     */
    public static final String PARAM_ACCOUNTID_PIC = "accountidPic";
    
    /**
     * 日期格式化 yyyymmdd
     */
    public static final String PATTERN_YYYYMMDD = "yyyyMMdd";
    
    /**
     * 分隔符 "/"
     */
    public static final String SEPARATOR_SLASH = "/";
    
    /**
     * 分隔符 "#"
     */
    public static final String SEPARATOR_HASHKEY = "#";
    
    /**
     * 分隔符 日志
     */
    public static final String SEPARATOR_FOR_LOG = "---------------- ";

    /**
     * 图片扩展名
     */
    public static final String PICTURE_EXTNAME = ".png";
    
    /**
     * 财富数据库存放图片路径的根目录
     */
    public static final String PIC_ROOT_PATH_CFDB_APP = "app";
    
    /**
     * 配置文件key wsdl url 汇金员工展业
     */
    public static final String WSDL_URL_IYGZY = "wsdl_url_iYgzy";
    
}
