$(document).ready(function() {
	listarConhecimento();
})

function listarConhecimento(){
	$.ajax({
        url: '/OrionSpring/conhecimento/findAllJSON',
        success: function (data) {
        	montarTabela(data);
        }
    });
}

function listarConhecimentoComFiltro(){
	if(validarCampos('conhecimentoFiltro')){
		var tipo = $("[data-select-filtro='tipo']").val();
		var categoria = $("[data-select-filtro='categoria']").val();
		$.ajax({
	        url: '/OrionSpring/conhecimento/findAllJSONComFiltro',
	        data: {
	        	'categoria.idCategoria' : categoria,
	        	'categoria.tipo.idTipo' : tipo
	        },
	        success: function (data) {
	        	montarTabela(data);
	        }
	    });
	}
}


function listarCategoriaFiltro(codTipo){
	$("[data-select-filtro='categoria']").removeAttr("readonly");
	$.ajax({
        url: '/OrionSpring/categoria/findAllByTipoJSON',
        data: {
        	codTipo: codTipo
        },
        success: function (data) {
        	montarSelectCategoriaFiltro(data);
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

function setandoCategoriaOnChangeTipoFiltro(){
	var idTipo = "";
	$( "[data-select-filtro='tipo']" ).change(function () {
	    $( "[data-select-filtro='tipo'] option:selected" ).each(function() {
	    	idTipo = $("[data-select-filtro='tipo']").val();
	    });
	  }).change(function(){
		  listarCategoriaFiltro(idTipo);
	  });
};


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

function listarTipo(){
	$.ajax({
        url: '/OrionSpring/tipo/findAllJSON',
        success: function (data) {
        	montarSelectTipo(data);
        	setandoCategoriaOnChangeTipo();
        }
    });
}

function listarTipoFiltro(){
	$.ajax({
        url: '/OrionSpring/tipo/findAllJSON',
        success: function (data) {
        	montarSelectTipoFiltro(data);
        	setandoCategoriaOnChangeTipoFiltro();
        }
    });
}




function montarTabela(data){
	var html = ""
	var i = 0;	
	while(data[i] != undefined && data[i] != ""){
		html += "<tr>";
	  	html += "<td>" + data[i].idConhecimento + "</td>";
	  	html += "<td>" + data[i].dtHrUltAlteracao + "</td>";
	  	html += "<td>" + data[i].descConhecimento + "</td>";
	  	html += "<td data-idReponsavel='" + data[i].responsavel.idUsuario + "'>" + data[i].responsavel.nome + "</td>";
	  	html += "<td data-idCategoria='" + data[i].categoria.idCategoria + "'>" + data[i].categoria.descCategoria + "</td>";
	  	html += "<td data-idTipo='" + data[i].categoria.tipo.idTipo + "'>" + data[i].categoria.tipo.descTipo + "</td>";
	  	html += "<td>";
	  	//html += "<button type='button' class='btn btn-default' href='#create_update_tipo' data-toggle='modal' onclick='limparForm('conhecimento');preencherFormAlterarConhecimento(" + data[i].idConhecimento+ ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deleteConhecimento(" +data[i].idConhecimento+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='conhecimento'] tbody").empty().append(html);
  	$("[data-table='conhecimento']").slideDown(1000);
}

function montarSelectTipoFiltro(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option value='" + data[i].idTipo + "'>";
	  	html += data[i].descTipo;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select-filtro='tipo']").html(html);
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

function setandoPrioridadeOnChangeCategoria(){
	$( "[data-select='categoria']" ).change(function () {
	    var prioridade = "";
	    $( "[data-select='categoria'] option:selected" ).each(function() {
	    	prioridade += $( this ).data("prioridade");
	    });
	    $("[data-input='prioridade']").val(prioridade);
	    
	  }).change();
};

function montarSelectCategoriaFiltro(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option data-idPrioridade=" + data[i].prioridade.idPrioridade + " data-prioridade='" + data[i].prioridade.descPrioridade + "' value=" + data[i].idCategoria + ">";
	  	html += data[i].descCategoria;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select-filtro='categoria']").html(html);
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

function createUpdateConhecimento(){
	//var id = $("#idTipo").val();
	var descricao = $("#descricao").val();
	var categoria = $("[data-select='categoria']").val();
	
	var url = '/OrionSpring/conhecimento/create';
	
	//if(id != undefined && id != "") url = '/OrionSpring/tipo/update';
	
	if(validarCampos('conhecimento')){
		$.ajax({
	        url: url,
	        data: {
	        	descConhecimento: descricao,
	        	'categoria.idCategoria': categoria
	        },
	        success: function (data) {
	        	$("#create_update_conhecimento").modal('toggle');
	        	listarConhecimento();
	        	exibirMensagemSucesso("Base de Conhecimento Salva com Sucesso!");
	        	removerMensagem();
	        },
	        error :function(){
	        	exibirMensagemErro("Erro Interno!");
	        	removerMensagem();
	        },
	        beforeSend: function(){
	        	limparForm('conhecimento');
	        },
	        complete: function(){}
	    });
	};
}

function deleteConhecimento(id){
	$.ajax({
        url: '/OrionSpring/conhecimento/delete',
        data: {
        	idConhecimento: id
        },
        success: function (data) {
        	listarConhecimento();
        	exibirMensagemSucesso("Conhecimento Excluido com Sucesso!");
        	removerMensagem();
        },
        error :function(){
        	exibirMensagemErro("Erro Interno!");
        	removerMensagem();
        },
        beforeSend: function(){},
        complete: function(){}
    });
}

/*
function preencherFormAlterarTipo(id){
	$.ajax({
        url: '/OrionSpring/tipo/findByCodeJSON',
        data: {
        	idTipo: id
        },
        success: function (data) {
        	var idTipo = data.idTipo;
        	var descTipo = data.descTipo;
        	$("#idTipo").val(idTipo);
        	$("#descTipo").val(descTipo);
        },
        error :function(){
        	exibirMensagemErro("Erro Interno!");
        	removerMensagem();
        },
        beforeSend: function(){
        	limparForm('tipo');
        },
        complete: function(){}
    });
}
*/