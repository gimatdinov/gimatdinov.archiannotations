package gimatdinov.archiannotations.ui.provider.relationships.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class FlowConnectionFigure extends com.archimatetool.editor.diagram.figures.connections.FlowConnectionFigure {

	@Override
	protected void setConnectionText() {
		ArchiAnnotationsPlugin.process(this, getModelConnection());
	}

}
