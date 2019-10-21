package project1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

public class BlankFrame extends JFrame
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldWithdraw;
	private JTextField textFieldDeposit;
	final JLabel labelFooter = new JLabel("Your balance is:");


	BankAccount account = new BankAccount(1, 500);

	/**
	 * Launching the application
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					BlankFrame frame = new BlankFrame();
					frame.setVisible(true);

				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * check for numeric keys needed
	 */
	public static boolean isNumeric(String strNum)
	{
		return strNum.matches("^-?\\d+(\\.\\d+)?$");
	}

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public BlankFrame() throws SQLException
	{
		// Set parameters of frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		labelFooter.setBounds(5, 232, 422, 16);

		// Add balance label to the appropriate space
		labelFooter.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelFooter);

		// Creates main frame panel
		JPanel panel = new JPanel();
		panel.setBounds(15, 22, 402, 210);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(4, 3, 0, 0));

		// Spacing panel
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setHgap(10);
		panel.add(panel_1);
		JButton buttonWithdraw = new JButton("Withdraw");
		panel_1.add(buttonWithdraw);
		buttonWithdraw.setHorizontalAlignment(SwingConstants.RIGHT);
		buttonWithdraw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("\"" + textFieldWithdraw.getText() + "\"");
				// Doesn't proceed if value is null
				if (textFieldWithdraw.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Number invalid!", "Error!", JOptionPane.ERROR_MESSAGE);
					textFieldWithdraw.setText("");
					return;
				}

				try
				{
					double amount = Double.valueOf(textFieldWithdraw.getText());
					if (amount > account.getBalance())
					{
						JOptionPane.showMessageDialog(null, "Balance too low!", "Not enough funds",
								JOptionPane.INFORMATION_MESSAGE);
						textFieldWithdraw.setText("");
						return;
					} else

						account.withdraw(amount);
					textFieldWithdraw.setText("");
					labelFooter.setText("Your balance is: £" + String.format("%.2f", account.getBalance()));
				} catch (NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(null, "Number invalid!", "Error!", JOptionPane.ERROR_MESSAGE);
					textFieldWithdraw.setText("");
				} catch (Exception ex)
				{
				}
			}
		});

		// Withdraw button listener
		textFieldWithdraw = new JTextField();
		labelFooter.setText("Your balance is: £" + String.format("%.2f", account.getBalance()));


		/**
		 * withdraw button and actions
		 */

		// Spacing panels
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		JPanel panel_3 = new JPanel();
		panel.add(panel_3);

		// Adds text field for amount to be withdrawn
		panel_3.add(textFieldWithdraw);
		textFieldWithdraw.setColumns(10);

		// Spacing panels
		JPanel panel_4 = new JPanel();
		panel.add(panel_4);
		JPanel panel_9 = new JPanel();
		panel.add(panel_9);
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		JPanel panel_8 = new JPanel();
		panel.add(panel_8);

		/**
		 * Listener for deposit button
		 */

		textFieldDeposit = new JTextField();
		JButton buttonDeposit = new JButton("Deposit");
		buttonDeposit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.out.println("\"" + textFieldDeposit.getText() + "\"");
				// Doesn't proceed if value is null
				if (textFieldDeposit.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Number invalid!", "Error!", JOptionPane.ERROR_MESSAGE);
					textFieldDeposit.setText("");
					return;
				}
				try
				{
					double amount = Double.valueOf(textFieldDeposit.getText());
					account.deposit(amount);
					textFieldDeposit.setText("");
					labelFooter.setText("Your balance is: £" + String.format("%.2f", account.getBalance()));
				} catch (NumberFormatException ex)
				{
					JOptionPane.showMessageDialog(null, "Number invalid!", "Error!", JOptionPane.ERROR_MESSAGE);
					textFieldDeposit.setText("");
				} catch (Exception ex)
				{
				}
			}
		});
		panel_8.add(buttonDeposit);

		// Spacing panels
		JPanel panel_6 = new JPanel();
		panel.add(panel_6);
		JPanel panel_7 = new JPanel();
		panel.add(panel_7);

		// Add deposit button to respective location
		panel_7.add(textFieldDeposit);
		textFieldDeposit.setColumns(10);

		// Spacing panels
		JPanel panel_11 = new JPanel();
		panel.add(panel_11);
		JPanel panel_12 = new JPanel();
		panel.add(panel_12);
		panel_12.setLayout(new BorderLayout(0, 0));

		// Balance button listener
		JButton buttonBalance = new JButton("Show Balance");
		panel_12.add(buttonBalance, BorderLayout.NORTH);
		buttonBalance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					labelFooter.setText("Your balance is: £" + String.format("%.2f", account.getBalance()));
				} catch (SQLException e1)
				{
					e1.printStackTrace();
				}
			}
		});

		// Spacing panel
		JPanel panel_10 = new JPanel();
		panel.add(panel_10);

		// Add bank name label
		JLabel labelHeader = new JLabel("Bank of Utopia");
		labelHeader.setBounds(5, 5, 422, 17);
		labelHeader.setFont(new Font("Tahoma", Font.BOLD, 14));
		labelHeader.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(labelHeader);

		// Spacing panels
		JPanel panel_13 = new JPanel();
		panel_13.setBounds(5, 22, 10, 210);
		contentPane.add(panel_13);
		JPanel panel_14 = new JPanel();
		panel_14.setBounds(417, 22, 10, 210);
		contentPane.add(panel_14);

	}

}
