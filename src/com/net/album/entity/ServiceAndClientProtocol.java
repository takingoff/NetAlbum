package com.net.album.entity;

public class ServiceAndClientProtocol
{
	

	/*-----------------------------------服务器 地址-----------------------------------------*/
//	public static final String ServerAddress = "http://huitengdebaozi.oicp.net:8080";
	public static final String ServerAddress = "http://192.168.1.10:8080";
	
	
//	public static final String ServerAddress;
//	static
//	{
//		SaxReadConfig configReader = new SaxReadConfig();
//		ServerAddress = configReader.getServerAdd();
//		System.out.println(ServerAddress);
//	}
	
	
	/*-----------------------------------成功-----------------------------------------*/
	
	public static final int SUCCESS = 1; 		
	
	/*-----------------------------------访问失败-----------------------------------------*/
	public static final int SERVICER_NO_ACCESS = 100; 	//因为 客户端异常 未提交 访问
	public static final int SERVICER_DB_EXCEPTION = 101; 	//服务端 数据库异常
	public static final int SERVICE_ACCESS_TIMEOUT = 102; //访问服务器超时。
	public static final int SERVICE_RECIVE_WRONG = 103; 	//服务器获取数据有误
	
	
	
	
	/*-----------------------------------LoginAction loginState字段-----------------------------------------*/
	public static final int LOGIN_USER_NOT_EXIST = 2; 		//用于不存在
	public static final int LOGIN_PSW_ERROR = 3; 				//密码错误
	
	/*-----------------------------------registryAndModifyUserAction  registryState字段-----------------------------------------*/
	
	public static final int REGISTRY_NAME_EXIST = 2;			//用户名已经存在了
	
	
	/*-----------------------------------代理图片大小-----------------------------------------*/
	
	public static final int PROXY_IMAGE_WIDTH = 150;
	public static final int PROXY_IMAGE_HEIGHT = 150;
	
	public static final int COVER_IMAGE_WIDTH = 96;
	public static final int COVER_IMAGE_HEIGHT = 96;
	
	
	public static final int NATIVE_IMAGE_WIDTH = 96;
	public static final int NATIVE_IMAGE_HEIGHT = 96;
	
	
	/*-----------------------------------图片 代理 与 全图 标记  imageType 字段-----------------------------------------*/
	
	public static final int IMAGE_TYPE_REAL_IMAGE = 1;
	public static final int IMAGE_TYPE_PROXY_IMAGE = 2;
	public static final int IMAGE_TYPE_HEAD = 3;

	/*-----------------------------------upPic state-----------------------------------------*/
	public static final int UP_PIC_ALBUMID_INVALIDATE = 2;
	
	
	/*-----------------------------------entity type (For delete by id function)-----------------------------------------*/
	public static final int DEL_TYPE_ALBUM = 1;
	public static final int DEL_TYPE_PIC = 2;
	
	
	/*-----------------------------------face size-----------------------------------------*/
	
	public static final int FACE_SIZE = 96;
	
	
	/*-----------------------------------sex type-----------------------------------------*/
	
	public static final int SEX_FEMALE = 1;
	public static final int SEX_MALE = 2;
	public static final int SEX_IGNORE = 3;
	
	
	/*-----------------------------------listView rquest count at one time -----------------------------------------*/
	
	public static final int LISTVIEW_PAGE_SIZE = 10;
}
