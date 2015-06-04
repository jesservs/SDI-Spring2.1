$(document).ready(function() {
	setandoCPF();
	listarTodosChamados(1);
});

function abrirModalTecnicos(){
	$("[data-btn-tecnicos]").click(function(){
		listarTecnicos();
		listarStatus();
		$("#modalResponsavel").modal('toggle');
		$("#idChamado").val($(this).parent().parent().data("idchamado"));
	});
}

function carregarFiltro(){
	listarPrioridade();
	listarStatusFiltro();
}

function abrirModalQualidade(){
	$("[data-btn-qualidade]").click(function(){
		limparForm('qualidade');
		$("#modalAvaliarChamado").modal("toggle");
		$("#idChamadoQualidade").val($(this).parent().parent().data("idchamado"));
	});
}

function abrirModalStatus(){
	$("[data-btn-status]").click(function(){
		limparForm('alterarStatus');
		listarStatusModalAlterar();
		$("#modalStatus").modal("toggle");
		$("#idChamadoStatus").val($(this).parent().parent().data("idchamado"));
	});
}

function createQualidade(){
	var id = $("#idChamadoQualidade").val();
	var nota = $("#avaliacaoQualidade").val();
	var descricao = $("#descQualidade").val();
	var url = '/OrionSpring/qualidade/create';
	
	if(validarCampos('qualidade')){
		$.ajax({
	        url: url,
	        data: {
	        	'chamado.idChamado': id,
	        	descQualidade: descricao,
	        	nota: nota
	        },
	        success: function (data) {
	        	var pagAtual = $("[data-pagatual]").val();
	        	$("#modalAvaliarChamado").modal('toggle');
	        	listarTodosChamados(pagAtual);
	        	exibirMensagemSucesso("Sua Avaliação foi salva e será útil para a Central de Qualidade!");
	        	removerMensagem();
	        },
	        error :function(){
	        	exibirMensagemErro("Erro Interno!");
	        	removerMensagem();
	        },
	        beforeSend: function(){
	        	limparForm('qualidade');
	        },
	        complete: function(){}
	    });
	};
}



function listarStatusModalAlterar(){
	$.ajax({
        url: '/OrionSpring/status/findAllJSON',
        success: function (data) {
        	montarSelectStatusModalAlterar(data);
        }
    });
}



function listarStatus(){
	$.ajax({
        url: '/OrionSpring/status/findAllJSON',
        success: function (data) {
        	montarSelectStatus(data);
        }
    });
}

function listarStatusFiltro(){
	$.ajax({
        url: '/OrionSpring/status/findAllJSON',
        success: function (data) {
        	montarSelectStatusFiltro(data);
        }
    });
}

function setTecnico(){
	var idTecnico = $("[data-select='tecnicos']").val();
	var idChamado = $("#idChamado").val();
	var obsChamado = $("#obsTecnico").val();
	var idStatus = $("[data-select='status']").val();
	var url = '/OrionSpring/chamado/setResponsavel';
	
	if(validarCampos('mudarReponsavel')){
		$.ajax({
	        url: url,
	        data: {
	        	idChamado: idChamado,
	        	descChamado: obsChamado,
	        	'status.idStatus': idStatus,
	        	'responsavel.idUsuario': idTecnico
	        },
	        success: function (data) {
	        	var pagAtual = $("[data-pagAtual]").val();
	        	$("#modalResponsavel").modal('toggle');
	        	listarTodosChamados(pagAtual);
	        	exibirMensagemSucesso("Chamado delegado com Sucesso!");
	        	removerMensagem();
	        },
	        error :function(){
	        	exibirMensagemErro("Erro Interno!");
	        	removerMensagem();
	        },
	        beforeSend: function(){},
	        complete: function(){
	        	$("[data-campos-filtro]").hide();
	        }
	    });
	}
}

