package useragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.sql.db_interface;
import content.SnipInfoContent;
import content.SnipInfoRqContent;

public class SnipInfoRqReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public SnipInfoRqReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("SnipInfoRequest");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			// AID supplicant = msg.getSender();
			// String jadeid = supplicant.getName();

			SnipInfoRqContent sirc = new SnipInfoRqContent();
			sirc.parse(msg.getContent());

			SnipInfoContent sc = m_db.get_snip(sirc);

			System.out.println("snipreply: " + sc.getContent());

			sc.setAuthor(sirc.getAuthor());
			sc.setDescription(sirc.getDescription());
			sc.setRating(sirc.getRating());
			sc.setRegData(sirc.getRegData());
			sc.setEditData(sirc.getEditData());
			sc.setJadeId(sirc.getJadeId());

			ACLMessage snipreply = msg.createReply();

			snipreply.setContent(sc.getContent());

			myAgent.send(snipreply);
		}
	}
}
