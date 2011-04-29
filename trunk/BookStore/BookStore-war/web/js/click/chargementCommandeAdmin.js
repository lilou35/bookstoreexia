
$(document).ready(function(){
    
    $("div[id^='commande']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
        $.post("../panier/detailCommande.htm", {id: obj.id} ,  function(data){
            
            //$(this).css('background-color', "#CCCCCC");
            $('#commande-'+ obj.id).before(data);
            $('#commande-'+ obj.id).toggle();
            
            
            $("div[id^='commandeDetail']").click(function(){
                var obj = $(this).metadata({type:'attr',name:'data'});
                $('#commande-'+ obj.id).css('display', 'inline');
                $(this).remove();
                
                
            });
            
        })
        
    });
    
     
        
   
    
    

    



});

