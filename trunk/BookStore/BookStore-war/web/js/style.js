function arrondi(){
    $("div[id='corps']").css("display", "none");
    $("div[id='corps']").corner("round 10px");
    $("div[id='titre']").corner("round top 10px");
    $("div[id='contenu']").corner("round bottom 10px");
    $("div[id='boutonMenu']").corner("round 10px");
    $("div[id='bouton']").corner("round 10px");
}

$(document).ready(function(){

    $("div[id='javascript']").css("display", "none");
    
    arrondi();



    $("div[id='corps']").fadeIn(1000);


    $("input[type='sumbit']").click(function(event){
		event.preventDefault();
		linkLocation = this.href;
		$("div[id='contenu']").fadeOut(400, redirectPage);
	});
    

   $("a").click(function(event){
		event.preventDefault();
		linkLocation = this.href;
		$("div[id='corps']").fadeOut(400, redirectPage);
	});

	function redirectPage() {
		window.location = linkLocation;
	}

});
