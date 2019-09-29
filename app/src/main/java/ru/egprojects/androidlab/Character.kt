package ru.egprojects.androidlab

enum class Character(var type: Type, private var status: Status = Status.ALIVE) {
    MERLIN(Type.HERO),
    ARTHUR(Type.HERO),
    UTER(Type.KING),
    MORGAN(Type.HERO),
    MORGAUSE(Type.VILLAIN);

    fun becomeAHero() {
        type = Type.HERO
    }

    fun becomeAVillain() {
        type = Type.VILLAIN
    }

    fun becomeAKing() {
        type = Type.KING
    }

    fun isAlive() = status == Status.ALIVE

    fun die() {
        status = Status.DEAD
    }
}

enum class Status {
    ALIVE, DEAD
}

enum class Type {
    HERO, VILLAIN, KING
}