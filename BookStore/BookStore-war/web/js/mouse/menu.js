$(document).ready(function(){

    $('#javascript').hide();

    $("div[id='boutonMenu']").mouseover(function(){
        
        $(this).attr('class', 'boutonMenuSurvole');
    });

    $("div[id='boutonMenu']").mouseout(function(){

        $(this).attr('class', 'boutonMenu');
    });

    $("div[id='bouton']").mouseover(function(){

        $(this).attr('class', 'boutonSurvole');
    });

    $("div[id='bouton']").mouseout(function(){

        $(this).attr('class', 'bouton');
    });


});






