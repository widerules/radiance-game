package org.anddev.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.anddev.engine.program.GameState;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLutil {
	
	public static String getThisNodeValue(Node node) {
		/**
		 * Return the text value of this node
		 */
		return node.getFirstChild().getNodeValue().trim();
	}
	
	public static String getThisNodeAttribute(Node node, String attrname) {
		/**
		 * Returns the string value of the attribute attrname 
		 * of this node
		 */
		return node.getAttributes().getNamedItem(attrname).getNodeValue().trim();
	}
	
	
	
	public static String getFirstElementValue(Node node, String nodename) {
	/**
	 *  returns the string value of the first child element of node
	 *  with the id name
	 */
		
		Element elem = (Element)node;
		return elem.getElementsByTagName(nodename).item(0).getFirstChild().getNodeValue().trim();
	}
	
	public static String getFirstElementAttribute(Node node, String nodename, String attrname) {
		/**
		 * Returns the string value of the attribute attrname 
		 * of the first child element of node with name nodename 
		 */
		Element elem = (Element)node;
		return elem.getElementsByTagName(nodename).item(0).getAttributes().
			getNamedItem(attrname).getNodeValue().trim();
	}
	
	
	
	
	public static NodeList getSubTree(Node node, String nodename) {
		/**
		 * returns the nodelist from the named node and below 
		 * (as if it was the root)
		 */
		Element elem = (Element)node;
		return elem.getElementsByTagName(nodename).item(0).getChildNodes();
	}
	
	
	
	
	public static Element getXMLroot(InputStream file) throws ParserConfigurationException, IOException, SAXException {
		/**
		 * Opens the xml asset file from the Android(asset) system, 
		 * parses the file into a dom document,
		 * and then returns the parsed tree-structure's root
		 */
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(file);
		doc.getDocumentElement().normalize();
		return doc.getDocumentElement();
	}
	
	public static InputStream getAsset(String path) throws IOException {
		return GameState.getProgramInterface().getAssetFile(path);
	}
	
	public static InputStream getFile(String path) throws IOException {
		return new FileInputStream(path);
	}
	

	public static void close(InputStream file) throws IOException {
		file.close();
	}
}
