function modifierArticles(html){
   $("#articles").replaceWith(html);
   evenement();
}



$(document).ready(function(){
    
    $("#panier").css("display", "none");
    
    evenement();
    
    


});

function evenement(){
    $("input[id='moins']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
         $.post("moins.htm", {id: obj.id} ,  function(data)
                                                {
                                                    //alert(data);
                                                    modifierArticles(data);
                                                    
                                                }
            );
       
    });

    $("input[id='plus']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
        $.post("plus.htm", {id: obj.id} ,  function(data)
                                                {
                                                    //alert(data);
                                                    modifierArticles(data);
                                                }
            );
    });

    $("input[id='retirer']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
        $.post("retirer.htm", {id: obj.id} ,  function(data)
                                                {
                                                    //alert(data);
                                                    modifierArticles(data);
                                                }
            );
    });
    
    $("input[id='terminer']").click(function(){
        if($('#carte').val().length<=10 ){
            $('#erreurCarte').html("Vous n'avez pas saisie au moin 10 chiffres");
            //evenement();
        }
        else{
            
        
        $.post("terminerCommande.htm", {carte: $('#carte').val()} ,  function(data)
                                                {
                                                    //alert(data);
                                                   modifierArticles(data);
                                                }
            );
        }
    });
}