import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MainMaze {
    public static void main(String[] args) {
        String fileName;
        Maze m = new Maze();
        char[][] someArray;
        boolean solved = false;

        Scanner scan = new Scanner(System.in);
        System.out.println("Inform file name: ");
        fileName = scan.next();
        someArray = m.loadMaze("src/" + fileName);
        m.printMaze();
        System.out.println();

        if (m.maze(someArray)) {
            System.out.println();
            System.out.println("Eureka! The maze has a solution!");
            System.out.println("Here is your solved maze:");
            System.out.println("The path is marked with a ° symbol");
            m.printMaze();
            solved = true;
        } else {
            System.out.println();
            System.out.println("Sorry! The maze is unsolvable");
        }

        try {
            File f = new File("src/" + fileName + "_answer.txt");
            FileWriter fr = new FileWriter(f);
            PrintWriter out = new PrintWriter(fr);
            if (solved) {
                out.println("The maze can be solved.");
            } else {
                out.println("Maze can't be solved.");
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Error! Could not write file!");
        }
    }
}