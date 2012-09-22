package org.vagosduke.andengine.radiance.game.spells.effects;

import org.vagosduke.andengine.radiance.util.DataDictionary;

public interface EffectFactory {
	public BaseEffect createEffect(DataDictionary dict) throws Exception;
}
