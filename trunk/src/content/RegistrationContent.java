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

public class RegistrationContent
{
	private DocumentBuilder m_builder = null;

	private Document m_document = null;

	public RegistrationContent()
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

	public void newRegistration(String username, String password)
	{
		m_document = m_builder.newDocument();

		Element newLoginMsg = m_document.createElement("registrationmsg");

		Attr UserName = m_document.createAttribute("username");
		UserName.setValue(username);

		Attr Password = m_document.createAttribute("password");
		Password.setValue(password);

		newLoginMsg.setAttributeNode(Password);
		newLoginMsg.setAttributeNode(UserName);

		m_document.appendChild(newLoginMsg);
	}

	public void setNome(String value)
	{
		Element Name = m_document.createElement("name");
		Name.appendChild(m_document.createTextNode(value));

		Element root = m_document.getDocumentElement();
		root.appendChild(Name);
	}

	public void setCognome(String value)
	{
		Element Name = m_document.createElement("surname");
		Name.appendChild(m_document.createTextNode(value));

		Element root = m_document.getDocumentElement();
		root.appendChild(Name);
	}

	public void setEmail(String value)
	{
		Element Name = m_document.createElement("email");
		Name.appendChild(m_document.createTextNode(value));

		Element root = m_document.getDocumentElement();
		root.appendChild(Name);
	}

	public void setHomePage(String value)
	{
		Element Name = m_document.createElement("homepage");
		Name.appendChild(m_document.createTextNode(value));

		Element root = m_document.getDocumentElement();
		root.appendChild(Name);
	}

	public String getUsername()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("username");
	}

	public String getPassword()
	{
		Element root = m_document.getDocumentElement();
		return root.getAttribute("password");
	}

	public String getNome()
	{
		NodeList nl = m_document.getDocumentElement().getElementsByTagName(
				"name");
		return nl.item(0).getTextContent();
	}

	public String getCognome()
	{
		NodeList nl = m_document.getDocumentElement().getElementsByTagName(
				"surname");
		return nl.item(0).getTextContent();
	}

	public String getEmail()
	{
		NodeList nl = m_document.getDocumentElement().getElementsByTagName(
				"email");
		return nl.item(0).getTextContent();
	}

	public String getHomePage()
	{
		NodeList nl = m_document.getDocumentElement().getElementsByTagName(
				"homepage");
		return nl.item(0).getTextContent();
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

	public String getResponseMsg()
	{
		return m_document.getDocumentElement().getAttribute("response");
	}

	public void newResponse(String resp)
	{
		m_document = m_builder.newDocument();

		Element newRegistrationResponse = m_document
				.createElement("registrationmsg");
		newRegistrationResponse.setAttribute("response", resp);

		m_document.appendChild(newRegistrationResponse);
	}
}
