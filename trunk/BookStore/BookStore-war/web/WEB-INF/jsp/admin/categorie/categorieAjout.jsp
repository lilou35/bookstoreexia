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

        <title>Nouvelle Categorie</title>
    </head>

    <body>
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Nouvelle Categorie
            </div>
            <div id="contenu" align="center">
                <form:form method="POST" action="adminCategorieAjouter.htm" commandName="categorie">
                    <form:hidden path="categorieid" />
                    <table>
                        <tr>
                            <td colspan="3" align="center">
                                <h1>Nouvelle de Catégorie</h1>

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
                                Titre:
                            </td>
                            <td>
                                <form:input path="categorietype" />
                            </td>
                            <td>
                                <form:errors path="categorieid" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Description:
                            </td>
                            <td>
                                <form:textarea path="categoriedescription" />
                            </td>
                            <td>
                                <form:errors path="categoriedescription" cssClass="error" />
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

