package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.ui.provider.relationships.figures.ServingConnectionFigure;

public class ServingRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.ServingRelationshipUIProvider {

    @Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getServingRelationship();
    }

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(ServingConnectionFigure.class);
    }
}
