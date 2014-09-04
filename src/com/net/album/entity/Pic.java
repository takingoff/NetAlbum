package com.net.album.entity;

public class Pic
{

	
	public int id;
	public int aId; 				//所属相册id
	public String picName;			//相片名
	public String picDescription; 	//相片说明
	public String uploadTime;		//上传时间
	
	
	
	public Pic()
	{
		super();
	}
	public Pic(int id, int aId, String picName, String picDescription,
			String uploadTime)
	{
		super();
		this.id = id;
		this.aId = aId;
		this.picName = picName;
		this.picDescription = picDescription;
		this.uploadTime = uploadTime;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public int getaId()
	{
		return aId;
	}
	public void setaId(int aId)
	{
		this.aId = aId;
	}
	public String getPicName()
	{
		return picName;
	}
	public void setPicName(String picName)
	{
		this.picName = picName;
	}
	public String getUploadTime()
	{
		return uploadTime;
	}
	public void setUploadTime(String uploadTime)
	{
		this.uploadTime = uploadTime;
	}
	public String getPicDescription()
	{
		return picDescription;
	}
	public void setPicDescription(String picDescription)
	{
		this.picDescription = picDescription;
	}
	
	
	
}
