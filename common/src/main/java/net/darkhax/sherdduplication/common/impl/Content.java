package net.darkhax.sherdduplication.common.impl;

import net.darkhax.bookshelf.common.api.registry.ContentProvider;
import net.darkhax.bookshelf.common.api.registry.adapters.GameRegistryAdapter;
import net.darkhax.sherdduplication.common.impl.crafting.SherdCopyRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class Content implements ContentProvider {

    @Override
    public String namespace() {
        return SherdDuplication.MOD_ID;
    }

    @Override
    public void defineRecipeSerializers(GameRegistryAdapter<RecipeSerializer<?>> registry) {
        registry.add("copy_sherd", new SimpleCraftingRecipeSerializer<>(SherdCopyRecipe::new));
    }
}