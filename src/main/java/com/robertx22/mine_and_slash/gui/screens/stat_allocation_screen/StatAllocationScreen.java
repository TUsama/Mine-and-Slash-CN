package com.robertx22.mine_and_slash.gui.screens.stat_allocation_screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.mine_and_slash.database.stats.Stat;
import com.robertx22.mine_and_slash.database.stats.types.core_stats.base.BaseCoreStat;
import com.robertx22.mine_and_slash.gui.bases.BaseScreen;
import com.robertx22.mine_and_slash.gui.bases.IAlertScreen;
import com.robertx22.mine_and_slash.gui.bases.INamedScreen;
import com.robertx22.mine_and_slash.gui.screens.main_hub.MainHubScreen;
import com.robertx22.mine_and_slash.gui.screens.spell_hotbar_setup.SpellHotbatSetupScreen;
import com.robertx22.mine_and_slash.mmorpg.MMORPG;
import com.robertx22.mine_and_slash.mmorpg.Ref;
import com.robertx22.mine_and_slash.packets.allocation.stats.SpendStatPointPacket;
import com.robertx22.mine_and_slash.packets.sync_cap.PlayerCaps;
import com.robertx22.mine_and_slash.packets.sync_cap.RequestSyncCapToClient;
import com.robertx22.mine_and_slash.registry.SlashRegistry;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.LvlPointStat;
import com.robertx22.mine_and_slash.saveclasses.player_stat_points.SingleStatPointData;
import com.robertx22.mine_and_slash.uncommon.capability.entity.EntityCap;
import com.robertx22.mine_and_slash.uncommon.capability.player.PlayerStatsPointsCap;
import com.robertx22.mine_and_slash.uncommon.datasaving.Load;
import com.robertx22.mine_and_slash.uncommon.localization.Styles;
import com.robertx22.mine_and_slash.uncommon.localization.Words;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.GuiUtils;
import com.robertx22.mine_and_slash.uncommon.utilityclasses.TooltipUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StatAllocationScreen extends BaseScreen implements INamedScreen, IAlertScreen {

    private static final ResourceLocation TEXTURE = new ResourceLocation(
        Ref.MODID, "textures/gui/stat_point_screen.png");
    static int sizeY = 220;
    static int sizeX = 215;

    public StatAllocationScreen() {
        super(sizeX, sizeY);
    }

    PlayerStatsPointsCap.IPlayerStatPointsData data;
    EntityCap.UnitData unitdata;

    int guiLeft = 0;
    int guiTop = 0;

    @Override
    public ResourceLocation iconLocation() {
        return new ResourceLocation(Ref.MODID, "textures/gui/main_hub/icons/stat_points.png");
    }

    @Override
    public boolean mouseReleased(double x, double y, int ticks) {

        buttons.forEach(b -> {
            if (GuiUtils.isInRectPoints(new Point(b.x, b.y), new Point(b.getWidth(), b.getHeight()),
                new Point((int) x, (int) y)
            )) {
                b.onClick(x, y);
            }
        });

        return super.mouseReleased(x, y, ticks);

    }

    @Override
    public Words screenName() {
        return Words.StatPoints;
    }

    @Override
    protected void init() {
        super.init();

        MMORPG.sendToServer(new RequestSyncCapToClient(PlayerCaps.STAT_POINTS));

        data = Load.statPoints(Minecraft.getInstance().player);
        unitdata = Load.Unit(Minecraft.getInstance().player);

        if (data == null || unitdata == null) {
            this.onClose();
            return;
        }
        //LvlPointStat

        this.guiLeft = (this.width - sizeX) / 2;
        this.guiTop = (this.height - sizeY) / 2;

        int y = 0;

        for (SingleStatPointData single : data.getData()
            .getAllStatDatas()) {
            this.buttons.add(new IncreaseStatButton(unitdata, data, single, guiLeft + sizeX / 2 + 50, guiTop + 40 + y));
            y += button_sizeY + 3;
        }

        addButton(new BackButton(guiLeft, guiTop - BackButton.ySize));
    }

    @Override
    public void render(int x, int y, float ticks) {

        drawGuiBackgroundLayer(ticks, x, y);
        super.render(x, y, ticks);

        String str = "Stat Points Remaining: " + data.getAvailablePoints(Load.Unit(minecraft.player));

        Minecraft.getInstance().fontRenderer.drawStringWithShadow(str,
            guiLeft + sizeX / 2 + 50 - Minecraft.getInstance().fontRenderer
                .getStringWidth(str), guiTop + 15,
            TextFormatting.GREEN.getColor()
        );

        buttons.forEach(b -> b.renderToolTip(x, y));

    }

    protected void drawGuiBackgroundLayer(float partialTicks, int x, int y) {

        minecraft.getTextureManager()
            .bindTexture(TEXTURE);
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.blit(minecraft.mainWindow.getScaledWidth() / 2 - this.sizeX / 2,
            minecraft.mainWindow.getScaledHeight() / 2 - this.sizeY / 2, 0, 0, sizeX, sizeY
        );

    }

    private static final ResourceLocation BUTTON_TEX = new ResourceLocation(Ref.MODID, "textures/gui/button.png");
    static int button_sizeX = 13;
    static int button_sizeY = 13;

    @Override
    public boolean shouldAlert() {
        try {
            return Load.statPoints(Minecraft.getInstance().player)
                .getAvailablePoints(Load.Unit(Minecraft.getInstance().player)) > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    class IncreaseStatButton extends ImageButton {

        FontRenderer font = Minecraft.getInstance().fontRenderer;
        PlayerStatsPointsCap.IPlayerStatPointsData data;
        LvlPointStat stat;
        EntityCap.UnitData unitdata;

        public IncreaseStatButton(EntityCap.UnitData unitdata, PlayerStatsPointsCap.IPlayerStatPointsData data,
                                  SingleStatPointData statData, int xPos, int yPos) {
            super(xPos, yPos, button_sizeX, button_sizeY, 0, 0, button_sizeY, BUTTON_TEX, (button) -> {

                // MMORPG.sendToServer(new SpendStatPointPacket(statData.stat));
                //MMORPG.sendToServer(new RequestSyncCapToClient(CapTypes.STAT_POINTS));

            });

            this.data = data;
            this.stat = statData.stat;
            this.unitdata = unitdata;

        }

        @Override
        public void onPress() {
            super.onPress();

            MMORPG.sendToServer(new SpendStatPointPacket(this.stat));
            MMORPG.sendToServer(new RequestSyncCapToClient(PlayerCaps.STAT_POINTS));

        }

        @Override
        public void renderToolTip(int x, int y) {
            if (isInside(x, y)) {
                Stat stat = SlashRegistry.Stats()
                    .get(this.stat.statguid);

                List<ITextComponent> tooltip = new ArrayList<>();

                tooltip.add(Styles.BLUECOMP()
                    .appendSibling(stat.locName()));

                if (stat instanceof BaseCoreStat) {
                    BaseCoreStat core = (BaseCoreStat) stat;
                    tooltip.addAll(core.getCoreStatTooltip(unitdata, unitdata.getUnit()
                        .getCreateStat(stat)));
                }

                StatAllocationScreen.this.renderTooltip(
                    TooltipUtils.compsToStrings(tooltip), x, y, Minecraft.getInstance().fontRenderer);

            }
        }

        public boolean isInside(int x, int y) {
            return GuiUtils.isInRect(this.x, this.y, button_sizeX, button_sizeY, x, y);
        }

        @Override
        public void renderButton(int x, int y, float f) {
            try {
                super.renderButton(x, y, f);

                TextFormatting format = TextFormatting.YELLOW;

                SingleStatPointData single = data.getStatData(stat);

                Stat stat = single.getStat();

                String str =
                    single.stat.formatting + single.stat.shortName + format + ": " + TextFormatting.GREEN + TextFormatting.GREEN + (int) this.unitdata.getUnit()
                            .getCreateStat(stat)
                            .getAverageValue() + format;
                str += ", Total (" + single.points + format + ")";

                font.drawStringWithShadow(str, this.x - button_sizeX - 5 - font.getStringWidth(str),
                    this.y - button_sizeY / 2 + font.FONT_HEIGHT, format.getColor()
                );
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    static ResourceLocation BACK_BUTTON = new ResourceLocation(
            Ref.MODID, "textures/gui/spell_schools/back_button.png");

    static class BackButton extends ImageButton {
        public static int xSize = 26;
        public static int ySize = 16;

        public BackButton(int xPos, int yPos) {
            super(xPos, yPos, xSize, ySize, 0, 0, ySize + 1, BACK_BUTTON, (button) -> {
                Minecraft.getInstance()
                        .displayGuiScreen(new MainHubScreen());
            });
        }

        @Override
        public void renderButton(int x, int y, float ticks) {
            super.renderButton(x, y, ticks);
        }

    }
}
