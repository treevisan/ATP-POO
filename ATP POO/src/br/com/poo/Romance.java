package br.com.poo;


public class Romance extends Livro {
	
	public String classificacao; 

	public Romance (String nome, int anoDePublicacao, String autor, String subtitulo) {
		super(nome, anoDePublicacao, autor);
		this.classificacao = subtitulo;
		
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String subtitulo) {
		this.classificacao = subtitulo;
	}
	public String toString() {
		String retorno = super.toString();
		retorno += "Classificação (Histórico, Aventura, Psicológico ou Experimental : " + this.classificacao + "\n";
		return retorno;

	}
}

