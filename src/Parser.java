/*
Gayasshi Amarathunga
w1956161_20221460
*/

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.FileReader;
public class Parser {

    private int rows;
    private int columns;
    private int start_A;
    private int start_B;
    private int end_A;
    private int end_B;
    private String[][] block;

    public int getStart_A() {
        return start_A;
    }

    public int getStart_B() {
        return start_B;
    }

    public int getEnd_A() {
        return end_A;
    }

    public int getEnd_B() {
        return end_B;
    }

    public String[][] getblock(){
        return block;
    }

    // Method to retrieve the number of rows and columns in a data structure and store the data in a 2D array. The method also includes adding data to the array.
    public void fileReader(){
        try {
            rows = Files.readAllLines(Paths.get("/Users/gayashiamarathunga/Desktop/AlgorithemsCW_20221460_W1956161/AlgorithemsCW/src/Course_Work/examples2/puzzle_80.txt")).size();
            columns = Files.readAllLines(Paths.get("/Users/gayashiamarathunga/Desktop/AlgorithemsCW_20221460_W1956161/AlgorithemsCW/src/Course_Work/examples2/puzzle_80.txt")).get(0).length();
        }
        catch (IOException ignored){
            System.out.println("File not Found");
        }

        block = new String[rows][columns];
        try {

            File file = new File("/Users/gayashiamarathunga/Desktop/AlgorithemsCW_20221460_W1956161/AlgorithemsCW/src/Course_Work/examples2/puzzle_80.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            int x =-1;
            while ((line = bufferedReader.readLine()) != null) {
                x+=1;
                // // Process each line of the input individually.
                for (int y = 0; y < columns; y++){
                    block[x][y] = String.valueOf(line.charAt(y));

                    if (String.valueOf(line.charAt(y)).equals("S")){      //checking for Starting point (S)
                        start_A = y;
                        start_B = x;
                    }
                    if (String.valueOf(line.charAt(y)).equals("F")){      //checking for finishing point (F)
                        end_A = y;
                        end_B = x;
                    }
                }

            }
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        Parser parser = new Parser();
        parser.fileReader();

        //Time calculator for the puzzle solving
        Puzzle IceSlider = new Puzzle();
        long startTime = System.currentTimeMillis();
        IceSlider.solve(parser.getblock(),parser.getStart_A(), parser.getStart_B(), parser.getEnd_A(), parser.getEnd_B());
        long current_time = System.currentTimeMillis();
        double timeTaken = (current_time - startTime)/1000.0;
        System.out.println("    ");
        System.out.println("Time Taken :- " + timeTaken + "seconds.");
    }
}