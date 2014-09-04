package com.net.album.main_action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.net.album.config.StaticClass;
import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.Album;
import com.net.album.entity.ServiceAndClientProtocol;
import com.net.album.entity.User;
import com.opensymphony.xwork2.ActionSupport;

public class FileUpLoadAction extends ActionSupport
{
	private static final long serialVersionUID = 1L;

	private File file;
	private String fileName;		
	private String id;				//相册 id 或者是 用户id
	private String type;			//指定 是上传 头像 还是 上传 一般图片
	

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName)
	{
		this.fileName = fileName;
		
	}

	public File getFile()
	{
		return file;
	}

	public void setFile(File file)
	{
		this.file = file;
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
		try
		{
			this.id = new String(id.getBytes(),"unicode");
			this.type = new String(type.getBytes(),"unicode");
			this.fileName = new String(fileName.getBytes(),"unicode");
			
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
			this.fileName = "noName";
		}
		
		if(file == null)
		{
			result = ServiceAndClientProtocol.SERVICE_RECIVE_WRONG;
			return "fileUploadResult";
		}
		
		FileInputStream fis = null;
		try
		{
			
			int isUploadHead = Integer.parseInt(type);
			
			if(isUploadHead == 1)
				uploadHead();
			else
				uploadImage();
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		finally
		{
				try
				{
					if(fis != null)
						fis.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		
		return "fileUploadResult";
	}

	
	private void uploadImage() throws Exception
	{

		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		
		int aid = Integer.parseInt(id);
		Album album = da.getAlbumById(aid);
		
		//如果 aid 不存在 那么不要 传 照片失败
		if(album == null)
		{
			result = ServiceAndClientProtocol.UP_PIC_ALBUMID_INVALIDATE;
			return;
		}
		
		da.addPic(StaticClass.fileToBytes(this.file),
				StaticClass.scaleAndCutToProxyImage(this.file,ServiceAndClientProtocol.PROXY_IMAGE_WIDTH,ServiceAndClientProtocol.PROXY_IMAGE_HEIGHT)				
				,this.fileName,aid);
		
		if(album.coverId <0)
		{
			List<Integer> list = da.getPicidsByAlbumId(aid);
			da.updateAlbumCover(aid,list.get(0));
		}
		
		result = ServiceAndClientProtocol.SUCCESS;
	}
	
	private void uploadHead() throws Exception
	{
		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		
		int uid = Integer.parseInt(id);
		User user = da.getUserById(uid);
		
		//如果 aid 不存在 那么不要 传 照片失败
		if(user == null)
		{
			result = ServiceAndClientProtocol.UP_PIC_ALBUMID_INVALIDATE;
			return;
		}
		
		da.addHead(uid,StaticClass.fileToBytes(this.file));
		
		result = ServiceAndClientProtocol.SUCCESS;
	}
	
	
	
	

	
	
	
	
	
	
	
//	
//	public class FileToDB implements Runnable
//	{
//		
//		File tempFile;
//		String tempFileName;
//		int tempAId;
//		public FileToDB(File file,String fileName,int aId)
//		{
//			this.tempFile = file;
//			this.tempFileName = fileName;
//			this.tempAId = aId;
//		}
//		
//		@Override
//		public void run()
//		{
//			DataAccessAbstract da = DataAccessAbstract.getDataAccess();
//			FileInputStream fis = null;
//			try
//			{
//				da.addPic(StaticClass.fileToBytes(this.tempFile),
//						StaticClass.fileToProxyBytes(this.tempFile,
//								ServiceAndClientProtocol.PROXY_IMAGE_WIDTH,ServiceAndClientProtocol.PROXY_IMAGE_HEIGHT,StaticClass.PROXY_COMPRESS_PROPORTION),
//						this.tempFileName,this.tempAId);
//				
//			}
//			catch (Exception e)
//			{
//				
//				e.printStackTrace();
//			}
//			finally
//			{
//					try
//					{
//						if(fis != null)
//							fis.close();
//					}
//					catch (IOException e)
//					{
//						e.printStackTrace();
//					}
//			}
//			
//		}
//		
//	}
	
	
	
}


