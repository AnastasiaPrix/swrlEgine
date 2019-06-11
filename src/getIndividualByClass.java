import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import java.util.Set;

public class getIndividualByClass {

    public static Set<OWLNamedIndividual> getIndividualofClass(OWLClass cls, OWLReasoner reasoner) {
//        for (OWLClass cls : ontology.getClassesInSignature()) {
//            //System.out.println(cls.getIRI().getShortForm());
//            if (cls.getIRI().getShortForm().equals(ClassName)) {
                NodeSet<OWLNamedIndividual> instances = reasoner.getInstances(cls, true);
                Set<OWLNamedIndividual> x = instances.getFlattened();
                // System.out.println("The Individuals of class : ");
                return x;
//            }
//        }

    }
}
