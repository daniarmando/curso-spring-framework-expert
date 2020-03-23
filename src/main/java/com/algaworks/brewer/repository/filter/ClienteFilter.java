package com.algaworks.brewer.repository.filter;

import com.algaworks.brewer.model.TipoPessoa;

public class ClienteFilter {
	
	private String nome;	
	private String cpfOuCnpj;
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpfOuCnpj() {
		return this.cpfOuCnpj;
	}
	
	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj= cpfOuCnpj;
	}
	
	public Object getCpfOuCnpjSemFormatacao() {
		return TipoPessoa.removerFormatacao(this.cpfOuCnpj);
	}

}
