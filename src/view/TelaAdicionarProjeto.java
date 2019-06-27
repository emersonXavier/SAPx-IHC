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

import model.Projeto;
import model.ProjetoTableModel;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.nio.channels.NetworkChannel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

public class TelaAdicionarProjeto {

	JFrame frmAddProj;
	private JTextField txtNomeCliente;
	TelaOperacional telaOperacional = new TelaOperacional();

	static ProjetoTableModel tableModel = new ProjetoTableModel();
	private JTextField txtNumProj;
	private JTextField txtVlrProj;
	JDateChooser dtInicio, dtTermino;
	ConexaoBanco conexaoBD = new ConexaoBanco();
	JComboBox cmbStatus;
	static Projeto p = new Projeto();
	String url = "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	private JTextField txtQtdGerente;
	private JTextField txtQtdCoord;
	private JTextField txtQtdArq;
	private JTextField txtQtdProgSr;
	private JTextField txtQtdProgPl;
	private JTextField txtQtdProgJr;
	private JTextField txtQtdDba;
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
	 * @throws ParseException 
	 */
	public TelaAdicionarProjeto() throws ParseException {
		initialize();

	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frmAddProj = new JFrame();
		frmAddProj.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		frmAddProj.setTitle("SAPx");
		frmAddProj.setBounds(100, 100, 650, 428);
		frmAddProj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddProj.getContentPane().setLayout(null);
		
		mskCNPJ = new MaskFormatter("##.###.###/####-##");
		JFormattedTextField txtCnpj = new JFormattedTextField(mskCNPJ);
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblNomeCliente = new JLabel("Nome Cliente:");
		lblNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeCliente.setBounds(10, 103, 125, 14);
		frmAddProj.getContentPane().add(lblNomeCliente);

		txtNomeCliente = new JTextField();
		txtNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeCliente.setBounds(145, 103, 153, 20);
		frmAddProj.getContentPane().add(txtNomeCliente);
		txtNomeCliente.setColumns(10);

		JLabel lblDataIncio = new JLabel("Data in\u00EDcio:");
		lblDataIncio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataIncio.setBounds(10, 155, 125, 14);
		frmAddProj.getContentPane().add(lblDataIncio);

		JLabel lblDataTrmino = new JLabel("Data t\u00E9rmino:");
		lblDataTrmino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataTrmino.setBounds(10, 181, 125, 14);
		frmAddProj.getContentPane().add(lblDataTrmino);

		JButton btnIncluirProjeto = new JButton("Incluir Projeto");
		btnIncluirProjeto.setBounds(128, 319, 170, 42);
		btnIncluirProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if ( txtCnpj.getText().equals("") || txtNomeCliente.getText().equals("")
						|| txtNumProj.getText().equals("") || txtQtdArq.getText().equals("")
						|| txtQtdCoord.getText().equals("") || txtQtdDba.getText().equals("")
						|| txtQtdGerente.getText().equals("") || txtQtdProgJr.getText().equals("")
						|| txtQtdProgPl.getText().equals("") || txtQtdProgSr.getText().equals("")
						|| txtVlrProj.getText().equals("") || dtInicio.getDate().equals(null)
						|| dtTermino.getDate().equals(null)) {
					JOptionPane.showMessageDialog(null,	"Erro na validação de dados. Verifique se todos os campos estão " + 
					"preenchidos e tente novamente", "Erro ao incluir projeto", JOptionPane.ERROR_MESSAGE);

				} else {
					if ((dtTermino.getDate().before(dtInicio.getDate()))) {
						JOptionPane.showMessageDialog(null, "Data de Início maior que a data de término",
								"ERRO DE VALIDAÇÃO DOS DADOS", JOptionPane.ERROR_MESSAGE);
					} else {

						DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

						try {
							Connection con = DriverManager.getConnection(url);
							Statement st = con.createStatement();
							st.execute(
									"INSERT INTO PROJETOS(id_proj, nome_cli, cnpj_cli, dt_ini, dt_ter, status, vlr_proj, qtdGer, qtdCoord, qtdArq,"
											+ " qtdProgSr, qtdProgPl, qtdProgJr, qtdDba) VALUES("
											+ Integer.parseInt(txtNumProj.getText()) + ", '" + txtNomeCliente.getText()
											+ "', '" + txtCnpj.getText() + "', '"
											+ dateFormat.format(dtInicio.getDate()) + "', '"
											+ dateFormat.format(dtTermino.getDate()) + "', '"
											+ cmbStatus.getSelectedItem().toString() + "', "
											+ Float.parseFloat(txtVlrProj.getText()) + ", "
											+ Integer.parseInt(txtQtdGerente.getText()) + ", "
											+ Integer.parseInt(txtQtdCoord.getText()) + ", "
											+ Integer.parseInt(txtQtdArq.getText()) + ", "
											+ Integer.parseInt(txtQtdProgSr.getText()) + ", "
											+ Integer.parseInt(txtQtdProgPl.getText()) + ", "
											+ Integer.parseInt(txtQtdProgJr.getText()) + ", "
											+ Integer.parseInt(txtQtdDba.getText()) + ")");

							JOptionPane.showMessageDialog(null, "Projeto adicionado com sucesso!");
							
							frmAddProj.dispose();
							// telaOperacional.getMainFrame().setVisible(true);
							// telaOperacional.frame.setVisible(true);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				}
			}

		});
		frmAddProj.getContentPane().add(btnIncluirProjeto);

		JLabel status = new JLabel("Status:");
		status.setFont(new Font("Tahoma", Font.PLAIN, 14));
		status.setBounds(10, 207, 125, 14);
		frmAddProj.getContentPane().add(status);

		JLabel lblNProjeto = new JLabel("N\u00BA do Projeto:");
		lblNProjeto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNProjeto.setBounds(10, 233, 125, 14);
		frmAddProj.getContentPane().add(lblNProjeto);

		txtNumProj = new JTextField();
		txtNumProj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumProj.setBounds(145, 233, 153, 20);
		txtNumProj.setColumns(10);
		frmAddProj.getContentPane().add(txtNumProj);

		JLabel lblValor = new JLabel("Valor do projeto:");
		lblValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValor.setBounds(10, 259, 125, 14);
		frmAddProj.getContentPane().add(lblValor);

		txtVlrProj = new JTextField();
		txtVlrProj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtVlrProj.setBounds(145, 259, 153, 20);
		txtVlrProj.setColumns(10);
		frmAddProj.getContentPane().add(txtVlrProj);

		dtInicio = new JDateChooser();
		dtInicio.setBounds(145, 155, 153, 20);
		frmAddProj.getContentPane().add(dtInicio);

		dtTermino = new JDateChooser();
		dtTermino.setBounds(145, 181, 153, 20);
		frmAddProj.getContentPane().add(dtTermino);

		cmbStatus = new JComboBox();
		cmbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbStatus.setBounds(145, 207, 153, 20);
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] { "Em negociação", "Contratado" }));
		frmAddProj.getContentPane().add(cmbStatus);

		JLabel lblCnpj = new JLabel("CNPJ Cliente:");
		lblCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCnpj.setBounds(10, 129, 125, 14);
		frmAddProj.getContentPane().add(lblCnpj);

		txtCnpj.setBounds(145, 129, 153, 20);
		frmAddProj.getContentPane().add(txtCnpj);
