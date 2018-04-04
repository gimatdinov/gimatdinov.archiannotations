package gimatdinov.archiannotations.ui.provider.elements.figures;

import gimatdinov.archiannotations.ArchiAnnotationsPlugin;

@SuppressWarnings({ "squid:MaximumInheritanceDepth", "squid:S2176" })
public class CourseOfActionFigure extends com.archimatetool.editor.diagram.figures.elements.CourseOfActionFigure {

    @Override
    protected void setText() {
        ArchiAnnotationsPlugin.process(this);
    }

}
