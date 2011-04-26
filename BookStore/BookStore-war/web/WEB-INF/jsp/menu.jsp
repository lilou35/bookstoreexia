
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<link href="<c:url value="/css/style.css" />" rel="stylesheet" type="text/css" media="screen" />
<script src="<c:url value="/js/jquery.corner.js" />" type="text/javascript" > </script>
<script src="<c:url value="/js/mouse/menu.js" />" type="text/javascript"> </script>
<script src="<c:url value="/js/style.js" />" type="text/javascript"> </script>


<div id="tete">
    <table  cellspacing="0" cellpadding="0">
        <tr>
            <td align="left">
               
            </td>
            <td align="center" width="100%">
                
            </td>

        </tr>
    </table>
</div>
<div id="javascript" align="center">
    <img border="none" src="<c:url value="/css/images/important.gif" />"/> Le Javascript n'est pas activé<img border="none" src="<c:url value="/css/images/important.gif" />"/>
</div>





<div id="connection">
    Bienvenue
    <c:if test="${sessionScope['scopedTarget.session'].client.clientid!=null}">
          ${sessionScope['scopedTarget.session'].client.clientprenom} ${sessionScope['scopedTarget.session'].client.clientnom} <br>
         
    </c:if>
    
   
    <a class="bouton" href="<c:url value="/accueil/accueil.htm" />"><div id="boutonMenu" class="boutonMenu">Accueil</div></a>
    <a class="bouton" href="<c:url value="/categorie/categories.htm" />"><div id="boutonMenu" class="boutonMenu">Categories</div></a>
    <a class="bouton" href="<c:url value="/auteur/auteurs.htm" />"><div id="boutonMenu" class="boutonMenu">Auteurs</div></a>
    <a class="bouton" href="<c:url value="/livre/livreListe.htm" />"><div id="boutonMenu" class="boutonMenu">Livres</div></a>

    
   
    <c:if test="${sessionScope['scopedTarget.session'].client.clientid==null}">
        <a class="bouton" href="<c:url value="/login/inscription.htm" />"><div id="boutonMenu" class="boutonMenu">Inscription</div></a>
        <a class="bouton" href="<c:url value="/login/login.htm" />"><div id="boutonMenu" class="boutonMenu">Connexion</div></a>
    </c:if>
    <c:if test="${sessionScope['scopedTarget.session'].client.clientid!=null}">
        <a class="bouton" href="<c:url value="/login/monCompte.htm" />"><div id="boutonMenu" class="boutonMenu">Mon compte</div></a>
        <a class="bouton" href="<c:url value="/login/deconnexion.htm" />"><div id="boutonMenu" class="boutonMenu">Déconnexion</div></a>
    </c:if>
        
        <a class="bouton" href="<c:url value="/login/adminLogin.htm" />"><div id="boutonMenu" class="boutonMenu">Administration</div></a>
    
    
    
    <a class="panierLien" href="<c:url value="/panier/panier.htm" />">
        <div id="panier" class="panier" style="height: 100%; width: 100%;">

             <c:if test="${sessionScope['scopedTarget.session'].panier.affichagePanier==null}">
                 Votre Panier: <br/><br/><br/>
             </c:if>
             ${sessionScope['scopedTarget.session'].panier.affichagePanier}

        </div>        
    </a>
    
</div>
