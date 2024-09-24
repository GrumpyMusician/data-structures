import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * TL; DR:
 * 
 * Write a program that solves Sudoku puzzles using sets and operations on sets. The general algorithm should be a traditional 
 * backtracking algorithm. In a backtracking algorithm, a potential solution is explored recursively until it fails. When that
 * happens, the most recent move is undone and an alternative move is explored.
 * 
 */

public class SudokuSolver {
    private final int M = 3; // Size of the sub-grid
    private final int N = M * M; // Size of the grid
    private int[][] grid;
    private ArrayList<Set<Integer>> rows;
    private ArrayList<Set<Integer>> cols;
    private ArrayList<Set<Integer>> squares;
    private Set<Integer> nums;

    public SudokuSolver(String fileName) {
        // read the puzzle file
        try (Scanner in = new Scanner(new File(fileName))) {
            this.grid = new int[N][N];

            for (int row = 0; row < N; row++) {
                String line = in.next();
                for (int col = 0; col < N; col++) {
                    String strVal = line.substring(col, col + 1);
                    int number = strVal.equals("x") ? 0 : Integer.parseInt(strVal);
                    this.grid[row][col] = number;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open: " + fileName);
        }

        // create the list of sets for each row (this.rows)
        ArrayList<Set<Integer>> rows = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < N; j++) {
                if (grid[i][j] != 0) set.add(grid[i][j]);
            }
            rows.add(set);
        }
        this.rows = rows;

        // create the list of sets for each col (this.cols)
        ArrayList<Set<Integer>> cols = new ArrayList<>(N);
        for (int i = 0; i < N; i++) {
            Set<Integer> set = new HashSet<>();
            for (int j = 0; j < N; j++) {
                if (grid[j][i] != 0) set.add(grid[j][i]);
            }
            cols.add(set);
        }
        this.cols = cols;

        // create the list of sets for each square (this.squares)
        ArrayList<Set<Integer>> sqrs = new ArrayList<>(N);
        for (int square = 0; square < N; square++) {
            Set<Integer> set = new HashSet<>();
            int startRow = (square / M) * M;
            int startCol = (square % M) * M;
            for (int row = startRow; row < startRow + M; row++) {
                for (int col = startCol; col < startCol + M; col++) {
                    if (this.grid[row][col] != 0) {
                        set.add(this.grid[row][col]);
                    }
                }
            }
            sqrs.add(set);
        }
        this.squares = sqrs;

        // create a hash set for [1...9] (this.nums)
        Set<Integer> nums = new HashSet<>();
        for (int i = 1; i <= 9; i++) {
            nums.add(i);
        }
        this.nums = nums;

        // visually inspect that all the sets are correct
        for (int row = 0; row < N; row++) {
            System.out.println("row " + row + ": " + this.rows.get(row));
        }
        for (int col = 0; col < N; col++) {
            System.out.println("col " + col + ": " + this.cols.get(col));
        }
        for (int square = 0; square < N; square++) {
            System.out.println("square " + square + ": " + this.squares.get(square));
        }
        System.out.println(this.nums);
    }

    public boolean solve() {
        // find an empty location, if any
        int nextRow = -1, nextCol = -1;
        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                if (this.grid[row][col] == 0) {
                    nextRow = row;
                    nextCol = col;
                    break;
                }
            }
            if (nextRow != -1) break;
        }

        // the board is complete; we solved it
        if (nextRow == -1) {
            return true;
        }

        // get all possible numbers for the row and column we are trying to populate
        Set<Integer> possibleNums = new HashSet<>(this.nums);
        possibleNums.removeAll(this.rows.get(nextRow));
        possibleNums.removeAll(this.cols.get(nextCol));
        int squareIndex = (nextRow / M) * M + (nextCol / M);
        possibleNums.removeAll(this.squares.get(squareIndex));

        // if there are no possible numbers, we cannot solve the board in its current state
        if (possibleNums.isEmpty()) {
            return false;
        }

        // try each possible number
        for (Integer possibleNum : possibleNums) {
            // update the grid and all three corresponding sets with possibleNum
            this.grid[nextRow][nextCol] = possibleNum;
            this.rows.get(nextRow).add(possibleNum);
            this.cols.get(nextCol).add(possibleNum);
            this.squares.get(squareIndex).add(possibleNum);

            // recursively solve the board
            if (this.solve()) {
                // the board is solved!
                return true;
            }

            // backtrack
            this.grid[nextRow][nextCol] = 0;
            this.rows.get(nextRow).remove(possibleNum);
            this.cols.get(nextCol).remove(possibleNum);
            this.squares.get(squareIndex).remove(possibleNum);
        }

        return false;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int[] row : grid) {
            for (int val : row) {
                str.append(val).append("\t");
            }
            str.append("\n");
        }
        return str.toString();
    }

    public static void main(String[] args) {
        // the file path is different on my laptop 
        String fileName = "C:/Users/thegr/OneDrive/Documents/GitHub/data-structures/Chapter 15 Activities/Sudoku/src/puzzle1.txt"; // Adjust the path as needed
        SudokuSolver solver = new SudokuSolver(fileName);
        System.out.println(solver);
        if (solver.solve()) {
            System.out.println("Solved!");
            System.out.println(solver);
        } else {
            System.out.println("Unsolvable...");
        }
    }
}
