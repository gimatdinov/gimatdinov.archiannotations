package gimatdinov.archiannotations.ui.provider.elements;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;

public class BusinessEventUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessEventUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(gimatdinov.archiannotations.ui.provider.elements.figures.EventFigure.class);
    }
}
