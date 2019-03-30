package ru.maksimbulva.dresdenchess

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PlayersTest {

    @Test
    fun otherPlayerTest() {
        assertEquals(Players.WHITE, Players.BLACK.other())
        assertEquals(Players.BLACK, Players.WHITE.other())
    }
}
