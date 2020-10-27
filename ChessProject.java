import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

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

        //initializing whitepawn method
        if (pieceName.equals("WhitePawn")) {
            whitePawnMethod(e);
        }
        //initializing blackpawn method
        else if (pieceName.equals("BlackPawn")) {
            blackPawnMethod(e);
        }
        //initializing rook methods
        else if ((pieceName.equals("WhiteRook")) || (pieceName.equals("BlackRook"))) {
            RookMethod(e);
        }
        //initializing knight methods
        else if ((pieceName.equals("WhiteKnight")) || (pieceName.equals("BlackKnight"))) {
            KnightMethod(e);
        }
        //initializing bishop methods
        else if ((pieceName.equals("WhiteBishup")) || (pieceName.equals("BlackBishup"))) {
            BishopMethod(e);
        }
        //initializing queen methods
        else if ((pieceName.equals("WhiteQueen")) || (pieceName.equals("BlackQueen"))) {
            QueenMethod(e);
        }
        //initializing queen methods
        else if ((pieceName.equals("WhiteKing")) || (pieceName.equals("BlackKing"))) {
            KingMethod(e);
        }

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
        System.out.println("--------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The initX is : " + initialX);
        System.out.println("The initY is : " + initialY);
        System.out.println("The starting coordinates are : " + "( " + newX + "," + newY + ")");
        System.out.println("--------------------------------");

        if (startY == 1) {
            if (((startX == (e.getX() / 75)) && ((((e.getY() / 75) - startY) == 1) || ((e.getY() / 75) - startY) == 2)) || ((newX == startX + 1) && (newY == startY + 1) && (checkWhiteOponent(e.getX(), e.getY()))) || ((newX == startX - 1) && (newY == startY + 1) && (checkWhiteOponent(e.getX(), e.getY())))) {
                if ((((e.getY() / 75) - startY) == 2)) {
                    if ((!piecePresent(e.getX(), (e.getY()))) && (!piecePresent(e.getX(), (e.getY() - 75)))) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                } else {
                    if ((!piecePresent(e.getX(), (e.getY())))) {
                        validMove = true;
                    } else if ((checkWhiteOponent(e.getX(), e.getY())) && (startX != newX)) {
                        validMove = true;
                    } else {
                        validMove = false;
                    }
                }
            } else {
                validMove = false;
            }
        } else {
            if ((startX - 1 >= 0) || (startX + 1 <= 7)) {
                if ((piecePresent(e.getX(), (e.getY()))) && ((((newX == (startX + 1) && (startX + 1 <= 7) && (newY == startY + 1))) || ((newX == (startX - 1)) && (startX - 1 >= 0) && (newY == startY + 1))))) {
                    if (checkWhiteOponent(e.getX(), e.getY())) {
                        validMove = true;
                        if (startY == 6) {
                            success = true;
                        }
                    } else {
                        validMove = false;
                    }
                } else {
                    if (!piecePresent(e.getX(), (e.getY()))) {
                        if ((startX == (e.getX() / 75)) && ((e.getY() / 75) - startY) == 1) {
                            if (startY == 6) {
                                success = true;
                            }
                            validMove = true;
                        } else {
                            validMove = false;
                        }
                    } else {
                        validMove = false;
                    }
                }
            } else {
                validMove = false;
            }
        }
    }//end white pawn method

    private void blackPawnMethod(MouseEvent e) {//Blackpawn method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("--------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The starting coordinates are : " + "( " + newX + "," + newY + ")");
        System.out.println("--------------------------------");

        //The pawn can move one or two times
        if (startY == 6) { // if the starting position for the black pawn = 6
            //if the BlackPawn moves 1 or 2 tiles on the first move
            if ((startX == newX) && (((startY - newY) == 1) || (startY - newY) == 2)) {//if the starting position of X is equal to the landing postion of X and the starting position of Y - the landing position of Y is eqaul to 1 move or the starting position of Y - the landing position of Y is equal to 2
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
        } else if ((startY != 6) && ((startX == newX) && (((startY - newY) == 1)))) {
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
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("--------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("This is initialX : " + initialX);
        System.out.println("This is initialY : " + initialY);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The starting coordinates are : " + "( " + newX + "," + newY + ")");
        System.out.println("--------------------------------");
        Boolean intheway = false;

        if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) { //piece postion on x and y axis is 0 and 7
            validMove = false;
        }//end if
        else {
            if (((Math.abs(startX - newX) != 0) && (Math.abs(startY - newY) == 0)) || ((Math.abs(startX - newX) == 0) && (Math.abs(newY - newY) != 0))) { //startx minus the position of x is not equal to 0 AND the same for starty, OR startX minus position of X is equal to 0
                if (Math.abs(startX - newX) != 0) {
                    if (startX - newX > 0) {
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent(initialX - (i * 75), e.getY())) {
                                intheway = true;
                                break;
                            }//end if piece present
                            else {
                                intheway = false;
                            }
                        }//end for loop
                    }//end if
                    else {
                        for (int i = 0; i < xMovement; i++) {
                            if (piecePresent(initialX + (i * 75), e.getY())) {
                                intheway = true;
                                break;
                            }//end if piece present
                            else {
                                intheway = false;
                            }//end else
                        }//end for loop
                    }//end else
                }//end math.abs if
                else {
                    if (startY - newY > 0) {
                        for (int i = 0; i < yMovement; i++) {
                            if (piecePresent(e.getX(), initialY - (i * 75))) {
                                intheway = true;
                                break;
                            }//end if piece present
                            else {
                                intheway = false;
                            }
                        }//end for loop
                    }//end if
                    else {
                        for (int i = 0; i < yMovement; i++) {
                            if (piecePresent(e.getX(), initialX + (i * 75))) {
                                intheway = true;
                                break;
                            }//end if piece present
                            else {
                                intheway = false;
                            }//end else
                        }//end for loop
                    }//end else
                }//end else
                if (intheway) {
                    validMove = false;
                }//end if intheway
                else {
                    if (piecePresent(e.getX(), e.getY())) {
                        if (pieceName.contains("White")) {
                            if (checkWhiteOponent(e.getX(), e.getY())) {
                                validMove = true;
                            }//end check white opponent
                            else {
                                validMove = false;
                            }//end valid move = false
                        }//end if piece.contains
                        else {
                            if (checkBlackOponent(e.getX(), e.getY())) {
                                validMove = true;
                            }//end check black opponent
                            else {
                                validMove = false;
                            }//end valid move = false
                        }//end else
                    }//end if piece is present
                    else {
                        validMove = true;
                    }//end valid move = true
                }//end  else
            }//end math.abs
            else {
                validMove = false;
            }//end valid move = false
        }//end main else for rook
    }//end rook method

    private void KnightMethod(MouseEvent e) {//knight method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("--------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The starting coordinates are : " + "( " + newX + "," + newY + ")");
        System.out.println("--------------------------------");
        if (((newX < 0) || (newX > 7)) || ((newY < 0) || newY > 7)) {
            validMove = false;
        } else {
            if (((newX == startX + 1) && (newY == startY + 2))
                    || ((newX == startX - 1) && (newY == startY + 2))
                    || ((newX == startX + 2) && (newY == startY + 1))
                    || ((newX == startX - 2) && (newY == startY + 1))
                    || ((newX == startX + 1) && (newY == startY - 2))
                    || ((newX == startX - 1) && (newY == startY - 2))
                    || ((newX == startX + 2) && (newY == startY - 1))
                    || ((newX == startX - 2) && (newY == startY - 1))) {

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
        }//end else
    }//end knight method

    private void BishopMethod(MouseEvent e) {//bishop method
        int newX = (e.getX() / 75); //position of piece after moved on X axis
        int newY = (e.getY() / 75); //position of piece after moved on Y axis
        int xMovement = Math.abs((e.getX() / 75) - startX); //number of moves made on the x axis
        int yMovement = Math.abs((e.getY() / 75) - startY); //number of moves made on the y axis
        System.out.println("--------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The new coordinates are : " + "( " + newX + "," + newY + ")");
        System.out.println("--------------------------------");
        Boolean inTheWay = false;
        int distance = (startX - newX); //distance is equal to the starting coordinate of X minus the coordinate of where piece was moved to on X axis

        if (((newX < 0) || (newX > 7)) || ((newY < 0) || (newY > 7))) { // if the piece is is not on the board
            validMove = false; //dont allow a move
        }//end if
        else { //otherwise
            validMove = true; //allow a move

            for(int i=0; i<distance; i++){

            }
        }//end else
    }//end bishop method

    private void QueenMethod(MouseEvent e) {//queen method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("--------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The starting coordinates are : " + "( " + newX + "," + newY + ")");
        System.out.println("--------------------------------");
    }//end queen method

    private void KingMethod(MouseEvent e) {//king method
        int newX = (e.getX() / 75);
        int newY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);
        System.out.println("--------------------------------");
        System.out.println("The piece that is being moved is : " + pieceName);
        System.out.println("The starting coordinates are : " + "( " + startX + "," + startY + ")");
        System.out.println("The xMovement is : " + xMovement);
        System.out.println("The yMovement is : " + yMovement);
        System.out.println("The starting coordinates are : " + "( " + newX + "," + newY + ")");
        System.out.println("--------------------------------");
    }//end king method


    public void mouseClicked(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /*
        Main method that gets the ball moving.
    */
    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}


