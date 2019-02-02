package ru.maksimbulva.dresdenchess.moves

import ru.maksimbulva.dresdenchess.board.Cell
import ru.maksimbulva.dresdenchess.board.Columns
import ru.maksimbulva.dresdenchess.board.Rows

object MoveTableGenerator {

    fun generateTable(directions: Array<Pair<Int, Int>>): Array<IntArray> {
        val result = Array(size = 64) { IntArray(0) }
        val generatedArrays = listOf<IntArray>()

        for (row in Rows.ROW_1..Rows.ROW_8) {
            for (column in Columns.COLUMN_A..Columns.COLUMN_H) {
                val departureCell = Cell.encode(row, column)
                val directionsAtCell = directions.asSequence()
                        .mapNotNull { (deltaRow, deltaColumn) ->
                            Cell.encodeOrNull(row + deltaRow, column + deltaColumn)
                        }
                        .map { destinationCell -> destinationCell - departureCell }
                        .toList()
                        .toIntArray()

                result[departureCell] = generatedArrays.firstOrNull { it.contentEquals(directionsAtCell) }
                        ?: directionsAtCell
            }
        }

        return result
    }
}
