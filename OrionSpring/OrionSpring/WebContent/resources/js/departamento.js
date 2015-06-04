$(document).ready(function() {
	listarDepartamento();
})

function listarDepartamento(){
	$.ajax({
        url: '/OrionSpring/departamento/findAllJSON',
        success: function (data) {
        	montarTabela(data);
        }
    });
}

function deleteDepartamento(id){
	$.ajax({
        url: '/OrionSpring/departamento/delete',
        data: {
        	idDepto: id
        },
        success: function (data) {
        	listarDepartamento();
        	exibirMensagemSucesso("Departamento Excluido com Sucesso!");
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

function preencherFormAlterarDepartamento(id){
	$.ajax({
        url: '/OrionSpring/departamento/findByCodeJSON',
        data: {
        	idDepto: id
        },
        success: function (data) {
        	var id = data.idDepto;
        	var nome = data.descDepto;
        	$("#idDepto").val(id);
        	$("#descDepto").val(nome);
        },
        error :function(){
        	exibirMensagemErro("Erro Interno!");
        	removerMensagem();
        },
        beforeSend: function(){
        	limparForm('departamento');
        },
        complete: function(){}
    });
}

function createUpdateDepartamento(){
	var id = $("#idDepto").val();
	var nome = $("#descDepto").val();
	var url = '/OrionSpring/departamento/create';
	
	if(id != undefined && id != "") url = '/OrionSpring/departamento/update';
	
	if(validarCampos('departamento')){
		$.ajax({
	        url: url,
	        data: {
	        	idDepto: id,
	        	descDepto: nome
	        },
	        success: function (data) {
	        	$("#create_update_departamento").modal('toggle');
	        	listarDepartamento();
	        	exibirMensagemSucesso("Departamento Salvo com Sucesso!");
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
	  	html += "<td>" + data[i].idDepto + "</td>";
	  	html += "<td>" + data[i].descDepto + "</td>";
	  	html += "<td>";
	  	html += "<button type='button' class='btn btn-default' href='#create_update_departamento' data-toggle='modal' onclick='preencherFormAlterarDepartamento(" + data[i].idDepto+ ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deleteDepartamento(" +data[i].idDepto+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='departamento'] tbody").empty().append(html);
  	$("[data-table='departamento']").slideDown(1000);
}

