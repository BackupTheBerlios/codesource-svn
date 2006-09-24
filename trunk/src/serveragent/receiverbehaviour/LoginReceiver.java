package serveragent.receiverbehaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import serveragent.sql.db_interface;
import content.LoginContent;

public class LoginReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public LoginReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Login");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			AID supplicant = msg.getSender();
			String jadeid = supplicant.getName();

			LoginContent lc = new LoginContent();
			lc.parse(msg.getContent());

			if (m_db.check_login(lc.getUsername(), lc.getPassword(), jadeid)
					&& !m_db.is_logged(lc.getUsername()))
			{
				m_db.set_online(lc.getUsername());
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
