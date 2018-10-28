package gimatdinov.archiannotations.adapter;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;

import com.archimatetool.model.IArchimateConcept;
import com.archimatetool.model.IArchimatePackage;

import gimatdinov.archiannotations.Logger;

public class ConceptAdapter extends EContentAdapter {

    private final IArchimateConcept concept;

    public ConceptAdapter(IArchimateConcept concept) {
        super();
        this.concept = concept;
    }

    @Override
    public void notifyChanged(Notification notification) {
        Object feature = notification.getFeature();
        if (feature == IArchimatePackage.Literals.PROPERTIES__PROPERTIES
                || feature == IArchimatePackage.Literals.PROPERTY__KEY
                || feature == IArchimatePackage.Literals.PROPERTY__VALUE) {
            if (Logger.isEnable()) {
                Logger.info("notifyChanged: conceptClass=" + concept.getClass().getSimpleName() + ", conceptId="
                        + concept.getId() + ", feature=" + notification.getFeature());
            }
            concept.eNotify(new ENotificationImpl((InternalEObject) concept, Notification.SET,
                    IArchimatePackage.Literals.NAMEABLE__NAME, "", ""));
        }
        super.notifyChanged(notification);
    }
}
