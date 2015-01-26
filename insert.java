package boss;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import firstlook.mysqlcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class insert extends JFrame {

	private JPanel contentPane;
	private JTextField name;
	private JTextField barcode;
	private JTextField val;
	private JTextField Description;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					insert frame = new insert();
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
	public insert() {
		setTitle("Insert Product Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name Product");
		lblNewLabel.setBounds(56, 56, 85, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Barcode");
		lblNewLabel_1.setBounds(56, 80, 85, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Quantity");
		lblNewLabel_2.setBounds(56, 105, 85, 14);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Description");
		lblNewLabel_3.setBounds(56, 130, 85, 14);
		contentPane.add(lblNewLabel_3);

		name = new JTextField();
		name.setBounds(151, 53, 86, 20);
		contentPane.add(name);
		name.setColumns(10);

		barcode = new JTextField();
		barcode.setBounds(151, 77, 86, 20);
		contentPane.add(barcode);
		barcode.setColumns(10);

		val = new JTextField();
		val.setBounds(151, 102, 86, 20);
		contentPane.add(val);
		val.setColumns(10);

		Description = new JTextField();
		Description.setBounds(151, 127, 270, 20);
		contentPane.add(Description);
		Description.setColumns(30);

		JButton btnNext = new JButton("Add");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					mysqlcon a = new mysqlcon("root", "23");
					Connection con = a.connectup();
					String sql = "INSERT INTO prod (name, bar, val, shortcut) VALUES(?,?,?,?)";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);

					if (name.getText().equals("")
							|| barcode.getText().equals("")
							|| Description.getText().equals("")
							|| val.getText().equals("")) {
						JOptionPane.showMessageDialog(null,
								"You have not filled all the fields");

					} else {

						stmt.setString(1, name.getText());
						stmt.setString(2, barcode.getText());
						stmt.setInt(3, Integer.parseInt(val.getText()));
						stmt.setString(4, Description.getText());
						stmt.execute();
						insert frame = new insert();
						frame.setVisible(true);
						// JOptionPane.showMessageDialog(null,"Successful Add."
						// );
						setVisible(false);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNext.setBounds(141, 191, 89, 23);
		contentPane.add(btnNext);

		JButton btnfin = new JButton("Finish");
		btnfin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				admin frame = new admin();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnfin.setBounds(138, 225, 89, 23);
		contentPane.add(btnfin);

	}
}
