/**
 * 
 */
package vorobyova.trianglesanalysis;

import java.util.Arrays;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * @author Asya
 *
 *         This class gets three double numbers as input and checks if these
 *         numbers correspond to sides of equilateral, isosceles or scalene
 *         triangle.
 */
public class TrianglesAnalysis {

    /**
     * This method calculates if sides array form a triangle and calculates a
     * type of this triangle (equilateral, isosceles or scalene)
     * 
     * @param sides
     * @return
     * @throws NoTriangleException
     */
    public TriangleType solveTriangle(double[] sides) throws NoTriangleException {
        // check that exactly three sides
        if (sides.length != 3)
            throw new NoTriangleException(EBadTriangle.SIDES_NUMBER, new double[] {});
        // check that all sides are positive
        double[] nonPositive = Arrays.stream(sides).filter(s -> (s <= 0)).toArray();
        if (nonPositive.length > 0)
            throw new NoTriangleException(EBadTriangle.SIDE_NOT_POSITIVE, nonPositive);
        // check triangle rule that sum of every two sides must be greater than
        // the third one
        double[] smallSides = IntStream.range(0, 3).filter(i -> (sides[i] >= sides[(i + 1) % 3] + sides[(i + 2) % 3]))
                .mapToDouble(i -> sides[i]).toArray();
        if (smallSides.length > 0)
            throw new NoTriangleException(EBadTriangle.WRONG_TRIANGLE_RULE, smallSides);

        // find number of distinct size to know triangle type
        DoubleStream distinctSides = Arrays.stream(sides).distinct();
        switch ((int) distinctSides.count()) {
        case 1:
            return TriangleType.EQUILATERAL;
        case 2:
            return TriangleType.ISOSCELES;
        default:
            return TriangleType.SCALENE;
        }
    }
}
