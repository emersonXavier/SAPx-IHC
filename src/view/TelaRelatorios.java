package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.LineNumberInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import model.Projeto;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import view.TelaPesquisarProjeto;

public class TelaRelatorios {
	
	private JFrame frmRelat;
	static String url= "jdbc:h2:mem:DB_PROJ;DB_CLOSE_DELAY=-1;";
	Connection con;
	Statement st;
	Projeto proj;
	JComboBox cmbBoxRelatorio = new JComboBox();
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaRelatorios window = new TelaRelatorios();
					window.frmRelat.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaRelatorios() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRelat = new JFrame();
		frmRelat.setTitle("SAPx");
		frmRelat.setBounds(100, 100, 345, 220);
		frmRelat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmRelat.getContentPane().setLayout(null);
		
		ArrayList<Projeto> arrayList = listaProjeto();
		
		JLabel lblRelatriosSapx = new JLabel("Relat\u00F3rios - SAPx");
		lblRelatriosSapx.setHorizontalAlignment(SwingConstants.CENTER);
		lblRelatriosSapx.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRelatriosSapx.setBounds(29, 11, 264, 42);
		frmRelat.getContentPane().add(lblRelatriosSapx);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione o relat\u00F3rio desejado...", "Tempo de Conclus\u00E3o dos Projetos", "Lista de fun\u00E7\u00F5es atribu\u00EDdas por projeto", "N\u00FAmero de Projetos iniciados por per\u00EDodo", "Tempo estimado do projeto em rela\u00E7\u00E3o ao tempo m\u00E9dio", "Situa\u00E7\u00E3o atual do projeto, em rela\u00E7\u00E3o ao acordado", "Apontamento de horas planejadas para o projeto, por fun\u00E7\u00E3o", "Lista de fun\u00E7\u00F5es planejadas para o projeto"}));
		comboBox.setToolTipText("");
		comboBox.setBounds(10, 64, 304, 20);
		frmRelat.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (comboBox.getSelectedIndex()) {
				case 2:
					cmbBoxRelatorio.setEnabled(true);					
					break;
				case 4:
					cmbBoxRelatorio.setEnabled(true);
					break;
				case 5:
					cmbBoxRelatorio.setEnabled(true);					
					break;
				case 6:
					cmbBoxRelatorio.setEnabled(true);
					break;
				case 7:
					cmbBoxRelatorio.setEnabled(true);
					break;
				default:
					cmbBoxRelatorio.setEnabled(false);
					break;
				}
				
				
			}
		});
		
		JButton btnNewButton = new JButton("Gerar relat\u00F3rio");
		btnNewButton.setBounds(94, 124, 151, 37);
		frmRelat.getContentPane().add(btnNewButton);
		
		
		cmbBoxRelatorio.setEnabled(false);
		cmbBoxRelatorio.setModel(new DefaultComboBoxModel(new String[] {"Selecione o projeto desejado..."}));
		cmbBoxRelatorio.setToolTipText("");
		cmbBoxRelatorio.setBounds(10, 94, 304, 20);
		populaCmbBox(cmbBoxRelatorio, arrayList);
		frmRelat.getContentPane().add(cmbBoxRelatorio);
		
		
		
	}

	private ArrayList<Projeto> listaProjeto() {
		// TODO Auto-generated method stub
		ArrayList<Projeto> arrayList = new ArrayList<>();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			con = DriverManager.getConnection(url);	
			st = con.createStatement();
			
			String query = "SELECT * FROM PROJETOS";
			
			ResultSet rs = st.executeQuery(query);
			
			
			while(rs.next()){
				proj = new Projeto(String.valueOf(rs.getInt("id_proj")), rs.getString("nome_cli"), rs.getString("cnpj_cli"), rs.getString("status"), dateFormat.format(rs.getDate("dt_ini")), dateFormat.format(rs.getDate("dt_ter")), 
						rs.getFloat("vlr_proj"), rs.getInt("qtdGer"), rs.getInt("qtdCoord"), rs.getInt("qtdArq"), rs.getInt("qtdProgSr"), 
						rs.getInt("qtdProgPl"), rs.getInt("qtdProgJr"), rs.getInt("qtdDba"));
				arrayList.add(proj);
			}
		
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}	
		return arrayList;
	}
	
	public void populaCmbBox(JComboBox cmbBox, ArrayList<Projeto> arrayList) {
		for(int i=0; i<arrayList.size(); i++) {
			cmbBox.insertItemAt(arrayList.get(i).getNumProj(), i);
		}
	}
	
}
