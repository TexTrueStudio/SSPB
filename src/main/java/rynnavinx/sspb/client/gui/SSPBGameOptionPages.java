package rynnavinx.sspb.client.gui;

import com.google.common.collect.ImmutableList;

import me.jellysquid.mods.sodium.client.gui.options.OptionFlag;
import me.jellysquid.mods.sodium.client.gui.options.OptionGroup;
import me.jellysquid.mods.sodium.client.gui.options.OptionImpl;
import me.jellysquid.mods.sodium.client.gui.options.OptionPage;
import me.jellysquid.mods.sodium.client.gui.options.control.ControlValueFormatter;
import me.jellysquid.mods.sodium.client.gui.options.control.SliderControl;
import me.jellysquid.mods.sodium.client.gui.options.control.TickBoxControl;

//import net.minecraft.text.Text;

import net.minecraft.text.TranslatableText;
import rynnavinx.sspb.client.gui.options.storage.SSPBOptionsStorage;

import java.util.ArrayList;
import java.util.List;


public class SSPBGameOptionPages {

    private static final SSPBOptionsStorage sspbOpts = new SSPBOptionsStorage();


    public static OptionPage sspb() {
        List<OptionGroup> groups = new ArrayList<>();
        groups.add(OptionGroup.createBuilder()
                .add(OptionImpl.createBuilder(int.class, sspbOpts)
                        .setName(new TranslatableText("sspb.options.shadowyness.name").getString())
                        .setTooltip(new TranslatableText("sspb.options.shadowyness.tooltip").getString())
                        .setControl(option -> new SliderControl(option, 0, 100, 1, ControlValueFormatter.percentage()))
                        .setBinding(SSPBGameOptions::updateShadowyness, opts -> opts.shadowynessPercent)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build())
                .add(OptionImpl.createBuilder(boolean.class, sspbOpts)
                        .setName(new TranslatableText("sspb.options.onlyaffectpathblocks.name").getString())
                        .setTooltip(new TranslatableText("sspb.options.onlyaffectpathblocks.tooltip").getString())
                        .setControl(TickBoxControl::new)
                        .setBinding((opts, value) -> opts.onlyAffectPathBlocks = value, opts -> opts.onlyAffectPathBlocks)
                        .setFlags(OptionFlag.REQUIRES_RENDERER_RELOAD)
                        .build())
                .build());

        return new OptionPage(new TranslatableText("sspb.pages.sspb_page.name").getString(), ImmutableList.copyOf(groups));
    }
}
