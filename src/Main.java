import sun.awt.windows.WBufferStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
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

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String currentLine;
            while((currentLine = br.readLine())!=null) {
                String[] sizeParams = currentLine.split(" ");
                //Set rowSize to equal the first integer of the input
                int rowSize = Integer.parseInt(sizeParams[0]);
                //Set columnSize to equal the second integer of the input
                int columnSize = Integer.parseInt(sizeParams[1]);
            }

        } catch(IOException e) {
            System.out.println("Could not read map size");
            e.printStackTrace();
        }

        char[][] map = initializeGrid();

        //Used for printing contents of char[][] array
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
        System.out.println(countStars(map));
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    /* Creates and initializes a char[][] to the specified size and fills the grid with the given input Strings.
     * The first line of the input will be two numbers (n and m) that sets the length and height of the grid. The
     * numbers will be separated by white space, so the numbers will have to be parsed and respectively stored into n
     * and m variables which will be used to make the char[][] array. This method assumes that the first line of the
     * input will always include size specifications for n and m. Otherwise, n and m will always be set to 0.
     *
     * After creating an empty char[][] array of length n and height m, the method fills the grid with the
     * given String inputs by calling br.readLine() in a while loop until the readLine() methods reaches the end of the
     * input (empty line).
     */
    static char[][] initializeGrid() {
        // If the first line (which has the size of n and m) is not null AND contains two integers, then create an
        // array of length n and height m. By default, n and m will be set to 0.
        int n = 0;
        int m = 0;
        try {
            /* ----------------------------------------------------------------
             * First, create an empty char[][] array of length n and height m:
             * ---------------------------------------------------------------- */
            //Create a BufferedReader to read a line of input
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String sizeSetting;
            if ((sizeSetting = br.readLine()) != null) {
                String[] sizeParams = sizeSetting.split(" ");
                //Set n to equal the first integer of the input
                n = Integer.parseInt(sizeParams[0]);
                //Set m to equal the second integer of the input
                m = Integer.parseInt(sizeParams[1]);
            }
            //Create an empty map of length n and height m
            char[][] map = new char[n][m];
            /* ----------------------------------------------------------------
             * Second, populate/fill the char[][] array with the input Strings. If the next input string is "###--###",
             * then the method will convert the input to a char array and assign it current row of the map.
             *
             * Note: 'n' refers to the number of rows that was set in the first line of input.
             * Using the toCharArray() method converts the String input to a charArray which
             * can be used to populate a row of the char[][] array. The for-loop will run until
             * all the rows of the Map array is populated
             * ---------------------------------------------------------------- */
            String currentRow;
            for (int i = 0; i < n; i++) {
                currentRow = br.readLine();
                map[i] = currentRow.toCharArray();
            }
            //Close the BufferedReader
            br.close();
            //Create and return the filled char[][] array
            return map;
        } catch (IOException e) {
            System.out.println("Error creating grid");
            e.printStackTrace();
        }
        //Failed to create a valid map, throw null
        return null;
    }

    /* Given a char[n][m] array initialized by initializeGrid(), count the number of stars in the grid.
     *
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


    //Detects and traces a star recursively, does not increment star count
    static void recurseTraceStar(char[][] map, int row, int column) {

        //If the space above current position exists (not index out of bounds) and is '-', then trace it
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
//
//    /**Reads the next line and returns a charArray of the line*/
//    static char[] readGrid() {
//
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//            String currentRow;
//            while((currentRow = br.readLine())!=null) {
//
//            }
//            br.close();
//        } catch (IOException e) {
//            System.out.println("Error reading grid");
//            e.printStackTrace();
//        }
//
//        //Add each character in the string into an arraylist
//        ArrayList<Character> row = new ArrayList<>();
//        for(int i=0; i<sample.length();i++) {
//            row.add(sample.charAt(i));
//        }
//        //Convert arraylist to charArray and return charArray
//        return toCharArray(row);
//    }

