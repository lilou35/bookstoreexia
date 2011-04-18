<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>
    
    <body>
        Hello:
        Top 10:
        <table>
            <tr>
                <th>Titre</th>
                <th>Résumé</th>
                <th>nb Vente</th>
                <th>parution</th>
                <th>couvetrure</th>
            </tr>
             <c:forEach items="${livres}" var="livre" >
                <tr>
                    <td> ${livre.livretitre} </td>
                    <td> ${livre.livreresume}</td>
                    <td> ${livre.livrenbvente}</td>
                    <td> ${livre.livreparution}</td>
                    <td> ${livre.livrecouverture}</td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
