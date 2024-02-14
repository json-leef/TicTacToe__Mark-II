//---------------------------------------------------------------------------
//
// Tic-Tac-Toe
// A Tic Tac Toe game, where you can play against another person, or against a CPU
//
// Author: Jason Lee
// Date: 02/14/24
// Class: MET CS622
// Issues: None known
//
// Description:
// 
//
// Assumptions:
// 
//
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    char result;
    
    private enum Elements {X, O, EMPTY};
    private Elements[][] gameGrid = new Elements[3][3];

    char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
    
    public static void main(String[] args) {
        TicTacToe action = new TicTacToe();

        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                action.gameGrid[a][b] = Elements.EMPTY;
            }
        }

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
        printGameBoard(gameBoard);

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

        while (true) {
            if (goFirst == false) {
                Random rand = new Random();
                int cpuRow = rand.nextInt(2) + 1;
                int cpuCol = rand.nextInt(2) + 1;

                placePiece(gameBoard, cpuRow, cpuCol, cpuIcon);
                printGameBoard(gameBoard);

                result = checkWinner(cpuIcon);
                if (result == 'w') {
                    System.out.println("CPU Wins!");
                    break;
                }
                else if (result == 'f') {
                    System.out.println("Board Full!");
                    break;
                }
                    goFirst = true;

            } 
            System.out.println("Enter your placement (row, column): ");
            Scanner scanRow =  new Scanner(System.in);
            System.out.println("What row?");
            int row = scanRow.nextInt();

            Scanner scanCol =  new Scanner(System.in);
            System.out.println("What column?");
            int col = scanCol.nextInt();


            row = row - 1;
            col = col - 1;

            while (gameGrid[row][col] != Elements.EMPTY) {
                System.out.println("Position Taken!");

                Scanner scanRow2 =  new Scanner(System.in);
                System.out.println("What row?");
                row = scanRow2.nextInt();

                Scanner scanCol2 =  new Scanner(System.in);
                System.out.println("What column?");
                col = scanCol2.nextInt();

                row = row - 1;
                col = col - 1;
            }


            placePiece(gameBoard, row, col, playerIcon);

            result = checkWinner(playerIcon);
            if (result == 'w') {
                System.out.println("Congratualtions!");
                break;
            }
            else if (result == 'f') {
                System.out.println("Board Full!");
                break;
            }
            

            Random rand = new Random();
            int cpuRow = rand.nextInt(3);
            int cpuCol = rand.nextInt(3);
            while (gameGrid[cpuRow][cpuCol] != Elements.EMPTY) {

                Random rand2 = new Random();
                cpuRow = rand2.nextInt(3);
                cpuCol = rand2.nextInt(3);

            }
            placePiece(gameBoard, cpuRow, cpuCol, cpuIcon);
            printGameBoard(gameBoard);

            result = checkWinner(cpuIcon);
            if (result == 'w') {
                System.out.println("CPU Wins!");
                break;
            }
            else if (result == 'f') {
                System.out.println("Board Full!");
                break;
            }

        }

    }

    public void versusFriend() {
        Elements playerOneIcon;
        Elements playerTwoIcon;
            playerOneIcon = Elements.X;
            playerTwoIcon = Elements.O;


        while (true) {
            
            System.out.println("Player 1: Enter your placement (row, column): ");
            Scanner scanRow =  new Scanner(System.in);
            System.out.println("What row?");
            int row = scanRow.nextInt();

            Scanner scanCol =  new Scanner(System.in);
            System.out.println("What column?");
            int col = scanCol.nextInt();

            row = row - 1;
            col = col - 1;

            while (gameGrid[row][col] != Elements.EMPTY) {
                System.out.println("Position Taken!");

                Scanner scanRowAgain1 =  new Scanner(System.in);
                System.out.println("What row?");
                row = scanRowAgain1.nextInt();

                Scanner scanColAgain1 =  new Scanner(System.in);
                System.out.println("What column?");
                col = scanColAgain1.nextInt();

                row = row - 1;
                col = col - 1;
            }


            placePiece(gameBoard, row, col, playerOneIcon);
            printGameBoard(gameBoard);

            result = checkWinner(playerOneIcon);
            if (result == 'w') {
                System.out.println("Congratualtions, Player 1 Wins!");
                break;
            }
            else if (result == 'f') {
                System.out.println("Board Full!");
                break;
            }
            
// ------------------------------------------------------------
            System.out.println("Player 2: Enter your placement (row, column): ");
            Scanner scanRow2 =  new Scanner(System.in);
            System.out.println("What row?");
            int row2 = scanRow2.nextInt();

            Scanner scanCol2 =  new Scanner(System.in);
            System.out.println("What column?");
            int col2 = scanCol2.nextInt();

            row2 = row2 - 1;
            col2 = col2 - 1;

            while (gameGrid[row2][col2] != Elements.EMPTY) {
                System.out.println("Position Taken!");

                Scanner scanRowAgain2 =  new Scanner(System.in);
                System.out.println("What row?");
                row2 = scanRowAgain2.nextInt();

                Scanner scanColAgain2 =  new Scanner(System.in);
                System.out.println("What column?");
                col2 = scanColAgain2.nextInt();

                row2 = row2 - 1;
                col2 = col2 - 1;
            }

            placePiece(gameBoard, row2, col2, playerTwoIcon);
            printGameBoard(gameBoard);

            result = checkWinner(playerTwoIcon);
            if (result == 'w') {
                System.out.println("Congratualtions, Player 2 Wins!");
                break;
            }
            else if (result == 'f') {
                System.out.println("Board Full!");
                break;
            }

        }


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
    }

    public char checkWinner(Elements icon) {

        //Top Row
        if (gameGrid[0][0] == icon){
            if (gameGrid[0][1] == icon) {
                if (gameGrid[0][2] == icon) {
                    return ('w');
                }
            }
        }
        //Middle Row
        if (gameGrid[1][0] == icon) {
            if (gameGrid[1][1] == icon) {
                if (gameGrid[1][2] == icon) {
                    return ('w');
                }
            }
        }
        //Bottom Row
        if (gameGrid[2][0] == icon) {
            if (gameGrid[2][1] == icon) {
                if (gameGrid[2][2] == icon) {
                    return ('w');
                }
            }
        }
        //Left Column
        if (gameGrid[0][0] == icon) {
            if (gameGrid[1][0] == icon) {
                if (gameGrid[2][0] == icon) {
                    return ('w');
                }
            }
        }
        //Middle Column
        if (gameGrid[0][1] == icon) {
            if (gameGrid[1][1] == icon) {
                if (gameGrid[2][1] == icon) {
                    return ('w');
                }
            }
        }
        //Right Column
        if (gameGrid[0][2] == icon) {
            if (gameGrid[1][2] == icon) {
                if (gameGrid[2][2] == icon) {
                    return ('w');
                }
            }
        }
        // Diagonal 1
        if (gameGrid[0][2] == icon) {
            if (gameGrid[1][1] == icon) {
                if (gameGrid[2][0] == icon) {
                    return ('w');
                }
            }
        }
        // Diagonal 2
        if (gameGrid[0][2] == icon) {
            if (gameGrid[1][2] == icon) {
                if (gameGrid[2][2] == icon) {
                    return ('w');
                }
            }
        }
        // Full Board Check
        else if (gameGrid[0][0] != Elements.EMPTY) {
            if (gameGrid[0][1] != Elements.EMPTY) {
                if (gameGrid[0][2] != Elements.EMPTY) {
                    if (gameGrid[1][0] != Elements.EMPTY) {
                        if (gameGrid[1][1] != Elements.EMPTY) {
                            if (gameGrid[1][2] != Elements.EMPTY) {
                                if (gameGrid[2][0] != Elements.EMPTY) {
                                    if (gameGrid[2][1] != Elements.EMPTY) {
                                        if (gameGrid[2][2] != Elements.EMPTY) {
                                            return ('f');
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        
        return ('o');
        
    }

}
