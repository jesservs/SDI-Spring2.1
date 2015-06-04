<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"  import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Orion Test Manager</title>
	<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-2.1.1.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/scripts.js' />"></script>
	<script>
		$(function(){
			setPrioridades();
		});
		function setPrioridades(){
			/*
				MAPEAMENTO DE PERFIL
				1 - Atendente
				2 - Tecnico
				3 - Adminitrador
				4 - Usuario
			*/
			var perfil = ${usuarioLogado.perfil.idPerfil};
			if(perfil == 2) {
				$("[data-menu-privado]").remove();
				$("[data-btn-novo]").remove();
				$("[data-btn-qualidade]").remove();
				$("[data-btn-status='atendente']").remove();
			}
			if(perfil == 1){
				$("[data-menu-privado]").hide();
				$("[data-btn-qualidade]").remove();
			}
			if(perfil == 4){
				$("[data-btn-status='atendente']").remove();
				$("[data-menu-privado]").remove();
				$("[data-btn-tecnicos]").remove();
				$("[data-btn-bsConhecimento]").remove();
				$("[data-control='cpf']").remove();
				$("[data-control='usuario']").remove();
				$("[data-btn-status]").remove();
				listarTipo();
			};
			if(perfil == 3){
				$("[data-btn-status='atendente']").remove();
				$("[data-btn-qualidade]").remove();
			}
		}
	</script>
</head>
<body>
<br/>
<div class="container">
	<div class="row clearfix">
		<div class="col-md-12 column">
			<nav class="navbar navbar-default  navbar-fixed-top" role="navigation">
				<div class="navbar-header">
					 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"> <span class="sr-only"></span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button> <a class="navbar-brand" href="/OrionSpring">Orion Test Manager</a>
				</div>
				
				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav navbar-right" data-menu-privado>
						<li class="dropdown">
							 <a href="#" class="dropdown-toggle" data-toggle="dropdown">Conf.Gerais<strong class="caret"></strong></a>
							<ul class="dropdown-menu">
								<li>
									<a href="<c:url value='/perfil/listar'/>">Perfil</a>
								</li>
								<li>
									<a href="<c:url value='/categoria/listar'/>">Categoria</a>
								</li>
								<li>
									<a href="<c:url value='/prioridade/listar'/>">Prioridade</a>
								</li>
								<li>
									<a href="<c:url value='/status/listar'/>">Status</a>
								</li>
								<li>
									<a href="<c:url value='/tipo/listar'/>">Tipo</a>
								</li>
								<li>
									<a href="<c:url value='/departamento/listar'/>">Departamento</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
				
			</nav>
		</div>
	</div>
</div>
<br/><br/>
 <div class="container">
	<div class="alert alert-info" role="alert">Bem vindo, ${usuarioLogado.nome} <a href="<c:url value='/logout'/>"><span class="label label-primary">Logout</span></a></div>
	<div class="row clearfix">
		<div class="col-md-6 column">
			<a href="<c:url value='/chamado/listar'/>">
				<img src="<c:url value='/resources/img/icon_chamados.png'/>" class="img-thumbnail" width="15%"/>
			</a>
			<a href="<c:url value='/usuario/listar'/>"  data-menu-privado>
				<img src="<c:url value='/resources/img/icon_usuarios.png'/>" class="img-thumbnail" width="15%"/>
			</a>
			<a href="<c:url value='/conhecimento/listar'/>"  data-btn-bsConhecimento>
				<img  src="<c:url value='/resources/img/icon_conhecimento.png'/>" class="img-thumbnail" width="13.5%"/>
			</a>
			<a href="<c:url value='/sla/listar'/>"  data-btn-bsConhecimento>
				<img  src="<c:url value='/resources/img/icon_sla.png'/>" class="img-thumbnail" width="19.5%"/>
			</a>
			<a href="<c:url value='/qualidade/listar'/>"  data-menu-privado>
				<img  data-menu-privado src="<c:url value='/resources/img/icon_qualidade.png'/>" class="img-thumbnail" width="15%"/>
			</a>
			<a href="<c:url value='/logout'/>">
				<img  data-menu-privado src="<c:url value='/resources/img/icon_logout.png'/>" class="img-thumbnail" width="15%"/>
			</a>
		</div>
	</div>
</div>
<!-- 
<a href="usuario/listar">Listar Usuarios</a>
 -->
</body>
</html>