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
package serveragent;

import jade.core.Agent;
import serveragent.receiverbehaviour.LoginReceiver;
import serveragent.receiverbehaviour.LogoutReceiver;
import serveragent.receiverbehaviour.RegistrationReceiver;
import serveragent.receiverbehaviour.SearchReceiver;
import serveragent.receiverbehaviour.SnipReceiver;
import serveragent.receiverbehaviour.VoteReceiver;
import serveragent.sql.db_interface;

public class ServerAgent extends Agent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	protected void setup()
	{
		m_db = new db_interface("serveragent.db");

		System.out.println(getAID().getName()+" in esecuzione.");

		RegistrationReceiver regrx = new RegistrationReceiver(m_db);
		LoginReceiver logrx = new LoginReceiver(m_db);
		LogoutReceiver logorx = new LogoutReceiver(m_db);
		SearchReceiver searchrx = new SearchReceiver(m_db);
		SnipReceiver sniprx = new SnipReceiver(m_db);
		VoteReceiver voterx = new VoteReceiver(m_db);

		addBehaviour(regrx);
		addBehaviour(logrx);
		addBehaviour(logorx);
		addBehaviour(searchrx);
		addBehaviour(sniprx);
		addBehaviour(voterx);
	}

	protected void takeDown()
	{
		m_db.shutdown();
	}
}
