import org.semanticweb.owlapi.model.*;
import java.util.List;

public class CreateProtectionIndivid {
    public static void CreateProtection(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager, int variant, int k) {

        // задание имени идивида
        String[] linesName1 = ind.toString().split("#");
        String[] linesName = linesName1[1].split(">");
        String h = protection.get(variant).getIRI().getShortForm().toString();
        String indName = linesName[0].concat(h);

        OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));
        OWLObjectProperty protect = df.getOWLObjectProperty(IRI.create(ns+"protect"));

        OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
        AxiomsAdding.AddingClass(ont,manager,df,indProtection,protection.get(variant));
        AxiomsAdding.adding(ont,manager,df,ind,indProtection,isProtectedBy);
        AxiomsAdding.adding(ont,manager,df,indProtection,ind,protect);
}
}
