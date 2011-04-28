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
        <script src="<c:url value="/js/jquery.metadata.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/interface.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/click/chargementLivreAdmin.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/click/admin.js" />" type="text/javascript" > </script>

        <title>Accueil</title>
    </head>

    <body>
         <%@include file="menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Accueil
            </div>
            <div id="contenu" align="center">
                Livre en Alerte de réapprovisionnement:
            <div class="listeArticle" >
                 <c:forEach items="${livres}" var="livre" >
                     <div class="article<c:if test="${livre.livrestock<=0}">Rupture</c:if><c:if test="${livre.livreetat=='à Venir'}">Venir</c:if>" id="article-${livre.livreid}" data="{id :'${livre.livreid}'}" >
                         <img src="<c:url value="/images/${livre.livrecouverture}" />" /><br/>
                         Titre: ${livre.livretitre} <br/>
                         Etat: <c:if test="${livre.livreetat=='Nouveauté'}"><span class="nouveau"></c:if>${livre.livreetat}<c:if test="${livre.livreetat=='Nouveauté'}"></span></c:if> <br/>
                         Prix: ${livre.livreprix} <br/>
                         <c:if test="${livre.livrestock<=0}">Rupture</c:if><br/>
                         <input id="modifier" type="button" value="Modifier" data="{id:'${livre.livreid}'}"/>
                     </div>
                </c:forEach>
                    
            </div>



            </div>




        </div>
    </body>
</html>

