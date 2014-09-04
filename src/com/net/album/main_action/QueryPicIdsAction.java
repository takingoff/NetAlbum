package com.net.album.main_action;

import java.util.List;

import com.net.album.data_access.DataAccessAbstract;
import com.opensymphony.xwork2.ActionSupport;

public class QueryPicIdsAction extends ActionSupport
{
	private static final long serialVersionUID = 5562002244208239407L;
	
	
	public int aId;
	public int getaId()
	{
		return aId;
	}

	public void setaId(int aId)
	{
		this.aId = aId;
	}

	
	
	List<Integer> ids;

	public List<Integer> getIds()
	{
		return ids;
	}

	public void setIds(List<Integer> ids)
	{
		this.ids = ids;
	}

	public String execute()
	{
		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		try
		{
			
			ids = da.getPicidsByAlbumId(aId);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return "queryPicIdsResult";
	}

}
