package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.PatchContent;

public class PatchSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_patch_msg = null;

	public PatchSender(PatchContent lc, String jadeid)
	{
		m_patch_msg = new ACLMessage(ACLMessage.CFP);
		m_patch_msg.addReceiver(new AID(jadeid, AID.ISLOCALNAME));
		m_patch_msg.setOntology("Patch");
		m_patch_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_patch_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Patch");

		ACLMessage rx = myAgent.blockingReceive(mt);

		PatchContent lc = new PatchContent();

		lc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.patch(lc.getResponse());
	}
}
