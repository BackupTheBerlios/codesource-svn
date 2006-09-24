package useragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.sql.db_interface;
import content.CommentContent;

public class CommentReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public CommentReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Comment");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			CommentContent cc = new CommentContent();
			cc.parse(msg.getContent());

			if (m_db.insert_comment(cc.getSnip(), cc.getAuthor(), cc.getComment()))
			{
				ACLMessage Commentreply = msg.createReply();
				cc.newResponse("ok");
				Commentreply.setContent(cc.getContent());
				myAgent.send(Commentreply);
			} else
			{
				ACLMessage Commentreply = msg.createReply();
				cc.newResponse("no");
				Commentreply.setContent(cc.getContent());
				myAgent.send(Commentreply);
			}
		}
	}
}
