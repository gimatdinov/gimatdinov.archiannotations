package gimatdinov.archiannotations.ui.provider.relationships.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class AssignmentConnectionFigure
		extends com.archimatetool.editor.diagram.figures.connections.AssignmentConnectionFigure {

	@Override
	protected void setConnectionText() {
		ArchiAnnotationsPlugin.process(this, getModelConnection());
	}

}
