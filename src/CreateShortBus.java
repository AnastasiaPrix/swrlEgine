import org.openrdf.model.vocabulary.OWL;
import org.semanticweb.owlapi.model.*;
import java.util.Collection;

public class CreateShortBus {

    public static void CreateBus(OWLOntology ontology, OWLOntologyManager manager, OWLDataFactory df, String ns, OWLIndividual j, OWLIndividual trans, OWLIndividual volt, Collection<OWLIndividual> nodes, int k) {
        OWLClass oshClass = df.getOWLClass(IRI.create(ns + "ShortBus"));
        OWLObjectProperty hasCN = df.getOWLObjectProperty(IRI.create(ns + "hasCN"));
        OWLObjectProperty hasVoltageLevel = df.getOWLObjectProperty(IRI.create(ns + "hasVoltageLevel"));
        OWLObjectProperty hasBus = df.getOWLObjectProperty(IRI.create(ns + "hasShortBus"));
        if (nodes.size() >= 2) {
            String name = trans.toStringID();
            String name1 = name.split("#")[1];
            String oshinName = "shortBus_".concat(name1) + k;
            System.out.println("has oshin");
            OWLIndividual indShin = df.getOWLNamedIndividual(IRI.create(ns + oshinName));
            AxiomsAdding.AddingClass(ontology, manager, df, indShin, oshClass);
            AxiomsAdding.adding(ontology, manager, df, indShin, j, hasCN);
            AxiomsAdding.adding(ontology, manager, df, trans, indShin, hasBus);
            AxiomsAdding.adding(ontology, manager, df, indShin, volt, hasVoltageLevel);
            for (OWLIndividual n : nodes) {
                AxiomsAdding.adding(ontology, manager, df, indShin, n, hasCN);
            }
        }
    }

    public static void CreateFalseBus(OWLOntology ontology, OWLOntologyManager manager, OWLDataFactory df, String ns, OWLIndividual j, OWLIndividual trans, OWLIndividual volt, Collection<OWLIndividual> nodes, int k) {
        OWLClass oshClass = df.getOWLClass(IRI.create(ns + "ShortBus"));
        OWLObjectProperty hasCN = df.getOWLObjectProperty(IRI.create(ns + "hasCN"));
        OWLObjectProperty hasVoltageLevel = df.getOWLObjectProperty(IRI.create(ns + "hasVoltageLevel"));
        OWLObjectProperty hasBus = df.getOWLObjectProperty(IRI.create(ns + "hasFalseBus"));
        OWLObjectProperty addedEquipment = df.getOWLObjectProperty(IRI.create(ns + "addedEquipment"));
        if (nodes.size() >= 2) {
            String name = trans.toStringID();
            String name1 = name.split("#")[1];
            String oshinName = "shortBus_".concat(name1) + k;
            System.out.println("has oshin");
            OWLIndividual indShin = df.getOWLNamedIndividual(IRI.create(ns + oshinName));
            AxiomsAdding.AddingClass(ontology, manager, df, indShin, oshClass);
            AxiomsAdding.adding(ontology, manager, df, indShin, j, hasCN);
            AxiomsAdding.adding(ontology, manager, df, trans, indShin, addedEquipment);
            AxiomsAdding.adding(ontology, manager, df, indShin, volt, hasVoltageLevel);
            for (OWLIndividual n : nodes) {
                AxiomsAdding.adding(ontology, manager, df, indShin, n, hasCN);
            }
        }

    }
}
