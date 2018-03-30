package gimatdinov.archiannotations.ui.provider.elements;

import org.eclipse.gef.EditPart;

import com.archimatetool.editor.diagram.editparts.ArchimateElementEditPart;

public class DistributionNetworkUIProvider
        extends com.archimatetool.editor.ui.factory.elements.DistributionNetworkUIProvider {

    @Override
    public EditPart createEditPart() {
        return new ArchimateElementEditPart(
                gimatdinov.archiannotations.ui.provider.elements.figures.DistributionNetworkFigure.class);
    }
}
