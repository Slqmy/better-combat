package net.slqmy.better_combat.manager

import net.slqmy.better_combat.type.CombatInstance
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
