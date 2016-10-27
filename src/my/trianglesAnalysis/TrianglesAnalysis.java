/**
 * 
 */
package my.trianglesAnalysis;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author Asya
 *
 *         This class gets three double numbers as input and checks if these
 *         numbers correspond to sides of equilateral, isosceles or scalene
 *         triangle.
 */
public class TrianglesAnalysis {

    /**
     *  Possible triangle types
     */
    public enum TriangleType {
        EQUILATERAL,
        ISOSCELES,
        SCALENE;
    }
    
    private enum EBadTriangle {
        SIDES_NUMBER("There must be exactly three sides"),
        SIDE_NOT_POSITIVE("Each side must be positive"),
        WRONG_TRIANGLE_RULE("Any side must be less than a sum of two others");
        
        private String reason;

        private EBadTriangle(String reason) {
            this.reason = reason;
        }

        public String getReason() {
            return reason;
        }
        
    }
    
    /**
     *  Exception thrown in a case of incorrect input data
     *
     */
    @SuppressWarnings("serial")
    public class NoTriangleException extends Exception {
        private EBadTriangle code;

        public NoTriangleException(EBadTriangle code) {
            this.code = code;
        }

        public String getName() {
            return code.name();
        }
        
        public String getMessage() {
            return code.getReason();
        }
        
    }
    
    /**
     * This method calculates if sides array form a triangle and calculates 
     * a type of this triangle (equilateral, isosceles or scalene)
     * 
     * @param sides
     * @return
     * @throws NoTriangleException
     */
    public TriangleType solveTriangle(Double[] sides) throws NoTriangleException {
        //check that exactly three sides
        if (sides.length != 3)
            throw new NoTriangleException(EBadTriangle.SIDES_NUMBER);
        //check that all sides are positive
        if (!Arrays.stream(sides).allMatch(s -> (s > 0)))
            throw new NoTriangleException(EBadTriangle.SIDE_NOT_POSITIVE);
        //check triangle rule that sum of every two sides must be greater than the third one
        if (!IntStream.range(0, 3).allMatch(i -> (sides[i] < sides[(i + 1) % 3] + sides[(i + 2) % 3])))
            throw new NoTriangleException(EBadTriangle.WRONG_TRIANGLE_RULE);
        
        //find number of distinct size to know triangle type
        Stream<Double> distinctSides = Arrays.stream(sides).distinct();
        switch ((int) distinctSides.count()) {
        case 1:
            return TriangleType.EQUILATERAL;
        case 2:
            return TriangleType.ISOSCELES;
        default:
            return TriangleType.SCALENE;
        }
    }
    
    /**
     * @param args
     * @throws NoTriangleException 
     */
    public static void main(String[] args) {
        try {
            Double[] sides = Arrays.stream(args).map(arg -> Double.parseDouble(arg)).toArray(Double[]::new);
            TrianglesAnalysis analysis = new TrianglesAnalysis();
            System.out.println("A triangle is exists and " + analysis.solveTriangle(sides).name().toLowerCase()); 
        } catch (NumberFormatException e) {
            System.out.println("It seems that arguments are invalid and cannot be converted to double");
        } catch (NoTriangleException e1) {
            System.out.println(e1.getMessage());
        }
    }

}
