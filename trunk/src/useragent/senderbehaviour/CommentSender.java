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
import content.CommentContent;

public class CommentSender extends OneShotBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean m_comment = false;

	private ACLMessage m_comment_msg = null;

	public CommentSender(CommentContent lc, String jadeid)
	{
		m_comment_msg = new ACLMessage(ACLMessage.CFP);
		m_comment_msg.addReceiver(new AID(jadeid, AID.ISLOCALNAME));
		m_comment_msg.setOntology("Comment");
		m_comment_msg.setContent(lc.getContent());
	}

	public void action()
	{
		myAgent.send(m_comment_msg);

		MessageTemplate mt = MessageTemplate.MatchOntology("Comment");

		ACLMessage rx = myAgent.blockingReceive(mt);

		CommentContent lc = new CommentContent();

		lc.parse(rx.getContent());

		UserAgent ua = (UserAgent) myAgent;
		ua.comment(lc.getResponse());
	}

	public boolean logged()
	{
		return m_comment;
	}
}
