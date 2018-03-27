package gimatdinov.archiannotations.ui.provider.elements.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

public class ProductFigure extends com.archimatetool.editor.diagram.figures.elements.ProductFigure {

	@Override
	protected void setText() {
		ArchiAnnotationsPlugin.process(this, getDiagramModelObject());
	}

}
