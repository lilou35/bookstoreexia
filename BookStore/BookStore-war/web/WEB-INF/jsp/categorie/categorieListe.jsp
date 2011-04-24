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

        <title>Categories</title>
    </head>

    <body>
         <%@include file="../menu.jsp" %>
        <div id="corps">


            <div id="titre">
                Categories:
            </div>
            <div id="contenu">
                
            
                 <c:forEach items="${categories}" var="categorie" >
                    <dl>
                        <dt><a href="categorie.htm?id=${categorie.categorieid}"> ${categorie.categorietype}</a> </dt>
                        <dd> ${categorie.categoriedescription} </dd>

                    </dl>
                </c:forEach>
            
       



            </div>




        </div>
    </body>
</html>

