package gimatdinov.archiannotations.ui.provider.elements;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class BusinessProcessUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessProcessUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(
                gimatdinov.archiannotations.ui.provider.elements.figures.ProcessFigure.class);
    }
}
