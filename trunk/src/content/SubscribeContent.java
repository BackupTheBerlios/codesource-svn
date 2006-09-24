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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SubscribeContent
{
	private DocumentBuilder m_builder = null;

	private Document m_document = null;

	public SubscribeContent()
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

	public void newSubscribe(String snip, String username)
	{
		m_document = m_builder.newDocument();

		Element newCommentMsg = m_document.createElement("subscribemsg");

		Attr Snip = m_document.createAttribute("snip");
		Snip.setValue(snip);

		Attr Username = m_document.createAttribute("username");
		Snip.setValue(snip);

		newCommentMsg.setAttributeNode(Snip);
		newCommentMsg.setAttributeNode(Username);

		m_document.appendChild(newCommentMsg);
	}

	public String getSnip()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("snip");
	}

	public String getUsername()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("username");
	}

	public boolean getResponse()
	{
		String response = m_document.getDocumentElement().getAttribute(
				"response");

		if (response.equals("ok"))
		{
			return true;
		} else if (response.equals("no"))
		{
			return false;
		}
		return false;
	}

	public void newResponse(String resp)
	{
		m_document = m_builder.newDocument();

		Element newRegistrationResponse = m_document.createElement("loginmsg");

		newRegistrationResponse.setAttribute("response", resp);
		m_document.appendChild(newRegistrationResponse);
	}
}