package content;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class DownloadContent
{
	private DocumentBuilder m_builder = null;

	private Document m_document = null;

	public DownloadContent()
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

	public void newDownload()
	{
		m_document = m_builder.newDocument();

		Element body = m_document.createElement("body");
		body.appendChild(m_document.createTextNode(""));

		m_document.appendChild(body);
	}

	public void appendLine(String line)
	{
		Text txtnode = (Text) m_document.getDocumentElement().getChildNodes()
				.item(0);
		txtnode.appendData(line);
	}

	public String getBody()
	{
		Element root = m_document.getDocumentElement();
		return root.getTextContent();
	}
}