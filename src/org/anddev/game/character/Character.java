package org.anddev.game.character;

import org.anddev.game.combat.Damage;

public class Character {
	
	/**
	 *  The general character "class" is composed from several 
	 *  modules:
	 *  - Info
	 *  - Stats
	 *  - Inventory
	 *  - Condition
	 *  - Combat
	 */
	
	// Modules
	protected InfoManager charInfo;					// Manages static character info like name etc.
	protected SkillsManager charSkills;				// Manages character base and temporary ability scores
	protected AttributesManager charAttributes;
	protected InventoryManager characterInventory;	// Manages items and inventory functions
	protected ConditionManager charCondition;		// Manages temporary character states like buffs etc.
	protected CombatManager charCombat;				// Manages combat functions 
	protected MagicManager charMagic;				// Manages spell-list and spellcasting functions
	
	
	
	
	/////////////////////////////////////////
	// Constructors
	/////////////////////////////////////////
	static public Character make_default_Character() {
		/**
		 *  makes an empty instance with default values from the default private constructor
		 */
		return new Character();
	}
	
//	static public Character make_new_Character(InfoManager info, StatsManager stats, InventoryEquip inventory, 
//			ConditionManager cond, CombatManager cmod) {
//		/**
//		 * creates a PlayerCharacter as new. The module arguments are imported, but 
//		 * condition, temporary HP and MP values, etc. are reset. 
//		 */
//		// TODO
//		return new Character(info, stats, inventory, cond, cmod);
//	}
//	
//	static public Character make_saved_Character(InfoManager info, StatsManager stats, InventoryEquip inventory, 
//			ConditionManager cond, CombatManager cmod) {
//		/** 
//		 * creates a PlayerCharacter importing exactly the modules from arguments
//		 */
//		return new Character(info, stats, inventory, cond, cmod);
//	}
	
	
	
	
	
	
	
	
	////////* Some method examples */////////////	
	public int doDamage(Damage dmg) {
		//return this.charStats.modHP(dmg);
		return 0;
	}
	
	public int doHeal(int heal) {
		//return this.charStats.modHP(heal);
		return 0;
	}
	
	

//	public int equipHandSlot1(int index) {	return characterInventory.equipHandSlot1(index); }
//	public int equipHandSlot2(int index) {	return characterInventory.equipHandSlot2(index); }
//	public int equipShieldSlot(int index) { return characterInventory.equipShieldSlot(index); }
//	public int equipArmorSlot(int index) {	return characterInventory.equipArmorSlot(index); }
	/*
	public Vector<Attack> do_attack() {
		return AttackMethods.do_attack(this);
	}
	*/
	
	
	
	
	/////////////////////////////////////////
	//	Getters / setters
	/////////////////////////////////////////
	
	public InfoManager getInfoManager() { return this.charInfo; }
	public SkillsManager getSkillsManager() { return this.charSkills; }
	public AttributesManager getAttributesManager() { return this.charAttributes; }
	public InventoryManager getInventoryManager() { return this.characterInventory; }
	public ConditionManager getConditionManager() { return this.charCondition; }
	public CombatManager getCombatManager() { return this.charCombat; }
	
	public InfoManager getInfo() { return charInfo; }
	public String getName() { return charInfo.name(); }
	public String getTitle() { return charInfo.title(); }
	public String getBio() { return charInfo.bio(); }
	public Boolean getAlive() { return charInfo.isAlive(); }
	public Boolean getPlayer() { return charInfo.isPlayer(); }

	public InventoryManager getInventory() { return characterInventory; }
	//public WeaponItem getHandSlot1() { return characterInventory.get_handSlot1(); }
	//public WeaponItem getHandSlot2() { return characterInventory.get_handSlot2(); }
	//public ShieldItem getShieldSlot() { return characterInventory.get_shieldSlot(); }
	//public ArmorItem getArmorSlot() { return characterInventory.get_armorSlot(); }
	//public void addWeaponByName(String iname) { characterInventory.addWeapon(GlobalItemList.get_weapon_byName(iname));}
	//public void addArmorByName(String iname) { characterInventory.addArmor(GlobalItemList.get_armor_byName(iname));}
	//public void addShieldByName(String iname) { characterInventory.addShield(GlobalItemList.get_shield_byName(iname));}
	//public void addConsumableByName(String iname) { characterInventory.addConsumable(GlobalItemList.get_consumable_byName(iname));}
	//public void addOtherByName(String iname) { characterInventory.addOther(GlobalItemList.get_other_byName(iname));}
	
	
	
	
	
	
	
	
	
	/////////////////////////////////////////
	// Private Constructors
	/////////////////////////////////////////
	protected Character() {
		this.charInfo = new InfoManager(this);
		this.charSkills = new SkillsManager(this);
		this.charAttributes = new AttributesManager(this);
		this.characterInventory = new InventoryManager(this);
		this.charCondition = ConditionManager.newConditionManager();
	}
	protected Character(InfoManager info, SkillsManager stats, AttributesManager attributes,
			InventoryManager inventory, ConditionManager cond, CombatManager cmod) {
		this.charInfo = info;
		this.charSkills = stats;
		this.charAttributes = attributes;
		this.characterInventory = inventory;
		this.charCondition = cond;
		this.charCombat = cmod;
	}

	
}
