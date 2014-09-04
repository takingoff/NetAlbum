package com.net.album.config;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class StaticClass
{
	
	
	public static String dbFilePath ;
	public static String albumRoot ;
	static
	{
		//数据库文件放在 StaticClass.class 所在文件夹下，名为 netAlbum
		
		dbFilePath = getCurrentClassPath(StaticClass.class) +"\\NetAlbum.db";
//		dbFilePath ="E:\\Users\\christopher\\Workspaces\\MyEclipse 10\\NetAlbum\\src\\com\\net\\album\\config\\NetAlbum.db";
//		albumRoot = "E:\\Program Files\\Apache Software Foundation\\Tomcat 6.0\\webapps\\NetAlbum\\albumRoot";
	}
	
	
	 /*得到当前类的编译路径*/
    public static String getCurrentClassPath(Class<?> clazz)
    {
        String path = "";
        try
        {
            File file = new File(clazz.getResource("").toURI());
            path = file.getAbsolutePath();
        }catch (URISyntaxException e){
            e.printStackTrace();
        }
        return path;
    }
    
    
  	public static byte[] fileToBytes(File file)
  	{
  		
  		FileInputStream fis = null;
		ByteArrayOutputStream baos = null;
		try
		{
			 fis = new FileInputStream(file);
			 baos = new ByteArrayOutputStream();
			 
			 int i;
			 byte[] bs = new byte[8192];
			 while((i=fis.read(bs))!=-1)
				 baos.write(bs,0,i);
			baos.close();
			return  baos.toByteArray();
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
  	
  	
  	public static final boolean PROXY_COMPRESS_PROPORTION = true;
  	/**
  	 * @param file
  	 * @param outPutHeight
  	 * @param outPutWidth
  	 * @param proportion  如果指定false（不按比例压缩。也就是 outputHeight 和outputWidth 参数指定的值将有效，图片会可能变形）
  	 * 		指定为true 图片保持原来的长宽比，outputWidth 和 outputHeight 两个参数只有一个有效 
  	 * @return
  	 */
  	public static byte[] fileToProxyBytes(File file,int outPutHeight,int outPutWidth,boolean proportion)
	{
		try
		{
			Image img = ImageIO.read(file);
			//如果读取失败
			if(img == null)
				return null;
			
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

				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				
				// JPEGImageEncoder可适用于其他图片类型的转换
				JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
				encoder.encode(tag);
				
				bos.close();
				return bos.toByteArray();
			}
		}
		catch (IOException ex)
		{
			return null;
		}
	}

  	
  	public static byte[] scaleAndCutToProxyImage(File srcImageFile,int destWidth,int destHeight) 
  	{
         try {
             Image img;
             ImageFilter cropFilter;
             // 读取源图像
             BufferedImage bi = ImageIO.read(srcImageFile);
             int srcWidth = bi.getWidth(); // 源图宽度
             int srcHeight = bi.getHeight(); // 源图高度
            
             
             
             int cropEdge ;
             int cropX;
             int cropY;
             if(srcWidth<srcHeight)
             {
            	 cropX = 0;
            	 cropY = (srcHeight - srcWidth)/2;
            	 cropEdge = srcWidth;
             }
             else
             {
            	 cropY = 0;
            	 cropX = (srcWidth - srcHeight)/2;
            	 cropEdge = srcHeight;
            	 
             }
             
             
             cropFilter = new CropImageFilter(cropX, cropY, cropEdge,cropEdge);
             
             Image image = Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(bi.getSource(), cropFilter));

             img = image.getScaledInstance(destWidth, destHeight,Image.SCALE_DEFAULT);//获取缩放后的图片大小
            
             
             BufferedImage tag = new BufferedImage(destWidth, destHeight,BufferedImage.TYPE_INT_RGB);
             Graphics g = tag.getGraphics();
             g.drawImage(img, 0, 0, null); // 绘制截取后的图
             g.dispose();
             
             // 输出为文件
             ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ImageIO.write(tag, "JPG", bos);
//             ImageIO.write(tag,"PNG",new File("C:/Users/christopher/Desktop/testCutNEW.png"));
             bos.close();
             
            return bos.toByteArray();
            
         }
         catch (Exception e)
         {
             e.printStackTrace();
             return null;
         }
     }

  	

}
