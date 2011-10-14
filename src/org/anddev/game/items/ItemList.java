package org.anddev.game.items;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;

import org.anddev.util.XMLutil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.util.Log;

public abstract class ItemList<I extends Item>{
	
	protected SortingItemList<I> sortingList;			// for sorting and sequential retrieval
	protected HashMap<String, I> dictionaryList;		// for fast retrieval by name
	
	
	int numberOfItems;
	Class<I> containsClass;
	
	
	
	////////////////////////
	//	Constructor
	////////////////////////
	public ItemList(Class<I> clazz) {
		numberOfItems = 0;
		this.containsClass = clazz;
		this.sortingList = new SortingItemList<I>();
		this.dictionaryList = new HashMap<String, I>();
	}

	
	
	
	
	
	///////////////////////////////
	//	Public Functions
	///////////////////////////////
	public boolean add(I item) {
		this.sortingList.add(item);
		this.dictionaryList.put(item.name, item);
		this.numberOfItems++;
		return true;
	}
	
	public I getByIndex(int index) {
		return this.sortingList.get(index);
	}
	public I getByName(String name) {
		return this.dictionaryList.get(name);
	}
	public int size() {
		return numberOfItems;
	}
	
	
	//////////////////////////////
	//	Private Constructor
	//////////////////////////////
	protected void loadFromXML(InputStream ifile) {
		try {
			NodeList listOfItems = XMLutil.getXMLroot(ifile).getChildNodes();
			for(int s=0; s<listOfItems.getLength() ; s++){
				Node itemNode = listOfItems.item(s);
				if(itemNode.getNodeType() == Node.ELEMENT_NODE) {
					I absItem = containsClass.newInstance();
					try {
						absItem.load(itemNode);
					}catch (Exception t) {
						StringWriter sw = new StringWriter();
						PrintWriter pw = new PrintWriter(sw);
						t.printStackTrace(pw);
						Log.e("ITEM-LOAD", sw.toString());
					}
					this.add(absItem);
				}
			}
		}
		catch (Exception err) {
			Log.e("ITEMLIST", err.getMessage());
		}
	}
}
