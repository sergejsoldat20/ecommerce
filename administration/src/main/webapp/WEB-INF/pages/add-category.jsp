<%@ page import="com.example.adminapp.models.Category" %>
<%@ page import="com.example.adminapp.beans.CategoryBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="categoryBean" type="com.example.adminapp.beans.CategoryBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Add category</title>

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
        <form class="form-add-user" method="POST" action="?action=add-category">
            <div class="form-group">
                <label for="name">Naziv</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="Category name"
                       required>
            </div>
            <button type="submit" name="submit" class="btn btn-success">Dodaj kategoriju</button>
        </form>
    </div>
</main>
</body>
</html>