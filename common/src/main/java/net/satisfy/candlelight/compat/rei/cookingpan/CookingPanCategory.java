package net.satisfy.candlelight.compat.rei.cookingpan;

import com.google.common.collect.Lists;
import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.network.chat.Component;
import net.satisfy.candlelight.registry.ObjectRegistry;
import net.satisfy.candlelight.block.entity.CookingPanBlockEntity;

import java.util.List;

public class CookingPanCategory implements DisplayCategory<CookingPanDisplay> {

    @Override
    public CategoryIdentifier<CookingPanDisplay> getCategoryIdentifier() {
        return CookingPanDisplay.COOKING_PAN_DISPLAY;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("rei.candlelight.cooking_pan_category");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ObjectRegistry.COOKING_PAN.get());
    }

    @Override
    public List<Widget> setupDisplay(CookingPanDisplay display, Rectangle bounds) {
        Point startPoint = new Point(bounds.getCenterX() - 55, bounds.getCenterY() - 13);
        List<Widget> widgets = Lists.newArrayList();
        widgets.add(Widgets.createRecipeBase(bounds));
        widgets.add(Widgets.createArrow(new Point(startPoint.x + 54, startPoint.y - 1)).animationDurationTicks(CookingPanBlockEntity.MAX_COOKING_TIME));
        widgets.add(Widgets.createResultSlotBackground(new Point(startPoint.x + 90, startPoint.y)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 90, startPoint.y)).entries(display.getOutputEntries().get(0)).disableBackground().markOutput());
        for(int i = 0; i < 6; i++){
            int x = i * 18;
            int y = -4;
            if(i > 2){
                x = (i - 3) * 18;
                y+=18;
            }
            x-=8;
            if(i >= display.getInputEntries().size() - 1) widgets.add(Widgets.createSlotBackground(new Point(startPoint.x + x, startPoint.y + y)));
            else widgets.add(Widgets.createSlot(new Point(startPoint.x + x, startPoint.y + y)).entries(display.getInputEntries().get(i + 1)).markInput());
        }
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 56, startPoint.y + 23)).entries(display.getInputEntries().get(0)).markInput());
        return widgets;
    }
}
