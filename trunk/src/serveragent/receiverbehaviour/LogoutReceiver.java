package serveragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import serveragent.sql.db_interface;
import content.LogoutContent;

public class LogoutReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public LogoutReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Logout");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			LogoutContent lc = new LogoutContent();
			lc.parse(msg.getContent());

			if (m_db.is_logged(lc.getUsername()))
			{
				m_db.set_offline(lc.getUsername());
				ACLMessage loginreply = msg.createReply();
				lc.newResponse("ok");
				loginreply.setContent(lc.getContent());
				myAgent.send(loginreply);
			} else
			{
				ACLMessage loginreply = msg.createReply();
				lc.newResponse("no");
				loginreply.setContent(lc.getContent());
				myAgent.send(loginreply);
			}
		}
	}
}
