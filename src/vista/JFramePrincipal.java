package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JTabbedPane;
import javax.swing.JInternalFrame;

public class JFramePrincipal extends JFrame {

	private JPanel contentPane;
	public JButton btnLogin;
	public JButton btnReservas;
	public JButton btnPeriodos;
	public JButton btnConfiguracion;
	public JDesktopPane desktopPane;
	public JButton btnSalir;
	public JButton btnAbout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFramePrincipal frame = new JFramePrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFramePrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 677, 636);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		contentPane.add(toolBar, BorderLayout.NORTH);

		btnLogin = new JButton("Login");
		btnLogin.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/images/login.png")));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		toolBar.add(btnLogin);

		btnReservas = new JButton("Reservas");
		btnReservas.setEnabled(false);
		btnReservas.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/images/report.png")));
		toolBar.add(btnReservas);

		btnPeriodos = new JButton("Gestión Periodos");
		btnPeriodos.setEnabled(false);
		btnPeriodos.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/images/categoria.png")));
		toolBar.add(btnPeriodos);

		btnConfiguracion = new JButton("Configuración");
		btnConfiguracion.setEnabled(false);
		btnConfiguracion.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/images/Conf.png")));
		toolBar.add(btnConfiguracion);

		btnSalir = new JButton("Salir");
		btnSalir.setEnabled(false);
		btnSalir.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/images/Logout.png")));
		toolBar.add(btnSalir);
		
		btnAbout = new JButton("About");
		btnAbout.setIcon(new ImageIcon(JFramePrincipal.class.getResource("/images/usuarios.png")));
		toolBar.add(btnAbout);

		JPanel Visual = new JPanel();
		contentPane.add(Visual, BorderLayout.CENTER);
		Visual.setLayout(new BorderLayout(0, 0));

		desktopPane = new JDesktopPane();
		Visual.add(desktopPane, BorderLayout.CENTER);
	}
}
