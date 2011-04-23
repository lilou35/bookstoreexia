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
        <script src="<c:url value="/js/click/chargementLivre.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/click/panier.js" />" type="text/javascript" > </script>

        <title>Categorie: ${categorie.categorietype}</title>
    </head>

    <body>
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Categorie: ${categorie.categorietype}
            </div>
            <div id="contenu" align="center">
                <div > ${categorie.categoriedescription} </div>
                <div> Pour ajouter un acticle faite le glisser jusqu'au panier.</div>
                <div class="listeArticle" >
                 <c:forEach items="${categorie.livreList}" var="livre" >
                     <div class="article" id="article-${livre.livreid}" data="{id :'${livre.livreid}'}" >
                         <img src="<c:url value="/images/${livre.livrecouverture}" />" />
                         Titre: ${livre.livretitre} <br/>
                         Etat: ${livre.livreetat} <br/>
                         Prix: ${livre.livreprix} <br/>
                     </div>
                </c:forEach>
                    
                </div>
       



            </div>




        </div>
    </body>
</html>

