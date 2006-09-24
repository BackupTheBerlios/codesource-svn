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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import useragent.sql.db_interface;

public class Comments extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	BorderLayout borderLayout1 = new BorderLayout();

	CommentTblModel m_stm = null;

	db_interface m_db;

	Agent m_agent = null;

	private JPanel jpnlMain = null;

	private JScrollPane jscrPanel = null;

	private JTable jtblComments = null;

	public Comments(Object[][] data, Agent agent, db_interface db)
	{
		super("Ricerca ", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		m_stm = new CommentTblModel(data);
		m_db = db;
		m_agent = agent;

		initialize();

		try
		{
			pack();
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	private void initialize()
	{
		this.setContentPane(getJpnlMain());
		getContentPane().add(getJpnlMain());
	}

	public void table_mouseClicked(MouseEvent e)
	{

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
			jpnlMain.add(getJscrPanel(), java.awt.BorderLayout.CENTER);
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
			jscrPanel.setViewportView(getJtblComments());
		}
		return jscrPanel;
	}

	/**
	 * This method initializes jtblComments
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJtblComments()
	{
		if (jtblComments == null)
		{
			jtblComments = new JTable();
			jtblComments.addMouseListener(new java.awt.event.MouseAdapter()
			{
				public void mouseClicked(java.awt.event.MouseEvent e)
				{
					/*
					 * int row = jtblComments.getSelectedRow(); Object[] data =
					 * m_stm.getRow(row); String jadeid =
					 * m_agent.getAID().getName();
					 * 
					 * if (jadeid.equals(m_stm.getJadeId(row))) { String[] data2 =
					 * m_db.get_snip(m_stm.getSnipName(row)); data2[1] =
					 * data[1].toString(); data2[5] = data[3].toString();
					 * data2[7] = data[4].toString(); data2[8] =
					 * data[5].toString(); data2[9] = data[2].toString();
					 * 
					 * UserAgent ua = (UserAgent)m_agent; ua.sniplocal(data2); }
					 * else { SnipInfoRqContent sirc = new SnipInfoRqContent();
					 * sirc.newSnipInfoRq(m_stm.getSnipName(row));
					 * sirc.setAuthor(data[1].toString());
					 * sirc.setDescription(data[2].toString());
					 * sirc.setRating(data[3].toString());
					 * sirc.setRegData(data[4].toString());
					 * sirc.setEditData(data[5].toString());
					 * 
					 * SnipRqSender srs = new SnipRqSender(sirc,
					 * m_stm.getJadeId(row)); m_agent.addBehaviour(srs); }
					 */
				}
			});
		}
		return jtblComments;
	}
}

class CommentTblModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;

	private String[] columnNames =
	{ "Nome", "Autore", "Descrizione", "Rating", "Data registrazione",
			"Data modifica" };

	private Object[][] m_data = null;

	public CommentTblModel(Object[][] data)
	{
		m_data = data;
	}

	public int getColumnCount()
	{
		return columnNames.length;
	}

	public int getRowCount()
	{
		return m_data.length;
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	public Object getValueAt(int row, int col)
	{
		return m_data[row][col];
	}
	
	public String getSnipName(int row)
	{
		return (String) m_data[row][0];
	}

	public String getJadeId(int row)
	{
		return (String) m_data[row][6];
	}

	public Object[] getRow(int row)
	{
		return m_data[row];
	}
}

class Comments_table_mouseAdapter extends MouseAdapter
{
	private Comments adaptee;

	Comments_table_mouseAdapter(Comments adaptee)
	{
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e)
	{
		adaptee.table_mouseClicked(e);
	}
}
