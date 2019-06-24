package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.naming.spi.DirStateFactory.Result;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;

import com.toedter.calendar.JDateChooser;

import model.Projeto;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;

import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TelaAlterarProjeto {

	JFrame frmAlterProj;
	JTextField txtNumProj;
	//JTextField txtCnpj;
	JTextField txtValor;
	static //TelaPesquisarProjeto view;
	Projeto projParalelo;
	private JTextField txtNomeCliente;
	private JTextField txtQtdGer;
	private JTextField txtQtdCoord;
	private JTextField txtQtdArq;
	private JTextField txtQtdProgSr;
	private JTextField txtQtdProgPl;
	private JTextField txtQtdProgJr;
	private JTextField txtQtdDba;
	JDateChooser dtTermino = new JDateChooser();
	JDateChooser dtInicio = new JDateChooser();
	JComboBox cmbStatus = new JComboBox();
	static String url = "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	Connection con;
	Statement st;
	MaskFormatter mskCNPJ = new MaskFormatter("##.###.###/####-##");
	JFormattedTextField txtCnpj = new JFormattedTextField(mskCNPJ);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAlterarProjeto window = new TelaAlterarProjeto(null);
					window.frmAlterProj.setVisible(true);
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
	public TelaAlterarProjeto(Projeto projeto) throws ParseException {
		initialize();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		txtNumProj.setText(projeto.getNumProj());
		txtNomeCliente.setText(projeto.getNomeCliente());		
		txtCnpj.setText(projeto.getCnpjCliente());
		dtInicio.setDate(dateFormat.parse(projeto.getDataInicio()));
		dtTermino.setDate(dateFormat.parse(projeto.getDataTermino()));
		cmbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cmbStatus.setSelectedIndex((projeto.getStatus().intern() == "Em negociação" ? 0:1));
		txtValor.setText(String.valueOf(projeto.getValProj()));
		txtQtdGer.setText(String.valueOf(projeto.getQtdGer()));
		txtQtdCoord.setText(String.valueOf(projeto.getQtdCoord()));
		txtQtdArq.setText(String.valueOf(projeto.getQtdArq()));
		txtQtdProgSr.setText(String.valueOf(projeto.getQtdProgSr()));
		txtQtdProgPl.setText(String.valueOf(projeto.getQtdProgPl()));
		txtQtdProgJr.setText(String.valueOf(projeto.getQtdProgJr()));
		txtQtdDba.setText(String.valueOf(projeto.getQtdDba()));
		
		JLabel lblNewLabel = new JLabel("Dados do cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 70, 170, 22);
		frmAlterProj.getContentPane().add(lblNewLabel);
		
		JLabel lblHoras = new JLabel("Horas");
		lblHoras.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblHoras.setBounds(420, 70, 125, 22);
		frmAlterProj.getContentPane().add(lblHoras);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frmAlterProj = new JFrame();
		frmAlterProj.setTitle("SAPx");
		frmAlterProj.setBounds(100, 100, 650, 428);
		frmAlterProj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAlterProj.getContentPane().setLayout(null);
		
		JLabel lblNmeroDoProjeto = new JLabel("N\u00BA do projeto:");
		lblNmeroDoProjeto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNmeroDoProjeto.setBounds(10, 103, 110, 17);
		frmAlterProj.getContentPane().add(lblNmeroDoProjeto);
		
		JLabel lblNomeDoCliente = new JLabel("CNPJ cliente:");
		lblNomeDoCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoCliente.setBounds(10, 155, 110, 14);
		frmAlterProj.getContentPane().add(lblNomeDoCliente);
		
		JLabel lblDataDeIncio = new JLabel("Data de in\u00EDcio:");
		lblDataDeIncio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeIncio.setBounds(10, 181, 110, 15);
		frmAlterProj.getContentPane().add(lblDataDeIncio);
		
		JLabel lblDataDeTrmino = new JLabel("Data de t\u00E9rmino:");
		lblDataDeTrmino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDataDeTrmino.setBounds(10, 207, 110, 14);
		frmAlterProj.getContentPane().add(lblDataDeTrmino);
		
		JLabel lblStatus = new JLabel("Status:");
		lblStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblStatus.setBounds(10, 233, 99, 14);
		frmAlterProj.getContentPane().add(lblStatus);
		
		JLabel lblValorOrado = new JLabel("Valor:");
		lblValorOrado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblValorOrado.setBounds(10, 259, 99, 14);
		frmAlterProj.getContentPane().add(lblValorOrado);
		
		txtNumProj = new JTextField();
		txtNumProj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNumProj.setEditable(false);
		txtNumProj.setBounds(145, 103, 153, 20);
		frmAlterProj.getContentPane().add(txtNumProj);
		txtNumProj.setColumns(10);
		
/*		txtCnpj = new JTextField();
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCnpj.setEditable(false);
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(120, 155, 153, 20);
		frmAlterProj.getContentPane().add(txtCnpj);
*/
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCnpj.setEditable(false);
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(145, 155, 153, 20);
		frmAlterProj.getContentPane().add(txtCnpj);
		
		txtValor = new JTextField();
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtValor.setColumns(10);
		txtValor.setBounds(145, 259, 153, 20);
		frmAlterProj.getContentPane().add(txtValor);
		
		
		cmbStatus.setBounds(145, 233, 153, 20);
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em negociação", "Contratado"}));
		frmAlterProj.getContentPane().add(cmbStatus);
		
		JButton btnAlterarProjeto = new JButton("Alterar");
		btnAlterarProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alteraValores();
			}
		});
		btnAlterarProjeto.setBounds(128, 319, 170, 42);
		frmAlterProj.getContentPane().add(btnAlterarProjeto);
		
		
		dtInicio.setBounds(145, 181, 153, 20);
		frmAlterProj.getContentPane().add(dtInicio);
		dtInicio.setEnabled(false);
		
		
		dtTermino.setBounds(145, 207, 153, 20);
		frmAlterProj.getContentPane().add(dtTermino);
		
		txtNomeCliente = new JTextField();
		txtNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setColumns(10);
		txtNomeCliente.setBounds(145, 129, 153, 20);
		frmAlterProj.getContentPane().add(txtNomeCliente);
		
		JLabel lblNomeDoCliente_1 = new JLabel("Cliente:");
		lblNomeDoCliente_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoCliente_1.setBounds(10, 129, 110, 14);
		frmAlterProj.getContentPane().add(lblNomeDoCliente_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(330, 319, 170, 42);
		frmAlterProj.getContentPane().add(btnCancelar);
		
		JLabel lblAlterarProjeto = new JLabel("Alterar Projeto");
		lblAlterarProjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarProjeto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAlterarProjeto.setBounds(180, 11, 264, 42);
		frmAlterProj.getContentPane().add(lblAlterarProjeto);
		
		JLabel label = new JLabel("Gerentes:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(420, 103, 110, 15);
		frmAlterProj.getContentPane().add(label);
		
		txtQtdGer = new JTextField();
		txtQtdGer.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdGer.setColumns(10);
		txtQtdGer.setBounds(550, 103, 40, 20);
		frmAlterProj.getContentPane().add(txtQtdGer);
		
		JLabel label_1 = new JLabel("Coordenadores:");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(420, 129, 110, 15);
		frmAlterProj.getContentPane().add(label_1);
		
		txtQtdCoord = new JTextField();
		txtQtdCoord.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdCoord.setColumns(10);
		txtQtdCoord.setBounds(550, 129, 40, 20);
		frmAlterProj.getContentPane().add(txtQtdCoord);
		
		JLabel label_2 = new JLabel("Arquitetos:");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(420, 155, 110, 15);
		frmAlterProj.getContentPane().add(label_2);
		
		txtQtdArq = new JTextField();
		txtQtdArq.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdArq.setColumns(10);
		txtQtdArq.setBounds(550, 155, 40, 20);
		frmAlterProj.getContentPane().add(txtQtdArq);
		
		JLabel label_3 = new JLabel("Progr. S\u00EAnior:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(420, 181, 110, 15);
		frmAlterProj.getContentPane().add(label_3);
		
		txtQtdProgSr = new JTextField();
		txtQtdProgSr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdProgSr.setColumns(10);
		txtQtdProgSr.setBounds(550, 181, 40, 20);
		frmAlterProj.getContentPane().add(txtQtdProgSr);
		
		JLabel label_4 = new JLabel("Progr. Pleno:");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_4.setBounds(420, 207, 110, 15);
		frmAlterProj.getContentPane().add(label_4);
		
		txtQtdProgPl = new JTextField();
		txtQtdProgPl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdProgPl.setColumns(10);
		txtQtdProgPl.setBounds(550, 207, 40, 20);
		frmAlterProj.getContentPane().add(txtQtdProgPl);
		
		JLabel label_5 = new JLabel("Progr. Jr:");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_5.setBounds(420, 233, 110, 15);
		frmAlterProj.getContentPane().add(label_5);
		
		txtQtdProgJr = new JTextField();
		txtQtdProgJr.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdProgJr.setColumns(10);
		txtQtdProgJr.setBounds(550, 233, 40, 20);
		frmAlterProj.getContentPane().add(txtQtdProgJr);
		
		JLabel label_6 = new JLabel("DBA:");
		label_6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_6.setBounds(420, 259, 110, 14);
		frmAlterProj.getContentPane().add(label_6);
		
		txtQtdDba = new JTextField();
		txtQtdDba.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtQtdDba.setColumns(10);
		txtQtdDba.setBounds(550, 259, 40, 20);
		frmAlterProj.getContentPane().add(txtQtdDba);
		
		limpaCampos();
		
	}
	
	public void limpaCampos() {
		txtNumProj.setText(null);
	}
	
	public void alteraValores(){
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			con = DriverManager.getConnection(url);
			st = con.createStatement();
			
			st.execute("UPDATE PROJETOS SET dt_ter='" + dateFormat.format(dtTermino.getDate())+"'," 
					+ " status='"+ cmbStatus.getSelectedItem().toString() + "', " 
					+ "vlr_proj="+ Float.parseFloat(txtValor.getText()) +", qtdGer=" + Integer.parseInt(txtQtdGer.getText()) + ", "
					+ "qtdCoord="+ Integer.parseInt(txtQtdCoord.getText()) + ", qtdArq=" + Integer.parseInt(txtQtdArq.getText()) + ", " 
					+ "qtdProgSr=" + Integer.parseInt(txtQtdProgSr.getText()) + ", qtdProgPl=" + Integer.parseInt(txtQtdProgPl.getText()) + ", " 
					+ "qtdProgJr=" + Integer.parseInt(txtQtdProgJr.getText()) + ", qtdDba="+ Integer.parseInt(txtQtdDba.getText()) 
					+ "WHERE id_proj=" + Integer.parseInt(txtNumProj.getText()));
			TelaPesquisarProjeto tela = new TelaPesquisarProjeto();
			tela.main(null);
			frmAlterProj.dispose();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
		}
	}
}
