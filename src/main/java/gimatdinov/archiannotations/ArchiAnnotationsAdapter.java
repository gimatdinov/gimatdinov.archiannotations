package gimatdinov.archiannotations;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimatePackage;

class ArchiAnnotationsAdapter extends EContentAdapter {

    private static class ArchiAnnotationsNotification extends NotificationWrapper {

        public ArchiAnnotationsNotification(final IArchimateConcept concept) {
            super(new ENotificationImpl((InternalEObject) concept, Notification.SET,
                    IArchimatePackage.Literals.NAMEABLE__NAME, "", ""));
        }

    }

    private final IArchimateConcept concept;

    public ArchiAnnotationsAdapter(IArchimateConcept concept) {
        super();
        this.concept = concept;
    }

    @Override
    public void notifyChanged(Notification notification) {
        if (!(notification instanceof ArchiAnnotationsNotification)) {
            if (Logger.isEnable()) {
                Logger.info("notifyChanged: " + concept.getClass().getSimpleName() + ", " + concept.getId());
            }
            concept.eNotify(new ArchiAnnotationsNotification(concept));
        }
        super.notifyChanged(notification);
    }
}
