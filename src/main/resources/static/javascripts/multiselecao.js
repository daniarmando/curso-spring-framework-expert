Brewer = Brewer || {};

Brewer.Multiselecao = (function() {
	
	function Multiselecao() {
		this.statusBtn = $('.js-status-btn');
		this.selecaoCheckbox = $('.js-selecao');
		this.selecaoTodosCheckbox = $('.js-selecao-todos');
	}
	
	Multiselecao.prototype.iniciar = function() {
		this.statusBtn.on('click', onStatusBtnClicado.bind(this));
		this.selecaoTodosCheckbox.on('click', onSelecaoTodosClicado.bind(this));
		this.selecaoCheckbox.on('click', onSelecaoClicado.bind(this));
		prepararCsrfToken();
	}
	
	function prepararCsrfToken() {		
		var token = $('input[name=__csrf]').val();
		var header = $('input[name=_csrf_header]').val();
		
		$(document).ajaxSend(function (e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	}
	
	function onStatusBtnClicado() {
		var botaoClicado = $(event.currentTarget);
		var status = botaoClicado.data('status');
		var url = botaoClicado.data('url');
		
		var checkboxSelecionados = this.selecaoCheckbox.filter(':checked');
		var codigos = $.map(checkboxSelecionados, function(c) {
			return $(c).data('codigo');
		});
		
		var token = $('input[name=__csrf]').attr('value');
		var header = $('input[name=_csrf_header]').attr('value') ;
		
		if (codigos.length > 0) {
			$.ajax({
				url: url,
				method: 'PUT',			
				data: {
					codigos: codigos,
					status: status
				},
				success: function() {
					window.location.reload();
				}
			});
		}		
	}
	
	function onSelecaoTodosClicado() {
		var status = this.selecaoTodosCheckbox.prop('checked');
		this.selecaoCheckbox.prop('checked', status);
		statusBotaoAcao.call(this, status);
	}
	
	function onSelecaoClicado() {
		var selecaoCheckboxChecados = this.selecaoCheckbox.filter(':checked');
		this.selecaoTodosCheckbox.prop('checked', selecaoCheckboxChecados.length >= this.selecaoCheckbox.length);
		statusBotaoAcao.call(this, selecaoCheckboxChecados.length);
	}
	
	function statusBotaoAcao(ativar) {
		ativar ? this.statusBtn.removeClass('disabled') : this.statusBtn.addClass('disabled');
	}
	
	return Multiselecao;
	
}());

$(function() {
	var multiselecao = new Brewer.Multiselecao();
	multiselecao.iniciar();		  
});
