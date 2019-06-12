import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;
import java.util.List;

public class CreateProtectionIndivid {
    public static void CreateProtection(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager, int variant) {
        String[] linesName1 = ind.toString().split("#");
        String[] linesName = linesName1[1].split(">");
        String h = protection.get(variant).getIRI().getShortForm().toString();
        String indName = linesName[0].concat(h);


        OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns+indName));
        OWLAxiom protLineBase = df.getOWLClassAssertionAxiom(protection.get(variant), indProtection);
        AddAxiom prot= new AddAxiom(ont,protLineBase);
        manager.applyChange(prot);
        }
}
