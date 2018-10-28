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
import com.archimatetool.model.IArchimateDiagramModel;
import com.archimatetool.model.IArchimateElement;
import com.archimatetool.model.IArchimateRelationship;
import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelArchimateObject;
import com.archimatetool.model.IDiagramModelObject;
import com.archimatetool.model.IProperties;

import gimatdinov.archiannotations.adapter.ConceptAdapter;
import gimatdinov.archiannotations.adapter.DiagramAdapter;
import gimatdinov.archiannotations.preferences.Preference;
import gimatdinov.archiannotations.ui.mods.DiagramMod;
import gimatdinov.archiannotations.ui.provider.elements.figures.GroupingFigure;

public class ArchiAnnotationsPlugin extends AbstractUIPlugin {
    public static final String PLUGIN_ID = "gimatdinov.archiannotations";

    public static final char SEPARATOR_LINE_FEED = '\n';
    public static final char SEPARATOR_SPACE = ' ';

    private static ArchiAnnotationsPlugin instance;

    private Set<String> conceptsWithListeners;
    private Set<String> diagramsWithListeners;

    private ArchiAnnotationsFinder stereotypesFinder;
    private ArchiAnnotationsFinder annotationsFinder;
    private ArchiAnnotationsFinder attributesFinder;

    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        setInstance(this);
        Logger.setEnable(true);
        try {
            conceptsWithListeners = new HashSet<>();
            diagramsWithListeners = new HashSet<>();
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

    private StringBuilder findAnnotations(IProperties object, char groupSeparator) {
        StringBuilder text = new StringBuilder();
        if (Preference.isStereotypesVisible()) {
            String stereotypes = stereotypesFinder.find(object);
            if (stereotypes.length() > 0) {
                text.append(stereotypes);
                text.append(groupSeparator);
            }
        }
        if (Preference.isAnnotationsVisible()) {
            String annotations = annotationsFinder.find(object);
            if (annotations.length() > 0) {
                text.append(annotations);
                text.append(groupSeparator);
            }
        }
        if (Preference.isAttributesVisible()) {
            String attributes = attributesFinder.find(object);
            if (attributes.length() > 0) {
                text.append(attributes);
                text.append(groupSeparator);
            }
        }
        return text;
    }

    public void injectListener(IArchimateConcept concept) {
        if (concept.getId() == null || conceptsWithListeners.contains(concept.getId())) {
            return;
        }
        if (Logger.isEnable()) {
            Logger.info("injectListener: conceptClass=" + concept.getClass().getSimpleName() + ", conceptId="
                    + concept.getId());
        }
        conceptsWithListeners.add(concept.getId());
        concept.eAdapters().add(new ConceptAdapter(concept));
    }

    public void injectListener(IArchimateDiagramModel diagram) {
        if (diagram.getId() == null || conceptsWithListeners.contains(diagram.getId())) {
            return;
        }
        if (Logger.isEnable()) {
            Logger.info("injectListener: diagramClass=" + diagram.getClass().getSimpleName() + ", diagramId="
                    + diagram.getId());
        }
        diagramsWithListeners.add(diagram.getId());
        diagram.eAdapters().add(new DiagramAdapter(diagram));
    }

    public static void process(AbstractTextControlContainerFigure figure) {
        IDiagramModelObject object = figure.getDiagramModelObject();
        DiagramMod diagramMod = new DiagramMod(object.getDiagramModel());
        if (diagramMod.isDAAP()) {
            if (Logger.isEnable()) {
                Logger.info("DAAP: " + figure.getClass().getSimpleName() + ", " + object.getId());
            }
            if (figure.getTextControl() instanceof TextFlow) {
                ((TextFlow) figure.getTextControl()).setText(object.getName());
            } else if (figure.getTextControl() instanceof Label) {
                ((Label) figure.getTextControl()).setText(object.getName());
            }
            return;
        }
        if (object instanceof IDiagramModelArchimateObject) {
            if (Logger.isEnable()) {
                Logger.info("process: " + figure.getClass().getSimpleName() + ", " + object.getId());
            }
            IArchimateElement element = ((IDiagramModelArchimateObject) object).getArchimateElement();
            getInstance().injectListener(element);
            char groupSeparator = (figure instanceof GroupingFigure) ? SEPARATOR_SPACE : SEPARATOR_LINE_FEED;
            StringBuilder text = getInstance().findAnnotations(element, groupSeparator);
            text.append(object.getName());
            if (figure.getTextControl() instanceof TextFlow) {
                ((TextFlow) figure.getTextControl()).setText(text.toString());
            } else if (figure.getTextControl() instanceof Label) {
                ((Label) figure.getTextControl()).setText(text.toString());
            }
        } else {
            if (Logger.isEnable()) {
                Logger.info("NOT process: " + figure.getClass().getSimpleName() + ", " + object.getId());
            }
        }

    }

    public static void process(AbstractArchimateConnectionFigure figure) {
        IDiagramModelArchimateConnection connection = figure.getModelConnection();
        DiagramMod diagramMod = new DiagramMod(connection.getDiagramModel());
        if (diagramMod.isDAAP()) {
            if (Logger.isEnable()) {
                Logger.info("DAAP: " + figure.getClass().getSimpleName() + ", " + connection.getId());
            }
            figure.getConnectionLabel().setText(connection.getName());
            return;
        }
        if (Logger.isEnable()) {
            Logger.info("process: " + figure.getClass().getSimpleName() + ", " + connection.getId());
        }
        if (diagramMod.isHCL()) {
            if (Logger.isEnable()) {
                Logger.info("HCL: " + figure.getClass().getSimpleName() + ", " + connection.getId());
            }
            figure.getConnectionLabel().setText("");
            return;
        }
        IArchimateRelationship relationship = connection.getArchimateRelationship();
        getInstance().injectListener(relationship);
        StringBuilder text = getInstance().findAnnotations(relationship, SEPARATOR_SPACE);
        text.append(connection.getName());
        figure.getConnectionLabel().setText(text.toString());
    }

}
