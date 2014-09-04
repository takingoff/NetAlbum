package com.net.album.data_access;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.net.album.config.StaticClass;
import com.net.album.entity.Album;
import com.net.album.entity.Comment;
import com.net.album.entity.Pic;
import com.net.album.entity.ServiceAndClientProtocol;
import com.net.album.entity.User;
import com.sun.rowset.CachedRowSetImpl;

public class DataAccessFromSqlite extends  DataAccessAbstract
{
	// /加载 sqlite 驱动
		static
		{
			try
			{
				Class.forName("org.sqlite.JDBC");
			}
			catch (ClassNotFoundException e)
			{
				e.printStackTrace();
			}
		}

		//所有sql 语句执行 。返回 CachedRowSet 若没有数据返回 （比如说是 添加 修改 删除）那么返回null
		public CachedRowSet execSQL(String sql, Object... objects)throws Exception
		{
			ResultSet resultSet = null;
			PreparedStatement preparedStatement = null;
			Connection connection = null;
			CachedRowSet crs = null;

			try
			{
				connection = DriverManager.getConnection("jdbc:sqlite:"+StaticClass.dbFilePath);
				preparedStatement = connection.prepareStatement(sql);
				for (int i = 0; i < objects.length; i++)
				{
					preparedStatement.setObject(i + 1, objects[i]);
				}
				preparedStatement.execute();
				
				// 若执行结果 没有数据返回 那么返回null，
				try
				{
					resultSet = preparedStatement.getResultSet();
				}
				catch (SQLException e1)
				{
					return null;
				}
				
				crs = new CachedRowSetImpl();
				crs.populate(resultSet);
			}
			catch(Exception e)
			{
				throw e;
			}
			
			finally
			{
				if (resultSet != null)
					resultSet.close();
				if (preparedStatement != null)
					preparedStatement.close();
				if (connection != null)
					connection.close();
			}
			return crs;
		}

		
		
		@Override
		public User getUserByName(String name) throws Exception
		{
			//select * from user where name = 'name'
			CachedRowSet rs =  execSQL("select id,psw,name,email,age,sex,description from user where name = ?",name);
			if(rs.next())
				return new User(rs.getInt("id"),rs.getString("name"),rs.getString("psw"),rs.getString("email")
						,rs.getInt("age"),rs.getString("sex"),rs.getString("description"));
			
			return null;
		}

		@Override
		public User getUserById(int id) throws Exception
		{

			//select * from user where id = 3
			CachedRowSet rs =  execSQL("select id,psw,name,email,age,sex,description from user where id = ?",id);
			if(rs.next())
				return new User(rs.getInt("id"),rs.getString("name"),rs.getString("psw"),rs.getString("email")
						,rs.getInt("age"),rs.getString("sex"),rs.getString("description"));
			
			return null;
		}

		@Override
		public void addUser(User user) throws Exception
		{
			//insert into user (psw,name,email,age,sex,description) values('psw','name','email',21,'男','shuoming')
			execSQL("insert into user(psw,name,email,age,sex,description) " +
					"values(?,?,?,?,?,?) ",
					user.psw,user.name,user.email,user.age,user.sex,user.description);
			
		}

		@Override
		public List<Album> getUserAlbums(int uid) throws Exception
		{
			// select * from album where uId = 4
			
			List<Album> list = new ArrayList<Album>();
			
			CachedRowSet crs = execSQL("select * from album where uId = ?",uid);
			
			while(crs.next())
			{
				list.add(
						new Album(
								crs.getInt("id"),
								crs.getInt("uId"),
								crs.getString("albumName"),
								crs.getString("albumDescription"),
								crs.getInt("coverId")
								)
						);
			}
			
			return list;
		}
		@Override
		public void addAlbum(Album album) throws Exception
		{
			
			//insert into album (uId,albumName,albumDescription,coverId) values(4,'albumName','albumdescription',0)
			execSQL("insert into album (uId,albumName,albumDescription,coverId) values(?,?,?,?)",
										album.uId,album.albumName,album.albumDescription,album.coverId);
			
		}
		@Override
		public Album getAlbumById(int id) throws Exception
		{
			CachedRowSet crs = execSQL("select * from album where id = ?",id);
			if(crs.next())
			{
				return
						new Album(
								crs.getInt("id"),
								crs.getInt("uId"),
								crs.getString("albumName"),
								crs.getString("albumDescription"),
								crs.getInt("coverId")
								);
			}
			return null;
			
		}



		@Override
		public String getPicName(int id) throws Exception
		{
			CachedRowSet crs = execSQL("select picName from pic where id = ?",id);
			if(crs.next())
			{
				return crs.getString(1);
			}
			return null;
		}



