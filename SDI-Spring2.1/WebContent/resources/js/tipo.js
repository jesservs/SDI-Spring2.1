$(document).ready(function() {
	listarTipo();
})

function listarTipo(){
	$.ajax({
        url: '/SDI-Spring2.1/tipo/findAllJSON',
        success: function (data) {
        	montarTabela(data);
        }
    });
}

function montarTabela(data){
	var html = ""
	var i = 0;	
	while(data[i] != undefined && data[i] != ""){
		html += "<tr>";
	  	html += "<td>" + data[i].idTipo + "</td>";
	  	html += "<td>" + data[i].descTipo + "</td>";
	  	html += "<td>";
	  	html += "<button type='button' class='btn btn-default' href='#create_update_tipo' data-toggle='modal' onclick='limparForm();preencherFormAlterarTipo(" + data[i].idTipo+ ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deleteTipo(" +data[i].idTipo+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='tipo'] tbody").empty().append(html);
  	$("[data-table='tipo']").slideDown(1000);
}

function createUpdateTipo(){
	var id = $("#idTipo").val();
	var descricao = $("#descTipo").val();
	var url = '/SDI-Spring2.1/tipo/create';
	
	if(id != undefined && id != "") url = '/SDI-Spring2.1/tipo/update';
	
	if(validarCampos('tipo')){
		$.ajax({
	        url: url,
	        data: {
	        	idTipo: id,
	        	descTipo: descricao
	        },
	        success: function (data) {
	        	$("#create_update_tipo").modal('toggle');
	        	listarTipo();
	        	exibirMensagemSucesso("Tipo Salvo com Sucesso!");
	        	removerMensagem();
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
	};
}



function deleteTipo(id){
	$.ajax({
        url: '/SDI-Spring2.1/tipo/delete',
        data: {
        	idTipo: id
        },
        success: function (data) {
        	listarTipo();
        	exibirMensagemSucesso("Tipo Excluido com Sucesso!");
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

function preencherFormAlterarTipo(id){
	$.ajax({
        url: '/SDI-Spring2.1/tipo/findByCodeJSON',
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
