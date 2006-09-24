package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.SubscribeContent;

public class SubscribeSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean m_vote = false;

	private ACLMessage m_vote_msg = null;

	public SubscribeSender(SubscribeContent lc, String jadeid)
	{
		m_vote_msg = new ACLMessage(ACLMessage.CFP);
		m_vote_msg.addReceiver(new AID(jadeid, AID.ISLOCALNAME));
		m_vote_msg.setOntology("Subscribe");
		m_vote_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_vote_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Subscribe");

		ACLMessage rx = myAgent.blockingReceive(mt);

		SubscribeContent lc = new SubscribeContent();

		lc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.subscribe(lc.getResponse());
	}

	public boolean logged()
	{
		return m_vote;
	}
}
