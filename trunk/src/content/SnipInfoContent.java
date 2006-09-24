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
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class SnipInfoContent
{
	private DocumentBuilder m_builder = null;

	private Document m_document = null;

	public SnipInfoContent()
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

	public void newSnip(String snipname)
	{
		m_document = m_builder.newDocument();

		Element newSnipMsg = m_document.createElement("snipmsg");
		Attr SnipName = m_document.createAttribute("name");

		SnipName.setValue(snipname);

		newSnipMsg.setAttributeNode(SnipName);

		m_document.appendChild(newSnipMsg);
	}

	public void setAuthor(String value)
	{
		Attr attrib = m_document.createAttribute("author");
		attrib.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(attrib);
	}

	public void setRating(String value)
	{
		Attr attrib = m_document.createAttribute("rating");
		attrib.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(attrib);
	}

	public void setStatus(String value)
	{
		Attr Status = m_document.createAttribute("status");
		Status.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(Status);
	}

	public void setVersion(String value)
	{
		Attr Version = m_document.createAttribute("version");
		Version.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(Version);
	}

	public void setLanguage(String value)
	{
		Attr Language = m_document.createAttribute("language");
		Language.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(Language);
	}

	public void setLicenza(String value)
	{
		Attr License = m_document.createAttribute("license");
		License.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(License);
	}

	public void setFile(String value)
	{
		Attr File = m_document.createAttribute("file");
		File.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(File);
	}

	public void setRegData(String value)
	{
		Attr attrib = m_document.createAttribute("regdata");
		attrib.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(attrib);

	}

	public void setEditData(String value)
	{
		Attr attrib = m_document.createAttribute("editdata");
		attrib.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(attrib);
	}

	public void setJadeId(String value)
	{
		Attr attrib = m_document.createAttribute("jadeid");
		attrib.setValue(value);

		Element root = m_document.getDocumentElement();
		root.setAttributeNode(attrib);
	}

	public void setDescription(String value)
	{
		Element Description = m_document.createElement("description");
		Description.appendChild(m_document.createTextNode(value));

		Element root = m_document.getDocumentElement();
		root.appendChild(Description);
	}

	public String getAuthor()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("author");
	}

	public String getSnipName()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("name");
	}

	public String getStatus()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("status");
	}

	public String getVersion()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("version");
	}

	public String getLanguage()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("language");
	}

	public String getLicense()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("license");
	}

	public String getFile()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("file");
	}

	public String getRating()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("rating");
	}

	public String getRegData()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("regdata");
	}

	public String getEditData()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("editdata");
	}

	public String getJadeId()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("jadeid");
	}

	public String getDescription()
	{
		NodeList nl = m_document.getDocumentElement().getElementsByTagName(
				"description");
		return nl.item(0).getTextContent();
	}
}
