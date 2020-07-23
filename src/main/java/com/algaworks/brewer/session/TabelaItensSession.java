package com.algaworks.brewer.session;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.algaworks.brewer.model.Cerveja;
import com.algaworks.brewer.model.ItemVenda;

@SessionScope
@Component
public class TabelaItensSession {
	
	private Set<TabelaItensVenda> tabelas = new HashSet<>();

	public void adicionarItemCerveja(String uuid, Cerveja cerveja, int quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorId(uuid);
		tabela.adicionarItemCerveja(cerveja, quantidade);		
		tabelas.add(tabela);
	}

	public void alterarQuantidadeItens(String uuid, Cerveja cerveja, Integer quantidade) {
		TabelaItensVenda tabela = buscarTabelaPorId(uuid);
		tabela.alterarQuantidadeItens(cerveja, quantidade);		
	}

	public void excluirItem(String uuid, Cerveja cerveja) {
		TabelaItensVenda tabela = buscarTabelaPorId(uuid);
		tabela.excluirItem(cerveja);
	}

	public List<ItemVenda> getItens(String uuid) {
		return buscarTabelaPorId(uuid).getItens();
	}
	
	public BigDecimal getValorTotal(String uuid) {
		return buscarTabelaPorId(uuid).getValorTotal();
	}
	
	private TabelaItensVenda buscarTabelaPorId(String uuid) {
		TabelaItensVenda tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny().orElse(new TabelaItensVenda(uuid));
		return tabela;
	}

}
