package gimatdinov.archiannotations;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import com.archimatetool.model.IArchimatePackage;
import com.archimatetool.model.IProperties;

public class ArchiAnnotationsNotification extends NotificationWrapper {

    public ArchiAnnotationsNotification(final IProperties properties) {
        super(new ENotificationImpl((InternalEObject) properties, Notification.SET,
                IArchimatePackage.Literals.NAMEABLE__NAME, "", ""));
    }

}
