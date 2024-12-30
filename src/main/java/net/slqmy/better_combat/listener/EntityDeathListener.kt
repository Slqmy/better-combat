package net.slqmy.better_combat.listener

import net.slqmy.better_combat.BetterCombatPlugin
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDeathEvent

class EntityDeathListener(
    private val plugin: BetterCombatPlugin
) : Listener {
    @EventHandler
    fun onEntityDeath(
        event: EntityDeathEvent
    ) {
        val combatManager =
            plugin.combatManager

        val deadEntity =
            event.entity

        val combatInstance =
            combatManager!!.getCombatInstance(
                deadEntity
            ) ?: return

        val highestDamageContributor =
            combatInstance.highestDamageContributor

        Bukkit.getLogger()
            .info(
                "$highestDamageContributor contributed most to the death of $deadEntity"
            )
        Bukkit.getLogger()
            .info(
                "combatInstance.damageContributorDamageMap = " + combatInstance.damageContributorDamageMap
            )
        Bukkit.getLogger()
            .info(
                "combatInstance.damageContributorDamageMap.entrySet = " + combatInstance.damageContributorDamageMap.entries
            )

        val entityDamageEvent =
            deadEntity.lastDamageCause

        if (entityDamageEvent is EntityDamageByEntityEvent) {
            val damager =
                entityDamageEvent.damager

            if (damager is Player) {
                deadEntity.killer =
                    damager
            }
        }

        combatManager.removeCombatInstance(
            combatInstance
        )
    }
}
