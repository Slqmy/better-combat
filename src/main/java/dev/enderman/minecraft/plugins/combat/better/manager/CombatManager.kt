package dev.enderman.minecraft.plugins.combat.better.manager

import dev.enderman.minecraft.plugins.combat.better.type.CombatInstance
import org.bukkit.entity.Entity

class CombatManager {
    private val combatInstances: MutableList<CombatInstance> =
        ArrayList()

    fun getCombatInstance(
        attackedEntity: Entity
    ): CombatInstance? {
        for (combatInstance in combatInstances) {
            if (combatInstance.attackedEntity == attackedEntity) {
                return combatInstance
            }
        }

        return null
    }

    fun addCombatInstance(
        combatInstance: CombatInstance
    ) {
        combatInstances.add(
            combatInstance
        )
    }

    fun removeCombatInstance(
        combatInstance: CombatInstance
    ) {
        combatInstances.remove(
            combatInstance
        )
    }
}
