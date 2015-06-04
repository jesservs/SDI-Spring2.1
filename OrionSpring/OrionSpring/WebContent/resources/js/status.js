$(document).ready(function() {
	listarStatus();
})

function listarStatus(){
	$.ajax({
        url: '/OrionSpring/status/findAllJSON',
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
	  	html += "<td>" + data[i].idStatus + "</td>";
	  	html += "<td>" + data[i].descStatus + "</td>";
	  	html += "<td>";
	  	html += "<button type='button' class='btn btn-default' href='#create_update_status' data-toggle='modal' onclick='preencherFormAlterarStatus(" + data[i].idStatus + ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deleteStatus(" +data[i].idStatus+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='status'] tbody").empty().append(html);
  	$("[data-table='status']").slideDown(1000);
}

function createUpdateStatus(){
	var id = $("#idStatus").val();
	var nome = $("#descStatus").val();
	var url = '/OrionSpring/status/create';
	
	if(id != undefined && id != "") url = '/OrionSpring/status/update';
	
	if(validarCampos('status')){
		$.ajax({
	        url: url,
	        data: {
	        	idStatus: id,
	        	descStatus: nome
	        },
	        success: function (data) {
	        	$("#create_update_status").modal('toggle');
	        	listarStatus();
	        	exibirMensagemSucesso("Status Salvo com Sucesso!");
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

function deleteStatus(id){
	$.ajax({
        url: '/OrionSpring/status/delete',
        data: {
        	idStatus: id
        },
        success: function (data) {
        	listarStatus();
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


/*


*/
