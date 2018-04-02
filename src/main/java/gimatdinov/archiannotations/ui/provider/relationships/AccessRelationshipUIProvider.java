package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.ui.provider.relationships.figures.AccessConnectionFigure;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class AccessRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.AccessRelationshipUIProvider {

    @Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getAccessRelationship();
    }

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(AccessConnectionFigure.class);
    }
}
