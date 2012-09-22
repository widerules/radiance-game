package org.vagosduke.andengine.radiance.util.beanClasses;

import org.vagosduke.andengine.radiance.game.template.TemplateInfo;
import org.vagosduke.andengine.radiance.game.template.Template.returnTarget;
import org.vagosduke.andengine.radiance.game.template.Template.snapTarget;
import org.vagosduke.andengine.radiance.game.template.Template.templateType;

public class BeanTemplateInfo {
	
	public int rangeIn;
	public int rangeOut;
	public templateType shape;
	public snapTarget snaps;
	public returnTarget targets;
	
	
	public BeanTemplateInfo() {
		this.rangeIn = 0;
		this.rangeOut = 0;
		this.shape = templateType.DIAMOND;
		this.snaps = snapTarget.ENEMY;
		this.targets = returnTarget.BOTH;
	}


	public int getRangeIn() {
		return rangeIn;
	}
	public int getRangeOut() {
		return rangeOut;
	}
	public templateType getShape() {
		return shape;
	}
	public snapTarget getSnaps() {
		return snaps;
	}
	public returnTarget getTargets() {
		return targets;
	}
	
	public void setRangeIn(int rangeIn) {
		this.rangeIn = rangeIn;
	}
	public void setRangeOut(int rangeOut) {
		this.rangeOut = rangeOut;
	}
	public void setShape(templateType shape) {
		this.shape = shape;
	}
	public void setSnaps(snapTarget snaps) {
		this.snaps = snaps;
	}
	public void setTargets(returnTarget targets) {
		this.targets = targets;
	}

	
	
	/////////////
	// Methods
	/////////////
	public TemplateInfo extract() {
		return new TemplateInfo(this.rangeIn, this.rangeOut, this.shape, this.snaps, this.targets);
	}
	
}
