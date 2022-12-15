<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#buttonSubmit").on("click",function(){ //제출 버튼 이벤트 지정
			$.ajax({
				url:'./112101ajaxServer.jsp',
				type:'post',
				data:{nickName:'멍멍이'},
				dataType : 'json',
				success:function(data){  //data = {"nickName": "멍멍이"}
					
					console.log("요청 성공");
					console.log(data.nickName)
					$("#nickName").text(data.nickName);
				}
			});
		});		
	});
</script>
</head>
<body>
	<input id="buttonSubmit" type="button" value="제출">
	<p id="nickName"></p>
	<p> ajax 통신성공</p>
</body>
</html>