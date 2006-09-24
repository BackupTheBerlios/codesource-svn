package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.VoteContent;

public class VoteSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean m_vote = false;

	private ACLMessage m_vote_msg = null;

	public VoteSender(VoteContent lc)
	{
		m_vote_msg = new ACLMessage(ACLMessage.CFP);
		m_vote_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_vote_msg.setOntology("Vote");
		m_vote_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_vote_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Vote");

		ACLMessage rx = myAgent.blockingReceive(mt);

		VoteContent vc = new VoteContent();

		vc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.vote(vc.getResponse());
	}

	public boolean logged()
	{
		return m_vote;
	}
}
