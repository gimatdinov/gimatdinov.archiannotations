package gimatdinov.archiannotations.ui.provider.elements;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class BusinessObjectUIProvider extends com.archimatetool.editor.ui.factory.elements.BusinessObjectUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(
                gimatdinov.archiannotations.ui.provider.elements.figures.ObjectFigure.class);
    }
}
