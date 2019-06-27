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
import model.ProjetoTabela;

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
	JTextField txtCodProj;
	//JTextField txtCnpj;
	JTextField txtValor;
	static //TelaPesquisarProjeto view;
	Projeto projParalelo;
	private JTextField txtNomeProj;
	JDateChooser dtTermino = new JDateChooser();
	JDateChooser dtInicio = new JDateChooser();
	JComboBox cmbStatus = new JComboBox();
	static String url = "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	Connection con;
	Statement st;
	MaskFormatter mskCNPJ = new MaskFormatter("##.###.###/####-##");
	JFormattedTextField txtNomeCliente = new JFormattedTextField(mskCNPJ);

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
	public TelaAlterarProjeto(ProjetoTabela projetoTabela) throws ParseException {
		initialize();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		txtCodProj.setText(String.valueOf(projetoTabela.getCodProj()));
		txtNomeProj.setText(projetoTabela.getNomeCliente());
		txtNomeCliente.setText(String.valueOf(projetoTabela.getCnpjCliente()));
		dtInicio.setDate(dateFormat.parse(projetoTabela.getDataIni()));
		dtTermino.setDate(dateFormat.parse(projetoTabela.getDataFim()));
		cmbStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
/*		cmbStatus.setSelectedIndex((projeto.getStatus().intern() == "Em negociação" ? 0:1));
*/		txtValor.setText(String.valueOf(projetoTabela.getCustoProj()));
		
		JLabel lblNewLabel = new JLabel("Dados do cliente");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 70, 170, 22);
		frmAlterProj.getContentPane().add(lblNewLabel);
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frmAlterProj = new JFrame();
		frmAlterProj.setTitle("SAPx");
		frmAlterProj.setBounds(100, 100, 400, 428);
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
		
		txtCodProj = new JTextField();
		txtCodProj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCodProj.setEditable(false);
		txtCodProj.setBounds(145, 103, 153, 20);
		frmAlterProj.getContentPane().add(txtCodProj);
		txtCodProj.setColumns(10);
		
/*		txtCnpj = new JTextField();
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCnpj.setEditable(false);
		txtCnpj.setColumns(10);
		txtCnpj.setBounds(120, 155, 153, 20);
		frmAlterProj.getContentPane().add(txtCnpj);
*/
		txtNomeCliente.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeCliente.setEditable(false);
		txtNomeCliente.setColumns(10);
		txtNomeCliente.setBounds(145, 155, 153, 20);
		frmAlterProj.getContentPane().add(txtNomeCliente);
		
		txtValor = new JTextField();
		txtValor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtValor.setColumns(10);
		txtValor.setBounds(145, 259, 153, 20);
		frmAlterProj.getContentPane().add(txtValor);
		
		
		cmbStatus.setBounds(145, 233, 153, 20);
		cmbStatus.setModel(new DefaultComboBoxModel(new String[] {"Em Negociacao", "Contratado", "Removido"}));
		frmAlterProj.getContentPane().add(cmbStatus);
		
		JButton btnAlterarProjeto = new JButton("Alterar");
		btnAlterarProjeto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				alteraValores();
			}
		});
		btnAlterarProjeto.setBounds(10, 319, 170, 42);
		frmAlterProj.getContentPane().add(btnAlterarProjeto);
		
		
		dtInicio.setBounds(145, 181, 153, 20);
		frmAlterProj.getContentPane().add(dtInicio);
		dtInicio.setEnabled(false);
		
		
		dtTermino.setBounds(145, 207, 153, 20);
		frmAlterProj.getContentPane().add(dtTermino);
		
		txtNomeProj = new JTextField();
		txtNomeProj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtNomeProj.setEditable(false);
		txtNomeProj.setColumns(10);
		txtNomeProj.setBounds(145, 129, 153, 20);
		frmAlterProj.getContentPane().add(txtNomeProj);
		
		JLabel lblNomeDoCliente_1 = new JLabel("Cliente:");
		lblNomeDoCliente_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNomeDoCliente_1.setBounds(10, 129, 110, 14);
		frmAlterProj.getContentPane().add(lblNomeDoCliente_1);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja cancelar a alteração?", "Aviso!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					TelaPesquisarProjeto telaPesquisar = new TelaPesquisarProjeto();
					telaPesquisar.main(null);
					frmAlterProj.dispose();
				}
				else {
					
				}
			}
		});
		btnCancelar.setBounds(190, 319, 170, 42);
		frmAlterProj.getContentPane().add(btnCancelar);
		
		JLabel lblAlterarProjeto = new JLabel("Alterar Projeto");
		lblAlterarProjeto.setHorizontalAlignment(SwingConstants.CENTER);
		lblAlterarProjeto.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAlterarProjeto.setBounds(58, 11, 264, 42);
		frmAlterProj.getContentPane().add(lblAlterarProjeto);
		
		limpaCampos();
		
	}
	
	public void limpaCampos() {
		txtCodProj.setText(null);
	}
	
	public void alteraValores(){
		try {
			int codStatus=0;
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			con = DriverManager.getConnection(url);
			st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT A.CodStatus FROM STATUS A WHERE A.ObsStatus='"+ cmbStatus.getSelectedItem().toString() + "'");
			
			while(rs.next()) {
				codStatus= rs.getInt("CodStatus");
			}
			
			String sql = "UPDATE PROJETOS SET DataFim='" + dateFormat.format(dtTermino.getDate())+"'," 
					+ " CodStatus= " + codStatus + ", " 
					+ "CustoProj="+ Integer.parseInt(txtValor.getText())
					+ " WHERE CodProj=" + Integer.parseInt(txtCodProj.getText());
			
			st.execute(sql);

			JOptionPane.showMessageDialog(null, "Projeto editado com sucesso!");
			
			TelaPesquisarProjeto tela = new TelaPesquisarProjeto();
			tela.main(null);
			frmAlterProj.dispose();
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro ao alterar", JOptionPane.ERROR_MESSAGE);
		}
	}
}
