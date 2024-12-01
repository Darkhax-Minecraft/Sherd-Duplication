package net.darkhax.sherdduplication.common.impl.crafting;

import net.darkhax.bookshelf.common.api.function.CachedSupplier;
import net.darkhax.sherdduplication.common.impl.SherdDuplication;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class SherdCopyRecipe extends CustomRecipe {

    private static final CachedSupplier<RecipeSerializer<?>> RECIPE_SERIALIZER = CachedSupplier.of(BuiltInRegistries.RECIPE_SERIALIZER, SherdDuplication.id("copy_sherd"));

    public SherdCopyRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput input, @NotNull Level level) {
        if (!this.canCraftInDimensions(input.width(), input.height())) {
            return false;
        }
        for (int slotId = 0; slotId < input.size(); slotId++) {
            final ItemStack currentStack = input.getItem(slotId);
            if (slotId == 1 && !currentStack.is(ItemTags.DECORATED_POT_SHERDS) || currentStack.is(SherdDuplication.CANT_DUPLICATE)) {
                return false;
            }
            else if (slotId == 4 && !currentStack.is(SherdDuplication.INNER_INGREDIENT)) {
                return false;
            }
            else if (slotId != 1 && slotId != 4 && !currentStack.is(SherdDuplication.OUTER_INGREDIENT)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public @NotNull ItemStack assemble(@NotNull CraftingInput input, @NotNull HolderLookup.Provider provider) {
        final ItemStack sherdStack = input.getItem(1);
        return (sherdStack.is(ItemTags.DECORATED_POT_SHERDS) && !sherdStack.is(SherdDuplication.CANT_DUPLICATE)) ? sherdStack.copyWithCount(4) : new ItemStack(Items.BRICK, 4);
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width == 3 && height == 3;
    }

    @Override
    public @NotNull RecipeSerializer<?> getSerializer() {
        return RECIPE_SERIALIZER.get();
    }
}