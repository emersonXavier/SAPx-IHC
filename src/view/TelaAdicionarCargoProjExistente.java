package view;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.Cargo;
import model.Projeto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.font.NumericShaper;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TelaAdicionarCargoProjExistente {

	JFrame frame;
	private JTextField txtQtdCargo;
	private JTextField txtHorasCargo;
	int idProj;
	Connection con;
	Statement st;
	Cargo cargo;
	String url= "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	private JTextField txtIdProj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionarCargoProjExistente window = new TelaAdicionarCargoProjExistente();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaAdicionarCargoProjExistente() {
		initialize();
	}
	public TelaAdicionarCargoProjExistente(int proj) {
		idProj = proj;
		initialize();	
		
	}	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 308, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		ArrayList<Cargo> arrayList = listaCargos();
		
		
		JComboBox cmbCargos = new JComboBox();
		cmbCargos.setBounds(89, 86, 160, 20);
		populaCmbBox(cmbCargos, arrayList);
		cmbCargos.setSelectedIndex(0);
		frame.getContentPane().add(cmbCargos);
		
		txtQtdCargo = new JTextField();
		txtQtdCargo.setColumns(10);
		txtQtdCargo.setBounds(89, 123, 63, 20);
		frame.getContentPane().add(txtQtdCargo);
		
		JLabel lblQuantidade = new JLabel("QTD:");
		lblQuantidade.setBounds(30, 126, 46, 14);
		frame.getContentPane().add(lblQuantidade);
		
		txtHorasCargo = new JTextField();
		txtHorasCargo.setColumns(10);
		txtHorasCargo.setBounds(89, 166, 63, 20);
		frame.getContentPane().add(txtHorasCargo);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(30, 169, 46, 14);
		frame.getContentPane().add(lblHoras);
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection(url);	
					st = con.createStatement();
					
					String sql = "INSERT INTO PLANEJARECURSOS (CodProj, CodCargo, QtdRecs, HorasTotais, ObsRecurso) "
							+ "VALUES (" + idProj + ", (SELECT A.CodCargo FROM CARGOS A WHERE NomeCargo='" 
							+ cmbCargos.getSelectedItem().toString() + "'), " + Integer.parseInt(txtQtdCargo.getText()) + 
							", " + Integer.parseInt(txtHorasCargo.getText()) + ", '')";
					
					st.execute(sql);
					
					ResultSet rs = st.executeQuery("SELECT * FROM PLANEJARECURSOS");
					System.out.println("\n\n\n");
					while(rs.next()) {
						System.out.println(rs.getInt("CodProj") + "\t" + rs.getInt("CodCargo") + "\t" 
						+ rs.getInt("QtdRecs") + "\t" + rs.getInt("HorasTotais") + "\t" + rs.getString("ObsRecurso") );
					}
					
				} catch(SQLException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Erro ao inserir cargo no projeto", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnAdicionar.setBounds(10, 227, 110, 23);
		frame.getContentPane().add(btnAdicionar);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(30, 89, 46, 14);
		frame.getContentPane().add(lblCargo);
		
		JButton btnFechar = new JButton("FECHAR");
		btnFechar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja fechar? Depois os cargos poderão ser adicionados manualmente", "Aviso!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
				else {
					
				}
			}
		});
		btnFechar.setBounds(172, 227, 110, 23);
		frame.getContentPane().add(btnFechar);
		
		JLabel lblCargosParaO = new JLabel("CARGOS PARA O PROJETO");
		lblCargosParaO.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCargosParaO.setBounds(30, 11, 252, 40);
		frame.getContentPane().add(lblCargosParaO);
		
		JLabel lblProjeto = new JLabel("Projeto:");
		lblProjeto.setBounds(30, 51, 46, 14);
		frame.getContentPane().add(lblProjeto);
		
		txtIdProj = new JTextField();
		txtIdProj.setEditable(false);
		txtIdProj.setBounds(89, 48, 63, 20);
		frame.getContentPane().add(txtIdProj);
		txtIdProj.setColumns(10);
		
		txtIdProj.setText(String.valueOf(idProj));
			
		
	}
	
	public ArrayList<Cargo> listaCargos(){
	ArrayList<Cargo> cargos = new ArrayList<>();
	try {
	con = DriverManager.getConnection(url);	
	st = con.createStatement();
	
	String sql = "SELECT * FROM CARGOS";
	
	ResultSet rs = st.executeQuery(sql);
	
	while(rs.next()) {
		cargo = new Cargo(rs.getString("NomeCargo"));
		cargos.add(cargo);
	}	
	
	} catch (SQLException e) {
		
	}
	return cargos;
	}
	
	public void populaCmbBox(JComboBox cmbBox, ArrayList<Cargo> arrayList) {
		for(int i=0; i<arrayList.size(); i++) {
			cmbBox.insertItemAt(arrayList.get(i).getNomeCargo(), i);
		}
	}
	
	public int NumeroProj() {
		int x = 0;
		try {
			con = DriverManager.getConnection(url);	
			st = con.createStatement();
			
			String sql = "SELECT TOP 1 A.CodProj FROM PROJETOS A ORDER BY A.CodProj DESC";
			
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				x = rs.getInt("CodProj");
			}
			
		} catch(SQLException e) {
			
		}
		
		return x;
	}
}
