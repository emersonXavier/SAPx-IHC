package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.LineNumberInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.log.Level;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.pdf.PdfWriter;

import model.Projeto;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import view.TelaPesquisarProjeto;
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
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecione o relat\u00F3rio desejado.", "Tempo de Conclus\u00E3o dos Projetos", "Lista de fun\u00E7\u00F5es atribu\u00EDdas por projeto", "N\u00FAmero de Projetos iniciados por per\u00EDodo", "Tempo estimado do projeto em rela\u00E7\u00E3o ao tempo m\u00E9dio", "Situa\u00E7\u00E3o atual do projeto, em rela\u00E7\u00E3o ao acordado", "Apontamento de horas planejadas para o projeto, por fun\u00E7\u00E3o", "Lista de fun\u00E7\u00F5es planejadas para o projeto"}));
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
		
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if (comboBox.getSelectedIndex() == 1) {
				
				//instancia de novo documento pdf
				Document relatorio = new Document();
				
				//data atual
				DateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
				Date dataatual = new Date();
				
				//gera relatorio
				try{
					PdfWriter.getInstance(relatorio, new FileOutputStream("relatorio1.pdf"));
					relatorio.open();
					
					Paragraph cabecalho = new Paragraph("Relatório SAPX - Tempo de conclusão de projetos ");
					cabecalho.setAlignment(Element.ALIGN_CENTER);
					
					Paragraph datahora = new Paragraph(dataformat.format(dataatual));
					datahora.setAlignment(Element.ALIGN_RIGHT);
					
					relatorio.add(cabecalho);
					relatorio.add(datahora);
					relatorio.add(new Paragraph(""));
					String queryproj = "SELECT * FROM PROJETOS";
					Statement stmt = null;
					
					
					
					try {
				        stmt = con.createStatement();
				        ResultSet rs = stmt.executeQuery(queryproj);
				        while (rs.next()) {
				            
				        	int coditem = rs.getInt("codProj");
				        	
				        	String nomeitem = rs.getString("nomeProj");
				        	
				        	int horasitem =  rs.getInt("horasTotais");
				        	
				        	String item = "Codigo: " + coditem +
				        			"              Nome: " + nomeitem + "              Horas:" + horasitem;
				            relatorio.add(new Paragraph(item));
				        }
				    } catch (SQLException e1 ) {
				    	JOptionPane.showMessageDialog(null, e1);
				    } 
					
					
				} catch (DocumentException | FileNotFoundException ex) {
					JOptionPane.showMessageDialog(null, ex);
				} finally {
					relatorio.close();
				}
				
				//exibe relatorio
		        try {
		            Desktop.getDesktop().open(new File("relatorio1.pdf"));
		        } catch (IOException ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
			}
				
				
				else if (comboBox.getSelectedIndex() == 2) {
					
					//instancia de novo documento pdf
					Document relatorio = new Document();
					 
					//data atual
					DateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					Date dataatual = new Date();
					
					//gera relatorio
					try{
						PdfWriter.getInstance(relatorio, new FileOutputStream("relatorio2.pdf"));
						relatorio.open();
						
						Paragraph cabecalho = new Paragraph("Relatório SAPX - Lista de funções por projetos ");
						cabecalho.setAlignment(Element.ALIGN_CENTER);
						
						Paragraph datahora = new Paragraph(dataformat.format(dataatual));
						datahora.setAlignment(Element.ALIGN_RIGHT);
						 
						relatorio.add(cabecalho);
						relatorio.add(datahora);
						relatorio.add(new Paragraph(""));
						String queryproj = "SELECT * FROM PLANEJARECURSOS P, CARGOS C, PROJETOS R WHERE P.CODCARGO = C.CODCARGO AND R.CODPROJ = P.CODPROJ AND P.CODPROJ = " + cmbBoxRelatorio.getSelectedItem();
						Statement stmt = null;
						
						try {
					        stmt = con.createStatement();
					        ResultSet rs = stmt.executeQuery(queryproj);
					        while (rs.next()) {
					            
					        	int coditem = rs.getInt("codProj");			        	
					        	String projitem = rs.getString("nomeProj");
					        	String cargoitem = rs.getString("nomeCargo");
					        	
					        	
					        	String item = "Código do Projeto: " + coditem + "              Projeto: " + projitem + "                 Função: " + cargoitem;
					            relatorio.add(new Paragraph(item));
					        }
					    } catch (SQLException e1 ) {
					    	JOptionPane.showMessageDialog(null, "Nenhum projeto selecionado, gerando relatorio em branco");
					    } 
						
					} catch (DocumentException | FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex);
					} finally {
						relatorio.close();
					}
					
					//exibe relatorio
			        try {
			            Desktop.getDesktop().open(new File("relatorio2.pdf"));
			        } catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}
				
				
				
				
				else if (comboBox.getSelectedIndex() == 3) {
					
					//instancia de novo documento pdf
					Document relatorio = new Document();
					
					//data atual
					DateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					Date dataatual = new Date();
					
					//gera relatorio
					try{
						PdfWriter.getInstance(relatorio, new FileOutputStream("relatorio3.pdf"));
						relatorio.open();
						
						Paragraph cabecalho = new Paragraph("Relatório SAPX - Número de projetos iniciados por período");
						cabecalho.setAlignment(Element.ALIGN_CENTER);
						
						Paragraph datahora = new Paragraph(dataformat.format(dataatual));
						datahora.setAlignment(Element.ALIGN_RIGHT);
						
						relatorio.add(cabecalho);
						relatorio.add(datahora);
						relatorio.add(new Paragraph(""));
						String queryproj = "SELECT * FROM PROJETOS";
						Statement stmt = null;
						
						

						
						
					} catch (DocumentException | FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex);
					} finally {
						relatorio.close();
					}
					
					//exibe relatorio
			        try {
			            Desktop.getDesktop().open(new File("relatorio3.pdf"));
			        } catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}
				
				
				else if (comboBox.getSelectedIndex() == 4) {
					
					//instancia de novo documento pdf
					Document relatorio = new Document();
					
					//data atual
					DateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					Date dataatual = new Date();
					
					//gera relatorio
					try{
						PdfWriter.getInstance(relatorio, new FileOutputStream("relatorio4.pdf"));
						relatorio.open();
						
						Paragraph cabecalho = new Paragraph("Relatório SAPX - Tempo estimado do projeto em relação ao tempo médio");
						cabecalho.setAlignment(Element.ALIGN_CENTER);
						
						Paragraph datahora = new Paragraph(dataformat.format(dataatual));
						datahora.setAlignment(Element.ALIGN_RIGHT);
						
						relatorio.add(cabecalho);
						relatorio.add(datahora);
						relatorio.add(new Paragraph(""));
						String queryproj = "SELECT * FROM PROJETOS";
						Statement stmt = null;
						

					} catch (DocumentException | FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex);
					} finally {
						relatorio.close();
					}
					
					//exibe relatorio
			        try {
			            Desktop.getDesktop().open(new File("relatorio4.pdf"));
			        } catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}
				

				
				
				
				else if (comboBox.getSelectedIndex() == 5) {
					
					//instancia de novo documento pdf
					Document relatorio = new Document();
					
					//data atual
					DateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					Date dataatual = new Date();
					
					//gera relatorio
					try{
						PdfWriter.getInstance(relatorio, new FileOutputStream("relatorio5.pdf"));
						relatorio.open();
						
						Paragraph cabecalho = new Paragraph("Relatório SAPX - Situação atual do projeto, em relação ao acordado");
						cabecalho.setAlignment(Element.ALIGN_CENTER);
						
						Paragraph datahora = new Paragraph(dataformat.format(dataatual));
						datahora.setAlignment(Element.ALIGN_RIGHT);
						
						relatorio.add(cabecalho);
						relatorio.add(datahora);
						relatorio.add(new Paragraph(""));
						String queryproj = "SELECT * FROM PROJETOS";
						Statement stmt = null;
						

						
						
					} catch (DocumentException | FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex);
					} finally {
						relatorio.close();
					}
					
					//exibe relatorio
			        try {
			            Desktop.getDesktop().open(new File("relatorio5.pdf"));
			        } catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}

				
				else if (comboBox.getSelectedIndex() == 6) {
					
					//instancia de novo documento pdf
					Document relatorio = new Document();
					
					//data atual
					DateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					Date dataatual = new Date();
					
					//gera relatorio
					try{
						PdfWriter.getInstance(relatorio, new FileOutputStream("relatorio6.pdf"));
						relatorio.open();
						
						Paragraph cabecalho = new Paragraph("Relatório SAPX - Apontamento de horas planejadas para o projeto por função");
						cabecalho.setAlignment(Element.ALIGN_CENTER);
						
						Paragraph datahora = new Paragraph(dataformat.format(dataatual));
						datahora.setAlignment(Element.ALIGN_RIGHT);
						
						relatorio.add(cabecalho);
						relatorio.add(datahora);
						relatorio.add(new Paragraph(""));
						String queryproj = "SELECT * FROM PROJETOS";
						Statement stmt = null;
						

						
						
					} catch (DocumentException | FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex);
					} finally {
						relatorio.close();
					}
					
					//exibe relatorio
			        try {
			            Desktop.getDesktop().open(new File("relatorio6.pdf"));
			        } catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}				
				
				
				else if (comboBox.getSelectedIndex() == 7) {
					
					//instancia de novo documento pdf
					Document relatorio = new Document();
					
					//data atual
					DateFormat dataformat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
					Date dataatual = new Date();
					
					//gera relatorio
					try{
						PdfWriter.getInstance(relatorio, new FileOutputStream("relatorio7.pdf"));
						relatorio.open();
						
						Paragraph cabecalho = new Paragraph("Relatório SAPX - Lista de funções planejadas para o projeto");
						cabecalho.setAlignment(Element.ALIGN_CENTER);
						
						Paragraph datahora = new Paragraph(dataformat.format(dataatual));
						datahora.setAlignment(Element.ALIGN_RIGHT);
						
						relatorio.add(cabecalho);
						relatorio.add(datahora);
						relatorio.add(new Paragraph(""));
						String queryproj = "SELECT * FROM PROJETOS";
						Statement stmt = null;
						

						
						
					} catch (DocumentException | FileNotFoundException ex) {
						JOptionPane.showMessageDialog(null, ex);
					} finally {
						relatorio.close();
					}
					
					//exibe relatorio
			        try {
			            Desktop.getDesktop().open(new File("relatorio7.pdf"));
			        } catch (IOException ex) {
						JOptionPane.showMessageDialog(null, ex);
					}
				}				
				
				
			}
		});
		
		
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
	
	public void populaCmbBox(JComboBox cmbBox, ArrayList<Projeto> arrayList) {
		for(int i=0; i<arrayList.size(); i++) {
			cmbBox.insertItemAt(arrayList.get(i).getCodProj(), i);
		}
	}
	
}
