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
		<div class="modal fade" id="create_update_categoria" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Categoria
					</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 column" data-form="categoria">
						<div class="form-group">
							 <label>Descrição</label>
							 <input type="hidden" class="form-control" id="idCategoria" />
							 <textarea class="form-control" rows="3" placeholder="Descrição" id="descCategoria" data-required></textarea>
						</div>
						<div class="form-group">
							<label>Tipo</label>
							<select class="form-control" data-option="tipo">
							</select>
						</div>
						<div class="form-group">
							<label>Prioridade</label>
							<select class="form-control" data-option="prioridade">
							</select>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
					 <button type="button" class="btn btn-primary" onclick="createUpdateCategoria();">Salvar</button>
				</div>
			</div>
		</div>
	</div>
			
	<div class="container">
		
		<div class="page-header">
			<h1>
				Categoria <small>Listagem de Categoria</small>
			</h1>
		</div>
		<button type="button" class="btn btn-primary" href="#create_update_categoria" data-toggle="modal" onclick="carregarTipoPrioridades();">Novo</button>
		<br/><br/>
		<div class="col-md-12 column" data-table="categoria" style="display:none;">
			<table class="table table-hover"> 
				<thead>
					<tr>
					  <th style="width: 20%">Código</th>
					  <th style="width: 20%">Descrição</th>
					  <th style="width: 20%">Tipo</th>
					  <th style="width: 20%">Prioridade</th>
					  <th style="width: 20%">Controle</th>
					</tr>
				</thead>
				<tbody>
				
				</tbody>
			</table>
			<input type="hidden" data-pagAtual value="1">
			<div class="col-md-12 column" style="text-align: center;">
				<ul class="pagination" data-pagination="categoria">
				</ul>
			</div>
		</div>
				
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/resources/js/categoria.js'/>"></script>
</body>
</html>