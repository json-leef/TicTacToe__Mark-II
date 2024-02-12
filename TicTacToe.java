import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
    static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();
    
    private enum Elements {X, O, EMPTY};
    private Elements[][] gameGrid = new Elements[3][3];

    char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};

        //printGameBoard(gameBoard);
    
    public static void main(String[] args) {
        TicTacToe action = new TicTacToe();


        while (true) {
            Scanner players =  new Scanner(System.in);
            System.out.println("1 Player or 2 Players? [Enter 1 or 2] ");
            int numPlayers = players.nextInt();

            if (numPlayers == 1) {

                action.versusCPU();
                break;

            } else if (numPlayers == 2) {
                action.versusFriend();
                break;

            } else {
                System.out.println(">Invalid Entry<");
            }
        }  
    }

    public void versusCPU() {
        System.out.println(gameGrid);
        gameGrid[2][2] = Elements.X;
        System.out.println(gameGrid[2][2]);

        Boolean goFirst = true;
        Elements playerIcon;
        Elements cpuIcon;

        Scanner order =  new Scanner(System.in);
        System.out.println("Would you like to go 1st or 2nd? [Enter 1 or 2] ");
        int orderTurns = order.nextInt();
        if (orderTurns == 1) {
            goFirst = true;
            playerIcon = Elements.X;
            cpuIcon = Elements.O;
        }
        else {
            goFirst = false;
            playerIcon = Elements.O;
            cpuIcon = Elements.X;
        }
        //order.close();

        while (true) {
            if (goFirst == false) {
                Random rand = new Random();
                int cpuRow = rand.nextInt(2) + 1;
                int cpuCol = rand.nextInt(2) + 1;
                // while (playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
                //     cpuPos = rand.nextInt(9) + 1;
                // }
                placePiece(gameBoard, cpuRow, cpuCol, cpuIcon);
                printGameBoard(gameBoard);

                String result = checkWinner();
                result = checkWinner();
                if (result.length() > 0) {
                    System.out.println(result);
                    break;
                }
                System.out.println(result);
                goFirst = true;

            } 
            System.out.println("Enter your placement (row, column): ");
            Scanner scanRow =  new Scanner(System.in);
            System.out.println("What row?");
            int row = scanRow.nextInt();

            Scanner scanCol =  new Scanner(System.in);
            System.out.println("What column?");
            int col = scanCol.nextInt();
            //int playerPos = scanRow.nextInt();
            //scan.close();
            int playerPos = 1; // deelete afgtyer. Just a placeholder

            row = row -1;
            col = col - 1;

            while (playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
                System.out.println("Position taken!");
                playerPos = scanRow.nextInt();
            }

            placePiece(gameBoard, row, col, playerIcon);

            String result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }

            Random rand = new Random();
            int cpuRow = rand.nextInt(2) + 1;
            int cpuCol = rand.nextInt(2) + 1;
            placePiece(gameBoard, cpuRow, cpuCol, cpuIcon);
            printGameBoard(gameBoard);

            result = checkWinner();
            if (result.length() > 0) {
                System.out.println(result);
                break;
            }
            System.out.println(result);

        }

    }

    public void versusFriend() {

    }

    public static void printGameBoard(char[][] gameBoard){
        for (char[] row : gameBoard) {
            for(char c : row ) {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public void placePiece(char[][] gameBoard, int row, int col, Elements icon) {
        gameGrid[row][col] = icon;
        String pos = null;
        char symbol = ' ';

        if (row == 0) {
            pos = "Top";
        } else if (row == 1) {
            pos = "Middle";
        } else if (row == 2) {
            pos = "Bottom";
        }

        if (col == 0) {
            pos += "Left";
        } else if (col == 1) {
            pos += "Middle";
        } else if (col == 2) {
            pos += "Right";
        }

        if (icon == Elements.X) {
            symbol = 'X';
        } else if (icon == Elements.O) {
            symbol = 'O';
        }


        switch (pos){
            case "TopLeft":
                gameBoard[0][0] = symbol;
                break;
            case "TopMiddle":
                gameBoard[0][2] = symbol;
                break;
            case "TopRight":
                gameBoard[0][4] = symbol;
                break;
            case "MiddleLeft":
                gameBoard[2][0] = symbol;
                break;
            case "MiddleMiddle":
                gameBoard[2][2] = symbol;
                break;
            case "MiddleRight":
                gameBoard[2][4] = symbol;
                break;
            case "BottomLeft":
                gameBoard[4][0] = symbol;
                break;
            case "BottomMiddle":
                gameBoard[4][2] = symbol;
                break;
            case "BottomRight":
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
        //gameBoard[row][col] = icon;
    }

    public static String checkWinner() {

        List topRow = Arrays.asList(1, 2, 3 );
        List midRow = Arrays.asList(4, 5, 6);
        List botRow = Arrays.asList(7, 8, 9);
        List leftCol = Arrays.asList(1, 4, 7);
        List midCol = Arrays.asList(2, 5, 8);
        List rigthCol = Arrays.asList(3, 6, 9);
        List cross1 = Arrays.asList(1, 5, 9);
        List cross2 = Arrays.asList(3, 5, 7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(midRow);
        winning.add(botRow);
        winning.add(midCol);
        winning.add(rigthCol);
        winning.add(cross1);
        winning.add(cross2);

        for (List l : winning) {
            if (playerPositions.containsAll(l)) {
                return "Congratulations!!";
                //System.out.println("Congrutaltions!");
            } else if(cpuPositions.containsAll(l)) {
                return "CPU wins! Sorry!";
            } else if (playerPositions.size() + cpuPositions.size() == 9) {
                return ("FULL!");
            }
        } 




        return "";
    }

}
