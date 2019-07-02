package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import org.h2.bnf.context.DbTableOrView;
import org.h2.command.ddl.TruncateTable;
import org.h2.table.Table;
import org.h2.table.TableBase;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.JobAttributes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;

import model.Projeto;
import model.ProjetoTabela;

import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

/* org.h2.jdbc.JdbcSQLException: Column "codProj" not found [42122-197] */

public class TelaPesquisarProjeto {

	private JFrame frmPesquisa;
	private JTable table;
	ProjetoTabela proj;
	static String url = "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	Connection con;
	Statement st;
	JTextField txtPesquisaDados = new JTextField();
	JDateChooser txtDataPesquisaIni = new JDateChooser();
	JDateChooser txtDataPesquisaTer = new JDateChooser();
	JComboBox cmbPesquisa = new JComboBox();
	JLabel lblCmbPesquisa = new JLabel("Dado para Pesquisa:");
	JLabel lblDataTrmino = new JLabel("Data máxima:");
	String arg1, arg2, dadoPesquisa, dadoPesquisa2;
	JButton btnAddCargo = new JButton("Adicionar Cargos");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPesquisarProjeto window = new TelaPesquisarProjeto();
					window.frmPesquisa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPesquisarProjeto() {
		initialize();
		mostraProjeto();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPesquisa = new JFrame();
		frmPesquisa.setTitle("SAPx");
		frmPesquisa.setBounds(100, 100, 1000, 496);
		frmPesquisa.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPesquisa.getContentPane().setLayout(null);
		lblDataTrmino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		lblDataTrmino.setVisible(false);
		
		JLabel lblFiltroParaPesquisa = new JLabel("Filtro para pesquisa:");
		lblFiltroParaPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFiltroParaPesquisa.setBounds(10, 54, 170, 18);
		frmPesquisa.getContentPane().add(lblFiltroParaPesquisa);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		comboBox.setBounds(190, 53, 190, 20);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione o filtro desejado.", "Numero Projeto", "Nome Cliente", "Status", "Data de in\u00EDcio", "Data de termino", "Intervalo entre datas de in\u00EDcio", "Intervalo entre datas de fim"}));
		frmPesquisa.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				switch (comboBox.getSelectedIndex()) {
				case 1:
					lblCmbPesquisa.setText("Numero do projeto:");
					txtPesquisaDados.setVisible(true);
					txtDataPesquisaIni.setVisible(false);
					txtDataPesquisaTer.setVisible(false);
					cmbPesquisa.setVisible(false);
					lblDataTrmino.setVisible(false);
					arg1= "CodProj";
					arg2= null;
					break;
				case 2:
					lblCmbPesquisa.setText("Nome do cliente:");
					txtPesquisaDados.setVisible(true);
					txtDataPesquisaIni.setVisible(false);
					txtDataPesquisaTer.setVisible(false);
					cmbPesquisa.setVisible(false);
					lblDataTrmino.setVisible(false);
					arg1= "NomeCliente";
					arg2= null;
					break;
				case 3: 
					lblCmbPesquisa.setText("Status:");
					txtPesquisaDados.setVisible(false);
					txtDataPesquisaIni.setVisible(false);
					txtDataPesquisaTer.setVisible(false);
					cmbPesquisa.setVisible(true);
					lblDataTrmino.setVisible(false);
					arg1= "ObsStatus";
					arg2= null;
					break;
				case 4:
					lblCmbPesquisa.setText("Data de início:");
					txtPesquisaDados.setVisible(false);
					txtDataPesquisaIni.setVisible(true);
					txtDataPesquisaTer.setVisible(false);
					cmbPesquisa.setVisible(false);
					lblDataTrmino.setVisible(false);
					arg1= "DataIni";
					arg2= null;
					break;
				case 5:
					lblCmbPesquisa.setText("Data de término:");
					txtPesquisaDados.setVisible(false);
					txtDataPesquisaIni.setVisible(true);
					txtDataPesquisaTer.setVisible(false);
					cmbPesquisa.setVisible(false);
					lblDataTrmino.setVisible(false);
					arg1= "DataFim";
					arg2= null;
					break;
				case 6:
					lblCmbPesquisa.setText("Data mínima:");
					txtPesquisaDados.setVisible(false);
					txtDataPesquisaIni.setVisible(true);
					txtDataPesquisaTer.setVisible(true);
					cmbPesquisa.setVisible(false);
					arg1= "DataIni";
					arg2= "DataIni";
					lblDataTrmino.setVisible(true);
					break;
				case 7:
					lblCmbPesquisa.setText("Data mínima:");
					txtPesquisaDados.setVisible(false);
					txtDataPesquisaIni.setVisible(true);
					txtDataPesquisaTer.setVisible(true);
					cmbPesquisa.setVisible(false);
					arg1= "DataFim";
					arg2= "DataFim";
					lblDataTrmino.setVisible(true);
					break;							
				
				default: 
					txtPesquisaDados.setVisible(true);
					txtDataPesquisaIni.setVisible(false);
					txtDataPesquisaTer.setVisible(false);
					cmbPesquisa.setVisible(false);
					lblDataTrmino.setVisible(false);
				}
			}
		});
		lblCmbPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCmbPesquisa.setBounds(10, 85, 170, 18);
		frmPesquisa.getContentPane().add(lblCmbPesquisa);
		txtPesquisaDados.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		txtPesquisaDados.setBounds(190, 84, 190, 20);
		frmPesquisa.getContentPane().add(txtPesquisaDados);
		txtPesquisaDados.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 129, 959, 296);
		frmPesquisa.getContentPane().add(scrollPane);

			
		JLabel lblPesquisarProjeto = new JLabel("Pesquisar Projeto");
		lblPesquisarProjeto.setBounds(370, 0, 264, 42);
		lblPesquisarProjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPesquisarProjeto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		frmPesquisa.getContentPane().add(lblPesquisarProjeto);
		txtDataPesquisaIni.setBounds(190, 84, 190, 20);
		txtDataPesquisaIni.setVisible(false);
		frmPesquisa.getContentPane().add(txtDataPesquisaIni);
		txtDataPesquisaTer.setBounds(500, 84, 170, 20);
		txtDataPesquisaTer.setVisible(false);
		frmPesquisa.getContentPane().add(txtDataPesquisaTer);
		
		JButton btnAlterar = new JButton("Alterar Projeto");
		btnAlterar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAlterar.setBounds(834, 52, 135, 23);
		btnAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAlterarProjeto telaAltera;
				try {
					telaAltera = new TelaAlterarProjeto(listaProjeto().get(table.getSelectedRow()));
					telaAltera.frmAlterProj.setVisible(true);
					frmPesquisa.dispose();
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		frmPesquisa.getContentPane().add(btnAlterar);
		btnAlterar.setEnabled(false);
		
		
		JButton btnExcluir = new JButton("Excluir Projeto");
		btnExcluir.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnExcluir.setBounds(689, 52, 135, 23);
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (JOptionPane.showConfirmDialog(btnExcluir, "Deseja excluir o Projeto?", "Excluir Projeto", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					apagaLinha((table.getValueAt(table.getSelectedRow(), 0)).toString());

					JOptionPane.showMessageDialog(null, "Projeto excluído!");
				}
				else {
					
				}
				btnExcluir.setEnabled(false);
				btnAlterar.setEnabled(false);
			}			
		});
		frmPesquisa.getContentPane().add(btnExcluir);
		btnExcluir.setEnabled(false);
		
		table = new JTable() {
			
		public boolean isCellEditable(int row, int column){
			return false;
			}
		
		
		};
		table.setModel(new DefaultTableModel(
				new Object[][] {}, 
				new String[] {
						"Numero Projeto", "Nome Cliente", "CNPJ", "Status", "Data Inicio", "Data Fim", "Valor Projeto"
				}
				
				
				));
		scrollPane.setViewportView(table);
		
		JButton btnPesquisar = new JButton("Pesquisar");
		btnPesquisar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnPesquisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboBox.getSelectedIndex() == 0) {
					JOptionPane.showMessageDialog(null, "Selecione um argumento para pesquisa", "Erro ao pesquisar projeto", JOptionPane.ERROR_MESSAGE);
				}
				else {
				
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				
				
				if(txtPesquisaDados.isVisible()) dadoPesquisa= txtPesquisaDados.getText();
				if(cmbPesquisa.isVisible()) dadoPesquisa= cmbPesquisa.getItemAt(cmbPesquisa.getSelectedIndex()).toString();
				if(txtDataPesquisaIni.isVisible()) dadoPesquisa= dateFormat.format(txtDataPesquisaIni.getDate());
				if(txtDataPesquisaTer.isVisible()) dadoPesquisa2= dateFormat.format(txtDataPesquisaTer.getDate());
				
				apagaTabela();
				pesquisaProjeto(arg1, arg2, dadoPesquisa, dadoPesquisa2);
				mostraProjetoPesquisa();
				dadoPesquisa= null;
				dadoPesquisa2= null;
			
				}
			}
		});
		btnPesquisar.setBounds(400, 53, 130, 23);
		frmPesquisa.getContentPane().add(btnPesquisar);
		cmbPesquisa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		cmbPesquisa.setModel(new DefaultComboBoxModel(new String[] {"Em Negociacao", "Contratado", "Removido"}));
		cmbPesquisa.setBounds(190, 84, 190, 20);
		frmPesquisa.getContentPane().add(cmbPesquisa);
		
		
		lblDataTrmino.setBounds(400, 85, 89, 18);
		frmPesquisa.getContentPane().add(lblDataTrmino);
		
		JButton btnResetTable = new JButton("Resetar");
		btnResetTable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnResetTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				apagaTabela();
				mostraProjeto();
				btnExcluir.setEnabled(false);
				btnAlterar.setEnabled(false);
				btnAddCargo.setEnabled(false);
				
			}
		});
		btnResetTable.setBounds(834, 16, 135, 23);
		frmPesquisa.getContentPane().add(btnResetTable);
		
		JButton btnInserirCliente = new JButton("Inserir Cliente");
		btnInserirCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdicionarCliente telaAddCliente;
				try {
					telaAddCliente = new TelaAdicionarCliente();
					telaAddCliente.main(null);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnInserirCliente.setBounds(834, 85, 135, 23);
		frmPesquisa.getContentPane().add(btnInserirCliente);
		
		btnAddCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaAdicionarCargoProjExistente telaAddCargo = new TelaAdicionarCargoProjExistente(Integer.parseInt(table.getValueAt((table.getSelectedRow()), 0).toString()));
				
				telaAddCargo.frame.setVisible(true);
			}
		});
		btnAddCargo.setBounds(689, 85, 135, 23);
		frmPesquisa.getContentPane().add(btnAddCargo);
		btnAddCargo.setEnabled(false);
		
		JButton btnNewButton = new JButton("Add cargos sistema");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TelaCargoNovo telaCargoSistema = new TelaCargoNovo();
				TelaCargoNovo.main(null);
			}
		});
		btnNewButton.setBounds(689, 16, 135, 23);
		frmPesquisa.getContentPane().add(btnNewButton);
		
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				btnExcluir.setEnabled(true);
				btnAlterar.setEnabled(true);
				btnAddCargo.setEnabled(true);
			}
		});
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!(table.isFocusable())) table.getSelectionModel().clearSelection();				
			}
		});

		
	}
	
	public ArrayList<ProjetoTabela> listaProjeto(){ 
		
	ArrayList<ProjetoTabela> arrayList = new ArrayList<>();
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	try {
		con = DriverManager.getConnection(url);	
		st = con.createStatement();
		
		String query = "SELECT A.CodProj, B.NomeCliente, B.CnpjCliente, C.ObsStatus, A.HorasTotais, A.DataIni, A.DataFim, A.CustoProj, A.ObsProj" +
		" FROM PROJETOS A, CLIENTES B, STATUS C" +
		" WHERE (A.CodCliente=B.CodCliente) AND (A.CodStatus=C.CodStatus) ORDER BY A.CodProj";
		
		ResultSet rs = st.executeQuery(query);
		
		
		while(rs.next()){
				proj = new ProjetoTabela(rs.getInt("codProj"), rs.getString("nomeCliente"),
					rs.getString("cnpjCliente"), rs.getString("obsStatus"), rs.getInt("horasTotais"),
					dateFormat.format(rs.getDate("dataIni")), dateFormat.format(rs.getDate("dataFim")),
					rs.getInt("custoProj"), rs.getString("obsProj"));
			arrayList.add(proj);
		}
	
		
	} catch (Exception e) {
		JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO AO MOSTRAR TELA", JOptionPane.ERROR_MESSAGE);
	}	
	return arrayList;
	
	}
	
	public void mostraProjeto(){
		
		ArrayList<ProjetoTabela> projetoArrayList = listaProjeto();
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object [] coluna = new Object[8];
		for(int i=0; i<projetoArrayList.size(); i++) {
			coluna[0]=projetoArrayList.get(i).getCodProj();
			coluna[1]=projetoArrayList.get(i).getNomeCliente();
			coluna[2]=projetoArrayList.get(i).getCnpjCliente();
			coluna[3]=projetoArrayList.get(i).getObsStatus();
			//coluna[4]=projetoArrayList.get(i).getHorasTotais();
			coluna[4]=projetoArrayList.get(i).getDataIni();
			coluna[5]=projetoArrayList.get(i).getDataFim();
			coluna[6]=projetoArrayList.get(i).getCustoProj();
			model.addRow(coluna);
		}
		
	}
	
	public void apagaTabela() {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int x = model.getRowCount();
		for(int i= x-1; i>=0; i--) {
			model.removeRow(i);			
		}
		
	}
	
	public void apagaLinha(String CodProj) {
		try {
			con = DriverManager.getConnection(url);	
			st = con.createStatement();
			
			String query = "DELETE FROM PLANEJARECURSOS WHERE CodProj=" + CodProj;
			
			int deletedRows = st.executeUpdate(query);
			
			query = "DELETE FROM CONTRATOS WHERE CodProj=" + CodProj;
			
			deletedRows = st.executeUpdate(query);
			
			query = "DELETE FROM PROJETOS WHERE CodProj=" + CodProj;
			
			deletedRows = st.executeUpdate(query);
			
			apagaTabela();
			mostraProjeto();
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO AO EXCLUIR", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	/* PRESTAR ATENÇÃO AQUI, VOU MUDAR PARAMETROS NO BD HARDCODED */
	public ArrayList<ProjetoTabela> pesquisaProjeto(String arg1, String arg2, String dadoPesquisa, String dadoPesquisa2) {
		
		proj = null;
		ArrayList<ProjetoTabela> arrayList = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String query;
		
		
		try {
			con = DriverManager.getConnection(url);
			st = con.createStatement();
			
			if (arg2==null) {
			
				
			
				query = "SELECT A.CodProj, B.NomeCliente, B.CnpjCliente, C.ObsStatus, A.HorasTotais, A.DataIni, A.DataFim, A.CustoProj, A.ObsProj" +
				" FROM PROJETOS A, CLIENTES B, STATUS C" +
				" WHERE (A.CodCliente=B.CodCliente) AND (A.CodStatus=C.CodStatus) AND "+ arg1 + "= '" + dadoPesquisa + "'";  
				
			} else {
				
				query = "SELECT A.CodProj, B.NomeCliente, B.CnpjCliente, C.ObsStatus, A.HorasTotais, A.DataIni, A.DataFim, A.CustoProj, A.ObsProj" +
						" FROM PROJETOS A, CLIENTES B, STATUS C" +
						" WHERE (A.CodCliente=B.CodCliente) AND (A.CodStatus=C.CodStatus) AND " 
						+ arg1 + ">= '" + dadoPesquisa + "' AND " + arg2 + " <= '"	+ dadoPesquisa2 + "'";
			}
			
			ResultSet rs = st.executeQuery(query);
			
			
			while(rs.next()){
				proj = new ProjetoTabela(rs.getInt("codProj"), rs.getString("nomeCliente"),
					rs.getString("cnpjCliente"), rs.getString("obsStatus"), rs.getInt("horasTotais"),
					dateFormat.format(rs.getDate("dataIni")), dateFormat.format(rs.getDate("dataFim")),
					rs.getInt("custoProj"), rs.getString("obsProj"));
			arrayList.add(proj);
		}
		
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO AO CONSULTAR", JOptionPane.WARNING_MESSAGE);
		}
		
		return arrayList;
	
	}
	
	public void mostraProjetoPesquisa(){
		
				
		ArrayList<ProjetoTabela> projetoArrayList = pesquisaProjeto(arg1, arg2, dadoPesquisa, dadoPesquisa2);
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		Object [] coluna = new Object[7];
		for(int i=0; i<projetoArrayList.size(); i++) {
			coluna[0]=projetoArrayList.get(i).getCodProj();
			coluna[1]=projetoArrayList.get(i).getNomeCliente();
			coluna[2]=projetoArrayList.get(i).getCnpjCliente();
			coluna[3]=projetoArrayList.get(i).getObsStatus();
			//coluna[4]=projetoArrayList.get(i).getHorasTotais();
			coluna[4]=projetoArrayList.get(i).getDataIni();
			coluna[5]=projetoArrayList.get(i).getDataFim();
			coluna[6]=projetoArrayList.get(i).getCustoProj();
			model.addRow(coluna);
		}
		
	}
}
