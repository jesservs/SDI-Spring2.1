$(document).ready(function() {
	listarPerfil();
})

function listarPerfil(){
	$.ajax({
        url: '/OrionSpring/perfil/findAllJSON',
        success: function (data) {
        	montarTabela(data);
        }
    });
}

function deletePerfil(id){
	$.ajax({
        url: '/OrionSpring/perfil/delete',
        data: {
        	idPerfil: id
        },
        success: function (data) {
        	listarPerfil();
        	exibirMensagemSucesso("Produto Excluido com Sucesso!");
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

function preencherFormAlterarPerfil(id){
	$.ajax({
        url: '/OrionSpring/perfil/findByCodeJSON',
        data: {
        	idPerfil: id
        },
        success: function (data) {
        	var id = data.idPerfil;
        	var nome = data.nomePerfil;
        	$("#idPerfil").val(id);
        	$("#nomePerfil").val(nome);
        },
        error :function(){
        	exibirMensagemErro("Erro Interno!");
        	removerMensagem();
        },
        beforeSend: function(){
        	limparForm('perfil');
        },
        complete: function(){}
    });
}

function createUpdatePerfil(){
	var id = $("#idPerfil").val();
	var nome = $("#nomePerfil").val();
	var url = '/OrionSpring/perfil/create';
	
	if(id != undefined && id != "") url = '/OrionSpring/perfil/update';
	
	if(validarCampos('perfil')){
		$.ajax({
	        url: url,
	        data: {
	        	idPerfil: id,
	        	nomePerfil: nome
	        },
	        success: function (data) {
	        	$("#create_update_perfil").modal('toggle');
	        	listarPerfil();
	        	exibirMensagemSucesso("Produto Salvo com Sucesso!");
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
	  	html += "<td>" + data[i].idPerfil + "</td>";
	  	html += "<td>" + data[i].nomePerfil + "</td>";
	  	html += "<td>";
	  	html += "<button type='button' class='btn btn-default' href='#create_update_perfil' data-toggle='modal' onclick='preencherFormAlterarPerfil(" + data[i].idPerfil+ ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deletePerfil(" +data[i].idPerfil+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='perfil'] tbody").empty().append(html);
  	$("[data-table='perfil']").slideDown(1000);
}

