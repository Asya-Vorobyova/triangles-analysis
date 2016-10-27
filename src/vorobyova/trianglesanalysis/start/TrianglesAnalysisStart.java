/**
 * 
 */
package vorobyova.trianglesanalysis.start;

import java.util.Arrays;

import vorobyova.trianglesanalysis.NoTriangleException;
import vorobyova.trianglesanalysis.TrianglesAnalysis;

/**
 * @author Asya
 *
 */
public class TrianglesAnalysisStart {
    
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
