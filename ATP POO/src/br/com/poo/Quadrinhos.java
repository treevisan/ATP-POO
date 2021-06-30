package br.com.poo;


public class Quadrinhos extends Livro {
	
		private double peso;

		public Quadrinhos(String nome, int anoDePublicacao, String autor, double peso) {
			super(nome, anoDePublicacao, autor);
			this.peso = peso;
			
		}

		public double getPeso() {
			return peso;
		}

		public void setPeso(double peso) {
			this.peso = peso;
		}
		public String toString() {
			String retorno = super.toString();
			retorno += "Peso do Livro: " + this.peso + " gramas\n";
			return retorno;
		}

	}


