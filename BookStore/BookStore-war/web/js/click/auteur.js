function draggable(){
     $("div[class='retirable']").Draggable(	
        {		
            zIndex: 	1000,		
            ghosting:	true,		
            opacity: 	0.7,
            revert:     true
        }
    );
    $("div[class='ajoutable']").Draggable(	
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
    
    
    
    
    $('#ajouter').Droppable(	
        {		
        accept : 'ajoutable', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        alert("action");
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.get("ajoutAuteurALivre.htm", {idAuteur: obj.idAuteur,idLivre: obj.idLivre} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $('#contenu').html(data);
                                                                                draggable();
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
