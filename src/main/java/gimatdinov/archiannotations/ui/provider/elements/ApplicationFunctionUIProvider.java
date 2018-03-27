package gimatdinov.archiannotations.ui.provider.elements;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;

public class ApplicationFunctionUIProvider
		extends com.archimatetool.editor.ui.factory.elements.ApplicationFunctionUIProvider {

	@Override
	public EditPart createEditPart() {
		return new ArchimateElementEditPart(
				gimatdinov.archiannotations.ui.provider.elements.figures.FunctionFigure.class);
	}
}
