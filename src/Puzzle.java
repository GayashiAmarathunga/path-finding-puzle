import java.util.LinkedList;
import java.util.ArrayList;

public class Puzzle {

    private ArrayList<Point> positions = new ArrayList<>(); //Creating arraylist named positions of type point

    public void solve(String[][] iceCave, int startX, int startY, int endX, int endY) {  //Method to co-ordinate the traversing of the parser

        Point position = new Point(startX, startY);
        LinkedList<Point> node = new LinkedList<>();
        Point[][] CaveLengths = new Point[iceCave.length][iceCave[0].length];

        node.addLast(position);
        CaveLengths[startY][startX] = position;

        boolean validator = true;

        while (node.size() != 0 && validator) {

            Point currPos = node.pollFirst();

            for (Direction dir : Direction.values()) {

                Point nextPos = move(iceCave, CaveLengths, currPos, dir, endX, endY); //Moving the parser to the next position.

                if (nextPos != null) {  //Validate the next Point to be not null (Previously un-accessed point by the parser)

                    node.addLast(nextPos);  //Updating the LinkedList by adding the moved position as the last element of it.
                    CaveLengths[nextPos.getY()][nextPos.getX()] = new Point(currPos.getX(), currPos.getY());  //Updating the array indexing of the point

                    if (nextPos.getY() == endY && nextPos.getX() == endX) {  //Check whether the moved position is the ending position.
                        // if yes, we found the end point

                        Point tmp = nextPos;  // if we start from nextPos we will count one too many edges
                        //the variable "tmp" stores the current position of the parser.

                        while (tmp != position) {  // While new current position is not the starting position.
                            tmp = CaveLengths[tmp.getY()][tmp.getX()]; //Then set the updated Cave length
                            positions.add(0, tmp);  //Add the point to 0th index of the Arraylist
                        }
                        validator = false;
                    }
                }
            }
        }

        positions.add(new Point(endX, endY));  //Add the ending position of the current traverse to the Arraylist

        for(int i = 0; i<positions.size(); i++) {

            if (i == 0) {  //Print the starting position which contained by the first index of the array
                System.out.println("Started At " + positions.get(i));
            } else {
                if (positions.get(i).getX() > positions.get(i - 1).getX()) {
                    System.out.println("Move RIGHT to " + positions.get(i));
                } else if (positions.get(i).getX() < positions.get(i - 1).getX()) {
                    System.out.println("Move LEFT  to " + positions.get(i));
                } else if (positions.get(i).getY() > positions.get(i - 1).getY()) {
                    System.out.println("Move DOWN  to " + positions.get(i));
                } else if (positions.get(i).getY() < positions.get(i - 1).getY()) {
                    System.out.println("Move UP    to " + positions.get(i));
                }
            }
        }
        System.out.println("Done!");  //Printing moving pathway step by step
    }

    public Point move (String[][]iceCave, Point[][]CaveLengths, Point currPos, Direction dir, int end_A, int end_B) {

        int x = currPos.getX();
        int y = currPos.getY();

        int diffX;  //difference between the X co-ordinates moved
        int diffY;  //difference between the Y co-ordinates moved

        //Conditions for traversing via the X axis
        if (dir == Direction.LEFT) {
            diffX = -1;
        }
        else if (dir == Direction.RIGHT) {
            diffX = 1;
        }
        else {
            diffX = 0;
        }

        //Conditions for traversing via the X axis
        if (dir == Direction.UP) {
            diffY = -1;
        }
        else if (dir == Direction.DOWN) {
            diffY = 1;
        }
        else {
            diffY = 0;
        }

        //Conditions needed to be satisfied to have a need
        int i = 1;
        while (x + i * diffX >= 0
                && x + i * diffX < iceCave[0].length
                && y + i * diffY >= 0
                && y + i * diffY < iceCave.length
                && !(iceCave[y + i * diffY][x + i * diffX]).equals("0")) {
            i++;

            if(iceCave[y + (i-1) * diffY][x + (i-1) * diffX].equals("F")) {
                return new Point(end_A,end_B);  //When found "F" return its co-ordinates as the end point
            }
        }
        i--;  // reverse the last step

        if (CaveLengths[y + i * diffY][x + i * diffX] != null) {
            // we've already seen this point
            return null;
        }
        return new Point(x + i * diffX, y + i * diffY);  //returning the new co-ordinates of the point
    }

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN
    }
}
