package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.ui.provider.relationships.figures.FlowConnectionFigure;

public class FlowRelationshipUIProvider
		extends com.archimatetool.editor.ui.factory.relationships.FlowRelationshipUIProvider {

	@Override
	public EClass providerFor() {
		return IArchimatePackage.eINSTANCE.getFlowRelationship();
	}

	@Override
	public EditPart createEditPart() {
		return new ArchimateRelationshipEditPart(FlowConnectionFigure.class);
	}
}
