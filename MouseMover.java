import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

public class MouseMover {
    public static final int FIVE_SECONDS = 10000;
    public static final int THRESHOLD = 10; // Minimum movement to detect manual control

    public static void main(String... args) throws Exception {
        Robot robot = new Robot();
        
        Point lastMouseLocation = MouseInfo.getPointerInfo().getLocation();
        while (true) {
            Point currentMouseLocation = MouseInfo.getPointerInfo().getLocation();
            if (isMouseMoved(lastMouseLocation, currentMouseLocation)) {
                System.out.println("Mouse moved manually, skipping automatic movement.");
            } else {
                moveMouseByOnePixel(robot, currentMouseLocation);
            }
            lastMouseLocation = currentMouseLocation;
            Thread.sleep(FIVE_SECONDS);
        }
    }

    private static boolean isMouseMoved(Point lastLocation, Point currentLocation) {
        int deltaX = Math.abs(currentLocation.x - lastLocation.x);
        int deltaY = Math.abs(currentLocation.y - lastLocation.y);
        return deltaX > THRESHOLD || deltaY > THRESHOLD;
    }

    private static void moveMouseByOnePixel(Robot robot, Point currentLocation) {
        int newX = currentLocation.x + 1;
        int newY = currentLocation.y;
        robot.mouseMove(newX, newY);
    }
}
