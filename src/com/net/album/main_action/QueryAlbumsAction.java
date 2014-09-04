package com.net.album.main_action;

import java.util.List;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.Album;
import com.opensymphony.xwork2.ActionSupport;

public class QueryAlbumsAction extends ActionSupport
{
	private static final long serialVersionUID = -3094377846085640360L;
	
	
	public int uId;
	public int getuId()
	{
		return uId;
	}
	public void setuId(int uId)
	{
		this.uId = uId;
	}



	public List<Album> albums ;
	
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
			albums = da.getUserAlbums(uId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "queryAlbumsResult";
	}
	
	
}
