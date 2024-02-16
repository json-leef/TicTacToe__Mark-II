//---------------------------------------------------------------------------
//
// Tic-Tac-Toe
// A Tic-Tac-Toe game, where you can play against another person, or against a CPU
//
// Author: Jason Lee
// Date: 02/14/24
// Class: MET CS622
// Issues: None known
//
// Description:
// A program that allows a user to play Tic-Tac-Toe. User can decide if they would like to
// play against another person beside them, against the computer, or watch the computer play
// against itself. The game board data is stored in a 3x3 2D array, & uses enum constants 
// {X, O, EMPTY} to be pieces on the board. After each move, the program determines whether 
// the game has been won, lost, or a draw.
// 
//
// Assumptions:
// n/a
//---------------------------------------------------------------------------

//
// Class: Tic-Tac-Toe
//
// Description:
// Declares a private 2D array (gameGrid), enums (X, O, and EMPTY), a visual board for printing the game (gameBoard).
// Features 8 different methods, used to help the user play against the computer, play against a friend, or watch cpu vs cpu.
//
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private enum Elements {X, O, EMPTY};
    private Elements[][] gameGrid = new Elements[3][3];
    char [] [] gameBoard = {{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
    char result;
    
    
    ///////////////////////////////////////////////////////////////////////////////////////////////
    /// Main (String[] args)                                                                    ///
    /// Input : (String[] args)                                                                 ///
    /// Output: n/a                                                                             ///
    /// Begins the program by initializing the array to be all EMPTY. And                       ///
    /// then asks the user if they want to play against the computer, play                      ///
    /// against another person beside them, or see the sim results of computer vs. computer     ///
    ///////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        TicTacToe action = new TicTacToe();
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                action.gameGrid[a][b] = Elements.EMPTY;
            }
        }
        while (true) {
            Scanner players =  new Scanner(System.in);
            System.out.println("1 Player, 2 Players, or CPU vs CPU? [Enter 1 or 2 or 3] ");
            int numPlayers = players.nextInt();

            if (numPlayers == 1) {
                action.versusCPU();
                break;

            } else if (numPlayers == 2) {
                action.versusFriend();
                break;

            } else if(numPlayers == 3) {
                action.cpuVersusCpu();
                break;
            } else {
                System.out.println(">Invalid Entry<");
            }
        }  
    }

    /////////////////////////////////////////////////////////////////////
    /// versusCPU ()                                                  ///
    /// Input : n/a                                                   ///
    /// Output: n/a                                                   ///
    /// Purpose is to simulate a Tic-Tac-Toe game of user vs. CPU     ///
    /// Simply simulates the game, and calls methods of checkWinner() ///
    /// and placePiece() after each move. If the game ends in a Win   ///
    /// loss, or draw, the method (& program) ends.                   ///
    /////////////////////////////////////////////////////////////////////
    public void versusCPU() {
        printGameBoard();

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
            // Handles case if user chooses to go second
            if (goFirst == false) {
                Random rand = new Random();
                int cpuRow = rand.nextInt(2) + 1;
                int cpuCol = rand.nextInt(2) + 1;

                placePiece(cpuRow, cpuCol, cpuIcon);
                printGameBoard();

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
            // User's turn
            System.out.println("Enter your placement (row, column): ");
            Scanner scanRow =  new Scanner(System.in);
            System.out.println("What row?");
            int row = scanRow.nextInt();

            Scanner scanCol =  new Scanner(System.in);
            System.out.println("What column?");
            int col = scanCol.nextInt();

            // Subtracts user's values by 1 to account for array's index zero
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
            placePiece(row, col, playerIcon);
            printGameBoard();

            result = checkWinner(playerIcon);
            if (result == 'w') {
                System.out.println("Congratualtions!");
                break;
            }
            else if (result == 'f') {
                System.out.println("Board Full!");
                break;
            }

            // CPU's turn
            Random rand = new Random();
            int cpuRow = rand.nextInt(3);
            int cpuCol = rand.nextInt(3);
            while (gameGrid[cpuRow][cpuCol] != Elements.EMPTY) {

                Random rand2 = new Random();
                cpuRow = rand2.nextInt(3);
                cpuCol = rand2.nextInt(3);

            }
            placePiece(cpuRow, cpuCol, cpuIcon);
            printGameBoard();

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

    /////////////////////////////////////////////////////////////////////////
    /// versusFriend ()                                                   ///
    /// Input : n/a                                                       ///
    /// Output: n/a                                                       ///
    /// Purpose is to simulate a Tic-Tac-Toe game of user vs. other user  ///
    /// Simply simulates the game, and calls methods of checkWinner()     ///
    /// and placePiece() after each move. If the game ends in a Win       ///
    /// loss, or draw, the method (& program) ends.                       ///
    /////////////////////////////////////////////////////////////////////////
    public void versusFriend() {
        // Establishes which player gets to be X and which gets to be O
        Elements playerOneIcon;
        Elements playerTwoIcon;
        playerOneIcon = Elements.X;
        playerTwoIcon = Elements.O;


        while (true) {
            // Player 1's Turn
            System.out.println("Player 1: Enter your placement (row, column): ");
            Scanner scanRow =  new Scanner(System.in);
            System.out.println("What row?");
            int row = scanRow.nextInt();
            Scanner scanCol =  new Scanner(System.in);
            System.out.println("What column?");
            int col = scanCol.nextInt();

            // Subtracts user's values by 1 to account for array's index zero
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


            placePiece(row, col, playerOneIcon);
            printGameBoard();

            result = checkWinner(playerOneIcon);
            if (result == 'w') {
                System.out.println("Congratualtions, Player 1 Wins!");
                break;
            }
            else if (result == 'f') {
                System.out.println("Board Full!");
                break;
            }
            
            // Player 2's Turn
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

            placePiece(row2, col2, playerTwoIcon);
            printGameBoard();

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

    /////////////////////////////////////////////////////////////////////////
    /// printGameBoard ()                                                 ///
    /// Input : n/a                                                       ///
    /// Output: Prints out a visual depiction of the gameboard thus far   ///                                            
    /// Double for-loop to cycle through all chars in the gameBoard array ///
    /// and print them all out                                            ///
    /////////////////////////////////////////////////////////////////////////
    public void printGameBoard(){
        System.out.println("***********");
        for (char[] row : gameBoard) {
            System.out.print("*  ");
            for(char c : row ) {
                System.out.print(c);
            }
            System.out.print("  *");
            System.out.println();
        }
        System.out.println("***********");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// placePiece (where the player wants to move, and which player is moving)                  ///                                 
    /// Input : int row and int column coordinates on grid. Whoever's turn's enum assignment.    ///                                               
    /// Output: n/a                                                                              ///
    /// Places the player's piece at their desired coordinates. Updates the gameGrid array       ///
    /// and also the visual gameBoard array. Uses strings to concatonate a unique identifier for ///
    /// where in the board the move must go, and then uses a switch statement to place either    ///
    /// X or O on the visual gameboard.                                                          ///
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void placePiece(int row, int col, Elements icon) {
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

    ////////////////////////////////////////////////////////////////////////////////////////
    /// checkWinner (Whoever's current turn it is' assigned enum element/game piece)     ///                                             
    /// Input : X or O                                                                   ///
    /// Output: Returns either ('w') = win, ('f') = full, or ('o') = continue            ///                                        
    /// Has the stored winning patterns; uses a series of if-statements to check the     ///
    /// current private 3x3 gameGrid if it has 3 of the current game piece in a row.     ///
    /// If it does, it returns ('w'). If the board is full, it returns ('f'), and        ///
    /// if no one has won yet and moves can still be made, it return ('o') for the       ///
    /// game to continue.                                                                ///
    ////////////////////////////////////////////////////////////////////////////////////////
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
        if (gameGrid[0][0] == icon) {
            if (gameGrid[1][1] == icon) {
                if (gameGrid[2][2] == icon) {
                    return ('w');
                }
            }
        }
        // Diagonal 2
        if (gameGrid[0][2] == icon) {
            if (gameGrid[1][1] == icon) {
                if (gameGrid[2][0] == icon) {
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
        // If game can continue to be played
        return ('o');
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /// cpuVersusCpu ()                                                                          ///                              
    /// Input :     ///                                               
    /// Output: Visual Depictions of how the simmed game went                                                                              ///
    /// Uses a while loop to simulate through the 2 cpu's turns. Each cpu has a plan with moves they
    /// wish to make. With the beginning of each new turn, the vitality of their current plan is
    /// checked, and if it is rendered invalid from opponent's previous move, it selects a new plan.                                                           ///
    ////////////////////////////////////////////////////////////////////////////////////////////////
    public void cpuVersusCpu() {
        Elements gamePiece;
        gamePiece = Elements.X;
        Boolean roundFlag = true;
        int round = 1;

        //The randomly generated seed that corresponds to a plan
        int cpuSeed;
        int x_planNumber = 0;
        int o_planNumber = 0;
        
        
        // Boolean checker for each side's plan
        Boolean planIsGood_X = false;
        Boolean planIsGood_O = false;
        
        
        // Name of the plan they wish to carry out
        String xPlan = null;
        String oPlan = null;
        String cpuPlan = null;

        // Checker variable that checks if the plan is still good
        String checker = null;
        System.out.println("Cpu vs. Cpu:");


        while (true) {
            //Checks if the plan is still good for either X or O when it's their turn.
            if (gamePiece == Elements.X) {
                cpuPlan = xPlan;
                checker = cpuStrategy(x_planNumber, gamePiece);
                if (checker == "Random") {
                    planIsGood_X = false;
                }

            } else if (gamePiece == Elements.O) {
                cpuPlan = oPlan;
                checker = cpuStrategy(o_planNumber, gamePiece);
                    if (checker == "Random") {
                        planIsGood_O = false;
                    }
            }
            
            // If the plan is no longer valid, then uses random number generator to select a new viable plan
            if (gamePiece == Elements.X && planIsGood_X == false) {
                Random rand = new Random();
                cpuSeed = rand.nextInt(8) + 1;
                x_planNumber = cpuSeed;
                xPlan = cpuStrategy(cpuSeed, gamePiece);
                cpuPlan = xPlan;
                planIsGood_X = true;
            } else if (gamePiece == Elements.O && planIsGood_O == false) {
                Random rand = new Random();
                cpuSeed = rand.nextInt(8) + 1;
                oPlan = cpuStrategy(cpuSeed, gamePiece);
                cpuPlan = oPlan;
                planIsGood_O = true;
            }
            
            // Depending on what the String for xPlan or oPlan is, uses the identifier to execute the right moves
            switch(cpuPlan) {
                case "TopRow":
                    if (gameGrid[0][0] == Elements.EMPTY) {
                        placePiece(0, 0, gamePiece);
                    } else if (gameGrid[0][1] == Elements.EMPTY) {
                        placePiece(0, 1, gamePiece);
                    } else if (gameGrid[0][2] == Elements.EMPTY) {
                        placePiece(0, 2, gamePiece);
                    }
                    break;
                case "MiddleRow":
                    if (gameGrid[1][0] == Elements.EMPTY) {
                        placePiece(1, 0, gamePiece);
                    } else if (gameGrid[1][1] == Elements.EMPTY) {
                        placePiece(1, 1, gamePiece);
                    } else if (gameGrid[1][2] == Elements.EMPTY) {
                        placePiece(1, 2, gamePiece);
                    }
                    break;
                case "BottomRow":
                    if (gameGrid[2][0] == Elements.EMPTY) {
                        placePiece(2, 0, gamePiece);
                    } else if (gameGrid[2][1] == Elements.EMPTY) {
                        placePiece(2, 1, gamePiece);
                    } else if (gameGrid[2][2] == Elements.EMPTY) {
                        placePiece(2, 2, gamePiece);
                    }
                    break;
                case "LeftColumn":
                    if (gameGrid[0][0] == Elements.EMPTY) {
                        placePiece(0, 0, gamePiece);
                    } else if (gameGrid[1][0] == Elements.EMPTY) {
                        placePiece(1, 0, gamePiece);
                    } else if (gameGrid[2][0] == Elements.EMPTY) {
                        placePiece(2, 0, gamePiece);
                    }
                    break;
                case "MiddleColumn":
                    if (gameGrid[0][1] == Elements.EMPTY) {
                        placePiece(0, 1, gamePiece);
                    } else if (gameGrid[1][1] == Elements.EMPTY) {
                        placePiece(1, 1, gamePiece);
                    } else if (gameGrid[2][1] == Elements.EMPTY) {
                        placePiece(2, 1, gamePiece);
                    }
                    break;
                case "RightColumn":
                    if (gameGrid[0][2] == Elements.EMPTY) {
                        placePiece(0, 2, gamePiece);
                    } else if (gameGrid[1][2] == Elements.EMPTY) {
                        placePiece(1, 2, gamePiece);
                    } else if (gameGrid[2][2] == Elements.EMPTY) {
                        placePiece(2, 2, gamePiece);
                    }
                    break;
                case "Diagonal1":
                    if (gameGrid[0][0] == Elements.EMPTY) {
                        placePiece(0, 0, gamePiece);
                    } else if (gameGrid[1][1] == Elements.EMPTY) {
                        placePiece(1, 1, gamePiece);
                    } else if (gameGrid[2][2] == Elements.EMPTY) {
                        placePiece(2, 2, gamePiece);
                    }
                    break;
                case "Diagonal2":
                    if (gameGrid[0][2] == Elements.EMPTY) {
                        placePiece(0, 2, gamePiece);
                    } else if (gameGrid[1][1] == Elements.EMPTY) {
                        placePiece(1, 1, gamePiece);
                    } else if (gameGrid[2][0] == Elements.EMPTY) {
                        placePiece(2, 0, gamePiece);
                    }
                    break;
                case "Random":
                    Random move = new Random();
                    int cpuRow = move.nextInt(3);
                    int cpuCol = move.nextInt(3);
                    while (gameGrid[cpuRow][cpuCol] != Elements.EMPTY) {
                        Random diffMove = new Random();
                        cpuRow = diffMove.nextInt(3);
                        cpuCol = diffMove.nextInt(3);
                    }
                    placePiece(cpuRow, cpuCol, gamePiece);

            }
            //Printing & checking board
            if (roundFlag == true) {
                System.out.println("~ Round " + round + " ~");
            }
            printGameBoard();
            result = checkWinner(gamePiece);
            if (result == 'w') {
                System.out.println(gamePiece + " Wins!");
                break;
            }
            else if (result == 'f') {
                System.out.println("Draw!");
                break;
            }

            // Switches the sides as the loop starts back up again
            if (gamePiece == Elements.X) {
                gamePiece = Elements.O;
                roundFlag = false;
            } else if (gamePiece == Elements.O) {
                gamePiece = Elements.X;
                roundFlag = true;
                round++;
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// cpuStrategy (Seeing if the next plan has a path to victory)                                       ///                                               ///                              
    /// Input : The randomly selected seed number to choose a plan, and the current side's icon           ///                                               
    /// Output: Returns a string of the plan's pattern. For the xPlan or oPlan variable.                  ///                                                                ///
    /// Uses a switch statement laying out all 8 winnable plans on the board. If it is a viable path      ///
    /// then returns the name of the plan, if no other paths exist, then returns a strategy to be random  ///                                                       ///
    /////////////////////////////////////////////////////////////////////////////////////////////////////////
    public String cpuStrategy(int seed, Elements icon) {
        switch (seed) {
            case 1:
                //Top Row
                if (gameGrid[0][0] == icon || gameGrid[0][0] == Elements.EMPTY){
                    if (gameGrid[0][1] == icon || gameGrid[0][1] == Elements.EMPTY) {
                        if (gameGrid[0][2] == icon || gameGrid[0][2] == Elements.EMPTY) {
                            return ("TopRow");
                        }
                    }
                }
                break;
            case 2:
                //Middle Row
                if (gameGrid[1][0] == icon || gameGrid[1][0] == Elements.EMPTY) {
                    if (gameGrid[1][1] == icon || gameGrid[1][1] == Elements.EMPTY) {
                        if (gameGrid[1][2] == icon || gameGrid[1][2] == Elements.EMPTY) {
                            return ("MiddleRow");
                        }
                    }
                }
                break;
            case 3:
                //Bottom Row
                if (gameGrid[2][0] == icon || gameGrid[2][0] == Elements.EMPTY) {
                    if (gameGrid[2][1] == icon || gameGrid[2][1] == Elements.EMPTY) {
                        if (gameGrid[2][2] == icon || gameGrid[2][2] == Elements.EMPTY) {
                            return ("BottomRow");
                        }
                    }
                }
                break;
            case 4:
                //Left Column
                if (gameGrid[0][0] == icon || gameGrid[0][0] == Elements.EMPTY) {
                    if (gameGrid[1][0] == icon || gameGrid[1][0] == Elements.EMPTY) {
                        if (gameGrid[2][0] == icon || gameGrid[2][0] == Elements.EMPTY) {
                            return ("LeftColumn");
                        }
                    }
                }
                break;
            case 5:
                //Middle Column
                if (gameGrid[0][1] == icon || gameGrid[0][1] == Elements.EMPTY) {
                    if (gameGrid[1][1] == icon || gameGrid[1][1] == Elements.EMPTY) {
                        if (gameGrid[2][1] == icon || gameGrid[2][1] == Elements.EMPTY) {
                            return ("MiddleColumn");
                        }
                    }
                }
                break;
            case 6:
                //Right Column
                if (gameGrid[0][2] == icon || gameGrid[0][2] == Elements.EMPTY) {
                    if (gameGrid[1][2] == icon || gameGrid[1][2] == Elements.EMPTY) {
                        if (gameGrid[2][2] == icon || gameGrid[2][2] == Elements.EMPTY) {
                            return ("RightColumn");
                        }
                    }
                }
                break;
            case 7:
                // Diagonal 1
                if (gameGrid[0][2] == icon || gameGrid[0][2] == Elements.EMPTY) {
                    if (gameGrid[1][1] == icon || gameGrid[1][1] == Elements.EMPTY) {
                        if (gameGrid[2][0] == icon || gameGrid[2][0] == Elements.EMPTY) {
                            return ("Diagonal1");
                        }
                    }
                }
                break;
            case 8:
                // Diagonal 2
                if (gameGrid[0][2] == icon || gameGrid[0][2] == Elements.EMPTY) {
                    if (gameGrid[1][2] == icon || gameGrid[1][2] == Elements.EMPTY) {
                        if (gameGrid[2][2] == icon || gameGrid[2][2] == Elements.EMPTY) {
                            return ("Diagonal2");
                        }
                    }
                }
                break;
            default:
                break;
                
        }
        return "Random";

    }
}
