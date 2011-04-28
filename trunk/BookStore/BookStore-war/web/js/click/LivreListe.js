$(document).ready(function(){
    $("input[id='modifier']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
        window.location.href='modifierLivreAdmin.htm?id=' + obj.id;
         
       
    });
    
    $("input[id='alerte']").click(function(){
        
        window.location.href='alerteLivreListe.htm';
         
       
    });

    
});
