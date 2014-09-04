package com.net.album.main_action;

import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.Album;
import com.net.album.entity.ServiceAndClientProtocol;
import com.net.album.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	
	
	public String account;
	public String getAccount()
	{
		return account;
	}
	public void setAccount(String account)
	{
		this.account = account;
	}
	
	public String password;
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}

	
	public User user;
	@JSON(name="user")
	public User getUser()
	{
		return user;
	}
	public void setUser(User user)
	{
		this.user = user;
	}
	
	public int loginState;
	public int getLoginState()
	{
		return loginState;
	}
	public void setLoginState(int loginState)
	{
		this.loginState = loginState;
	}
	
	
	public List<Album> albums;
	public List<Album> getAlbums()
	{
		return albums;
	}
	public void setAlbums(List<Album> albums)
	{
		this.albums = albums;
	}
	
	
	
	public String execute()
	{
		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		try
		{
			//解码
			account = new String(account.getBytes("iso-8859-1"),"utf-8");
			password = new String(password.getBytes("iso-8859-1"),"utf-8");
			
			
			user = da.getUserByName(account);
			if(user == null)
			{
				//不存在用户
				loginState = ServiceAndClientProtocol.LOGIN_USER_NOT_EXIST;
				return "loginResult";
			}
			
			if(!user.psw.equals(password))
			{
				//密码错误
				loginState = ServiceAndClientProtocol.LOGIN_PSW_ERROR;
				return "loginResult";
			}
			
			//登录成功
			loginState = ServiceAndClientProtocol.SUCCESS;

			//获取相册列表
			albums = da.getUserAlbums(user.id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			loginState = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		
		return "loginResult";
	}
	
	
	
	
}
