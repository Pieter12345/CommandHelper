package com.laytonsmith.abstraction;

import com.laytonsmith.abstraction.blocks.MCBlockData;
import com.laytonsmith.abstraction.blocks.MCMaterial;
import com.laytonsmith.abstraction.enums.MCEnchantment;
import com.laytonsmith.abstraction.enums.MCItemFlag;
import com.laytonsmith.abstraction.enums.MCItemRarity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MCItemMeta extends AbstractionObject {

	/**
	 * Checks for existence of a display name
	 *
	 * @return true if this has a display name
	 */
	boolean hasDisplayName();

	/**
	 * Gets the display name that is set
	 *
	 * @return the display name that is set
	 */
	String getDisplayName();

	/**
	 * Sets the display name
	 *
	 * @param name the name to set
	 */
	void setDisplayName(String name);

	/**
	 * Checks for existence of lore
	 *
	 * @return true if this has lore
	 */
	boolean hasLore();

	/**
	 * Gets the lore that is set
	 *
	 * @return a list of lore that is set
	 */
	List<String> getLore();

	/**
	 * Sets the lore for this item
	 *
	 * @param lore the lore that will be set
	 */
	void setLore(List<String> lore);

	/**
	 * Checks if this item has any enchantments
	 *
	 * @return true if there are enchantments
	 */
	boolean hasEnchants();

	/**
	 * Gets the enchantments on this items
	 *
	 * @return a map of MCEnchantment keys and enchantLevel values
	 */
	Map<MCEnchantment, Integer> getEnchants();

	/**
	 * Adds a given enchantment to this meta
	 *
	 * @param ench The type of enchantment to add
	 * @param level The level of enchantment
	 * @param ignoreLevelRestriction Should adding an outrageous level be
	 * allowed?
	 * @return whether the enchantment was added successfully
	 */
	boolean addEnchant(MCEnchantment ench, int level, boolean ignoreLevelRestriction);

	/**
	 * Set itemflags which should be ignored when rendering a MCItemStack in the
	 * Client.
	 *
	 * @param flags The flags to ignore.
	 */
	void addItemFlags(MCItemFlag... flags);

	/**
	 * Get current set itemFlags.
	 *
	 * @return A set of all itemFlags set
	 */
	Set<MCItemFlag> getItemFlags();

	/**
	 * Removes a given enchantment from this meta
	 *
	 * @param ench The type of enchantment to remove
	 * @return whether the enchantment was removed successfully
	 */
	boolean removeEnchant(MCEnchantment ench);

	boolean hasRepairCost();

	int getRepairCost();

	void setRepairCost(int cost);

	boolean isUnbreakable();

	void setUnbreakable(boolean unbreakable);

	boolean hasDamage();

	int getDamage();

	void setDamage(int damage);

	boolean hasMaxDamage();

	int getMaxDamage();

	void setMaxDamage(Integer damage);

	MCBlockData getBlockData(MCMaterial material);

	Map<String, String> getExistingBlockData();

	boolean hasBlockData();

	void setBlockData(MCBlockData blockData);

	boolean hasCustomModelData();

	int getCustomModelData();

	void setCustomModelData(int id);

	List<MCAttributeModifier> getAttributeModifiers();

	void setAttributeModifiers(List<MCAttributeModifier> modifiers);

	boolean hasCustomTags();

	MCTagContainer getCustomTags();

	boolean hasItemName();

	String getItemName();

	void setItemName(String name);

	boolean isHideTooltip();

	void setHideTooltip(boolean hide);

	boolean hasEnchantmentGlintOverride();

	boolean getEnchantmentGlintOverride();

	void setEnchantmentGlintOverride(boolean glint);

	boolean hasMaxStackSize();

	int getMaxStackSize();

	void setMaxStackSize(Integer size);

	boolean hasRarity();

	MCItemRarity getRarity();

	void setRarity(MCItemRarity rarity);

	boolean hasEnchantable();

	int getEnchantable();

	void setEnchantable(Integer enchantability);

	boolean hasJukeboxPlayable();

	String getJukeboxPlayable();

	void setJukeboxPlayable(String playable);

	boolean isGlider();

	void setGlider(boolean glider);

	boolean hasUseRemainder();

	MCItemStack getUseRemainder();

	void setUseRemainder(MCItemStack remainder);

	boolean hasFood();

	MCFoodComponent getFood();

	void setFood(MCFoodComponent component);

}
