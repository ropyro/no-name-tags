package no.name.tags;

import net.fabricmc.api.ModInitializer;

import net.minecraft.client.option.KeyBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoNameTags implements ModInitializer {

	public static final Logger LOGGER = LoggerFactory.getLogger("no-name-tags");
	public static KeyBinding keyHideNameTags;
	public static boolean hideNameTags;

	public static KeyBinding keyHideHotBar;
	public static boolean hideHotBar;

	public static KeyBinding keyHideFire;
	public static boolean hideFire;

	@Override
	public void onInitialize() {
		LOGGER.info("No name tags initialized!");
	}
}