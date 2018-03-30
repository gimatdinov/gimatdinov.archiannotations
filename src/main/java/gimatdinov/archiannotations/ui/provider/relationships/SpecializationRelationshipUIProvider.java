package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.ui.provider.relationships.figures.SpecializationConnectionFigure;

public class SpecializationRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.SpecializationRelationshipUIProvider {

    @Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getSpecializationRelationship();
    }

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(SpecializationConnectionFigure.class);
    }
}
