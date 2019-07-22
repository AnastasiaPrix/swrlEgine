import org.semanticweb.owlapi.model.*;

import java.util.List;

public class CreateComplectOfProtection {

    public static int CreateComplect(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager, int l) {
       // int l = 1;
        for (OWLClass j: protection) {
            //String l1 = l+"";
            String[] linesName1 = ind.toString().split("#");
            String[] linesName = linesName1[1].split(">");
            String h = j.getIRI().getShortForm().toString().concat("_"+l);
            String indName = linesName[0].concat("_").concat(h);
            OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));
            OWLObjectProperty protect = df.getOWLObjectProperty(IRI.create(ns+"protect"));

            OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
            AxiomsAdding.AddingClass(ont,manager,df,indProtection,j);
            AxiomsAdding.adding(ont,manager,df,ind,indProtection,isProtectedBy);
            AxiomsAdding.adding(ont,manager,df,indProtection,ind,protect);
            l++;

        }
        return l;
    }
}