/*				
		JLabel lblCpf = new JLabel("CPF Cliente:");
		lblCpf.setBounds(10, 129, 80, 14);
		lblCpf.setVisible(false);
		frmAddProj.getContentPane().add(lblCpf);
		
		
		mskCPF = new MaskFormatter("###.###.###-##");
		JFormattedTextField txtCpf = new JFormattedTextField(mskCPF);
		txtCpf.setBounds(120, 129, 153, 20);
		frmAddProj.getContentPane().add(txtCpf);
		txtCnpj.setVisible(false);*/
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddProj.dispose();
			}
		});
		btnCancelar.setBounds(330, 319, 170, 42);
		frmAddProj.getContentPane().add(btnCancelar);

		JLabel lblAdicionarProjeto = new JLabel("Incluir Projeto");
		lblAdicionarProjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAdicionarProjeto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAdicionarProjeto.setBounds(180, 11, 264, 42);
		frmAddProj.getContentPane().add(lblAdicionarProjeto);

		JLabel lblNDeProgramadores = new JLabel("Gerentes:");
		lblNDeProgramadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNDeProgramadores.setBounds(420, 103, 61, 14);
		frmAddProj.getContentPane().add(lblNDeProgramadores);

		JLabel lblArquitetos = new JLabel("Arquitetos:");
		lblArquitetos.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblArquitetos.setBounds(420, 155, 80, 14);
		frmAddProj.getContentPane().add(lblArquitetos);

		JLabel lblProgramadoresSnior = new JLabel("Progr. S\u00EAnior:");
		lblProgramadoresSnior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProgramadoresSnior.setBounds(420, 181, 80, 14);
		frmAddProj.getContentPane().add(lblProgramadoresSnior);

		JLabel lblProgrPleno = new JLabel("Progr. Pleno:");
		lblProgrPleno.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProgrPleno.setBounds(420, 207, 80, 14);
		frmAddProj.getContentPane().add(lblProgrPleno);

		JLabel lblProgrJnior = new JLabel("Progr. Jr:");
		lblProgrJnior.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblProgrJnior.setBounds(420, 233, 80, 14);
		frmAddProj.getContentPane().add(lblProgrJnior);

		JLabel lblDba = new JLabel("DBA:");
		lblDba.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDba.setBounds(420, 259, 80, 14);
		frmAddProj.getContentPane().add(lblDba);

		JLabel lblCoordenadores = new JLabel("Coordenadores:");
		lblCoordenadores.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCoordenadores.setBounds(420, 129, 99, 14);
		frmAddProj.getContentPane().add(lblCoordenadores);

		txtQtdGerente = new JTextField();
		txtQtdGerente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdGerente.setBounds(550, 103, 40, 20);
		frmAddProj.getContentPane().add(txtQtdGerente);
		txtQtdGerente.setColumns(10);

		txtQtdCoord = new JTextField();
		txtQtdCoord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdCoord.setColumns(10);
		txtQtdCoord.setBounds(550, 129, 40, 20);
		frmAddProj.getContentPane().add(txtQtdCoord);

		txtQtdArq = new JTextField();
		txtQtdArq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdArq.setColumns(10);
		txtQtdArq.setBounds(550, 155, 40, 20);
		frmAddProj.getContentPane().add(txtQtdArq);

		txtQtdProgSr = new JTextField();
		txtQtdProgSr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdProgSr.setColumns(10);
		txtQtdProgSr.setBounds(550, 181, 40, 20);
		frmAddProj.getContentPane().add(txtQtdProgSr);

		txtQtdProgPl = new JTextField();
		txtQtdProgPl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdProgPl.setColumns(10);
		txtQtdProgPl.setBounds(550, 207, 40, 20);
		frmAddProj.getContentPane().add(txtQtdProgPl);

		txtQtdProgJr = new JTextField();
		txtQtdProgJr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdProgJr.setColumns(10);
		txtQtdProgJr.setBounds(550, 233, 40, 20);
		frmAddProj.getContentPane().add(txtQtdProgJr);

		txtQtdDba = new JTextField();
		txtQtdDba.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdDba.setColumns(10);
		txtQtdDba.setBounds(550, 259, 40, 20);
		frmAddProj.getContentPane().add(txtQtdDba);
		
		JLabel lblDadosDoCliente = new JLabel("Dados do cliente");
		lblDadosDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblDadosDoCliente.setBounds(10, 70, 170, 22);
		frmAddProj.getContentPane().add(lblDadosDoCliente);
		
		JLabel lblHoras = new JLabel("Horas");
		lblHoras.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHoras.setBounds(420, 70, 125, 22);
		frmAddProj.getContentPane().add(lblHoras);
		
		JButton btnAdicionarCargo = new JButton("Adicionar cargo");
		btnAdicionarCargo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AdicionarCargo tela = new AdicionarCargo();
				tela.main(null);
			}
		});
		btnAdicionarCargo.setBounds(510, 329, 114, 23);
		frmAddProj.getContentPane().add(btnAdicionarCargo);
		
		
	}
}
