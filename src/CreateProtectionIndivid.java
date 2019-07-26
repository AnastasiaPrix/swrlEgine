import org.semanticweb.owlapi.model.*;
import java.util.List;

public class CreateProtectionIndivid {
    public static void CreateProtection(OWLNamedIndividual ind, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager, int variant, int k, List<OWLClass> mainList, List<OWLClass> reserveList, List<OWLClass> technologicalList) {

        // задание имени идивида
        String[] linesName1 = ind.toString().split("#");
        String[] linesName = linesName1[1].split(">");
        String h = protection.get(variant).getIRI().getShortForm().toString();
        String indName = linesName[0].concat(h+"_"+k);

        OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));
        OWLObjectProperty mProtect = df.getOWLObjectProperty(IRI.create(ns+"mainProtect"));
        OWLObjectProperty rProtect = df.getOWLObjectProperty(IRI.create(ns+"reserveProtect"));
        OWLObjectProperty tProtect = df.getOWLObjectProperty(IRI.create(ns+"techProtect"));
        OWLDataProperty protType = df.getOWLDataProperty(IRI.create(ns+"typeOfProtection"));


        OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
        AxiomsAdding.AddingClass(ont,manager,df,indProtection,protection.get(variant));
        AxiomsAdding.adding(ont,manager,df,ind,indProtection,isProtectedBy);
       // AxiomsAdding.adding(ont,manager,df,indProtection,ind,protect);

        if (mainList.contains(protection.get(variant))) {
           // AxiomsAdding.AddingData(ont,manager,df,indProtection,0,protType);
            AxiomsAdding.adding(ont,manager,df,indProtection,ind,mProtect);
        }

        if (reserveList.contains(protection.get(variant))) {
            AxiomsAdding.AddingData(ont,manager,df,indProtection,1,protType);
            AxiomsAdding.adding(ont,manager,df,indProtection,ind,rProtect);
        }
        if (technologicalList.contains(protection.get(variant))) {
            //AxiomsAdding.AddingData(ont,manager,df,indProtection,2,protType);
            AxiomsAdding.adding(ont,manager,df,indProtection,ind,tProtect);
        }
}
}
