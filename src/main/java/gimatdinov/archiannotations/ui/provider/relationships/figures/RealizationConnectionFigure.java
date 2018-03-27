package gimatdinov.archiannotations.ui.provider.relationships.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class RealizationConnectionFigure
		extends com.archimatetool.editor.diagram.figures.connections.RealizationConnectionFigure {

	@Override
	protected void setConnectionText() {
		ArchiAnnotationsPlugin.process(this, getModelConnection());
	}

}
