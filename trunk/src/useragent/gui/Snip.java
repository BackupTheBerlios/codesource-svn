package useragent.gui;

import jade.core.Agent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import useragent.senderbehaviour.DownloadSender;
import useragent.senderbehaviour.SubscribeSender;
import content.DownloadRqContent;
import content.SubscribeContent;

public class Snip extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object[] m_data = null;

	Agent m_agent = null;

	private String m_username = null;

	private JPanel jpnlMain = null;

	private JPanel panel2 = null;

	private JLabel jlblSnipName = null;

	private JLabel jlbldatSnipName = null;

	private JLabel jlblSnipStatus = null;

	private JLabel jlbldatSnipStatus = null;

	private JLabel jlblVersion = null;

	private JLabel jlbldatVersion = null;

	private JLabel jlblLang = null;

	private JLabel jlbldatLang = null;

	private JLabel jlblDateSignIn = null;

	private JLabel jlbldatDateSignIn = null;

	private JLabel jlblDateUpdate = null;

	private JLabel jlbldatDateUpdate = null;

	private JLabel jlblRating = null;

	private JLabel jlbldatRating = null;

	private JLabel jlblLicense = null;

	private JLabel jlbldatLicense = null;

	private JLabel jlblAuthor = null;

	private JLabel jlbldatAuthor = null;

	private JLabel jlblSources = null;

	private JLabel jlbldatSources = null;

	private JPanel panel4 = null;

	private JButton jbtnDownload = null;

	private JButton jbtnVote = null;

	private JButton jbtnSendPatch = null;

	private JButton jbtnBug = null;

	private JButton jbtnComment = null;

	private JButton jbtnSubscribe = null;

	public Snip(Object[] data, Agent agent, String username)
	{
		super("Ricerca ", true, // resizable
				true, // closable
				true, // maximizable
				true);

		m_data = data;
		m_agent = agent;
		m_username = username;

		initialize();// iconifiable

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
		this.setContentPane(getJpnlMain());
	}

	public void jbtnDownload_actionPerformed(ActionEvent e)
	{
		System.out.println("Richiesta invio download");
		DownloadRqContent dc = new DownloadRqContent();
		dc.newDownload(m_data[0].toString());
		DownloadSender ds = new DownloadSender(dc, m_data[10].toString());
		m_agent.addBehaviour(ds);
	}

	public void jbtnVote_actionPerformed(ActionEvent e)
	{
		VoteBox dlg = new VoteBox("Inserire il voto", m_agent, m_data[0]
				.toString());

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void jbtnSendPatch_actionPerformed(ActionEvent e)
	{
		PatchBox dlg = new PatchBox("Inserire il file della patch", m_agent,
				m_data[10].toString(), m_data[0].toString(), m_username);

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void jbtnBug_actionPerformed(ActionEvent e)
	{
		BugBox dlg = new BugBox("Descrivere il bug", m_agent,
				m_data[10].toString(), m_data[0].toString(), m_username);

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x+100,
				(frmSize.height - dlgSize.height) / 2 + loc.y+100);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void jbtnComment_actionPerformed(ActionEvent e)
	{
		CommentBox dlg = new CommentBox("Inserisci qui il commento", m_agent,
				m_data[10].toString(), m_data[0].toString(), m_username);

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x+100,
				(frmSize.height - dlgSize.height) / 2 + loc.y+100);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void jbtnSubscribe_actionPerformed(ActionEvent e)
	{
		SubscribeContent sc = new SubscribeContent();
		sc.newSubscribe(m_data[0].toString(), m_username);
		SubscribeSender ss = new SubscribeSender(sc, m_data[10].toString());
		m_agent.addBehaviour(ss);
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
			jpnlMain.add(getPanel2(), java.awt.BorderLayout.CENTER);
			jpnlMain.add(getPanel4(), java.awt.BorderLayout.SOUTH);
		}
		return jpnlMain;
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
			GridLayout gridLayout = new GridLayout();
			gridLayout.setColumns(2);
			gridLayout.setRows(10);

			jlbldatSources = new JLabel();
			jlblSources = new JLabel();
			jlbldatAuthor = new JLabel();
			jlblAuthor = new JLabel();
			jlbldatLicense = new JLabel();
			jlblLicense = new JLabel();
			jlbldatRating = new JLabel();
			jlblRating = new JLabel();
			jlbldatDateUpdate = new JLabel();
			jlblDateUpdate = new JLabel();
			jlbldatDateSignIn = new JLabel();
			jlblDateSignIn = new JLabel();
			jlbldatLang = new JLabel();
			jlblLang = new JLabel();
			jlbldatVersion = new JLabel();
			jlblVersion = new JLabel();
			jlbldatSnipStatus = new JLabel();
			jlblSnipStatus = new JLabel();
			jlbldatSnipName = new JLabel();
			jlblSnipName = new JLabel();

			jlblSources.setText("Sorgenti disponibili");
			jlblAuthor.setText("Autore");
			jlblLicense.setText("Licenze");
			jlblRating.setText("Rating");
			jlblDateUpdate.setText("Data modifica");
			jlblDateSignIn.setText("Data publicazione");
			jlblLang.setText("Linguaggio");
			jlblVersion.setText("Versione");
			jlblSnipStatus.setText("Stato");
			jlblSnipName.setText("Nome");

			jlbldatSnipName.setText(m_data[0].toString());
			jlbldatAuthor.setText(m_data[1].toString());
			jlbldatLang.setText(m_data[2].toString());
			jlbldatLicense.setText(m_data[3].toString());
			jlbldatSnipStatus.setText(m_data[4].toString());
			jlbldatRating.setText(m_data[5].toString());
			jlbldatVersion.setText(m_data[6].toString());
			jlbldatDateSignIn.setText(m_data[7].toString());
			jlbldatDateUpdate.setText(m_data[8].toString());

			panel2 = new JPanel();
			panel2.setLayout(gridLayout);
			panel2.add(jlblSnipName, jlblSnipName.getName());
			panel2.add(jlbldatSnipName, null);
			panel2.add(jlblSnipStatus, jlblSnipStatus.getName());
			panel2.add(jlbldatSnipStatus, null);
			panel2.add(jlblVersion, jlblVersion.getName());
			panel2.add(jlbldatVersion, null);
			panel2.add(jlblLang, jlblLang.getName());
			panel2.add(jlbldatLang, null);
			panel2.add(jlblDateSignIn, jlblDateSignIn.getName());
			panel2.add(jlbldatDateSignIn, null);
			panel2.add(jlblDateUpdate, jlblDateUpdate.getName());
			panel2.add(jlbldatDateUpdate, null);
			panel2.add(jlblRating, jlblRating.getName());
			panel2.add(jlbldatRating, null);
			panel2.add(jlblLicense, jlblLicense.getName());
			panel2.add(jlbldatLicense, null);
			panel2.add(jlblAuthor, jlblAuthor.getName());
			panel2.add(jlbldatAuthor, null);
			panel2.add(jlblSources, jlblSources.getName());
			panel2.add(jlbldatSources, null);
		}
		return panel2;
	}

	/**
	 * This method initializes panel4
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getPanel4()
	{
		if (panel4 == null)
		{
			panel4 = new JPanel();
			panel4.add(getJbtnDownload(), getJbtnDownload().getName());
			panel4.add(getJbtnVote(), getJbtnVote().getName());
			panel4.add(getJbtnSendPatch(), getJbtnSendPatch().getName());
			panel4.add(getJbtnBug(), getJbtnBug().getName());
			panel4.add(getJbtnComment(), getJbtnComment().getName());
			panel4.add(getJbtnSubscribe(), getJbtnSubscribe().getName());
		}
		return panel4;
	}

	/**
	 * This method initializes jbtnDownload
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnDownload()
	{
		if (jbtnDownload == null)
		{
			jbtnDownload = new JButton();
			jbtnDownload.setText("Scarica");
			jbtnDownload
					.addActionListener(new jbtnDownload_actionAdapter(this));
		}
		return jbtnDownload;
	}

	/**
	 * This method initializes jbtnVote
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnVote()
	{
		if (jbtnVote == null)
		{
			jbtnVote = new JButton();
			jbtnVote.setText("Vota Snip");
			jbtnVote.addActionListener(new jbtnVote_actionAdapter(this));
		}
		return jbtnVote;
	}

	/**
	 * This method initializes jbtnSendPatch
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnSendPatch()
	{
		if (jbtnSendPatch == null)
		{
			jbtnSendPatch = new JButton();
			jbtnSendPatch.setText("Invia Patch");
			jbtnSendPatch.addActionListener(new jbtnSendPatch_actionAdapter(
					this));
		}
		return jbtnSendPatch;
	}

	/**
	 * This method initializes jbtnBug
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnBug()
	{
		if (jbtnBug == null)
		{
			jbtnBug = new JButton();
			jbtnBug.setText("Segnala Bug");
			jbtnBug.addActionListener(new jbtnBug_actionAdapter(
					this));
		}
		return jbtnBug;
	}

	/**
	 * This method initializes jbtnComment
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnComment()
	{
		if (jbtnComment == null)
		{
			jbtnComment = new JButton();
			jbtnComment.setText("Invia Commento");
			jbtnComment.addActionListener(new jbtnComment_actionAdapter(this));
		}
		return jbtnComment;
	}

	/**
	 * This method initializes jbtnSubscribe
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnSubscribe()
	{
		if (jbtnSubscribe == null)
		{
			jbtnSubscribe = new JButton();
			jbtnSubscribe.setText("Sottoscrivi");
			jbtnSubscribe.addActionListener(new jbtnSubscribe_actionAdapter(
					this));
		}
		return jbtnSubscribe;
	}
}

class jbtnDownload_actionAdapter implements ActionListener
{
	private Snip m_adaptee;

	jbtnDownload_actionAdapter(Snip adaptee)
	{
		this.m_adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		m_adaptee.jbtnDownload_actionPerformed(actionEvent);
	}
}

class jbtnVote_actionAdapter implements ActionListener
{
	private Snip m_adaptee;

	jbtnVote_actionAdapter(Snip adaptee)
	{
		this.m_adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		m_adaptee.jbtnVote_actionPerformed(actionEvent);
	}
}

class jbtnSendPatch_actionAdapter implements ActionListener
{
	private Snip m_adaptee;

	jbtnSendPatch_actionAdapter(Snip adaptee)
	{
		this.m_adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		m_adaptee.jbtnSendPatch_actionPerformed(actionEvent);
	}
}

class jbtnBug_actionAdapter implements ActionListener
{
	private Snip m_adaptee;

	jbtnBug_actionAdapter(Snip adaptee)
	{
		this.m_adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		m_adaptee.jbtnBug_actionPerformed(actionEvent);
	}
}

class jbtnComment_actionAdapter implements ActionListener
{
	private Snip m_adaptee;

	jbtnComment_actionAdapter(Snip adaptee)
	{
		this.m_adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		m_adaptee.jbtnComment_actionPerformed(actionEvent);
	}
}

class jbtnSubscribe_actionAdapter implements ActionListener
{
	private Snip m_adaptee;

	jbtnSubscribe_actionAdapter(Snip adaptee)
	{
		this.m_adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		m_adaptee.jbtnSubscribe_actionPerformed(actionEvent);
	}
}