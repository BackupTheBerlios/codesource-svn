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
package useragent.gui;

import jade.core.Agent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import useragent.senderbehaviour.CommentSender;
import content.CommentContent;

public class CommentBox extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GridLayout msglayout = new GridLayout(2, 0);

	private String m_msg = null;

	private String m_snipname = null;

	private String m_jadeid = null;

	private String m_sender = null;

	private Agent m_agent = null;

	private JPanel jpnlMain = null;

	private JLabel jlblMsg = null;

	private JScrollPane jscrPanel = null;

	private JButton jbtnOk = null;

	private JTextArea jtxaData = null;

	public CommentBox(String msg, Agent agent, String dest, String snipname,
			String username)
	{
			m_msg = msg;
		m_agent = agent;
		m_snipname = snipname;
		m_jadeid = dest;
		m_sender = username;

		initialize();

		try
		{
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * Component initialization.
	 * 
	 * @throws java.lang.Exception
	 */
	private void initialize()
	{
		setTitle("Comment interface");
		setResizable(false);
		this.setPreferredSize(new Dimension(448, 412));
		this.setContentPane(getJpnlMain());
		jbtnOk.grabFocus();
	}

	/**
	 * Close the dialog on a button event.
	 * 
	 * @param actionEvent
	 *            ActionEvent
	 */
	public void actionPerformed(ActionEvent actionEvent)
	{
		if (actionEvent.getSource() == jbtnOk)
		{
			CommentContent cc = new CommentContent();
			cc.newComment(m_snipname, m_sender, jtxaData.getText());
			CommentSender cs = new CommentSender(cc, m_jadeid);
			m_agent.addBehaviour(cs);
			
			dispose();
		}
	}

	/**
	 * This method initializes jpnlMain
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlMain()
	{
		if (jpnlMain == null)
		{
			jlblMsg = new JLabel();
			jlblMsg.setText(m_msg);
			jpnlMain = new JPanel();
			jpnlMain.setLayout(new BorderLayout());
			jpnlMain.add(jlblMsg, BorderLayout.NORTH);
			jpnlMain.add(getJscrPanel(), BorderLayout.CENTER);
			jpnlMain.add(getJbtnOk(), BorderLayout.SOUTH);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes jscrPanel	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJscrPanel()
	{
		if (jscrPanel == null)
		{
			jscrPanel = new JScrollPane();
			jscrPanel.setViewportView(getJtxaData());
		}
		return jscrPanel;
	}

	/**
	 * This method initializes jbtnOk	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbtnOk()
	{
		if (jbtnOk == null)
		{
			jbtnOk = new JButton();
			jbtnOk.setText("OK");
			jbtnOk.addActionListener(this);
		}
		return jbtnOk;
	}

	/**
	 * This method initializes jtxaData	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getJtxaData()
	{
		if (jtxaData == null)
		{
			jtxaData = new JTextArea();
			jtxaData.setColumns(30);
			jtxaData.setRows(30);
		}
		return jtxaData;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
