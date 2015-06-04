<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"  import="java.sql.*" errorPage=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
	<link href="<c:url value='/resources/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" />
	<link href="<c:url value='/resources/css/style.css' />" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="<c:url value='/resources/js/jquery-2.1.1.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/bootstrap.min.js' />"></script>
	<script type="text/javascript" src="<c:url value='/resources/js/scripts.js' />"></script>
	<script>
		$(document).ready(function() {
			var mensagem = "${mensagem}";
			if(mensagem != null && mensagem != undefined && mensagem != ""){
				exibirMensagemModalAlerta(mensagem, "logar");
				removerMensagem();
			}
		});
	</script>
</head>
<body>
<!-- Modal -->
<div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">Orion Test Manager</h4>
      </div>
      <form role="form" action="logar">
	      <div class="modal-body" data-form="logar">
			  <div class="form-group">
			    <label>Login</label>
			    <input type="text" class="form-control" name="login" data-input="login" placeholder="Login" data-required>
			  </div>
			  <div class="form-group">
			    <label>Senha</label>
			    <input class="form-control" type="password" name="senha" data-input="senha" placeholder="Senha" data-required>
			  </div>
	      </div>
	      <div class="modal-footer">
	        <!--button type="button" class="btn btn-default" data-dismiss="modal">Close</button-->
	        <button type="button" class="btn btn-primary" onclick="logar();">Entrar</button>
	      </div>
      </form>
    </div>
  </div>
</div>
<script type="text/javascript" src="<c:url value='/resources/js/login.js'/>"></script>
</body>
</html>