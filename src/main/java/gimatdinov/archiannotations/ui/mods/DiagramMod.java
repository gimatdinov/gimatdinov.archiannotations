package gimatdinov.archiannotations.ui.mods;

import java.util.List;
import java.util.stream.Collectors;

import com.archimatetool.model.IDiagramModel;
import com.archimatetool.model.IProperty;

public class DiagramMod {
    private final IDiagramModel diagram;

    public DiagramMod(IDiagramModel diagram) {
        this.diagram = diagram;
    }

    private List<IProperty> findProperty(String key) {
        return diagram.getProperties().stream().filter(property -> key.equals(property.getKey()))
                .collect(Collectors.toList());
    }

    private boolean testProperty(String key) {
        return diagram != null && diagram.getProperties().stream().anyMatch(property -> key.equals(property.getKey()));
    }

    public boolean isDAAP() {
        return testProperty("%DAAP");
    }

    public boolean isHCL() {
        return testProperty("%HCL");
    }

}
