
<%@ page import="com.example.customersupport.beans.MessageBean" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page errorPage="error.jsp" %>
<jsp:useBean id="messageService" class="com.example.customersupport.services.MessageService" scope="application"/>
<jsp:useBean id="adminBean" class="com.example.customersupport.beans.AdminBean" scope="session"/>

<%! MessageBean messageBean = new MessageBean(); %>
<%

    if (adminBean.isLoggedIn()) {
        if (request.getParameter("id") != null) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                messageBean = messageService.getById(id);
                messageBean.setSeen(true);

            } catch (Exception e) {
                response.sendRedirect("messages.jsp");
            }
        } else if (request.getParameter("submit") != null) {
            messageService.sendMessage(messageBean.getAccountEmail(), request.getParameter("content"), messageBean.getId());
            response.sendRedirect("messages.jsp");
        }
    } else {
        response.sendRedirect("sign-in.jsp");
    }
%>
<!doctype html>
<%

%>
<html lang="en">
<head>
    <title>New message</title>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <style>
        .center {
            margin: auto;
            width: 50%;
        }
    </style>
</head>
<body>
<%@include file="WEB-INF/header.jsp" %>
<br>
<br>
<div class="card border-success mb-3 center" style="max-width: 50rem;">
    <div class="card-header">View message</div>
    <div class="card-body">
        <div class="card-body">
            <form>
                <div class="form-group">
                    <label class="form-label" for="receivedContent">Received message</label>
                    <textarea class="form-control" id="receivedContent" name="receivedContent" disabled
                              rows="4"><%=messageBean.getMessageText()%></textarea>
                </div>
                <div class="form-group">
                    <label class="form-label" for="messageReply">Reply message</label>
                    <textarea class="form-control" id="messageReply" name="content" rows="4"
                              placeholder="Reply message"></textarea>
                </div>
                <button type="submit" name="submit" class="btn btn-success">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>