<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function (){
			$("a.deleteItems").click(function (){
				return confirm("确定删除「"+ $(this).parent().parent().find("td:first").text() +"」吗？");
			});

			$("#clearCart").click(function (){
				return confirm("确定要清空你的购物车吗？");
			});

			$(".updateCount").change(function (){
			//	获取商品名称
				var name = $(this).parent().parent().find("td:first").text();
				var id = $(this).attr("bookId");
				// 获取商品数量
				var count =  this.value;
				if (confirm("将商品「"+ name +"」的数量修改为"+ count +"个?")){
					location.href="http://localhost:8080/book/cartServlet?action=updateCount&id="+id+"&count="+count;
				}else {
					this.value = this.defaultValue;
				}
			});
		});
	</script>
</head>
<body>
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<%@include file="/pages/common/logoin_success_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="index.jsp">当前购物车为空，快去浏览吧！</a></td>
				</tr>
			</c:if>
			<c:forEach items="${sessionScope.cart.items}" var="entry">
				<tr>
					<td>${entry.value.name}</td>
					<td>
						<input class="updateCount"  style="width: 80px;"
							   bookId="${entry.value.id}"
							   type="text" value="${entry.value.count}">
					</td>
					<td>${entry.value.price}</td>
					<td>${entry.value.totalPrice}</td>
					<td><a class="deleteItems" href="cartServlet?action=deleteItems&id=${entry.value.id}">删除</a></td>
				</tr>
			</c:forEach>

			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalItem}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span" id="clearCart"><a href="cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	
	</div>

	<%@include file="/pages/common/footer.jsp" %>
</body>
</html>