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
	<div class="modal fade" id="create_update_conhecimento" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Conhecimento
					</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 column" data-form="conhecimento">
						<div class="form-group">
							<label>Tipo</label>
							<select class="form-control" data-select="tipo" data-required>
							</select>
						</div>
						<div class="form-group">
							<label>Categoria</label>
							<select class="form-control" data-select="categoria" readonly="true">
							</select>
						</div><div class="form-group">
							<label>Prioridade</label>
							<input type="text" class="form-control" placeholder="Prioridade" readonly="true" data-input="prioridade">
						</div>
						<div class="form-group">
						  <label>Observações</label><textarea class="form-control" rows="3" id="descricao"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> <button type="button" class="btn btn-primary" onclick="createUpdateConhecimento();">Salvar</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="page-header">
			<h1>
				Base de conhecimento <small>Listagem de Conhecimento</small>
			</h1>
		</div>
		<div class="col-md-12 column" style="text-align: right;">
			<div class="alert alert-info" role="alert" style="background-color: white;display:none;"  data-campos-filtro="conhecimento" data-form="conhecimentoFiltro">
			    <div class="form-inline">
					<label>Tipo</label>
					<select class="form-control" data-select-filtro="tipo" data-required></select>
					<label>Categoria</label>
					<select class="form-control" data-select-filtro="categoria" readonly="true"></select>
					<button type="button" class="btn btn-default" onclick="listarConhecimentoComFiltro();">Filtrar</button>
				</div>
			</div>
		</div>
		<div class="col-md-6 column">
			<button type="button" class="btn btn-primary" href="#create_update_conhecimento" data-toggle="modal" onclick="limparForm('conhecimento');listarTipo();">Novo</button>
		</div>
		<div class="col-md-6 column" style="text-align: right;">
			<button type="button" class="btn btn-default" onclick="exibirFiltro('conhecimento');listarTipoFiltro();">Filtro</button>
		</div>
		<br/><br/>
		<div class="col-md-12 column" data-table="conhecimento" style="display:none;">
			<table class="table table-hover"> 
				<thead>
				  <th style="width: 5%">Código</th>
				  <th style="width: 15%">Criação/Alteração</th>
				  <th style="width: 35%">Solução</th>
				  <th style="width: 15%">Responsável</th>
				  <th style="width: 20%">Categoria</th>
				  <th style="width: 10%">Tipo</th>
				  <!-- 
				  <th style="width: 15%">Controle</th>
				   -->
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/resources/js/conhecimento.js'/>"></script>
</body>
</html>