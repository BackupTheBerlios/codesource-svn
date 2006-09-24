package useragent.gui;

import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MsgBox extends JDialog implements ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	GridLayout msglayout = new GridLayout(2, 0);

	String m_msg = null;

	private JPanel jpnlMain = null;

	private JLabel jlblMsg = null;

	private JButton jbtnOk = null;

	public MsgBox(Frame parent, String msg)
	{
		super(parent);

		m_msg = msg;

		initialize();

		try
		{
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		} catch (Exception exception)
		{
			exception.printStackTrace();
		}
	}

	public MsgBox()
	{
		this(null, "Niente da dichiarare");
	}

	/**
	 * Component initialization.
	 * 
	 * @throws java.lang.Exception
	 */
	private void initialize()
	{
		setResizable(false);
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
			gridLayout.setRows(2);
			gridLayout.setColumns(1);
			jlblMsg = new JLabel();
			jlblMsg.setText(m_msg);
			jpnlMain = new JPanel();
			jpnlMain.setLayout(gridLayout);
			jpnlMain.add(jlblMsg, null);
			jpnlMain.add(getJbtnOk(), null);
		}
		return jpnlMain;
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
} // @jve:decl-index=0:visual-constraint="10,10"
