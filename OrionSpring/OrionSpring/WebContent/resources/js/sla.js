$(document).ready(function() {
	//var perfil = ${usuarioLogado.perfil.idPerfil};
	verificarGrafico();
	verificarGraficoPorTecnico();
	listarTecnicos();
	listarAtendentes();
	gerandoGraficoOnChangeTecnico();
	gerandoGraficoOnChangeAtendente();
})

function gerandoGraficoOnChangeTecnico(){
	var idTipo = "";
	$( "[data-select='tecnicos']" ).change(function () {
	    $( "[data-select='tecnicos'] option:selected" ).each(function() {
	    	id = $("[data-select='tecnicos']").val();
	    });
	  }).change(function(){
		  verificarGraficoAdministrador(id);
		  var nomeTecnico =  $("[data-select='tecnicos'] :selected").text();
	  	  $("[data-mensagem-tecnico]").remove();
		  $("#graficoTecnico").before("<h2 data-mensagem-tecnico>Gráfico de Desempenho do Técnico:"+nomeTecnico+" </h2>");
	  });
};

function gerandoGraficoOnChangeAtendente(){
	var idTipo = "";
	$( "[data-select='atendentes']" ).change(function () {
	    $( "[data-select='atendentes'] option:selected" ).each(function() {
	    	id = $("[data-select='atendentes']").val();
	    });
	  }).change(function(){
		  verificarGraficoAdministrador(id);
		  var nomeTecnico =  $("[data-select='atendentes'] :selected").text();
	  	  $("[data-mensagem-tecnico]").remove();
		  $("#graficoTecnico").before("<h2 data-mensagem-tecnico>Gráfico de Desempenho do Técnico:"+nomeTecnico+" </h2>");
	  });
};


function verificarGrafico(){
	$.ajax({
        url: '/OrionSpring/sla/carregarGraficoSLA',
        success: function (data) {
        	carregarGrafico(data, "graficoGeral");
        	$("#graficoGeral").before("<h2>Gráfico de Desempenho Geral</h2>")
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

function listarAtendentes(){
	$.ajax({
        url: '/OrionSpring/usuario/findAtendentesJSON',
        success: function (data) {
        	montarSelectAtendentes(data);
        }
    });
}


function verificarGraficoAdministrador(id){
	$.ajax({
        url: '/OrionSpring/sla/carregarGraficoSLAAdministrador',
        data:	{
        	id: id
        },
        success: function (data) {
    		carregarGrafico(data, "graficoTecnico");
        },
		complete: function (data){
			
		}
    });
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

function montarSelectAtendentes(data){
	var i = 0;	
	var html = "<option value=''></option>"
	while(data[i] != undefined && data[i] != ""){
		html += "<option data-cpf='" + data[i].cpf + "' value=" + data[i].idUsuario + ">";
	  	html += data[i].nome;
	  	html += "</option>";
	  	i++;
	}
  	$("[data-select='atendentes']").html(html);
}


function verificarGraficoPorTecnico(){
	$.ajax({
        url: '/OrionSpring/sla/carregarGraficoSLAPorTecnico',
        success: function (data) {
        	if(data.perfil == 2){
        		carregarGrafico(data, "graficoTecnico");
        		$("#graficoTecnico").before("<h2>Gráfico de Desempenho Pessoal</h2>")
        	}
        	if(data.perfil == 1){
        		carregarGrafico(data, "graficoTecnico");
        		$("#graficoTecnico").before("<h2>Gráfico de Desempenho Pessoal</h2>")
        	}
        },
		complete: function (data){
			
		}
    });
}


function carregarGrafico(data, id){
	 $('#' + id).highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: 1,//null,
	            plotShadow: false
	        },
	        title: {
	            text: ''
	        },
	        tooltip: {
	            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
	        },
	        plotOptions: {
	            pie: {
	                allowPointSelect: true,
	                cursor: 'pointer',
	                dataLabels: {
	                    enabled: true,
	                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
	                    style: {
	                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
	                    }
	                }
	            }
	        },
	        series: [{
	            type: 'pie',
	            name: 'Porcentagem de chamados',
	            data: [ 
	                ['Não Resolvidos Dentro da SLA',   data.CNRDSLA],
	                ['Não Resolvidos Fora da SLA',     data.CNRFSLA],
	                ['Resolvidos Dentro da SLA',       data.CRDSLA],
	                ['Resolvidos Fora da SLA',         data.CRFSLA]
	            ]
	        }]
	    });
}