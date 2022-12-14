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
		$("#check").click(function(){
			if($("#userid").val() ==""){
				$("#result").text("아이디를 입력하세요!");
				return;
			}
			
			query = {
					userid:$("#userid").val(),
					userpwd:$("#userpwd").val()
			}
			
			$.ajax({
				url: 'ajaxServer5.jsp',
				type:'post',
				data:query
			}).done(function(data){
				obj = JSON.parse(data);
				if(obj.userid != undefined){
					$("#result").text(obj.userid + " / " + obj.userpwd)
				}else{
					$("#result").text("존재하지 않는 아이디 입니다.");
				}
			});
		});
	});
</script>
</head>
<body>
	<label>user ID</label>
	<input type="text" id="userid">
	<br>
	<label>user PWD</label>
	<input type="password" id="userpwd">
	<button id="check">Check</button>
	<p id="result"></p>
</body>
</html>