package useragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.sql.db_interface;
import content.BugContent;

public class BugReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public BugReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Bug");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			System.out.println("Ricevuto un bug:" + msg.getContent());
			
			BugContent bc = new BugContent();
			bc.parse(msg.getContent());

			if (m_db.insert_bug(bc.getSender(), bc.getSnip(), bc.getBug()))
			{
				ACLMessage bugreply = msg.createReply();
				bc.newResponse("ok");
				bugreply.setContent(bc.getContent());
				myAgent.send(bugreply);
			} else
			{
				ACLMessage loginreply = msg.createReply();
				bc.newResponse("no");
				loginreply.setContent(bc.getContent());
				myAgent.send(loginreply);
			}
		}
	}
}