function setStatus(){
	var idChamado = $("#idChamadoStatus").val();
	var obsChamado = $("#obsStatus").val();
	var idStatus = $("[data-select-novoStatus='status']").val();
	var url = '/OrionSpring/chamado/setNovoStatus';
	
	$.ajax({
        url: url,
        data: {
        	idChamado: idChamado,
        	descChamado: obsChamado,
        	'status.idStatus': idStatus,
        },
        success: function (data) {
        	var pagAtual = $("[data-pagAtual]").val();
        	$("#modalStatus").modal('toggle');
        	listarTodosChamados(pagAtual);
        	exibirMensagemSucesso("Chamado alterado com Sucesso!");
        	removerMensagem();
        },
        error :function(){
        	exibirMensagemErro("Erro Interno!");
        	removerMensagem();
        },
        beforeSend: function(){},
        complete: function(){
        	$("[data-campos-filtro]").hide();
        }
    });
}

function create(){
	var idUsuario = $("[data-select='usuario']").val();
	var descricao = $("#descricao").val();
	var idCategoria = $("[data-select='categoria']").val();
	var url = '/OrionSpring/chamado/create';
	
	if(validarCampos('chamado')){
		$.ajax({
	        url: url,
	        data: {
	        	descChamado: descricao,
	        	'solicitante.idUsuario': idUsuario,
	        	'categoria.idCategoria': idCategoria
	        },
	        success: function (data) {
	        	$("#create_chamado").modal('toggle');
	        	//alert(data.idChamado + ": " + data.dtHrAbertura.getTime + ": " + data.dtHrAbertura.day);
	        	exibirMensagemSucesso("Chamado Salvo com Sucesso!");
	        	removerMensagem();
	        },
	        error :function(){
	        	exibirMensagemErro("Erro Interno!");
	        	removerMensagem();
	        },
	        beforeSend: function(){},
	        complete: function(){
	        	var pagAtual = $("[data-pagAtual]").val();
	        	listarTodosChamados(pagAtual);
	        }
	    });
	};
}

function listarPrioridade(){
	$.ajax({
        url: '/OrionSpring/prioridade/findAllJSON',
        success: function (data) {
        	montarSelectPrioridadeFiltro(data);
        }
    });
}

function listarTodosChamados(numPagina){
	$.ajax({
        url: '/OrionSpring/chamado/findAll',
        data: {
        	pagina: numPagina,
        },
        success: function (data) {
        	setPagAtual(numPagina);
        	montarTabelaChamado(data);
        	setPrioridades();
        	obterNumeroElementos();
        	abrirModalTecnicos();
        	abrirModalStatus();
        	abrirModalQualidade();
        }
    });
}

function listarTodosChamadosFiltro(numPagina){
	var idStatus = $("[data-select-filtro='status']").val();
	var idPrioridade = $("[data-select-filtro='prioridade']").val();
	$.ajax({
        url: '/OrionSpring/chamado/findAllComFiltro',
        data: {
        	pagina: numPagina,
        	'status.idStatus': idStatus,
        	'categoria.prioridade.idPrioridade': idPrioridade
        },
        success: function (data) {
        	setPagAtual(numPagina);
        	montarTabelaChamado(data);
        	setPrioridades();
        	obterNumeroElementosComFiltro();
        	abrirModalTecnicos();
        	abrirModalStatus();
        	abrirModalQualidade();
        }
    });
}

function obterNumeroElementosComFiltro(){
	var idStatus = $("[data-select-filtro='status']").val();
	var idPrioridade = $("[data-select-filtro='prioridade']").val();
	$.ajax({
        url: '/OrionSpring/chamado/getNumberElementosComFiltro',
        data: {
        	'status.idStatus': idStatus,
        	'categoria.prioridade.idPrioridade': idPrioridade
        },
        success: function (data) {
        	if(data > 10) {
        		$("[data-pagination]").show();
        		criarPagination(data, 10, 'chamado', 'listarTodosChamadosFiltro');
        	}else{
        		$("[data-pagination]").hide();
        	}
        		
        }
    });
}

