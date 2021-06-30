package br.com.poo;


public class Poesia extends Livro {
	
	private int paginas;

	public Poesia(String nome, int anoDePublicacao, String autor, int paginas) {
		super(nome, anoDePublicacao, autor);
		this.paginas = paginas;
		}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}
	public String toString() {
		String retorno = super.toString();
		retorno += "Quantidade de Páginas: " + this.paginas + "\n";
		return retorno;
	}

}

