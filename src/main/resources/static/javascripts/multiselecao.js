Brewer = Brewer || {};

Brewer.Multiselecao = (function() {
	
	function Multiselecao() {
		this.statusBtn = $('.js-status-btn');
		this.selecaoCheckbox = $('.js-selecao');			
	}
	
	Multiselecao.prototype.iniciar = function() {
		this.statusBtn.on('click', onStatusBtnClicado.bind(this));
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
		
		var checkboxSelecionados = this.selecaoCheckbox.filter(':checked');
		var codigos = $.map(checkboxSelecionados, function(c) {
			return $(c).data('codigo');
		});
		
		var token = $('input[name=__csrf]').attr('value');
		var header = $('input[name=_csrf_header]').attr('value') ;
		
		if (codigos.length > 0) {
			$.ajax({
				url: '/brewer/usuarios/status',
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
	
	return Multiselecao;
	
}());

$(function() {
	var multiselecao = new Brewer.Multiselecao();
	multiselecao.iniciar();		  
});
