package gimatdinov.archiannotations.preferences;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {

    private static final String BUNDLE_NAME = "gimatdinov.archiannotations.preferences.messages";

    public static String Stereotypes;
    public static String StereotypesVisible;
    public static String StereotypesUsageDescription;

    public static String Annotations;
    public static String AnnotationsVisible;
    public static String AnnotationsUsageDescription;

    public static String Attributes;
    public static String AttributesVisible;
    public static String AttributesUsageDescription;

    public static String ConnectionLabelLocation;
    public static String ConnectionLabelLocation_TuneMiddle;

    static {
        NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}
