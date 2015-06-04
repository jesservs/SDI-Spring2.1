<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"  import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Orion</title>
</head>
<body>
<jsp:include page="../decorators/header.jsp"></jsp:include>
	<div class="modal fade" id="create_update_prioridade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Prioridade
					</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 column" data-form="prioridade">
						<div class="form-group">
							<input type="hidden" class="form-control" id="idPrioridade"/>
							 <label>Descrição</label><input type="text" class="form-control" placeholder="Descrição" id="descPrioridade"/>
						</div>
						<div class="form-group">
							<label>Tempo Resolução</label><input type="text" class="form-control" placeholder="Tempo Resolução" id="tempoResolucao" data-required/>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
					 <button type="button" class="btn btn-primary" onclick="createUpdatePrioridade();">Salvar</button>
				</div>
			</div>
		</div>
	</div>
			
	<div class="container">
		
		<div class="page-header">
			<h1>
				Prioridade <small>Listagem de Prioridade</small>
			</h1>
		</div>
		<button type="button" class="btn btn-primary" href="#create_update_prioridade" data-toggle="modal"  onclick="limparForm('prioridade');">Novo</button>
		<br/><br/>
		<div class="col-md-12 column" data-table="prioridade" style="display:none;">
			<table class="table table-hover"> 
				<thead>
				  <th style="width: 25%">Código</th>
				  <th style="width: 25%">Descrição</th>
				  <th style="width: 25%">Tempo Resolução(h)</th>
				  <th style="width: 25%">Controle</th>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/resources/js/prioridade.js'/>"></script>
</body>
</html>