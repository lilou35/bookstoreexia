function draggable(){
     $("div[class='commande']").Draggable(	
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
    
    
    
    
    $('#valider').Droppable(	
        {		
        accept : 'commande', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.post("valider.htm", {id: obj.id, etat: "Validée"} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $(html).html(data);
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
        
    $('#preparer').Droppable(	
        {		
        accept : 'commande', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.post("preparer.htm", {id: obj.id, etat: "en Préparation"} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $(html).html(data);
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
        
    $('#annuler').Droppable(	
        {		
        accept : 'commande', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        jPrompt( 'Raison de l\'annulation', raisoon, title, callback )
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.post("preparer.htm", {id: obj.id, etat: "en Préparation"} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $(html).html(data);
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
