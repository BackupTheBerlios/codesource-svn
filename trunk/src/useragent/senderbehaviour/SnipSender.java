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

import useragent.UserAgent;
import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import content.SnipContent;

public class SnipSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_snip_msg = null;

	public SnipSender(SnipContent sc)
	{
		System.out.println(sc.getContent());
		m_snip_msg = new ACLMessage(ACLMessage.CFP);
		m_snip_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_snip_msg.setOntology("PublicSnip");
		m_snip_msg.setContent(sc.getContent());
	}

	public void action()
	{
		myAgent.send(m_snip_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("PublicSnip");

		ACLMessage rx = myAgent.blockingReceive(mt);
		
		SnipContent sc = new SnipContent();

		sc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		
		ua.snipresponse(sc.getResponse());
	}
}