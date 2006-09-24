package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.LogoutContent;

public class LogoutSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_logout_msg = null;

	public LogoutSender(LogoutContent lc)
	{
		m_logout_msg = new ACLMessage(ACLMessage.CFP);
		m_logout_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_logout_msg.setOntology("Logout");

		m_logout_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_logout_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Logout");

		ACLMessage rx = myAgent.blockingReceive(mt);

		LogoutContent lc = new LogoutContent();

		lc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.logout(lc.getResponse());
	}
}
