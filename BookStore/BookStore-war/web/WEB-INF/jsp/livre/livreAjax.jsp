<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="article<c:if test="${livre.livrestock<=0}">Rupture</c:if><c:if test="${livre.livreetat=='� Venir'}">Venir</c:if>" id="detail-${livre.livreid}" data="{id :'${livre.livreid}'}">
    <table style="background-color: #ffff00">
        <tr>
            <th>Titre</th>
            <th>R�sum�</th>
            <th>nb Vente</th>
            <th>Etat</th>
            <th>parution</th>
            <th>couvetrure</th>
        </tr>

        <tr>
            <td> ${livre.livretitre} </td>
            <td> ${livre.livreresume}</td>
            <td> ${livre.livrenbvente}</td>
            <td> <c:if test="${livre.livreetat=='Nouveaut�'}"><span class="nouveau"></c:if>${livre.livreetat}<c:if test="${livre.livreetat=='Nouveaut�'}"></span></c:if></td>
            <td> ${livre.livreparution}</td>
            <td> <img src="<c:url value="/images/${livre.livrecouverture}" />" /></td>
            <td><c:if test="${livre.livrestock <= livre.livrestockalerte}">Il reste ${livre.livrestock} exemplaire(s)</c:if><c:if test="${livre.livrestock<=0}">Rupture</c:if></td>
        </tr>

    </table>
</div>





       

