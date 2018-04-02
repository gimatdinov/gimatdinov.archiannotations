package gimatdinov.archiannotations;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.archimatetool.editor.diagram.figures.AbstractTextControlContainerFigure;
import com.archimatetool.editor.diagram.figures.connections.AbstractArchimateConnectionFigure;
import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelObject;
import com.archimatetool.model.IIdentifier;
import com.archimatetool.model.IProperties;
import com.archimatetool.model.impl.DiagramModelArchimateConnection;
import com.archimatetool.model.impl.DiagramModelArchimateObject;

import gimatdinov.archiannotations.preferences.Preference;

public class ArchiAnnotationsPlugin extends AbstractUIPlugin {
    public static final String PLUGIN_ID = "gimatdinov.archiannotations";

    private static ArchiAnnotationsPlugin instance;

    private Set<String> objectsWithListeners;
    private ArchiAnnotationsFinder stereotypesFinder;
    private ArchiAnnotationsFinder annotationsFinder;
    private ArchiAnnotationsFinder attributesFinder;

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        instance = this;
        try {
            objectsWithListeners = new HashSet<>();
            stereotypesFinder = new ArchiAnnotationsFinder(Preference.getStereotypePropertyKeyPrefix(),
                    Preference.getStereotypeDisplayPrefix(), Preference.getStereotypeDisplayPostfix(), false);
            annotationsFinder = new ArchiAnnotationsFinder(Preference.getAnnotationPropertyKeyPrefix(),
                    Preference.getAnnotationDisplayPrefix(), Preference.getAnnotationDisplayPostfix(), false);
            attributesFinder = new ArchiAnnotationsFinder(Preference.getAttributePropertyKeyPrefix(),
                    Preference.getAttributeDisplayPrefix(), Preference.getAttributeDisplayPostfix(), true);
            Logger.info("start: OK");
        } catch (Exception e) {
            Logger.error("start: FAIL", e);
            throw e;
        }
        Logger.setEnable(Preference.isLoggerEnable());
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        instance = null;
        super.stop(context);
    }

    public static ArchiAnnotationsPlugin getDefault() {
        return instance;
    }

    private StringBuilder findAnnotations(IIdentifier object, char groupSeparator) {
        Logger.info("findAnnotations: " + object.getId());
        StringBuilder text = new StringBuilder();
        if (Preference.isStereotypesVisible()) {
            StringBuilder builder = stereotypesFinder.find(object);
            if (builder.length() > 0) {
                builder.append(groupSeparator);
                text.append(builder);
            }
        }
        if (Preference.isAnnotationsVisible()) {
            StringBuilder builder = annotationsFinder.find(object);
            if (builder.length() > 0) {
                builder.append(groupSeparator);
                text.append(builder);
            }
        }
        if (Preference.isAttributesVisible()) {
            StringBuilder builder = attributesFinder.find(object);
            if (builder.length() > 0) {
                builder.append(groupSeparator);
                text.append(builder);
            }
        }
        return text;
    }

    private void injectPropertiesListener(IIdentifier object) {
        if (object.getId() == null || objectsWithListeners.contains(object.getId())) {
            return;
        }
        Logger.info("injectPropertiesListener: " + object.getId());
        objectsWithListeners.add(object.getId());
        final IProperties properties = (IProperties) object;
        properties.eAdapters().add(new EContentAdapter() {
            @Override
            public void notifyChanged(Notification notification) {
                if (!(notification instanceof ArchiAnnotationsNotification)) {
                    Logger.info("notifyChanged: " + object.getId());
                    properties.eNotify(new ArchiAnnotationsNotification(properties));
                }
                super.notifyChanged(notification);
            }
        });

    }

    public static void process(AbstractTextControlContainerFigure figure, IDiagramModelObject object) {
        Logger.info("process: " + figure.getClass().getSimpleName() + ", " + object.getId());
        DiagramModelArchimateObject dmaObject = (DiagramModelArchimateObject) object;
        IIdentifier element = dmaObject.getArchimateElement();
        getDefault().injectPropertiesListener(element);
        StringBuilder text = getDefault().findAnnotations(object, '\n');
        text.append(object.getName());
        if (figure.getTextControl() instanceof TextFlow) {
            ((TextFlow) figure.getTextControl()).setText(text.toString());
        } else if (figure.getTextControl() instanceof Label) {
            ((Label) figure.getTextControl()).setText(text.toString());
        }
    }

    public static void process(AbstractArchimateConnectionFigure figure, IDiagramModelArchimateConnection connection) {
        Logger.info("process: " + figure.getClass().getSimpleName() + ", " + connection.getId());
        DiagramModelArchimateConnection dmaConnection = (DiagramModelArchimateConnection) connection;
        IIdentifier relationship = dmaConnection.getArchimateRelationship();
        getDefault().injectPropertiesListener(relationship);
        StringBuilder text = getDefault().findAnnotations(connection, ' ');
        text.append(connection.getName());
        figure.getConnectionLabel().setText(text.toString());
    }

}
