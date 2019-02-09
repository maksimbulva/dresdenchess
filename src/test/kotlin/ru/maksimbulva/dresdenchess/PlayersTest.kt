package ru.maksimbulva.dresdenchess

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PlayersTest {

    @Test
    fun otherPlayerTest() {
        assertEquals(Players.WHITE, otherPlayer(Players.BLACK))
        assertEquals(Players.BLACK, otherPlayer(Players.WHITE))
    }
}
