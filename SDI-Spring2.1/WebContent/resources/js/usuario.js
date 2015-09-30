$(document).ready(function() {
	listarUsuario();
})

function deleteUsuario(id){
	$.ajax({
        url: '/SDI-Spring2.1/usuario/delete',
        data: {
        	idUsuario: id
        },
        success: function (data) {
        	listarUsuario();
        	exibirMensagemSucesso("Usuario Excluido com Sucesso!");
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

function preencherFormAlterarUsuario(id){
	$.ajax({
        url: '/SDI-Spring2.1/usuario/findByCodeJSON',
        data: {
        	idUsuario: id
        },
        success: function (data) {
        	$("#idUsuario").val(data.idUsuario);
        	$("#nomeUsuario").val(data.nome);
        	$("#cpf").val(data.cpf);
        	$("#email").val(data.email);
        	$("#login").val(data.login);
        	$("#senha").val("");
        	$("#telefone").val(data.telefone);
        	//$("[data-select='departamento']").val();
        	//$("[data-select='perfil']").val();
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

function createUpdateUsuario(){
	var id = $("#idUsuario").val();
	var nome = $("#nomeUsuario").val();
	var cpf = $("#cpf").val();
	var email = $("#email").val();
	var login = $("#login").val();
	var senha = $("#senha").val();
	var telefone = $("#telefone").val();
	var departamento = $("[data-select='departamento']").val();
	var perfil = $("[data-select='perfil']").val();
	
	var url = '/SDI-Spring2.1/usuario/create';
	
	if(id != undefined && id != "") url = '/SDI-Spring2.1/usuario/update';
	
	if(validarCampos('usuario')){
		$.ajax({
	        url: url,
	        data: {
	        	idUsuario: id,
	        	login: login,
	        	senha: senha,
	        	nome: nome,
	        	cpf: cpf,
	        	telefone: telefone,
	        	email: email,
	        	'perfil.idPerfil': perfil,
	        	'departamento.idDepto':departamento
	        },
	        success: function (data) {
	        	//var pagAtual = $("[data-pagAtual]").val();
	        	$("#create_update_usuario").modal('toggle');
	        	//listarUsuario(pagAtual);
	        	listarUsuario();
	        	exibirMensagemSucesso("Usuario Salvo com Sucesso!");
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

function listarUsuario(){
	$.ajax({
        url: '/SDI-Spring2.1/usuario/findAllJSON',
        success: function (data) {
        	montarTabela(data);
        }
    });
}

function listarDepartamento(){
	$.ajax({
        url: '/SDI-Spring2.1/departamento/findAllJSON',
        success: function (data) {
        	preencherSelectDepartamento(data);
        }
    });
}

function listarPerfil(){
	$.ajax({
        url: '/SDI-Spring2.1/perfil/findAllJSON',
        success: function (data) {
        	preencherSelectPerfil(data);
        }
    });
}

function montarTabela(data){
	var html = ""
	var i = 0;	
	while(data[i] != undefined && data[i] != ""){
		html += "<tr>";
	  	html += "<td>" + data[i].idUsuario + "</td>";
	  	html += "<td>" + data[i].nome + "</td>";
	  	html += "<td>" + data[i].cpf + "</td>";
	  	html += "<td>" + data[i].telefone + "</td>";
	  	html += "<td>" + data[i].departamento.descDepto + "</td>";
	  	html += "<td>" + data[i].perfil.nomePerfil + "</td>";
	  	html += "<td>";
	  	html += "<button type='button' class='btn btn-default' href='#create_update_usuario' data-toggle='modal' onclick='listarDepartamento();listarPerfil();preencherFormAlterarUsuario(" + data[i].idUsuario+ ")'>Alterar</button>";
	  	html += " <button type='button' class='btn btn-danger' onclick='deleteUsuario(" +data[i].idUsuario+ ")'>Excluir</button>";
	  	html += "</td>";
	  	html += "</tr>";
	  	i++;
	}
  	$("[data-table='usuario'] tbody").empty().append(html);
  	$("[data-table='usuario']").slideDown(1000);
}

/*
function deletePerfil(id){
	$.ajax({
        url: '/SDI-Spring2.1/perfil/delete',
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
        url: '/SDI-Spring2.1/perfil/findByCodeJSON',
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
	var url = '/SDI-Spring2.1/perfil/create';
	
	if(id != undefined && id != "") url = '/SDI-Spring2.1/perfil/update';
	
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

*/
