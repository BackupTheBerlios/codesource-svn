package serveragent.receiverbehaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import serveragent.sql.db_interface;
import content.ResultContent;
import content.SearchContent;

public class SearchReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public SearchReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Search");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			AID supplicant = msg.getSender();
			String jadeid = supplicant.getName();

			if (m_db.jadeid_is_logged(jadeid))
			{
				SearchContent sc = new SearchContent();
				sc.parse(msg.getContent());
				ResultContent rc = m_db.search(sc);
				ACLMessage searchreply = msg.createReply();
				searchreply.setContent(rc.getContent());
				myAgent.send(searchreply);
			} else
			{
				ResultContent rc = new ResultContent();
				rc.newResult();
				ACLMessage searchreply = msg.createReply();
				searchreply.setContent(rc.getContent());
				myAgent.send(searchreply);
			}
		}
	}
}
