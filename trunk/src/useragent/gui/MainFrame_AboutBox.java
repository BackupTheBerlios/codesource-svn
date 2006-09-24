package useragent.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainFrame_AboutBox extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String product = "CodeSource";

	String version = "1.0";

	String copyright = "Copyright (c) 2005";

	String comments = "Sistema ad agenti per la gestione di una comunit� di programmatori software libero";

	private JPanel jpnlMain = null;

	private JPanel insetsPanel1 = null;

	private JButton button1 = null;

	private JPanel panel2 = null;

	private JPanel insetsPanel3 = null;

	private JLabel label1 = null;

	private JLabel label2 = null;

	private JLabel label3 = null;

	private JLabel label4 = null;

	public MainFrame_AboutBox(Frame parent)
	{
		super(parent);

		initialize();

		try
		{
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public MainFrame_AboutBox()
	{
		this(null);
	}

	/**
	 * Component initialization.
	 * 
	 * @throws java.lang.Exception
	 */
	private void initialize()
	{
		setTitle("About");
		this.setContentPane(getJpnlMain());
		button1.addActionListener(this);
		setResizable(true);
	}

	/**
	 * Close the dialog on a button event.
	 * 
	 * @param actionEvent
	 *            ActionEvent
	 */
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getSource() == button1)
		{
			dispose();
		}
	}

	/**
	 * This method initializes jpnlMain
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlMain()
	{
		if (jpnlMain == null)
		{
			jpnlMain = new JPanel();
			jpnlMain.setLayout(new BorderLayout());
			jpnlMain.add(getInsetsPanel1(), java.awt.BorderLayout.SOUTH);
			jpnlMain.add(getPanel2(), java.awt.BorderLayout.NORTH);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes insetsPanel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getInsetsPanel1()
	{
		if (insetsPanel1 == null)
		{
			insetsPanel1 = new JPanel();
			insetsPanel1.setLayout(new FlowLayout());
			insetsPanel1.add(getButton1(), null);
		}
		return insetsPanel1;
	}

	/**
	 * This method initializes button1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getButton1()
	{
		if (button1 == null)
		{
			button1 = new JButton();
			button1.setText("OK");
		}
		return button1;
	}

	/**
	 * This method initializes panel2
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanel2()
	{
		if (panel2 == null)
		{
			panel2 = new JPanel();
			panel2.setLayout(new BorderLayout());
			panel2.add(getInsetsPanel3(), java.awt.BorderLayout.CENTER);
		}
		return panel2;
	}

	/**
	 * This method initializes insetsPanel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getInsetsPanel3()
	{
		if (insetsPanel3 == null)
		{
			label4 = new JLabel();
			label4
					.setText("Sistema ad agenti per la gestione di una comunit\ufffd di programmatori software libero");
			label3 = new JLabel();
			label3.setText("Copyright (c) 2005");
			label2 = new JLabel();
			label2.setText("1.0");
			label1 = new JLabel();
			label1.setText("CodeSource");
			GridLayout gridLayout11 = new GridLayout();
			gridLayout11.setRows(4);
			gridLayout11.setColumns(1);
			insetsPanel3 = new JPanel();
			insetsPanel3.setBorder(BorderFactory.createEmptyBorder(10, 60, 10,
					10));
			insetsPanel3.setLayout(gridLayout11);
			insetsPanel3.add(label1, null);
			insetsPanel3.add(label2, null);
			insetsPanel3.add(label3, null);
			insetsPanel3.add(label4, null);
		}
		return insetsPanel3;
	}
}