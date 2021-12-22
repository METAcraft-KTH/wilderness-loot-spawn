package net.lucasdow.wildernessmod.mixin;

import net.lucasdow.wildernessmod.WildernessMod;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.world.gen.feature.DungeonFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DungeonFeature.class)
public class WildernessModMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		WildernessMod.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
