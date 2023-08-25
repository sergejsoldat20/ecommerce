
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    session.invalidate();
    response.sendRedirect("sign-in.jsp");
%>
<!doctype html>
<html lang="en">
<head>
    <title>Sign out</title>
</head>
<body>
</body>
</html>