package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import gimatdinov.archiannotations.ui.provider.relationships.figures.FlowConnectionFigure;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class FlowRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.FlowRelationshipUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(FlowConnectionFigure.class);
    }
}
