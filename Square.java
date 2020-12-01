//initialize the getters and setters for an x-coordinate, a y-coordinate and the name of a piece if present on a square
class Square {
    public int xCoor;
    public int yCoor;
    public String pieceName;

    public Square(int x, int y, String name) {
        xCoor = x;
        yCoor = y;
        pieceName = name;
    }

    public Square(int x, int y) {
        xCoor = x;
        yCoor = y;
        pieceName = "";
    }



    public int getXC() {
        return xCoor;
    }

    public int getYC() {
        return yCoor;
    }

    public String getName() {
        return pieceName;
    }


}//end class square