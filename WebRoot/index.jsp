<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"  contentType="text/html; charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>

<%response.setHeader("Content-type", "text/html; charset=utf-8"); %>

<html> 
<head> 
　 <title>My Page</title> 

	
  <sx:head />
  
 <script>
 	dojo.event.topic.subscribe("/loginResult", function(data, request, widget){ 
 		
 		if(request == "load"){
 			alert(data);
 			var user = eval("("+data+")").user;
 		}
 	});
 	dojo.event.topic.subscribe("/requestPicResult", function(data, request, widget){ 
 		
 		if(request == "load"){
 			alert(data);
 		}
 	});
 </script>
 
  
</head> 

<body>

	
	<s:form action="loginAction" namespace="/main"  method="get">
		<s:textfield label ="密码" name="password" ></s:textfield>
		<s:textfield label ="邮箱或者id" name="account" ></s:textfield>
		<sx:submit type="submit"  notifyTopics="/loginResult"  value="loginAction.action提交">//////////////////////////////////////////queryAlbumAction  android 客户端使用的是 utf-8 编码////////////////////////////////////////////////// 
	<br></sx:submit>
	</s:form>
	<br>
	
	
	//////////////////////////////////////////friend test  android 客户端使用的是 utf-8 编码//////////////////////////////////////////////////
	<br>
	<br>
	////////////findFriend
	<s:form action ="findFriendAction" namespace="/main" method="get">
		<s:textfield name="name" label="name"></s:textfield>
		<s:textfield name="age1" label="age1"></s:textfield>
		<s:textfield name="age2" label="age2"></s:textfield>
		<s:textfield name="sex" label="sex"></s:textfield>
		<s:textfield name="requestCount" label="requestCount"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	<br>
	<br>
	////////////queryMyFriend
	<s:form action ="queryMyFriendAction" namespace="/main" method="get">
		<s:textfield name="id" label="id"></s:textfield>
		<s:textfield name="requestCount" label="requestCount"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	<br>
	<br>
	////////////delFriend
	<s:form action ="delFriendAction" namespace="/main"  method="get">
		<s:textfield name="observerId" label="observerId"></s:textfield>
		<s:textfield name="targetId" label="tagetId"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	<br>
	<br>
	////////////addFriend
	<s:form action ="addFriendAction" namespace="/main"  method="get">
		<s:textfield name="observerId" label="observerId"></s:textfield>
		<s:textfield name="targetIds" label="tagetIds"></s:textfield>
		<s:textfield name="targetIds" label="tagetIds"></s:textfield>
		<s:textfield name="targetIds" label="tagetIds"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	
	
	//////////////////////////////////////////pic entity test  android 客户端使用的是 utf-8 编码//////////////////////////////////////////////////
	
	<br>
	<br>
	////////////modifyPicEntity
	<s:form action ="modifyPicAction" namespace="/main"  method="get">
		<s:textfield name="id" label="id"></s:textfield>
		<s:textfield name="picName" label="picName"></s:textfield>
		<s:textfield name="picDescription" label="picDescription"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	<br>
	<br>
	////////////obtainPicEntity
	<s:form action ="obtainPicAction" namespace="/main"  method="get">
		<s:textfield name="id" label="id"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	
	
	//////////////////////////////////////////comment test  android 客户端使用的是 utf-8 编码//////////////////////////////////////////////////
	
	<br>
	<br>
	////////////addComment
	<s:form action ="addCommentAction" namespace="/main"  method="get">
		<s:textfield name="pId" label="pId"></s:textfield>
		<s:textfield name="uId" label="uId"></s:textfield>
		<s:textfield name="msg" label="msg"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	<br>
	<br>
	////////////delComment
	<s:form action ="delCommentAction" namespace="/main"  method="get">
		<s:textfield name="id" label="id"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	////////////queryComment
	<s:form action ="queryCommentAction" namespace="/main"  method="get">
		<s:textfield name="pId" label="pId"></s:textfield>
		<s:submit>submit</s:submit>
	</s:form>
	
	
</body>
</html>