package content;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SearchContent
{
	private DocumentBuilder m_builder = null;

	private Document m_document = null;

	public SearchContent()
	{
		try
		{
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			factory.setValidating(false);

			m_builder = factory.newDocumentBuilder();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void newSearchAuthor()
	{
		m_document = m_builder.newDocument();
		Element newSearchMsg = m_document.createElement("searchmsg");

		Attr searchTable = m_document.createAttribute("table");
		searchTable.setValue("Autore");
		newSearchMsg.setAttributeNode(searchTable);

		m_document.appendChild(newSearchMsg);
	}

	public void newSearchSnip()
	{
		m_document = m_builder.newDocument();
		Element newSearchMsg = m_document.createElement("searchmsg");

		Attr searchTable = m_document.createAttribute("table");
		searchTable.setValue("Snip");
		newSearchMsg.setAttributeNode(searchTable);

		m_document.appendChild(newSearchMsg);
	}

	public void addSearch(String field, String pattern)
	{
		Element newSearch = m_document.createElement("search");
		Attr searchField = m_document.createAttribute("field");
		searchField.setValue(field);
		newSearch.setAttributeNode(searchField);
		Text searchPattern = m_document.createTextNode(pattern);
		newSearch.appendChild(searchPattern);
		Element root = m_document.getDocumentElement();
		root.appendChild(newSearch);
	}

	public String getContent()
	{
		StringWriter output = new StringWriter();

		try
		{
			TransformerFactory.newInstance().newTransformer().transform(
					new DOMSource(m_document), new StreamResult(output));
		} catch (Exception ex)
		{
		}

		return output.toString();
	}

	public void parse(String data)
	{
		InputSource is_data = new InputSource(new StringReader(data));

		try
		{
			m_document = m_builder.parse(is_data);
		} catch (SAXException sae)
		{
		} catch (IOException ioe)
		{
		}
	}

	public String getTable()
	{
		return m_document.getDocumentElement().getAttribute("table");
	}

	public String[] getFields()
	{
		NodeList searchs = m_document.getElementsByTagName("search");
		int Size = searchs.getLength();
		String[] result = new String[Size];

		for (int i = 0; i < Size; i++)
		{
			Element tmp = (Element) searchs.item(i);
			result[i] = tmp.getAttribute("field");
		}

		return result;
	}

	public String getFieldValue(String fieldname)
	{
		NodeList searchs = m_document.getElementsByTagName("search");
		int Size = searchs.getLength();
		String tmpstr = null;

		for (int i = 0; i < Size; i++)
		{
			Element tmp = (Element) searchs.item(i);
			tmpstr = tmp.getAttribute("field");
			if (tmpstr.equals(fieldname))
			{
				return tmp.getTextContent();
			}
		}

		return tmpstr;
	}
}
