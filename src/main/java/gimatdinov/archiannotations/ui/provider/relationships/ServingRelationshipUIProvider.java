package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import gimatdinov.archiannotations.ui.provider.relationships.figures.ServingConnectionFigure;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class ServingRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.ServingRelationshipUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(ServingConnectionFigure.class);
    }
}
