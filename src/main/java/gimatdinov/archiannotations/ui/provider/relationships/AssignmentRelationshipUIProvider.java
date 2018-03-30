package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.ui.provider.relationships.figures.AssignmentConnectionFigure;

public class AssignmentRelationshipUIProvider
        extends com.archimatetool.editor.ui.factory.relationships.AssignmentRelationshipUIProvider {

    @Override
    public EClass providerFor() {
        return IArchimatePackage.eINSTANCE.getAssignmentRelationship();
    }

    @Override
    public EditPart createEditPart() {
        return new ArchimateRelationshipEditPart(AssignmentConnectionFigure.class);
    }
}
