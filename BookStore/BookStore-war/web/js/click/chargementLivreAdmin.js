


$(document).ready(function(){
    
    $("div[id^='article']").click(function(){
        var obj = $(this).metadata({type:'attr',name:'data'});
        $.post("../livre/chargementLivreAdmin.htm", {id: obj.id} ,  function(data){
            
            //$(this).css('background-color', "#CCCCCC");
            $('#article-'+ obj.id).before(data);
            $('#article-'+ obj.id).css('display', 'none');
            
            
            $("div[id^='detail']").click(function(){
                var obj = $(this).metadata({type:'attr',name:'data'});
                $('#article-'+ obj.id).css('display', 'inline');
                $(this).remove();
                
                
            });
            
        })
        
    });
    
     
        
   
    
    

    



});

