package ru.maksimbulva.dresdenchess

import ru.maksimbulva.dresdenchess.board.Cells
import ru.maksimbulva.dresdenchess.moves.MovesGenerator
import ru.maksimbulva.dresdenchess.position.PlayMoveUseCase
import ru.maksimbulva.dresdenchess.position.Position
import java.time.Duration

fun calcMovesCount(positionMoveUseCase: PlayMoveUseCase, depthPly: Int): Long {
    val position = positionMoveUseCase.position
    val moves = MovesGenerator.generateSemiLegalMoves(position)
    var movesCounter = moves.size.toLong()
    if (depthPly > 0) {
        for (move in moves) {
            positionMoveUseCase.playMove(move)
            movesCounter += calcMovesCount(positionMoveUseCase, depthPly - 1)
            positionMoveUseCase.undoMove(move)
        }
    }
    return movesCounter
}

fun perft(): Long {
    val position = Position(Cells.A1, Cells.E8)
    val positionMoveUseCase = PlayMoveUseCase(position)
    return calcMovesCount(positionMoveUseCase, 10)
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
