import java.util.Arrays;
import java.util.Scanner;

public class TriangleClassifier {
    private static final double EPS = 1e-9;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter three side lengths (separated by spaces): ");
        try {
            double a = sc.nextDouble();
            double b = sc.nextDouble();
            double c = sc.nextDouble();

            if (!isValidTriangle(a, b, c)) {
                System.out.println("Not a valid triangle (check side lengths and triangle inequality).");
                return;
            }

            String sidesType = classifyBySides(a, b, c);
            String anglesType = classifyByAngles(a, b, c);

            System.out.println("Type by sides: " + sidesType);
            System.out.println("Type by angles: " + anglesType);
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter three numeric values, e.g., 3 4 5");
        } finally {
            sc.close();
        }
    }

    // Triangle inequality + positive sides
    private static boolean isValidTriangle(double a, double b, double c) {
        return a > 0 && b > 0 && c > 0 &&
               (a + b > c) && (a + c > b) && (b + c > a);
    }

    private static boolean eq(double x, double y) {
        return Math.abs(x - y) <= EPS;
    }

    private static String classifyBySides(double a, double b, double c) {
        if (eq(a, b) && eq(b, c)) {
            return "equilateral";
        } else if (eq(a, b) || eq(a, c) || eq(b, c)) {
            return "isosceles";
        } else {
            return "scalene";
        }
    }

    private static String classifyByAngles(double a, double b, double c) {
        // Sort so the largest side is last, required for Pythagorean comparison
        double[] sides = new double[] { a, b, c };
        Arrays.sort(sides);
        double x = sides[0], y = sides[1], z = sides[2]; // z is largest
        double lhs = x * x + y * y;
        double rhs = z * z;

        if (Math.abs(lhs - rhs) <= EPS) {
            return "right";
        } else if (lhs > rhs) {
            return "acute";
        } else {
            return "obtuse";
        }
    }
}