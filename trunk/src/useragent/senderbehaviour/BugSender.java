package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.BugContent;

public class BugSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_bug_msg = null;

	public BugSender(BugContent bc, String jadeid)
	{
		m_bug_msg = new ACLMessage(ACLMessage.CFP);
		m_bug_msg.addReceiver(new AID(jadeid, AID.ISLOCALNAME));
		m_bug_msg.setOntology("Bug");
		m_bug_msg.setContent(bc.getContent());
	}

	public void action()
	{
		myAgent.send(m_bug_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Bug");

		ACLMessage rx = myAgent.blockingReceive(mt);

		BugContent bc = new BugContent();

		bc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.bug(bc.getResponse());
	}
}
