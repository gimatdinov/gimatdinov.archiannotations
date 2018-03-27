package gimatdinov.archiannotations.ui.provider.relationships.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class AccessConnectionFigure
		extends com.archimatetool.editor.diagram.figures.connections.AccessConnectionFigure {

	@Override
	protected void setConnectionText() {
		ArchiAnnotationsPlugin.process(this, getModelConnection());
	}

}
