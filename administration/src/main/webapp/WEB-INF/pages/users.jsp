<%@ page import="com.example.adminapp.models.User" %>
<%@ page import="com.example.adminapp.beans.UserBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userBean" type="com.example.adminapp.beans.UserBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Users</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });

    </script>
</head>
<body>
<%@include file="header.jsp" %>
<main role="main" class="container">
    <br>
    <h1>Korisnici</h1>
    <button type="button" class="btn btn-success" onclick="location.href='?action=add-user'">
        <span class="fa fa-plus"></span>
        Dodaj novog korisnika
    </button>
    <hr>
    <table id="example" class="table table-hover" style="width:100%">
        <caption>Users</caption>
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Ime</th>
            <th scope="col">Prezime</th>
            <th scope="col">Lokacija</th>
            <th scope="col">email</th>
            <th scope="col">Broj telefona</th>
            <th scope="col">Lokacija</th>
            <th scope="col">Uloga</th>
            <th scope="col">Status naloga</th>
            <th scope="col">Akcije</th>
        </tr>
        </thead>
        <tbody>
        <% for (User user : userBean.getAll()) { %>
        <tr>
            <td>
                <%=user.getId()%>
            </td>
            <td>
                <%=user.getFirstName()%>
            </td>
            <td>
                <%=user.getLastName()%>
            </td>
            <td>
                <%=user.getUsername()%>
            </td>
            <td>
                <%=user.getEmail()%>
            </td>
            <td>
                <%=user.getPhoneNumber()%>
            </td>
            <td>
                <%=user.getLocation()%>
            </td>
            <td>
                <%=user.getRole()%>
            </td>
            <td>
                <%=user.getStatus()? "Aktivan": "Neaktivan"%>
            </td>
            <td>
                <div class="d-flex flex-row mb-3">
                    <div style="align-content: center;justify-content: center">
                        <button type="button" class="btn btn-info mr-1"
                                onclick="location.href='?action=update-user&id=<%=user.getId()%>'">
                            Izmjeni
                        </button>
                    </div>
                    <div style="align-content: center;justify-content: center">
                        <button type="button" class="btn btn-danger ml-2"
                                onclick="location.href='?action=delete-user&id=<%=user.getId()%>'">
                            Onemoguci
                        </button>
                    </div>
                </div>
            </td>
        </tr>
        <% } %>

        </tbody>
        <tfoot>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Ime</th>
            <th scope="col">Prezime</th>
            <th scope="col">Lokacija</th>
            <th scope="col">email</th>
            <th scope="col">Broj telefona</th>
            <th scope="col">Lokacija</th>
            <th scope="col">Uloga</th>
            <th scope="col">Status naloga</th>
            <th scope="col">Akcije</th>
        </tr>
        </tfoot>
    </table>

</main>
</body>
</html>