function obterNumeroElementos(){
	$.ajax({
        url: '/OrionSpring/chamado/getNumberElementos',
        success: function (data) {
        	if(data > 10) 
        		criarPagination(data, 10, 'chamado', 'listarTodosChamados');
        }
    });
}

function montarTabelaChamado(data){
	var html = ""
	var i = 0;	
	while(data[i] != undefined && data[i] != ""){
		html += "<tr data-idChamado='" + data[i].idChamado + "'>";
		  	html += "<td><a href='javascript: listarEventosHistorico("+data[i].idChamado+");'>" + data[i].idChamado + "</a></td>";
		  	html += "<td>" + data[i].dtHrAbertura + "</td>";
		  	if(data[i].dtHrFechamento == null || data[i].dtHrFechamento == undefined || data[i].dtHrFechamento == "null"){
		  		html += "<td></td>";
		  	}else{
		  		html += "<td>" + data[i].dtHrFechamento + "</td>";
		  	}
		  	html += "<td>" + data[i].descChamado + "</td>";
		  	html += "<td data-idStatus='" + data[i].status.idStatus + "'>" + data[i].status.descStatus;
		  	if(		(data[i].responsavel != null) && (data[i].responsavel != undefined) && (data[i].status.idStatus != 6)		)
		  	{
		  		html += "<br/><button type='button' data-btn-status class='btn btn-default btn-xs'>Alterar</button>";
		  	}
		  	
		  	if(  (data[i].atendente != null && data[i].atendente != undefined) && ((data[i].responsavel == null) || (data[i].responsavel == undefined)) && (data[i].status.idStatus != 6)){
		  		html += "<br/><button type='button' data-btn-status='atendente' class='btn btn-default btn-xs'>Alterar</button>";
		  	}
		  			  	
		 	if((data[i].status.idStatus == 6) && (		(data[i].qualidade == null) || (data[i].qualidade == undefined) 
		 			|| (data[i].qualidade.idQualidade == undefined) || (data[i].qualidade.idQualidade == null)		)){
		  		html += "<br/><button type='button' data-btn-qualidade class='btn btn-primary btn-xs'>AVALIAR</button>";
		  	}
		  	html += "</td>";
		  	if((data[i].responsavel == null || data[i].responsavel == undefined)){
		  		html += "<td data-idResponsavel='null'>Nenhum Responsável<br/>";
		  		if(data[i].status.idStatus != 6)
		  			html += "<button type='button' data-btn-tecnicos class='btn btn-default btn-xs'>Delegar</button>"; 
		  		html += "</td>";
		  	}else{
		  		html += "<td data-idReponsavel='"+data[i].responsavel.idUsuario+"'>" + data[i].responsavel.nome;
		  		if(data[i].status.idStatus != 6)
		  			html += "<br/><button type='button' data-btn-tecnicos class='btn btn-default btn-xs'>Delegar</button>";
		  		html += "</td>";
		  	}
		  	html += "<td data-idSolicitante='" + data[i].solicitante.idUsuario + "'>" + data[i].solicitante.nome + "</td>";

		  	if(data[i].atendente == null || data[i].atendente == undefined){
		  		html += "<td data-idAtendente='null'>Nenhum Atendente</td>";
		  	}else{
		  		html += "<td data-idAtendente='" + data[i].atendente.idUsuario + "'>" + data[i].atendente.nome + "</td>";
		  	}
		  	
		  	html += "<td>" + data[i].categoria.descCategoria + "</td>";
		  	html += "<td>" + data[i].novaPrioridade.descPrioridade + "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='chamado'] tbody").empty().append(html);
  	
  	//adicionando estilo para os chamados fechados
  	$("[data-idstatus='6']").parent().addClass("active");
  //adicionando estilo para os chamados novos
  	$("[data-idstatus='1']").parent().addClass("success");
    //adicionando estilo para os chamados pausados
  	$("[data-idstatus='4']").parent().addClass("warning");
  	$("[data-table='chamado']").slideDown(1000);
}

