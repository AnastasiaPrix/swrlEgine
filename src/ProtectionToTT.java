import org.semanticweb.owlapi.model.*;

import java.util.Collection;

public class ProtectionToTT {
    public static void createLinks (OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df){
        OWLObjectProperty connectedEquipment = df.getOWLObjectProperty(IRI.create(ns+"connectedEquipment"));
        OWLObjectProperty hasTCTR = df.getOWLObjectProperty(IRI.create(ns+"hasTCTR"));
        Collection<OWLIndividual> listConnection = getIndividualFromProperty.getIndivid(ind,ont, connectedEquipment);
        Collection<OWLIndividual> listTCTR= getIndividualFromProperty.getIndivid(ind,ont, hasTCTR);
        for (OWLIndividual i: listConnection){
            Collection<OWLIndividual> listTCTR2= getIndividualFromProperty.getIndivid(i,ont, hasTCTR);
        }

    }
}
