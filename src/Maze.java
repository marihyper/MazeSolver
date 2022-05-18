import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Maze {
    private char[][] finalArray;

    public char[][] loadMaze(String fileName) {
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String L = br.readLine();
            String C = br.readLine();
            int r = Integer.parseInt(L);
            int c = Integer.parseInt(C);
            finalArray = new char[r][c];
            String row = br.readLine();

            for (r = 0; r < finalArray.length; r++) {
                for (c = 0; c < finalArray[r].length; c++) {
                    finalArray[r][c] = row.charAt(c);
                }
                row = br.readLine();
            }
            br.close();
            System.out.println();
            System.out.println("Congrats! Your maze is successfully loaded!");

        } catch (FileNotFoundException e) {
            System.out.println("File not found!: " + "\n" + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArrayIndexOutOfBoundsException oe) {
            System.out.println("Array invalid index: " + oe);
        } catch (StringIndexOutOfBoundsException se) {
            System.out.println("String invalid index: " + se);
        }
        return finalArray;
    }

    public void printMaze() {
        for (int i = 0; i < finalArray.length; i++) {
            System.out.println();
            for (int j = 0; j < finalArray[i].length; j++) {
                System.out.print(finalArray[i][j]);
            }
        }

    }

    public boolean isValidSpot(char[][] finalArray, int r, int c) {
        //check if I'm on a valid point
        if (r >= 0 && r < finalArray.length && c >= 0 && c < finalArray[r].length) {
            return finalArray[r][c] == ' ' || finalArray[r][c] == 'D';
        }
        return false;
    }

    public boolean maze(char[][] finalArray) throws IllegalArgumentException {
        if (finalArray == null) throw new IllegalArgumentException();
        return solveMaze(finalArray, 0, 0);
    }

    private boolean solveMaze(char[][] finalArray, int r, int c) {
        if (isValidSpot(finalArray, r, c)) {
            //base case: did I reach 'D' (finish marker)?
            if (finalArray[r][c] == 'D')
                return true;
            //path marker (breadcrumb)
            finalArray[r][c] = '*';
            //check up
            boolean returnValue = solveMaze(finalArray, r - 1, c);
            //check down
            if (!returnValue) returnValue = solveMaze(finalArray, r + 1, c);
            //check left
            if (!returnValue) returnValue = solveMaze(finalArray, r, c - 1);
            //check right
            if (!returnValue) returnValue = solveMaze(finalArray, r, c + 1);

            if(returnValue) {
                //System.out.println(r + ", " + c); //position check
                finalArray[r][c] = '°'; //re-printing path
            }
            return returnValue;
        }
        //if I reached this point, no solution can ever be found, at least by my method...
        return false;
    }
}