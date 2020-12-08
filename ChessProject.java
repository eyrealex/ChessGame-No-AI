import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
	This class can be used as a starting point for creating your Chess game project. The only piece that 
	has been coded is a white pawn...a lot done, more to do!
*/

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
    JLayeredPane layeredPane;
    JPanel chessBoard;
    JLabel chessPiece;
    int xAdjustment;
    int yAdjustment;
    int startX;
    int startY;
    int initialX;
    int initialY;
    JPanel panels;
    JLabel pieces;


    public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        //  Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        //Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
            JPanel square = new JPanel(new BorderLayout());
            chessBoard.add(square);

            int row = (i / 8) % 2;
            if (row == 0)
                square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
            else
                square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
            pieces = new JLabel(new ImageIcon("WhitePawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
            pieces = new JLabel(new ImageIcon("BlackPawn.png"));
            panels = (JPanel) chessBoard.getComponent(i);
            panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
    }


    /*
        This method checks if there is a piece present on a particular square.
    */
    private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
            return false;
        } else {
            return true;
        }
    }

    private String newPiece(int x, int y) {
        x = x * 75;
        y = y * 75;
        if (chessBoard.findComponentAt(x, y) instanceof JLabel) {
            Component newC = chessBoard.findComponentAt(x, y);
            JLabel awaitingPiece = (JLabel) newC;
            return awaitingPiece.getIcon().toString();
        } else {
            return "";
        }
    }

    /*
        This is a method to check if a piece is a Black piece.
    */
    private Boolean checkWhiteOponent(int newX, int newY) {
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("Black")))) {
            oponent = true;
        } else {
            oponent = false;
        }
        return oponent;
    }

    /*
       This is a method to check if a piece is a White piece.
   */
    private Boolean checkBlackOponent(int newX, int newY) {
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("White")))) {
            oponent = true;
        } else {
            oponent = false;
        }
        return oponent;
    }

    /*
        This method is called when we press the Mouse. So we need to find out what piece we have
        selected. We may also not have selected a piece!
    */
    public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
            return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
    }

    public void mouseDragged(MouseEvent me) {
        if (chessPiece == null) return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
    }

    /*
       This method is used when the Mouse is released...we need to make sure the move was valid before
       putting the piece back on the board.
   */

    //global variables
    Boolean validMove = false;
    boolean turn = false;
    Boolean success;
    String pieceName;

    public void mouseReleased(MouseEvent e) {
        if (chessPiece == null) return;
        chessPiece.setVisible(false);
        success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        pieceName = tmp.substring(0, (tmp.length() - 4));
        validMove = false;

        if((!turn)){
            switch (pieceName) {
                case ("WhitePawn"):
                    whitePawnMethod(e);
                    break;
                case ("WhiteRook"):
                    RookMethod(e);
                    break;
                case ("WhiteKnight"):
                    KnightMethod(e);
                    break;
                case ("WhiteBishup"):
                    BishopMethod(e);
                    break;
                case ("WhiteQueen"):
                    QueenMethod(e);
                    break;
                case ("WhiteKing"):
                    KingMethod(e);
                    break;
            }//end switch for white piece
            if(validMove){
                turn = !turn;
            }

        } else {
            switch (pieceName) {
                case ("BlackPawn"):
                    blackPawnMethod(e);
                    break;
                case ("BlackRook"):
                    RookMethod(e);
                    break;
                case ("BlackKnight"):
                    KnightMethod(e);
                    break;
                case ("BlackBishup"):
                    BishopMethod(e);
                    break;
                case ("BlackQueen"):
                    QueenMethod(e);
                    break;
                case ("BlackKing"):
                    KingMethod(e);
                    break;
            }//end switch for black pieces
            if(validMove){
                turn = !turn;
            }
        }//end else  for turn statement

        if (!validMove) {
            int location = 0;
            if (startY == 0) {
                location = startX;
            } else {
                location = (startY * 8) + startX;
            }
            String pieceLocation = pieceName + ".png";
            pieces = new JLabel(new ImageIcon(pieceLocation));
            panels = (JPanel) chessBoard.getComponent(location);
            panels.add(pieces);
        } //end if not a valid move
        else {
            if ((success) && (pieceName.contains("White"))) {
                int location = 56 + (e.getX() / 75);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                } else {
                    Container parent = (Container) c;
                    pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                }
            } else if ((success) && (pieceName.contains("Black"))) {
                int location = (e.getX() / 75);
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                } else {
                    Container parent = (Container) c;
                    pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                    parent = (JPanel) chessBoard.getComponent(location);
                    parent.add(pieces);
                }

            } else {
                if (c instanceof JLabel) {
                    Container parent = c.getParent();
                    parent.remove(0);
                    parent.add(chessPiece);
                } else {
                    Container parent = (Container) c;
                    parent.add(chessPiece);
                }
                chessPiece.setVisible(true);
            }

        }//end else for if a valid move

    }//end mouseReleased method

    private void whitePawnMethod(MouseEvent e) { //whitePawn method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);

        if (e.getX() > 590 || e.getX() < 0 || e.getY() < 0 || e.getY() > 590) {
            validMove = false;
            return;
        }
        if (startY == 1) { //if white pawn is in its first position
            if ((startX == newX) && (((newY - startY) == 1) || (newY - startY) == 2)) { //if the white pawn moves one OR two squares on the y axis
                if (newY - startY == 2) { // if the white piece moves two squares on the y axis
                    if (!piecePresent(e.getX(), e.getY()) && (!piecePresent(e.getX(), e.getY() - 75))) { // check if no pieces are present in the two squares the white pawn jumps
                        validMove = true; //allow the move
                    }//end if
                    else { //else dont allow the move
                        validMove = false;
                    }
                }//end if the pawn moves two squares
                else { //if the white pawn has not moved 2 squares
                    if (!piecePresent(e.getX(), e.getY())) {//if no piece is present allow the pawn to move one square
                        validMove = true; //alow the move
                    }//end if
                    else { //else dont allow the move
                        validMove = false;
                    }//end else
                }//end else for the pawn to move one square
            }//end if the pawn has moved one OR two squares forward
            //if there is a piece present down to the right or down to the left and withing the boundaries of the board
            else if ((piecePresent(e.getX(), (e.getY()))) && ((((newX == (startX + 1) && (startX + 1 <= 7))) && (newY - startY == 1) || ((newX == (startX - 1)) && (startX - 1 >= 0)))) && (newY - startY == 1)) {
                if (checkWhiteOponent(e.getX(), e.getY())) { //check if this is a black piece
                    validMove = true; //allow the white pawn to take the black pawn
                } else {
                    validMove = false; //don't allow the white pawn to take the black pawn
                }
            } else { //else if there are no pieces diagonal to the white pawn from the starting position
                validMove = false; // don't allow a move
            }
        }//end if the white pawn is in the first position
        else {//if the white pawn is not in the first position
            if ((startX - 1 >= 0) || (startX + 1 <= 7)) { //ensure the pawn stays on the board over the x axis
                // if there is a piece present down and to the right or down and to the left and within the boundaries of the board
                if ((piecePresent(e.getX(), (e.getY()))) && ((((newX == (startX + 1) && (startX + 1 <= 7) && (newY == startY + 1))) || ((newX == (startX - 1)) && (startX - 1 >= 0) && (newY == startY + 1))))) {
                    if (checkWhiteOponent(e.getX(), e.getY())) { //if its a black piece
                        validMove = true;//allow it to be taken
                        if (startY == 6) { //if the pawn was on the the position of 6 while taking a piece
                            success = true; // this allows the pawn to transform
                        }
                    } else {
                        validMove = false;
                    }
                } //end if for taking a piece when on the starting y position of 6
                else { //otherwise
                    if (!piecePresent(e.getX(), (e.getY()))) { //if there is no piece present
                        if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == 1) { //if the pawn moves forward one square
                            if (startY == 6) { //and its one the starting y position of 6
                                success = true;// this allows the pawn to transform
                            }
                            validMove = true; //allow the move
                        } else {
                            validMove = false; //dont allow the move if the pawn does not move one position
                        }
                    } else {
                        validMove = false; //dont allow the pawn to move if there is a piece present in front of it
                    }
                }// end else for taking a piece and transforming into a queen
            } else {
                validMove = false; //dont allow the pawn to move if it does not take a piece
            }
        }//end else if the pawn is not in the first position
    }//end white pawn method

    private void blackPawnMethod(MouseEvent e) {//Blackpawn method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);

        if (e.getX() > 590 || e.getX() < 0 || e.getY() < 0 || e.getY() > 590) {
            validMove = false;
            return;
        }
        if (startY == 6) { // if the black pawn is in its first position
            if ((startX == newX) && (((startY - newY) == 1) || (startY - newY) == 2)) { //if the black pawn can move one or two squares from the starting position
                //If there is a piece in the way
                if ((!piecePresent(e.getX(), e.getY())) && (!piecePresent(e.getX(), e.getY() + 75))) { // if there is no piece present on an X coordinate or Y coordinate and there is no piece present on an X coordinate or Y coordinate + 1 tile
                    validMove = true; //the move can be made
                }//end if
            } //end
            else {
                validMove = false; //the move cant be made
            }
        } else if ((Math.abs(startX - newX) == 1) && (((startY - newY) == 1))) {
            if (piecePresent(e.getX(), e.getY())) {
                if (checkBlackOponent(e.getX(), e.getY())) {
                    validMove = true;
                    if (newY == 0) {
                        success = true;
                    }
                } else {
                    validMove = false;
                }
            } else {
                validMove = false;
            }
        } else if ((startY != 6) && ((startX == newX) && (((startY - newY) == 1)))) { //if the black pawn gets to the other side of the board it becomes a queen
            //If there is a piece in the way
            if (!piecePresent(e.getX(), e.getY())) {
                validMove = true;
                if (newY == 0) {
                    success = true;
                }
            } else {
                validMove = false;
            }
        } else {
            validMove = false;
        }
    }//end blackpawn method

    private void RookMethod(MouseEvent e) { //rook method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);

        int j = (startX - newX) + (startY - newY); //j is the starting position of x minus the new position, the change in X is j. Can be negative or positive because rook can move up or down.
        if (j < 0) {
            j = j * -1; //make sure in integer is always positive to use in the for loop as for loop cant run on negative number
        }

        if (e.getX() > 590 || e.getX() < 0 || e.getY() < 0 || e.getY() > 590) {
            validMove = false;
            return;
        }
        for (int i = 1; i <= j; i++) {
            if ((startY == newY) && (startX > newX)) {
                if (piecePresent(((startX - i) * 75), ((startY) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteRook") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackRook") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//end if for going left
            //moving right
            if ((startY == newY) && (startX < newX)) {
                if (piecePresent(((startX + i) * 75), ((startY) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteRook") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackRook") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//end if for moving to the right
            //moving down on the board
            if ((startY < newY) && (startX == newX)) {
                if (piecePresent(((startX) * 75), ((startY + i) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteRook") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackRook") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//end if for moving down on the board
            //moving up on the board
            if ((startY > newY) && (startX == newX)) {
                if (piecePresent(((startX) * 75), ((startY - i) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteRook") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackRook") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//end if for moving up on the board
        }//end for loop
    }//end rook method

    private void KnightMethod(MouseEvent e) {//knight method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);

        if (e.getX() > 590 || e.getX() < 0 || e.getY() < 0 || e.getY() > 590) {
            validMove = false;
            return;
        }
        if (((newX * 75 == (startX * 75) + 75) && (newY * 75 == (startY * 75) + 150))//move down two spaces on the y axis and one space to the right on the x axis
                || ((newX * 75 == (startX * 75) - 75) && (newY * 75 == (startY * 75) + 150))//move down two spaces on the y axis and one space to the left on the x axis
                || ((newX * 75 == (startX * 75) + 150) && (newY * 75 == (startY * 75) + 75))//move down one spaces on the y axis and two spaces to the right on the x axis
                || ((newX * 75 == (startX * 75) - 150) && (newY * 75 == (startY * 75) + 75))//move down one spaces on the y axis and two spaces to the left on the x axis
                || ((newX * 75 == (startX * 75) + 75) && (newY * 75 == (startY * 75) - 150))//move up two spaces on the y axis and one space to the right on the x axis
                || ((newX * 75 == (startX * 75) - 75) && (newY * 75 == (startY * 75) - 150))//move up two spaces on the y axis and one space to the left on the x axis
                || ((newX * 75 == (startX * 75) + 150) && (newY * 75 == (startY * 75) - 75))//move up one space on the y axis and two spaces to the right on the x axis
                || ((newX * 75 == (startX * 75) - 150) && (newY * 75 == (startY * 75) - 75))) {//move up one space on the y axis and two spaces to the left on the x axis
            if (!piecePresent(e.getX(), (e.getY()))) {
                validMove = true;
            } else {
                if ((!checkWhiteOponent(e.getX(), e.getY())) && (pieceName.contains("White"))) {
                    validMove = false;
                } else if ((checkWhiteOponent(e.getX(), e.getY())) && (pieceName.contains("Black"))) {
                    validMove = false;
                } else {
                    validMove = true;
                }
            }//end else
        } else {
            validMove = false;
        }
    }//end knight method

    private void BishopMethod(MouseEvent e) {//bishop method
        int newX = (e.getX() / 75); //position of piece after moved on X axis
        int newY = (e.getY() / 75); //position of piece after moved on Y axis

        if (e.getX() > 590 || e.getX() < 0 || e.getY() < 0 || e.getY() > 590) {
            validMove = false;
            return;
        }


        int j = (startX - newX); //j is the starting position of x minus the new position, the change in X is j. Can be negative or positive because bishop can move up or down diagonally
        if (j < 0) {
            j = j * -1; //make sure in integer is always positive to use in the for loop as for loop cant run on negative number
        }
        //convert int values to strings
        String str1 = Integer.toString(newX);
        String str2 = Integer.toString(newY);
        String str3 = Integer.toString(startX);
        String str4 = Integer.toString(startY);

        //add the converted strings together and parse them back into an integer do a modulus
        int value1 = Integer.parseInt(str1 + str2);
        int value2 = Integer.parseInt(str3 + str4);
        int value = value1 - value2; //minus the two parsed integers to find the value that we divide by (9 and 11)


        for (int i = 1; i <= j; i++) { //loop through all the moves possible

            //move down and left. if the new value stored after value1 - value2 is divisible by 9, there is a valid move
            if ((value % 9 == 0) && (startX > newX)) { //if the remainder of value when divided by 9 is equal to 0
                if (piecePresent(((startX - i) * 75), ((startY + i) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteBishup") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackBishup") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//END IF FOR modulus

            //move up and to the left. if the new value stored after value1 - value2 is divisible by 11, there is a valid move
            if ((value % 11 == 0) && (startX > newX)) {
                if (piecePresent(((startX - i) * 75), ((startY - i) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteBishup") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackBishup") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//END IF FOR modulus

            //move down and to the right
            if ((value % 11 == 0) && (startX < newX)) {
                if (piecePresent(((startX + i) * 75), ((startY + i) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteBishup") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackBishup") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//END IF FOR modulus

            //move up and to the right
            if ((value % 9 == 0) && (startX < newX)) {
                if (piecePresent(((startX + i) * 75), ((startY - i) * 75))) {
                    if ((j == i) && (checkWhiteOponent((newX * 75), (newY * 75))) && (pieceName.equals("WhiteBishup") || pieceName.equals("WhiteQueen"))) {
                        validMove = true;
                        break;
                    } else if ((j == i) && (checkBlackOponent((newX * 75), (newY * 75))) && (pieceName.equals("BlackBishup") || pieceName.equals("BlackQueen"))) {
                        validMove = true;
                        break;
                    }//end else if
                    else {
                        validMove = false;
                        break;
                    }
                }//end if piece name
                else {
                    validMove = true;
                }
            }//END IF FOR modulus

        }//end for for J

    }//end bishop method

    private void QueenMethod(MouseEvent e) {//queen method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);

        if (e.getX() > 590 || e.getX() < 0 || e.getY() < 0 || e.getY() > 590) {
            validMove = false;
            return;
        }
        if ((pieceName.equals("WhiteQueen")) || (pieceName.equals("BlackQueen"))) { //if the white queen or black queen are trying to move
            RookMethod(e); //call the rook method to allow the queen to move up, down , left and right like the rook
            if (!validMove) { //if this move is not valid
                BishopMethod(e); //call the bishop method and allow the queen to move diagonally like the bishop
            }
        }


    }//end queen method

    private void KingMethod(MouseEvent e) {//king method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);

        if (e.getX() > 590 || e.getX() < 0 || e.getY() < 0 || e.getY() > 590) {
            validMove = false;
            return;
        }
        if ((startX * 75 == newX * 75 && (startY * 75) + 75 == newY * 75) //move one space down on the y axis
                || ((startX * 75) + 75 == newX * 75 && startY * 75 == newY * 75)//move one space to the right on the x axis
                || (startX * 75 == newX * 75 && (startY * 75) - 75 == newY * 75)//move one space up on the y axis
                || ((startX * 75) - 75 == newX * 75 && startY * 75 == newY * 75)//move one space to the left on the x axis
                || ((startX * 75) + 75 == newX * 75 && (startY * 75) - 75 == newY * 75)//diagonal move, one space up on the y axis and one space right on the x axis
                || ((startX * 75) - 75 == newX * 75 && (startY * 75) + 75 == newY * 75)//diagonal move, one space down on the y axis and one space left on the x axis
                || ((startX * 75) + 75 == newX * 75 && (startY * 75) + 75 == newY * 75)//diagonal move, one space down on the y axis and one space right on the x axis
                || ((startX * 75) - 75 == newX * 75 && (startY * 75) - 75 == newY * 75)) //diagonal move, one space up on the y axis and one space left on the x axis
        {

            if (!piecePresent(e.getX(), (e.getY()))) {
                validMove = true;
            } else {
                if ((!checkWhiteOponent(e.getX(), e.getY())) && (pieceName.contains("White"))) {
                    validMove = false;
                } else if ((checkWhiteOponent(e.getX(), e.getY())) && (pieceName.contains("Black"))) {
                    validMove = false;
                } else {
                    validMove = true;
                }
            }//end else
        }//end else for the king to move in any direction
        else {
            validMove = false;
        }

    }//end king method


    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    public static void showBoard() {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    /*
        Main method that gets the ball moving.
    */
    public static void main(String[] args) {
        showBoard();
    }
}


