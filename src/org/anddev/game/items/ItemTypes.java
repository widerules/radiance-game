package org.anddev.game.items;

import java.io.InputStream;

import org.anddev.program.config.Filepath;
import org.anddev.program.variables.EnumClass;
import org.anddev.util.FileUtil;
import org.anddev.util.YAMLutil;

public class ItemTypes {
	
	///////////////////////
	// Global Item Enums
	///////////////////////
	public static enum ItemType { WEAPON, SHIELD, ARMOR, MOVEMENT, CONSUMABLE, ACCESSORY, OTHER }		// Item

	public static EnumClass WEAPONtype;			// Weapon subtype
	public static EnumClass ARMORtype;			// Armor subtype
	public static EnumClass SHIELDtype;			// shield subtype
	public static EnumClass MOVEMENTtype;		// boots subtype
	public static EnumClass CONSUMABLEtype;		// consumable subtype
	public static EnumClass ACCESSORYtype;		// accessory subtype
	public static EnumClass OTHERtype;			// other subtype
	
	
	
	///////////////////////
	//	public methods
	///////////////////////
	public static void initItemSubTypes() {
		try {
			InputStream input = FileUtil.open(Filepath.yamlItemSubTypes);
			Object data = YAMLutil.loadData(input);
			WEAPONtype = EnumClass.create(YAMLutil.getArray(YAMLutil.getDictionary(data).get("WeaponType")));
			ARMORtype = EnumClass.create(YAMLutil.getArray(YAMLutil.getDictionary(data).get("ArmorType")));
			SHIELDtype = EnumClass.create(YAMLutil.getArray(YAMLutil.getDictionary(data).get("ShieldType")));
			MOVEMENTtype = EnumClass.create(YAMLutil.getArray(YAMLutil.getDictionary(data).get("MovementType")));
			CONSUMABLEtype = EnumClass.create(YAMLutil.getArray(YAMLutil.getDictionary(data).get("ConsumableType")));
			ACCESSORYtype = EnumClass.create(YAMLutil.getArray(YAMLutil.getDictionary(data).get("AccessoryType")));
			OTHERtype = EnumClass.create(YAMLutil.getArray(YAMLutil.getDictionary(data).get("OtherType")));
			FileUtil.close(Filepath.yamlItemSubTypes);
		}
		catch (Exception err) {
			err.printStackTrace();
		}
	}
}
