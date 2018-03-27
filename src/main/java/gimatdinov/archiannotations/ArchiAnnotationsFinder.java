package gimatdinov.archiannotations;

import java.util.List;

import com.archimatetool.model.INameable;
import com.archimatetool.model.IProperty;
import com.archimatetool.model.impl.DiagramModelArchimateConnection;
import com.archimatetool.model.impl.DiagramModelArchimateObject;

public class ArchiAnnotationsFinder {

	private final String propertyKey;
	private final String displayPrefix;
	private final String dispalyPostfix;
	private final boolean grouping;

	public ArchiAnnotationsFinder(String propertyKey, String displayPrefix, String dispalyPostfix, boolean grouping) {
		this.propertyKey = propertyKey;
		this.displayPrefix = displayPrefix;
		this.dispalyPostfix = dispalyPostfix;
		this.grouping = grouping;
	}

	public StringBuilder find(INameable object) {
		StringBuilder builder = new StringBuilder();
		if (object instanceof DiagramModelArchimateObject) {
			DiagramModelArchimateObject dmaObject = (DiagramModelArchimateObject) object;
			find(builder, dmaObject.getArchimateElement().getProperties());
		}
		if (object instanceof DiagramModelArchimateConnection) {
			DiagramModelArchimateConnection dmaConnection = (DiagramModelArchimateConnection) object;
			find(builder, dmaConnection.getArchimateRelationship().getProperties());
		}
		return builder;
	}

	private void find(StringBuilder builder, List<IProperty> properties) {
		int count = 0;
		for (IProperty property : properties) {
			if (propertyKey.equals(property.getKey())) {
				if (grouping && count == 0) {
					builder.append(displayPrefix);
				}
				if (count > 0) {
					if (grouping) {
						builder.append(", ");
					} else {
						builder.append(' ');
					}
				}
				if (!grouping) {
					builder.append(displayPrefix);
				}
				builder.append(property.getValue());
				if (!grouping) {
					builder.append(dispalyPostfix);
				}
				count++;
			}
		}
		if (grouping && count > 0) {
			builder.append(dispalyPostfix);
		}
	}

}
