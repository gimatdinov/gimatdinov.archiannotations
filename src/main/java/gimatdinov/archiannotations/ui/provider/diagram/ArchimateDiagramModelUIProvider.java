package gimatdinov.archiannotations.ui.provider.diagram;

import org.eclipse.gef.EditPart;

import com.archimatetool.model.IArchimateDiagramModel;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class ArchimateDiagramModelUIProvider
        extends com.archimatetool.editor.ui.factory.diagram.ArchimateDiagramModelUIProvider {

    @Override
    public EditPart createEditPart() {
        ArchiAnnotationsPlugin.getInstance().injectListener((IArchimateDiagramModel) instance);
        return super.createEditPart();
    }

}
