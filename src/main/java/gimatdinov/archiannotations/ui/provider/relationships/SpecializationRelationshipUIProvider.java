package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import gimatdinov.archiannotations.ui.provider.relationships.figures.SpecializationConnectionFigure;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class SpecializationRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.SpecializationRelationshipUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(SpecializationConnectionFigure.class);
    }
}
