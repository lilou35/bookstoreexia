function cliquable(){
    $("#sumbit").click(function(){
        //alert("action");
        var obj = $(this).metadata({type:'attr',name:'data'});
        $.post("ajoutAuteur.htm", {nomAuteur: $("#nom").val(),prenomAuteur: $("#prenom").val() ,idLivre: obj.idLivre} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $('#contenu').html(data);
                                                                                draggable();
                                                                                dropable();
                                                                                cliquable();
                                                                            }
                        );
    });
    
    $("#terminer").click(function(){
        
    });
    
}




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
   dropable();
   cliquable();
    
});

function dropable(){
    
    $('#ajouter').Droppable(	
        {		
        accept : 'ajoutable', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.get("ajoutAuteurALivre.htm", {idAuteur: obj.idAuteur,idLivre: obj.idLivre} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $('#contenu').html(data);
                                                                                draggable();
                                                                                dropable();
                                                                                cliquable();
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
        
        
    $('#retirer').Droppable(	
        {		
        accept : 'retirable', 		
        activeclass: 'panierOut',
        hoverclass: 'panierIn',
        ondrop:	function (drag) 				
                    {	
                        
                        var obj = $(drag).metadata({type:'attr',name:'data'});
                        $.get("retirerAuteurALivre.htm", {idAuteur: obj.idAuteur,idLivre: obj.idLivre} ,  function(data)
                                                                            {
                                                                                //alert(data);
                                                                                $('#contenu').html(data);
                                                                                draggable();
                                                                                dropable();
                                                                                cliquable();
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
        
        
        
    
        
        
    }
