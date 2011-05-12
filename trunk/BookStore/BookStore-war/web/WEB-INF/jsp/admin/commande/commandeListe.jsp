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
        <script src="<c:url value="/js/interface.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/click/chargementCommandeAdmin.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/click/etatCommande.js" />" type="text/javascript" > </script>
        <script src="<c:url value="/js/alerts.js" />" type="text/javascript" > </script>
        <link href="<c:url value="/css/alerts.css" />" rel="stylesheet" type="text/css" media="screen" />
        

        <title>Liste des Commmande</title>
    </head>

    <body>
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Liste des Commmande
            </div>
            <div id="contenu">
                <div align="left">
                    <a class="bouton" href="ajouterLivreAdmin.htm"><div id="bouton" class="bouton">Ajouter un livre</div></a>
                    
                </div>
                <div align="right">
                    <form action="listeCommandeRecherche.htm" method="post" >
                        <label>Rechercher par Id:
                            <input type="text" name="id" >
                        </label>
                        <input type="submit" value="Rechercher">
                    </form>
                </div>
                <table width="100%" rules="rows">
                    <tr align="center">
                        <td colspan="8">
                            <form action="listeCommande.htm" method="get" >
                                <input name="etat" type="submit"  value="Validée">
                                <input name="etat" type="submit"  value="en Préparation">
                                <input name="etat" type="submit"  value="Annulée">
                                <input name="etat" type="submit"  value="Envoyée">
                                <input name="etat" type="submit"  value="Du Jour" disabled>                                
                            </form>
                            
                            
                        </td>
                    </tr>
                    <tr align="center">
                        <td colspan="9">
                            <span class="error">${error}</span>
                            <span class="valide">${info}</span>
                            ${message} ${etat}
                        </td>
                    </tr>
                </table>
                        <%@include file="commande.jsp" %>
                






            



            </div>
                        <table width="100%">
                            <tr align="center">
                                <td><div id="valider" style=" width: 150px; background-color: #009900;"> <br/>Validée<br/><br/></div></td>
                                <td><div id="preparer" style=" width: 150px; background-color: #0000ff;"> <br/>En Préparation<br/><br/></div></td>
                                <td><div id="annuler" style=" width: 150px; background-color: #ff0000;"> <br/>Annulée<br/><br/></div></td>
                                <td><div id="envoyer" style=" width: 150px; background-color: darkorchid;"> <br/>Envoyée<br/><br/></div></td>
                            </tr>
                        </table>
            
            
            
            
            
        </div>
    </body>
</html>

