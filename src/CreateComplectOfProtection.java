import org.semanticweb.owlapi.model.*;

import java.util.List;

public class CreateComplectOfProtection {

    public static int CreateComplect(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager, int l, List<OWLClass> mainList, List<OWLClass> reserveList, List<OWLClass> technologicalList) {
       // int l = 1;
        for (OWLClass j: protection) {
            //String l1 = l+"";
            String[] linesName1 = ind.toString().split("#");
            String[] linesName = linesName1[1].split(">");
            String h = j.getIRI().getShortForm().toString().concat("_"+l);
            String indName = linesName[0].concat("_").concat(h);
            OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));
            OWLObjectProperty mProtect = df.getOWLObjectProperty(IRI.create(ns+"mainProtect"));
            OWLObjectProperty rProtect = df.getOWLObjectProperty(IRI.create(ns+"reserveProtect"));
            OWLObjectProperty tProtect = df.getOWLObjectProperty(IRI.create(ns+"techProtect"));
            OWLDataProperty protType = df.getOWLDataProperty(IRI.create(ns+"typeOfProtection"));
            OWLClass RS = df.getOWLClass(IRI.create(ns+"RS"));


            OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
            AxiomsAdding.AddingClass(ont,manager,df,indProtection,j);
            AxiomsAdding.adding(ont,manager,df,ind,indProtection,isProtectedBy);
            //AxiomsAdding.adding(ont,manager,df,indProtection,ind,protect);

            if (protection.contains(RS)){
                AxiomsAdding.adding(ont,manager,df,indProtection,ind,mProtect);
            }
            else {

            if (mainList.contains(j)) {
               // AxiomsAdding.AddingData(ont,manager,df,indProtection,0,protType);
                AxiomsAdding.adding(ont,manager,df,indProtection,ind,mProtect);
            }

            if (reserveList.contains(j)) {
                //AxiomsAdding.AddingData(ont,manager,df,indProtection,1,protType);
                AxiomsAdding.adding(ont,manager,df,indProtection,ind,rProtect);
            }
            if (technologicalList.contains(j)) {
                AxiomsAdding.adding(ont,manager,df,indProtection,ind,tProtect);
               // AxiomsAdding.AddingData(ont,manager,df,indProtection,2,protType);
            }
            }
            l++;

        }
        return l;
    }
}
