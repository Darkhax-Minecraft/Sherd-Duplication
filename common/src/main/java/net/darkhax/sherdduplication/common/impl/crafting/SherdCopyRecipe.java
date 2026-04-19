package net.darkhax.sherdduplication.common.impl.crafting;

import com.mojang.serialization.MapCodec;
import net.darkhax.sherdduplication.common.impl.SherdDuplication;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NonNull;

public class SherdCopyRecipe extends CustomRecipe {

    public static final SherdCopyRecipe INSTANCE = new SherdCopyRecipe();
    public static final MapCodec<SherdCopyRecipe> MAP_CODEC = MapCodec.unit(INSTANCE);
    public static final StreamCodec<RegistryFriendlyByteBuf, SherdCopyRecipe> STREAM_CODEC = StreamCodec.unit(INSTANCE);
    public static final RecipeSerializer<SherdCopyRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);

    @Override
    public boolean matches(CraftingInput input, @NotNull Level level) {
        if (input.width() != 3 && input.height() != 3) {
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

    @NonNull
    @Override
    public ItemStack assemble(CraftingInput input) {
        final ItemStack sherdStack = input.getItem(1);
        return (sherdStack.is(ItemTags.DECORATED_POT_SHERDS) && !sherdStack.is(SherdDuplication.CANT_DUPLICATE)) ? sherdStack.copyWithCount(4) : new ItemStack(Items.BRICK, 4);
    }

    @NonNull
    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return SERIALIZER;
    }
}