package serveragent.receiverbehaviour;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import serveragent.sql.db_interface;
import content.SnipContent;

public class SnipReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public SnipReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("PublicSnip");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			AID supplicant = msg.getSender();
			String jadeid = supplicant.getName();

			SnipContent sc = new SnipContent();
			sc.parse(msg.getContent());

			boolean status = false;

			if (m_db.jadeid_is_logged(jadeid))
			{
				if (sc.is_newsnip())
				{
					status = m_db.publicsnip(sc);
				} else if (sc.is_newrelease())
				{
					status = m_db.newreleasesnip(sc);
				}
			} else
				status = false;

			if (status)
			{
				ACLMessage snipreply = msg.createReply();
				sc.newResponse("ok");
				snipreply.setContent(sc.getContent());
				myAgent.send(snipreply);
			} else
			{
				ACLMessage snipreply = msg.createReply();
				sc.newResponse("no");
				snipreply.setContent(sc.getContent());
				myAgent.send(snipreply);
			}
		}
	}
}
/*
 * if (m_db.vote(vc.getSnip(), vc.getVote())) { ACLMessage votereply =
 * msg.createReply(); vc.newResponse("ok");
 * votereply.setContent(vc.getContent()); myAgent.send(votereply); } else {
 * ACLMessage votereply = msg.createReply(); vc.newResponse("no");
 * votereply.setContent(vc.getContent()); myAgent.send(votereply); }
 */