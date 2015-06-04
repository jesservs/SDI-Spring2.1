$(document).ready(function() {
	listarPrioridade();
})

function listarPrioridade(){
	$.ajax({
        url: '/OrionSpring/prioridade/findAllJSON',
        success: function (data) {
        	montarTabela(data);
        }
    });
}

function deletePrioridade(id){
	$.ajax({
        url: '/OrionSpring/prioridade/delete',
        data: {
        	idPrioridade: id
        },
        success: function (data) {
        	listarPrioridade();
        	exibirMensagemSucesso("Prioridade Excluido com Sucesso!");
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

function preencherFormAlterarPrioridade(id){
	$.ajax({
        url: '/OrionSpring/prioridade/findByCodeJSON',
        data: {
        	idPrioridade: id
        },
        success: function (data) {
        	var id = data.idPrioridade;
        	var desc = data.descPrioridade;
        	var tempoResolucao = data.tempoResolucao;
        	$("#idPrioridade").val(id);
        	$("#descPrioridade").val(desc);
        	$("#tempoResolucao").val(tempoResolucao);
        },
        error :function(){
        	exibirMensagemErro("Erro Interno!");
        	removerMensagem();
        },
        beforeSend: function(){
        	limparForm('prioridade');
        },
        complete: function(){}
    });
}

function createUpdatePrioridade(){
	var id = $("#idPrioridade").val();
	var desc = $("#descPrioridade").val();
	var tempoResolucao = $("#tempoResolucao").val();
	var url = '/OrionSpring/prioridade/create';
	
	if(id != undefined && id != "") url = '/OrionSpring/prioridade/update';
	
	if(validarCampos('prioridade')){
		$.ajax({
	        url: url,
	        data: {
	        	idPrioridade: id,
	        	descPrioridade: desc,
	        	tempoResolucao: tempoResolucao
	        },
	        success: function (data) {
	        	$("#create_update_prioridade").modal('toggle');
	        	listarPrioridade();
	        	exibirMensagemSucesso("Prioridade Salvo com Sucesso!");
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

function montarTabela(data){
	var html = ""
	var i = 0;	
	while(data[i] != undefined && data[i] != ""){
		html += "<tr>";
	  	html += "<td>" + data[i].idPrioridade + "</td>";
	  	html += "<td>" + data[i].descPrioridade + "</td>";
	  	html += "<td>" + data[i].tempoResolucao + "</td>";
	  	html += "<td>";
	  	html += "<button type='button' class='btn btn-default' href='#create_update_prioridade' data-toggle='modal' onclick='preencherFormAlterarPrioridade(" + data[i].idPrioridade+ ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deletePrioridade(" +data[i].idPrioridade+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='prioridade'] tbody").empty().append(html);
  	$("[data-table='prioridade']").slideDown(1000);
}

