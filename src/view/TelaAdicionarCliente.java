package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import model.Cargo;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class TelaAdicionarCliente {

	private JFrame frame;
	private MaskFormatter mskCNPJ;
	Connection con;
	Statement st;
	String url= "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaAdicionarCliente window = new TelaAdicionarCliente();
					window.frame.setVisible(true);
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
	public TelaAdicionarCliente() throws ParseException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() throws ParseException {
		frame = new JFrame();
		frame.setBounds(100, 100, 308, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCargosParaO = new JLabel("ADICIONAR CLIENTE");
		lblCargosParaO.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCargosParaO.setBounds(65, 11, 217, 40);
		frame.getContentPane().add(lblCargosParaO);
		
		JTextField txtNomeCliente = new JTextField();
		txtNomeCliente.setColumns(10);
		txtNomeCliente.setBounds(106, 74, 133, 20);
		frame.getContentPane().add(txtNomeCliente);
		
		JLabel lblNomeCliente = new JLabel("Nome Cliente:");
		lblNomeCliente.setBounds(30, 77, 80, 14);
		frame.getContentPane().add(lblNomeCliente);
		
		mskCNPJ = new MaskFormatter("##.###.###/####-##");
		JFormattedTextField txtCnpj = new JFormattedTextField(mskCNPJ);
		txtCnpj.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtCnpj.setBounds(106, 127, 133, 20);
		frame.getContentPane().add(txtCnpj);
		
		JLabel lblCNPJ = new JLabel("CNPJ:");
		lblCNPJ.setBounds(30, 130, 46, 14);
		frame.getContentPane().add(lblCNPJ);
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection(url);	
					st = con.createStatement();
					
					String sql = "INSERT INTO CLIENTES (NomeCliente, CnpjCliente) values ('" 
					+ txtNomeCliente.getText() + "', '" + txtCnpj.getText() + "')";
					
					st.execute(sql);					
					
				} catch(SQLException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Erro ao inserir cliente", JOptionPane.ERROR_MESSAGE);
				}
				
				txtNomeCliente.setText("");
				txtCnpj.setText("");
			}
		});
		btnAdicionar.setBounds(30, 227, 100, 23);
		frame.getContentPane().add(btnAdicionar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja fechar?", "Aviso!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
					frame.dispose();
				}
				else {
					
				}
			}
		});
		btnCancelar.setBounds(182, 227, 100, 23);
		frame.getContentPane().add(btnCancelar);
	}
}
