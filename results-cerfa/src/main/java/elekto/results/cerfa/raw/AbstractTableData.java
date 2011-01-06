package elekto.results.cerfa.raw;

import java.util.ArrayList;
import java.util.List;

abstract class AbstractTableData {

    AbstractTableData(
            final int rowCount,
            final int columnCount)
    {
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        this.table = makeTable(rowCount, columnCount);
    }


    protected final int getColumnCount()
    {
        return this.columnCount;
    }


    protected final int getRowCount()
    {
        return this.rowCount;
    }


    protected final String getValue(
            final int rowIndex,
            final int columnIndex)
    {
        final String value = this.isInBound(rowIndex, columnIndex)
                ? this.table.get(rowIndex).get(columnIndex)
                : "";

        return value;
    }


    protected final void setValue(
            final int rowIndex,
            final int columnIndex,
            final String value)
    {
        if (this.isInBound(rowIndex, columnIndex)) {
            this.table.get(rowIndex).set(columnIndex, value);
        }
    }


    private final boolean isInBound(
            final int rowIndex,
            final int columnIndex)
    {
        final boolean inbound = ((rowIndex >= 0) && (rowIndex < this.rowCount) && (columnIndex >= 0) && (columnIndex < this.columnCount));

        return inbound;
    }


    private static List<List<String>> makeTable(
            final int rowCount,
            final int columnCount)
    {
        final List<List<String>> table = new ArrayList<List<String>>();
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            final List<String> row = new ArrayList<String>();

            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                row.add("");
            }

            table.add(row);
        }

        return table;
    }

    private final int rowCount;

    private final int columnCount;

    private final List<List<String>> table;

}
