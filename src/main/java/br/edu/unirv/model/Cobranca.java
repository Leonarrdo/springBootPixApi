package br.edu.unirv.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Cobranca {
	
	@Id
	@GeneratedValue
	private Long id;
	private String txid;
	private String chave;
	private int expiracao;
	private String cpf;
	private String cnpj;
	private String nome;
	private String valorOriginal;
	private String solicitacaoPagador;
	private String status;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public int getExpiracao() {
		return expiracao;
	}
	public void setExpiracao(int expiracao) {
		this.expiracao = expiracao;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getValorOriginal() {
		return valorOriginal;
	}
	public void setValorOriginal(String valorOriginal) {
		this.valorOriginal = valorOriginal;
	}
	public String getSolicitacaoPagador() {
		return solicitacaoPagador;
	}
	public void setSolicitacaoPagador(String solicitacaoPagador) {
		this.solicitacaoPagador = solicitacaoPagador;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
