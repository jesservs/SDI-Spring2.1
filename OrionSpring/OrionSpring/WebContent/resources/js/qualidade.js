$(document).ready(function() {
	listarQualidade();
	verificarGrafico();
})

function verificarGrafico(){
	$.ajax({
        url: '/OrionSpring/qualidade/getNotas',
        success: function (data) {
        	carregarGrafico(data);
        	//alert(data.satisfeito);
        }
    });
}

function carregarGrafico(data){
	 $('#graficoGeral').highcharts({
	        chart: {
	            plotBackgroundColor: null,
	            plotBorderWidth: 1,//null,
	            plotShadow: false
	        },
	        title: {
	            text: 'Gráfico da Qualidade dos Chamados'
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
	                ['Muito insatisfeito',   data.minsatisfeito],
	                ['Insatisfeito',     data.insatisfeito],
	                ['Satisfeito',       data.satisfeito],
	                ['Muito Satisfeito',         data.msatisfeito]
	            ]
	        }]
	    });
}

function listarQualidade(){
	$.ajax({
        url: '/OrionSpring/qualidade/findAllJSON',
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
	  	html += "<td>" + data[i].idQualidade + "</td>";
	  	html += "<td>" + data[i].nota + "</td>";
	  	html += "<td>" + data[i].descQualidade + "</td>";
		html += "<td>" + data[i].solicitante.nome + "</td>";
		html += "<td>";
		html += "<a href='javascript: listarEventosHistorico("+data[i].chamado.idChamado+");'>" + data[i].chamado.idChamado + "</a>"
	  	html += "</td>";
		html += "</tr>";
	  	i++;
	}
  	$("[data-table='qualidade'] tbody").empty().append(html);
  	$("[data-table='qualidade']").slideDown(1000);
}

