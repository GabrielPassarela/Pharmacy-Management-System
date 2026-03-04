package model;

import java.text.ParseException;
import java.time.LocalDate;

public class Remedio {
	private int id;
	private String nome;
	private Double preco;
	private LocalDate dataVencimento;
	private String tarja;
	private String descricao;

	public Remedio(int id, String nome, Double preco, LocalDate dataVencimento, String tarja, String descricao) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.dataVencimento = dataVencimento;
		this.tarja = tarja;
		this.descricao = descricao;
	}

	public String verificarVencimento() {
		LocalDate hoje = LocalDate.now();

		if (dataVencimento.isBefore(hoje)) {
			return "O remédio está vencido.";
		} else if (dataVencimento.isEqual(hoje)) {
			return "O remédio vence hoje!";
		} else {
			return "O remédio ainda é válido.";
		}
	}

	public String verificarTarja() {
		if (tarja.equals("tarja Branca") || tarja.equals("tarja Amarela")) {
			return "Remédio não precisa de receita";
		} else if (tarja.equals("tarja Vermelha") || tarja.equals("tarja Preta") || tarja.equals("Tarja Amarela/Vermelha/Preta")) {
			return "Remédio precisa de receita";
		} else {
			return "Informação de tarja inválida";
		}
	}

	public Double calcularDesconto() {
		if (preco >= 100.0) {
			return preco * 0.90;
		} else if (preco >= 50.0 && preco < 100) {
			return preco * 0.95;
		} else {
			return preco;
		}
	}

	public static LocalDate parseDate(String dateStr) throws ParseException {
		return LocalDate.parse(dateStr);
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }
	public Double getPreco() { return preco; }
	public void setPreco(Double preco) { this.preco = preco; }
	public LocalDate getDataVencimento() { return dataVencimento; }
	public void setDataVencimento(LocalDate dataVencimento) { this.dataVencimento = dataVencimento; }
	public String getTarja() { return tarja; }
	public void setTarja(String tarja) { this.tarja = tarja; }
	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }
}