function listarTipo(){
	$.ajax({
        url: '/OrionSpring/tipo/findAllJSON',
        success: function (data) {
        	montarSelectTipo(data);
        	setandoCategoriaOnChangeTipo();
        }
    });
}

function listarUsuario(){
	$.ajax({
        url: '/OrionSpring/usuario/findAllByPerfilJSON',
        success: function (data) {
        	montarSelectUsuario(data);
        }
    });
}

function listarTecnicos(){
	$.ajax({
        url: '/OrionSpring/usuario/findTecnicosJSON',
        success: function (data) {
        	montarSelectTecnicos(data);
        }
    });
}

function listarCategoria(codTipo){
	$("[data-select='categoria']").removeAttr("readonly");
	$.ajax({
        url: '/OrionSpring/categoria/findAllByTipoJSON',
        data: {
        	codTipo: codTipo
        },
        success: function (data) {
        	montarSelectCategoria(data);
        }
    });
}

function montarSelectStatusModalAlterar(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option value=" + data[i].idStatus + ">";
	  	html += data[i].descStatus;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select-novoStatus='status']").html(html);
}

function montarSelectStatus(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option value=" + data[i].idStatus + ">";
	  	html += data[i].descStatus;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select='status']").html(html);
}

function montarSelectPrioridadeFiltro(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option value=" + data[i].idPrioridade + ">";
	  	html += data[i].descPrioridade;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select-filtro='prioridade']").html(html);
}

function montarSelectStatusFiltro(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option value=" + data[i].idStatus + ">";
	  	html += data[i].descStatus;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select-filtro='status']").html(html);
}

function montarSelectStatus(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option value=" + data[i].idStatus + ">";
	  	html += data[i].descStatus;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select='status']").html(html);
}


function montarSelectCategoria(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option data-idPrioridade=" + data[i].prioridade.idPrioridade + " data-prioridade='" + data[i].prioridade.descPrioridade + "' value=" + data[i].idCategoria + ">";
	  	html += data[i].descCategoria;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select='categoria']").html(html);
}

function montarSelectUsuario(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option data-cpf='" + data[i].cpf + "' value=" + data[i].idUsuario + ">";
	  	html += data[i].nome;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select='usuario']").html(html);
}

function montarSelectTecnicos(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option data-cpf='" + data[i].cpf + "' value=" + data[i].idUsuario + ">";
	  	html += data[i].nome;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select='tecnicos']").html(html);
}


function montarSelectTipo(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option value='" + data[i].idTipo + "'>";
	  	html += data[i].descTipo;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select='tipo']").html(html);
}

function setandoCPF(){
	$( "[data-select='usuario']" ).change(function () {
	    var cpf = "";
	    $( "[data-select='usuario'] option:selected" ).each(function() {
	      cpf += $( this ).data("cpf");
	    });
	    $("[data-input='cpf']").val(cpf);
	  }).change(function(){
		  listarTipo();
		  setandoCategoriaOnChangeTipo();
	  });
}

function setandoCategoriaOnChangeTipo(){
	var idTipo = "";
	$( "[data-select='tipo']" ).change(function () {
	    $( "[data-select='tipo'] option:selected" ).each(function() {
	    	idTipo = $("[data-select='tipo']").val();
	    });
	  }).change(function(){
		  listarCategoria(idTipo);
		  setandoPrioridadeOnChangeCategoria();
	  });
};

function setandoPrioridadeOnChangeCategoria(){
	$( "[data-select='categoria']" ).change(function () {
	    var prioridade = "";
	    $( "[data-select='categoria'] option:selected" ).each(function() {
	    	prioridade += $( this ).data("prioridade");
	    });
	    $("[data-input='prioridade']").val(prioridade);
	    
	  }).change();
};


