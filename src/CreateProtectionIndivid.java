import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;
import java.util.List;

public class CreateProtectionIndivid {
    public static void CreateProtection(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager, int variant) {

        // задание имени идивида
        String[] linesName1 = ind.toString().split("#");
        String[] linesName = linesName1[1].split(">");
        String h = protection.get(variant).getIRI().getShortForm().toString();
        String indName = linesName[0].concat(h);

        OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));

//    создание индивида
        OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns+indName));
        // создание аксиомы для для задания класса индивида
        OWLAxiom protLineBase = df.getOWLClassAssertionAxiom(protection.get(variant), indProtection);
        // добавление аксиомы в онтологию
        AddAxiom prot= new AddAxiom(ont,protLineBase);
        //сохранение изменений
        manager.applyChange(prot);


      OWLAxiom prortected = df.getOWLObjectPropertyAssertionAxiom(isProtectedBy,ind,indProtection);
    // добавление аксиомы в онтологию
       AddAxiom protBy= new AddAxiom(ont,prortected);
    //сохранение изменений
        manager.applyChange(protBy);
}
}
