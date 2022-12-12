<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>웹소켓 채팅</title>
</head>
<body>
	<script>
		function chatWinOpen(){ //채팅창을 팝업창으로 열어주는함수
			var id = document.getElementById("chatId"); //대화명 입력상자의 DOM객체를 얻어와서 대화명이입력되지않으면 경고창을 띄워준다.
			if (id.value == ""){
				alert("대화명을 입력 후 채팅창을 열어주세요.");
				id.focus();
				return;
			}
			/*문제가없다면 대화명을 매개변수로 전달해 채팅창을 띄워요 */
			window.open("ChatWindow.jsp?chatId=" + id.value, "","width=320,height=400"); 
			id.value = "";
		}
	</script>
	<h2>웹 소켓 채팅 - 대화명 적용해서 채팅창 띄어주기</h2>
	대화명 : <input type="text" id="chatId" /> <!-- 대화명 입력상자 -->
	<button onclick="chatWinOpen();">채팅 참여</button>
</body>
</html>