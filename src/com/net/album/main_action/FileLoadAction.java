package com.net.album.main_action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.ServiceAndClientProtocol;
import com.opensymphony.xwork2.ActionSupport;

public class FileLoadAction extends ActionSupport
{
	private static final long serialVersionUID = 2930108945455732579L;
	
	InputStream targetStream = null;
	
	public InputStream getTargetStream()
	{
		return targetStream;
	}


	public void setTargetStream(InputStream targetStream)
	{
		this.targetStream = targetStream;
	}


	private int id;				//图片id
	private int imageType;			//指示图片是 代理 图片 还是 真实图片请求
	

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getImageType()
	{
		return imageType;
	}


	public void setImageType(int imageType)
	{
		this.imageType = imageType;
	}


	public String execute()
	{
		try
		{
			DataAccessAbstract da = DataAccessAbstract.getDataAccess();
			if(id >0)
			{
				byte[] bs = null;
				if(imageType == ServiceAndClientProtocol.IMAGE_TYPE_HEAD)
					bs = da.getHeadBytes(id);
				else
				{
					Boolean isProxy = (imageType == ServiceAndClientProtocol.IMAGE_TYPE_PROXY_IMAGE)?true :false;
					bs = da.getPicBytes(id,isProxy);
				}
				
				if(bs != null)
					targetStream  = new ByteArrayInputStream(bs);
			}
			else
				targetStream  = new ByteArrayInputStream(da.getPicBytes(1,false));
				
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "fileLoadResult";
	}

}
