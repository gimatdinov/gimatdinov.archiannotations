package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import gimatdinov.archiannotations.ui.provider.relationships.figures.AssignmentConnectionFigure;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class AssignmentRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.AssignmentRelationshipUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(AssignmentConnectionFigure.class);
    }
}
