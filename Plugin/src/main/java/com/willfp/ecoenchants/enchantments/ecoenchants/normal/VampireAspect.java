package com.willfp.ecoenchants.enchantments.ecoenchants.normal;

import com.willfp.ecoenchants.enchantments.EcoEnchant;
import com.willfp.ecoenchants.enchantments.EcoEnchantBuilder;
import com.willfp.ecoenchants.enchantments.EcoEnchants;
import com.willfp.ecoenchants.nms.Cooldown;
import com.willfp.ecoenchants.nms.Target;
import com.willfp.ecoenchants.util.AntiGrief;
import com.willfp.ecoenchants.util.HasEnchant;
import com.willfp.ecoenchants.util.Rand;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

@SuppressWarnings("deprecation")
public class VampireAspect extends EcoEnchant {
    public VampireAspect() {
        super(
                new EcoEnchantBuilder("vampire_aspect", EnchantmentType.NORMAL, Target.Applicable.SWORD, 4.0)
        );
    }

    // START OF LISTENERS

    @EventHandler
    public void vampireAspectHit(EntityDamageByEntityEvent event) {
        if (!(event.getDamager() instanceof Player))
            return;

        if (!(event.getEntity() instanceof LivingEntity))
            return;

        Player player = (Player) event.getDamager();

        LivingEntity victim = (LivingEntity) event.getEntity();

        if(event.getEntity() instanceof Player) {
            if(!AntiGrief.canInjurePlayer(player, (Player) event.getEntity())) return;
        }

        if (!HasEnchant.playerHeld(player, this)) return;

        if (Cooldown.getCooldown(player) != 1.0f && !this.getConfig().getBool(EcoEnchants.CONFIG_LOCATION + "allow-not-fully-charged"))
            return;

        int level = HasEnchant.getPlayerLevel(player, this);
        if (Rand.randFloat(0, 1) > level * 0.01 * this.getConfig().getDouble(EcoEnchants.CONFIG_LOCATION + "chance-per-level"))
            return;

        victim.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, level * 10 + 20, level));
    }
}
