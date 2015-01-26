package cust;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JButton;

import firstlook.Login;
import firstlook.mysqlcon;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class cust extends JFrame {

	private JPanel contentPane;
	public int row;
	public String buffer;
	public JTable table;
	private JTextField txtdec;
	private JTextField txtadd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cust frame = new cust();
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
	public cust() {
		setTitle("Employee Form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 621, 313);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnDecr = new JButton("Decrease Quantity");
		btnDecr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					mysqlcon a = new mysqlcon("root", "23");
					Statement statement = a.Connect();
					String sql1 = "select * from prod";
					ResultSet rs = statement.executeQuery(sql1);
					Connection con = a.connectup();
					String sql = "UPDATE prod SET val = ? " + " WHERE name = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					int val = 0;
					while (rs.next()) {
						if (buffer.equals(rs.getString(1))) {
							val = rs.getInt(3);
						}

					}
					if (txtdec.getText().equals("")) {
						val = val - 1;
					} else {
						val = val - Integer.parseInt(txtdec.getText());
						if (val < 0) {
							val = val + Integer.parseInt(txtdec.getText());
							JOptionPane.showMessageDialog(null,
									"Not enough quantity .");
						}
					}
					stmt.setInt(1, val);
					stmt.setString(2, buffer);
					stmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				showtable();
			}
		});
		btnDecr.setBounds(10, 11, 170, 23);
		contentPane.add(btnDecr);

		JButton btnAdd = new JButton("Add Quantity");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					mysqlcon a = new mysqlcon("root", "23");
					Statement statement = a.Connect();
					String sql1 = "select * from prod";
					ResultSet rs = statement.executeQuery(sql1);
					Connection con = a.connectup();
					String sql = "UPDATE prod SET val = ? " + " WHERE name = ?";
					PreparedStatement stmt = con.prepareStatement(sql);
					int val = 0;
					while (rs.next()) {
						if (buffer.equals(rs.getString(1))) {
							val = rs.getInt(3);
						}

					}
					if (txtadd.getText().equals("")) {
						val = val + 1;
					} else {
						val = val + Integer.parseInt(txtadd.getText());
					}
					stmt.setInt(1, val);
					stmt.setString(2, buffer);
					stmt.execute();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				showtable();
			}

		});
		btnAdd.setBounds(309, 11, 170, 23);
		contentPane.add(btnAdd);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setModel(new DefaultTableModel(new Object[][] {
				{ "Name Product", "Barcode", "Quantity", "Description" },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, { null, null, null, null },
				{ null, null, null, null }, }, new String[] { "Name Product",
				"Barcode", "Quantity", "Description" }));
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent event) {
						row = table.getSelectedRow();
						buffer = table.getValueAt(table.getSelectedRow(), 0)
								.toString();

					}
				});
		table.setBounds(10, 63, 585, 160);
		contentPane.add(table);

		JButton btnOut = new JButton("Logout");
		btnOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login frame = new Login();
				frame.setVisible(true);
				setVisible(false);
			}
		});
		btnOut.setBounds(238, 241, 110, 23);
		contentPane.add(btnOut);

		txtdec = new JTextField();
		txtdec.setBounds(202, 12, 86, 20);
		contentPane.add(txtdec);
		txtdec.setColumns(10);

		txtadd = new JTextField();
		txtadd.setBounds(493, 12, 86, 20);
		contentPane.add(txtadd);
		txtadd.setColumns(10);
		showtable();

	}

	public void showtable() {
		try {

			mysqlcon a = new mysqlcon("root", "23");
			Statement statement = a.Connect();
			String sql1 = "select * from prod";
			ResultSet rs = statement.executeQuery(sql1);
			int li_row = 1;
			while (rs.next()) {

				table.setValueAt(rs.getString(1), li_row, 0);
				table.setValueAt(rs.getString(2), li_row, 1);
				table.setValueAt(rs.getInt(3), li_row, 2);
				table.setValueAt(rs.getString(4), li_row, 3);
				li_row++;
			}
		} catch (Exception err) {
			System.out.println("ERROR: " + err);
		}
	}
}
