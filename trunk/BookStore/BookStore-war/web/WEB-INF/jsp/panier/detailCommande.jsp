<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:forEach items="${commandes}" var="commade" step="tour" >
    <div id="commandeDetail">
        <c:if test="${tour==0}">
                Etat de la commande: ${commande.commandeid}<br/>
                <br/>
                Statu: ${commande.commandeetat}<br/>
                <br/>
                Date de commande: ${commande.commandedate}<br/>
                <br/>
                Date de livraison: ${commande.commandedatelivraison}<br/>
                <br/>
        </c:if>
         <div class="article<c:if test="${commande.livre.livrestock<=0}">Rupture</c:if><c:if test="${commande.livre.livreetat=='à Venir'}">Venir</c:if>" id="article-${commande.livre.livreid}" data="{id :'${commande.livre.livreid}'}" >
             <img src="<c:url value="/images/${commande.livre.livrecouverture}" />" /><br/>
             Titre: ${commande.livre.livretitre} <br/>
             Etat: <c:if test="${commande.livre.livreetat=='Nouveauté'}"><span class="nouveau"></c:if>${commande.livre.livreetat}<c:if test="${commande.livre.livreetat=='Nouveauté'}"></span></c:if> <br/>
             Prix: ${commande.livre.livreprix} <br/>
             <c:if test="${commande.livre.livrestock<=0}">Rupture</c:if><br/>

         </div>

    </div>
</c:forEach>


