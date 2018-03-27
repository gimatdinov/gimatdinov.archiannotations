package gimatdinov.archiannotations.ui.provider.relationships.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class CompositionConnectionFigure
		extends com.archimatetool.editor.diagram.figures.connections.CompositionConnectionFigure {

	@Override
	protected void setConnectionText() {
		ArchiAnnotationsPlugin.process(this, getModelConnection());
	}

}
