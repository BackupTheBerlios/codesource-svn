package useragent.senderbehaviour;

import useragent.UserAgent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import content.SnipContent;

public class SnipSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_snip_msg = null;

	public SnipSender(SnipContent sc)
	{
		System.out.println(sc.getContent());
		m_snip_msg = new ACLMessage(ACLMessage.CFP);
		m_snip_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_snip_msg.setOntology("PublicSnip");
		m_snip_msg.setContent(sc.getContent());
	}

	public void action()
	{
		myAgent.send(m_snip_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("PublicSnip");

		ACLMessage rx = myAgent.blockingReceive(mt);
		
		SnipContent sc = new SnipContent();

		sc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		
		ua.snipresponse(sc.getResponse());
	}
}