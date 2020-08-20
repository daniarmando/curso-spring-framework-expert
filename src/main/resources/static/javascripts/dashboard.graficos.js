var Brewer = Brewer || {};

Brewer.GraficoVendasPorMes = (function() {
	
	function GraficoVendasPorMes() {
		this.ctx = $('#graficoVendasPorMes')[0].getContext('2d');
	}
	
	GraficoVendasPorMes.prototype.iniciar = function() {
		var graficoVendasPorMes = new Chart(this.ctx, {
			type: 'line',
			data: {
				labels: ['Jan', 'Fev', 'Mar', 'Abri', 'Mai', 'Jun'],
				datasets: [{
					label: 'Vendas por mês',
					backgroundColor: 'rgba(26, 179, 148, 0.5)',
					pointBorderColor: 'rgba(26, 179, 148, 0.5)',
					pointBackgroundColor: '#fff',
					data: [10, 5, 7, 2, 9]
				}]
			}			
		});			
	}
	
	return GraficoVendasPorMes;
	
}());

$(function() {
	var graficoVendasPorMes = new Brewer.GraficoVendasPorMes();
	graficoVendasPorMes.iniciar();
});