package br.com.poo;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Biblioteca {

	private ArrayList<Livro> livros = new ArrayList<Livro>();

	public String[] leValores(String[] dadosIn) { // método de leitura de dados
		String[] dadosOut = new String[dadosIn.length];

		for (int i = 0; i < dadosIn.length; i++)
			dadosOut[i] = JOptionPane.showInputDialog("" + dadosIn[i] + ": ");

		return dadosOut;
	}

	public Poesia lePoesia() { // método para a leitura do gênero poesia

		String[] valores = new String[3];
		String[] nomeVal = { "Nome do Livro", "Ano de Publicação", "Autor", "Número de Páginas" };
		valores = leValores(nomeVal);

		int anoDePublicacao = this.retornaInteiro(valores[1]);
		int paginas = this.retornaInteiro(valores[3]);

		Poesia poe = new Poesia(valores[0], anoDePublicacao, valores[2], paginas);
		return poe;
	}

	public Romance leRomance() { // método para a leitura do gênero romance

		String[] valores = new String[3];
		String[] nomeVal = { "Nome do Livro", "Ano de Publicação", "Autor", "Subgênero Literário" }; // Subgênero:
																										// Histórico,
																										// Suspense,
																										// Psicológico,
																										// etc
		valores = leValores(nomeVal);

		int anoDePublicacao = this.retornaInteiro(valores[1]);

		Romance rom = new Romance(valores[0], anoDePublicacao, valores[2], valores[3]);
		return rom;
	}

	public Quadrinhos leQuadrinhos() { // método para a leitura do gênero quadrinhos

		String[] valores = new String[3];
		String[] nomeVal = { "Nome do Livro", "Ano de Publicação", "Autor", "Peso" };
		valores = leValores(nomeVal);

		int anoDePublicacao = this.retornaInteiro(valores[1]);
		double peso = this.retornaDouble(valores[3]);

		Quadrinhos quad = new Quadrinhos(valores[0], anoDePublicacao, valores[2], peso);
		return quad;
	}

	private boolean intValido(String s) {
		try {
			Integer.parseInt(s); // para transformar em um String um inteiro
			return true;
		} catch (NumberFormatException e) { // erro caso não consiga transformar
			return false;
		}
	}

	public int retornaInteiro(String entrada) { // retorna um valor inteiro
		int numInt;
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	private boolean doubleValido(String s) { // para transformar em um String um double
		try {
			Double.parseDouble(s); //
			return true;
		} catch (NumberFormatException e) { //
			return false;
		}
	}

	public int retornaDouble(String entrada) { // retorna um valor double
		double numDouble;

		// O loop se repete até conseguir transformar
		while (!this.intValido(entrada)) {
			entrada = JOptionPane.showInputDialog(null, "Valor incorreto!\n\nDigite um número inteiro.");
		}
		return Integer.parseInt(entrada);
	}

	public void salvaLivros(ArrayList<Livro> livros) { // método para salvar os dados
		ObjectOutputStream outputStream = null;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream("livros.dados"));
			for (int i = 0; i < livros.size(); i++)
				outputStream.writeObject(livros.get(i));
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Impossível criar arquivo!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.flush();
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@SuppressWarnings("finally")
	public ArrayList<Livro> recuperaLivros() { // método para recuperar os dados salvos
		ArrayList<Livro> bibliotecaTemp = new ArrayList<Livro>();

		ObjectInputStream inputStream = null;

		try {
			inputStream = new ObjectInputStream(new FileInputStream("livros.dados"));
			Object obj = null;
			while ((obj = inputStream.readObject()) != null) {
				if (obj instanceof Livro) {
					bibliotecaTemp.add((Livro) obj);
				}
			}
		} catch (EOFException ex) { // when EOF is reached
			System.out.println("Fim de arquivo.");
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (FileNotFoundException ex) {
			JOptionPane.showMessageDialog(null, "Arquivo com livros inexistente!");
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally { // Close the ObjectInputStream
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (final IOException ex) {
				ex.printStackTrace();
			}
			return bibliotecaTemp;
		}
	}

	public void menuBiblioteca() { // inicializando o menu

		String menu = "";
		String entrada;
		int opc1, opc2;

		do {
			menu = "*Menu da Biblioteca*\n" + "Por gentileza escolha a opção desejada:\n" + "1. Entrar Livros\n"
					+ "2. Exibir Livros\n" + "3. Limpar Livros\n" + "4. Gravar Livros\n" + "5. Recuperar Livros\n"
					+ "9. Sair";
			entrada = JOptionPane.showInputDialog(menu + "\n\n");
			opc1 = this.retornaInteiro(entrada);

			switch (opc1) {
			case 1:// Entrada de dados
				menu = "Entrada de Livros\n" + "Opções:\n" + "1. Poesia\n" + "2. Romance\n" + "3. Quadrinhos\n";

				entrada = JOptionPane.showInputDialog(menu + "\n\n");
				opc2 = this.retornaInteiro(entrada);

				switch (opc2) {
				case 1:
					livros.add((Livro) lePoesia());
					break;
				case 2:
					livros.add((Livro) leRomance());
					break;
				case 3:
					livros.add((Livro) leQuadrinhos());
					break;
				default:
					JOptionPane.showMessageDialog(null, "Livro não disponível.");
				}

				break;
			case 2: // Exibir dados
				if (livros.size() == 0) {
					JOptionPane.showMessageDialog(null, "Primeiro, insira as informações do livro.");
					break;
				}
				String dados = "";
				for (int i = 0; i < livros.size(); i++) {
					dados += livros.get(i).toString() + "---------------\n";
				}
				JOptionPane.showMessageDialog(null, dados);
				break;
			case 3: // Limpar Dados
				if (livros.size() == 0) {
					JOptionPane.showMessageDialog(null, "Não há dados para limpar!");
					break;
				} else {
					livros.clear();
					JOptionPane.showMessageDialog(null, "Dados foram limpos!");
					break;
				}
			case 4: // Gravar Dados
				if (livros.size() == 0) {
					JOptionPane.showMessageDialog(null, "");
					break;
				} else {
					salvaLivros(livros);
					JOptionPane.showMessageDialog(null, "Dados salvos!");
					break;
				}
			case 5: // Recuperar Dados
				livros = recuperaLivros();
				if (livros.size() == 0) {
					JOptionPane.showMessageDialog(null, "Não há dados salvos!");
					break;
				} else {
					JOptionPane.showMessageDialog(null, "Os dados solicitados foram recuperados!");
					break;
				}
			case 9:
				JOptionPane.showMessageDialog(null, "Chegamos ao fim do aplicativo Biblioteca.");
				break;
			}
		} while (opc1 != 9);
	}

	public static void main(String[] args) { // método principal
		Biblioteca bibli = new Biblioteca();
		bibli.menuBiblioteca();
	}

}
