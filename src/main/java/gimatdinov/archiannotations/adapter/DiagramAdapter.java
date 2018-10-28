package gimatdinov.archiannotations.adapter;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.archimatetool.model.IArchimateDiagramModel;
import com.archimatetool.model.IArchimatePackage;
import com.archimatetool.model.IDiagramModelConnection;
import com.archimatetool.model.IDiagramModelObject;

import gimatdinov.archiannotations.Logger;

public class DiagramAdapter extends EContentAdapter {

    private final IArchimateDiagramModel diagram;

    public DiagramAdapter(IArchimateDiagramModel diagram) {
        super();
        this.diagram = diagram;
    }

    @Override
    public void notifyChanged(Notification notification) {
        Object feature = notification.getFeature();
        if (feature == IArchimatePackage.Literals.PROPERTIES__PROPERTIES
                || feature == IArchimatePackage.Literals.PROPERTY__KEY
                || feature == IArchimatePackage.Literals.PROPERTY__VALUE) {
            if (Logger.isEnable()) {
                Logger.info("notifyChanged: diagramClass=" + diagram.getClass().getSimpleName() + ", diagramId="
                        + diagram.getId() + ", feature=" + notification.getFeature());
            }

            for (IDiagramModelObject object : diagram.getChildren()) {
                object.eNotify(createNotification());
                Set<IDiagramModelConnection> connections = new HashSet<>();
                connections.addAll(object.getSourceConnections());
                connections.addAll(object.getTargetConnections());
                for (IDiagramModelConnection connection : connections) {
                    connection.eNotify(createNotification());
                }
            }
        }
        super.notifyChanged(notification);
    }

    private Notification createNotification() {
        return new ENotificationImpl((InternalEObject) diagram, Notification.SET,
                IArchimatePackage.Literals.NAMEABLE__NAME, "", "");
    }
}
