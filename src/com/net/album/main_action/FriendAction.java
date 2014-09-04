package com.net.album.main_action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.ServiceAndClientProtocol;
import com.net.album.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class FriendAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private DataAccessAbstract da = DataAccessAbstract.getDataAccess();

//	age1,age2,name,sex,requestCount,id,targetIds,observerId,result,targetId

	
	/*-----------------------------------findFriendAction-----------------------------------------*/
	
	private String name = null;
	private int age1;
	private int age2;
	private int sex;
	private int requestCount;
	
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}


	public int getAge1()
	{
		return age1;
	}

	public void setAge1(int age1)
	{
		this.age1 = age1;
	}


	public int getAge2()
	{
		return age2;
	}

	public void setAge2(int age2)
	{
		this.age2 = age2;
	}


	public int getSex()
	{
		return sex;
	}

	public void setSex(int sex)
	{
		this.sex = sex;
	}


	public int getRequestCount()
	{
		return requestCount;
	}

	public void setRequestCount(int requestCount)
	{
		this.requestCount = requestCount;
	}

	
	List<User> friends;
	
	public List<User> getFriends()
	{
		return friends;
	}

	public void setFriends(List<User> friends)
	{
		this.friends = friends;
	}
	
	public String find()
	{
		try
		{
			name = new String(name.getBytes("iso-8859-1"),"utf-8");
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		int minAge;
		int maxAge;
		if(age1 >= age2)
		{
			minAge = age2;
			maxAge = age1;
		}
		else
		{
			minAge = age1;
			maxAge = age2;
		}
		
		String sSex = null;
		if(sex == ServiceAndClientProtocol.SEX_FEMALE)
			sSex = "女";
		else if(sex == ServiceAndClientProtocol.SEX_MALE)
			sSex = "男";
		
		try
		{
			
			friends  = da.findFriend(name,minAge,maxAge,sSex
					,ServiceAndClientProtocol.LISTVIEW_PAGE_SIZE*requestCount,ServiceAndClientProtocol.LISTVIEW_PAGE_SIZE);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "friends";
	}
	
	
	
	/*-----------------------------------queryMyFriendAction-----------------------------------------*/
	
	private int id;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String queryMy()
	{
		try
		{
			friends = da.getMyFriend(id,requestCount*ServiceAndClientProtocol.LISTVIEW_PAGE_SIZE,ServiceAndClientProtocol.LISTVIEW_PAGE_SIZE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "friends";
	}
	
	
	
	/*-----------------------------------addFriendAction-----------------------------------------*/
	private int result ;
	
	private int observerId;
	
	private List<Integer> targetIds;
	
	
	public int getObserverId()
	{
		return observerId;
	}

	public void setObserverId(int observerId)
	{
		this.observerId = observerId;
	}

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public List<Integer> getTargetIds()
	{
		return targetIds;
	}

	public void setTargetIds(List<Integer> targetIds)
	{
		this.targetIds = targetIds;
	}

	public String add()
	{
		try
		{
			da.addFriends(observerId,targetIds);
			result = ServiceAndClientProtocol.SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		return "friendOperation" ;
	}
	
	
	/*-----------------------------------delFriendAction-----------------------------------------*/
	
	private int targetId;
	public int getTargetId()
	{
		return targetId;
	}

	public void setTargetId(int targetId)
	{
		this.targetId = targetId;
	}

	public String del()
	{
		try
		{
			da.delFriend(observerId,targetId);
			result = ServiceAndClientProtocol.SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		return "friendOperation" ;
	}
	
}
