package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.RegistrationContent;

public class RegistrationSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_reg_msg = null;

	private RegistrationContent m_rc = null;

	public RegistrationSender(RegistrationContent rc)
	{
		m_rc = rc;
		m_reg_msg = new ACLMessage(ACLMessage.CFP);
		m_reg_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_reg_msg.setOntology("Registration");
		m_reg_msg.setContent(m_rc.getContent());
	}

	public void action()
	{
		myAgent.send(m_reg_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Registration");

		ACLMessage rx = myAgent.blockingReceive(mt);

		m_rc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.registration(m_rc.getResponse(), m_rc.getResponseMsg());
	}
}
