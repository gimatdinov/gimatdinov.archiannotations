package gimatdinov.archiannotations.ui.provider.relationships;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateRelationshipEditPart;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.ui.provider.relationships.figures.CompositionConnectionFigure;

public class CompositionRelationshipUIProvider
		extends com.archimatetool.editor.ui.factory.relationships.CompositionRelationshipUIProvider {

	@Override
	public EClass providerFor() {
		return IArchimatePackage.eINSTANCE.getCompositionRelationship();
	}

	@Override
	public EditPart createEditPart() {
		return new ArchimateRelationshipEditPart(CompositionConnectionFigure.class);
	}
}
