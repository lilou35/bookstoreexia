<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:if test="${total == 0}">
    <div>Votre panier ne contient aucun Article</div>
</c:if>
                
                
<c:if test="${total != 0}">
    <table border="1px" id="articles">
        <tr>
            <th>Couveture</th>
            <th>Livre</th>
            <th>Quantité</th>
            <th>Prix Unitaire</th>
            <th>Sous Total</th>
        </tr>
         <c:forEach items="${panier}" var="article" >
             <tr>
                 <td><img height="100px" width="75px" src="<c:url value="/images/${article.livre.livrecouverture}" />" /></td>
                 <td>${article.livre.livretitre}</td>
                 <td><input type="button" id="moins" value="-" data="{id:'${article.livre.livreid}'}"/>${article.qtt}<input type="button" id="plus" value="+" data="{id:'${article.livre.livreid}'}"/></td>
                 <td>${article.livre.livreprix}</td>
                 <td>${article.sousTotal}</td>
                 <td><input type="button" value="Retirer" id="retirer" data="{id:'${article.livre.livreid}'}" />
             </tr>
        </c:forEach>
             <tr>
                 <td colspan="4" align="right">Total</td>
                 <td>${total}</td>
             </tr>
    </table>
</c:if>





       

