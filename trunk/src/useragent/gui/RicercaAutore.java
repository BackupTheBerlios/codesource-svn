package useragent.gui;

import jade.core.Agent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import starlight.util.Base64;
import useragent.senderbehaviour.SearchSender;
import content.SearchContent;

public class RicercaAutore extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Agent m_agent;

	private JPanel jpnlMain = null;

	private JPanel jpnlFields = null;

	private JLabel jlblUserName = null;

	private JTextField jtxtUserName = null;

	private JLabel jlblName = null;

	private JTextField jtxtName = null;

	private JLabel jlblSurname = null;

	private JTextField jtxtSurname = null;

	private JLabel jlblEmail = null;

	private JTextField jtxtEmail = null;

	private JLabel jlblHomePage = null;

	private JTextField jtxtHomePage = null;

	private JPanel jpnlButton = null;

	private JButton jbtnSearchAuthor = null;

	public RicercaAutore(Agent agent)
	{
		super("Ricerca autore", false, // resizable
				true, // closable
				false, // maximizable
				true);// iconifiable

		m_agent = agent;

		initialize();

		try
		{
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 * 
	 * @throws java.lang.Exception
	 */
	private void initialize()
	{
		setSize(new Dimension(454, 189));
		this.setContentPane(getJpnlMain());
		this.setResizable(false);
		jtxtUserName.grabFocus();
		jtxtUserName.selectAll();
	}

	public void jbtnSearchAuthor_actionPerformed(ActionEvent e)
	{
		SearchContent sc = new SearchContent();
		sc.newSearchAuthor();

		if (!jtxtUserName.getText().equals(""))
			sc.addSearch("username", jtxtUserName.getText());

		if (!jtxtName.getText().equals(""))
			sc.addSearch("nome", jtxtName.getText());

		if (!jtxtSurname.getText().equals(""))
			sc.addSearch("cognome", jtxtSurname.getText());

		if (!jtxtEmail.getText().equals(""))
			sc.addSearch("email", jtxtEmail.getText());

		if (!jtxtHomePage.getText().equals(""))
			sc.addSearch("homepage", jtxtHomePage.getText());

		SearchSender ss = new SearchSender(sc);
		m_agent.addBehaviour(ss);
		dispose();
	}

	public String md5(String value) throws NoSuchAlgorithmException
	{
		// MD5 password hashing
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(value.getBytes());
		byte[] raw = md.digest();
		return new String(Base64.encode(raw));
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
			jpnlMain.add(getJpnlFields(), BorderLayout.CENTER);
			jpnlMain.add(getJpnlButton(), BorderLayout.SOUTH);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes jpnlFields
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlFields()
	{
		if (jpnlFields == null)
		{
			jlblHomePage = new JLabel();
			jlblHomePage.setText("HomePage");
			jlblEmail = new JLabel();
			jlblEmail.setText("Email");
			jlblSurname = new JLabel();
			jlblSurname.setText("Last Name");
			jlblName = new JLabel();
			jlblName.setText("First Name");
			jlblUserName = new JLabel();
			jlblUserName.setText("UserName");
			jpnlFields = new JPanel();
			jpnlFields.setLayout(new GridLayout(5, 2));
			jpnlFields.add(jlblUserName, jlblUserName.getName());
			jpnlFields.add(getJtxtUserName(), getJtxtUserName().getName());
			jpnlFields.add(jlblName, jlblName.getName());
			jpnlFields.add(getJtxtName(), getJtxtName().getName());
			jpnlFields.add(jlblSurname, jlblSurname.getName());
			jpnlFields.add(getJtxtSurname(), getJtxtSurname().getName());
			jpnlFields.add(jlblEmail, jlblEmail.getName());
			jpnlFields.add(getJtxtEmail(), getJtxtEmail().getName());
			jpnlFields.add(jlblHomePage, jlblHomePage.getName());
			jpnlFields.add(getJtxtHomePage(), getJtxtHomePage().getName());
		}
		return jpnlFields;
	}

	/**
	 * This method initializes jtxtUserName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtUserName()
	{
		if (jtxtUserName == null)
		{
			jtxtUserName = new JTextField();
			jtxtUserName.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtName.grabFocus();
					jtxtName.selectAll();
				}
			});
		}
		return jtxtUserName;
	}

	/**
	 * This method initializes jtxtName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtName()
	{
		if (jtxtName == null)
		{
			jtxtName = new JTextField();
			jtxtName.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtSurname.grabFocus();
					jtxtSurname.selectAll();
				}
			});
		}
		return jtxtName;
	}

	/**
	 * This method initializes jtxtSurname
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtSurname()
	{
		if (jtxtSurname == null)
		{
			jtxtSurname = new JTextField();
			jtxtSurname.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtEmail.grabFocus();
					jtxtEmail.selectAll();
				}
			});
		}
		return jtxtSurname;
	}

	/**
	 * This method initializes jtxtEmail
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtEmail()
	{
		if (jtxtEmail == null)
		{
			jtxtEmail = new JTextField();
			jtxtEmail.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtHomePage.grabFocus();
					jtxtHomePage.selectAll();
				}
			});
		}
		return jtxtEmail;
	}

	/**
	 * This method initializes jtxtHomePage
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtHomePage()
	{
		if (jtxtHomePage == null)
		{
			jtxtHomePage = new JTextField();
			jtxtHomePage.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnSearchAuthor_actionPerformed(e);
				}
			});
		}
		return jtxtHomePage;
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
			jpnlButton = new JPanel();
			jpnlButton.setLayout(new FlowLayout());
			jpnlButton.add(getJbtnSearchAuthor(), getJbtnSearchAuthor()
					.getName());
		}
		return jpnlButton;
	}

	/**
	 * This method initializes jbtnSearchAuthor
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnSearchAuthor()
	{
		if (jbtnSearchAuthor == null)
		{
			jbtnSearchAuthor = new JButton();
			jbtnSearchAuthor.setText("Cerca");

			jbtnSearchAuthor
					.addActionListener(new java.awt.event.ActionListener()
					{
						public void actionPerformed(java.awt.event.ActionEvent e)
						{
							jbtnSearchAuthor_actionPerformed(e);
						}
					});
		}
		return jbtnSearchAuthor;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
