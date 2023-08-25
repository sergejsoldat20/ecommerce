
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isErrorPage="true"%>
<jsp:useBean id="adminBean" class="com.example.customersupport.beans.AdminBean" scope="session"/>
<%
    if(!adminBean.isLoggedIn())
        response.sendRedirect("sign-in.jsp");
%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>404 HTML Template by Color lib</title>

    <link href="styles/sign-in.css" rel="stylesheet">
    <meta name="robots" content="noindex, follow">
</head>
<body>
<div id="notfound">
    <div class="notfound">
        <div class="notfound-404"></div>
        <h1>Sorry, your application encountered an error</h1>
        <h2>Oops! Something went wrong</h2>
        <a href="${pageContext.request.contextPath}/messages.jsp">Back to homepage</a>
    </div>
</div>

<script async src="https://www.googletagmanager.com/gtag/js?id=UA-23581568-13"></script>
<script>
    window.dataLayer = window.dataLayer || [];

    function gtag() {
        dataLayer.push(arguments);
    }

    gtag('js', new Date());

    gtag('config', 'UA-23581568-13');
</script>
<script defer src="https://static.cloudflareinsights.com/beacon.min.js/vaafb692b2aea4879b33c060e79fe94621666317369993"
        integrity="sha512-0ahDYl866UMhKuYcW078ScMalXqtFJggm7TmlUtp0UlD4eQk0Ixfnm5ykXKvGJNFjLMoortdseTfsRT8oCfgGA=="
        data-cf-beacon='{"rayId":"79318961392f3a8a","token":"cd0b4b3a733644fc843ef0b185f98241","version":"2022.11.3","si":100}'
        crossorigin="anonymous"></script>
</body>
</html>