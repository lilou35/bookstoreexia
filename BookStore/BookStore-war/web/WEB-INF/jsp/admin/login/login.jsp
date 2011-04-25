<%--
    Document   : accueil
    Created on : 19 janv. 2011, 10:03:12
    Author     : Wikola
--%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>

        <script src="<c:url value="/js/jquery-1.5.1.min.js" />" type="text/javascript" > </script>

        <title>Login</title>
    </head>

    <body>
        <%-- TODO NicoExia sortir le menu --%>
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Login
            </div>
            <div id="contenu" align="center">
                <form:form method="POST" action="adminLogin.htm" commandName="login">
                <table>
                    <tr>
                        <td colspan="3" align="center">
                            <h1>Login</h1>
                            <span style="color: #ff0000">${erreurLogin}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Login:
                        </td>
                        <td>
                            <form:input path="login" />
                        </td>
                        <td>
                            <form:errors path="login" cssClass="error" />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            Mot de passe:
                        </td>
                        <td>
                            <form:password showPassword="true" path="pass" />
                        </td>
                        <td>
                            <form:errors path="pass" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <input type="submit" value="Valider">
                        </td>
                    </tr>
                </table>
                </form:form>
       



            </div>




        </div>
    </body>
</html>

