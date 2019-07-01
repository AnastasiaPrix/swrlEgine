import org.semanticweb.owlapi.model.*;

import java.util.List;

public class CreateComplectForSeveral {

    public static void CreateComplect_2(OWLNamedIndividual ind, int number, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager) {
        int l = 1;
        for (OWLClass j: protection) {
            //String l1 = l+"";
            String[] linesName1 = ind.toString().split("#");
            String[] linesName = linesName1[1].split(">");
            String h = j.getIRI().getShortForm().toString().concat("_"+l);
            String indName = linesName[0].concat("_").concat(h);
            OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));
            OWLObjectProperty protect = df.getOWLObjectProperty(IRI.create(ns+"protect"));
            OWLDataProperty type = df.getOWLDataProperty(IRI.create(ns+"voltageType"));

            OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
            AxiomsAdding.AddingClass(ont,manager,df,indProtection,j);
            AxiomsAdding.adding(ont,manager,df,ind,indProtection,isProtectedBy);
            AxiomsAdding.adding(ont,manager,df,indProtection,ind,protect);

            if (number ==1 || number ==3){
                AxiomsAdding.AddingData(ont,manager,df,indProtection,2,type);

            }
            if (number ==2){
                AxiomsAdding.AddingData(ont,manager,df,indProtection,3,type);

            }
            if (number ==4 || number == 6){
                AxiomsAdding.AddingData(ont,manager,df,indProtection,1,type);

            }
            l++;

        }
    }
}
