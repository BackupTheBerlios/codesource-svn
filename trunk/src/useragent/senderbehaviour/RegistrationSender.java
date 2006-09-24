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
import content.RegistrationContent;

public class RegistrationSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ACLMessage m_reg_msg = null;

	private RegistrationContent m_rc = null;

	public RegistrationSender(RegistrationContent rc)
	{
		m_rc = rc;
		m_reg_msg = new ACLMessage(ACLMessage.CFP);
		m_reg_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_reg_msg.setOntology("Registration");
		m_reg_msg.setContent(m_rc.getContent());
	}

	public void action()
	{
		myAgent.send(m_reg_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Registration");

		ACLMessage rx = myAgent.blockingReceive(mt);

		m_rc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.registration(m_rc.getResponse(), m_rc.getResponseMsg());
	}
}
