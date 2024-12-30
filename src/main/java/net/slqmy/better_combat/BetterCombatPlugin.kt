package net.slqmy.better_combat

import net.slqmy.better_combat.listener.EntityAttackListener
import net.slqmy.better_combat.listener.EntityDeathListener
import net.slqmy.better_combat.manager.CombatManager
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
