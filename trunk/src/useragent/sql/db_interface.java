package useragent.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import content.ResultContent;
import content.SnipContent;
import content.SnipInfoContent;
import content.SnipInfoRqContent;

public class db_interface
{
	Connection m_db_con = null;

	public db_interface(String filename)
	{
		try
		{
			Class.forName("org.hsqldb.jdbcDriver").newInstance();
			m_db_con = DriverManager.getConnection("jdbc:hsqldb:file:"
					+ filename, "sa", "");
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}

		if (!existdb())
			CreateDb();
	}

	protected void finalize()
	{
	}

	public void shutdown()
	{
		try
		{
			Statement stmt = m_db_con.createStatement();
			stmt.execute("COMMIT");
			stmt.execute("SHUTDOWN");
		} catch (SQLException ex)
		{
			System.out.println("Fallito lo shutdown");
		}
	}

	public boolean existdb()
	{
		try
		{
			String selectautore = "SELECT SnipName FROM Snip";
			Statement stmt = m_db_con.createStatement();
			stmt.executeQuery(selectautore);
		} catch (SQLException ex)
		{
			System.out.println("Creazione Useragent DB");
			return false;
		}

		return true;
	}

	public void CreateDb()
	{
		try
		{
			String createtable_Snip = "CREATE CACHED TABLE Snip( ";
			createtable_Snip += "SnipName VARCHAR(30) NOT NULL PRIMARY KEY,";
			createtable_Snip += "Status VARCHAR(6) NOT NULL,";
			createtable_Snip += "Versione VARCHAR(8) NOT NULL,";
			createtable_Snip += "Linguaggio VARCHAR(30) NOT NULL,";
			createtable_Snip += "Licenza VARCHAR(30) NOT NULL,";
			createtable_Snip += "File VARCHAR(255) NOT NULL);";

			String createtable_Comment = "CREATE CACHED TABLE Comment( ";
			createtable_Comment += "Id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,";
			createtable_Comment += "SnipName VARCHAR(30) NOT NULL,";
			createtable_Comment += "Commento LONGVARCHAR NOT NULL,";
			createtable_Comment += "Author VARCHAR(8) NOT NULL)";

			String createtable_Subscriber = "CREATE CACHED TABLE Subscriber( ";
			createtable_Subscriber += "SnipName VARCHAR(30) NOT NULL,";
			createtable_Subscriber += "Username VARCHAR(8) NOT NULL PRIMARY KEY,";
			createtable_Subscriber += "JadeId VARCHAR(30) NOT NULL)";

			String createtable_Bug = "CREATE CACHED TABLE Bug( ";
			createtable_Bug += "Id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,";
			createtable_Bug += "Descrizione LONGVARCHAR NOT NULL,";
			createtable_Bug += "Sender VARCHAR(8) NOT NULL,";
			createtable_Bug += "SnipName VARCHAR(30) NOT NULL)";

			String createtable_Patch = "CREATE CACHED TABLE Patch( ";
			createtable_Patch += "Id INTEGER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,";
			createtable_Patch += "SnipName VARCHAR(30) NOT NULL,";
			createtable_Patch += "Sender VARCHAR(8) NOT NULL,";
			createtable_Patch += "Filename VARCHAR(30),";
			createtable_Patch += "data DATE,";
			createtable_Patch += "descrizione LONGVARCHAR,";
			createtable_Patch += "BugId INTEGER)";

			Statement stmt1 = m_db_con.createStatement();
			stmt1.executeUpdate(createtable_Snip);
			stmt1.executeUpdate(createtable_Comment);
			stmt1.executeUpdate(createtable_Bug);
			stmt1.executeUpdate(createtable_Patch);
			stmt1.executeUpdate(createtable_Subscriber);
			stmt1.close();
		} catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public boolean publicsnip(SnipContent sc)
	{
		try
		{
			String insertsnip = "INSERT INTO Snip(SnipName, Status, Versione, Linguaggio, Licenza, File) ";
			insertsnip += "VALUES('";
			insertsnip += sc.getSnipName() + "','";
			insertsnip += sc.getStatus() + "','";
			insertsnip += sc.getVersion() + "','";
			insertsnip += sc.getLanguage() + "','";
			insertsnip += sc.getLicense() + "','";
			insertsnip += sc.getFile() + "')";

			System.out.println(insertsnip);

			Statement stmt1 = m_db_con.createStatement();
			stmt1.executeUpdate(insertsnip);
			stmt1.execute("COMMIT");
			stmt1.close();

			return true;
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}

	public boolean newrelease(SnipContent sc)
	{
		try
		{
			String insertsnip = "UPDATE Snip SET ";
			insertsnip += "Status='" + sc.getStatus() + "',";
			insertsnip += "Versione='" + sc.getVersion() + "',";
			insertsnip += "Linguaggio='" + sc.getLanguage() + "',";
			insertsnip += "Licenza='" + sc.getLicense() + "',";
			insertsnip += "File='" + sc.getFile() + "' ";
			insertsnip += "WHERE SnipName='" + sc.getSnipName() + "'";

			System.out.println(insertsnip);

			Statement stmt1 = m_db_con.createStatement();
			stmt1.executeUpdate(insertsnip);
			stmt1.execute("COMMIT");
			stmt1.close();

			return true;
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}

	public boolean insert_patch(String sender, String snipname, String filename)
	{
		String insert = "INSERT INTO Patch(Id, Sender, SnipName, Filename)";
		insert += "VALUES(NULL,'" + sender + "','" + snipname + "','"
				+ filename + "')";
		try
		{
			System.out.println(insert);

			Statement stmt1 = m_db_con.createStatement();
			stmt1.executeUpdate(insert);
			stmt1.execute("COMMIT");
			stmt1.close();
			return true;
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}

	public boolean insert_bug(String sender, String snipname, String bug)
	{
		String insert = "INSERT INTO Bug(Id, Sender, SnipName, Descrizione)";
		insert += "VALUES(NULL,'" + sender + "','" + snipname + "','" + bug
				+ "')";
		try
		{
			System.out.println(insert);

			Statement stmt1 = m_db_con.createStatement();
			stmt1.executeUpdate(insert);
			stmt1.execute("COMMIT");
			stmt1.close();
			return true;
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}

	public boolean insert_comment(String Snipname, String Author, String Comment)
	{
		String insertcomment = "INSERT INTO Comment(Id, SnipName, Author,Commento)";
		insertcomment += "VALUES(NULL,'" + Author + "','" + Snipname + "','"
				+ Comment + "')";
		try
		{
			System.out.println(insertcomment);

			Statement stmt1 = m_db_con.createStatement();
			stmt1.executeUpdate(insertcomment);
			stmt1.execute("COMMIT");
			stmt1.close();
			return true;
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}

	public boolean insert_subscriber(String snipname, String username,
			String jadeid)
	{
		String insertsubscriber = "INSERT INTO Subscriber(Snipname,Username,Jadeid) ";
		insertsubscriber += "VALUES('" + snipname + "','" + username + "','"
				+ jadeid + "')";

		try
		{
			System.out.println(insertsubscriber);

			Statement stmt1 = m_db_con.createStatement();
			stmt1.executeUpdate(insertsubscriber);
			stmt1.execute("COMMIT");
			stmt1.close();
			return true;
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return false;
		}
	}

	public SnipInfoContent get_snip(SnipInfoRqContent sirc)
	{
		SnipInfoContent tmp = new SnipInfoContent();
		try
		{
			String query = "SELECT * FROM Snip ";
			query += " WHERE SnipName='" + sirc.getSnip() + "'";

			System.out.println(query);

			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			tmp.newSnip(sirc.getSnip());

			while (res.next())
			{
				tmp.setVersion(res.getString("Versione"));
				tmp.setStatus(res.getString("Status"));
				tmp.setLanguage(res.getString("Linguaggio"));
				tmp.setLicenza(res.getString("Licenza"));
				tmp.setFile(res.getString("File"));
			}

		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		return tmp;
	}

	public String[] get_snip(String snipname)
	{
		String[] tmp = new String[10];

		try
		{
			String query = "SELECT * FROM Snip ";
			query += " WHERE SnipName='" + snipname + "'";

			System.out.println(query);

			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next())
			{
				tmp[0] = snipname;
				tmp[2] = res.getString("Linguaggio");
				tmp[3] = res.getString("Licenza");
				tmp[4] = res.getString("Status");
				tmp[6] = res.getString("Versione");
			}

		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		return tmp;
	}

	public String get_snipfile(String snipname)
	{
		String tmp = null;

		try
		{
			String query = "SELECT File FROM Snip ";
			query += " WHERE SnipName='" + snipname + "'";

			System.out.println(query);

			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next())
			{
				tmp = res.getString("File");
			}

		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		return tmp;
	}

	public Vector get_bugs(String snipname)
	{		
		Vector result = new Vector<Vector>();

		String query = "SELECT * FROM Bug WHERE SnipName='";
		query += snipname + "'";

		try
		{
			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next())
			{
				Vector tmp = new Vector<String>();
				tmp.add(res.getString("Id"));
				tmp.add(res.getString("Sender"));
				tmp.add(res.getString("Descrizione"));
				
				result.add(tmp);
			}
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		return result;
	}

	/*
	 * public void get_bugs(String snipname) { String query = "SELECT * FROM Bug
	 * WHERE SnipName='"; query += snipname + "'";
	 * 
	 * try { System.out.println(query);
	 * 
	 * Statement stmt = m_db_con.createStatement(); ResultSet res =
	 * stmt.executeQuery(query);
	 * 
	 * int i = 0;
	 * 
	 * while (res.next()) { System.out.println("Bug: " + res.getString("Id") +
	 * res.getString("Sender") + res.getString("Descrizione")); i++; } } catch
	 * (SQLException ex) { System.err.println("SQLException: " +
	 * ex.getMessage()); } }
	 */
	public Object[][] get_comments(String snipname)
	{
		int num = count_comments(snipname);
		Object[][] result = new Object[num][4];

		String query = "SELECT * FROM Comment WHERE SnipName='";
		query += snipname + "'";

		try
		{
			System.out.println(query);

			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			int i = 0;

			while (res.next())
			{
				result[i][0] = res.getString("Id");
				result[i][1] = res.getString("Author");
				result[i][2] = res.getString("Commento");
				i++;
			}
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		return result;
	}

	public void get_comment()
	{
	}

	public Object[][] get_subscribers(String snipname)
	{
		int num = count_subscribers(snipname);
		Object[][] result = new Object[num][3];

		String query = "SELECT * FROM Subscriber WHERE SnipName='";
		query += snipname + "'";

		try
		{
			System.out.println(query);

			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			int i = 0;

			while (res.next())
			{
				result[i][1] = res.getString("Username");
				result[i][2] = res.getString("JadeId");
				result[i][3] = res.getString("Email");
				i++;
			}
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		return result;
	}

	public void get_subscriber()
	{
		String query = null;
		try
		{
			System.out.println(query);

			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			while (res.next())
			{
			}
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
	}

	public void get_patches()
	{
		String query = null;
		ResultContent tmp = new ResultContent();
		try
		{
			System.out.println(query);

			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);

			tmp.newResult();

			while (res.next())
			{
			}
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
		}
		// return tmp;
	}

	public void get_patch()
	{
	}

	public int count_subscribers(String snipname)
	{
		String query = "SELECT COUNT(*) FROM Subscriber WHERE SnipName='";
		query += snipname + "'";

		try
		{
			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);
			return res.getInt(0);
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return 0;
		}
	}

	public int count_bugs(String snipname)
	{
		String query = "SELECT COUNT(*) FROM Bug WHERE SnipName='";
		query += snipname + "'";

		try
		{
			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);
			return res.getInt(0);
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return 0;
		}
	}

	public int count_comments(String snipname)
	{
		String query = "SELECT COUNT(*) FROM Comment WHERE SnipName='";
		query += snipname + "'";

		try
		{
			Statement stmt = m_db_con.createStatement();
			ResultSet res = stmt.executeQuery(query);
			return res.getInt(0);
		} catch (SQLException ex)
		{
			System.err.println("SQLException: " + ex.getMessage());
			return 0;
		}
	}

	public void delete_subscriber()
	{
	}

	public void delete_comment()
	{
	}

	public void delete_patch()
	{
	}

	/*
	 * public String get_next_id() { }
	 */
}

/*
 * public ResultContent search(SearchContent sc) { ResultContent tmp = null;
 * 
 * String table = sc.getTable();
 * 
 * if (table.equals("Autore")) tmp = search_author(sc);
 * 
 * if (table.equals("Snip")) tmp = search_snip(sc);
 * 
 * return tmp; } public boolean check_login(String Username, String Password) {
 * try { Statement stmt = m_db_con.createStatement();
 * 
 * ResultSet res = stmt .executeQuery("SELECT Password FROM Autore WHERE
 * Username='" + Username + "'");
 * 
 * String thePass = "";
 * 
 * if (res.next()) thePass = res.getString("Password");
 * 
 * return Password.equals(thePass); } catch (SQLException ex) {
 * System.err.println("SQLException: " + ex.getMessage()); }
 * 
 * return false; }
 * 
 * public boolean is_logged(String Username) { try { Statement stmt =
 * m_db_con.createStatement();
 * 
 * ResultSet res = stmt .executeQuery("SELECT Online FROM Autore WHERE
 * Username='" + Username + "'");
 * 
 * if (res.next()) return res.getBoolean("Online"); } catch (SQLException ex) {
 * System.err.println("SQLException: " + ex.getMessage()); } return false; }
 * 
 * public boolean jadeid_is_logged(String jadeid) { try { Statement stmt =
 * m_db_con.createStatement();
 * 
 * ResultSet res = stmt .executeQuery("SELECT Online FROM Autore WHERE JadeId='" +
 * jadeid + "'");
 * 
 * if (res.next()) return res.getBoolean("Online"); } catch (SQLException ex) {
 * System.err.println("SQLException: " + ex.getMessage()); } return false; }
 * public boolean register(String Username, String Password, String JadeId,
 * String Nome, String Cognome, String Email, String HomePage) { try { String
 * insertautore = "INSERT INTO
 * Autore(Username,Password,JadeId,Nome,Cognome,Email,HomePage) "; insertautore +=
 * "VALUES('"; insertautore += Username + "','"; insertautore += Password +
 * "','"; insertautore += JadeId + "','"; insertautore += Nome + "','";
 * insertautore += Cognome + "','"; insertautore += Email + "','"; insertautore +=
 * HomePage + "')";
 * 
 * System.out.println(insertautore);
 * 
 * Statement stmt = m_db_con.createStatement();
 * stmt.executeUpdate(insertautore); stmt.close(); return true; } catch
 * (SQLException ex) { System.err.println("SQLException: " + ex.getMessage());
 * return false; } }
 * 
 * public void set_online(String Username, String JadeId) { try { String
 * updateonline = "UPDATE Autore SET Online=True WHERE Username='" + Username +
 * "'"; Statement stmt = m_db_con.createStatement();
 * stmt.executeUpdate(updateonline); stmt.close(); } catch (SQLException ex) {
 * System.err.println("SQLException: " + ex.getMessage()); }
 * 
 * try { String updatejadeid = "UPDATE Autore SET JadeId='" + JadeId + "' WHERE
 * Username='" + Username + "'"; Statement stmt = m_db_con.createStatement();
 * stmt.executeUpdate(updatejadeid); stmt.close(); } catch (SQLException ex) {
 * System.err.println("SQLException: " + ex.getMessage()); } }
 * 
 * 
 * public void set_offline(String Username) { try { String updateonline =
 * "UPDATE Autore SET Online=False WHERE Username='" + Username + "'"; Statement
 * stmt = m_db_con.createStatement(); stmt.executeUpdate(updateonline);
 * stmt.close(); } catch (SQLException ex) { System.err.println("SQLException: " +
 * ex.getMessage()); } }
 * 
 * public ResultContent search_author(SearchContent sc) { ResultContent tmp =
 * new ResultContent(); try { String table = sc.getTable(); String fields[] =
 * sc.getFields();
 * 
 * String query = "SELECT * FROM " + table;
 * 
 * if (fields.length != 0) query += " WHERE " + fields[0] + " LIKE '" +
 * sc.getFieldValue(fields[0]) + "' ";
 * 
 * for (int i = 1; i < fields.length; i++) { query += "AND " + fields[i] + "
 * LIKE '" + sc.getFieldValue(fields[i]) + "' "; }
 * 
 * System.out.println(query);
 * 
 * Statement stmt = m_db_con.createStatement(); ResultSet res =
 * stmt.executeQuery(query);
 * 
 * tmp.newResult();
 * 
 * while (res.next()) { tmp.addAuthorResult(res.getString("UserName"), res
 * .getString("JadeId"), res.getString("nome"), res .getString("cognome"),
 * res.getString("email"), res .getString("homepage"), res.getString("online")); } }
 * catch (SQLException ex) { System.err.println("SQLException: " +
 * ex.getMessage()); } return tmp; }
 */

class ObjVect
{
	private Object[][] m_data = null;

	private int m_size = 0;

	public ObjVect()
	{
	}

	public void push_back(String id, String sender, String desc)
	{
		m_size++;
		if (m_data != null)
		{
			Object[][] temp = m_data.clone();

			m_data = new Object[m_size][3];

			for (int i = 0; i < m_size; i++)
				m_data = temp;

			m_data[m_size - 1][0] = id;
			m_data[m_size - 1][1] = sender;
			m_data[m_size - 1][2] = desc;
		} else
		{
			m_data = new Object[1][3];
			m_data[0][0] = id;
			m_data[0][1] = sender;
			m_data[0][2] = desc;
		}
	}

	public Object[][] get_data()
	{
		return m_data.clone();
	}
}