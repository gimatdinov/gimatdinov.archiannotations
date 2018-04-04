package gimatdinov.archiannotations;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.archimatetool.editor.diagram.figures.AbstractTextControlContainerFigure;
import com.archimatetool.editor.diagram.figures.connections.AbstractArchimateConnectionFigure;
import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimateElement;
import com.archimatetool.model.IArchimateRelationship;
import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelObject;
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
        setInstance(this);
        Logger.setEnable(true);
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
        setInstance(null);
        super.stop(context);
    }

    private static void setInstance(ArchiAnnotationsPlugin instance) {
        ArchiAnnotationsPlugin.instance = instance;
    }

    public static ArchiAnnotationsPlugin getInstance() {
        return instance;
    }

    private StringBuilder findAnnotations(IArchimateConcept concept, char groupSeparator) {
        if (Logger.isEnable()) {
            Logger.info("findAnnotations: " + concept.getClass().getSimpleName() + ", " + concept.getId());
        }
        StringBuilder text = new StringBuilder();
        if (Preference.isStereotypesVisible()) {
            String stereotypes = stereotypesFinder.find(concept);
            if (stereotypes.length() > 0) {
                text.append(stereotypes);
                text.append(groupSeparator);
            }
        }
        if (Preference.isAnnotationsVisible()) {
            String annotations = annotationsFinder.find(concept);
            if (annotations.length() > 0) {
                text.append(annotations);
                text.append(groupSeparator);
            }
        }
        if (Preference.isAttributesVisible()) {
            String attributes = attributesFinder.find(concept);
            if (attributes.length() > 0) {
                text.append(attributes);
                text.append(groupSeparator);
            }
        }
        return text;
    }

    private void injectPropertiesListener(IArchimateConcept concept) {
        if (concept.getId() == null || objectsWithListeners.contains(concept.getId())) {
            return;
        }
        if (Logger.isEnable()) {
            Logger.info("injectPropertiesListener: " + concept.getClass().getSimpleName() + ", " + concept.getId());
        }
        objectsWithListeners.add(concept.getId());
        concept.eAdapters().add(new ArchiAnnotationsAdapter(concept));
    }

    public static void process(AbstractTextControlContainerFigure figure) {
        IDiagramModelObject object = figure.getDiagramModelObject();
        if (Logger.isEnable()) {
            Logger.info("process: " + figure.getClass().getSimpleName() + ", " + object.getId());
        }
        DiagramModelArchimateObject dmaObject = (DiagramModelArchimateObject) object;
        IArchimateElement element = dmaObject.getArchimateElement();
        getInstance().injectPropertiesListener(element);
        StringBuilder text = getInstance().findAnnotations(element, '\n');
        text.append(object.getName());
        if (figure.getTextControl() instanceof TextFlow) {
            ((TextFlow) figure.getTextControl()).setText(text.toString());
        } else if (figure.getTextControl() instanceof Label) {
            ((Label) figure.getTextControl()).setText(text.toString());
        }
    }

    public static void process(AbstractArchimateConnectionFigure figure) {
        IDiagramModelArchimateConnection connection = figure.getModelConnection();
        if (Logger.isEnable()) {
            Logger.info("process: " + figure.getClass().getSimpleName() + ", " + connection.getId());
        }
        DiagramModelArchimateConnection dmaConnection = (DiagramModelArchimateConnection) connection;
        IArchimateRelationship relationship = dmaConnection.getArchimateRelationship();
        getInstance().injectPropertiesListener(relationship);
        StringBuilder text = getInstance().findAnnotations(relationship, ' ');
        text.append(connection.getName());
        figure.getConnectionLabel().setText(text.toString());
    }

}
