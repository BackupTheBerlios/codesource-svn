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

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import useragent.senderbehaviour.VoteSender;
import content.VoteContent;
import java.awt.Dimension;

public class VoteBox extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GridLayout msglayout = new GridLayout(2, 0);

	private String m_msg = null;

	private String m_value = null;

	private String m_snipname = null;

	private Agent m_agent = null;

	private JPanel jpnlMain = null;

	private JLabel jlblMsg = null;

	private JButton jbtnOk = null;

	private JTextField jtxtData = null;

	public VoteBox(String msg, Agent agent, String snipname)
	{
		// super();

		m_msg = msg;
		m_agent = agent;
		m_snipname = snipname;

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
		setResizable(false);
		this.setPreferredSize(new Dimension(138, 111));
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

			VoteContent vc = new VoteContent();
			vc.newVote(m_snipname, jtxtData.getText());
			VoteSender vs = new VoteSender(vc);
			m_agent.addBehaviour(vs);
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
			GridLayout gridLayout = new GridLayout();
			gridLayout.setRows(3);
			gridLayout.setColumns(1);
			jlblMsg = new JLabel();
			jlblMsg.setText(m_msg);
			jpnlMain = new JPanel();
			jpnlMain.setLayout(gridLayout);
			jpnlMain.add(jlblMsg, null);
			jpnlMain.add(getJtxtData2(), null);
			jpnlMain.add(getJbtnOk(), null);
			jpnlMain.add(getJtxtData(), null);
			jpnlMain.add(getJbtnOk(), null);
		}
		return jpnlMain;
	}

	private JButton getJtxtData()
	{
		if (jtxtData == null)
		{
			jtxtData.addActionListener(this);
		}
		return jbtnOk;
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

	public String getValue()
	{
		return m_value;
	}

	/**
	 * This method initializes jtxtData
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtData2()
	{
		if (jtxtData == null)
		{
			jtxtData = new JTextField();
		}
		return jtxtData;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
