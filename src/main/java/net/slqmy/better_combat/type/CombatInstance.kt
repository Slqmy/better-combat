package net.slqmy.better_combat.type

import org.bukkit.entity.Entity

class CombatInstance(
    val attackedEntity: Entity
) {
    private val damageContributorDamageMap: MutableMap<Entity, Double> =
        HashMap()

    fun getDamageContributorDamageMap(): Map<Entity, Double> {
        return damageContributorDamageMap
    }

    fun getEntityDamageContribution(
        damager: Entity
    ): Double? {
        return damageContributorDamageMap[damager]
    }

    fun addEntityDamageContribution(
        damagerEntity: Entity,
        damageAmount: Double
    ) {
        val currentDamage =
            damageContributorDamageMap[damagerEntity]

        damageContributorDamageMap[damagerEntity] =
            if (currentDamage == null) damageAmount else currentDamage + damageAmount
    }

    val highestDamageContributor: Entity?
        get() {
            var highestDamageContributor: Entity? =
                null
            var highestContributedDamage =
                0.0

            for ((key, contributedDamage) in damageContributorDamageMap) {
                if (contributedDamage > highestContributedDamage) {
                    highestDamageContributor =
                        key
                    highestContributedDamage =
                        contributedDamage
                }
            }

            return highestDamageContributor
        }

    override fun toString(): String {
        return "CombatInstance{attackedEntity=$attackedEntity}"
    }
}
