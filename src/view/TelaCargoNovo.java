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

public class TelaCargoNovo {

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
					TelaCargoNovo window = new TelaCargoNovo();
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
	public TelaCargoNovo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws ParseException 
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 308, 230);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblCargosParaO = new JLabel("ADICIONAR CARGO NO SISTEMA");
		lblCargosParaO.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCargosParaO.setBounds(10, 11, 272, 40);
		frame.getContentPane().add(lblCargosParaO);
		
		JTextField txtNomeCargo = new JTextField();
		txtNomeCargo.setColumns(10);
		txtNomeCargo.setBounds(106, 74, 133, 20);
		frame.getContentPane().add(txtNomeCargo);
		
		JLabel lblNomeCliente = new JLabel("Nome Cargo:");
		lblNomeCliente.setBounds(30, 77, 80, 14);
		frame.getContentPane().add(lblNomeCliente);
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					con = DriverManager.getConnection(url);	
					st = con.createStatement();
					
					String sql = "INSERT INTO CARGOS (NomeCargo) values ('" + txtNomeCargo.getText() +"')" ;
					
					st.execute(sql);					
					
				} catch(SQLException x) {
					JOptionPane.showMessageDialog(null, x.getMessage(), "Erro ao inserir cargo", JOptionPane.ERROR_MESSAGE);
				}
				
				txtNomeCargo.setText("");
			}
		});
		btnAdicionar.setBounds(30, 130, 100, 23);
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
		btnCancelar.setBounds(160, 130, 100, 23);
		frame.getContentPane().add(btnCancelar);
	}
}
