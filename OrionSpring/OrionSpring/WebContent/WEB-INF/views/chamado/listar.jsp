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
						  <!-- 
						  <th style="width: 10%">Solicitante</th>
						   -->
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

	<div class="modal fade" id="modalResponsavel" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Escolha o Técnico
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group" data-form="mudarReponsavel">
						<input type="hidden" id="idChamado">
						<label>Status</label>
						<select class="form-control" data-select="status" data-required>
						</select>
						<label>Técnico</label>
						<select class="form-control" data-select="tecnicos" data-required>
						</select>
						<div class="form-group">
						  <label>Observações</label><textarea class="form-control" rows="3" id="obsTecnico" data-input="observacoes"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
					 <button type="button" class="btn btn-primary" onclick="setTecnico();">Salvar</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modalAvaliarChamado" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Avaliação da qualidade do Chamado
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group" data-form="qualidade">
						<input type="hidden" id="idChamadoQualidade">
						<label>Grau de Satisfação</label>
						<select class="form-control" data-select="avaliacaoQualidade" id="avaliacaoQualidade" data-required>
							<option value=""></option>
							<option value="1">Muito Insatisfeito</option>
							<option value="2">Insatisfeito</option>
							<option value="3">Satisfeito</option>
							<option value="4">Muito Satisfeito</option>
						</select>
						<div class="form-group">
						  <label>Observações</label><textarea class="form-control" rows="3" id="descQualidade" data-required></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
					 <button type="button" class="btn btn-primary" onclick="createQualidade();">Salvar</button>
				</div>
			</div>
		</div>
	</div>
	
	<div class="modal fade" id="modalStatus" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Escolha o Status
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group" data-form="alterarStatus">
						<input type="hidden" id="idChamadoStatus">
						<label>Status</label>
						<select class="form-control" data-select-novoStatus="status">
						</select>
						<div class="form-group">
						  <label>Observações</label><textarea class="form-control" rows="3" id="obsStatus"></textarea>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> 
					 <button type="button" class="btn btn-primary" onclick="setStatus();">Salvar</button>
				</div>
			</div>
		</div>
	</div>

		<div class="modal fade" id="create_chamado" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="myModalLabel">
						Chamado
					</h4>
				</div>
				<div class="modal-body" data-form="chamado">
					<div class="col-md-12 column">
						<div class="form-group" data-control="usuario">
							<label>Usuario</label>
							<select class="form-control" data-select="usuario" data-required>
							</select>
						</div>
						<div class="form-group" data-control="cpf">
							<label>CPF</label>
							<input type="text" class="form-control" placeholder="CPF" readonly="true" data-input="cpf">
						</div>
					</div>
					<div class="col-md-12 column">
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
					 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button> <button type="button" class="btn btn-primary" onclick="create();">Salvar</button>
				</div>
			</div>
		</div>
	</div>
			
	<div class="container">
		<div class="page-header">
			<h1>
				Chamado <small>Listagem de Chamado</small>
			</h1>
		</div>
		<div class="col-md-12 column" style="text-align: right;">
			<div class="alert alert-info" role="alert" style="background-color: white;display:none;" data-campos-filtro="chamado" data-form="conhecimentoFiltro">
			    <div class="form-inline">
					<label>Status</label>
					<select class="form-control" data-select-filtro="status" data-required></select>
					<label>Prioridade</label>
					<select class="form-control" data-select-filtro="prioridade"></select>
					<button type="button" class="btn btn-default" onclick="listarTodosChamadosFiltro(1);setPagAtual(1);">Filtrar</button>
				</div>
			</div>
		</div>
		<div class="col-md-6 column">
			<button type="button" class="btn btn-primary" data-btn-novo href="#create_chamado" data-toggle="modal" onclick="limparForm('chamado');listarUsuario();">Novo</button>
			<img src="<c:url value='/resources/img/statusChamados.jpg'/>" class="img-thumbnail"/>
		</div>
		<div class="col-md-6 column" style="text-align: right;">
			<button type="button" class="btn btn-default" onclick="exibirFiltro('chamado');carregarFiltro();">Filtro</button>
		</div>
		<br/><br/>
		<div class="col-md-12 column" data-table="chamado" style="display:none;">
			<table class="table table-hover"> 
				<thead>
				  <th style="width: 5%">Código</th>
				  <th style="width: 10%">Data Abertura</th>
				  <th style="width: 10%">Data Fechamento</th>
				  <th style="width: 15%">Descrição</th>
				  <th style="width: 10%">Status</th>
				  <th style="width: 10%">Responsável</th>
				  <th style="width: 10%">Solicitante</th>
				  <th style="width: 10%">Atendente</th>
				  <th style="width: 10%">Categoria</th>
				  <th style="width: 10%">Prioridade</th>
				</thead>
				<tbody>
				</tbody>
			</table>
			<input type="hidden" data-pagAtual value="1">
			<div class="col-md-12 column" style="text-align: center;">
				<ul class="pagination" data-pagination="chamado">
				</ul>
			</div>
		</div>
	</div>
<jsp:include page="../decorators/footer.jsp"></jsp:include>
<!--  -->
<script type="text/javascript" src="<c:url value='/resources/js/chamado.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/historico.js'/>"></script>
</body>
</html>