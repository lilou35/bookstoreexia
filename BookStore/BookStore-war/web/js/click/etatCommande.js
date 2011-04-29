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
                        $.post("valider.htm", {id: obj.id} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $('#listeCommande').replaceWith(data);
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
        
    $('#preparer').Droppable(	
        {		
        accept : 'commande', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.post("preparer.htm", {id: obj.id} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $('#listeCommande').replaceWith(data);
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
        
    $('#annuler').Droppable(	
        {		
        accept : 'commande', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        jPrompt( 'Raison de l\'annulation', 'Par le client', 'Annulation', function(raison){
                            if(raison){
                                
                            
                                var obj = $(drag).metadata({type:'attr',name:'data'});
                                $.post("annuler.htm", {id: obj.id, raison: raison} ,  function(data)
                                                                                    {
                                                                                        //alert(data);
                                                                                        $('#listeCommande').replaceWith(data);
                                                                                        draggable();
                                                                                    }
                                );
                            }
                            else{
                                jAlert('Une raison est obligatoire!', 'Annulation')
                            }
                            
                            
                        });
                        
                            
                            
                        //$(this).html($(this).html() +'<hr/>' +$(drag).html());
//                        alert(this.html()); //the droppable					
//                        alert(drag); //the dragganle				
                    },		
            fit: true,
            pointer: true,
            intersect: true
        }
    );
        
        
        $('#envoyer').Droppable(	
        {		
        accept : 'commande', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        jPrompt( 'Date de livraison (dd/mm/aaaa)',new Date() , 'Envoyer', function(envoi){
                            if(envoi){
                                
                            
                                var obj = $(drag).metadata({type:'attr',name:'data'});
                                $.post("envoyer.htm", {id: obj.id, envoi: envoi} ,  function(data)
                                                                                    {
                                                                                        //alert(data);
                                                                                        $('#listeCommande').replaceWith(data);
                                                                                        draggable();
                                                                                    }
                                );
                            }
                            else{
                                JAlert('Une date de livraison est obligatoire!', 'Envoyer')
                            }
                            
                            
                        });
                        
                            
                            
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
