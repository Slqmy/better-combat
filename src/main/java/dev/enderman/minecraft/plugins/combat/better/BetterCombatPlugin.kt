package dev.enderman.minecraft.plugins.combat.better

import dev.enderman.minecraft.plugins.combat.better.listener.EntityAttackListener
import dev.enderman.minecraft.plugins.combat.better.listener.EntityDeathListener
import dev.enderman.minecraft.plugins.combat.better.manager.CombatManager
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BetterCombatPlugin :
    JavaPlugin() {
    var combatManager: CombatManager? =
        null
        private set

    override fun onEnable() {
        combatManager =
            CombatManager()

        val pluginManager =
            Bukkit.getPluginManager()

        pluginManager.registerEvents(
            EntityAttackListener(
                this
            ),
            this
        )
        pluginManager.registerEvents(
            EntityDeathListener(
                this
            ),
            this
        )
    }
}
