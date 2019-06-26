package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import model.Projeto;

import javax.swing.JLabel;
import javax.swing.JButton;

public class AdicionarCargo {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdicionarCargo window = new AdicionarCargo();
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
	public AdicionarCargo() {
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
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(23, 23, 219, 20);
		frame.getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(82, 85, 160, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblCargo = new JLabel("Cargo:");
		lblCargo.setBounds(23, 88, 46, 14);
		frame.getContentPane().add(lblCargo);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(82, 128, 160, 20);
		frame.getContentPane().add(textField_1);
		
		JLabel lblQuantidade = new JLabel("QTD:");
		lblQuantidade.setBounds(23, 131, 46, 14);
		frame.getContentPane().add(lblQuantidade);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(82, 171, 160, 20);
		frame.getContentPane().add(textField_2);
		
		JLabel lblHoras = new JLabel("Horas:");
		lblHoras.setBounds(23, 174, 46, 14);
		frame.getContentPane().add(lblHoras);
		
		JButton btnAdicionar = new JButton("ADICIONAR");
		btnAdicionar.setBounds(100, 227, 125, 23);
		frame.getContentPane().add(btnAdicionar);
	}
	
	
	public void populaCmbBox(JComboBox cmbBox, ArrayList<Projeto> arrayList) {
		for(int i=0; i<arrayList.size(); i++) {
			cmbBox.insertItemAt(arrayList.get(i).getNumProj(), i);
		}
	}
}
