<%--
    Document   : accueil
    Created on : 19 janv. 2011, 10:03:12
    Author     : Wikola
--%>

<%@page language="java" contentType="text/html; charset=ISO-8859-15" pageEncoding="ISO-8859-15"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">


<html>
    <head>
        
        <script src="<c:url value="/js/jquery-1.5.1.min.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/jquery.mapkey.min.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/jquery.metadata.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/click/chargementLivreAdmin.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/click/auteurListe.js" />" type="text/javascript" > </script>
        

        <title>Auteurs</title>
    </head>

    <body>
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Auteurs
            </div>
            <div id="contenu">
                <div align="left">
                    <a class="bouton" href="ajouterLivreAdmin.htm"><div id="bouton" class="bouton">Ajouter un livre</div></a>
                    
                </div>
                <div align="right">
                    <form action="livreListeAdmin.htm" method="post" >
                        <label>Rechercher:
                            <input type="text" name="lettre" >
                        </label>
                        <input type="submit" value="Rechercher">
                    </form>
                </div>
                <table width="100%" rules="rows">
                    <tr align="center">
                        <td colspan="8">
                            <form action="livreListeAdmin.htm" method="post" >
                                <input name="lettre" type="submit"  value="A">
                                <input name="lettre" type="submit"  value="B">
                                <input name="lettre" type="submit"  value="C">
                                <input name="lettre" type="submit"  value="D">
                                <input name="lettre" type="submit"  value="E">
                                <input name="lettre" type="submit"  value="F">
                                <input name="lettre" type="submit"  value="G">
                                <input name="lettre" type="submit"  value="H">
                                <input name="lettre" type="submit"  value="I">
                                <input name="lettre" type="submit"  value="J">
                                <input name="lettre" type="submit"  value="K">
                                <input name="lettre" type="submit"  value="L">
                                <input name="lettre" type="submit"  value="M">
                                <input name="lettre" type="submit"  value="N"><br>
                                <input name="lettre" type="submit"  value="O">
                                <input name="lettre" type="submit"  value="P">
                                <input name="lettre" type="submit"  value="Q">
                                <input name="lettre" type="submit"  value="R">
                                <input name="lettre" type="submit"  value="S">
                                <input name="lettre" type="submit"  value="T">
                                <input name="lettre" type="submit"  value="U">
                                <input name="lettre" type="submit"  value="V">
                                <input name="lettre" type="submit"  value="W">
                                <input name="lettre" type="submit"  value="X">
                                <input name="lettre" type="submit"  value="Y">
                                <input name="lettre" type="submit"  value="Z">
                            </form>
                            
                            
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="9">
                            <span class="error">${error}</span>
                            <span class="valide">${info}</span>
                            ${message} ${lettre}
                        </td>
                    </tr>
                </table>
                <c:if test="${livres!=null}">
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

                   
                </c:if>






                </table>



            </div>
        </div>
    </body>
</html>

