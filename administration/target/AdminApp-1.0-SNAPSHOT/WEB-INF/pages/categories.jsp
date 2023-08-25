<%@ page import="com.example.adminapp.beans.CategoryBean" %>
<%@ page import="com.example.adminapp.models.Category" %>
<%@ page import="java.net.URISyntaxException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="categoryBean" type="com.example.adminapp.beans.CategoryBean" scope="session"/>
<!doctype html>
<html lang="en">
<head>
    <title>Categories</title>
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
    <div class="container">
        <br>
        <h1>Kategorije</h1>
        <button type="button" class="btn btn-success" onclick="location.href='?action=add-category'">
            <span class="fa fa-plus"></span>
            Dodaj kategoriju
        </button>
        <hr>
        <table id="example" class="table table-hover" style="width:100%">
            <caption>Kategorije</caption>
            <thead>
            <tr>
                <th scope="col">Id</th>
                <th scope="col" style="width: 50%">Naziv</th>
<%--                <th scope="col">Parent Category Id</th>--%>
                <th scope="col">Akcije</th>
            </tr>
            </thead>
            <tbody>
            <% try {
                for (Category category : categoryBean.getAll()) { %>
                <tr>
                    <td>
                        <%=category.getId()%>
                    </td>
                    <td>
                        <%=category.getName()%>
                    </td>
    <%--                <td>--%>
    <%--                    <% if (category.getParentCategoryId() != 0) {%>--%>
    <%--                    <%=category.getParentCategoryId() %>--%>
    <%--                    <%}%>--%>
    <%--                </td>--%>
                    <td>
                        <div class="d-flex flex-row mb-3">
                            <div style="align-content: center;justify-content: center">
                                <button type="button" class="btn btn-info"
                                        onclick="location.href='?action=update-category&id=<%=category.getId()%>'">
                                    Izmjeni
                                </button>
                            </div>
                            <div style="align-content: center;justify-content: center">
                                <button type="button" class="btn btn-danger"
                                        onclick="location.href='?action=delete-category&id=<%=category.getId()%>'">
                                    Obrisi
                                </button>
                            </div>
                        </div>
                    </td>
                </tr>
                <% }
            } catch (URISyntaxException | InterruptedException e) {
                throw new RuntimeException(e);
            } %>

            </tbody>
            <tfoot>
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Naziv</th>
<%--                <th scope="col">Parent Category Id</th>--%>
                <th scope="col">Akcije</th>
            </tr>
            </tfoot>
        </table>
    </div>
</main>
</body>
</html>