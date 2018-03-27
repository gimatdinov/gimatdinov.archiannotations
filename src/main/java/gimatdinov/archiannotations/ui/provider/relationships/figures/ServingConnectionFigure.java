package gimatdinov.archiannotations.ui.provider.relationships.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class ServingConnectionFigure
		extends com.archimatetool.editor.diagram.figures.connections.ServingConnectionFigure {
	@Override

	protected void setConnectionText() {
		ArchiAnnotationsPlugin.process(this, getModelConnection());
	}

}
