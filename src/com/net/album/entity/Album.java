package com.net.album.entity;

public class Album
{
	
	public int id;					//相册id
	public int uId;					//相册所属 用户 id
	public String albumName;		//相册名
	public String albumDescription;	//说明
	public int coverId;				//封面 相片 id
	
	
	
	public Album()
	{
		super();
	}
	
	public Album(int id, int uId, String albumName, String albumDescription,
			int coverId)
	{
		super();
		this.id = id;
		this.uId = uId;
		this.albumName = albumName;
		this.albumDescription = albumDescription;
		this.coverId = coverId;
	}

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
	public String getAlbumName()
	{
		return albumName;
	}
	public void setAlbumName(String albumName)
	{
		this.albumName = albumName;
	}
	
	public String getAlbumDescription()
	{
		return albumDescription;
	}

	public void setAlbumDescription(String albumDescription)
	{
		this.albumDescription = albumDescription;
	}

	public int getCoverId()
	{
		return coverId;
	}
	public void setCoverId(int coverId)
	{
		this.coverId = coverId;
	}
	
	
	
	
}
