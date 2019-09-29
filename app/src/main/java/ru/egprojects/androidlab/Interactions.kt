package ru.egprojects.androidlab

interface Interaction {
    fun interact(character1: Character, character2: Character)
}

class Killing : Interaction {
    override fun interact(character1: Character, character2: Character) {
        character2.die()
    }
}

class GainingOver : Interaction {
    override fun interact(character1: Character, character2: Character) {
        character2.type = character1.type
    }
}