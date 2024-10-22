$("#nav").slideDown("slow");
$("#nav").css({ "display": "flex",
                "width": "100%",
                "justify-content": "space-between",
                "background-color": "rgb(0, 0, 0)",
                "box-shadow":"0px 4px 3px rgba(0,0,0,.5)"});
$(document).ready(function(){
	var altura = $('#nav').offset().top;
	
	$(window).on('scroll', function(){
		if ( $(window).scrollTop() > altura ){
			$('#nav').addClass('nav-fixed');
		} else {
			$('#nav').removeClass('nav-fixed');
		}
	});
});