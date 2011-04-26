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
        <script src="<c:url value="/js/interface.js" />" type="text/javascript" > </script>


        <title>Nouveau Livre</title>
    </head>

    <body>
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Nouveau Livre
            </div>
            <div id="contenu" align="center">
                <form:form method="POST" action="ajouterLivreAdmin.htm" commandName="livre">
                    <form:hidden path="livreid" />
                    <table>
                        <tr>
                            <td colspan="3" align="center">
                                <h1>Nouveau Livre</h1>

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
                                <form:input path="livretitre" />
                            </td>
                            <td>
                                <form:errors path="livretitre" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Résumé:
                            </td>
                            <td>
                                <form:textarea path="livreresume" />
                            </td>
                            <td>
                                <form:errors path="livreresume" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Sommaire:
                            </td>
                            <td>
                                <form:textarea path="livresommaire" />
                            </td>
                            <td>
                                <form:errors path="livresommaire" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Couverture:
                            </td>
                            <td>
                                <form:input path="livrecouverture" />
                            </td>
                            <td>
                                <form:errors path="livrecouverture" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Stock:
                            </td>
                            <td>
                                <form:input path="livrestock" />
                            </td>
                            <td>
                                <form:errors path="livrestock" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Prix:
                            </td>
                            <td>
                                <form:input path="livreprix" />
                            </td>
                            <td>
                                <form:errors path="livreprix" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Stock Alerte:
                            </td>
                            <td>
                                <form:input path="livrestockalerte" />
                            </td>
                            <td>
                                <form:errors path="livrestockalerte" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Editeur:
                            </td>
                            <td>
                                <form:input path="livreediteur" />
                            </td>
                            <td>
                                <form:errors path="livreediteur" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Date de parution: 
                            </td>
                            <td>
                                <form:input path="livreparution" />
                            </td>
                            <td>
                                <form:errors path="livreparution" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Etat:
                            </td>
                            <td>
                                <label><form:radiobutton  value="Nouveauté" path="livreetat"/>Nouveauté</label>
                                <label><form:radiobutton value="à Venir" path="livreetat"/>à Venir</label>
                                <label><form:radiobutton value="en Stock" path="livreetat"/>en Stock</label>
                                <label><form:radiobutton value="en Réapprovisionnement" path="livreetat"/>en Réapprovisionnement</label>
                            </td>
                            <td>
                                <form:errors path="livreetat" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Catégorie:
                            </td>
                            <td>
                                <form:select path="categorie">
                                    <c:forEach items="${categories}" var="categorie" >
                                        <form:option value="${categorie.categorieid}">${categorie.categorietype}</form:option>
                                    </c:forEach>
                                </form:select>
                            </td>
                            <td>
                                <form:errors path="categorie" cssClass="error" />
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

