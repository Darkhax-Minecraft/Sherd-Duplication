package net.darkhax.sherdduplication.common.impl;

import net.darkhax.bookshelf.common.api.registry.IContentProvider;
import net.darkhax.bookshelf.common.api.registry.register.Register;
import net.darkhax.sherdduplication.common.impl.crafting.SherdCopyRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;

public class ContentProvider implements IContentProvider {

    @Override
    public String contentNamespace() {
        return SherdDuplication.MOD_ID;
    }

    @Override
    public void registerRecipeSerializers(Register<RecipeSerializer<?>> registry) {
        registry.add("copy_sherd", new SimpleCraftingRecipeSerializer<>(SherdCopyRecipe::new));
    }
}