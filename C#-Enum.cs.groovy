/*
 * Available context bindings:
 *   COLUMNS     List<DataColumn>
 *   ROWS        Iterable<DataRow>
 *   OUT         { append() }
 *   FORMATTER   { format(row, col); formatValue(Object, col); getTypeName(Object, col); isStringLiteral(Object, col); }
 *   TRANSPOSED  Boolean
 * plus ALL_COLUMNS, TABLE, DIALECT
 *
 * where:
 *   DataRow     { rowNumber(); first(); last(); data(): List<Object>; value(column): Object }
 *   DataColumn  { columnNumber(), name() }
 */

NEWLINE = System.getProperty("line.separator")

def printRow = { idx, columns, valueToString ->
  def id = valueToString(columns[0])
  def description = valueToString(columns[1])
  def name = valueToString(columns[2])

  OUT.append("/// <summary>$NEWLINE")
  OUT.append("/// $description$NEWLINE")
  OUT.append("/// </summary>$NEWLINE")
  OUT.append("\t$name = $id,")
  OUT.append(NEWLINE)
}

OUT.append("public enum $TABLE.name$NEWLINE")
OUT.append("{$NEWLINE")
ROWS.eachWithIndex { row, idx -> printRow(idx, COLUMNS, { FORMATTER.format(row, it) }) }
OUT.append("}")