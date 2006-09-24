package useragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.sql.db_interface;
import content.SubscribeContent;

public class SubscribeReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public SubscribeReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Subscribe");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{			
			String jadeid = msg.getSender().getName();
			SubscribeContent sc = new SubscribeContent();
			sc.parse(msg.getContent());

			if (m_db.insert_subscriber(sc.getSnip(), sc.getUsername(), jadeid))
			{
				ACLMessage Commentreply = msg.createReply();
				sc.newResponse("ok");
				Commentreply.setContent(sc.getContent());
				myAgent.send(Commentreply);
			} else
			{
				ACLMessage Commentreply = msg.createReply();
				sc.newResponse("no");
				Commentreply.setContent(sc.getContent());
				myAgent.send(Commentreply);
			}
		}
	}
}
