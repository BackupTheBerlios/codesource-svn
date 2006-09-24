package useragent.senderbehaviour;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.UserAgent;
import content.DownloadContent;
import content.DownloadRqContent;

public class DownloadSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean m_download = false;

	private ACLMessage m_download_msg = null;

	public DownloadSender(DownloadRqContent lc, String jadeid)
	{
		m_download_msg = new ACLMessage(ACLMessage.CFP);
		m_download_msg.addReceiver(new AID(jadeid, AID.ISLOCALNAME));
		m_download_msg.setOntology("Download");
		m_download_msg.setContent(lc.getContent());
		System.out.println("downloadrq: " + lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_download_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Download");

		ACLMessage rx = myAgent.blockingReceive(mt);

		DownloadContent lc = new DownloadContent();

		System.out.println("downloadrx: " + rx.getContent());

		lc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;

		ua.download(lc.getBody());
	}

	public boolean logged()
	{
		return m_download;
	}
}
