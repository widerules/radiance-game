package org.vagosduke.andengine.radiance.game.template;

import org.vagosduke.andengine.radiance.game.template.Template.returnTarget;
import org.vagosduke.andengine.radiance.game.template.Template.snapTarget;
import org.vagosduke.andengine.radiance.game.template.Template.templateType;
import org.vagosduke.andengine.radiance.program.log.FileErrors;
import org.vagosduke.andengine.radiance.util.DataDictionary;

public class TemplateInfo {

	public int rangeIn;
	public int rangeOut;
	public templateType shape;
	public snapTarget snaps;
	public returnTarget targets;
	
	
	
	////////////////////////
	//	Static Constructor
	////////////////////////
//	public static TemplateInfo create(DataDictionary dict) {
//		TemplateInfo ret = new TemplateInfo();
//		return ret.load(dict);
//	}
	
	public TemplateInfo() {
		this.rangeIn = 0;
		this.rangeOut = 0;
		this.shape = templateType.DIAMOND;
		this.snaps = snapTarget.ENEMY;
		this.targets = returnTarget.BOTH;
	}
	public TemplateInfo(int rangeIn, int rangeOut, templateType shape,
			snapTarget snaps, returnTarget targets) {
		this.rangeIn = rangeIn;
		this.rangeOut = rangeOut;
		this.shape = shape;
		this.snaps = snaps;
		this.targets = targets;
	}






	/////////////////
	//	Loader
	/////////////////
//	public TemplateInfo load(DataDictionary dict) throws Exception {
//		String job = "N/A";
//		try {
//			job = "rangeIn"; this.rangeIn = dict.getInteger("rangeIn");
//			job = "rangeOut"; this.rangeOut = dict.getInteger("rangeOut");
//			job = "shape"; if(dict.exists("shape")) {this.shape = templateType.valueOf(dict.getString("shape")); }
//			job = "snaps"; if(dict.exists("snaps")) {this.snaps = snapTarget.valueOf(dict.getString("snaps")); }
//			job = "targets"; if(dict.exists("targets")) {this.targets = returnTarget.valueOf(dict.getString("targets")); }
//		}
//		catch (Exception err) {
//			FileErrors.globalErrors.addError("TEPLATE-LOAD", ("TemplateInfo.load, at=" + job), err);
//			throw err;
//		}
//		return this;
//	}
}
