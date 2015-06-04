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
	
	<div class="modal fade" id="create_update_departamento" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Departamento
					</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 column" data-form="departamento">
						<div class="form-group">
							 <input type="hidden" class="form-control" placeholder="Descrição" id="idDepto"/>
							 <label>Descrição</label><input type="text" class="form-control" placeholder="Descrição" id="descDepto" data-required />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
					 <button type="button" class="btn btn-primary" onclick="createUpdateDepartamento();">Salvar</button>
				</div>
			</div>
		</div>
	</div>
			
	<div class="container">
		
		<div class="page-header">
			<h1>
				Departamento <small>Listagem de Departamento</small>
			</h1>
		</div>
		<button type="button" class="btn btn-primary" href="#create_update_departamento" data-toggle="modal"  onclick="limparForm('departamento');">Novo</button>
		<br/><br/>
		<div class="col-md-12 column" data-table="departamento" style="display:none;">
			<table class="table table-hover"> 
				<thead>
				  <th style="width: 15%">codDepartamento</th>
				  <th style="width: 70%">descDepartamento</th>
				  <th style="width: 15%">Controle</th>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/resources/js/departamento.js'/>"></script>
</body>
</html>