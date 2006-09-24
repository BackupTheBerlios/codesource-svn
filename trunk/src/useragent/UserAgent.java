package useragent;

import jade.core.Agent;

import java.awt.Dimension;

import useragent.gui.MainFrame;
import useragent.receiverbehaviour.BugReceiver;
import useragent.receiverbehaviour.CommentReceiver;
import useragent.receiverbehaviour.DownloadReceiver;
import useragent.receiverbehaviour.PatchReceiver;
import useragent.receiverbehaviour.SnipInfoRqReceiver;
import useragent.receiverbehaviour.SubscribeReceiver;
import useragent.sql.db_interface;

public class UserAgent extends Agent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private MainFrame m_win = null;

	private db_interface m_db = null;

	protected void setup()
	{
		System.out.println(getAID().getName() + getAID().getLocalName()
				+ " in esecuzione.");

		m_db = new db_interface(getAID().getLocalName() + ".db");

		BugReceiver bugrx = new BugReceiver(m_db);
		CommentReceiver commentrx = new CommentReceiver(m_db);
		PatchReceiver patchrx = new PatchReceiver(m_db);
		SnipInfoRqReceiver sirrx = new SnipInfoRqReceiver(m_db);
		DownloadReceiver downloadrx = new DownloadReceiver(m_db);
		SubscribeReceiver subscribrx = new SubscribeReceiver(m_db);

		addBehaviour(downloadrx);
		addBehaviour(bugrx);
		addBehaviour(commentrx);
		addBehaviour(patchrx);
		addBehaviour(sirrx);
		addBehaviour(subscribrx);

		Dimension d = new Dimension(800, 600);
		m_win = new MainFrame(this, m_db);
		m_win.setSize(d);
		m_win.setTitle("Codesource: " + getAID().getName()
				+ getAID().getLocalName());
		m_win.setVisible(true);
	}

	protected void takeDown()
	{
		System.out.println(getAID().getName() + getAID().getLocalName()
				+ " fine esecuzione.");

		m_db.shutdown();
	}

	public void registration(boolean success, String msg)
	{
		m_win.registration(success, msg);
	}

	public void login(String username, boolean success)
	{
		m_win.login(username, success);
	}

	public void logout(boolean success)
	{
		m_win.logout(success);
	}

	public void vote(boolean success)
	{
		m_win.vote(success);
	}

	public void authors(Object[][] data)
	{
		m_win.authors(data);
	}

	public void snips(Object[][] data)
	{
		m_win.snips(data);
	}

	public void snip(String[] data)
	{
		m_win.snip(data);
	}

	public void sniplocal(String[] data)
	{
		m_win.sniplocal(data);
	}

	public void download(String data)
	{
		m_win.download(data);
	}

	public void patch(boolean success)
	{
		m_win.patch(success);
	}

	public void bug(boolean success)
	{
		m_win.bug(success);
	}

	public void comment(boolean success)
	{
		m_win.comment(success);
	}

	public void subscribe(boolean success)
	{
		m_win.subscribe(success);
	}
	
	public void snipresponse(boolean success)
	{
		m_win.snipresponse(success);
	}
}