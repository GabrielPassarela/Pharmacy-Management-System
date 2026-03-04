package controller;

import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.FarmaciaDAO;
import model.Remedio;

@WebServlet("/FarmaciaController")
public class FarmaciaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private FarmaciaDAO fDAO;

	@Resource(name="bancoFarmacia")
	private DataSource dataSource;

	@Override
	public void init() throws ServletException {
		fDAO = new FarmaciaDAO(dataSource);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacao = request.getParameter("operacao");

		if (operacao == null) {
			response.sendRedirect("index.html");
			return;
		}

		operacao = operacao.toLowerCase();

		switch(operacao) {
			case "listar":
				listarRemedios(request, response);
				break;
			case "buscar":
				buscarRemedio(request, response);
				break;
			case "vencimento":
				verificarRemedios(request, response);
				break;
			case "tarja":
				verificarReceita(request, response);
				break;
			case "desconto":
				verificarDesconto(request, response);
				break;
			default:
				System.out.println("Erro! Operação não encontrada.");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacao = request.getParameter("operacao");

		if (operacao == null) {
			response.sendRedirect("index.html");
			return;
		}

		operacao = operacao.toLowerCase();

		switch(operacao) {
			case "cadastrar":
				cadastrarRemedio(request, response);
				break;
			case "remover":
				removerRemedio(request, response);
				break;
			case "atualizar":
				atualizarRemedio(request, response);
				break;
			default:
				System.out.println("Erro! Operação não encontrada.");
		}
	}

	private void cadastrarRemedio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String descricao = request.getParameter("descricao");
		String nome = request.getParameter("nome");
		double preco = Double.parseDouble(request.getParameter("preco"));
		String tarja = request.getParameter("tarja");
		String dataVencimentoStr = request.getParameter("dataVencimento");

		LocalDate dataVencimento = null;
		try {
			dataVencimento = Remedio.parseDate(dataVencimentoStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		boolean inseriu = fDAO.inserirRemedio(descricao, nome, preco, tarja, dataVencimento);

		request.setAttribute("status", inseriu);
		request.setAttribute("operacao", "cadastrada");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/status.jsp");
		dispatcher.forward(request, response);
	}

	private void listarRemedios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Remedio> listarRemedios = fDAO.consultarRemedio();

		request.setAttribute("bancoFarmacia", listarRemedios);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/exibirRemedios.jsp");
		dispatcher.forward(request, response);
	}

	private void buscarRemedio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Remedio bancoFarmacia = fDAO.procurarRemedio(id);
		request.setAttribute("remedio", bancoFarmacia);

		String simbolo = request.getParameter("simbolo");
		String caminho;
		if (simbolo.equals("lixeira"))
			caminho = "/confirmaRemocao.jsp";
		else
			caminho = "/atualizaRemedio.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(caminho);
		dispatcher.forward(request, response);
	}

	private void removerRemedio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		boolean excluiu = fDAO.excluirRemedio(id);

		request.setAttribute("status", excluiu);
		request.setAttribute("operacao", "removida");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/status.jsp");
		dispatcher.forward(request, response);
	}

	private void atualizarRemedio(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		double preco = Double.parseDouble(request.getParameter("preco"));
		String tarja = request.getParameter("tarja");
		String dataVencimentoStr = request.getParameter("dataVencimento");
		LocalDate dataVencimento = null;
		try {
			dataVencimento = Remedio.parseDate(dataVencimentoStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String descricao = request.getParameter("descricao");

		boolean atualizou = fDAO.modificarRemedio(id, nome, preco, tarja, dataVencimento, descricao);

		request.setAttribute("status", atualizou);
		request.setAttribute("operacao", "atualizada");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/status.jsp");
		dispatcher.forward(request, response);
	}

	private void verificarRemedios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Remedio remedio = fDAO.procurarRemedio(id);
		String status = remedio.verificarVencimento();

		request.setAttribute("remedio", remedio);
		request.setAttribute("status", status);

		String simbolo = request.getParameter("simbolo");
		String caminho;
		if (simbolo.equals("lixeira")) {
			caminho = "/remediosVencidos.jsp";
		} else {
			caminho = "/atualizaRemedio.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(caminho);
		dispatcher.forward(request, response);
	}

	private void verificarReceita(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Remedio remedio = fDAO.procurarRemedio(id);
		String status = remedio.verificarTarja();

		request.setAttribute("remedio", remedio);
		request.setAttribute("status", status);

		String simbolo = request.getParameter("simbolo");
		String caminho;
		if (simbolo.equals("lixeira")) {
			caminho = "/remediosTarja.jsp";
		} else {
			caminho = "/atualizaRemedio.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(caminho);
		dispatcher.forward(request, response);
	}

	private void verificarDesconto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Remedio remedio = fDAO.procurarRemedio(id);
		Double status = remedio.calcularDesconto();

		request.setAttribute("remedio", remedio);
		request.setAttribute("status", status);

		String simbolo = request.getParameter("simbolo");
		String caminho;
		if (simbolo.equals("lixeira")) {
			caminho = "/remedioDesconto.jsp";
		} else {
			caminho = "/atualizaRemedio.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(caminho);
		dispatcher.forward(request, response);
	}
}