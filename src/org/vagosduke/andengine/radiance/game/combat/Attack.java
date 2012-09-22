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

package org.vagosduke.andengine.radiance.game.combat;

import java.util.ArrayList;
import org.vagosduke.andengine.radiance.game.character.Character;
import org.vagosduke.andengine.radiance.game.items.WeaponItem;

public class Attack {

	private WeaponItem weapon;
	private ArrayList<Damage> damageVector;
	private int offenseSkill;
	private double CriticalChance;
	
	public Attack(int iattack, double icritical, WeaponItem iweapon) {
		this.offenseSkill = iattack;
		this.weapon = iweapon;
		this.CriticalChance = icritical;
		this.damageVector = this.weapon.generateDamage();
	}
	public Attack(int ioffense, double icritical,  ArrayList<Damage> idamageVector) {
		this.offenseSkill = ioffense;
		this.weapon = null;
		this.CriticalChance = icritical;
		this.damageVector = idamageVector;
	}
	public Attack(Character attacker, WeaponItem iweapon) {
		this.offenseSkill = attacker.getAttributesModule().getOffense();
		this.CriticalChance = attacker.getAttributesModule().getCriticalChance();
		this.weapon = iweapon;
		this.damageVector = new ArrayList<Damage>();
	}
	
	public void add(Damage dmg) {
		damageVector.add(dmg);
	}
	public void remove(int key) {
		damageVector.remove(key);
	}

	
	
	
	///////////////////////
	//	Getters/Setters
	///////////////////////
	public WeaponItem getWeapon() { return weapon; }
	public ArrayList<Damage> getDamageVector() { return damageVector; }
	public int getOffenseSkill() { return offenseSkill; }
	public double getCriticalChance() { return CriticalChance; }

	public void setWeapon(WeaponItem iweapon) { this.weapon = iweapon; }
	public void setDamageVector(ArrayList<Damage> idamageVector) { this.damageVector = idamageVector; }
	public void setOffenseSkill(int ioffenseSkill) { this.offenseSkill = ioffenseSkill; }
	public void setCriticalChance(double icriticalChance) { CriticalChance = icriticalChance; }
}
