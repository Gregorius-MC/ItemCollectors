package com.supermartijn642.itemcollectors.screen;

import com.supermartijn642.itemcollectors.CollectorTile;
import com.supermartijn642.itemcollectors.ItemCollectors;
import com.supermartijn642.itemcollectors.packet.*;
import net.minecraft.client.resources.I18n;

/**
 * Created 7/15/2020 by SuperMartijn642
 */
public class BasicCollectorScreen extends CollectorScreen<BasicCollectorContainer> {

    private ArrowButton upXButton, downXButton;
    private ArrowButton upYButton, downYButton;
    private ArrowButton upZButton, downZButton;

    public BasicCollectorScreen(BasicCollectorContainer container){
        super(container, ItemCollectors.basic_collector.getTranslationKey());
    }

    @Override
    protected void addButtons(CollectorTile tile){
        this.upXButton = this.addButton(new ArrowButton(this.guiLeft + 40, this.guiTop + 37, false, () -> ItemCollectors.CHANNEL.sendToServer(new PacketIncreaseXRange(this.container.pos))));
        this.downXButton = this.addButton(new ArrowButton(this.guiLeft + 40, this.guiTop + 63, true, () -> ItemCollectors.CHANNEL.sendToServer(new PacketDecreaseXRange(this.container.pos))));
        this.upYButton = this.addButton(new ArrowButton(this.guiLeft + 93, this.guiTop + 37, false, () -> ItemCollectors.CHANNEL.sendToServer(new PacketIncreaseYRange(this.container.pos))));
        this.downYButton = this.addButton(new ArrowButton(this.guiLeft + 93, this.guiTop + 63, true, () -> ItemCollectors.CHANNEL.sendToServer(new PacketDecreaseYRange(this.container.pos))));
        this.upZButton = this.addButton(new ArrowButton(this.guiLeft + 146, this.guiTop + 37, false, () -> ItemCollectors.CHANNEL.sendToServer(new PacketIncreaseZRange(this.container.pos))));
        this.downZButton = this.addButton(new ArrowButton(this.guiLeft + 146, this.guiTop + 63, true, () -> ItemCollectors.CHANNEL.sendToServer(new PacketDecreaseZRange(this.container.pos))));
    }

    @Override
    protected void drawToolTips(CollectorTile tile, int mouseX, int mouseY){
        if(this.upXButton.isHovered() || this.upYButton.isHovered() || this.upZButton.isHovered())
            this.renderToolTip(true, "gui.itemcollectors.basic_collector.range.increase", mouseX, mouseY);
        if(this.downXButton.isHovered() || this.downYButton.isHovered() || this.downZButton.isHovered())
            this.renderToolTip(true, "gui.itemcollectors.basic_collector.range.decrease", mouseX, mouseY);
    }

    @Override
    protected void tick(CollectorTile tile){
    }

    @Override
    protected String getBackground(){
        return "basic_collector_screen.png";
    }

    @Override
    protected void drawText(CollectorTile tile){
        this.drawCenteredString(this.title, this.xSize / 2f, 6);
        String range = I18n.format("gui.itemcollectors.basic_collector.range")
            .replace("$numberx$","" + (tile.rangeX * 2 + 1))
            .replace("$numbery$","" + (tile.rangeY * 2 + 1))
            .replace("$numberz$","" + (tile.rangeZ * 2 + 1));
        this.drawString(range,8, 26);

        this.drawCenteredString("x:", 35, 51);
        this.drawCenteredString("" + tile.rangeX, 49, 52);
        this.drawCenteredString("y:", 88, 51);
        this.drawCenteredString("" + tile.rangeY, 102, 52);
        this.drawCenteredString("z:", 141, 51);
        this.drawCenteredString("" + tile.rangeZ, 155, 52);
    }
}
