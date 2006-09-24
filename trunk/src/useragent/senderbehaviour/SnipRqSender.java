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
