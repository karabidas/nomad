package firstlook;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.JButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPasswordField;

import cust.cust;

import boss.admin;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField usertxt;
	private JPasswordField passw;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setTitle("LogIn");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		usertxt = new JTextField();
		usertxt.setBounds(191, 113, 113, 20);
		contentPane.add(usertxt);
		usertxt.setColumns(10);

		JLabel lblNewLabel = new JLabel("Username :");
		lblNewLabel.setBounds(108, 113, 82, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password :");
		lblNewLabel_1.setBounds(108, 138, 82, 14);
		contentPane.add(lblNewLabel_1);

		JButton btnlog = new JButton("Log in");
		btnlog.addMouseListener(new MouseAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {

				try {
					mysqlcon a = new mysqlcon("root", "23");
					Statement statement = a.Connect();
					String sql1 = "select * from adm";
					String sql2 = "select * from cust";
					boolean flag = true;
					ResultSet rs1 = statement.executeQuery(sql1);

					while (rs1.next()) {
						String mysqluser = rs1.getString("user");
						String mysqlpass = rs1.getString("pass");

						if ((usertxt.getText().equals(mysqluser))
								&& (passw.getText().equals(mysqlpass))) {
							JOptionPane.showMessageDialog(null,
									"Login Successful.");
							admin frame1 = new admin();
							frame1.setVisible(true);
							setVisible(false);
							flag = false;
						}
					}

					ResultSet rs2 = statement.executeQuery(sql2);

					while (rs2.next()) {
						String mysqluser = rs2.getString("user");
						String mysqlpass = rs2.getString("pass");

						if ((usertxt.getText().equals(mysqluser))
								&& (passw.getText().equals(mysqlpass))) {
							JOptionPane.showMessageDialog(null,
									"Login Successful.");
							cust frame1 = new cust();
							frame1.setVisible(true);
							setVisible(false);
							flag = false;
						}

					}
					if (flag) {
						JOptionPane.showMessageDialog(null, "Login Fail.");
						usertxt.setText("");
						passw.setText("");
					}
				}

				catch (Exception err) {
					System.out.println("ERROR: " + err);
				}

			}
		});

		btnlog.setBounds(215, 164, 89, 23);
		contentPane.add(btnlog);

		passw = new JPasswordField();
		passw.setBounds(191, 135, 113, 20);
		contentPane.add(passw);

	}
}