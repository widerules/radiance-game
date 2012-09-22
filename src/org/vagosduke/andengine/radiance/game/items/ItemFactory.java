package org.vagosduke.andengine.radiance.game.items;

import org.vagosduke.andengine.radiance.game.items.ItemTypes.ItemType;
import org.vagosduke.andengine.radiance.program.loader.GameItemLoader;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class ItemFactory {
	
	ItemType type;
	
	public ItemFactory(ItemType itype) {
		type = itype;
	}

	
	public BaseItem create(DataDictionary dict) {
		switch(type) {
		case WEAPON: return GameItemLoader.loadWeaponItem(dict);
		case SHIELD: return GameItemLoader.loadShieldItem(dict);
		case ARMOR: return GameItemLoader.loadArmorItem(dict);
		case ACCESSORY: return GameItemLoader.loadAccessoryItem(dict);	
		case MOVEMENT: return GameItemLoader.loadMovementItem(dict);
		case CONSUMABLE: return GameItemLoader.loadConsumableItem(dict);
		case OTHER: return GameItemLoader.loadOtherItem(dict);
		}
		return null;
	}
}
