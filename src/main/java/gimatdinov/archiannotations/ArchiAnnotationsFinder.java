package gimatdinov.archiannotations;

import java.util.List;

import com.archimatetool.model.IIdentifier;
import com.archimatetool.model.IProperty;
import com.archimatetool.model.impl.DiagramModelArchimateConnection;
import com.archimatetool.model.impl.DiagramModelArchimateObject;

public class ArchiAnnotationsFinder {

    private final String propertyKeyPrefix;
    private final String displayPrefix;
    private final String dispalyPostfix;
    private final boolean grouping;

    public ArchiAnnotationsFinder(String propertyKeyPrefix, String displayPrefix, String dispalyPostfix,
            boolean grouping) {
        this.propertyKeyPrefix = propertyKeyPrefix;
        this.displayPrefix = displayPrefix;
        this.dispalyPostfix = dispalyPostfix;
        this.grouping = grouping;
    }

    public String find(IIdentifier object) {
        StringBuilder builder = new StringBuilder();
        if (object instanceof DiagramModelArchimateObject) {
            DiagramModelArchimateObject dmaObject = (DiagramModelArchimateObject) object;
            find(builder, dmaObject.getArchimateElement().getProperties());
        }
        if (object instanceof DiagramModelArchimateConnection) {
            DiagramModelArchimateConnection dmaConnection = (DiagramModelArchimateConnection) object;
            find(builder, dmaConnection.getArchimateRelationship().getProperties());
        }
        if (grouping && (builder.length() > 0)) {
            builder.insert(0, displayPrefix);
            builder.append(dispalyPostfix);
        }
        return builder.toString();
    }

    private void find(StringBuilder builder, List<IProperty> properties) {
        int count = 0;
        for (IProperty property : properties) {
            if ((property.getKey() != null) && property.getKey().startsWith(propertyKeyPrefix)) {
                if (count > 0) {
                    builder.append(grouping ? ", " : " ");
                }
                append(builder, property);
                count++;
            }
        }
    }

    private void append(StringBuilder builder, IProperty property) {
        if (!grouping) {
            builder.append(displayPrefix);
        }
        builder.append(property.getKey().substring(propertyKeyPrefix.length()));
        if (property.getValue() != null && property.getValue().length() > 0) {
            builder.append('(');
            builder.append(property.getValue());
            builder.append(')');
        }
        if (!grouping) {
            builder.append(dispalyPostfix);
        }
    }

}
