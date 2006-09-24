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

import useragent.UserAgent;
import useragent.senderbehaviour.SnipRqSender;
import useragent.sql.db_interface;
import content.SnipInfoRqContent;

class SnipTblModel extends AbstractTableModel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] columnNames =
	{ "Nome", "Autore", "Descrizione", "Rating", "Data registrazione",
			"Data modifica" };

	private Object[][] m_data = null;

	public SnipTblModel(Object[][] data)
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

public class Snips extends JInternalFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JPanel panel1 = new JPanel();

	BorderLayout borderLayout1 = new BorderLayout();

	SnipTblModel m_stm = null;

	JTable m_table = null;

	db_interface m_db;

	Agent m_agent = null;

	public Snips(Object[][] data, Agent agent, db_interface db)
	{
		super("Ricerca ", true, // resizable
				true, // closable
				true, // maximizable
				true);// iconifiable

		m_stm = new SnipTblModel(data);
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
		panel1.setLayout(borderLayout1);

		getContentPane().add(panel1);
		m_table = new JTable(m_stm);
		JScrollPane scrollPane1 = new JScrollPane(m_table);
		panel1.add(scrollPane1, java.awt.BorderLayout.CENTER);
		m_table.addMouseListener(new Snips_table_mouseAdapter(this));
	}

	public void table_mouseClicked(MouseEvent e)
	{
		int row = m_table.getSelectedRow();
		Object[] data = m_stm.getRow(row);
		String jadeid = m_agent.getAID().getName();

		if (jadeid.equals(m_stm.getJadeId(row)))
		{
			String[] data2 = m_db.get_snip(m_stm.getSnipName(row));
			data2[1] = data[1].toString();
			data2[5] = data[3].toString();
			data2[7] = data[4].toString();
			data2[8] = data[5].toString();
			data2[9] = data[2].toString();

			UserAgent ua = (UserAgent) m_agent;
			ua.sniplocal(data2);

		} else
		{
			SnipInfoRqContent sirc = new SnipInfoRqContent();
			sirc.newSnipInfoRq(m_stm.getSnipName(row));
			sirc.setAuthor(data[1].toString());
			sirc.setDescription(data[2].toString());
			sirc.setRating(data[3].toString());
			sirc.setRegData(data[4].toString());
			sirc.setEditData(data[5].toString());
			sirc.setJadeId(m_stm.getJadeId(row));

			SnipRqSender srs = new SnipRqSender(sirc, m_stm.getJadeId(row));
			m_agent.addBehaviour(srs);
		}
	}
}

class Snips_table_mouseAdapter extends MouseAdapter
{
	private Snips adaptee;

	Snips_table_mouseAdapter(Snips adaptee)
	{
		this.adaptee = adaptee;
	}

	public void mouseClicked(MouseEvent e)
	{
		adaptee.table_mouseClicked(e);
	}
}
