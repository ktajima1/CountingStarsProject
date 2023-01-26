import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
/*
    This program is for the Counting Stars problem on Kattis for CS185A
    This program scans a grid of arbitrary size and counts the number of stars in the grid
    '#' indicates empty space, '-' indicates the light of a star, 'O' indicates a discovered star (a visited grid space)
    Note: Any space with '-' is converted to 'O' to mark as visited
    Note: Any other characters (ie. +,=,$,etc.) will be treated as empty space
    - Keigo Tajima (1/26/2023)
 */

import java.util.ArrayList;

public class Main {
    static ArrayList<Character> visited; //may not be needed

    public static void main(String[] args) {
        int c = countStars();
        System.out.print("\n"+ c);
    }

    //Counts the number of stars in a grid
    static int countStars() {
        int countedStars = 0;
        char[] sample = readRow();              //get the next row

        for(int n=0; n<sample.length;n++) {     //Looks for a star in an array of characters and traces the whole star if star character is found
            if(sample[n]=='-') {                //If space is a star, count star and trace remaining star spaces
                countedStars++;
                traceStar(sample,n);
            }
        }
        for (char ch:sample) {
            System.out.print(ch);
        }
        return countedStars;
    }

    //Detects and traces a star recursively, does not increment star count
    static void traceStar(char[] sample, int n) {
        sample[n]='O';                                      //Mark space as visited

        /* Check surrounding spaces for connected star symbols*/
        //Checking by row
        if( n-1>0 && sample[n-1]=='-') {                    //If the previous space exists (not index out of bounds) and is '-', then trace it
            traceStar(sample,n-1);
        }
        if( n+1 < sample.length && sample[n+1]=='-') {      //If the next space exists (not index out of bounds) and is '-', then trace it
            traceStar(sample,n+1);
        }
        //Checking by column

    }

    /**Reads the next line and returns a charArray of the line*/
    static char[] readRow() {
        String sample = "#################---";
        //Add each character in the string into an arraylist
        ArrayList<Character> row = new ArrayList<>();
        for(int i=0; i<sample.length();i++) {
            row.add(sample.charAt(i));
        }
        //Convert arraylist to charArray and return charArray
        return toCharArray(row);
    }

//    static char[] readRow() {
//        try {
//            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
////            String row = br.readLine();
////            System.out.print(row);
//            br.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

        //Converts a Character ArrayList to a char[] array
    static char[] toCharArray(ArrayList<Character> list) {
        char[] row = new char[list.size()];
        for (int i=0; i<list.size(); i++) {
            row[i] = list.get(i);
        }
        return row;
    }

    static ArrayList<Character> toArrayList() {

        return new ArrayList<Character>();
    }
}