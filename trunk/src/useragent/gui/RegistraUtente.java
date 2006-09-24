package useragent.gui;

import jade.core.Agent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import starlight.util.Base64;
import useragent.senderbehaviour.RegistrationSender;
import content.RegistrationContent;

public class RegistraUtente extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4018741606282515436L;

	private Agent m_agent;

	private JPanel jpnlMain = null;

	private JPanel jpnlButton = null;

	private JButton jbtnInsert = null;

	private JPanel jpnlFields = null;

	private JLabel jlblUserName = null;

	private JTextField jtxtUserName = null;

	private JLabel jlblPasswd = null;

	private JPasswordField jtxtPasswd = null;

	private JLabel jlblName = null;

	private JTextField jtxtName = null;

	private JLabel jlblSurname = null;

	private JTextField jtxtSurname = null;

	private JLabel jlblEmail = null;

	private JTextField jtxtEmail = null;

	private JLabel jlblHomePage = null;

	private JTextField jtxtHomePage = null;

	public RegistraUtente(Frame parent, Agent agent)
	{
		super(parent);

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
		this.setContentPane(getJpnlMain());
		this.setResizable(false);
		this.setSize(new Dimension(468, 230));
		setTitle("Registrazione Utente");
		jtxtUserName.grabFocus();
		jtxtUserName.selectAll();
	}

	public void jbtnInsert_actionPerformed(ActionEvent e)
	{
		try
		{
			String password = md5(new String(jtxtPasswd.getPassword()));

			RegistrationContent rc = new RegistrationContent();
			rc.newRegistration(jtxtUserName.getText(), password);
			rc.setNome(jtxtName.getText());
			rc.setCognome(jtxtSurname.getText());
			rc.setEmail(jtxtEmail.getText());
			rc.setHomePage(jtxtHomePage.getText());

			RegistrationSender rs = new RegistrationSender(rc);
			m_agent.addBehaviour(rs);
			dispose();
		} catch (NoSuchAlgorithmException ex)
		{
			System.out.println("Errore codifica password");
		}
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
			jpnlMain.add(getJpnlButton(), BorderLayout.SOUTH);
			jpnlMain.add(getJpnlFields(), BorderLayout.CENTER);
		}
		return jpnlMain;
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
			jpnlButton.add(getJbtnInsert(), getJbtnInsert().getName());
		}
		return jpnlButton;
	}

	/**
	 * This method initializes jbtnInsert
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnInsert()
	{
		if (jbtnInsert == null)
		{
			jbtnInsert = new JButton();
			jbtnInsert.setText("Register");
			jbtnInsert.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnInsert_actionPerformed(e);
				}
			});
		}
		return jbtnInsert;
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
			jlblPasswd = new JLabel();
			jlblPasswd.setText("Password");
			jlblUserName = new JLabel();
			jlblUserName.setText("UserName");
			jpnlFields = new JPanel();
			jpnlFields.setLayout(new GridLayout(6, 2));
			jpnlFields.add(jlblUserName, jlblUserName.getName());
			jpnlFields.add(getJtxtUserName(), getJtxtUserName().getName());
			jpnlFields.add(jlblPasswd, jlblPasswd.getName());
			jpnlFields.add(getJtxtPasswd(), getJtxtPasswd().getName());
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
			jtxtUserName.setText("Inserisci qui lo username");
			jtxtUserName.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtPasswd.grabFocus();
					jtxtPasswd.selectAll();
				}
			});
		}
		return jtxtUserName;
	}

	/**
	 * This method initializes jtxtPasswd
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJtxtPasswd()
	{
		if (jtxtPasswd == null)
		{
			jtxtPasswd = new JPasswordField();
			jtxtPasswd.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtName.grabFocus();
					jtxtName.selectAll();
				}
			});
		}
		return jtxtPasswd;
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
			jtxtName.setText("Inserisci qui il tuo nome");
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
			jtxtSurname.setText("Inserisci qui il tuo cognome");
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
			jtxtEmail.setText("Inserisci qui la tua email");
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
			jtxtHomePage.setText("Inserisci qui l\'url della tua homepage");
			jtxtHomePage.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnInsert_actionPerformed(e);
				}
			});
		}
		return jtxtHomePage;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
