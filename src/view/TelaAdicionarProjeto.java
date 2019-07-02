package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;

import model.Cliente;
import model.Projeto;
import model.ProjetoTabela;
import model.ProjetoTableModel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.channels.NetworkChannel;
import java.security.acl.LastOwnerException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;

import view.TelaOperacional;
import com.toedter.calendar.JDateChooser;

import conexao.ConexaoBanco;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.SoftBevelBorder;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import java.awt.Window.Type;
import javax.swing.JDesktopPane;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;

public class TelaAdicionarProjeto {

	JFrame frmAddProj;
	TelaOperacional telaOperacional = new TelaOperacional();

	static ProjetoTableModel tableModel = new ProjetoTableModel();
	private JTextField txtHoras;
	private JTextField txtVlrProj;
	JDateChooser dtInicio, dtTermino;
	ConexaoBanco conexaoBD = new ConexaoBanco();
	JComboBox cmbStatus;
	static Projeto proj = new Projeto();
	Cliente cliente = new Cliente();
	static String url= "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	Connection con;
	Statement st;
	private MaskFormatter mskCNPJ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionarProjeto window = new TelaAdicionarProjeto();
					window.frmAddProj.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws ParseException
	 */
	public TelaAdicionarProjeto() throws ParseException {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws ParseException
	 */
	private void initialize() throws ParseException {
		frmAddProj = new JFrame();
		frmAddProj.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmAddProj.setTitle("SAPx");
		frmAddProj.setBounds(100, 100, 530, 428);
		frmAddProj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddProj.getContentPane().setLayout(null);
		
		ArrayList<Cliente> arrayList = listaCliente();
		
		mskCNPJ = new MaskFormatter("##.###.###/####-##");

		JLabel lblNomeCliente = new JLabel("Nome Cliente:");
		lblNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeCliente.setBounds(10, 103, 125, 14);
		frmAddProj.getContentPane().add(lblNomeCliente);

		JLabel lblDataIncio = new JLabel("Data in\u00EDcio:");
		lblDataIncio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataIncio.setBounds(10, 133, 125, 14);
		frmAddProj.getContentPane().add(lblDataIncio);

		JLabel lblDataTrmino = new JLabel("Data t\u00E9rmino:");
		lblDataTrmino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataTrmino.setBounds(10, 159, 125, 14);
		frmAddProj.getContentPane().add(lblDataTrmino);

		//ANTIGO LOCAL DO BOTÃO

		JLabel status = new JLabel("Status:");
		status.setFont(new Font("Tahoma", Font.PLAIN, 14));
		status.setBounds(10, 185, 125, 14);
		frmAddProj.getContentPane().add(status);

		JLabel lblNProjeto = new JLabel("Horas Totais:");
		lblNProjeto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNProjeto.setBounds(10, 211, 125, 14);
		frmAddProj.getContentPane().add(lblNProjeto);

		txtHoras = new JTextField();
		txtHoras.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHoras.setBounds(145, 211, 153, 20);
		txtHoras.setColumns(10);
		frmAddProj.getContentPane().add(txtHoras);

		JLabel lblValor = new JLabel("Valor do projeto:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 237, 125, 20);
		frmAddProj.getContentPane().add(lblValor);

		txtVlrProj = new JTextField();
		txtVlrProj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtVlrProj.setBounds(145, 237, 153, 20);
		txtVlrProj.setColumns(10);
		frmAddProj.getContentPane().add(txtVlrProj);

		dtInicio = new JDateChooser();
		dtInicio.setBounds(145, 133, 153, 20);
		frmAddProj.getContentPane().add(dtInicio);

		dtTermino = new JDateChooser();
		dtTermino.setBounds(145, 159, 153, 20);
		frmAddProj.getContentPane().add(dtTermino);

		cmbStatus = new JComboBox();
		cmbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbStatus.setBounds(145, 185, 153, 20);
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] { "Em Negociacao", "Contratado", "Removido"}));
		cmbStatus.setSelectedIndex(0);
		frmAddProj.getContentPane().add(cmbStatus);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddProj.dispose();
			}
		});
		btnCancelar.setBounds(310, 319, 170, 42);
		frmAddProj.getContentPane().add(btnCancelar);

		JLabel lblAdicionarProjeto = new JLabel("Incluir Projeto");
		lblAdicionarProjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarProjeto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAdicionarProjeto.setBounds(130, 11, 264, 42);
		frmAddProj.getContentPane().add(lblAdicionarProjeto);

		JLabel lblDadosDoCliente = new JLabel("Dados");
		lblDadosDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDadosDoCliente.setBounds(10, 70, 170, 22);
		frmAddProj.getContentPane().add(lblDadosDoCliente);
		
		JLabel lblComentriosobservaes = new JLabel("Observa\u00E7\u00F5es");
		lblComentriosobservaes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblComentriosobservaes.setBounds(330, 73, 197, 22);
		frmAddProj.getContentPane().add(lblComentriosobservaes);
		
		JTextArea txtObs = new JTextArea();
		txtObs.setBounds(330, 96, 163, 161);
		frmAddProj.getContentPane().add(txtObs);

		JComboBox cmbCliente = new JComboBox();
		cmbCliente.setBounds(145, 102, 153, 20);
		populaCmbBox(cmbCliente, arrayList);
		cmbCliente.setSelectedIndex(0);
		frmAddProj.getContentPane().add(cmbCliente);

		JButton btnIncluirProjeto = new JButton("Incluir Projeto");
		btnIncluirProjeto.setBounds(40, 319, 170, 42);
		btnIncluirProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ( 	txtVlrProj.getText().equals("") || dtInicio.getDate().equals(null)
						|| dtTermino.getDate().equals(null)) {
					JOptionPane.showMessageDialog(null,
							"Erro na validação de dados. Verifique se todos os campos estão "
									+ "preenchidos e tente novamente",
							"Erro ao incluir projeto", JOptionPane.ERROR_MESSAGE);

				} else {
					if ((dtTermino.getDate().before(dtInicio.getDate()))) {
						JOptionPane.showMessageDialog(null, "Data de Início maior que a data de término",
								"ERRO DE VALIDAÇÃO DOS DADOS", JOptionPane.ERROR_MESSAGE);
					} else {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

						try {
							Connection con = DriverManager.getConnection(url);
							Statement st = con.createStatement();
							
							String sql =	"INSERT INTO PROJETOS(NomeProj, CodCliente, CodStatus, HorasTotais, DataIni, DataFim, CustoProj, ObsProj) "
											+ "VALUES('"
											+ cmbCliente.getSelectedItem().toString() + "', "
											+ "(SELECT A.CodCliente FROM CLIENTES A WHERE NomeCliente='" + cmbCliente.getSelectedItem().toString() +"'), "
											+ "(SELECT A.CodStatus FROM STATUS A WHERE ObsStatus='" + cmbStatus.getSelectedItem().toString() + "'), "
											+ Integer.parseInt(txtHoras.getText()) + ", '"
											+ dateFormat.format(dtInicio.getDate()) + "', '"
											+ dateFormat.format(dtTermino.getDate()) + "', "											
											+ Integer.parseInt(txtVlrProj.getText()) + ", '"
											+ txtObs.getText() + "')";
							st.execute(sql);
											
							
							JOptionPane.showMessageDialog(null, "Projeto adicionado com sucesso!");
							
							ArrayList<Projeto> teste = listaProjeto();

							TelaAdicionarCargo telaAdicionaCargo = new TelaAdicionarCargo(proj);
							telaAdicionaCargo.idProj=(listaProjeto().size()-1);
							telaAdicionaCargo.main(null);
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}

		});
		frmAddProj.getContentPane().add(btnIncluirProjeto);
			
	}
	
	public void populaCmbBox(JComboBox cmbBox, ArrayList<Cliente> arrayList) {
		for(int i=0; i<arrayList.size(); i++) {
			cmbBox.insertItemAt(arrayList.get(i).getNomeCliente(), i);
		}
	}
	
	private ArrayList<Cliente> listaCliente() {
		// TODO Auto-generated method stub
		ArrayList<Cliente> arrayList = new ArrayList<>();

		try {
			con = DriverManager.getConnection(url);	
			st = con.createStatement();
			
			String query = "SELECT * FROM CLIENTES";
			
			ResultSet rs = st.executeQuery(query);
			
			
			while(rs.next()){
				cliente = new Cliente(rs.getString("NomeCliente"));
				arrayList.add(cliente);
			}
		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}	
		return arrayList;
	}
	
	
	public ArrayList<Projeto> listaProjeto(){ 
		
		ArrayList<Projeto> arrayList = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			con = DriverManager.getConnection(url);	
			st = con.createStatement();
			
			String query = "SELECT A.CodProj, B.NomeCliente, B.CnpjCliente, C.ObsStatus, A.HorasTotais, A.DataIni, A.DataFim, A.CustoProj, A.ObsProj" +
			" FROM PROJETOS A, CLIENTES B, STATUS C" +
			" WHERE (A.CodCliente=B.CodCliente) AND (A.CodStatus=C.CodStatus)";
			
			ResultSet rs = st.executeQuery(query);
			
			
			while(rs.next()){
					proj = new Projeto(rs.getInt("codProj"));
				arrayList.add(proj);
			}
		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "ERRO AO MOSTRAR TELA", JOptionPane.ERROR_MESSAGE);
		}	
		return arrayList;
		
		}
	
}
