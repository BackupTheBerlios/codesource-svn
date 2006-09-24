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
import content.VoteContent;

public class VoteSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean m_vote = false;

	private ACLMessage m_vote_msg = null;

	public VoteSender(VoteContent lc)
	{
		m_vote_msg = new ACLMessage(ACLMessage.CFP);
		m_vote_msg.addReceiver(new AID("Manager", AID.ISLOCALNAME));
		m_vote_msg.setOntology("Vote");
		m_vote_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_vote_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Vote");

		ACLMessage rx = myAgent.blockingReceive(mt);

		VoteContent vc = new VoteContent();

		vc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.vote(vc.getResponse());
	}

	public boolean logged()
	{
		return m_vote;
	}
}
