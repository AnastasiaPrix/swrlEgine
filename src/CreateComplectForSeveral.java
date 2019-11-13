import org.semanticweb.owlapi.model.*;

import java.util.ArrayList;
import java.util.List;

public class CreateComplectForSeveral {

    public static int CreateComplect_2(OWLNamedIndividual ind, int number, OWLOntology ont, String ns, List<OWLClass> protection, OWLDataFactory df, OWLOntologyManager manager, int l , List<OWLClass> mainList, List<OWLClass> reserveList, List<OWLClass> technologicalList, List<OWLClass> voltageTypeProt, List<OWLClass> curVoltProt) {
        OWLDataProperty typeOfControlledValue = df.getOWLDataProperty(IRI.create(ns+"typeOfControlledValue"));
        for (OWLClass j: protection) {
            String[] linesName1 = ind.toString().split("#");
            String[] linesName = linesName1[1].split(">");
            String h = j.getIRI().getShortForm().toString().concat("_"+l);
            String indName = linesName[0].concat("_").concat(h);
            OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns+"isProtectedBy"));
            OWLObjectProperty mProtect = df.getOWLObjectProperty(IRI.create(ns+"mainProtect"));
            OWLObjectProperty rProtect = df.getOWLObjectProperty(IRI.create(ns+"reserveProtect"));
            OWLObjectProperty tProtect = df.getOWLObjectProperty(IRI.create(ns+"techProtect"));
            OWLDataProperty type = df.getOWLDataProperty(IRI.create(ns+"voltageType"));

            OWLIndividual indProtection = df.getOWLNamedIndividual(IRI.create(ns + indName));
            AxiomsAdding.AddingClass(ont,manager,df,indProtection,j);
            AxiomsAdding.adding(ont,manager,df,ind,indProtection,isProtectedBy);

            if (mainList.contains(j)) {
                AxiomsAdding.adding(ont,manager,df,indProtection,ind,mProtect);
            }

            if (reserveList.contains(j)) {
                AxiomsAdding.adding(ont,manager,df,indProtection,ind,rProtect);
            }
            if (technologicalList.contains(j)) {
                AxiomsAdding.adding(ont,manager,df,indProtection,ind,tProtect);
            }

            if (curVoltProt.contains(j)){
                AxiomsAdding.AddingData(ont,manager,df,indProtection,0,typeOfControlledValue);
                AxiomsAdding.AddingData(ont,manager,df,indProtection,1,typeOfControlledValue);
            }
            if (voltageTypeProt.contains(j)){
                AxiomsAdding.AddingData(ont,manager,df,indProtection,1,typeOfControlledValue);
            }

            if (number ==1 || number ==3 || number==7){
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
        return l;
    }
}
