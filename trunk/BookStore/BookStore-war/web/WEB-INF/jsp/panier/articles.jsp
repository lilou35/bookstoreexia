<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:choose>
    <c:when test="${total == 0}">
        <div>Votre panier ne contient aucun Article</div>
    </c:when>
    <c:when test="${total == null}">
        <div>Votre panier ne contient aucun Article</div>
    </c:when>


   <c:otherwise>
        <table id="articles" width="100%" rules="rows">
            <tr>
                <th>Couveture</th>
                <th>Livre</th>
                <th>Quantité</th>
                <th>Prix Unitaire</th>
                <th>Sous Total</th>
                <th></th>
            </tr>
             <c:forEach items="${panier}" var="article" >
                 <tr align="center">
                     <td><img height="100px" width="75px" src="<c:url value="/images/${article.livre.livrecouverture}" />" /></td>
                     <td>${article.livre.livretitre}</td>
                     <td><input type="button" id="moins" value="-" data="{id:'${article.livre.livreid}'}"/>${article.qtt}<input type="button" id="plus" value="+" data="{id:'${article.livre.livreid}'}"/></td>
                     <td>${article.livre.livreprix}</td>
                     <td align="right">${article.sousTotal}</td>
                     <td><input type="button" value="Retirer" id="retirer" data="{id:'${article.livre.livreid}'}" />
                 </tr>
            </c:forEach>
                 <tr>
                     <th colspan="4" align="right">Total</th>
                     <th align="right">${total}</th>
                     <td></td>
                 </tr>
        </table>
   </c:otherwise>
</c:choose>





       

