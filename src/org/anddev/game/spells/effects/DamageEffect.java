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

package org.anddev.game.spells.effects;

import java.util.Random;

import org.anddev.engine.variables.EnumValue;
import org.anddev.game.character.Character;
import org.anddev.game.combat.Damage;
import org.anddev.game.constants.Types.templateType;
import org.anddev.game.template.Template.TemplateInfo;
import org.anddev.util.XMLutil;
import org.w3c.dom.Node;

public class DamageEffect implements BaseEffect{

	private float basePower;
	private float basePowerVariation;
	private float magicPowerModifier;
	private EnumValue dmgType;
	private TemplateInfo effectTemplate;
	
	///////////////////////////////
	//	Constructor
	///////////////////////////////
	public DamageEffect() {
		this.basePower = 1;
		this.basePowerVariation = 0;
		this.magicPowerModifier = 0;
		this.dmgType = Damage.DMGtype.defaultValue();
		effectTemplate = new TemplateInfo();
	}
	@Override
	public BaseEffect create(Node neffect) throws Exception {
		loadFromNode(neffect);
		return this;
	}

	
	
	
	
	////////////////////////////////
	//	Public Functions
	////////////////////////////////
	@Override
	public void apply(Character caster, Character target) {
		/**
		 * Inflicts damage to target
		 * Damage is calculated as 
		 * (BasePower) +
		 * (+ or - a random percent between 0% and basePowerVariation of BasePower) +
		 * (magicPowerModifier percent the caster's Magic attribute)
		 */
		Random generator = new Random();
		double percent = ((generator.nextDouble()*2.0)/this.basePowerVariation) - this.basePowerVariation;
		int damageFinal = (int) Math.round( this.basePower + 
				( caster.getSkillsManager().getMagic() * this.magicPowerModifier ) + 
				( ((double)this.basePower) * percent) );
		Damage dmg = Damage.create(damageFinal, dmgType);
		
		target.doDamage(dmg);
	}

	@Override
	public TemplateInfo getEffectTemplate() {
		return this.effectTemplate;
	}
	
	
	
	
	/////////////////////////////////
	//	Private Functions
	/////////////////////////////////
	private void loadFromNode(Node neffect) throws Exception {
		this.basePower = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "power"));
		this.basePowerVariation = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "powerVar"));
		this.magicPowerModifier = Float.parseFloat(XMLutil.getFirstElementValue(neffect, "magicMod"));
		this.dmgType = Damage.DMGtype.valueOf(XMLutil.getFirstElementValue(neffect, "dmgType"));
		this.effectTemplate.shape = templateType.valueOf(XMLutil.getFirstElementValue(neffect, "effectTemplate"));
		this.effectTemplate.rangeIn = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "in"));
		this.effectTemplate.rangeOut = Integer.parseInt(XMLutil.getFirstElementAttribute(neffect, "effectTemplate", "out"));
	}
	
}
