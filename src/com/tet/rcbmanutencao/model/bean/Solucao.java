package com.tet.rcbmanutencao.model.bean;

public class Solucao {

	private Long id;
	private String descricao_peca;
	private String numero_peca;
	private String quantidade;
	private String tipo;
	private String tecnico_resposavel;
	private String retorno;
	private String hora_final;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao_peca() {
		return descricao_peca;
	}
	public void setDescricao_peca(String descricao_peca) {
		this.descricao_peca = descricao_peca;
	}
	public String getNumero_peca() {
		return numero_peca;
	}
	public void setNumero_peca(String numero_peca) {
		this.numero_peca = numero_peca;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getTecnico_resposavel() {
		return tecnico_resposavel;
	}
	public void setTecnico_resposavel(String tecnico_resposavel) {
		this.tecnico_resposavel = tecnico_resposavel;
	}
	public String getRetorno() {
		return retorno;
	}
	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}
	public String getHora_final() {
		return hora_final;
	}
	public void setHora_final(String hora_final) {
		this.hora_final = hora_final;
	}
}
