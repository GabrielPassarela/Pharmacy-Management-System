package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.sql.DataSource;

public class FarmaciaDAO {
	private DataSource dataSource;
	
	public FarmaciaDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	
	

	public boolean inserirRemedio(String descricao, String nome, Double preco, String tarja, LocalDate dataVencimento) {
	    Connection conexao = null;
	    PreparedStatement statement = null;
	    int resultado;

	    try {
	        conexao = dataSource.getConnection();
	        String sql = "insert into farmacia(descricao, nome, preco, tarja, data_vencimento) values (?,?,?,?,?);";
	        statement = conexao.prepareStatement(sql);
	        statement.setString(1, descricao);
	        statement.setString(2, nome);
	        statement.setDouble(3, preco);
	        statement.setString(4, tarja);
	        statement.setDate(5, java.sql.Date.valueOf(dataVencimento));

	        resultado = statement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        resultado = 0;
	    } finally {
	        fecharConexao(conexao, statement, null);
	    }
	    return resultado == 1;
	    }
	
	    public ArrayList<Remedio>consultarRemedio(){
		ArrayList<Remedio> listaRemedios = new ArrayList<>();
		Connection conexao = null;
		PreparedStatement statement = null;
		ResultSet resultado = null;
		
		try {
			conexao = dataSource.getConnection();
			String sql = "select * from farmacia;";
			statement = conexao.prepareStatement(sql);
			resultado = statement.executeQuery();
			
			while(resultado.next()) {
				int id = resultado.getInt("id");
				String descricao = resultado.getString("descricao");
				String nome = resultado.getString("nome");
				Double preco = resultado.getDouble("preco");
				String tarja = resultado.getString("tarja");
				Date dataVencimentoSql = resultado.getDate("data_vencimento");
				LocalDate dataVencimento = dataVencimentoSql.toLocalDate();				
				
				Remedio remedio = new Remedio (id, nome, preco, dataVencimento, tarja, descricao);
				listaRemedios.add(remedio);
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		finally {
			fecharConexao(conexao,statement,resultado);

		}
		return listaRemedios;
		
	}
	    
	    public Remedio procurarRemedio(int id) {
			Remedio remedio= null;
			Connection conexao = null;
			PreparedStatement statement = null;
			ResultSet resultado = null;
			
			try {
				conexao = dataSource.getConnection();
				String sql = "select * from farmacia where id = ?;";
				statement = conexao.prepareStatement(sql);
				statement.setInt(1, id);
				resultado = statement.executeQuery();
				
				if(resultado.next()) {
					String descricao = resultado.getString("descricao");
					String nome = resultado.getString("nome");
					Double preco = resultado.getDouble("preco");
					String tarja = resultado.getString("tarja");
					Date dataVencimentoSql = resultado.getDate("data_vencimento");
					LocalDate dataVencimento = dataVencimentoSql.toLocalDate();				

					remedio = new Remedio(id, nome, preco, dataVencimento, tarja, descricao);
				}	
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			finally {
				fecharConexao(conexao , statement, resultado);
				
			}
			return remedio;
			
			
		}
	    
	    public boolean excluirRemedio(int id) {
			Connection conexao = null;
			PreparedStatement statement = null;
			int resultado;
			
			try {
				conexao = dataSource.getConnection();
				String sql = "delete from farmacia where id= ?;";
				statement = conexao.prepareStatement(sql);
				statement.setInt(1, id);
				resultado = statement.executeUpdate();
				
			}
			catch(SQLException e){
				e.printStackTrace();
				resultado=0;
				
			}
			finally {
				fecharConexao(conexao,statement, null);
				
			}
			return resultado == 1;
			
		}
	    
	    public boolean modificarRemedio(int id, String nome, Double preco, String tarja, LocalDate dataVencimento, String descricao) {
			Connection conexao = null;
			PreparedStatement statement = null;
			int resultado;
			
			try {
				conexao = dataSource.getConnection();
				String sql = "update farmacia set nome = ?, preco = ?, tarja = ?, data_vencimento = ?, descricao = ? where id = ?;";
				statement = conexao.prepareStatement(sql);
				statement.setString(1, nome);
				statement.setDouble(2, preco);
				statement.setString(3, tarja);
				statement.setDate(4,  java.sql.Date.valueOf(dataVencimento));
				statement.setString(5, descricao);
				statement.setInt(6,id);

				resultado =  statement.executeUpdate();
				
			}
			catch(SQLException e){
				e.printStackTrace();
				resultado=0;
				
			}
			finally {
				fecharConexao(conexao,statement, null);
				
			}
			return resultado == 1;
		}
	    
		

		
	    
	    
	    private void fecharConexao(Connection conexao, PreparedStatement statement, ResultSet resultado) {
			try {
				if(conexao !=null)
					conexao.close();
				if(statement != null)
					statement.close();
				if(resultado != null)
					resultado.close();
				
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
	}

