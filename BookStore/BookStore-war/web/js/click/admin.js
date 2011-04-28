$(document).ready(function(){
    $("input[id='modifier']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
        window.location.href='../livre/afficherModifierLivreAdmin.htm?id=' + obj.id;
         
       
    });

    
});
