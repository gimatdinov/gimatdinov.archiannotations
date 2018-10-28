package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import gimatdinov.archiannotations.ui.provider.relationships.figures.InfluenceConnectionFigure;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class InfluenceRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.InfluenceRelationshipUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(InfluenceConnectionFigure.class);
    }
}
