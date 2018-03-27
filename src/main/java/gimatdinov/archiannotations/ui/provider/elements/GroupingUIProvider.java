package gimatdinov.archiannotations.ui.provider.elements;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;

public class GroupingUIProvider extends com.archimatetool.editor.ui.factory.elements.GroupingUIProvider {

	@Override
	public EditPart createEditPart() {
		return new ArchimateElementEditPart(
				gimatdinov.archiannotations.ui.provider.elements.figures.GroupingFigure.class);
	}
}
