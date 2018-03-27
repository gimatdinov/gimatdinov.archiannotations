package gimatdinov.archiannotations;

import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.text.TextFlow;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.archimatetool.editor.diagram.figures.AbstractTextControlContainerFigure;
import com.archimatetool.editor.diagram.figures.connections.AbstractArchimateConnectionFigure;
import com.archimatetool.model.IArchimatePackage;
import com.archimatetool.model.IDiagramModelArchimateConnection;
import com.archimatetool.model.IDiagramModelObject;
import com.archimatetool.model.INameable;
import com.archimatetool.model.IProperties;
import com.archimatetool.model.impl.DiagramModelArchimateConnection;
import com.archimatetool.model.impl.DiagramModelArchimateObject;

import gimatdinov.archiannotations.preferences.Preference;

public class ArchiAnnotationsPlugin extends AbstractUIPlugin {
	public static final String PLUGIN_ID = "gimatdinov.archiannotations";
	public static ArchiAnnotationsPlugin INSTANCE;

	private ArchiAnnotationsFinder stereotypesFinder;
	private ArchiAnnotationsFinder annotationsFinder;
	private ArchiAnnotationsFinder attributesFinder;

	public ArchiAnnotationsPlugin() {
		INSTANCE = this;
		initStereotypesFinder();
		initAnnotationsFinder();
		initAttributesFinder();
	}

	public void initStereotypesFinder() {
		stereotypesFinder = new ArchiAnnotationsFinder(Preference.getStereotypePropertyKey(),
				Preference.getStereotypeDisplayPrefix(), Preference.getStereotypeDisplayPostfix(), false);
	}

	public void initAnnotationsFinder() {
		annotationsFinder = new ArchiAnnotationsFinder(Preference.getAnnotationPropertyKey(),
				Preference.getAnnotationDisplayPrefix(), Preference.getAnnotationDisplayPostfix(), false);
	}

	public void initAttributesFinder() {
		attributesFinder = new ArchiAnnotationsFinder(Preference.getAttributePropertyKey(),
				Preference.getAttributeDisplayPrefix(), Preference.getAttributeDisplayPostfix(), true);
	}

	private static StringBuilder findAnnotations(INameable object, char groupSeparator) {
		StringBuilder text = new StringBuilder();
		if (Preference.isStereotypesVisible()) {
			StringBuilder builder = INSTANCE.stereotypesFinder.find(object);
			if (builder.length() > 0) {
				builder.append(groupSeparator);
				text.append(builder);
			}
		}
		if (Preference.isAnnotationsVisible()) {
			StringBuilder builder = INSTANCE.annotationsFinder.find(object);
			if (builder.length() > 0) {
				builder.append(groupSeparator);
				text.append(builder);
			}
		}
		if (Preference.isAttributesVisible()) {
			StringBuilder builder = INSTANCE.attributesFinder.find(object);
			if (builder.length() > 0) {
				builder.append(groupSeparator);
				text.append(builder);
			}
		}
		return text;
	}

	private static void injectPropertiesListener(INameable object) {
		IProperties properties = null;
		if (object instanceof IDiagramModelObject) {
			DiagramModelArchimateObject dmaObject = (DiagramModelArchimateObject) object;
			properties = dmaObject.getArchimateElement();
		}
		if (object instanceof IDiagramModelArchimateConnection) {
			DiagramModelArchimateConnection dmaConnection = (DiagramModelArchimateConnection) object;
			properties = (IProperties) dmaConnection.getArchimateRelationship();
		}
		if (properties != null) {
			final IProperties _properties = properties;
			properties.eAdapters().add(new EContentAdapter() {
				public void notifyChanged(Notification notification) {
					super.notifyChanged(notification);
					if (!(notification instanceof ArchiAnnotationsNotification)) {
						Notification msg = new ENotificationImpl((InternalEObject) _properties, Notification.SET,
								IArchimatePackage.Literals.NAMEABLE__NAME, "", "");
						_properties.eNotify(new ArchiAnnotationsNotification(msg));
					}
				}
			});
		}
	}

	public static void process(AbstractTextControlContainerFigure figure, IDiagramModelObject object) {
		injectPropertiesListener(object);
		StringBuilder text = findAnnotations(object, '\n');
		text.append(object.getName());
		if (figure.getTextControl() instanceof TextFlow) {
			((TextFlow) figure.getTextControl()).setText(text.toString());
		} else if (figure.getTextControl() instanceof Label) {
			((Label) figure.getTextControl()).setText(text.toString());
		}
	}

	public static void process(AbstractArchimateConnectionFigure figure, IDiagramModelArchimateConnection connection) {
		injectPropertiesListener(connection);
		StringBuilder text = findAnnotations(connection, ' ');
		text.append(connection.getName());
		figure.getConnectionLabel().setText(text.toString());
	}

}
