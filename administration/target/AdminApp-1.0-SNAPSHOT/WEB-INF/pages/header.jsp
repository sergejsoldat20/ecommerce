<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <title>Header</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <a class="navbar-brand" href="?action=logs" style="margin-left: 10px">Administracija</a>
    <div class="container d-flex flex-wrap" style="margin-left: 1%">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="?action=users">Korisnici</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?action=categories">Kategorije</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="?action=sign-out">Odjavi se</a>
            </li>
        </ul>
    </div>
</nav>
</body>
</html>