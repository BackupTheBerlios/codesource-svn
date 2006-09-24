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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import starlight.util.Base64;
import useragent.senderbehaviour.SearchSender;
import content.SearchContent;

public class RicercaSnip extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Agent m_agent;

	private JPanel jpnlMain = null;

	private JPanel jpnlButton = null;

	private JButton jbtnSearch = null;

	private JPanel jpnlFields = null;

	private JLabel jlblSnipName = null;

	private JTextField jtxtSnipName = null;

	private JLabel jlblAuthor = null;

	private JTextField jtxtAuthor = null;

	private JLabel jlblLanguage = null;

	private JTextField jtxtLanguage = null;

	private JLabel jlblStatus = null;

	private JComboBox jcmbStatus = null;

	private JLabel jlblVersion = null;

	private JTextField jtxtVersion = null;

	private JLabel jlblRegDate = null;

	private JTextField jtxtRegDate = null;

	private JLabel jlblEditDate = null;

	private JTextField jtxtEditDate = null;

	public RicercaSnip(Agent agent)
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
		setSize(new Dimension(577, 205));
		this.setContentPane(getJpnlMain());
		this.setResizable(false);
		setTitle("Registrazione Utente");
		jtxtSnipName.grabFocus();
		jtxtSnipName.selectAll();
	}

	public void jbtnInsert_actionPerformed(ActionEvent e)
	{
		SearchContent sc = new SearchContent();
		sc.newSearchSnip();

		if (!jtxtSnipName.getText().equals(""))
			sc.addSearch("snipname", jtxtSnipName.getText());

		if (!jtxtAuthor.getText().equals(""))
			sc.addSearch("author", jtxtAuthor.getText());

		if (!jtxtLanguage.getText().equals(""))
			sc.addSearch("language", jtxtLanguage.getText());

		if (!jcmbStatus.getSelectedItem().toString().equals(""))
			sc.addSearch("status", jcmbStatus.getSelectedItem().toString());

		if (!jtxtVersion.getText().equals(""))
			sc.addSearch("version", jtxtVersion.getText());

		if (!jtxtRegDate.getText().equals(""))
			sc.addSearch("registrazione", jtxtRegDate.getText());

		if (!jtxtEditDate.getText().equals(""))
			sc.addSearch("modifica", jtxtEditDate.getText());

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
			jpnlButton.add(getJbtnSearch(), getJbtnSearch().getName());
		}
		return jpnlButton;
	}

	/**
	 * This method initializes jbtnSearch
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnSearch()
	{
		if (jbtnSearch == null)
		{
			jbtnSearch = new JButton();
			jbtnSearch.setText("Cerca");
			jbtnSearch.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnInsert_actionPerformed(e);
				}
			});
		}
		return jbtnSearch;
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
			jlblEditDate = new JLabel();
			jlblEditDate.setText("EditDate");
			jlblRegDate = new JLabel();
			jlblRegDate.setText("RegDate");
			jlblVersion = new JLabel();
			jlblVersion.setText("Version");
			jlblStatus = new JLabel();
			jlblStatus.setText("Status");
			jlblLanguage = new JLabel();
			jlblLanguage.setText("Language");
			jlblAuthor = new JLabel();
			jlblAuthor.setText("Author");
			jlblSnipName = new JLabel();
			jlblSnipName.setText("SnipName");
			jpnlFields = new JPanel();
			jpnlFields.setLayout(new GridLayout(7, 2));
			jpnlFields.add(jlblSnipName, jlblSnipName.getName());
			jpnlFields.add(getJtxtSnipName(), getJtxtSnipName().getName());
			jpnlFields.add(jlblAuthor, jlblAuthor.getName());
			jpnlFields.add(getJtxtAuthor(), getJtxtAuthor().getName());
			jpnlFields.add(jlblLanguage, jlblLanguage.getName());
			jpnlFields.add(getJtxtLanguage(), getJtxtLanguage().getName());
			jpnlFields.add(jlblStatus, jlblStatus.getName());
			jpnlFields.add(getJcmbStatus(), getJcmbStatus().getName());
			jpnlFields.add(jlblVersion, jlblVersion.getName());
			jpnlFields.add(getJtxtVersion(), getJtxtVersion().getName());
			jpnlFields.add(jlblRegDate, jlblRegDate.getName());
			jpnlFields.add(getJtxtRegDate(), getJtxtRegDate().getName());
			jpnlFields.add(jlblEditDate, jlblEditDate.getName());
			jpnlFields.add(getJtxtEditDate(), getJtxtEditDate().getName());
		}
		return jpnlFields;
	}

	/**
	 * This method initializes jtxtSnipName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtSnipName()
	{
		if (jtxtSnipName == null)
		{
			jtxtSnipName = new JTextField();
			jtxtSnipName.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtAuthor.grabFocus();
					jtxtAuthor.selectAll();
				}
			});
		}
		return jtxtSnipName;
	}

	/**
	 * This method initializes jtxtAuthor
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtAuthor()
	{
		if (jtxtAuthor == null)
		{
			jtxtAuthor = new JTextField();
			jtxtAuthor.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtLanguage.grabFocus();
					jtxtLanguage.selectAll();
				}
			});
		}
		return jtxtAuthor;
	}

	/**
	 * This method initializes jtxtLanguage
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtLanguage()
	{
		if (jtxtLanguage == null)
		{
			jtxtLanguage = new JTextField();
			jtxtLanguage.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jcmbStatus.grabFocus();
				}
			});
		}
		return jtxtLanguage;
	}

	/**
	 * This method initializes jcmbStatus
	 * 
	 * @return javax.swing.JTextField
	 */
	private JComboBox getJcmbStatus()
	{
		if (jcmbStatus == null)
		{
			jcmbStatus = new JComboBox();
			jcmbStatus.addItem(new String(""));
			jcmbStatus.addItem(new String("Alpha"));
			jcmbStatus.addItem(new String("Beta"));
			jcmbStatus.addItem(new String("Stable"));
			jcmbStatus.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtVersion.grabFocus();
				}
			});
		}
		return jcmbStatus;
	}

	/**
	 * This method initializes jtxtVersion
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtVersion()
	{
		if (jtxtVersion == null)
		{
			jtxtVersion = new JTextField();
			jtxtVersion.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtRegDate.grabFocus();
					jtxtRegDate.selectAll();
				}
			});
		}
		return jtxtVersion;
	}

	/**
	 * This method initializes jtxtRegDate
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtRegDate()
	{
		if (jtxtRegDate == null)
		{
			jtxtRegDate = new JTextField();
			jtxtRegDate.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtEditDate.grabFocus();
					jtxtEditDate.selectAll();
				}
			});
		}
		return jtxtRegDate;
	}

	/**
	 * This method initializes jtxtEditDate
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtEditDate()
	{
		if (jtxtEditDate == null)
		{
			jtxtEditDate = new JTextField();
			jtxtEditDate.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnInsert_actionPerformed(e);
				}
			});
		}
		return jtxtEditDate;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
