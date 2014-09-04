package com.net.album.data_access;

import java.util.List;

import com.net.album.entity.Album;
import com.net.album.entity.Comment;
import com.net.album.entity.Pic;
import com.net.album.entity.User;

public abstract class DataAccessAbstract
{
	
	
	public static DataAccessAbstract getDataAccess()
	{
		
		
		try
		{
			return (DataAccessAbstract)Class.forName("com.net.album.data_access.DataAccessFromSqlite").newInstance();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	/**
	 * @param name
	 * @return  通过 name 获取 user对象 如果不存在 返回 null
	 * @throws Exception
	 */
	public abstract User getUserByName(String name)throws Exception;
	
	/**
	 * @param id
	 * @return 通过 email 获取 user对象 如果不存在 返回 null
	 * @throws Exception
	 */
	public abstract User getUserById(int id)throws Exception;
	
	
	/**
	 * @param name
	 * @return 如果用户名存在返回所在 id 否者返回0
	 * @throws Exception
	 */
	public abstract int getUserIdByName(String name)throws Exception;
	
	
	/**
	 * @param user 直接向数据库中添加 user对象。 (不指定id)
	 * @throws Exception
	 */
	public abstract void addUser(User user)throws Exception;
	
	
	/**
	 * @param album  (不指定id 其他都应指定）
	 * @throws Exception
	 */
	public abstract void addAlbum(Album album)throws Exception;
	
	
	/**
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public abstract Album getAlbumById(int id)throws Exception;
	
	
	/**
	 * @param cid  通过用户id 在数据库中查找拥有的所用 相册
	 * @throws Exception
	 */
	public abstract List<Album> getUserAlbums(int uid)throws Exception;
	
	
	public abstract String getPicName(int id)throws Exception;
	
	
	public abstract void updatePic(Pic pic)throws Exception;
	
	
	/**
	 * 向数据库中添加图片。除了说明。生成时间，id之外的其他字段都指定
	 * @param data
	 * @param proxyData
	 * @param name
	 * @param aId
	 * @throws Exception
	 */
	public abstract void addPic(byte[] data,byte[] proxyData,String name,int aId)throws Exception;
	
	
	/**
	 * @param picId
	 * @param isProxyData
	 * @return	 从数据库中 将 文件数据 转换成字节数组
	 * @throws Exception
	 */
	public abstract byte[] getPicBytes(int picId,boolean isProxyData)throws Exception;
	
	/**
	 * @param aId
	 * @return 返回 指定相册下 所有相片的 id 列表
	 * @throws Exception
	 */
	public abstract List<Integer> getPicidsByAlbumId(int aId)throws Exception;
	
	
	
	/**
	 * @param user  除了 id ，name修改用户的所有字段
	 * @throws Exception
	 */
	public abstract void modifyUser(User user)throws Exception;
	
	
	/**
	 * @param aId
	 * @param coverId 修改相册封面
	 * @throws Exception
	 */
	public abstract void updateAlbumCover(int aId,int coverId)throws Exception;
	
	
	/**
	 * 通过其 id 删除 某实体
	 * @param id
	 * @param type	
	 * @throws Exception
	 */
	public abstract void delById(int id,int type)throws Exception;
	
	
	/**
	 * @param album  必须指定 id   将会修改 coverId ， name  ，description
	 * @throws Exception
	 */
	public abstract void modifyAlbum(Album album)throws Exception;
	
	
	/**
	 * @param id 根据id 获取 pic 对象（不包括文件字节）
	 * @throws Exception
	 */
	public abstract Pic getPicEntity(int id)throws Exception;
	
	
	/**
	 * @param pic  修改指定的 图片对象 ，针对 name descrption  （id 必须指定）
	 * @throws Exception
	 */
	public abstract void modifyPicEntity(Pic pic)throws Exception;
	
	
	/**
	 * 用户添加 头像
	 * @param id
	 * @param bytes
	 * @throws Exception
	 */
	public abstract void addHead(int id,byte[] bytes)throws Exception;
	
	
	
	/**
	 * 获取用户头像 字节数组
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public abstract byte[] getHeadBytes(int id)throws Exception;
	
	
	/**
	 * 如果用户名 为空， 或者 用户名一个都没查找到，那么使用 年龄 性别查找， 
	 * @param name		用户名
	 * @param minAge	最小年龄		指定小于 零的数 忽略过滤
	 * @param maxAge	最大年龄		指定小于 零的数 忽略过滤
	 * @param sex		性别			指定为null  忽略 过滤
	 * @param offset	从第几个开始
	 * @param num		请求个数
	 * @return
	 * @throws Exception
	 */
	public abstract List<User> findFriend(String name,int minAge ,int maxAge,String sex,int offset,int num)throws Exception;
	
	
	/**
	 * 返回用户的一组 好友列表
	 * @param id
	 * @param offset		偏移 数
	 * @param num			个数
	 * @return
	 * @throws Exception
	 */
	public abstract List<User> getMyFriend(int id, int offset,int num)throws Exception;
	
	
	/**
	 * 添加一组好友 
	 * @param observerId
	 * @param targetIds
	 * @throws Exception
	 */
	public abstract void addFriends(int observerId,List<Integer> targetIds)throws Exception;
	
	
	/**
	 * 删除 一个好友
	 * @param observerId
	 * @param targetId
	 * @throws Exception
	 */
	public abstract void delFriend(int observerId,int targetId)throws Exception;
	
	
	
	/**
	 * 添加 评论 
	 * @param comment   （id 以及 genTime 不指定，其他字段必须指定）
	 * @throws Exception
	 */
	public abstract void addComment(Comment comment)throws Exception;
	
	
	/**
	 * 删除 评论
	 * @param id
	 * @throws Exception
	 */
	public abstract void delComment(int id)throws Exception;
	
	
	/**
	 * 返回相片的评论列表  
	 * @param pId
	 * @param offset		指定从哪条开始返回 （以id 排序）
	 * @param num			指定返回的条数
	 * @return
	 * @throws Exception
	 */
	public abstract List<Comment> queryComment(int pId ,int offset,int num)throws Exception;
	
	
	/**
	 * 修改相册封面
	 * @param albumId
	 * @param picId
	 * @throws Exception
	 */
	public abstract void modifyCover(int albumId,int picId)throws Exception;
	
	
	
	
	
	
	
	
	
}
