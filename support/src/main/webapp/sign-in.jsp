<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.example.customersupport.beans.AdminBean" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="adminBean" class="com.example.customersupport.beans.AdminBean" scope="session"/>
<jsp:useBean id="authService" class="com.example.customersupport.services.AuthService" scope="application"/>
<jsp:setProperty property="username" name="adminBean" param="username"/>
<jsp:setProperty property="password" name="adminBean" param="password"/>
<!doctype html>
<%
    if (request.getParameter("submit") != null) {
        AdminBean admin = authService.authenticate(adminBean.getUsername(), adminBean.getPassword());
        if (admin != null) {
            adminBean.setId(admin.getId());
            adminBean.setFirstName(admin.getFirstName());
            adminBean.setLastName(admin.getLastName());
            adminBean.setRole(admin.getRole());
            adminBean.setLoggedIn(true);
            session.setAttribute("notification", "");
            response.sendRedirect("messages.jsp");
        } else {
            session.setAttribute("notification", "Incorrect credentials.");
            adminBean.setLoggedIn(false);
        }
    } else {
        session.setAttribute("notification", "");
    }
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Sign in</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/sign-in/">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="styles/sign-in.css" rel="stylesheet">
</head>

<body class="text-center">
<form class="form-sign-in" method="post" action="sign-in.jsp">
    <h1 class="h3 mb-3 font-weight-normal">Korisnicka podrska</h1>
    <label for="username" class="sr-only">Username</label>
    <input type="text" value="sega" id="username" name="username" class="form-control" placeholder="Username" required autofocus>
    <label for="password" class="sr-only">Lozinka</label>
    <input type="password" value="pass" id="password" name="password" class="form-control" placeholder="Lozinka" required>
    <button class="btn btn-lg btn-success btn-block" type="submit" name="submit">Sign in</button>
    <h5><%=session.getAttribute("notification").toString()%>
    </h5>
    <p class="mt-5 mb-3 text-muted">&copy; 2023</p>
</form>
</body>
</html>