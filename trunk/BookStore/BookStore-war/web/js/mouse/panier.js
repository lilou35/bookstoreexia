$(document).ready(function(){
    
    $("tr").mouseover(function(){
        
        $(this).attr('class', 'ligneSurvoler');
    });

    $("tr").mouseout(function(){

        $(this).attr('class', 'ligneNonSurvoler');
    });

});


