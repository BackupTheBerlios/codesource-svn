package serveragent;

import jade.core.Agent;
import serveragent.receiverbehaviour.LoginReceiver;
import serveragent.receiverbehaviour.LogoutReceiver;
import serveragent.receiverbehaviour.RegistrationReceiver;
import serveragent.receiverbehaviour.SearchReceiver;
import serveragent.receiverbehaviour.SnipReceiver;
import serveragent.receiverbehaviour.VoteReceiver;
import serveragent.sql.db_interface;

public class ServerAgent extends Agent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	protected void setup()
	{
		m_db = new db_interface("serveragent.db");

		System.out.println(getAID().getName()+" in esecuzione.");

		RegistrationReceiver regrx = new RegistrationReceiver(m_db);
		LoginReceiver logrx = new LoginReceiver(m_db);
		LogoutReceiver logorx = new LogoutReceiver(m_db);
		SearchReceiver searchrx = new SearchReceiver(m_db);
		SnipReceiver sniprx = new SnipReceiver(m_db);
		VoteReceiver voterx = new VoteReceiver(m_db);

		addBehaviour(regrx);
		addBehaviour(logrx);
		addBehaviour(logorx);
		addBehaviour(searchrx);
		addBehaviour(sniprx);
		addBehaviour(voterx);
	}

	protected void takeDown()
	{
		m_db.shutdown();
	}
}
