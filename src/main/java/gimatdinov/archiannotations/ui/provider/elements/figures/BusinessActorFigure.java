package gimatdinov.archiannotations.ui.provider.elements.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class BusinessActorFigure extends com.archimatetool.editor.diagram.figures.elements.BusinessActorFigure {

	@Override
	protected void setText() {
		ArchiAnnotationsPlugin.process(this, getDiagramModelObject());
	}

}
