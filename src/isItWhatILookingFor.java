import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class isItWhatILookingFor {
   // public  static OWLIndividual x = null;
    public static OWLIndividual hasNecessary(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, OWLClassExpression clas, List<OWLIndividual> clasCollection){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
       // OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
        OWLIndividual x = null;
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
//            if (gg.contains(CBR)) {
//                x = i;
//            }
            if(gg.contains(clas)){
                clasCollection.add(i);
                x = i;
            }
        }
    return x;}
    public static boolean foundBreaker(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, List<OWLIndividual> listTCTR, List<OWLIndividual> listTVTR, List<OWLIndividual> listCBR ){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
        OWLClassExpression TCTR = df.getOWLClass(IRI.create(ns+"TCTR"));
        OWLClassExpression TVTR = df.getOWLClass(IRI.create(ns+"TVTR"));
        OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
        boolean x = false;
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
            if (gg.contains(CBR)) {
                x = true;
                listCBR.add(i);
            }
            else if(gg.contains(TCTR) && !listTCTR.contains(i)){
                listTCTR.add(i);
              //  System.out.println("found TCTR "+ i);
            }
            else if(gg.contains(TVTR)){
                listTVTR.add(i);
            }
        }

        return x;
    }
}
