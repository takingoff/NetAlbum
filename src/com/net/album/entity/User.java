package com.net.album.entity;

public class User
{
	
	public int id;
	public String psw;
	public String name;
	public String email;
	public int age;
	public String sex;
	public String description;
	
	public User(int id,String name, String psw, String email, int age, String sex,
			String description)
	{
		super();
		this.id = id;
		this.name = name;
		this.psw = psw;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.description = description;
	}
	public User(){}
	
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getPsw()
	{
		return psw;
	}
	public void setPsw(String psw)
	{
		this.psw = psw;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
}
