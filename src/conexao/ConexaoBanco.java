package conexao;

import java.sql.Array;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.NClob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLClientInfoException;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.SQLXML;
import java.sql.Savepoint;
import java.sql.Statement;
import java.sql.Struct;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;
import view.TelaAdicionarProjeto;

public class ConexaoBanco {
	
	static String url = "jdbc:h2:mem:";
	static Connection con;
	static Statement st;
	Projeto projeto = new Projeto();
	
	public static void main(String[] args) throws SQLException {
		
		try {
			Connection con = DriverManager.getConnection(url);
			
				Statement st = con.createStatement();
				st.execute("CREATE TABLE PROJETOS(id_proj int primary key, nome_cli varchar(255), dt_ini date, dt_ter date, status varchar(255), "
						+ "vlr_proj float)");
				
				
				st.execute("INSERT INTO PROJETOS(id_proj, nome_cli, dt_ini, dt_ter, status, vlr_proj) VALUES(155, 'Consul', '2008-05-03', '2009-10-06', 'inicio', 5000)");
				st.execute("INSERT INTO PROJETOS(id_proj, nome_cli, dt_ini, dt_ter, status, vlr_proj) VALUES(191, 'Brastemp', '2008-09-06', '2009-05-06', 'inicio', 4000)");
				st.execute("INSERT INTO PROJETOS(id_proj, nome_cli, dt_ini, dt_ter, status, vlr_proj) VALUES(744, 'LG', '2010-09-07', '2012-10-06', 'inicio', 500000)");
				
				
				ResultSet rs = st.executeQuery("SELECT * from PROJETOS");
				
				while(rs.next()) {
					
					System.out.println("Id " + rs.getInt("id_proj") + " Name" + rs.getString("nome_cli") + "data Inicio " + rs.getDate("dt_ini") + " data termino " + rs.getDate("dt_ter")
					+ " status " + rs.getString("status") + " Valor " + rs.getFloat("vlr_proj"));
					
				}
					
			}catch (SQLException e) {
			Logger lgr = Logger.getLogger(ConexaoBanco.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public void insereBanco(String id_proj, String nome_cli, String status, String dt_ini, String dt_ter, double vlr_proj) throws SQLException {
		try {
		
		st.execute("INSERT INTO PROJETOS(id_proj, nome_cli, dt_ini, dt_ter, status, vlr_proj) VALUES(" + id_proj + ", '" + nome_cli + "', '" + 
		dt_ini + "', '" + dt_ter +"', '" + status + "', " + vlr_proj + ")");
		
		ResultSet rs = st.executeQuery("SELECT * from PROJETOS");
		
		while(rs.next()) {
			
			System.out.println("Id " + rs.getInt("id_proj") + " Name" + rs.getString("nome_cli") + "data Inicio " + rs.getDate("dt_ini") + " data termino " + rs.getDate("dt_ter")
			+ " status " + rs.getString("status") + " Valor " + rs.getFloat("vlr_proj"));
			
		}
			
	}catch (SQLException e) {
	Logger lgr = Logger.getLogger(ConexaoBanco.class.getName());
	lgr.log(Level.SEVERE, e.getMessage(), e);
}
}
			
	public void conectaBanco() throws SQLException {
		con = DriverManager.getConnection(url);
		st = con.createStatement();
		st.execute("CREATE TABLE PROJETOS(id_proj int primary key, nome_cli varchar(255), dt_ini date, dt_ter date, status varchar(255), "
				+ "vlr_proj float)");
	}
	
	public void testaBanco() throws SQLException{
		if (!con.isClosed()) System.out.println("OK!");
	}
	
	public void listaTabela() throws SQLException{

		
		
	}
	
	
	
}
