import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
    This program is for the Counting Stars problem on Kattis for CS185A
    This program reads lines of input and creates a text representation of the night sky.
    The first line of input sets the size of a char[][] array using two integers. First integer 'n' sets the length (row #)
    of the array and second integer sets the height (column #) of the array. The program creates a matrix of size
    char[n][m] and populates the matrix with the remaining string inputs. The remaining string inputs are of length 'm'
    and there are 'n' number of lines that are read and recorded into the matrix.

    Key:
    '#' indicates empty space, '-' indicates the light of a star, 'O' indicates a discovered star (a visited grid space)
    Note: Any space with '-' is converted to 'O' to mark as visited when discovered
    Note: Any invalid characters (ie. +,=,$,etc.) will be treated like '#', empty space
    - Keigo Tajima (1/26/2023)
 */

public class Main {

    public static void main(String[] args) {
        int caseNum=1;
        try {
            /* Create a char[][] to the specified size (decided by first line of input) and fill the grid with the given input
             * Strings. The first line of the input will be two numbers (n and m) that sets the height (rowSize) and
             * length (columnSize) of the grid. Numbers will be separated by white space, so the numbers will have to
             * be parsed and respectively stored into rowSize and columnSize variables which will be used to make the
             * char[][] array. This method assumes that the first line of the input will always include size
             * specifications for rowSize and columnSize.
             *
             * After creating an empty char[rowSize][columnSize] matrix, the method fills the grid with the
             * given String inputs by calling br.readLine() in a while loop until the readLine() methods reaches the end of the
             * input (empty line). */
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String currentLine;
            while((currentLine = br.readLine())!=null) {
                //If the input is empty, then terminate the program
                if(currentLine.equals("")) return;
                /* Obtain the rowSize and columnSize of the matrix using the split method on the first line of input */
                String[] sizeParams = currentLine.split(" ");
                //Set rowSize to equal the first integer of the input
                int rowSize = Integer.parseInt(sizeParams[0]);
                //Set columnSize to equal the second integer of the input
                int columnSize = Integer.parseInt(sizeParams[1]);
                /* Create a char matrix with rowSize * columnSize specifications */
                char[][] map = new char[rowSize][columnSize];

                /* Record the remaining string inputs (that describe the map of space) into the char matrix */
                String currentRow;
                for (int i = 0; i < rowSize; i++) {
                    currentRow = br.readLine();
                    map[i] = currentRow.toCharArray();
                }

                /* Read the map matrix and count the number of stars in the map and print it*/
                System.out.println("Case " + caseNum + ": " + countStars(map));

                /* Increment the case number */
                caseNum++;
            }

        } catch(IOException e) {
            System.out.println("Problem reading map");
            e.printStackTrace();
        }

    }

    /* Given a char[n][m] array initialized by initializeGrid(), count the number of stars in the grid.
     * When a star a discovered (an element in the matrix is a '-' symbol, then increment the star count and trace out
     * the entire star in the matrix by marking adjacent star spaces as 'O'. Any 'O' spaces will be skipped over
     */
    static int countStars(char[][] map) {
        int countedStars = 0;
        for (int row=0; row<map.length; row++) {
            for (int column=0;column<map[row].length;column++) {
                if(map[row][column]=='-') {
                    countedStars++;
                    recurseTraceStar(map, row, column);
                }
            }
        }
        return countedStars;
    }


    /* Detects and traces a star recursively, does not increment star count */
    static void recurseTraceStar(char[][] map, int row, int column) {

        //If the space above current position exists (not index out of bounds) and is '-', then mark it as visited and
        //check adjacent spaces for anymore star spaces
        if(row<0 || column<0 || row>=map.length || column>=map[0].length) { return; }
        if(map[row][column]=='#') { return; }
        if(map[row][column]=='-') {
            map[row][column]='O';                       //Mark space as visited
            recurseTraceStar(map, row-1, column);
            recurseTraceStar(map, row+1, column);
            recurseTraceStar(map, row, column-1);
            recurseTraceStar(map, row, column+1);
        }

    }
}