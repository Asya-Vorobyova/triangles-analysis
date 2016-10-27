package vorobyova.trianglesanalysis;

public enum EBadTriangle {
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