package br.com.poo;

import java.io.Serializable;

public abstract class Livro implements Serializable {
	
	private String nome;
	private int anoDePublicacao;
	private String autor;
	
	public Livro(String nome, int anoDePublicacao, String autor) {
		this.nome = nome;
		this.anoDePublicacao = anoDePublicacao;
		this.autor = autor;

}
	public String toString() {
		String retorno = "";
		retorno += "Nome do Livro: " + this.nome + "\n";
		retorno += "Autor: " + this.autor + "\n";
		retorno += "Ano de Publicação: " + this.anoDePublicacao + "\n";
		return retorno;
	
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(int anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
}

