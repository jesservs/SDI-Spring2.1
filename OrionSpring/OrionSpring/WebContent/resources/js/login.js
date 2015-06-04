$(document).ready(function() {
	$("#login").modal("toggle");
	pressionarEnter();
});

function pressionarEnter(){
	$("[data-input='senha']").keypress(function(e) {
	 if(e.which == 13) {
		 	logar();
	    }
	});	
}

function logar(){
	var login = $("[data-input='login']").val();
	var senha = $("[data-input='senha']").val();
	var url = '/OrionSpring/logar';
	
	if(validarCampos('logar')){
		$.ajax({
	        url: url,
	        data: {
	        	login: login,
	        	senha: senha
	        },
	        success: function (data) {
	        	window.location = "/OrionSpring/chamado/listar";
	        },
	        error :function(){
	        	exibirMensagemErro("Erro Interno!");
	        	removerMensagem();
	        },
	        beforeSend: function(){},
	        complete: function(){}
	    });
	};
}
