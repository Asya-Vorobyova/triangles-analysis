/**
 * 
 */
package vorobyova.trianglesanalysis;

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
     * This method calculates if sides array form a triangle and calculates a
     * type of this triangle (equilateral, isosceles or scalene)
     * 
     * @param sides
     * @return
     * @throws NoTriangleException
     */
    public TriangleType solveTriangle(Double[] sides) throws NoTriangleException {
        // check that exactly three sides
        if (sides.length != 3)
            throw new NoTriangleException(EBadTriangle.SIDES_NUMBER, new Double[] {});
        // check that all sides are positive
        Double[] nonPositive = Arrays.stream(sides).filter(s -> (s <= 0)).toArray(Double[]::new);
        if (nonPositive.length > 0)
            throw new NoTriangleException(EBadTriangle.SIDE_NOT_POSITIVE, nonPositive);
        // check triangle rule that sum of every two sides must be greater than
        // the third one
        Double[] smallSides = IntStream.range(0, 3).filter(i -> (sides[i] >= sides[(i + 1) % 3] + sides[(i + 2) % 3]))
                .mapToObj(i -> ((Double) sides[i])).toArray(Double[]::new);
        if (smallSides.length > 0)
            throw new NoTriangleException(EBadTriangle.WRONG_TRIANGLE_RULE, smallSides);

        // find number of distinct size to know triangle type
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

}
