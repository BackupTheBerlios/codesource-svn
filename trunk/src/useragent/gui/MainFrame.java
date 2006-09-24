package useragent.gui;

import jade.core.Agent;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import useragent.senderbehaviour.LogoutSender;
import useragent.senderbehaviour.SearchSender;
import useragent.sql.db_interface;
import content.LogoutContent;
import content.SearchContent;

public class MainFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Agent m_agent = null;

	private db_interface m_db = null;

	JDesktopPane desktop = new JDesktopPane();

	JMenuBar jMenuBar1 = new JMenuBar();

	JMenu jmnuMain = new JMenu();

	JMenu jMenuHelp = new JMenu();

	JMenu jmnuManagment = new JMenu();

	JMenu jmnuSearch = new JMenu();

	JMenuItem jMenuHelpAbout = new JMenuItem();

	JMenuItem jmnuExit = new JMenuItem();

	JMenuItem jmnuMainLogin = new JMenuItem();

	JMenuItem jmnuMainLogout = new JMenuItem();

	JLabel statusBar = new JLabel();

	JMenuItem jmnuMainRegister = new JMenuItem();

	JMenuItem jMenuItem1 = new JMenuItem();

	JMenuItem jMenuItem2 = new JMenuItem();

	JMenuItem jmnuPubblicaSnip = new JMenuItem();

	JMenuItem jmnuSearchSnip = new JMenuItem();

	JMenuItem jmnuSearchAuthor = new JMenuItem();

	JMenuItem jmnuSearchAllSnip = new JMenuItem();

	JMenuItem jmnuSearchAllAuthor = new JMenuItem();

	private String m_username = null;

	private boolean m_logged = false;

	public MainFrame(Agent a, db_interface db)
	{
		m_agent = a;
		m_db = db;

		try
		{
			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			jbInit();
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
	private void jbInit() throws Exception
	{
		setContentPane(desktop);

		setSize(new Dimension(400, 300));
		statusBar.setText(" ");
		jmnuMain.setText("Main");
		jmnuExit.setText("Exit");
		jmnuExit.addActionListener(new MainFrame_jMenuFileExit_ActionAdapter(
				this));
		jMenuHelp.setText(";)");
		jMenuHelpAbout.setText("About");
		jMenuHelpAbout
				.addActionListener(new MainFrame_jMenuHelpAbout_ActionAdapter(
						this));
		jmnuMainLogin.setText("Login");
		jmnuMainLogin.addActionListener(new MainFrame_jmnuLogin_actionAdapter(
				this));

		jmnuMainLogout.setText("Logout");
		jmnuMainLogout.setEnabled(false);
		jmnuMainLogout
				.addActionListener(new MainFrame_jmnuLogout_actionAdapter(this));

		jmnuMainRegister.setText("Register");
		jmnuMainRegister
				.addActionListener(new MainFrame_jmnuMainRegister_actionAdapter(
						this));
		jmnuManagment.setEnabled(false);
		jmnuManagment.setText("SnipCode Management");

		jmnuSearchSnip.setText("Cerca Snip");
		jmnuSearchSnip
				.addActionListener(new MainFrame_jmnuSearchSnip_actionAdapter(
						this));

		jmnuSearchAuthor.setText("Cerca autore");
		jmnuSearchAuthor
				.addActionListener(new MainFrame_jmnuSearchAuthor_ActionAdapter(
						this));

		jmnuSearchAllSnip.setText("Tutti Snip");
		jmnuSearchAllSnip
				.addActionListener(new MainFrame_jmnuSearchAllSnip_actionAdapter(
						this));

		jmnuSearchAllAuthor.setText("Tutti autori");
		jmnuSearchAllAuthor
				.addActionListener(new MainFrame_jmnuSearchAllAuthor_ActionAdapter(
						this));

		jmnuSearch.setText("Cerca");
		jmnuSearch.setEnabled(false);

		jmnuPubblicaSnip.setText("Pubblica Snip");
		jmnuPubblicaSnip
				.addActionListener(new MainFrame_jmnuPubblicaSnip_ActionAdapter(
						this));

		jMenuItem2.setText("Chiude Bug");
		jMenuItem1.setText("Applica Patch");
		jMenuBar1.add(jmnuMain);
		jMenuBar1.add(jmnuSearch);
		jMenuBar1.add(jmnuManagment);
		jmnuMain.add(jmnuMainLogin);
		jmnuMain.add(jmnuMainLogout);
		jmnuMain.add(jmnuMainRegister);
		jmnuMain.add(jmnuExit);
		jMenuBar1.add(jMenuHelp);
		jMenuHelp.add(jMenuHelpAbout);
		setJMenuBar(jMenuBar1);
		jmnuManagment.add(jmnuPubblicaSnip);
		jmnuManagment.add(jMenuItem2);
		jmnuManagment.add(jMenuItem1);
		jmnuSearch.add(jmnuSearchAuthor);
		jmnuSearch.add(jmnuSearchSnip);
		jmnuSearch.add(jmnuSearchAllAuthor);
		jmnuSearch.add(jmnuSearchAllSnip);
	}

	/**
	 * File | Exit action performed.
	 * 
	 * @param actionEvent
	 *            ActionEvent
	 */
	void jMenuFileExit_actionPerformed(ActionEvent actionEvent)
	{
		m_agent.doDelete();
		dispose();
	}

	/**
	 * Help | About action performed.
	 * 
	 * @param actionEvent
	 *            ActionEvent
	 */
	void jMenuHelpAbout_actionPerformed(ActionEvent actionEvent)
	{
		MainFrame_AboutBox dlg = new MainFrame_AboutBox(this);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void jmnuLogin_actionPerformed(ActionEvent e)
	{
		Login dlg = new Login(this, m_agent);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);

	}

	public void jmnuLogout_actionPerformed(ActionEvent e)
	{
		LogoutContent loc = new LogoutContent();
		loc.newLogout(get_username());

		LogoutSender los = new LogoutSender(loc);
		m_agent.addBehaviour(los);
	}

	public void jmnuMainRegister_actionPerformed(ActionEvent e)
	{
		RegistraUtente dlg = new RegistraUtente(this, m_agent);
		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void jmnuSearchSnip_actionPerformed(ActionEvent e)
	{
		RicercaSnip rs = new RicercaSnip(m_agent);
		rs.setVisible(true);
		desktop.add(rs);

		try
		{
			rs.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}
	}/*
		 * RicercaSnip dlg = new RicercaSnip(this, m_agent); Dimension dlgSize =
		 * dlg.getPreferredSize(); Dimension frmSize = getSize(); Point loc =
		 * getLocation(); dlg.setLocation((frmSize.width - dlgSize.width) / 2 +
		 * loc.x, (frmSize.height - dlgSize.height) / 2 + loc.y);
		 * dlg.setModal(true); dlg.pack(); dlg.setVisible(true); }
		 */

	public void jmnuSearchAuthor_actionPerformed(ActionEvent e)
	{

		RicercaAutore ra = new RicercaAutore(m_agent);
		ra.setVisible(true);
		desktop.add(ra);

		try
		{
			ra.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}
		/*
		 * RicercaAutore dlg = new RicercaAutore(this, m_agent); Dimension
		 * dlgSize = dlg.getPreferredSize(); Dimension frmSize = getSize();
		 * Point loc = getLocation(); dlg.setLocation((frmSize.width -
		 * dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 +
		 * loc.y); dlg.setModal(true); dlg.pack(); dlg.setVisible(true);
		 */
	}

	public void jmnuSearchAllSnip_actionPerformed(ActionEvent e)
	{
		SearchContent sc = new SearchContent();
		sc.newSearchSnip();

		SearchSender ss = new SearchSender(sc);
		m_agent.addBehaviour(ss);
	}

	public void jmnuSearchAllAuthor_actionPerformed(ActionEvent e)
	{
		SearchContent sc = new SearchContent();
		sc.newSearchAuthor();

		SearchSender ss = new SearchSender(sc);
		m_agent.addBehaviour(ss);
	}

	public void jmnuPubblicaSnip_actionPerformed(ActionEvent e)
	{
		PubblicaCodice pc = new PubblicaCodice(m_username, m_agent, m_db);
		pc.setVisible(true);
		desktop.add(pc);

		try
		{
			pc.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}

		/*
		 * PubblicaCodice dlg = new PubblicaCodice(this, m_agent); Dimension
		 * dlgSize = new Dimension(320, 240); Dimension frmSize = getSize();
		 * Point loc = getLocation(); dlg.setLocation((frmSize.width -
		 * dlgSize.width) / 2 + loc.x, (frmSize.height - dlgSize.height) / 2 +
		 * loc.y); dlg.setModal(true); dlg.pack(); dlg.setVisible(true);
		 */
	}

	public void registration(boolean success, String msg)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Utente registrato con successo");
		} else
		{
			dlg = new MsgBox(this, "Utente non registrato: " + msg);
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void login(String username, boolean success)
	{
		m_username = username;
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Login di " + username
					+ " effettuato con successo");
			m_logged = true;
			jmnuMainLogin.setEnabled(false);
			jmnuMainRegister.setEnabled(false);
			jmnuMainLogout.setEnabled(true);
			jmnuManagment.setEnabled(true);
			jmnuSearch.setEnabled(true);
			jmnuExit.setEnabled(false);
		} else
		{
			dlg = new MsgBox(this, "Login di " + username + " fallito");
			m_logged = false;
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void logout(boolean success)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Logout di " + get_username()
					+ " effettuato con successo");
			m_logged = true;
			jmnuMainLogin.setEnabled(true);
			jmnuMainRegister.setEnabled(true);
			jmnuMainLogout.setEnabled(false);
			jmnuManagment.setEnabled(false);
			jmnuSearch.setEnabled(false);
			jmnuExit.setEnabled(true);

			JInternalFrame[] frames = desktop.getAllFrames();
			for (int i = 0; i < frames.length; i++)
				frames[i].dispose();

		} else
		{
			dlg = new MsgBox(this, "Logout di " + get_username() + "  fallito");
			m_logged = false;
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void vote(boolean success)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Votazione effettuata con successo");
		} else
		{
			dlg = new MsgBox(this, "Votazione non effettuata");
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void patch(boolean success)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Patch inoltrata con successo");
		} else
		{
			dlg = new MsgBox(this, "Patch non inoltrata");
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}
	
	public void bug(boolean success)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Bug inoltrato con successo");
		} else
		{
			dlg = new MsgBox(this, "Bug non inoltrata");
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}
	
	public void comment(boolean success)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Commento inoltrato con successo");
		} else
		{
			dlg = new MsgBox(this, "Commento non inoltrata");
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}

	public void subscribe(boolean success)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Sottoscrizione effettuata con successo");
		} else
		{
			dlg = new MsgBox(this, "Sottoscrizione non effettuata");
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}
	
	public void snipresponse(boolean success)
	{
		MsgBox dlg = null;
		if (success)
		{
			dlg = new MsgBox(this, "Pubblicazione effettuata con successo");
		} else
		{
			dlg = new MsgBox(this, "Pubblicazione non effettuata");
		}

		Dimension dlgSize = dlg.getPreferredSize();
		Dimension frmSize = getSize();
		Point loc = getLocation();
		dlg.setLocation((frmSize.width - dlgSize.width) / 2 + loc.x,
				(frmSize.height - dlgSize.height) / 2 + loc.y);
		dlg.setModal(true);
		dlg.pack();
		dlg.setVisible(true);
	}
	public void authors(Object[][] data)
	{
		Authors authors = new Authors(data);
		authors.setVisible(true);
		desktop.add(authors);
		try
		{
			authors.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}
	}

	public void snips(Object[][] data)
	{
		Snips snips = new Snips(data, m_agent, m_db);
		snips.setVisible(true);
		desktop.add(snips);

		try
		{
			snips.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}
	}

	public void snip(String[] data)
	{
		Snip snip = new Snip(data, m_agent, m_username);
		snip.setVisible(true);
		desktop.add(snip);

		try
		{
			snip.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}
	}

	public void sniplocal(String[] data)
	{
		SnipLocal snip = new SnipLocal(data, m_agent, m_db);
		snip.setVisible(true);
		desktop.add(snip);

		try
		{
			snip.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}
	}

	public void download(String data)
	{
		Body body = new Body(data);
		body.setVisible(true);
		desktop.add(body);

		try
		{
			body.setSelected(true);
		} catch (java.beans.PropertyVetoException ex)
		{
			ex.printStackTrace();
		}
	}

	public boolean logged()
	{
		return m_logged;
	}

	public String get_username()
	{
		return m_username;
	}
}

