package com.net.album.main_action;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.net.album.data_access.DataAccessAbstract;
import com.net.album.entity.Comment;
import com.net.album.entity.ServiceAndClientProtocol;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CommentAction extends ActionSupport implements ModelDriven<Comment>
{

	private static final long serialVersionUID = 1L;
	private DataAccessAbstract da = DataAccessAbstract.getDataAccess();
	
	private Comment comment = new Comment();
	@Override
	public Comment getModel()
	{
		return comment;
	}
	
	public Comment getComment()
	{
		return comment;
	}
	
	public void setComment(Comment comment)
	{
		this.comment = comment;
	}
	
	/*-----------------------------------addCommentAction-----------------------------------------*/
	
	private int result;

	public int getResult()
	{
		return result;
	}

	public void setResult(int result)
	{
		this.result = result;
	}

	public String add()
	{
		try
		{
			comment.msg = new String (comment.msg.getBytes("iso-8859-1"),"utf-8");
		}
		catch (UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		
		try
		{
			da.addComment(comment);
			result = ServiceAndClientProtocol.SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		return "commentOperation";
	}
	
	/*-----------------------------------delCommentAction-----------------------------------------*/
	
	public String del()
	{
		try
		{
			da.delComment(comment.id);
			result = ServiceAndClientProtocol.SUCCESS;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			result = ServiceAndClientProtocol.SERVICER_DB_EXCEPTION;
		}
		return "commentOperation";
	}
	
	/*-----------------------------------queryCommentAction-----------------------------------------*/
	
	private List<Comment> comments;
	
	public List<Comment> getComments()
	{
		return comments;
	}

	public void setComments(List<Comment> comments)
	{
		this.comments = comments;
	}

	private int requestCount;
	
	public int getRequestCount()
	{
		return requestCount;
	}

	public void setRequestCount(int requestCount)
	{
		this.requestCount = requestCount;
	}

	public String query()
	{
		
		try
		{
			comments = da.queryComment(comment.pId,ServiceAndClientProtocol.LISTVIEW_PAGE_SIZE*requestCount,ServiceAndClientProtocol.LISTVIEW_PAGE_SIZE);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return "queryComment";
	}

	
	
}
