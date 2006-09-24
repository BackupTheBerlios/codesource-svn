package useragent.receiverbehaviour;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.sql.db_interface;
import content.PatchContent;

public class PatchReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public PatchReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Patch");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			PatchContent pc = new PatchContent();
			pc.parse(msg.getContent());

			String curDir = System.getProperty("user.dir");
			boolean success = (new File(curDir + "/patches/" + pc.getSnip()))
					.mkdirs();

			Date now = new Date(System.currentTimeMillis());

			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");

			String filename = curDir + "/patches/" + pc.getSnip() + "/"+ df.format(now) + ".patch";

			m_db.insert_patch(pc.getSender(), pc.getSnip(), filename);

			if (success)
			{
				try
				{
					PrintWriter out = new PrintWriter(new BufferedWriter(
							new FileWriter(filename)));
					out.print(pc.getPatch());
					out.close();
				} catch (FileNotFoundException ex)
				{
					ACLMessage patchreply = msg.createReply();
					pc.newResponse("no");
					patchreply.setContent(pc.getContent());
					myAgent.send(patchreply);

				} catch (IOException ex)
				{
					ACLMessage patchreply = msg.createReply();
					pc.newResponse("no");
					patchreply.setContent(pc.getContent());
					myAgent.send(patchreply);
				}

				if (m_db.insert_patch(pc.getSender(), pc.getSnip(), filename))
				{
					ACLMessage patchreply = msg.createReply();
					pc.newResponse("ok");
					patchreply.setContent(pc.getContent());
					myAgent.send(patchreply);
				} else
				{
					ACLMessage patchreply = msg.createReply();
					pc.newResponse("no");
					patchreply.setContent(pc.getContent());
					myAgent.send(patchreply);
				}
			}
		}
	}
}
