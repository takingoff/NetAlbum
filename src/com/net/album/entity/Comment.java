package com.net.album.entity;

public class Comment
{
	public int id;
	public int uId;
	public int pId;
	public String msg;
	public String genTime;
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getuId()
	{
		return uId;
	}
	public void setuId(int uId)
	{
		this.uId = uId;
	}
	public int getpId()
	{
		return pId;
	}
	public void setpId(int pId)
	{
		this.pId = pId;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getGenTime()
	{
		return genTime;
	}
	public void setGenTime(String genTime)
	{
		this.genTime = genTime;
	}
	public Comment(int id, int uId, int pId, String msg, String genTime)
	{
		super();
		this.id = id;
		this.uId = uId;
		this.pId = pId;
		this.msg = msg;
		this.genTime = genTime;
	}
	public Comment()
	{
	}
	
	
}
