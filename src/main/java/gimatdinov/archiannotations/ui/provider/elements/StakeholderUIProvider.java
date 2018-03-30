package gimatdinov.archiannotations.ui.provider.elements;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;

public class StakeholderUIProvider extends com.archimatetool.editor.ui.factory.elements.StakeholderUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(
                gimatdinov.archiannotations.ui.provider.elements.figures.StakeholderFigure.class);
    }
}
