package com.net.album.main_action;

import java.io.UnsupportedEncodingException;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.ServiceAndClientProtocol;
import com.net.album.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RegistryAndModifyUserAction extends ActionSupport implements ModelDriven<User>
{
	private static final long serialVersionUID = 6233035323332077485L;
	
	
	public User user=new User();
	public int isRegistry;			//如果是1 那么视为 注册，否者视为修改
	
	public int getIsRegistry()
	{
		return isRegistry;
	}

	public void setIsRegistry(int isRegistry)
	{
		this.isRegistry = isRegistry;
	}

	public User getUser()
	{
		return user;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	@Override
	public User getModel()
	{
		return user;
	}
	
	

	private int result;
	public int getResult()
	{
		return result;
	}
	public void setResult(int result)
	{
		this.result = result;
	}

	public String execute()
	{
		try
		{
			//字符串 解码
			user.description = new String(user.description.getBytes("iso-8859-1"),"utf-8");
			user.name = new String(user.name.getBytes("iso-8859-1"),"utf-8");
			user.email = new String(user.email.getBytes("iso-8859-1"),"utf-8");
			user.psw = new String(user.psw.getBytes("iso-8859-1"),"utf-8");
			user.sex = new String(user.sex.getBytes("iso-8859-1"),"utf-8");
			
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		try
		{
			
			if(isRegistry == 1)
				registryUser(da);
			else
				modifyUser(da);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		
		return "registryResult";
	}
	
	
	private void modifyUser(DataAccessAbstract da) throws Exception
	{
		da.modifyUser(user);
		result = ServiceAndClientProtocol.SUCCESS;
	}
	
	private void registryUser(DataAccessAbstract da) throws Exception
	{
		int id = da.getUserIdByName(user.name);
		//如果存在用户名
		if(id > 0)
		{
			result = ServiceAndClientProtocol.REGISTRY_NAME_EXIST;
			return;
		}
		
		da.addUser(user);
		result = ServiceAndClientProtocol.SUCCESS;
	}
	
}
