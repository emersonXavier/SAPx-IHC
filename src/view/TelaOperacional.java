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
							
							RunScript.execute(con, new FileReader("create-tables.sql"));
				
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
