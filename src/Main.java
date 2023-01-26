import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {
    static ArrayList<Character> visited;

    public static void main(String[] args) {
        int c = countStars();
        System.out.print("\n"+ c);
    }

    static int countStars() {
        int countedStars = 0;
        char[] sample = readRow(); //get the next row
        for(int i=0; i<sample.length;i++) {
            if(sample[i]=='-') {
                countedStars++;
                traceStar(sample,i);
            }
        }
//        for (char ch:sample) {
//            System.out.print(ch);
//        }
        return countedStars;
    }
    static void traceStar(char[] sample, int i) {
        sample[i]='O';
        if( i+1 < sample.length && sample[i+1]=='-') {      //If the next character exists and is '-', then trace it
            traceStar(sample,i+1);
        }
    }

    /**Reads the next line and returns a charArray of line*/
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