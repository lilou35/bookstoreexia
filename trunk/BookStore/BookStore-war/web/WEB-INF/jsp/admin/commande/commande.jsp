
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

                <c:if test="${commandes!=null}">
                    <div id="listeCommande" >
                         <c:forEach items="${commandes}" var="commande" >
                             <div id="commande-${commande.commandeid}" class="commande" data="{id :'${commande.commandeid}'}" >
                                Id de la commande: ${commande.commandeid}<br/>
                                Etat de la commande: ${commande.commandeid}<br/>                                
                                Statu: ${commande.commandeetat}<br/>                              
                                Date de commande: ${commande.commandedate}<br/>
                                Date de livraison: ${commande.commandedatelivraison}
                             </div>
                        </c:forEach>

                    </div>

                   
                </c:if>
