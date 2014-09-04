package com.net.album.file_access;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class FileAccessImp extends FileAccessAbstract
{

	@Override
	public String fileToString(String filePath)
	{
		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try
		{
			 fis = new FileInputStream(filePath);
			 baos = new ByteArrayOutputStream();
			 
			 int i;
			 while((i=fis.read())!=-1)
				 baos.write(i);
			baos.close();
			return  baos.toString("ISO-8859-1");
		}
		catch (Exception e)
		{
			return null;
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
	}

	
	@Override
	public void StringToFile(String desPath, String data)
	{
		
		FileOutputStream fis = null;
		try
		{
			fis = new FileOutputStream(desPath);
			fis.write(data.getBytes("ISO-8859-1"));
			fis.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
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
	}



	
	@Override
	public String fileCompressToString(String inputFilePath,int outPutWidth,int outPutHeight,boolean proportion)
	{

		ByteArrayOutputStream out = null;
		try
		{
			// 获得源文件
			File file = new File(inputFilePath);
			if (!file.exists())
				return null;
			
			
			Image img = ImageIO.read(file);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1)
				return null;
			else
			{
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (proportion == true)
				{
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) outPutWidth + 0.1;
					double rate2 = ((double) img.getHeight(null))
							/ (double) outPutHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				}
				else
				{
					newWidth = outPutWidth; // 输出的图片宽度
					newHeight = outPutHeight; // 输出的图片高度
				}
				
				
				
				BufferedImage tag = new BufferedImage((int) newWidth,(int) newHeight, BufferedImage.TYPE_INT_RGB);
				//Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH), 0, 0, null);
				out = new ByteArrayOutputStream();
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
				
				out.close();
				return out.toString("ISO-8859-1");
				
			}
		}
		catch (IOException ex)
		{
			return null;
		}
	}


	
	
	@Override
	public void fileCompressToFile(String inputFilePath, String outPutPath,int outPutWidth, int outPutHeight, boolean proportion)
	{
		FileOutputStream out = null;
		try
		{
			// 获得源文件
			File file = new File(inputFilePath);
			if (!file.exists())
				return ;
			
			
			Image img = ImageIO.read(file);
			// 判断图片格式是否正确
			if (img.getWidth(null) == -1)
				return ;
			else
			{
				int newWidth;
				int newHeight;
				// 判断是否是等比缩放
				if (proportion == true)
				{
					// 为等比缩放计算输出的图片宽度及高度
					double rate1 = ((double) img.getWidth(null))
							/ (double) outPutWidth + 0.1;
					double rate2 = ((double) img.getHeight(null))
							/ (double) outPutHeight + 0.1;
					// 根据缩放比率大的进行缩放控制
					double rate = rate1 > rate2 ? rate1 : rate2;
					newWidth = (int) (((double) img.getWidth(null)) / rate);
					newHeight = (int) (((double) img.getHeight(null)) / rate);
				}
				else
				{
					newWidth = outPutWidth; // 输出的图片宽度
					newHeight = outPutHeight; // 输出的图片高度
				}
				
				
				
				BufferedImage tag = new BufferedImage((int) newWidth,(int) newHeight, BufferedImage.TYPE_INT_RGB);
				//Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的 优先级比速度高 生成的图片质量比较好 但速度慢
				tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight,Image.SCALE_SMOOTH), 0, 0, null);
				out = new FileOutputStream(outPutPath);
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
				encoder.encode(tag);
			}
		}
		catch (IOException ex)
		{
			return ;
		}
		finally
		{
				try
				{
					if(out != null)
						out.close();
				}
				catch (IOException e)
				{
					return;
				}
		}
	}

	
	
	
}
