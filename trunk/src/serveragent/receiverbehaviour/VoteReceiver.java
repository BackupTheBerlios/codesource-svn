package serveragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import serveragent.sql.db_interface;
import content.VoteContent;

public class VoteReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public VoteReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Vote");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			VoteContent vc = new VoteContent();
			vc.parse(msg.getContent());

			if (m_db.vote(vc.getSnip(), vc.getVote()))
			{
				ACLMessage votereply = msg.createReply();
				vc.newResponse("ok");
				votereply.setContent(vc.getContent());
				myAgent.send(votereply);
			} else
			{
				ACLMessage votereply = msg.createReply();
				vc.newResponse("no");
				votereply.setContent(vc.getContent());
				myAgent.send(votereply);
			}
		}
	}
}