class MainFrame_jmnuSearchSnip_actionAdapter implements ActionListener
{
	private MainFrame adaptee;

	MainFrame_jmnuSearchSnip_actionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jmnuSearchSnip_actionPerformed(e);
	}
}

class MainFrame_jmnuLogin_actionAdapter implements ActionListener
{
	private MainFrame adaptee;

	MainFrame_jmnuLogin_actionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jmnuLogin_actionPerformed(e);
	}
}

class MainFrame_jmnuLogout_actionAdapter implements ActionListener
{
	private MainFrame adaptee;

	MainFrame_jmnuLogout_actionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jmnuLogout_actionPerformed(e);
	}
}

class MainFrame_jmnuMainRegister_actionAdapter implements ActionListener
{
	private MainFrame adaptee;

	MainFrame_jmnuMainRegister_actionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jmnuMainRegister_actionPerformed(e);
	}
}

class MainFrame_jMenuFileExit_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jMenuFileExit_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		adaptee.jMenuFileExit_actionPerformed(actionEvent);
	}
}

class MainFrame_jMenuHelpAbout_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jMenuHelpAbout_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		adaptee.jMenuHelpAbout_actionPerformed(actionEvent);
	}
}

class MainFrame_jmnuSearchAuthor_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jmnuSearchAuthor_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		adaptee.jmnuSearchAuthor_actionPerformed(actionEvent);
	}
}

class MainFrame_jmnuPubblicaSnip_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jmnuPubblicaSnip_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		adaptee.jmnuPubblicaSnip_actionPerformed(actionEvent);
	}
}

class MainFrame_jmnuSearchAllAuthor_ActionAdapter implements ActionListener
{
	MainFrame adaptee;

	MainFrame_jmnuSearchAllAuthor_ActionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent actionEvent)
	{
		adaptee.jmnuSearchAllAuthor_actionPerformed(actionEvent);
	}
}

class MainFrame_jmnuSearchAllSnip_actionAdapter implements ActionListener
{
	private MainFrame adaptee;

	MainFrame_jmnuSearchAllSnip_actionAdapter(MainFrame adaptee)
	{
		this.adaptee = adaptee;
	}

	public void actionPerformed(ActionEvent e)
	{
		adaptee.jmnuSearchAllSnip_actionPerformed(e);
	}
}