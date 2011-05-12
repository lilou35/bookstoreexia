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
                Connexion
            </div>
            <div id="contenu" align="center">
                <span class="error">${error}</span>
                <span class="valide">${valide}</span>
                ${message}
                <li>
                    <ul><a href="<c:url value="/panier/listeCommande.htm"/>"> Liste des Commandes </a></ul>
                </li>
       



            </div>




        </div>
    </body>
</html>

