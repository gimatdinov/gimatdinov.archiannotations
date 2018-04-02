package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.ui.provider.relationships.figures.InfluenceConnectionFigure;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class InfluenceRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.InfluenceRelationshipUIProvider {

    @Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getInfluenceRelationship();
    }

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(InfluenceConnectionFigure.class);
    }
}
