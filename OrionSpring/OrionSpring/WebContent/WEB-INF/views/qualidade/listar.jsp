<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"  import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>Orion</title>
</head>
<body>
<jsp:include page="../decorators/header.jsp"></jsp:include>
	
		<!-- Large modal -->
	<div class="modal fade bs-example-modal-lg" tabindex="-1" id="historicoChamado" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	  <div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
				<h4 class="modal-title" id="myModalLabel">
					Histórico Chamado
				</h4>
			</div>
	    	<div class="container-fluid">
		  		<br>
				<div class="row">
					<div class="col-md-12 column" data-table="historico" style="display:none;">
					<table class="table table-hover"> 
						<thead>
						  <tr>
						  <th style="width: 10%">Data Abertura</th>
						  <th style="width: 10%">Data Fechamento</th>
						  <th style="width: 20%">Descrição</th>
						  <th style="width: 10%">Status</th>
						  <th style="width: 10%">Responsável</th>
						  <th style="width: 10%">Atendente</th>
						  <th style="width: 10%">Categoria</th>
						  <th style="width: 10%">Prioridade</th>
						  </tr>
						</thead>
						<tbody></tbody>
				   </table>
		           </div>
		  		</div>
			</div>
			<div class="modal-footer">
				 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
	    </div>
	  </div>
	</div>

	<div class="container">
		<div class="page-header">
			<h1>
				Central de Qualidade <small>Listagem de qualidade dos chamados</small>
			</h1>
		</div>
		<div class="col-md-12 column" id="graficoGeral">
		</div>
		<div class="col-md-12 column" data-table="qualidade" style="display:none;">
			<table class="table table-hover"> 
				<thead>
				  <th style="width: 10%">Código</th>
				  <th style="width: 15%">Nota (1 a 3)</th>
				  <th style="width: 45%">Descrição</th>
				  <th style="width: 20%">Solicitante/Avaliador</th>
				  <th style="width: 10%">Código do Chamado</th>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/resources/js/qualidade.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/historico.js'/>"></script>
</body>
</html>