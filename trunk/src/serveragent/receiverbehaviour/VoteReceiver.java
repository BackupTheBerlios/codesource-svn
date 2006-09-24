/***************************************************************************
                          Codesource Platform
                         ---------------------
    begin                : Mon Sep 18 2006
    copyright            : Giuseppe "denever" Martino
    email                : denever@users.berlios.de
 ***************************************************************************/
/***************************************************************************
 *                                                                         *
 *   This program is free software; you can redistribute it and/or modify  *
 *   it under the terms of the GNU General Public License as published by  *
 *   the Free Software Foundation; either version 2 of the License, or     *
 *   (at your option) any later version.                                   *
 *                                                                         *
 *  This program is distributed in the hope that it will be useful,        *
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of         *
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the          *
 *  GNU General Public License for more details.                           *
 *                                                                         *
 *  You should have received a copy of the GNU General Public License      *
 *  along with this program; if not, write to the Free Software            *
 *  Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,             *
 *  MA 02110-1301 USA                                                      *
 *                                                                         *
 ***************************************************************************/
package serveragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import serveragent.sql.db_interface;
import content.VoteContent;

public class VoteReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public VoteReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Vote");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			VoteContent vc = new VoteContent();
			vc.parse(msg.getContent());

			if (m_db.vote(vc.getSnip(), vc.getVote()))
			{
				ACLMessage votereply = msg.createReply();
				vc.newResponse("ok");
				votereply.setContent(vc.getContent());
				myAgent.send(votereply);
			} else
			{
				ACLMessage votereply = msg.createReply();
				vc.newResponse("no");
				votereply.setContent(vc.getContent());
				myAgent.send(votereply);
			}
		}
	}
}
