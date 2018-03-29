package gimatdinov.archiannotations.preferences;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class PreferenceInitializer extends AbstractPreferenceInitializer implements IPreferenceConstants {

	@Override
	public void initializeDefaultPreferences() {
		IPreferenceStore store = ArchiAnnotationsPlugin.getDefault().getPreferenceStore();

		store.setDefault(ANNOTATIONS_VISIBLE, true);
		store.setDefault(ANNOTATION_PROPERTY_KEY, "@");
		store.setDefault(ANNOTATION_DISPLAY_PREFIX, "@");
		store.setDefault(ANNOTATION_DISPLAY_POSTFIX, "");

		store.setDefault(STEREOTYPES_VISIBLE, true);
		store.setDefault(STEREOTYPE_PROPERTY_KEY, "$");
		store.setDefault(STEREOTYPE_DISPLAY_PREFIX, "«");
		store.setDefault(STEREOTYPE_DISPLAY_POSTFIX, "»");

		store.setDefault(ATTRIBUTES_VISIBLE, true);
		store.setDefault(ATTRIBUTE_PROPERTY_KEY, "#");
		store.setDefault(ATTRIBUTE_DISPLAY_PREFIX, "[");
		store.setDefault(ATTRIBUTE_DISPLAY_POSTFIX, "]");

		store.setDefault(EXTRA_CONNECTION_LABEL_LOCATOR_ENABLE, true);
	}

}
