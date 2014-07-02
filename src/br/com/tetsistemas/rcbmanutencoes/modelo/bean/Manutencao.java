package br.com.tetsistemas.rcbmanutencoes.modelo.bean;

import java.io.Serializable;

@SuppressWarnings("serial")

public class Manutencao implements Serializable{

	private Long id;
	private String nome;
	private String descricao;
	private String numero;
	private String af;
	private String horimetro;
	private String foto_plataforma;
	private String descricao_problema;
	private String foto_problema;
	private String causa_problema;
	private String foto_causa;
	
	//Variaveis para a solução da manutenção
	private String descricao_peca;
	private String numero_peca;
	private String quantidade;
	private String tipo;
	
	
	@Override
	public String toString() {
		return nome;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getAf() {
		return af;
	}

	public void setAf(String af) {
		this.af = af;
	}

	public String getHorimetro() {
		return horimetro;
	}

	public void setHorimetro(String horimetro) {
		this.horimetro = horimetro;
	}

	public String getFoto_plataforma() {
		return foto_plataforma;
	}

	public void setFoto_plataforma(String foto_plataforma) {
		this.foto_plataforma = foto_plataforma;
	}

	public String getDescricao_problema() {
		return descricao_problema;
	}

	public void setDescricao_problema(String descricao_problema) {
		this.descricao_problema = descricao_problema;
	}

	public String getFoto_problema() {
		return foto_problema;
	}

	public void setFoto_problema(String foto_problema) {
		this.foto_problema = foto_problema;
	}

	public String getCausa_problema() {
		return causa_problema;
	}

	public void setCausa_problema(String causa_problema) {
		this.causa_problema = causa_problema;
	}

	public String getFoto_causa() {
		return foto_causa;
	}

	public void setFoto_causa(String foto_causa) {
		this.foto_causa = foto_causa;
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

}
