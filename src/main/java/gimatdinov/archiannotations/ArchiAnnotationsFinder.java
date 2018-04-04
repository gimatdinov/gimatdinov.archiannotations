package gimatdinov.archiannotations;

import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IProperty;

class ArchiAnnotationsFinder {

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

    public String find(IArchimateConcept concept) {
        StringBuilder builder = new StringBuilder();
        int count = 0;
        for (IProperty property : concept.getProperties()) {
            if ((property.getKey() != null) && property.getKey().startsWith(propertyKeyPrefix)) {
                if (count > 0) {
                    builder.append(grouping ? ", " : " ");
                }
                append(builder, property);
                count++;
            }
        }
        if (grouping && (builder.length() > 0)) {
            builder.insert(0, displayPrefix);
            builder.append(dispalyPostfix);
        }
        return builder.toString();
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
