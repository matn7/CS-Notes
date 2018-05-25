package algorithms.other_algorithms.point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mati on 29.07.2017.
 */
public class Punkt {

    public static class Point {
        private int x;
        private int y;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public double getDistance(Point otherPoint) {
            return Math.sqrt(Math.pow(otherPoint.x - x, 2) + Math.pow(otherPoint.y - y, 2));
        }

        public boolean isWithinDistance(Point otherPoint, double distance) {
            if (Math.abs(otherPoint.x - x) > distance || (otherPoint.y - y) > distance) {
                return false;
            }
            return getDistance(otherPoint) <= distance;
        }
    }

    public static List<Point> getPointsWithDistance(List<Point> list, Point point, double distance) {
        List<Point> withinDistanceList = new ArrayList<>();
        for (Point p : list) {
            if (p.isWithinDistance(point, distance)) {
                withinDistanceList.add(p);
            }
        }
        System.out.println(String.format("Points within %s of point x = %s, y = %s", distance, point.getX(), point.getY()));
        for (Point p : withinDistanceList) {
            System.out.println(String.format("Point: x=%s, y=%s", p.getX(), p.getY()));
        }
        return withinDistanceList;
    }

}
