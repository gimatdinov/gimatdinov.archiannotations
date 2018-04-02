package gimatdinov.archiannotations.ui.provider.relationships.figures;

import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.IFigure;

import com.archimatetool.editor.diagram.figures.connections.ArchiConnectionEndpointLocator;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;
import gimatdinov.archiannotations.extra.ConnectionLabelMiddleLocator;
import gimatdinov.archiannotations.preferences.Preference;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class FlowConnectionFigure extends com.archimatetool.editor.diagram.figures.connections.FlowConnectionFigure {

    @Override
    protected void setConnectionText() {
        ArchiAnnotationsPlugin.process(this, getModelConnection());
    }

    @Override
    public void setConstraint(IFigure child, Object constraint) {
        if (Preference.isExtraConnectionLabelLocatorEnable()) {
            if (constraint instanceof ArchiConnectionEndpointLocator) {
                super.setConstraint(child, constraint);
            } else {
                if (constraint instanceof ConnectionLocator) {
                    super.setConstraint(child, new ConnectionLabelMiddleLocator(this, getConnectionLabel()));
                }
            }
        } else {
            super.setConstraint(child, constraint);
        }
    }

}
