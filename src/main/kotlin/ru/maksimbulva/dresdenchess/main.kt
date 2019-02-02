package ru.maksimbulva.dresdenchess

import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Cells
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.board.Rows
import ru.maksimbulva.dresdenchess.moves.MovesGenerator
import ru.maksimbulva.dresdenchess.pieces.Knight
import ru.maksimbulva.dresdenchess.pieces.WhitePawn
import ru.maksimbulva.dresdenchess.position.Position
import java.time.Duration

fun perft(): Long {
    val position = Position(Cells.E1, Cells.E8)
    position.board.addPiece(Players.WHITE, Knight, Cells.B1)
    position.board.addPiece(Players.WHITE, Knight, Cells.G1)
    for (column in Columns.COLUMN_A..Columns.COLUMN_H) {
        position.board.addPiece(Players.WHITE, WhitePawn, Cell.encode(Rows.ROW_2, column))
    }
    return MovesGenerator.countPossibleMoves(position, 3)
}

fun main(args: Array<String>) {
    val durations = mutableListOf<Duration>()
    for (i in 0 until 10) {
        val startTimeMillis = System.currentTimeMillis()
        val movesGenerated = perft()
        val endTimMillis = System.currentTimeMillis()
        val duration = Duration.ofMillis(endTimMillis - startTimeMillis)
        durations.add(duration)
        val movesPerSecond = ((movesGenerated.toDouble() / duration.toMillis()) * 1000).toInt()
        println("Run $i: $movesPerSecond moves/sec, $movesGenerated moves in ${duration.toMillis()} milliseconds")
    }

    println("Done")
    durations.sort()
    val medianDuration = durations[durations.size / 2]
    println("Median duration ${medianDuration.toMillis()} milliseconds")
}
