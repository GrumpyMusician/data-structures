import java.util.Stack;

public class Grid
{
    private static final int SIZE = 10;
    int[][] pixels = new int[SIZE][SIZE];
    Stack<Pair> tasks = new Stack<>();

    /**
     * Flood fill, starting with the given row and column.
    */
    public void floodfill(int row, int col)
    {
        for (int i = 1; i <= 100; i++){
            Pair pair = new Pair(row, col);
            tasks.push(pair);

            pixels[col][row] = -1;

            if (row > 0 && pixels[col][row-1] == 0){
                row--;
            }
            else if (col < SIZE - 1 && pixels[col+1][row] == 0){
                col++;
            }
            else if (row < SIZE - 1 && pixels[col][row+1] == 0){
                row++;
            }
            else if (col > 0 && pixels[col-1][row] == 0){
                col--;
            }
        }

        for (int i = 100; i >= 1; i--){
            Pair pair = tasks.pop();
            pixels[pair.getColumn()][pair.getRow()] = i;
        }
    }

    @Override
    public String toString()
    {
        String r = "";
        for (int i = 0; i < SIZE; i++)
        {
            for (int j = 0; j < SIZE; j++)
                r = r + String.format("%4d", pixels[i][j]);
            r = r + "\n";
        }
        return r;
    }
}