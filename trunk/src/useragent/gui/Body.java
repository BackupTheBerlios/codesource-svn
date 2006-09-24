package useragent.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Body extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel jpnlMain = null;

	private JScrollPane jscrPanel = null;

	private JTextArea jtxaBody = null;

	private String m_data = null;

	private JPanel jpnlButton = null;

	private JTextField jtxtFileName = null;

	private JButton jbtnSave = null;

	private JLabel jlblFileName = null;

	public Body(String data)
	{
		super("Snip body", true, // resizable
				true, // closable
				true, // maximizable
				true);

		m_data = data;

		initialize();
		try
		{
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			pack();
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize()
	{
		this.setSize(new Dimension(384, 215));
		this.setContentPane(getJpnlMain());
		jtxtFileName.grabFocus();
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
			jpnlMain.add(getJscrPanel(), BorderLayout.CENTER);
			jpnlMain.add(getJpnlButton(), BorderLayout.SOUTH);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes jscrPanel
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJscrPanel()
	{
		if (jscrPanel == null)
		{
			jscrPanel = new JScrollPane();
			jscrPanel.setViewportView(getJtxaBody());
		}
		return jscrPanel;
	}

	/**
	 * This method initializes jtxpBody
	 * 
	 * @return javax.swing.JTextPane
	 */
	private JTextArea getJtxaBody()
	{
		if (jtxaBody == null)
		{
			jtxaBody = new JTextArea();
			jtxaBody.setFocusable(false);
			jtxaBody.setRows(30);
			jtxaBody.setColumns(30);
			jtxaBody.setText(m_data);
		}
		return jtxaBody;
	}

	/**
	 * This method initializes jpnlButton
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlButton()
	{
		if (jpnlButton == null)
		{
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(1);
			gridLayout.setColumns(3);
			jlblFileName = new JLabel();
			jlblFileName.setText("Salva come:");
			jpnlButton = new JPanel();
			jpnlButton.setLayout(gridLayout);
			jpnlButton.add(jlblFileName, null);
			jpnlButton.add(getJtxtFileName(), null);
			jpnlButton.add(getJbtnSave(), null);
		}
		return jpnlButton;
	}

	/**
	 * This method initializes jtxtFileName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtFileName()
	{
		if (jtxtFileName == null)
		{
			jtxtFileName = new JTextField();
			jtxtFileName.setText("Inserisci qui il nome del file");
			jtxtFileName.selectAll();
			jtxtFileName.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					try
					{
						String curDir = System.getProperty("user.dir");
						PrintWriter out = new PrintWriter(new BufferedWriter(
								new FileWriter(curDir + "/"
										+ jtxtFileName.getText())));
						jtxaBody.write(out);
						out.close();
					} catch (FileNotFoundException ex)
					{

					} catch (IOException ex)
					{
					}
				}
			});
		}
		return jtxtFileName;
	}

	/**
	 * This method initializes jbtnSave
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnSave()
	{
		if (jbtnSave == null)
		{
			jbtnSave = new JButton();
			jbtnSave.setText("Salva");
			jbtnSave.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					try
					{
						PrintWriter out = new PrintWriter(new BufferedWriter(
								new FileWriter(jtxtFileName.getText())));
						jtxaBody.write(out);
						out.close();
					} catch (FileNotFoundException ex)
					{

					} catch (IOException ex)
					{
					}
				}
			});
		}
		return jbtnSave;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
