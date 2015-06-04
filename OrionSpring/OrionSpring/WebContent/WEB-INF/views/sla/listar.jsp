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
	<div class="container">
		<div class="page-header">
			<h1>
				Acordo de nível de serviço <small>SLA</small>
			</h1>
		</div>
		<div class="col-md-12 column" style="text-align: right;" data-menu-privado>
			<div class="alert alert-info" role="alert" style="background-color: white;">
			    <div class="form-inline">
					<label>Técnicos</label>
					<select class="form-control" data-select="tecnicos"></select>
					<label>Atendentes</label>
					<select class="form-control" data-select="atendentes"></select>
				</div>
			</div>
		</div>
		<div class="col-md-12 column" id="graficoTecnico">

		</div>
		<div class="col-md-12 column" id="graficoGeral">
		</div>
		<br/><br/>
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<script type="text/javascript" src="<c:url value='/resources/js/sla.js'/>"></script>
</body>
</html>