package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.ResultContent;
import content.SearchContent;

public class SearchSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_search_msg = null;

	public SearchSender(SearchContent sc)
	{
		m_search_msg = new ACLMessage(ACLMessage.CFP);
		m_search_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_search_msg.setOntology("Search");
		m_search_msg.setContent(sc.getContent());
	}

	public void action()
	{
		myAgent.send(m_search_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Search");

		ACLMessage rx = myAgent.blockingReceive(mt);

		ResultContent rc = new ResultContent();
		rc.parse(rx.getContent());
		System.out.println(rc.getContent());
		Object[][] authordata = rc.getAuthorData();
		Object[][] snipdata = rc.getSnipData();

		UserAgent ua = (UserAgent) myAgent;

		if (authordata.length != 0)
			ua.authors(authordata);

		if (snipdata.length != 0)
			ua.snips(snipdata);
	}
}
