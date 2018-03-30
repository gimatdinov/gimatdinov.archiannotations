package gimatdinov.archiannotations.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class Preference implements IPreferenceConstants {

    private static IPreferenceStore getStore() {
        return ArchiAnnotationsPlugin.getDefault().getPreferenceStore();
    }

    public static boolean isAnnotationsVisible() {
        return getStore().getBoolean(IPreferenceConstants.ANNOTATIONS_VISIBLE);
    }

    public static String getAnnotationPropertyKey() {
        return getStore().getString(IPreferenceConstants.ANNOTATION_PROPERTY_KEY);
    }

    public static String getAnnotationDisplayPrefix() {
        return getStore().getString(IPreferenceConstants.ANNOTATION_DISPLAY_PREFIX);
    }

    public static String getAnnotationDisplayPostfix() {
        return getStore().getString(IPreferenceConstants.ANNOTATION_DISPLAY_POSTFIX);
    }

    public static boolean isStereotypesVisible() {
        return getStore().getBoolean(IPreferenceConstants.STEREOTYPES_VISIBLE);
    }

    public static String getStereotypePropertyKey() {
        return getStore().getString(IPreferenceConstants.STEREOTYPE_PROPERTY_KEY);
    }

    public static String getStereotypeDisplayPrefix() {
        return getStore().getString(IPreferenceConstants.STEREOTYPE_DISPLAY_PREFIX);
    }

    public static String getStereotypeDisplayPostfix() {
        return getStore().getString(IPreferenceConstants.STEREOTYPE_DISPLAY_POSTFIX);
    }

    public static boolean isAttributesVisible() {
        return getStore().getBoolean(IPreferenceConstants.ATTRIBUTES_VISIBLE);
    }

    public static String getAttributePropertyKey() {
        return getStore().getString(IPreferenceConstants.ATTRIBUTE_PROPERTY_KEY);
    }

    public static String getAttributeDisplayPrefix() {
        return getStore().getString(IPreferenceConstants.ATTRIBUTE_DISPLAY_PREFIX);
    }

    public static String getAttributeDisplayPostfix() {
        return getStore().getString(IPreferenceConstants.ATTRIBUTE_DISPLAY_POSTFIX);
    }

    public static boolean isExtraConnectionLabelLocatorEnable() {
        return getStore().getBoolean(IPreferenceConstants.EXTRA_CONNECTION_LABEL_LOCATOR_ENABLE);
    }

}
