package net.darkhax.sherdduplication.common.impl.addons.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IJeiHelpers;
import mezz.jei.api.registration.IRecipeRegistration;
import net.darkhax.sherdduplication.common.impl.SherdDuplication;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;
import java.util.List;

@JeiPlugin
public class JEIPlugin implements IModPlugin {

    private static final ResourceLocation PLUGIN_ID = SherdDuplication.id("jei_plugin");

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return PLUGIN_ID;
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        registration.addRecipes(RecipeTypes.CRAFTING, createRecipes(registration.getJeiHelpers()));
    }

    private static List<RecipeHolder<CraftingRecipe>> createRecipes(IJeiHelpers helpers) {
        final List<RecipeHolder<CraftingRecipe>> recipes = new LinkedList<>();
        final String group = SherdDuplication.MOD_ID + ".jei.sherds";
        for (Holder<Item> item : BuiltInRegistries.ITEM.getTagOrEmpty(ItemTags.DECORATED_POT_SHERDS)) {
            if (!item.is(SherdDuplication.CANT_DUPLICATE)) {
                final ResourceLocation itemId = item.unwrapKey().orElseThrow().location();
                final ResourceLocation recipeId = SherdDuplication.id("jei." + itemId.getNamespace() + "." + itemId.getPath());
                final ItemStack sherdInput = item.value().getDefaultInstance();
                final ItemStack output = sherdInput.copyWithCount(4);
                recipes.add(new RecipeHolder<>(recipeId, helpers.getVanillaRecipeFactory().createShapedRecipeBuilder(CraftingBookCategory.MISC, List.of(output))
                        .group(group)
                        .define('a', Ingredient.of(SherdDuplication.OUTER_INGREDIENT))
                        .define('b', Ingredient.of(SherdDuplication.INNER_INGREDIENT))
                        .define('c', Ingredient.of(item.value()))
                        .pattern("aca")
                        .pattern("aba")
                        .pattern("aaa")
                        .build()));
            }
        }

        return recipes;
    }
}
