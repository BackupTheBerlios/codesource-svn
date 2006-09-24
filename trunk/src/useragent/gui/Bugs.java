package useragent.gui;

import jade.core.Agent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import useragent.sql.db_interface;

public class Bugs extends JInternalFrame
{
	private static final long serialVersionUID = 1L;

	BugsTblModel m_stm = null;

	db_interface m_db;

	Agent m_agent = null;

	private JPanel jpnlMain = null;

	private JScrollPane jpnlScroll = null;

	private JTable jtblBugs = null;

	public Bugs(Vector data, Agent agent, db_interface db)
	{
		super("Lista bug ", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		m_stm = new BugsTblModel(data);
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
		this.setSize(new Dimension(474, 338));
		this.setContentPane(getJpnlMain());
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
			jpnlMain.add(getJpnlScroll(), BorderLayout.CENTER);
		}
		return jpnlMain;
	}

	/**
	 * This method initializes jpnlScroll
	 * 
	 * @return javax.swing.JScrollPane
	 */
	private JScrollPane getJpnlScroll()
	{
		if (jpnlScroll == null)
		{
			jpnlScroll = new JScrollPane();
			jpnlScroll.setViewportView(getJtblBugs());
		}
		return jpnlScroll;
	}

	/**
	 * This method initializes jtblBugs
	 * 
	 * @return javax.swing.JTable
	 */
	private JTable getJtblBugs()
	{
		if (jtblBugs == null)
		{
			jtblBugs = new JTable();

			jtblBugs.addMouseListener(new java.awt.event.MouseAdapter()
			{
				public void mouseClicked(java.awt.event.MouseEvent e)
				{
					/*
					 * int row = jtblBugs.getSelectedRow(); Object[] data =
					 * m_stm.getRow(row); String jadeid =
					 * m_agent.getAID().getName();
					 * 
					 * if (jadeid.equals(m_stm.getJadeId(row))) { String[] data2 =
					 * m_db.get_snip(m_stm.getSnipName(row)); data2[1] =
					 * data[1].toString(); data2[5] = data[3].toString();
					 * data2[7] = data[4].toString(); data2[8] =
					 * data[5].toString(); data2[9] = data[2].toString();
					 * 
					 * UserAgent ua = (UserAgent) m_agent; ua.sniplocal(data2); }
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
		return jtblBugs;
	}

}

class BugsTblModel extends AbstractTableModel
{
	private static final long serialVersionUID = 1L;

	private String[] columnNames =
	{ "Nome", "Autore", "Descrizione", "Rating", "Data registrazione",
			"Data modifica" };

	private Object[] m_data = null;

	public BugsTblModel(Vector data)
	{
		m_data = data.toArray();
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
		Vector tmp = (Vector) m_data[row];
		Object[] atmp = tmp.toArray();
		return atmp[col];
	}

	public String getSnipName(int row)
	{
		Vector tmp = (Vector) m_data[row];
		Object[] atmp = tmp.toArray();
		return (String) atmp[0];
	}

	public String getJadeId(int row)
	{
		Vector tmp = (Vector) m_data[row];
		Object[] atmp = tmp.toArray();
		return (String) atmp[6];
	}

	public Object[] getRow(int row)
	{
		Vector tmp = (Vector) m_data[row];
		return tmp.toArray();
	}
}
