package gimatdinov.archiannotations.preferences;

import java.text.MessageFormat;

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
import gimatdinov.archiannotations.Logger;

public class ArchiAnnotationsPreferencesPage extends PreferencePage
        implements IWorkbenchPreferencePage, IPreferenceConstants {

    public static final String ID = "gimatdinov.archiannotations.preferences.ArchiAnnotationsPreferencesPage";

    private Button stereotypesVisibleButton;
    private Button annotationsVisibleButton;
    private Button attributesVisibleButton;
    private Button extraConnectionLabelLocatorEnableButton;
    private Button loggerEnableButton;

    public ArchiAnnotationsPreferencesPage() {
        setPreferenceStore(ArchiAnnotationsPlugin.getInstance().getPreferenceStore());
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
        stereotypesLabel.setText(MessageFormat.format(Messages.StereotypesUsageDescription,
                getPreferenceStore().getString(STEREOTYPE_PROPERTY_KEY_PREFIX),
                getPreferenceStore().getString(STEREOTYPE_DISPLAY_PREFIX),
                getPreferenceStore().getString(STEREOTYPE_DISPLAY_POSTFIX)));

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
        annotationsLabel.setText(MessageFormat.format(Messages.AnnotationsUsageDescription,
                getPreferenceStore().getString(ANNOTATION_PROPERTY_KEY_PREFIX),
                getPreferenceStore().getString(ANNOTATION_DISPLAY_PREFIX),
                getPreferenceStore().getString(ANNOTATION_DISPLAY_POSTFIX)));

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
        attributesLabel.setText(MessageFormat.format(Messages.AttributesUsageDescription,
                getPreferenceStore().getString(ATTRIBUTE_PROPERTY_KEY_PREFIX),
                getPreferenceStore().getString(ATTRIBUTE_DISPLAY_PREFIX),
                getPreferenceStore().getString(ATTRIBUTE_DISPLAY_POSTFIX)));

        Group connectionLabelGroup = new Group(client, SWT.NULL);
        connectionLabelGroup.setText(Messages.ConnectionLabelLocation);
        connectionLabelGroup.setLayout(new GridLayout(1, false));
        connectionLabelGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        extraConnectionLabelLocatorEnableButton = new Button(connectionLabelGroup, SWT.CHECK);
        extraConnectionLabelLocatorEnableButton.setText(Messages.ConnectionLabelLocation_TuneMiddle);
        extraConnectionLabelLocatorEnableButton.setLayoutData(gd);

        Group debugGroup = new Group(client, SWT.NULL);
        debugGroup.setText(Messages.Debug);
        debugGroup.setLayout(new GridLayout(1, false));
        debugGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        gd = new GridData(GridData.FILL_HORIZONTAL);
        gd.horizontalSpan = 2;
        loggerEnableButton = new Button(debugGroup, SWT.CHECK);
        loggerEnableButton.setText(Messages.LoggerEnable);
        loggerEnableButton.setLayoutData(gd);

        setValues();
        return client;
    }

    private void setValues() {
        stereotypesVisibleButton.setSelection(getPreferenceStore().getBoolean(STEREOTYPES_VISIBLE));
        annotationsVisibleButton.setSelection(getPreferenceStore().getBoolean(ANNOTATIONS_VISIBLE));
        attributesVisibleButton.setSelection(getPreferenceStore().getBoolean(ATTRIBUTES_VISIBLE));
        extraConnectionLabelLocatorEnableButton
                .setSelection(getPreferenceStore().getBoolean(EXTRA_CONNECTION_LABEL_LOCATOR_ENABLE));
        loggerEnableButton.setSelection(getPreferenceStore().getBoolean(LOGGER_ENABLE));
    }

    @Override
    public boolean performOk() {
        getPreferenceStore().setValue(STEREOTYPES_VISIBLE, stereotypesVisibleButton.getSelection());
        getPreferenceStore().setValue(ANNOTATIONS_VISIBLE, annotationsVisibleButton.getSelection());
        getPreferenceStore().setValue(ATTRIBUTES_VISIBLE, attributesVisibleButton.getSelection());
        getPreferenceStore().setValue(EXTRA_CONNECTION_LABEL_LOCATOR_ENABLE,
                extraConnectionLabelLocatorEnableButton.getSelection());
        getPreferenceStore().setValue(LOGGER_ENABLE, loggerEnableButton.getSelection());
        Logger.setEnable(Preference.isLoggerEnable());
        return true;
    }

    @Override
    protected void performDefaults() {
        stereotypesVisibleButton.setSelection(getPreferenceStore().getDefaultBoolean(STEREOTYPES_VISIBLE));
        annotationsVisibleButton.setSelection(getPreferenceStore().getDefaultBoolean(ANNOTATIONS_VISIBLE));
        attributesVisibleButton.setSelection(getPreferenceStore().getDefaultBoolean(ATTRIBUTES_VISIBLE));
        extraConnectionLabelLocatorEnableButton
                .setSelection(getPreferenceStore().getDefaultBoolean(EXTRA_CONNECTION_LABEL_LOCATOR_ENABLE));
        loggerEnableButton.setSelection(getPreferenceStore().getDefaultBoolean(LOGGER_ENABLE));
        super.performDefaults();
    }

    @Override
    public void init(IWorkbench workbench) {
        // none
    }

}
