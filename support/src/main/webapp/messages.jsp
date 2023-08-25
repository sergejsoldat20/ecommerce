
<%@page import="com.example.customersupport.beans.MessageBean" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="messageService" class="com.example.customersupport.services.MessageService" scope="application"/>
<jsp:useBean id="adminBean" class="com.example.customersupport.beans.AdminBean" scope="session"/>
<!doctype html>
<%
    if (!adminBean.isLoggedIn())
        response.sendRedirect("sign-in.jsp");
%>
<html lang="en">
<head>
    <title>Messages</title>
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
<body style="margin:20px auto">
<%@include file="WEB-INF/header.jsp" %>
<main role="main" class="container">
    <div class="container">
        <br><br>
        <h2>Poruke</h2>
        <table id="example" class="table table-striped" style="width:100%">
            <caption>Messages</caption>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <!-- TODO: Set max width for column. -->
                <th scope="col">Sadrzaj</th>
                <th scope="col">Username</th>
                <th scope="col">e-mail</th>
                <th scope="col">Akcije</th>
            </tr>
            </thead>
            <tbody>
            <% for (MessageBean message : messageService.getUnreadMessages()) { %>
            <tr>
                <td>
                    <%=message.getId()%>
                </td>
                <td>
                    <%=message.getMessageText()%>
                </td>
                <td>
                    <%=message.getAccountEmail()%>
                </td>
                <td>
                    <%=message.getAccountUsername()%>
                </td>
                <td>
                    <button type="button" class="btn btn-success"
                            onclick="location.href='message.jsp?id=<%=message.getId()%>'">
                        &nbsp;Pogledaj
                    </button>
                </td>
            </tr>
            <% } %>

            </tbody>
            <tfoot>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Sadrzaj</th>
                <th scope="col">Username</th>
                <th scope="col">e-mail</th>
                <th scope="col">Akcije</th>
            </tr>
            </tfoot>
        </table>
    </div>
</main>
</body>
</html>