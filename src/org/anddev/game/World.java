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

package org.anddev.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;




public class World {
	
	final String fileName = "map.txt";
	
	private terrainTypeUtil.terrainType terrain[][];
	private WorldItem Entities;
	private int worldWidth, worldHeight;
	
	
	public World() {
		// Init world
		init_terrain();
		Entities = new WorldItem(worldWidth, worldHeight);
	}
	
	private void init_terrain() {
	    try {
	    	File inFile = new File(fileName);
	    	Scanner scan = new Scanner(inFile);
	    	BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(inFile)));
	    	
	    	// first line contain dimensions
	    	worldWidth = scan.nextInt();
	    	worldHeight = scan.nextInt();
	    	
	    	terrain = new terrainTypeUtil.terrainType[worldWidth][worldHeight];
	    	for(int i=0; i<worldWidth; i++) {
	    		for(int j=0; j<worldHeight; j++) {
	    			terrain[i][j] = terrainTypeUtil.char2tt((char) scan.nextByte());
	    		}
	    	}
	    	br.close();
	    } // Try
	    catch (FileNotFoundException ex) {
	    //
	    } catch (IOException ex) {
	    //
	    }
	}
	
	
	
	
	/*
	public static void main(String[] arguments) {
		Character Bob = new Character();
		
		Bob.do_damage(10);
		Bob.do_damage(5);
		Bob.do_heal(30);
		Bob.do_exp(50);
	}
	*/
}
