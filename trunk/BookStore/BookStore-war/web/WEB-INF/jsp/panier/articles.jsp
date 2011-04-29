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
                     <td><input type="button" id="moins" value="-" data="{id:'${article.livre.livreid}'}"/>${article.qtt}<input type="button" id="plus" value="+" <c:if test="${article.livre.livrestock<=article.qtt}">disabled="true"</c:if> data="{id:'${article.livre.livreid}'}"/><c:if test="${article.livre.livrestock<=article.qtt}">Stock Insuffisant</c:if></td>
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
         <div>
             <c:if test="${sessionScope['scopedTarget.session'].client.clientid==null}">
                <a class="bouton" href="<c:url value="/login/inscription.htm" />"><div id="bouton" class="bouton">Nouveau Client</div></a>
                <a class="bouton" href="<c:url value="/login/login.htm" />"><div id="bouton" class="bouton">Déjà client</div></a>
            </c:if>
            <c:if test="${sessionScope['scopedTarget.session'].client.clientid!=null}">
                Vous êtes ${sessionScope['scopedTarget.session'].client.clientprenom} ${sessionScope['scopedTarget.session'].client.clientnom} sinon merci de <a class="bouton" href="<c:url value="/login/deconnexion.htm" />"><div id="bouton" class="bouton">Cliquer ici</div></a> <br/>
                <fieldset>
                    <legend> Adresse de livraison: </legend>
                    ${sessionScope['scopedTarget.session'].client.clientrue}<br/>
                    ${sessionScope['scopedTarget.session'].client.clientcodepostal}${sessionScope['scopedTarget.session'].client.clientville}
                </fieldset>
                    Si cette adresse de vous convient pas vous pouvez la changer ici: <a class="bouton" href="<c:url value="/login/monCompte.htm" />"><div id="bouton" class="bouton">Changer mon adresse</div></a>
                    <br/>
                    <br/>
                    <input type="text" id="carte" size="10"/>
                    <input type="buton" id="terminer" />Terminer votre Commande</div></a>
                
            </c:if>
                    
        </div>
   </c:otherwise>
</c:choose>
        




       

