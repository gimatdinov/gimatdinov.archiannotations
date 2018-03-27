package gimatdinov.archiannotations.ui.provider.elements.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class RequirementFigure extends com.archimatetool.editor.diagram.figures.elements.RequirementFigure {

	@Override
	protected void setText() {
		ArchiAnnotationsPlugin.process(this, getDiagramModelObject());
	}

}
