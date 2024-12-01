package net.darkhax.sherdduplication.common.impl;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SherdDuplication {

    public static final String MOD_ID = "sherdduplication";
    public static final String MOD_NAME = "SherdDuplication";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static final TagKey<Item> OUTER_INGREDIENT = itemTag("outer_ingredient");
    public static final TagKey<Item> INNER_INGREDIENT = itemTag("inner_ingredient");
    public static final TagKey<Item> CANT_DUPLICATE = itemTag("cant_duplicate");

    public static TagKey<Item> itemTag(String path) {
        return TagKey.create(Registries.ITEM, id(path));
    }

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }
}