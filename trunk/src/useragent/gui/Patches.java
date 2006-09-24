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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import useragent.sql.db_interface;

public class Patches extends JInternalFrame
{
	private static final long serialVersionUID = 1L;

	BorderLayout borderLayout1 = new BorderLayout();

	PatchTblModel m_stm = null;

	db_interface m_db;

	Agent m_agent = null;

	private JPanel jpnlMain = null;

	private JScrollPane jScrollPane = null;

	private JTable jtblPatch = null;

	public Patches(Object[][] data, Agent agent, db_interface db)
	{
		super("Ricerca ", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		m_stm = new PatchTblModel(data);
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
		this.setSize(new Dimension(503, 207));

		this.setContentPane(getJpnlMain());
	}

	public void table_mouseClicked(MouseEvent e)
	{
		/*
		 * int row = jtblPatch.getSelectedRow(); Object[] data =
		 * m_stm.getRow(row); String jadeid = m_agent.getAID().getName();
		 * 
		 * if (jadeid.equals(m_stm.getJadeId(row))) { String[] data2 =
		 * m_db.get_patch(m_stm.getPatchName(row)); data2[1] =
		 * data[1].toString(); data2[5] = data[3].toString(); data2[7] =
		 * data[4].toString(); data2[8] = data[5].toString(); data2[9] =
		 * data[2].toString();
		 * 
		 * UserAgent ua = (UserAgent) m_agent; ua.patch(data2); } else {
		 * PatchInfoRqContent sirc = new PatchInfoRqContent();
		 * sirc.newPatchInfoRq(m_stm.getPatchName(row));
		 * sirc.setAuthor(data[1].toString());
		 * sirc.setDescription(data[2].toString());
		 * sirc.setRating(data[3].toString());
		 * sirc.setRegData(data[4].toString());
		 * sirc.setEditData(data[5].toString());
		 * 
		 * PatchRqSender srs = new PatchRqSender(sirc, m_stm.getJadeId(row));
		 * m_agent.addBehaviour(srs); }
		 */
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
			jpnlMain.add(getJScrollPane(), BorderLayout.CENTER);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes jScrollPane
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJScrollPane()
	{
		if (jScrollPane == null)
		{
			jScrollPane = new JScrollPane();
			jScrollPane.setViewportView(getJtblPatch());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jtblPatch
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJtblPatch()
	{
		if (jtblPatch == null)
		{
			jtblPatch = new JTable(m_stm);
		}
		return jtblPatch;
	}
} // @jve:decl-index=0:visual-constraint="10,10"

class PatchTblModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames =
	{ "Nome", "Autore", "Descrizione", "Rating", "Data registrazione",
			"Data modifica" };

	private Object[][] m_data = null;

	public PatchTblModel(Object[][] data)
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
	
	public String getPatchName(int row)
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

class Patchs_table_mouseAdapter extends MouseAdapter
{
	private Patches adaptee;

	Patchs_table_mouseAdapter(Patches adaptee)
	{
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e)
	{
		adaptee.table_mouseClicked(e);
	}
}
