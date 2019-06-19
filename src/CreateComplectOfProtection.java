import org.semanticweb.owlapi.model.*;

import java.util.List;

public class CreateComplectOfProtection {

    public static void CreateComplect(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager) {
        for (OWLClass j: protection) {
            String[] linesName1 = ind.toString().split("#");
            String[] linesName = linesName1[1].split(">");
            String h = j.getIRI().getShortForm().toString();
            String indName = linesName[0].concat(h);
            OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));

            OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
            OWLAxiom protInd = df.getOWLClassAssertionAxiom(j, indProtection);
            AddAxiom prot = new AddAxiom(ont,protInd);
            manager.applyChange(prot);

            OWLAxiom prortected = df.getOWLObjectPropertyAssertionAxiom(isProtectedBy,ind,indProtection);
            // добавление аксиомы в онтологию
            AddAxiom protBy= new AddAxiom(ont,prortected);
            //сохранение изменений
            manager.applyChange(protBy);

        }
    }
}
