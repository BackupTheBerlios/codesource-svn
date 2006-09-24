package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.SnipInfoContent;
import content.SnipInfoRqContent;

public class SnipRqSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_snip_msg = null;

	public SnipRqSender(SnipInfoRqContent sirc, String jadeid)
	{
		m_snip_msg = new ACLMessage(ACLMessage.CFP);
		m_snip_msg.addReceiver(new AID(jadeid, AID.ISLOCALNAME));
		m_snip_msg.setOntology("SnipInfoRequest");
		m_snip_msg.setContent(sirc.getContent());
	}

	public void action()
	{
		myAgent.send(m_snip_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("SnipInfoRequest");

		ACLMessage rx = myAgent.blockingReceive(mt);

		SnipInfoContent lc = new SnipInfoContent();
		lc.parse(rx.getContent());

		String[] data = new String[11];

		data[0] = lc.getSnipName();
		data[1] = lc.getAuthor();
		data[2] = lc.getLanguage();
		data[3] = lc.getLicense();
		data[4] = lc.getStatus();
		data[5] = lc.getRating();
		data[6] = lc.getVersion();
		data[7] = lc.getRegData();
		data[8] = lc.getEditData();
		data[9] = lc.getDescription();
		data[10] = lc.getJadeId();

		UserAgent ua = (UserAgent) myAgent;
		ua.snip(data);
	}
}
