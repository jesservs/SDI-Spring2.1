$(document).ready(function() {
	listarCategoria(1);
})

function listarCategoria(numPagina){
	$.ajax({
        url: '/OrionSpring/categoria/findAllJSON',
        data: {
        	pagina: numPagina,
        },
        success: function (data) {
        	setPagAtual(numPagina);
        	montarTabela(data);
        	obterNumeroElementos();
        }
    });
}

function carregarTipoPrioridades(){
	$.ajax({
        url: '/OrionSpring/categoria/findAllTipoAndPrioridadeJSON',
        success: function (data) {
        	montarTipos(data.tipo);
        	montarPrioridades(data.prioridade);
        }
    });
}

function obterNumeroElementos(){
	$.ajax({
        url: '/OrionSpring/categoria/getNumberElementos',
        success: function (data) {
        	criarPagination(data, 10, 'categoria', 'listarCategoria');
        }
    });
}

function montarTipos(data){
	var i = 0;
	var html = "";
	while(data[i] != undefined && data[i] != ""){
		html += '<option value='+data[i].idTipo+'>';
		html += data[i].descTipo;
		html += "</option>";
		i++;
	}
	$("[data-option='tipo']").html(html);
}

function montarPrioridades(data){
	var i = 0;
	var html = "";
	while(data[i] != undefined && data[i] != ""){
		html += '<option value='+data[i].idPrioridade+'>';
		html += data[i].descPrioridade;
		html += "</option>";
		i++;
	}
	$("[data-option='prioridade']").html(html);
}



function montarTabela(data){
	var html = ""
	var i = 0;	
	while(data[i] != undefined && data[i] != ""){
		html += "<tr>";
	  	html += "<td>" + data[i].idCategoria + "</td>";
	  	html += "<td>" + data[i].descCategoria + "</td>";
	  	html += "<td>" + data[i].tipo.descTipo + "</td>";
	  	html += "<td>" + data[i].prioridade.descPrioridade + "</td>";
	  	html += "<td>";
	  	html += "<button type='button' class='btn btn-default' href='#create_update_categoria' data-toggle='modal' onclick='preencherFormAlterarStatus(" + data[i].idCategoria + ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deleteCategoria(" +data[i].idCategoria+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='categoria'] tbody").empty().append(html);
  	$("[data-table='categoria']").slideDown(1000);
}

function createUpdateCategoria(){
	var id = $("#idCategoria").val();
	var desc = $("#descCategoria").val();
	var idTipo = $("[data-option='tipo']").val();
	var idPrioridade = $("[data-option='prioridade']").val();
	
	var url = '/OrionSpring/categoria/create';
	
	if(id != undefined && id != "") url = '/OrionSpring/categoria/update';
	
	if(validarCampos('categoria')){
		$.ajax({
	        url: url,
	        data: {
	        	idCategoria: id,
	        	descCategoria: desc,
	        	'tipo.idTipo': idTipo,
	        	'prioridade.idPrioridade': idPrioridade
	        },
	        success: function (data) {
	        	var pagAtual = $("[data-pagAtual]").val();
	        	$("#create_update_categoria").modal('toggle');
	        	listarCategoria(pagAtual);
	        	exibirMensagemSucesso("Categoria Salvo com Sucesso!");
	        	removerMensagem();
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

function deleteCategoria(id){
	$.ajax({
        url: '/OrionSpring/categoria/delete',
        data: {
        	idCategoria: id
        },
        success: function (data) {
        	var pagAtual = $("[data-pagAtual]").val();
        	listarCategoria(pagAtual);
        	exibirMensagemSucesso("Status Excluido com Sucesso!");
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
function preencherFormAlterarStatus(id){
	$.ajax({
        url: '/OrionSpring/status/findByCodeJSON',
        data: {
        	idStatus: id
        },
        success: function (data) {
        	var id = data.idStatus;
        	var nome = data.descStatus;
        	$("#idStatus").val(id);
        	$("#descStatus").val(nome);
        },
        error :function(){
        	exibirMensagemErro("Erro Interno!");
        	removerMensagem();
        },
        beforeSend: function(){
        	limparForm('status');
        },
        complete: function(){}
    });
}

*/
