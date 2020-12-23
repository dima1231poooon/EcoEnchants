package com.willfp.eco.util.integrations.anticheat.plugins;

import com.willfp.eco.util.integrations.anticheat.AnticheatWrapper;
import me.konsolas.aac.api.AACAPI;
import me.konsolas.aac.api.AACExemption;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class AnticheatAAC implements AnticheatWrapper, Listener {
    private final AACExemption ecoEnchantsExemption = new AACExemption("EcoEnchants");
    private final AACAPI api = Bukkit.getServicesManager().load(AACAPI.class);

    @Override
    public String getPluginName() {
        return "AAC";
    }

    @Override
    public void exempt(@NotNull Player player) {
        api.addExemption(player, ecoEnchantsExemption);
    }

    @Override
    public void unexempt(@NotNull Player player) {
        api.removeExemption(player, ecoEnchantsExemption);
    }
}
