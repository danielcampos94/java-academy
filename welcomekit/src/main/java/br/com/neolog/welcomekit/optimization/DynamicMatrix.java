package br.com.neolog.welcomekit.optimization;

import java.util.HashMap;
import java.util.Map;

public class DynamicMatrix<T>
{
    private final Map<Integer,Map<Integer,T>> elements = new HashMap<>();

    public void set(
        final int row,
        final int column,
        final T element )
    {
        final Map<Integer,T> columns = getColumns( row );
        final Integer key = Integer.valueOf( column );
        if( element != null ) {
            columns.put( key, element );
        } else {
            columns.remove( key );
        }
    }

    public T get(
        final int row,
        final int column )
    {
        final Map<Integer,T> columns = getColumns( row );
        final Integer key = Integer.valueOf( column );
        final T element = columns.get( key );
        return element;
    }

    private Map<Integer,T> getColumns(
        final int row )
    {
        final int key = Integer.valueOf( row );
        Map<Integer,T> columns = elements.get( key );
        if( columns == null ) {
            columns = new HashMap<>();
            elements.put( key, columns );
        }
        return columns;
    }

    public static DynamicMatrix<Long> create(
        final int rows,
        final int columns )
    {
        final DynamicMatrix<Long> matrix = new DynamicMatrix<>();
        long number = 1;
        for( int i = 0; i < rows; i++ ) {
            for( int j = 0; j < columns; j++ ) {
                final Long element = number++;
                matrix.set( i, j, element );
            }
        }
        return matrix;
    }

    public static void printMatrix(
        final int rows,
        final int columns,
        final DynamicMatrix<Long> matrix )
    {
        for( int i = 0; i < rows; i++ ) {
            for( int j = 0; j < columns; j++ ) {
                final Long element = matrix.get( i, j );
                if( j == columns - 1 ) {
                    System.out.println( element + " " );
                } else {
                    System.out.print( element + " " );
                }
            }
        }
    }

}
