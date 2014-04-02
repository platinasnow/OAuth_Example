<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
	<title>Home</title>
</head>
<link rel="stylesheet" href="resources/css/agile_carousel.css" type='text/css'>
<body>
<h1>
	Hello world!  
</h1>
<div>
	<sec:authentication property="principal"/>
</div>

	<button type="button" onclick="oAuth()">oAuth</button>
	<button type="button" onclick="messageConverterAJax()">메세지</button>
	
	
	<div class="slideshow" id="flavor_1"></div>
	
	<div style="text-align: center;">
	<div style="display: inline-block;">
		<ul>
			<li>1</li>
			<li>2</li>
			<li>3</li>
			<li>4</li>
			<li>5</li>
			<li>6</li>
			<li>7</li>
		</ul>
	</div>
	</div>

</body>
<style type="text/css">
ul{
	margin:0;
	padding:0;
	display: table;
	width:100%;
	clear: both;
	overflow:visible;
	list-style: none;
}
li{
		margin:0;
	padding:0;
	width: 100px;
	float:left;
	height:100px;
	display:table-cell;
	background: #bbb;
}

</style>
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="resources/js/agile_carousel.alpha.js"></script>
<script type="text/javascript">
	var oAuth = function(){
		location.href="naverLogin";
	}
	
	var messageConverterAJax = function(){
		$.post( 'ajax.js', { name : '심해펭귄' , userId : '펭귄1호' }, function(result){
			console.log(result.list);
		} );
	}
	
	$.getJSON("json", function(data) {
        $(document).ready(function(){
            $("#flavor_1").agile_carousel({
                carousel_data: data,
                carousel_outer_height: 228,
                carousel_height: 228,
                slide_height: 230,
                carousel_outer_width: 480,
                slide_width: 480,
                transition_time: 300,
                timer: 4000,
                continuous_scrolling: true,
                control_set_1: "numbered_buttons",
                no_control_set: "hover_previous_button,hover_next_button"
            });
        });
    });
</script>
</html>
