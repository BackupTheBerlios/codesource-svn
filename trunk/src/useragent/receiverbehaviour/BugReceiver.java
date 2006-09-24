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
package useragent.receiverbehaviour;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import useragent.sql.db_interface;
import content.BugContent;

public class BugReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public BugReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Bug");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			System.out.println("Ricevuto un bug:" + msg.getContent());
			
			BugContent bc = new BugContent();
			bc.parse(msg.getContent());

			if (m_db.insert_bug(bc.getSender(), bc.getSnip(), bc.getBug()))
			{
				ACLMessage bugreply = msg.createReply();
				bc.newResponse("ok");
				bugreply.setContent(bc.getContent());
				myAgent.send(bugreply);
			} else
			{
				ACLMessage loginreply = msg.createReply();
				bc.newResponse("no");
				loginreply.setContent(bc.getContent());
				myAgent.send(loginreply);
			}
		}
	}
}
