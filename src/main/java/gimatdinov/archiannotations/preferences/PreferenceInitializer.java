package gimatdinov.archiannotations.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class PreferenceInitializer extends AbstractPreferenceInitializer implements IPreferenceConstants {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = ArchiAnnotationsPlugin.INSTANCE.getPreferenceStore();

		store.setDefault(IPreferenceConstants.ANNOTATIONS_VISIBLE, true);
		store.setDefault(IPreferenceConstants.ANNOTATION_PROPERTY_KEY, "@");
		store.setDefault(IPreferenceConstants.ANNOTATION_DISPLAY_PREFIX, "@");
		store.setDefault(IPreferenceConstants.ANNOTATION_DISPLAY_POSTFIX, "");

		store.setDefault(IPreferenceConstants.STEREOTYPES_VISIBLE, true);
		store.setDefault(IPreferenceConstants.STEREOTYPE_PROPERTY_KEY, "$");
		store.setDefault(IPreferenceConstants.STEREOTYPE_DISPLAY_PREFIX, "«");
		store.setDefault(IPreferenceConstants.STEREOTYPE_DISPLAY_POSTFIX, "»");

		store.setDefault(IPreferenceConstants.ATTRIBUTES_VISIBLE, true);
		store.setDefault(IPreferenceConstants.ATTRIBUTE_PROPERTY_KEY, "#");
		store.setDefault(IPreferenceConstants.ATTRIBUTE_DISPLAY_PREFIX, "[");
		store.setDefault(IPreferenceConstants.ATTRIBUTE_DISPLAY_POSTFIX, "]");
	}

}
