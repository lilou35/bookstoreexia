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
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Mon Compte
            </div>
            <div id="contenu" align="center">
                <form:form method="POST" action="monCompte.htm" commandName="client">
                    <form:hidden path="clientid" />
                <table>
                    <tr>
                        <td colspan="3" align="center">
                            <h1>Mon Compte</h1>
                            <span style="color: #ff0000">${erreurLogin}</span>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="3" align="center">
                            <span class="error">${error}</span>
                            <span class="valide">${valide}</span>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Login:
                        </td>
                        <td>
                            <form:input path="clientlogin" />
                        </td>
                        <td>
                            <form:errors path="clientlogin" cssClass="error" />
                        </td>
                    </tr>
                     <tr>
                        <td>
                            Mot de passe:
                        </td>
                        <td>
                            <form:password showPassword="true" path="clientmdp" />
                        </td>
                        <td>
                            <form:errors path="clientmdp" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Nom:
                        </td>
                        <td>
                            <form:input path="clientnom" />
                        </td>
                        <td>
                            <form:errors path="clientnom" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Prénom:
                        </td>
                        <td>
                            <form:input path="clientprenom" />
                        </td>
                        <td>
                            <form:errors path="clientprenom" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            E-mail:
                        </td>
                        <td>
                            <form:input path="clientmail" />
                        </td>
                        <td>
                            <form:errors path="clientmail" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Rue:
                        </td>
                        <td>
                            <form:input path="clientrue" />
                        </td>
                        <td>
                            <form:errors path="clientrue" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Code Postal:
                        </td>
                        <td>
                            <form:input path="clientcodepostal" />
                        </td>
                        <td>
                            <form:errors path="clientcodepostal" cssClass="error" />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Ville:
                        </td>
                        <td>
                            <form:input path="clientville" />
                        </td>
                        <td>
                            <form:errors path="clientville" cssClass="error" />
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

