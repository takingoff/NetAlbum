package com.net.album.main_action;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.ServiceAndClientProtocol;
import com.opensymphony.xwork2.ActionSupport;

public class DelByIdAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;

	
	private int type;
	private int delId;
	public int getType()
	{
		return type;
	}
	public void setType(int type)
	{
		this.type = type;
	}

	public int getDelId()
	{
		return delId;
	}
	public void setDelId(int delId)
	{
		this.delId = delId;
	}




	private int delResult;
	public int getDelResult()
	{
		return delResult;
	}
	public void setDelResult(int delResult)
	{
		this.delResult = delResult;
	}
	
	
	@Override
	public String execute() throws Exception
	{
		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		try
		{
			da.delById(delId,type);
			delResult = ServiceAndClientProtocol.SUCCESS;
		}
		catch (Exception e)
		{
			delResult = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		
		return "delResult";
	}
	

}
