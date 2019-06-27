package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.toedter.calendar.JMonthChooser;

import conexao.ConexaoBanco;

import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JCalendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStream;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.html.parser.Parser;

import org.h2.table.Table;
import org.h2.tools.RunScript;

import model.Projeto;
import model.ProjetoTableModel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Panel;
import javax.swing.ImageIcon;
import java.awt.Label;
import view.TelaAlterarProjeto;
import java.awt.Font;
import javax.swing.SwingConstants;

import view.TelaPesquisarProjeto;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class TelaOperacional {

	JFrame frmMain;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	ProjetoTableModel tableModelOper = new ProjetoTableModel();	
	TelaAdicionarProjeto viewTelaAdicionar;
	TelaAlterarProjeto viewTelaAlterar;
	ConexaoBanco bdProjeto = new ConexaoBanco();
	Projeto proj;
	static String url = "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	Connection con;
	Statement st;

	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
						Connection con = DriverManager.getConnection(url);		
						
							Statement st = con.createStatement();
					/*
					 * st.
					 * execute("CREATE TABLE PROJETOS(id_proj int primary key, nome_cli varchar(30), cnpj_cli varchar(30), dt_ini date, dt_ter date, status varchar(30), "
					 * +
					 * "vlr_proj float, qtdGer int, qtdCoord int, qtdArq int, qtdProgSr int, qtdProgPl int, qtdProgJr int, qtdDba int)"
					 * );
					 * 
					 * st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(191, 'Brastemp', '12.312.312/0001-23', '2008-09-06', '2009-05-06', 'Contratado', 4000, 1, 1, 1, 1, 1, 1, 1)"
					 * ); st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(149, 'LG', '16.534.352/0001-89', '2010-09-06', '2018-05-06', 'Contratado', 5000, 1, 1, 1, 1, 1, 1, 1)"
					 * ); st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(735, 'Honda', '14.313.090/0001-33', '2013-09-06', '2019-11-06', 'Contratado', 96000, 1, 1, 1, 1, 1, 1, 1)"
					 * ); st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(443, 'Brastemp', '12.312.312/0001-23', '2016-03-16', '2021-08-21', 'Contratado', 134000, 1, 1, 1, 1, 1, 1, 1)"
					 * ); st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(444, 'Brastemp', '12.312.312/0001-23', '2017-04-21', '2020-08-21', 'Em negociação', 242000, 1, 1, 1, 1, 1, 1, 1)"
					 * ); st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(452, 'Amazon', '11.443.190/0001-81', '2018-02-10', '2025-09-20', 'Contratado', 13684000, 1, 1, 1, 1, 1, 1, 1)"
					 * ); st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(978, 'Acer', '10.112.881/0001-99', '2017-02-21', '2023-11-30', 'Em negociação', 280000, 1, 1, 1, 1, 1, 1, 1)"
					 * ); st.
					 * execute("INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj"
					 * +
					 * ", qtdGer, qtdCoord, qtdArq, qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES(732, 'Sony', '15.421.872/0002-34', '2015-07-26', '2020-04-20', 'Contratado', 376000, 1, 1, 1, 1, 1, 1, 1)"
					 * );
					 */
							
							RunScript.execute(con, new FileReader("lib/create-tables.sql"));			
							
							
							/*if(!(con.isClosed())) JOptionPane.showMessageDialog(null, "OK!");*/					
					TelaOperacional window = new TelaOperacional();
					window.frmMain.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public TelaOperacional(){
		initialize();
	}
	
	public ArrayList<Projeto> listaProjeto(){ 
	
		ArrayList<Projeto> arrayList = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			con = DriverManager.getConnection(url);	
			st = con.createStatement();
		
			String query = "SELECT * FROM PROJETOS";
		
			ResultSet rs = st.executeQuery(query);
		
			while(rs.next()){
				proj = new Projeto(rs.getInt("codProj"), rs.getString("nomeProj"),
						rs.getInt("codCliente"), rs.getInt("codStatus"), rs.getInt("horasTotais"),
						dateFormat.format(rs.getDate("dataIni")), dateFormat.format(rs.getDate("dataFim")),
						rs.getDouble("custoProj"), rs.getString("obsProj"));
				
				arrayList.add(proj);
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
		
		return arrayList;
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMain = new JFrame();
		frmMain.setTitle("SAPx");
		frmMain.setBounds(100, 100, 470, 271);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMain.getContentPane().setLayout(null);
		
		JButton btnAdicionarProjeto = new JButton("Incluir projeto");
		btnAdicionarProjeto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAdicionarProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					viewTelaAdicionar = new TelaAdicionarProjeto();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
				viewTelaAdicionar.frmAddProj.setVisible(true);
				
			}
		});
		btnAdicionarProjeto.setBounds(10, 70, 130, 50);
		frmMain.getContentPane().add(btnAdicionarProjeto);
		
		JButton btnPesquisar = new JButton("Pesquisar...");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaPesquisarProjeto telaPesquisar = new TelaPesquisarProjeto();
				telaPesquisar.main(null);
			}
		});
		btnPesquisar.setBounds(160, 70, 130, 50);
		frmMain.getContentPane().add(btnPesquisar);
		
		JButton btnNewButton = new JButton("Relat\u00F3rios");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TelaRelatorios tela = new TelaRelatorios();
				tela.main(null);
			}
		});
		btnNewButton.setBounds(310, 70, 130, 50);
		frmMain.getContentPane().add(btnNewButton);
		
		JButton btnSair = new JButton("Encerrar");
		btnSair.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja sair?", "Encerrar Sessão", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					frmMain.dispose();
				}
				else {
					
				}
			}
		});
		btnSair.setBounds(310, 192, 130, 29);
		frmMain.getContentPane().add(btnSair);
		
		JLabel lblIncioSapx = new JLabel("In\u00EDcio - SAPx");
		lblIncioSapx.setHorizontalAlignment(SwingConstants.CENTER);
		lblIncioSapx.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblIncioSapx.setBounds(100, 10, 264, 42);
		frmMain.getContentPane().add(lblIncioSapx);
	}
	
	public final JFrame getMainFrame() {
		return frmMain;
	}
}
