package useragent.gui;

import jade.core.Agent;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import useragent.sql.db_interface;

public class SnipLocal extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Object[] m_data = null;

	private Agent m_agent = null;

	private db_interface m_db = null;

	private JPanel jpnlMain = null;

	private JPanel jpnlFields = null;

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

	private JPanel jpnlButtons = null;

	private JButton jbtnBugView = null;

	private JButton jbtnPatchView = null;

	private JButton jbtnCommentView = null;

	private JButton jbtnNewVer = null;

	public SnipLocal(Object[] data, Agent agent, db_interface db)
	{
		super("Ricerca ", true, // resizable
				true, // closable
				true, // maximizable
				true);

		m_data = data;
		m_agent = agent;
		m_db = db;

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

	/*
	 * public void jbtnDownload_actionPerformed(ActionEvent e) {
	 * System.out.println("Richiesta invio download"); DownloadRqContent dc =
	 * new DownloadRqContent(); dc.newDownload("ciao"); DownloadSender ds = new
	 * DownloadSender(dc); m_agent.addBehaviour(ds); }
	 * 
	 * public void jbtnVote_actionPerformed(ActionEvent e) { VoteContent vc =
	 * new VoteContent(); VoteSender vs = new VoteSender(vc);
	 * m_agent.addBehaviour(vs); }
	 * 
	 * public void jbtnSendPatch_actionPerformed(ActionEvent e) { PatchContent
	 * pc = new PatchContent(); PatchSender ps = new PatchSender(pc);
	 * m_agent.addBehaviour(ps); }
	 * 
	 * public void jbtnBug_actionPerformed(ActionEvent e) { BugContent bc = new
	 * BugContent(); BugSender bs = new BugSender(bc); m_agent.addBehaviour(bs); }
	 * 
	 * public void jbtnComment_actionPerformed(ActionEvent e) { CommentContent
	 * cc = new CommentContent(); CommentSender cs = new CommentSender(cc);
	 * m_agent.addBehaviour(cs); }
	 * 
	 * public void jbtnSubscribe_actionPerformed(ActionEvent e) {
	 * SubscribeContent sc = new SubscribeContent(); SubscribeSender ss = new
	 * SubscribeSender(sc); m_agent.addBehaviour(ss); }
	 */
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
			jpnlMain.add(getJpnlFields(), java.awt.BorderLayout.CENTER);
			jpnlMain.add(getJpnlButtons(), java.awt.BorderLayout.SOUTH);
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
			GridLayout gridLayout = new GridLayout();
			gridLayout.setColumns(2);
			gridLayout.setRows(9);

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

			jpnlFields = new JPanel();
			jpnlFields.setLayout(gridLayout);
			jpnlFields.add(jlblSnipName, jlblSnipName.getName());
			jpnlFields.add(jlbldatSnipName, null);
			jpnlFields.add(jlblSnipStatus, jlblSnipStatus.getName());
			jpnlFields.add(jlbldatSnipStatus, null);
			jpnlFields.add(jlblVersion, jlblVersion.getName());
			jpnlFields.add(jlbldatVersion, null);
			jpnlFields.add(jlblLang, jlblLang.getName());
			jpnlFields.add(jlbldatLang, null);
			jpnlFields.add(jlblDateSignIn, jlblDateSignIn.getName());
			jpnlFields.add(jlbldatDateSignIn, null);
			jpnlFields.add(jlblDateUpdate, jlblDateUpdate.getName());
			jpnlFields.add(jlbldatDateUpdate, null);
			jpnlFields.add(jlblRating, jlblRating.getName());
			jpnlFields.add(jlbldatRating, null);
			jpnlFields.add(jlblLicense, jlblLicense.getName());
			jpnlFields.add(jlbldatLicense, null);
			jpnlFields.add(jlblAuthor, jlblAuthor.getName());
			jpnlFields.add(jlbldatAuthor, null);
		}
		return jpnlFields;
	}

	/**
	 * This method initializes jpnlButtons
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlButtons()
	{
		if (jpnlButtons == null)
		{
			jpnlButtons = new JPanel();
			jpnlButtons.add(getJbtnNewVer(), null);
			jpnlButtons.add(getJbtnBugView(), null);
			jpnlButtons.add(getJbtnPatchView(), null);
			jpnlButtons.add(getJbtnCommentView(), null);
		}
		return jpnlButtons;
	}

	/**
	 * This method initializes jbtnBugView
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnBugView()
	{
		if (jbtnBugView == null)
		{
			jbtnBugView = new JButton();
			jbtnBugView.setText("Visualizza Bug");
			jbtnBugView.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					// m_db.get_bugs(m_data[0].toString());

					Vector data = m_db.get_bugs(m_data[0].toString());
					Bugs newver = new Bugs(data, m_agent, m_db);
					newver.setVisible(true);
					getParent().add(newver);

					try
					{
						newver.setSelected(true);
					} catch (java.beans.PropertyVetoException ex)
					{
						ex.printStackTrace();
					}
				}
			});
		}
		return jbtnBugView;
	}

	/**
	 * This method initializes jbtnPatchView
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnPatchView()
	{
		if (jbtnPatchView == null)
		{
			jbtnPatchView = new JButton();
			jbtnPatchView.setText("Visualizza Patch");
		}
		return jbtnPatchView;
	}

	/**
	 * This method initializes jbtnCommentView
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnCommentView()
	{
		if (jbtnCommentView == null)
		{
			jbtnCommentView = new JButton();
			jbtnCommentView.setText("Visualizza Commenti");
		}
		return jbtnCommentView;
	}

	/**
	 * This method initializes jbtnNewVer
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnNewVer()
	{
		if (jbtnNewVer == null)
		{
			jbtnNewVer = new JButton();
			jbtnNewVer.setText("Nuova versione");
			jbtnNewVer.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					NuovaVersioneSnip newver = new NuovaVersioneSnip(m_data,
							m_agent, m_db);
					newver.setVisible(true);
					getParent().add(newver);

					try
					{
						newver.setSelected(true);
					} catch (java.beans.PropertyVetoException ex)
					{
						ex.printStackTrace();
					}
				}
			});
		}
		return jbtnNewVer;
	}
}
/*
 * class jbtnDownload_actionAdapter implements ActionListener { private
 * SnipLocal m_adaptee;
 * 
 * jbtnDownload_actionAdapter(SnipLocal adaptee) { this.m_adaptee = adaptee; }
 * 
 * public void actionPerformed(ActionEvent actionEvent) {
 * m_adaptee.jbtnDownload_actionPerformed(actionEvent); } }
 * 
 * class jbtnVote_actionAdapter implements ActionListener { private SnipLocal
 * m_adaptee;
 * 
 * jbtnVote_actionAdapter(SnipLocal adaptee) { this.m_adaptee = adaptee; }
 * 
 * public void actionPerformed(ActionEvent actionEvent) {
 * m_adaptee.jbtnVote_actionPerformed(actionEvent); } }
 * 
 * class jbtnSendPatch_actionAdapter implements ActionListener { private
 * SnipLocal m_adaptee;
 * 
 * jbtnSendPatch_actionAdapter(SnipLocal adaptee) { this.m_adaptee = adaptee; }
 * 
 * public void actionPerformed(ActionEvent actionEvent) {
 * m_adaptee.jbtnSendPatch_actionPerformed(actionEvent); } }
 * 
 * class jbtnBug_actionAdapter implements ActionListener { private SnipLocal
 * m_adaptee;
 * 
 * jbtnBug_actionAdapter(SnipLocal adaptee) { this.m_adaptee = adaptee; }
 * 
 * public void actionPerformed(ActionEvent actionEvent) {
 * m_adaptee.jbtnBug_actionPerformed(actionEvent); } }
 * 
 * class jbtnComment_actionAdapter implements ActionListener { private SnipLocal
 * m_adaptee;
 * 
 * jbtnComment_actionAdapter(SnipLocal adaptee) { this.m_adaptee = adaptee; }
 * 
 * public void actionPerformed(ActionEvent actionEvent) {
 * m_adaptee.jbtnComment_actionPerformed(actionEvent); } }
 * 
 * class jbtnSubscribe_actionAdapter implements ActionListener { private
 * SnipLocal m_adaptee;
 * 
 * jbtnSubscribe_actionAdapter(SnipLocal adaptee) { this.m_adaptee = adaptee; }
 * 
 * public void actionPerformed(ActionEvent actionEvent) {
 * m_adaptee.jbtnSubscribe_actionPerformed(actionEvent); } }
 */