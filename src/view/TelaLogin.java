package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TelaLogin {

	private JFrame frmLogin;
	private JTextField txtUser;
	private JPasswordField txtSenha;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaLogin window = new TelaLogin();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaLogin() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("SAPx");
		frmLogin.setBounds(100, 100, 300, 233);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLogin.setBounds(35, 68, 46, 18);
		frmLogin.getContentPane().add(lblLogin);
		
		txtUser = new JTextField();
		txtUser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUser.setBounds(90, 67, 154, 20);
		frmLogin.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		JLabel lblSenha = new JLabel("Senha:");
		lblSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSenha.setBounds(35, 93, 46, 18);
		frmLogin.getContentPane().add(lblSenha);
		
		JButton btnLogar = new JButton("Acessar");
		btnLogar.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(checkLogin(txtUser.getText(), new String(txtSenha.getPassword()))) {
					
					JOptionPane.showMessageDialog(null, "BEM VINDO!");
					
					try {
						TelaOperacional tela = new TelaOperacional();
						//tela.frmMain.setVisible(true);
						tela.main(null);
						frmLogin.dispose();
						
					}catch(Exception e) {
						e.printStackTrace();
					}
				
				}else {
					JOptionPane.showMessageDialog(null, "Usuário ou Senha incorretos. Tente novamente!");
				}
				
			}
		});
		btnLogar.setBounds(75, 137, 154, 33);
		frmLogin.getContentPane().add(btnLogar);
		
		txtSenha = new JPasswordField();
		txtSenha.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtSenha.setBounds(90, 92, 154, 20);
		frmLogin.getContentPane().add(txtSenha);
		
		JLabel lblLoginSistema = new JLabel("Sistema SAPx");
		lblLoginSistema.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginSistema.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLoginSistema.setBounds(10, 11, 264, 42);
		frmLogin.getContentPane().add(lblLoginSistema);
	}
	
	public boolean checkLogin(String user, String pass) {
		return user.equals("admin") && pass.equals("admin");
	}
}
