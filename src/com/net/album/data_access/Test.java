package com.net.album.data_access;

import java.util.List;

import com.net.album.entity.User;











public class Test
{
	
	static public  void main(String[] ar)
	{
		
		DataAccessAbstract da = DataAccessAbstract.getDataAccess();
		
		try
		{
			
//			da.addUser(new User(0,"tangli","mypsw","myemail",21,"男","说明"));
			
//			System.out.println(da.getUserByName("唐力").name);
//			System.out.println(da.getUserIdByName("唐力"));
			
//			da.addPic(new Pic(0,1,"pic1.jpg","shuomign",null));
//			da.addAlbum(new Album(0,4,"alName","descrip",1));
//			System.out.println(da.getUserById(5).name);
//			System.out.println(da.getAlbumById(5).albumDescription);
//			
//			List<Album> list = da.getUserAlbums(4);
//			for(Album al:list)
//			{
//				System.out.println(al.albumName);
//			}
			
//			String data;
//			data = fa.fileCompressToString("E:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/NetAlbum/albumRoot/12/image.jpg",150,150,false);
//			data = fa.fileToString("E:\\Installer\\practical\\tool\\C32Asm.zip");
//			fa.StringToFile("C:/Users/christopher/Desktop/copy.jpeg",data);
//			System.out.println(System.currentTimeMillis());
			
			
			
			
//			byte[] bys = {7,8,1,2,3,4,5,6};

			
			
//			Long now = System.currentTimeMillis();
//			bys = StaticClass.fileToByte(new File("C:/Users/christopher/Desktop/p2.jpg"));
//			bys =  (byte[])da.bytesFromDB(1,false);
//			System.out.println((System.currentTimeMillis()-now)/1000);
			
			
//			now = System.currentTimeMillis();
//			da.fileToDB(bys,1,false);
//			InputStream bis = new ByteArrayInputStream(bys);
//			System.out.println((System.currentTimeMillis()-now)/1000);
			
			
			
//			now = System.currentTimeMillis();
//			FileOutputStream fis = new FileOutputStream(new File("C:/Users/christopher/Desktop/dddddbbbb.jpg"));
//			byte[] tempByte = new byte[8192];
//			int count;
//			while((count = bis.read(tempByte)) != -1)
//				fis.write(tempByte,0,count);
//			System.out.println((System.currentTimeMillis()-now)/1000);
			
//			String path = "/nam/name/man.jsp";
//			System.out.println(path.substring(path.lastIndexOf("/")+1));
			
			List<User> users = da.findFriend("是",-1,-1,null,20,20);
			System.out.println(users.size());
//			List<User> users = da.getMyFriend(72,0,20);
//			for(User user: users)
//				System.out.println(user.name);
			
			
//			List<Comment> comments = da.queryComment(613,0,100);
//			da.addComment(comments.get(0));
//			da.delComment(1);
//			System.out.println(comments.size());
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
