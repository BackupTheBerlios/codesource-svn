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

public class ResultContent
{
	private DocumentBuilder m_builder = null;

	private Document m_document = null;

	public ResultContent()
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

	public void newResult()
	{
		m_document = m_builder.newDocument();
		Element newResultMsg = m_document.createElement("resultmsg");
		m_document.appendChild(newResultMsg);
	}

	public void addAuthorResult(String author, String jadeid, String nome,
			String cognome, String email, String homepage, String status)
	{
		Element newResult = m_document.createElement("authorresult");

		Attr Author = m_document.createAttribute("author");
		Author.setValue(author);

		Attr JadeId = m_document.createAttribute("jadeid");
		JadeId.setValue(jadeid);

		Attr Nome = m_document.createAttribute("name");
		Nome.setValue(nome);

		Attr Cognome = m_document.createAttribute("surname");
		Cognome.setValue(cognome);

		Attr Email = m_document.createAttribute("email");
		Email.setValue(email);

		Attr HomePage = m_document.createAttribute("homepage");
		HomePage.setValue(homepage);

		Attr Status = m_document.createAttribute("status");
		Status.setValue(status);

		newResult.setAttributeNode(Author);
		newResult.setAttributeNode(JadeId);
		newResult.setAttributeNode(Nome);
		newResult.setAttributeNode(Cognome);
		newResult.setAttributeNode(Email);
		newResult.setAttributeNode(HomePage);
		newResult.setAttributeNode(Status);

		Element root = m_document.getDocumentElement();
		root.appendChild(newResult);
	}

	public void addSnipResult(String snipname, String author,
			String description, String rating, String regdate, String editdate,
			String jadeid)
	{
		Element newResult = m_document.createElement("snipresult");

		Attr SnipName = m_document.createAttribute("snipname");
		SnipName.setValue(snipname);
		newResult.setAttributeNode(SnipName);

		Attr Author = m_document.createAttribute("author");
		Author.setValue(author);
		newResult.setAttributeNode(Author);

		Attr RegDate = m_document.createAttribute("regdate");
		RegDate.setValue(regdate);
		newResult.setAttributeNode(RegDate);

		Attr EditDate = m_document.createAttribute("editdate");
		EditDate.setValue(editdate);
		newResult.setAttributeNode(EditDate);

		Attr Rating = m_document.createAttribute("rating");
		Rating.setValue(rating);
		newResult.setAttributeNode(Rating);

		Attr JadeId = m_document.createAttribute("jadeid");
		JadeId.setValue(jadeid);
		newResult.setAttributeNode(JadeId);

		Text Description = m_document.createTextNode(description);
		newResult.appendChild(Description);

		Element root = m_document.getDocumentElement();
		root.appendChild(newResult);
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

	public Object[][] getAuthorData()
	{
		NodeList Results = m_document.getElementsByTagName("authorresult");
		int Size = Results.getLength();

		Object[][] result = new Object[Size][7];

		for (int i = 0; i < Size; i++)
		{
			Element tmp = (Element) Results.item(i);
			result[i][0] = tmp.getAttribute("author");
			result[i][1] = tmp.getAttribute("jadeid");
			result[i][2] = tmp.getAttribute("name");
			result[i][3] = tmp.getAttribute("surname");
			result[i][4] = tmp.getAttribute("email");
			result[i][5] = tmp.getAttribute("homepage");
			result[i][6] = tmp.getAttribute("status");
		}

		return result;
	}

	public Object[][] getSnipData()
	{
		NodeList Results = m_document.getElementsByTagName("snipresult");
		int Size = Results.getLength();

		Object[][] result = new Object[Size][7];

		for (int i = 0; i < Size; i++)
		{
			Element tmp = (Element) Results.item(i);
			result[i][0] = tmp.getAttribute("snipname");
			result[i][1] = tmp.getAttribute("author");
			result[i][2] = tmp.getTextContent();
			result[i][3] = tmp.getAttribute("rating");
			result[i][4] = tmp.getAttribute("regdate");
			result[i][5] = tmp.getAttribute("editdate");
			result[i][6] = tmp.getAttribute("jadeid");
		}

		return result;
	}
}
