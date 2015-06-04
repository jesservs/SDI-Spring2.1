$(document).ready(function() {
})


function setPagAtual(numPagina){
	$("[data-pagAtual]").val(numPagina);
}

function marcarPagAtual(){
	$("[data-pagination] li").each(function(){
		var li = $(this).text();
		var pagAtual = $("[data-pagAtual]").val();
		if(li == pagAtual) $(this).addClass("active");
	});
}

function exibirFiltro(filtro){
	$("[data-campos-filtro='"+filtro+"']").slideToggle();
}

function preencherSelectPerfil(data){
	var i = 0;	
	$("[data-select='perfil']").empty();
	$("[data-select='perfil']").append("<option value=''>Nenhum</option>");
	while(data[i] != undefined && data[i] != ""){
		
		$("[data-select='perfil']").append("<option value='" + data[i].idPerfil + "'>" + data[i].nomePerfil + "</option>");
		i++;
	}
}

function preencherSelectDepartamento(data){
	var i = 0;
	$("[data-select='departamento']").empty();
	$("[data-select='departamento']").append("<option value=''>Nenhum</option>");
	while(data[i] != undefined && data[i] != ""){
	  	$("[data-select='departamento']").append("<option value='" + data[i].idDepto + "'>" + data[i].descDepto + "</option>");
	  	i++;
	}
}

function criarPagination(numElementos, elementosPorPagina, ulDataPagination, metodoListar){
	var pagAtual = $("[data-pagAtual]").val();
	var totalPaginas = Math.ceil(numElementos/elementosPorPagina);
	var i = 1;
	var proximo = parseInt(pagAtual) + 1;
	var anterior = parseInt(pagAtual) - 1;
	
	if(pagAtual <= 1) anterior = 1; 
	if(pagAtual >= totalPaginas) proximo = totalPaginas;
	
	$("[data-pagination='"+ulDataPagination+"']").empty();
	$("[data-pagination='"+ulDataPagination+"']").append("<li><a data-pagination-link=" + anterior + " onclick='"+metodoListar+"("+anterior+");'>&laquo;</a></li>");
	while(i <= totalPaginas){
		$("[data-pagination='"+ulDataPagination+"']").append("<li><a data-pagination-link="+i+" onclick='"+metodoListar+"("+i+");'>"+i+"</a></li>");
		i++;
	}
	$("[data-pagination='"+ulDataPagination+"']").append("<li><a data-pagination-link=" + proximo + " onclick='"+metodoListar+"("+proximo+");'>&raquo;</a></li>");
	marcarPagAtual();
}

function validarCampos(dataForm){
	var input;
	var numCamposObrigatorios = $("[data-form='"+dataForm+"'] [data-required]").size();
	var numCamposObrigatorioPreenchidos = 0;
	$("[data-form='"+dataForm+"'] [data-required]").each(function(){
		input = $(this).val();
		if(input == "" || input == undefined ){
			$(this).css("border", "1px solid #A00");
		}else{
			$(this).removeAttr("style");
			numCamposObrigatorioPreenchidos++;
		}
	});
	if(numCamposObrigatorioPreenchidos == numCamposObrigatorios){
		return true;
	}else{
		exibirMensagemModalAlerta("Por favor preencha todos os campos obrigatórios!", dataForm);
		removerMensagem();
		return false;
	}
}

function limparForm(dataForm){
	$("[data-form='"+dataForm+"'] :input").each(function(){
		$(this).val("");
		$(this).attr("style", "");
	});
}

function exibirMensagemSucesso(mensagem){
	var div = $('<div>',{
	    class : 'alert alert-success',
	    role : 'alert',
	    'data-mensagem' : 'sucesso'
	});
	$(div).append(mensagem);
	$(".page-header").after(div);
}

function exibirMensagemErro(mensagem){
	var div = $('<div>',{
	    class : 'alert alert-danger',
	    role : 'alert',
	    'data-mensagem' : 'erro'
	});
	$(div).append(mensagem);
	$(".page-header").after(div);
}

function exibirMensagemAlerta(mensagem){
	var div = $('<div>',{
	    class : 'alert alert-info',
	    role : 'alert',
	    'data-mensagem' : 'alerta'
	});
	$(div).append(mensagem);
	$(".page-header").after(div);
}

function exibirMensagemModalAlerta(mensagem, dataForm){
	var div = $('<div>',{
	    class : 'alert alert-info',
	    role : 'alert',
	    'data-mensagem' : 'alerta'
	});
	$(div).append(mensagem);
	$("[data-form='"+dataForm+"']").prepend(div);
}

function removerMensagem(){
	setTimeout(function() {
		$("[data-mensagem]").slideUp(500, function() { 
			$(this).remove(); 
		});
	}, 3000);
}