		@Override
		public void updatePic(Pic pic) throws Exception
		{
			execSQL("insert into pic(aId,picName,picDescription) " +
					"values(?,?,?) ",
					pic.aId,pic.picName,pic.picDescription);
		}



		@Override
		public void addPic(byte[] data,byte[] proxyData,String name,int aId) throws Exception
		{
			//insert into pic(picName,aId) values('new pic',2)
			execSQL("insert into pic(picName,aId,data,proxyData) values(?,?,?,?)",name,aId,data,proxyData);
			
		}



		@Override
		public byte[] getPicBytes(int picId, boolean isProxyData)
				throws Exception
		{
			
			CachedRowSet crs = null;
			if(isProxyData)
				crs = execSQL("select proxyData from pic  where id = ?",picId);
			else
				crs = execSQL("select data from pic where id = ?",picId);
			
			
			if(crs.next())
				return (byte[])crs.getObject(1);
			return null;
			
		}



		@Override
		public int getUserIdByName(String name) throws Exception
		{
			
			//select * from user where name = 'name'
			CachedRowSet rs =  execSQL("select id from user where name = ?",name);
			if(rs.next())
				return rs.getInt(1);
			return 0;
		}


		@Override
		public List<Integer> getPicidsByAlbumId(int aId) throws Exception
		{
			// select * from album where uId = 4
			
			List<Integer> list = new ArrayList<Integer>();
			
			CachedRowSet crs = execSQL("select id from pic where aId = ?",aId);
			
			while(crs.next())
			{
				list.add(crs.getInt(1));
			}
			return list;
		}


		
		@Override
		public void modifyUser(User user) throws Exception
		{
//			update user set psw='1',age=10,email='1',sex='1',description='1' where name=1
			execSQL("update user set psw=?,age=?,email=?,sex=?,description=? where name= ?",
					user.psw,user.age,user.email,user.sex,user.description,user.name);
		}



		@Override
		public void updateAlbumCover(int aId, int coverId) throws Exception
		{
			execSQL("update album set coverId=? where id= ?",coverId,aId);
		}



		@Override
		public void delById(int id, int type) throws Exception
		{
			switch(type)
			{
			case ServiceAndClientProtocol.DEL_TYPE_ALBUM:
				execSQL("delete from album where id = ?",id);
				break;
				
			case ServiceAndClientProtocol.DEL_TYPE_PIC:
				
				execSQL("delete from pic where id = ?",id);
				break;
			
			}
		}


		@Override
		public void modifyAlbum(Album album) throws Exception
		{
			
			execSQL("update album set albumName=?,albumDescription=?,coverId=? where id= ?",
					album.albumName,album.albumDescription,album.coverId,album.id);
		}


		
		@Override
		public Pic getPicEntity(int id) throws Exception
		{
			CachedRowSet rs =  execSQL("select * from pic where id = ?",id);
			if(rs.next())
				return new Pic(rs.getInt("id"),rs.getInt("aId"),rs.getString("picName"),rs.getString("picDescription"),rs.getString("uploadTime"));
			
			return null;
		}
		



		@Override
		public void modifyPicEntity(Pic pic) throws Exception
		{
			execSQL("update pic set picName=?,picDescription=? where id= ?",
					pic.picName,pic.picDescription,pic.id);
			
		}



		@Override
		public void addHead(int id, byte[] bytes) throws Exception
		{
			//update user set age = 100 where id = 68
			execSQL("update user set head = ? where id = ?",bytes,id);
			
		}



		@Override
		public byte[] getHeadBytes(int id) throws Exception
		{
			CachedRowSet crs = execSQL("select head from user  where id = ?",id);
			if(crs.next())
				return (byte[])crs.getObject(1);
			return null;
		}

		
		
