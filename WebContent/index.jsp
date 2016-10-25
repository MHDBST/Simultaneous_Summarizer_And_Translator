<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>





<!DOCTYPE html PUBLIC>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="styles.css" rel="stylesheet" type="text/css" />
<title>News Translator and Summarizer</title>

</head>
<body>
	<%-- <div id="RSS">
		<c:import var="xmlFile" url="http://www.cbc.ca/cmlink/rss-business"
			charEncoding="UTF-8" />
		<x:parse varDom="dom" xml="${xmlFile}" />



		<a href="RSS?id=<x:out select="$dom/rss/channel/item[1]/link"/>"><x:out
				select="$dom/rss/channel/item[1]/title" /></a><br> <a
			href="RSS?id=<x:out select="$dom/rss/channel/item[2]/link"/>"><x:out
				select="$dom/rss/channel/item[2]/title" /></a><br> <a
			href="RSS?id=<x:out select="$dom/rss/channel/item[3]/link"/>"><x:out
				select="$dom/rss/channel/item[3]/title" /></a><br> <a
			href="RSS?id=<x:out select="$dom/rss/channel/item[4]/link"/>"><x:out
				select="$dom/rss/channel/item[4]/title" /></a><br> <a
			href="RSS?id=<x:out select="$dom/rss/channel/item[5]/link"/>"><x:out
				select="$dom/rss/channel/item[5]/title" /></a><br> <a
			href="RSS?id=<x:out select="$dom/rss/channel/item[6]/link"/>"><x:out
				select="$dom/rss/channel/item[6]/title" /></a><br>

	</div>
	 --%>





	<nav class='top-bar rtl' data-topbar=''>
		<ul class='title-area'>

			<li class='name'>
				<h1>
					<a href='#'>خلاصه‌سازی متون</a>
				</h1>
			</li>
			<li class='toggle-topbar menu-icon'><a href='#'>گزینه‌ها</a></li>
		</ul>
		<section class='top-bar-section'>
			<ul class='left'>



				<li class='has-form'>
					<form action='http://parsijoo.ir/websearch' method='GET'
						target='_blank'>
						<div class='row collapse'>
							<div class='large-8 small-9 columns'>
								<input id='search-field' name='q'
									placeholder='جستجو با پارسی‌جو' type='text'>
							</div>
							<div class='large-4 small-3 columns'>
								<button class='alert button expand' href='#' id='search-button'>بیاب!</button>
							</div>
						</div>
					</form>
				</li>
			</ul>
		</section>

	</nav>





	<form method="POST" action="Login" id="login_form">
		<div id="reglog">
			<table id="log">
				<tr>
					<td>نام کاربری</td>
					<td><input type="text" name="user"></td>
				</tr>
				<tr>
					<td>گذرواژه</td>
					<td><input type="password" name="pass"></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="Login"> <input
						type="button" value="Register"
						onclick="location.href='Register.jsp'"></td>
				</tr>
			</table>
		</div>
	</form>

	<div id="links">
		<table id="addresses">
			<tr>
				<td><a id="0" href="Index?name=summ0&percent=0.5&id='id'"
					onclick="newC(0)">Keystone vote set to pass U.S. </a></td>
			</tr>
			<tr>
				<td><a id="1" href="Index?name=summ1&percent=0.5"
					onclick="newC(1)"> 2014 Fuel Consumption Guide (Natural
						Resources Canada). </a></td>

			</tr>
			<tr>
				<td><a id="2" href="Index?name=summ2&percent=0.5"
					onclick="newC(2)">Bank of Canada looking into issuing digital
						currency. </a></td>
			</tr>
			<tr>
				<td><a id="3" href="Index?name=summ3&percent=0.5"
					onclick="newC(3)"> Putin stations Russian warships off
						Australia's coast ahead of G20. </a></td>

			</tr>
			<tr>
				<td><a id="4" href="Index?name=summ4&percent=0.5"
					onclick="newC(4)"> BlackBerry partners with Samsung on mobile
						security. </a></td>

			</tr>
		</table>
	</div>

	<div id="res"  style="	padding: 15px;	margin: 20px;;">${summary}</div>
	<div id="resT" style="	padding: 15px;	margin: 20px;;">${trans}</div>
	<div id="comments">${comment0}</div>
	<div id="newComment">
		<form method="POST" action="Comment" id="comment_form">
			<input type="text" name="comment_text" /> <input type="submit"
				onclick="alert(id)" />
			<!-- onclick="func()"  /> -->
		</form>
	</div>
	<!-- <script type="text/javascript">
		alert("${userN}");
	</script> -->
	<script type="text/javascript">
		var clicked;
		function init() {
			document.getElementById('comment_form').style.display = "none";
		}
		if ("${userN}") {
			document.getElementById('login_form').style.display = "none";

		} else {
			/*  */
		}
		function newC(id) {
			$("#comment_form").show();
			clicked = id;
			alert(clicked);
		}
		function func() {

			var theDiv = document.getElementById("comments");
			var content = document.createTextNode("salaaaaaaaaameMan");
			theDiv.appendChild(content);
			alert("comment bi comment00");

			var x = document.getElementById('comments');
			var newed = document.createElement("div");

			newd.innerHTML = "salam";
			x.appendChild(newd);
			alert("comment bi comment2");

		}
	</script>

</body>
</html>