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
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class PatchContent
{
	private DocumentBuilder m_builder = null;

	private Document m_document = null;

	public PatchContent()
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

	public void newPatch(String snip, String sender)
	{
		m_document = m_builder.newDocument();

		Element newPatchMsg = m_document.createElement("patchmsg");

		Attr Snip = m_document.createAttribute("snip");
		Snip.setValue(snip);

		Attr Sender = m_document.createAttribute("sender");
		Sender.setValue(sender);

		newPatchMsg.setAttributeNode(Snip);
		newPatchMsg.setAttributeNode(Sender);

		newPatchMsg.appendChild(m_document.createTextNode(""));

		m_document.appendChild(newPatchMsg);
	}

	public String getSnip()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("snip");
	}

	public String getSender()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("sender");
	}

	public String getPatch()
	{
		return m_document.getDocumentElement().getTextContent();
	}

	public void appendLine(String line)
	{
		Text txtnode = (Text) m_document.getDocumentElement().getChildNodes()
				.item(0);
		txtnode.appendData(line);
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
		String username = getSender();
		m_document = m_builder.newDocument();

		Element newRegistrationResponse = m_document.createElement("patchmsg");

		newRegistrationResponse.setAttribute("response", resp);
		newRegistrationResponse.setAttribute("username", username);

		m_document.appendChild(newRegistrationResponse);
	}
}