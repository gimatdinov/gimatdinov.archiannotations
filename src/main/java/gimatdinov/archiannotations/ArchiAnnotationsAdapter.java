package gimatdinov.archiannotations;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.archimatetool.model.IArchimateConcept;

class ArchiAnnotationsAdapter extends EContentAdapter {

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
