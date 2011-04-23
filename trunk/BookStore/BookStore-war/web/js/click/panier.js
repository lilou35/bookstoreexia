function draggable(){
     $("div[class^='article']").Draggable(	
        {		
            zIndex: 	1000,		
            ghosting:	true,		
            opacity: 	0.7,
            revert:     true
        }
    );
}


$(document).ready(function(){
    
   draggable();
    
    
    
    
    $('#panier').Droppable(	
        {		
        accept : 'article', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.post("../panier/ajoutLivre.htm", {id: obj.id} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $('#panier').html($('#panier').html() +"<hr/>"+ data);
                                                                            }
                        );
                            
                            
                        //$(this).html($(this).html() +'<hr/>' +$(drag).html());
//                        alert(this.html()); //the droppable					
//                        alert(drag); //the dragganle				
                    },		
            fit: true,
            pointer: true,
            intersect: true
        }
    );
        
    
        
        
    });
