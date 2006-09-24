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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import useragent.sql.db_interface;
import content.DownloadContent;
import content.DownloadRqContent;

public class DownloadReceiver extends CyclicBehaviour
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private db_interface m_db = null;

	public DownloadReceiver(db_interface db)
	{
		m_db = db;
	}

	public void action()
	{
		MessageTemplate mt = MessageTemplate.MatchOntology("Download");
		ACLMessage msg = myAgent.receive(mt);

		if (msg != null)
		{
			DownloadRqContent drq = new DownloadRqContent();
			drq.parse(msg.getContent());

			String filename = m_db.get_snipfile(drq.getSnip());

			DownloadContent dc = new DownloadContent();
			dc.newDownload();

			try
			{
				BufferedReader file = new BufferedReader(new FileReader(
						filename));
				String inputLine = null;

				while ((inputLine = file.readLine()) != null)
				{
					dc.appendLine(inputLine + "\n");
				}
			} catch (FileNotFoundException ex)
			{
			} catch (IOException ex)
			{
			}
			System.out.println("Il file letto è: " + dc.getContent());

			ACLMessage downloadreply = msg.createReply();
			downloadreply.setContent(dc.getContent());

			myAgent.send(downloadreply);
		}
	}
}
/*
 * if (m_db.insert_bug(drq.getSender(), drq.getSnip(), drq.getBug())) {
 * ACLMessage bugreply = msg.createReply(); drq.newResponse("ok");
 * bugreply.setContent(drq.getContent()); myAgent.send(bugreply); } else {
 * ACLMessage downloadreply = msg.createReply(); bc.newResponse("no");
 * downloadreply.setContent(bc.getContent()); myAgent.send(downloadreply); }
 */