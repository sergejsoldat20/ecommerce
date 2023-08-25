<%@ page import="com.example.adminapp.models.User" %>
<%@ page import="com.example.adminapp.beans.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" type="com.example.adminapp.beans.UserBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Izmjeni podatke za korisnika<%=userBean.getUser().getUsername()%></title>

    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/css/select2.min.css" rel="stylesheet"/>
    <script src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
    <link href="styles/forms.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp" %>
<main role="main" class="container bg-white">
    <div class="card border-success" style="width: 30rem">
        <form class="form-add-user" method="POST" action="?action=update-user&id=<%=userBean.getUser().getId()%>">
            <div class="form-group">
                <label for="firstName">Ime</label>
                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="First name"
                       required value="<%=userBean.getUser().getFirstName()%>">
            </div>
            <div class="form-group">
                <label for="lastName">Prezime</label>
                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Last name" required
                       value="<%=userBean.getUser().getLastName()%>">
            </div>
            <div class="form-group">
                <label for="username">Koriznicko ime</label>
                <input type="text" class="form-control" id="username" name="username" placeholder="Username" required
                       value="<%=userBean.getUser().getUsername()%>">
            </div>

            <div class="form-group">
                <label for="email">email</label>
                <input type="email" class="form-control" id="email" name="email" placeholder="E-mail" required
                       value="<%=userBean.getUser().getEmail()%>">
            </div>
            <div class="form-group">
                <label for="phoneNumber">Broj telefona</label>
                <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Phone number"
                       required value="<%=userBean.getUser().getPhoneNumber()%>">
            </div>
            <div class="form-group">
                <label for="city">Lokacija</label>
                <input type="text" class="form-control" id="city" name="city" placeholder="City" required
                       value="<%=userBean.getUser().getLocation()%>">
            </div>

            <button type="submit" name="submit" class="btn btn-success">Submit</button>
        </form>
    </div>
</main>
</body>
</html>