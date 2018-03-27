package gimatdinov.archiannotations.preferences;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class ArchiAnnotationsPreferencesPage extends PreferencePage
		implements IWorkbenchPreferencePage, IPreferenceConstants {

	public static String ID = "gimatdinov.archiannotations.preferences.ArchiAnnotationsPreferencesPage";

	private Button stereotypesVisibleButton;
	private Button annotationsVisibleButton;
	private Button attributesVisibleButton;

	public ArchiAnnotationsPreferencesPage() {
		setPreferenceStore(ArchiAnnotationsPlugin.INSTANCE.getPreferenceStore());
	}

	@Override
	protected Control createContents(Composite parent) {
		Composite client = new Composite(parent, SWT.NULL);
		client.setLayout(new GridLayout());

		GridData gd;

		Group stereotypesGroup = new Group(client, SWT.NULL);
		stereotypesGroup.setText(Messages.Stereotypes);
		stereotypesGroup.setLayout(new GridLayout(1, false));
		stereotypesGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		stereotypesVisibleButton = new Button(stereotypesGroup, SWT.CHECK);
		stereotypesVisibleButton.setText(Messages.StereotypesVisible);
		stereotypesVisibleButton.setLayoutData(gd);
		Label stereotypesLabel = new Label(stereotypesGroup, SWT.NULL);
		stereotypesLabel.setText(Messages.StereotypesUsageDescription);

		Group annotationsGroup = new Group(client, SWT.NULL);
		annotationsGroup.setText(Messages.Annotations);
		annotationsGroup.setLayout(new GridLayout(1, false));
		annotationsGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		annotationsVisibleButton = new Button(annotationsGroup, SWT.CHECK);
		annotationsVisibleButton.setText(Messages.AnnotationsVisible);
		annotationsVisibleButton.setLayoutData(gd);
		Label annotationsLabel = new Label(annotationsGroup, SWT.NULL);
		annotationsLabel.setText(Messages.AnnotationsUsageDescription);

		Group attributesGroup = new Group(client, SWT.NULL);
		attributesGroup.setText(Messages.Attributes);
		attributesGroup.setLayout(new GridLayout(1, false));
		attributesGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		attributesVisibleButton = new Button(attributesGroup, SWT.CHECK);
		attributesVisibleButton.setText(Messages.AttributesVisible);
		attributesVisibleButton.setLayoutData(gd);
		Label attributesLabel = new Label(attributesGroup, SWT.NULL);
		attributesLabel.setText(Messages.AttributesUsageDescription);

		setValues();
		return client;
	}

	private void setValues() {
		stereotypesVisibleButton.setSelection(getPreferenceStore().getBoolean(STEREOTYPES_VISIBLE));
		annotationsVisibleButton.setSelection(getPreferenceStore().getBoolean(ANNOTATIONS_VISIBLE));
		attributesVisibleButton.setSelection(getPreferenceStore().getBoolean(ATTRIBUTES_VISIBLE));
	}

	@Override
	public boolean performOk() {
		getPreferenceStore().setValue(STEREOTYPES_VISIBLE, stereotypesVisibleButton.getSelection());
		getPreferenceStore().setValue(ANNOTATIONS_VISIBLE, annotationsVisibleButton.getSelection());
		getPreferenceStore().setValue(ATTRIBUTES_VISIBLE, attributesVisibleButton.getSelection());
		return true;
	}

	@Override
	protected void performDefaults() {
		stereotypesVisibleButton.setSelection(getPreferenceStore().getDefaultBoolean(STEREOTYPES_VISIBLE));
		annotationsVisibleButton.setSelection(getPreferenceStore().getDefaultBoolean(ANNOTATIONS_VISIBLE));
		attributesVisibleButton.setSelection(getPreferenceStore().getDefaultBoolean(ATTRIBUTES_VISIBLE));
		super.performDefaults();
	}

	@Override
	public void init(IWorkbench workbench) {
	}

}