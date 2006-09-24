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
