package com.net.album.main_action;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.Album;
import com.net.album.entity.ServiceAndClientProtocol;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AddOrModifyAlbumAction extends ActionSupport implements ModelDriven<Album>
{
	
	private static final long serialVersionUID = 1338767675776231554L;
	

	private int type;
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}


	public Album album = new Album();
	public Album getAlbum()
	{
		return album;
	}
	public void setAlbum(Album album)
	{
		this.album = album;
	}
	@Override
	public Album getModel()
	{
		return album;
	}
	
	
	
	public int result;
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
		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		
		try
		{
			album.albumDescription = new String(album.albumDescription.getBytes("iso-8859-1"),"utf-8");
			album.albumName = new String(album.albumName.getBytes("iso-8859-1"),"utf-8");
			
			if(type == 0)
				da.addAlbum(album);
			else if(type == 1)
				da.modifyAlbum(album);
			
			result = ServiceAndClientProtocol.SUCCESS;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		
		
		return "addOrModifyAlbumResult";
	}

	
}
