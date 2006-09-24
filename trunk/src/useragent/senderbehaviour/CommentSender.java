package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.CommentContent;

public class CommentSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean m_comment = false;

	private ACLMessage m_comment_msg = null;

	public CommentSender(CommentContent lc, String jadeid)
	{
		m_comment_msg = new ACLMessage(ACLMessage.CFP);
		m_comment_msg.addReceiver(new AID(jadeid, AID.ISLOCALNAME));
		m_comment_msg.setOntology("Comment");
		m_comment_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_comment_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Comment");

		ACLMessage rx = myAgent.blockingReceive(mt);

		CommentContent lc = new CommentContent();

		lc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.comment(lc.getResponse());
	}

	public boolean logged()
	{
		return m_comment;
	}
}
