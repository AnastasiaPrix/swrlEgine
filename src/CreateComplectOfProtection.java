import org.semanticweb.owlapi.model.*;

import java.util.List;

public class CreateComplectOfProtection {

    public static void CreateComplect(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager) {
        int l = 1;
        for (OWLClass j: protection) {
            //String l1 = l+"";
            String[] linesName1 = ind.toString().split("#");
            String[] linesName = linesName1[1].split(">");
            String h = j.getIRI().getShortForm().toString().concat(l+"");
            String indName = linesName[0].concat(h);
            OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));
            OWLObjectProperty protect = df.getOWLObjectProperty(IRI.create(ns+"protect"));

            OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
            OWLAxiom protInd = df.getOWLClassAssertionAxiom(j, indProtection);
            AddAxiom prot = new AddAxiom(ont,protInd);
            manager.applyChange(prot);

            OWLAxiom prortected = df.getOWLObjectPropertyAssertionAxiom(isProtectedBy,ind,indProtection);
            // добавление аксиомы в онтологию
            AddAxiom protBy= new AddAxiom(ont,prortected);
            //сохранение изменений
            manager.applyChange(protBy);

            OWLAxiom protectAx = df.getOWLObjectPropertyAssertionAxiom(protect,indProtection,ind);
            // добавление аксиомы в онтологию
            AddAxiom protAd= new AddAxiom(ont,protectAx);
            //сохранение изменений
            manager.applyChange(protAd);
            l++;

        }
    }
}
