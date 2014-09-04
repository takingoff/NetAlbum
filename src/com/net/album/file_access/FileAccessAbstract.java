package com.net.album.file_access;

public abstract class FileAccessAbstract
{
	
	
	public static FileAccessAbstract getFileAccess()
	{
		try
		{
			return (FileAccessAbstract)Class.forName("com.net.album.file_access.FileAccessImp").newInstance();
		}
		catch (Exception e)
		{
			return null;
		}
		
	}

	
	
	/**
	 * @param filePath
	 * @return	将指定文件的字节数组 转换成 iso-8859-1 类型字符串返回
	 */
	public abstract String fileToString(String filePath);
	
	
	
	/**
	 * @param desPath (目标文件路径，以及文件名称)
	 * @param data		(文件数据)
	 * 
	 * 将指定数据data字符串   以 iso-8859-1 解码成 二进制文件
	 */
	public abstract void StringToFile(String desPath,String data);
	
	
	/**
	 * @param inputFilePath	压缩文件路径
	 * @param outPutWidth	压缩后的宽度
	 * @param outPutHeight	压缩后高度
	 * @param proportion 是否为等比压缩	(如果为等比放缩，放缩后图片长宽将会和之前的长宽比相同，而不是参数指定长宽)(非等比放缩，长宽将是参数指定的长宽)
	 * @return		如果不能压缩 ，或者文件不存在，或者异常，那么 返回null ，否者返回文件 iso-8859-1 编码的字符流
	 */
	public abstract String fileCompressToString(String inputFilePath,int outPutWidth,int outPutHeight,boolean proportion);
	
	
	
	
	/**
	 * @param inputFilePath	压缩文件路径
	 * @param outPutWidth	压缩后的宽度
	 * @param outPutHeight	压缩后高度
	 * @param proportion 是否为等比压缩  (如果为等比放缩，放缩后图片长宽将会和之前的长宽比相同，而不是参数指定长宽)(非等比放缩，长宽将是参数指定的长宽)
	 * 	如果不能压缩 ，或者文件不存在，或者异常，那么不会生成文件 否者生成 指定路径的文件
	 */
	public abstract void fileCompressToFile(String inputFilePath,String outPutPath,int outPutWidth,int outPutHeight,boolean proportion);
	
	
	
}




/*SaxReadConfig saxReader = new SaxReadConfig();

File filePath = new File(saxReader.getAlbumRoot()+"/12");
if(!filePath.exists())
	filePath.mkdirs();


File file = new File(saxReader.getAlbumRoot() +"/12/test.txt");
FileOutputStream fos= null;
OutputStreamWriter osw = null;
BufferedWriter bw = null;
try
{
	fos = new FileOutputStream(file);
	osw = new OutputStreamWriter(fos);
	bw = new BufferedWriter(osw);
	
	bw.write("好样的haoyangde！");
}
catch (Exception e)
{
	e.printStackTrace();
}
finally
{
	try
	{
		if(bw != null)
			bw.close();
		if(osw != null)
			osw.close();
		if(fos != null)
			fos.close();
		
	}
	catch (Exception e)
	{
		e.printStackTrace();
	}
	
}*/
