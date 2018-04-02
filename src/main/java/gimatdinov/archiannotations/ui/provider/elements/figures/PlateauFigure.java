package gimatdinov.archiannotations.ui.provider.elements.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class PlateauFigure extends com.archimatetool.editor.diagram.figures.elements.PlateauFigure {

    @Override
    protected void setText() {
        ArchiAnnotationsPlugin.process(this, getDiagramModelObject());
    }

}
