import org.semanticweb.owlapi.model.*;

import java.util.Collection;
import java.util.List;


public class checkForCBRWithDis {

    public static void hasConectionWithDis (OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, List<OWLIndividual> cbrInd, List<OWLIndividual> listTCTR){
        OWLDataProperty breakerWithDis = df.getOWLDataProperty(IRI.create(ns+"breakerWithDis"));
        OWLClass TCTR = df.getOWLClass(IRI.create(ns+"TCTR"));
        for (OWLIndividual cbr: cbrInd){
            Collection <OWLLiteral> listProperty = getValuesFromProperty.getValues(cbr,ont,breakerWithDis);
            if (!listProperty.isEmpty()){
                lookFor.getSomething(ind,ont,ns,df,null,false, TCTR,listTCTR);
            }
        }
    }
}
