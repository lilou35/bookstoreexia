<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<div class="article" id="detail-${livre.livreid}" data="{id :'${livre.livreid}'}">
    <table style="background-color: #ffff00">
        <tr>
            <th>Titre</th>
            <th>R�sum�</th>
            <th>nb Vente</th>
            <th>parution</th>
            <th>couvetrure</th>
        </tr>

        <tr>
            <td> ${livre.livretitre} </td>
            <td> ${livre.livreresume}</td>
            <td> ${livre.livrenbvente}</td>
            <td> ${livre.livreparution}</td>
            <td> <img src="<c:url value="/images/${livre.livrecouverture}" />" /></td>
        </tr>

    </table>
</div>





       

