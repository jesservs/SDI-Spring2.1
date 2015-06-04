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
	
	<div class="modal fade" id="create_update_usuario" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Usuário
					</h4>
				</div>
				<div class="modal-body">
					<div class="col-md-12 column" data-form="usuario">
						<input type="hidden" class="form-control" id="idUsuario" />
						<div class="form-group">
							 <label>Nome</label><input type="text" class="form-control" placeholder="Nome" id="nomeUsuario"/>
						</div>
						<div class="form-group">
							 <label>CPF</label><input type="text" class="form-control" placeholder="CPF" id="cpf" data-required/>
						</div>
						<div class="form-group">
							 <label>Email</label><input type="text" class="form-control" placeholder="Email" id="email" data-required/>
						</div>
						<div class="row">
							<div class="col-md-6 column">
							<div class="form-group">	
								<label>Login</label>
								<input type="text" class="form-control" id="login" placeholder="Login" />
							</div>
							</div>
							<div class="col-md-6 column">
								<div class="form-group">
									<label>Senha</label>
									<input type="password" class="form-control" id="senha" placeholder="Senha" />
								</div>
							</div>
						</div>
						<div class="row">
							<div class="col-md-6 column">
								<div class="form-group">
									<label>Departamento</label>
									<select class="form-control"  data-select="departamento">
									</select>
								</div>
							</div>
							<div class="col-md-6 column">
								<div class="form-group">
									<label>Perfil</label>
									<select class="form-control" id="perfil" data-select="perfil" data-required>
									</select>
								</div>
							</div>
						</div>
						<div class="form-group">
							 <label>Telefone</label><input type="text" class="form-control" id="telefone" placeholder="Telefone" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> <button type="button" class="btn btn-primary" onclick="createUpdateUsuario();">Salvar</button>
				</div>
			</div>
		</div>
	</div>
				
	<div class="container">
		<div class="page-header">
			<h1>
				Usuario <small>Listagem de Usuario</small>
			</h1>
		</div>
		<button type="button" class="btn btn-primary" href="#create_update_usuario" data-toggle="modal" onclick="limparForm('usuario');listarDepartamento();listarPerfil();">Novo</button>
		<br/><br/>
		<div class="col-md-12 column" data-table="usuario" style="display:none;">
			<table class="table table-hover"> 
				<thead>
				  <th style="width: 5%">Código</th>
				  <th style="width: 20%">Nome</th>
				  <th style="width: 15%">CPF</th>
				  <th style="width: 10%">Telefone</th>
				  <th style="width: 20%">Departamento</th>
				  <th style="width: 15%">Perfil</th>
				  <th style="width: 15%">Controle</th>
				</thead>
				<tbody>
				</tbody>
			</table>
		</div>
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/resources/js/usuario.js'/>"></script>
</body>
</html>