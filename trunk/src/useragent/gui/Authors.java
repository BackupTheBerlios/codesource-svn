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

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

class AuthorTblModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames =
	{ "Username", "JadeId", "Nome", "Cognome", "Email", "Homepage", "Online" };

	private Object[][] m_data = null;

	public AuthorTblModel(Object[][] data)
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
}

public class Authors extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6171582195139889023L;

	JPanel panel1 = new JPanel();

	BorderLayout borderLayout1 = new BorderLayout();

	AuthorTblModel m_atm = null;

	JTable m_table = null;

	public Authors(Object[][] atm)
	{
		super("Autori", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		m_atm = new AuthorTblModel(atm);

		try
		{
			// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			jbInit();
			pack();
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	private void jbInit() throws Exception
	{
		panel1.setLayout(borderLayout1);
		getContentPane().add(panel1);
		m_table = new JTable(m_atm);
		JScrollPane scrollPane1 = new JScrollPane(m_table);
		panel1.add(scrollPane1, java.awt.BorderLayout.CENTER);
		m_table.addMouseListener(new table_mouseAdapter(this));
	}

	public void table_mouseClicked(MouseEvent e)
	{

	}
}

class table_mouseAdapter extends MouseAdapter
{
	private Authors adaptee;

	table_mouseAdapter(Authors adaptee)
	{
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e)
	{
		adaptee.table_mouseClicked(e);
	}
}
