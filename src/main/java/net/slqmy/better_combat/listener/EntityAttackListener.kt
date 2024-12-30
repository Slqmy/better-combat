package net.slqmy.better_combat.listener

import net.slqmy.better_combat.BetterCombatPlugin
import net.slqmy.better_combat.type.CombatInstance
import org.bukkit.Bukkit
import org.bukkit.entity.LivingEntity
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import kotlin.math.min

class EntityAttackListener(
    private val plugin: BetterCombatPlugin
) : Listener {
    @EventHandler
    fun onEntityAttack(
        event: EntityDamageByEntityEvent
    ) {
        val attacker =
            event.damager
        val attacked =
            event.entity

        var damage =
            event.finalDamage

        Bukkit.getLogger()
            .info(
                "Original damage: $damage"
            )

        if (attacked is LivingEntity) {
            val remainingHealth =
                attacked.health

            damage =
                min(
                    damage,
                    remainingHealth
                ) // Can't deal more damage than there is health remaining.

            Bukkit.getLogger()
                .info(
                    "New damage: $damage"
                )
        }

        Bukkit.getLogger()
            .info(
                "$attacker attacked $attacked & dealt $damage damage"
            )

        val combatManager =
            plugin.combatManager

        var combatInstance =
            combatManager!!.getCombatInstance(
                attacked
            )

        if (combatInstance == null) {
            combatInstance =
                CombatInstance(
                    attacked
                )

            combatManager.addCombatInstance(
                combatInstance
            )
        }

        combatInstance.addEntityDamageContribution(
            attacker,
            damage
        )
    }
}
