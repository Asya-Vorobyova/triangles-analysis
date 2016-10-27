/**
 * 
 */
package trianglesAnalysis;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import my.trianglesAnalysis.TrianglesAnalysis;
import my.trianglesAnalysis.TrianglesAnalysis.NoTriangleException;

/**
 * @author Asya
 *
 * Test for {@link my.trianglesAnalysis.TrianglesAnalysis} class
 */
public class TrianglesAnalysisTest {

    private TrianglesAnalysis analysis;
    
    @Before
    public void setUp() throws Exception {
       analysis = new TrianglesAnalysis();
    }
    
    /**
     * Test method for {@link my.trianglesAnalysis.TrianglesAnalysis#solveTriangle(java.lang.Double[])}.
     * Checks that sides number are incorrect.
     */
    @Test
    public void testSidesNumberFail() {
        Double[] sides = new Double[]{ 1d, 2d, 3d, 4d };
        try {
            analysis.solveTriangle(sides);
            fail("NoTriangleException was not occured!");
        } catch (NoTriangleException e) {
            assertEquals(e.getName(), "SIDES_NUMBER");
        }
    }

    /**
     * Test method for {@link my.trianglesAnalysis.TrianglesAnalysis#solveTriangle(java.lang.Double[])}.
     * Checks that some side isn't positive.
     */
    @Test
    public void testSidesNotPositiveFail() {
        Double[] sides = new Double[]{ -1d, 2d, 3d };
        try {
            analysis.solveTriangle(sides);
            fail("NoTriangleException was not occured!");
        } catch (NoTriangleException e) {
            assertEquals(e.getName(), "SIDE_NOT_POSITIVE");
        }
    }

    /**
     * Test method for {@link my.trianglesAnalysis.TrianglesAnalysis#solveTriangle(java.lang.Double[])}.
     * Checks that some side isn't greater than a sum of two others.
     */
    @Test
    public void testTriangleRuleFail() {
        Double[] sides = new Double[]{ 1d, 2d, 4d };
        try {
            analysis.solveTriangle(sides);
            fail("NoTriangleException was not occured!");
        } catch (NoTriangleException e) {
            assertEquals(e.getName(), "WRONG_TRIANGLE_RULE");
        }
    }

    /**
     * Test method for {@link my.trianglesAnalysis.TrianglesAnalysis#solveTriangle(java.lang.Double[])}.
     * Checks the case of equilateral triangle.
     */
    @Test
    public void testEquilateralTriangle() {
        Double[] sides = new Double[]{ 1d, 1d, 1d };
        try {
            assertEquals(analysis.solveTriangle(sides).name(), "EQUILATERAL");
        } catch (NoTriangleException e) {
            fail("NoTriangleException was occured");
        }
    }

    /**
     * Test method for {@link my.trianglesAnalysis.TrianglesAnalysis#solveTriangle(java.lang.Double[])}.
     * Checks the case of isosceles triangle.
     */
    @Test
    public void testIsoscelesTriangle() {
        Double[] sides = new Double[]{ 1d, 1d, 0.5d };
        try {
            assertEquals(analysis.solveTriangle(sides).name(), "ISOSCELES");
        } catch (NoTriangleException e) {
            fail("NoTriangleException was occured");
        }
    }

    /**
     * Test method for {@link my.trianglesAnalysis.TrianglesAnalysis#solveTriangle(java.lang.Double[])}.
     * Checks the case of scalene triangle.
     */
    @Test
    public void testScaleneTriangle() {
        Double[] sides = new Double[]{ 3d, 4d, 5d };
        try {
            assertEquals(analysis.solveTriangle(sides).name(), "SCALENE");
        } catch (NoTriangleException e) {
            fail("NoTriangleException was occured");
        }
    }

}
