import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import java.util.Collection;
import java.util.Set;

public class ChangeValueOfUsageTT {

    public static void changeUsage(OWLClass classType, OWLReasoner reasoner, OWLOntology ontology, OWLObjectProperty oProperty, OWLOntologyManager manager, OWLDataFactory df, OWLDataProperty dProperty, int value1, int value2 ){
        Set<OWLNamedIndividual> indPB = getIndividualByClass.getIndividualofClass(classType, reasoner);
        for (OWLNamedIndividual t : indPB) {
            Collection<OWLIndividual> listOfUse = getIndividualFromProperty.getIndivid(t, ontology, oProperty);
            for (OWLIndividual i : listOfUse) {
                AxiomsAdding.changingAxiomsData(ontology, manager, df, i, value1, value2, dProperty);
            }
        }
    }
}
