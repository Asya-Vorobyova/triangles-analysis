package vorobyova.trianglesanalysis;

import java.util.Arrays;

/**
 *  Exception thrown in a case of incorrect input data
 *
 */
@SuppressWarnings("serial")
public class NoTriangleException extends Exception {
    private EBadTriangle code;

    private double[] badSides;
    
    public NoTriangleException(EBadTriangle code, double[] badSides) {
        this.code = code;
        this.badSides = badSides;
    }

    public String getName() {
        return code.name();
    }
    
    public String getMessage() {
        return code.getReason() + " : problem with side length(s) " + Arrays.toString(badSides);
    }
    
}