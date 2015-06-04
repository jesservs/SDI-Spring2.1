$(document).ready(function() {
});

function listarEventosHistorico(idChamado){
	$.ajax({
        url: '/OrionSpring/historico/findAllJSON',
        data: {
        	id: idChamado,
        },
        success: function (data) {
        	$("#historicoChamado").modal("toggle");
        	montarTabelaHistorico(data);
        }
    });
}

function montarTabelaHistorico(data){
	var html = ""
	var i = 0;	
	while(data[i] != undefined && data[i] != ""){
		html += "<tr data-idHistorico='" + data[i].idHistorico + "'>";
		  	html += "<td>" + data[i].dtHrAbertura + "</td>";
		  	if(data[i].dtHrFechamento == null || data[i].dtHrFechamento == undefined || data[i].dtHrFechamento == "null"){
		  		html += "<td></td>";
		  	}else{
		  		html += "<td>" + data[i].dtHrFechamento + "</td>";
		  	}
		  	
		  	html += "<td>" + data[i].descChamado + "</td>";
		  	html += "<td data-idStatus='" + data[i].status.idStatus + "'>" + data[i].status.descStatus + "</td>";
		  	
		  	if(data[i].responsavel == null || data[i].responsavel == undefined){
		  		html += "<td data-idResponsavel='null'>Nenhum Responsável<br/></td>";
		  	}else{
		  		html += "<td data-idReponsavel='"+data[i].responsavel.idUsuario+"'>" + data[i].responsavel.nome + "<br/></td>";
		  	}
		  	
		  	//html += "<td data-idSolicitante='" + data[i].solicitante.idUsuario + "'>" + data[i].solicitante.nome + "</td>";

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
  	$("[data-table='historico'] tbody").empty().append(html);
	$("[data-table='historico'] tbody")
		.append($("<tr>",{
			class: 'info',
			id: 'ultimaLinha'
		})
	);
	////////////////////////////
	var ultimaLinha = "<td>" + data[0].chamado.dtHrAbertura + "</td>";
  	if(data[0].chamado.dtHrFechamento == null || data[0].chamado.dtHrFechamento == undefined){
  		ultimaLinha += "<td></td>";
  	}else{
  		ultimaLinha += "<td>" + data[0].chamado.dtHrFechamento + "</td>";
  	}
  	
  	ultimaLinha += "<td>" + data[0].chamado.descChamado + "</td>";
  	ultimaLinha += "<td data-idStatus='" + data[0].chamado.status.idStatus + "'>" + data[0].chamado.status.descStatus + "</td>";
  	
  	if(data[0].chamado.responsavel == null || data[0].chamado.responsavel == undefined){
  		ultimaLinha += "<td data-idResponsavel='null'>Nenhum Responsável<br/></td>";
  	}else{
  		ultimaLinha += "<td data-idReponsavel='"+data[0].chamado.responsavel.idUsuario+"'>" + data[0].chamado.responsavel.nome + "<br/></td>";
  	}
  	
  	if(data[0].chamado.atendente.nome == null || data[0].chamado.atendente.nome == undefined){
  		ultimaLinha += "<td data-idAtendente='null'>Nenhum Atendente</td>";
  	}else{
  		ultimaLinha += "<td data-idAtendente='" + data[0].chamado.atendente.idUsuario + "'>" + data[0].chamado.atendente.nome + "</td>";
  	}
  	ultimaLinha += "<td>" + data[0].chamado.categoria.descCategoria + "</td>";
  	ultimaLinha += "<td>" + data[0].chamado.novaPrioridade.descPrioridade + "</td>";
	///////////////////////////
	$("#ultimaLinha").html(ultimaLinha);
  	$("[data-table='historico']").slideDown(1000);
}

