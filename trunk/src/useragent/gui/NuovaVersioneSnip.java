package useragent.gui;

import jade.core.Agent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import useragent.senderbehaviour.SnipSender;
import useragent.sql.db_interface;
import content.SnipContent;

public class NuovaVersioneSnip extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object[] m_data = null;

	private Agent m_agent = null;

	private String m_username = null;

	private db_interface m_db = null;

	private JPanel jpnlMain = null;

	private JPanel jpnlCenter = null;

	private JPanel panel1 = null;

	private JLabel jlblSnipName = null;

	private JTextField jtxtSnipName = null;

	private JLabel jlblVersion = null;

	private JTextField jtxtVersion = null;

	private JLabel jlblLang = null;

	private JTextField jtxtLang = null;

	private JLabel jlblStatus = null;

	private JComboBox jcmbStatus = null;

	private JLabel jlblLicenza = null;

	private JTextField jtxtLicenza = null;

	private JLabel jlblFile = null;

	private JTextField jtxtFile = null;

	private JPanel panel3 = null;

	private JLabel jlblDescription = null;

	private JTextArea jtxaDescription = null;

	private JButton jbtnRelease = null;

	public NuovaVersioneSnip(Object[] data, Agent agent, db_interface db)
	{
		super("Nuova versione", false, // resizable
				true, // closable
				false, // maximizable
				true);// iconifiable

		m_data = data;
		m_agent = agent;
		m_db = db;
		m_username = m_data[1].toString();

		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 */
	private void initialize()
	{
		this.setSize(new Dimension(582, 346));
		this.setContentPane(getJpnlMain());
		jtxtVersion.grabFocus();
		jtxtVersion.selectAll();
	}

	public void jbtnRelease_actionPerformed(ActionEvent e)
	{
		SnipContent snc = new SnipContent();
		snc.newRelease(m_username, jtxtSnipName.getText());
		snc.setLanguage(jtxtLang.getText());
		snc.setStatus(jcmbStatus.getSelectedItem().toString());
		snc.setVersion(jtxtVersion.getText());
		snc.setLicenza(jtxtLicenza.getText());
		snc.setFile(jtxtFile.getText());
		snc.setDescription(jtxaDescription.getText());

		SnipSender ss = new SnipSender(snc);
		m_agent.addBehaviour(ss);
		dispose();

		m_db.newrelease(snc);
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
			jpnlMain.add(getJpnlCenter(), java.awt.BorderLayout.CENTER);
			jpnlMain.add(getJbtnRelease(), java.awt.BorderLayout.SOUTH);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes jpnlCenter
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlCenter()
	{
		if (jpnlCenter == null)
		{
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(2);
			jpnlCenter = new JPanel();
			jpnlCenter.setLayout(gridLayout1);
			jpnlCenter.add(getPanel1(), null);
			jpnlCenter.add(getPanel3(), null);
		}
		return jpnlCenter;
	}

	/**
	 * This method initializes panel1
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanel1()
	{
		if (panel1 == null)
		{
			jlblFile = new JLabel();
			jlblLicenza = new JLabel();
			jlblStatus = new JLabel();
			jlblLang = new JLabel();
			jlblVersion = new JLabel();
			jlblSnipName = new JLabel();

			jlblFile.setText("File");
			jlblLicenza.setText("Licenza");
			jlblStatus.setText("Status");
			jlblLang.setText("Language");
			jlblVersion.setText("Version");
			jlblSnipName.setText("SnipName");

			GridLayout gridLayout11 = new GridLayout();
			gridLayout11.setRows(6);
			gridLayout11.setColumns(2);

			panel1 = new JPanel();
			panel1.setLayout(gridLayout11);
			panel1.add(jlblSnipName, jlblSnipName.getName());
			panel1.add(getJtxtSnipName(), getJtxtSnipName().getName());
			panel1.add(jlblVersion, jlblVersion.getName());
			panel1.add(getJtxtVersion(), getJtxtVersion().getName());
			panel1.add(jlblLang, jlblLang.getName());
			panel1.add(getJtxtLang(), getJtxtLang().getName());
			panel1.add(jlblStatus, jlblStatus.getName());
			panel1.add(getJcmbStatus(), getJcmbStatus().getName());
			panel1.add(jlblLicenza, null);
			panel1.add(getJtxtLicenza(), null);
			panel1.add(jlblFile, null);
			panel1.add(getJtxtFile(), null);

		}
		return panel1;
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
			jtxtSnipName.setText(m_data[0].toString());
			jtxtSnipName.setEditable(false);
		}
		return jtxtSnipName;
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
			jtxtVersion.setText(m_data[6].toString());
			jtxtVersion.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtLang.grabFocus();
					jtxtLang.selectAll();
				}
			});
		}
		return jtxtVersion;
	}

	/**
	 * This method initializes jtxtLang
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtLang()
	{
		if (jtxtLang == null)
		{
			jtxtLang = new JTextField();
			jtxtLang.setText(m_data[2].toString());
			jtxtLang.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jcmbStatus.grabFocus();
				}
			});
		}
		return jtxtLang;
	}

	/**
	 * This method initializes jcmbStatus
	 * 
	 * @return javax.swing.JComboBox
	 */
	private JComboBox getJcmbStatus()
	{
		if (jcmbStatus == null)
		{
			jcmbStatus = new JComboBox();
			jcmbStatus.addItem(new String("Alpha"));
			jcmbStatus.addItem(new String("Beta"));
			jcmbStatus.addItem(new String("Stable"));
			this.setjcmbStatus(m_data[4].toString());
			jcmbStatus.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtLicenza.grabFocus();
					jtxtLicenza.selectAll();
				}
			});
		}
		return jcmbStatus;
	}

	private void setjcmbStatus(String value)
	{
		if (jcmbStatus != null)
		{
			if (value.equals("Alpha"))
				jcmbStatus.setSelectedIndex(0);
			if (value.equals("Beta"))
				jcmbStatus.setSelectedIndex(1);
			if (value.equals("Stable"))
				jcmbStatus.setSelectedIndex(2);
		}
	}

	/**
	 * This method initializes jtxtLicenza
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtLicenza()
	{
		if (jtxtLicenza == null)
		{
			jtxtLicenza = new JTextField();
			jtxtLicenza.setText(m_data[3].toString());
			jtxtLicenza.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtFile.grabFocus();
					jtxtFile.selectAll();
				}
			});
		}
		return jtxtLicenza;
	}

	/**
	 * This method initializes jtxtFile
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtFile()
	{
		if (jtxtFile == null)
		{
			jtxtFile = new JTextField();

			jtxtFile.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxaDescription.grabFocus();
					jtxaDescription.selectAll();
				}
			});
		}
		return jtxtFile;
	}

	/**
	 * This method initializes panel3
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanel3()
	{
		if (panel3 == null)
		{
			jlblDescription = new JLabel();
			jlblDescription.setText("Description");
			GridLayout gridLayout3 = new GridLayout(1, 1);
			gridLayout3.setRows(3);
			gridLayout3.setColumns(1);
			panel3 = new JPanel();
			panel3.setLayout(gridLayout3);
			panel3.add(jlblDescription, jlblDescription.getName());
			panel3.add(getJtxaDescription(), getJtxaDescription().getName());
		}
		return panel3;
	}

	/**
	 * This method initializes jtxaDescription
	 * 
	 * @return javax.swing.JTextArea
	 */
	private JTextArea getJtxaDescription()
	{
		if (jtxaDescription == null)
		{
			jtxaDescription = new JTextArea();
			jtxaDescription.setText("Inserisci qui la descrizione dello Snip");
			jtxaDescription.setSize(new Dimension(582, 200));
		}
		return jtxaDescription;
	}

	/**
	 * This method initializes jbtnRelease
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnRelease()
	{
		if (jbtnRelease == null)
		{
			jbtnRelease = new JButton();
			jbtnRelease.setText("Rilascia nuova versione");
			jbtnRelease.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnRelease_actionPerformed(e);
				}
			});
		}
		return jbtnRelease;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
