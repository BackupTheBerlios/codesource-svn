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
import java.awt.Frame;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import starlight.util.Base64;
import useragent.senderbehaviour.LoginSender;
import content.LoginContent;

public class Login extends JDialog
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BorderLayout borderLayout1 = new BorderLayout();

	GridLayout mainlayout = new GridLayout(2, 2);

	Agent m_agent = null;

	private JPanel jpnlMain = null;

	private JPanel jpnlFields = null;

	private JLabel jlblUserName = null;

	private JTextField jtxtUserName = null;

	private JLabel jlblPasswd = null;

	private JPasswordField jtxtPasswd = null;

	private JPanel jpnlButton = null;

	private JButton jbtnLogin1 = null;

	public Login(Frame parent, Agent agent)
	{
		super(parent);
		m_agent = agent;

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
		setSize(new Dimension(420, 100));
		this.setContentPane(getJpnlMain());
		setTitle("Login");
		jtxtUserName.grabFocus();
		jtxtUserName.selectAll();
	}

	public void jbtnLogin_actionPerformed(ActionEvent e)
	{
		try
		{
			String password = md5(new String(jtxtPasswd.getPassword()));
			LoginContent lc = new LoginContent();
			lc.newLogin(jtxtUserName.getText(), password);
			LoginSender ls = new LoginSender(lc);
			m_agent.addBehaviour(ls);

			dispose();
		} catch (NoSuchAlgorithmException ex)
		{
			System.out.println("Errore codifica password");
		}
	}

	// MD5 password hashing
	public String md5(String value) throws NoSuchAlgorithmException
	{
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.reset();
		md.update(value.getBytes());
		byte[] raw = md.digest();
		return new String(Base64.encode(raw));
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
			jpnlMain = new JPanel();
			jpnlMain.setLayout(new BorderLayout());
			jpnlMain.add(getJpnlFields(), BorderLayout.CENTER);
			jpnlMain.add(getJpnlButton(), BorderLayout.SOUTH);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes jpnlFields
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlFields()
	{
		if (jpnlFields == null)
		{
			jlblPasswd = new JLabel();
			jlblPasswd.setText("Password");
			jlblUserName = new JLabel();
			jlblUserName.setText("UserName");
			jpnlFields = new JPanel();
			jpnlFields.setLayout(new GridLayout(2, 2));
			jpnlFields.add(jlblUserName, jlblUserName.getName());
			jpnlFields.add(getJtxtUserName(), getJtxtUserName().getName());
			jpnlFields.add(jlblPasswd, jlblPasswd.getName());
			jpnlFields.add(getJtxtPasswd(), getJtxtPasswd().getName());
		}
		return jpnlFields;
	}

	/**
	 * This method initializes jtxtUserName
	 * 
	 * @return javax.swing.JTextField
	 */
	private JTextField getJtxtUserName()
	{
		if (jtxtUserName == null)
		{
			jtxtUserName = new JTextField();
			jtxtUserName.setText("Inserisci qui lo username");
			jtxtUserName.setFocusAccelerator('n');
			jtxtUserName.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jtxtPasswd.grabFocus();
				}
			});
		}
		return jtxtUserName;
	}

	/**
	 * This method initializes jtxtPasswd
	 * 
	 * @return javax.swing.JPasswordField
	 */
	private JPasswordField getJtxtPasswd()
	{
		if (jtxtPasswd == null)
		{
			jtxtPasswd = new JPasswordField();
			jtxtPasswd.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnLogin_actionPerformed(e);
				}
			});
		}
		return jtxtPasswd;
	}

	/**
	 * This method initializes jpnlButton
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJpnlButton()
	{
		if (jpnlButton == null)
		{
			jpnlButton = new JPanel();
			jpnlButton.setLayout(new GridBagLayout());
			jpnlButton.add(getJbtnLogin1(), getJbtnLogin1().getName());
		}
		return jpnlButton;
	}

	/**
	 * This method initializes jbtnLogin1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getJbtnLogin1()
	{
		if (jbtnLogin1 == null)
		{
			jbtnLogin1 = new JButton();
			jbtnLogin1.setText("Login");
			jbtnLogin1.addActionListener(new java.awt.event.ActionListener()
			{
				public void actionPerformed(java.awt.event.ActionEvent e)
				{
					jbtnLogin_actionPerformed(e);
				}
			});
		}
		return jbtnLogin1;
	}
} // @jve:decl-index=0:visual-constraint="10,10"
