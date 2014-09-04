package com.net.album.main_action;

import java.io.UnsupportedEncodingException;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.Pic;
import com.net.album.entity.ServiceAndClientProtocol;
import com.opensymphony.xwork2.ActionSupport;

public class PicAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;
	private DataAccessAbstract da = DataAccessAbstract.getDataAccess();
	
	
	/*-----------------------------------modifyPicAction-----------------------------------------*/
	private String picName;
	private String picDescription;
	private int id;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getPicName()
	{
		return picName;
	}

	public void setPicName(String picName)
	{
		this.picName = picName;
	}

	public String getPicDescription()
	{
		return picDescription;
	}

	public void setPicDescription(String picDescription)
	{
		this.picDescription = picDescription;
	}

	
	private int result ;
	
	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public String  modify()
	{
		try
		{
			picName = new String(picName.getBytes("iso-8859-1"),"utf-8");
			picDescription = new String(picDescription.getBytes("iso-8859-1"),"utf-8");
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			da.modifyPicEntity(new Pic(id,0,picName,picDescription,null));
			result = ServiceAndClientProtocol.SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		
		return "result";
	}
	
	
	
	/*-----------------------------------obtainPicAction-----------------------------------------*/
	private Pic pic;
	

	public Pic getPic()
	{
		return pic;
	}

	public void setPic(Pic pic)
	{
		this.pic = pic;
	}


	public String obtain()
	{
		
		try
		{
			pic = da.getPicEntity(id);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "obtainPic";
	}
	
	
	
	/*-----------------------------------modifyCovcerAction-----------------------------------------*/
	private int albumId;
	
	public int getAlbumId()
	{
		return albumId;
	}

	public void setAlbumId(int albumId)
	{
		this.albumId = albumId;
	}

	public String modifyCover()
	{
		try
		{
			da.modifyCover(albumId,id);
			result = ServiceAndClientProtocol.SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		return "result";
	}

}
