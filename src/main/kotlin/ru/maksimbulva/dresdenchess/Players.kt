package ru.maksimbulva.dresdenchess

enum class Players {
    WHITE,
    BLACK
}

fun Players.other() = if (this == Players.WHITE) {
    Players.BLACK
} else {
    Players.WHITE
}
