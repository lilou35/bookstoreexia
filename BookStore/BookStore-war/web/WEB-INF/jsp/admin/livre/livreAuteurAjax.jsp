 <%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
 <table>
     <tr>
         <td rowspan="3" colspan="2">
             <div class="article<c:if test="${livre.livrestock<=0}">Rupture</c:if><c:if test="${livre.livreetat=='à Venir'}">Venir</c:if>" id="article-${livre.livreid}"  >
                 <img src="<c:url value="/images/${livre.livrecouverture}" />" /><br/>
                 Titre: ${livre.livretitre} <br/>
                 Etat: <c:if test="${livre.livreetat=='Nouveauté'}"><span class="nouveau"></c:if>${livre.livreetat}<c:if test="${livre.livreetat=='Nouveauté'}"></span></c:if> <br/>
                 Prix: ${livre.livreprix} <br/>
                 <c:if test="${livre.livrestock<=0}">Rupture</c:if>
             </div>
         </td>
     </tr>
     <tr>
         <td colspan="2">
             <div class="ajouter" id="ajouter">
                 <br/>
                <c:forEach items="${livre.auteurList}" var="auteur"  >
                    <div class="retirable" data="{idAuteur:'${auteur.auteurid}', idLivre:'${livre.livreid}'}">
                        <br/>
                        ${auteur.auteurprenom} ${auteur.auteurnom}<br/>
                        <br/>
                    </div>
                </c:forEach>
                 <br/>
            </div>
         </td>
     </tr>
     <tr>
         <td>
             <div> Pour ajouter un auteur faite le glisser au dessus.</div>
             <div class="retirer" id="retirer">
                 <br/>
             <c:forEach items="${auteurs}" var="auteur" >
                 <div class="ajoutable" data="{idAuteur:'${auteur.auteurid}', idLivre:'${livre.livreid}'}" >
                    <br/>
                    ${auteur.auteurprenom} ${auteur.auteurnom}<br/>
                    <br/>
                </div>
            </c:forEach>
                 <br/>
            </div>
         </td>
         <td>
             <div id="modifier"  align="center">
                <c:if test="${action==null}">Ajouter un auteur:</c:if>${action}<br/><br/>
                <span class="error" >${erreur} </span><br/>
                Nom: <input type="text" id="nom" value="${nom}"/><br/>
                Prenom: <input type="text" id="prenom" value="${prenom}"/><br/>
                <input type="button" value="<c:if test="${action==null}">Ajouter</c:if>${action}" id="<c:if test="${action==null}">sumbit</c:if>${action}" data="{idLivre:'${livre.livreid}', idAuteur:'${auteurId}'}" />
            </div>
         </td>
     </tr>
 </table>
<p align="right">
    <input type="button" value="Terminer" id="terminer" data="{lettre:'${livre.livretitre}'}" />
</p>
 







 
       



            