package org.anddev.tests;
import org.anddev.engine.program.Init;
import org.anddev.game.character.PlayerCharacter;
import org.anddev.game.items.GlobalItemList;
import org.anddev.game.items.ItemBase;

import android.app.Activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;


public class AndroidInterfaceTest extends Activity {
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView tv = new TextView(this);
		StringBuilder strb = new StringBuilder();
		tv.setText("...Loading...");
		setContentView(tv);
		
		
		try {
			Init.init_All(this);
		}
		catch (Exception e) {
			Toast.makeText(this, "init. failed!", Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
		
		//System.out.println(GlobalItemList.staticToString());
		
		PlayerCharacter player1 = PlayerCharacter.make_empty_PlayerCharacter();
		
//		player1.getInventory().addWeapon(GlobalItemList.getWeapon("Dagger"));
//		player1.getInventory().addWeapon(GlobalItemList.getWeapon("Dagger"));
//		player1.getInventory().addShield(GlobalItemList.getShield("Buckler"));
		player1.getInventory().equipHandSlot1(0);
		player1.getInventory().equipShieldSlot(0);
		System.out.println(player1.getInventory().toString());
		
		strb.append(GlobalItemList.getWeaponList(0, 99, true, true).toString());
		strb.append("\n\n");
		strb.append(ItemBase.ARMtype.valueOf("CLOTH").getValue());
		tv.setText(strb.toString());
		setContentView(tv);
	}
	
	
	
}