		@Override
		public List<User> findFriend(String name, int minAge, int maxAge,
				String sex, int offset, int num) throws Exception
		{
			User user = null;
			List<User> users = new ArrayList<User>();
			if(name != null)
				user = getUserByName(name);
			
			if(user  != null)
			{
				users.add(user);
				return users;
			}
			else
			{
				
//				select id,psw,name,email,age,sex,description from user where age <= 122 and age >= 0
//				and sex = '男' order by id asc limit 5  OFFSET  2
				
				CachedRowSet rs  = null;
				if(maxAge<=-1)
					if(minAge<=-1)
						if(sex == null)
							rs=  execSQL("select id,psw,name,email,age,sex,description from user  order by id asc limit ?  OFFSET  ?"
									,num,offset);
						else
							rs=  execSQL("select id,psw,name,email,age,sex,description from user where sex = ? order by id asc limit ?  OFFSET  ?"
									,sex,num,offset);
					else
						if(sex == null)
							rs=  execSQL("select id,psw,name,email,age,sex,description from user where age >= ? order by id asc limit ?  OFFSET  ?"
									,minAge,num,offset);
						else
							rs=  execSQL("select id,psw,name,email,age,sex,description from user where age >= ? and sex = ? order by id asc limit ?  OFFSET  ?"
									,minAge,sex,num,offset);
				else
					if(minAge<=-1)
						if(sex == null)
							rs=  execSQL("select id,psw,name,email,age,sex,description from user where age <= ?  order by id asc limit ?  OFFSET  ?"
									,maxAge,num,offset);
						else
							rs=  execSQL("select id,psw,name,email,age,sex,description from user where age <= ? and sex = ? order by id asc limit ?  OFFSET  ?"
									,maxAge,sex,num,offset);
					else
						if(sex == null)
							rs=  execSQL("select id,psw,name,email,age,sex,description from user where age <= ? and age >= ? order by id asc limit ?  OFFSET  ?"
									,maxAge,minAge,num,offset);
						else
							rs=  execSQL("select id,psw,name,email,age,sex,description from user where age <= ? and age >= ? and sex = ? order by id asc limit ?  OFFSET  ?"
									,maxAge,minAge,sex,num,offset);
					
				
				while(rs.next())
				{
					users.add( new User(rs.getInt("id"),rs.getString("name"),rs.getString("psw"),
							rs.getString("email"),rs.getInt("age"),rs.getString("sex"),rs.getString("description")));
				}
				
				return users;
			}
			
			
			
		}

		
		@Override
		public List<User> getMyFriend(int id, int offset, int num)
				throws Exception
		{
				
//			select u.id,u.psw,u.name,u.email,u.age,u.sex,u.description from user as u , friend as f 
//			where  f.observerId = 72 and f.[targetId] = u.id   order by u.id asc limit 20  OFFSET  0
			
			List<User> users = new ArrayList<User>();
			CachedRowSet rs  = execSQL("select u.id,u.psw,u.name,u.email,u.age,u.sex,u.description from user as u , friend as f " +
					" where  f.observerId = ? and f.targetId = u.id   order by u.id asc limit ?  OFFSET  ?",id,num,offset);
			
			while(rs.next())
			{
				users.add( new User(rs.getInt("id"),rs.getString("name"),rs.getString("psw"),
						rs.getString("email"),rs.getInt("age"),rs.getString("sex"),rs.getString("description")));
			}
			
			return users;
		}

		
		
		@Override
		public void addFriends(int observerId, List<Integer> targetIds)
				throws Exception
		{
//			insert into friend (observerId,targetId) values(70,74)
//			select count(*) from friend where observerId = 72 and targetId = 71
			
			CachedRowSet rs = null;
			int count;
			
			for(int targetId:targetIds)
			{
				rs = execSQL("select count(*) from friend where observerId = ? and targetId = ?",observerId,targetId);
				rs.next();
				count = rs.getInt(1);
				
				// 如果 已经关注了该用户那么就不在 关注了。
				if(count != 0)
					continue;
				else
					execSQL("insert into friend (observerId,targetId) values(?,?)",observerId,targetId);
					
			}
			
		}

		
		@Override
		public void delFriend(int observerId, int targetId) throws Exception
		{
//			delete from friend where targetId = 74 and observerID = 72
			execSQL("delete from friend where targetId = ? and observerID = ?",targetId,observerId);
		}
		
		



		@Override
		public void addComment(Comment comment) throws Exception
		{
//			insert into comment (uId,pId,msg) values(0,613,'goodggodd')
			execSQL("insert into comment (uId,pId,msg) values(?,?,?)",comment.uId,comment.pId,comment.msg);	
			
		}
		
		



		@Override
		public void delComment(int id) throws Exception
		{
			// delete from comment where id = 17
			execSQL("delete from comment where id = ?",id);
		}
		
		



		@Override
		public List<Comment> queryComment(int pId, int offset, int num) throws Exception
		{
			// select * from comment where pId = 613 order by id asc limit 15  OFFSET  0
			CachedRowSet crs = execSQL("select * from comment where pId = ? order by id asc limit ?  OFFSET ?",
					pId,num,offset);
			
			List<Comment> comments = new ArrayList<Comment>();
			
			while(crs.next())
				comments.add(new Comment(crs.getInt("id"),crs.getInt("uId"),crs.getInt("pId"),crs.getString("msg"),crs.getString("genTime")));
			
			return comments;
			
		}



	
		
		
		@Override
		public void modifyCover(int albumId, int picId) throws Exception
		{
			execSQL("update album set coverId = ? where id = ?" ,picId,albumId);
		}
		
		
		
		
		
}
