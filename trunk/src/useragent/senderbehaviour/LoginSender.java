package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.LoginContent;

public class LoginSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean m_login = false;

	private ACLMessage m_login_msg = null;

	public LoginSender(LoginContent lc)
	{
		m_login_msg = new ACLMessage(ACLMessage.CFP);
		m_login_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_login_msg.setOntology("Login");
		m_login_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_login_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Login");

		ACLMessage rx = myAgent.blockingReceive(mt);

		LoginContent lc = new LoginContent();

		lc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.login(lc.getUsername(), lc.getResponse());
	}

	public boolean logged()
	{
		return m_login;
	}
}
