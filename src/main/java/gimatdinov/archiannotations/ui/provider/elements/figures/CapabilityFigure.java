package gimatdinov.archiannotations.ui.provider.elements.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class CapabilityFigure extends com.archimatetool.editor.diagram.figures.elements.CapabilityFigure {

	@Override
	protected void setText() {
		ArchiAnnotationsPlugin.process(this, getDiagramModelObject());
	}

}
