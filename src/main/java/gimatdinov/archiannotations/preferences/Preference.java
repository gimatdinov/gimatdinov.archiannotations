package gimatdinov.archiannotations.preferences;

import org.eclipse.jface.preference.IPreferenceStore;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class Preference implements IPreferenceConstants {

	public static IPreferenceStore STORE = ArchiAnnotationsPlugin.INSTANCE.getPreferenceStore();

	public static boolean isAnnotationsVisible() {
		return STORE.getBoolean(IPreferenceConstants.ANNOTATIONS_VISIBLE);
	}

	public static String getAnnotationPropertyKey() {
		return STORE.getString(IPreferenceConstants.ANNOTATION_PROPERTY_KEY);
	}

	public static String getAnnotationDisplayPrefix() {
		return STORE.getString(IPreferenceConstants.ANNOTATION_DISPLAY_PREFIX);
	}

	public static String getAnnotationDisplayPostfix() {
		return STORE.getString(IPreferenceConstants.ANNOTATION_DISPLAY_POSTFIX);
	}

	public static boolean isStereotypesVisible() {
		return STORE.getBoolean(IPreferenceConstants.STEREOTYPES_VISIBLE);
	}

	public static String getStereotypePropertyKey() {
		return STORE.getString(IPreferenceConstants.STEREOTYPE_PROPERTY_KEY);
	}

	public static String getStereotypeDisplayPrefix() {
		return STORE.getString(IPreferenceConstants.STEREOTYPE_DISPLAY_PREFIX);
	}

	public static String getStereotypeDisplayPostfix() {
		return STORE.getString(IPreferenceConstants.STEREOTYPE_DISPLAY_POSTFIX);
	}

	public static boolean isAttributesVisible() {
		return STORE.getBoolean(IPreferenceConstants.ATTRIBUTES_VISIBLE);
	}

	public static String getAttributePropertyKey() {
		return STORE.getString(IPreferenceConstants.ATTRIBUTE_PROPERTY_KEY);
	}

	public static String getAttributeDisplayPrefix() {
		return STORE.getString(IPreferenceConstants.ATTRIBUTE_DISPLAY_PREFIX);
	}

	public static String getAttributeDisplayPostfix() {
		return STORE.getString(IPreferenceConstants.ATTRIBUTE_DISPLAY_POSTFIX);
	}

}
