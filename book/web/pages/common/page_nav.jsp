<%--
  Created by IntelliJ IDEA.
  User: 46726
  Date: 2021/4/26
  Time: 10:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">
    <c:if test="${ requestScope.page.pageNo > 1}">
        <a href="${requestScope.page.url}&pageNo=1">首页</a>
        <a href="${requestScope.page.url}&pageNo=${ requestScope.page.pageNo - 1}">上一页</a>
    </c:if>
    <%--        页码显示--%>
    <c:choose>
        <%--情况1：总页码小于5的情况--%>
        <c:when test="${ requestScope.page.pageTotal <= 5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--情况2：总页码大于5--%>
        <c:when test="${ requestScope.page.pageTotal > 5 }">
            <c:choose>
                <%--2.1:前5个--%>
                <c:when test="${requestScope.page.pageNo <= 3}">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--2.2：后5个--%>
                <c:when test="${ requestScope.page.pageNo > requestScope.page.pageTotal -3 }">
                    <c:set var="begin" value="${ requestScope.page.pageTotal - 4}"/>
                    <c:set var="end" value="${ requestScope.page.pageTotal }"/>
                </c:when>
                <%--2.3：中间情况--%>
                <c:otherwise>
                    <c:set var="begin" value="${ requestScope.page.pageNo - 2 }"/>
                    <c:set var="end" value="${ requestScope.page.pageNo + 2 }"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i == requestScope.page.pageNo}">
            [${i}]
        </c:if>
        <c:if test="${i != requestScope.page.pageNo}">
            <%-- 发送页参数 --%>
            <a href="${requestScope.page.url}&pageNo=${i}">${i}</a>
        </c:if>
    </c:forEach>

    <c:if test="${ requestScope.page.pageNo < requestScope.page.pageTotal }">
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
        <a href="${requestScope.page.url}&pageNo=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    <%--    跳到指定页码--%>
    到第<input value="${param.pageNo}" name="pn"
             id="pn_input"/>页
    <input type="button" id="searchBtn" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchBtn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}${requestScope.page.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>
