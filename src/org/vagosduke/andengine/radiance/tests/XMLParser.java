/*
 *  RADIANCE - An Android 2D turn-based tactics-rpg game.
 *  
 *  Copyright (C) 2011  VagosDuke (vagosduke@gmail.com)
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *  
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
/**
 * @author VagosDuke
 */

package org.vagosduke.andengine.radiance.tests;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.vagosduke.andengine.radiance.game.GameState;
import org.vagosduke.andengine.radiance.util.FileUtil;
import org.vagosduke.andengine.radiance.util.XMLutil;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class XMLParser extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		
		try {
			GameState.init(this);
		}
		catch (Exception e) {
			Toast.makeText(this, "failed!", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		StringBuilder strb = new StringBuilder();
		
		try {
			DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(FileUtil.open("dict/testxml.xml"));
		
			// normalize text representation
			doc.getDocumentElement().normalize();
			System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
			
			
			NodeList listOfWeapons = doc.getDocumentElement().getChildNodes();
            
            for(int s=0; s<listOfWeapons.getLength() ; s++){

                Node WeaponNode = listOfWeapons.item(s);
                if(WeaponNode.getNodeType() == Node.ELEMENT_NODE) {

                    //-------
                    strb.append("Name : " + XMLutil.getFirstElementValue(WeaponNode, "name") + " ");

                    //-------
                    strb.append(" " + XMLutil.getFirstElementValue(WeaponNode, "value") + "$ ");

                    //----
                    strb.append(" (" +  XMLutil.getFirstElementValue(WeaponNode, "range") + ")");
                    strb.append("<" +  XMLutil.getFirstElementAttribute(WeaponNode, "range", "in") + ",");
                    strb.append(	XMLutil.getFirstElementAttribute(WeaponNode, "range", "out") + ">\n");
                    //------


                }//end of if clause


            }//end of for loop with s var


            
		} catch (SAXParseException err) {
        	Log.e("XML", "SAXParseException");
		} catch (SAXException e) {
        	Log.e("XML", "SAXException");
		}catch (Throwable t) {
        	Log.e("ERROR", "Throwable");
		}
        
		Log.d("STRING", strb.toString());
		tv.setText(strb.toString());
		setContentView(tv);
	}
}
