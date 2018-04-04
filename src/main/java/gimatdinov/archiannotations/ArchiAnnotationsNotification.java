package gimatdinov.archiannotations;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimatePackage;

public class ArchiAnnotationsNotification extends NotificationWrapper {

    public ArchiAnnotationsNotification(final IArchimateConcept concept) {
        super(new ENotificationImpl((InternalEObject) concept, Notification.SET,
                IArchimatePackage.Literals.NAMEABLE__NAME, "", ""));
    }

}
