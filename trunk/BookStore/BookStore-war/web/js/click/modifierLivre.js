$(document).ready(function(){
    $("input[id='supprimer']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
         jConfirm('Etes vous sur?', 'Supprimmer',function(raison){
                            if(raison){
                                
                                $.post("supprimer.htm", {id: obj.id} ,  function(data)
                                                                                    {
                                                                                        //alert(data);
                                                                                        $('#contenu').replaceWith(data);
                                                                                        
                                                                                    }
                                );
                            }
                           
                            
                            
                        });
         
       
    });

    
});
