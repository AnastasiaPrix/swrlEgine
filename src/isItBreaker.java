import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;
import java.util.HashSet;

public class isItBreaker {
    public static boolean f = false;
   // public  static OWLIndividual x = null;
    public static OWLIndividual hasBreaker(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
        OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
        OWLIndividual x = null;
        //  Collection<OWLIndividual> nodes = EntitySearcher.getObjectPropertyValues(ind, cnOf, ont);
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
            if (gg.contains(CBR)) {
                x = i;
//            for (OWLClassExpression j : EntitySearcher.getTypes(i, ont)) {
//                // System.out.println(j);
//                if (j.equals(CBR)) {
//                    x = i;
//                    f = true;
//                }

            }
        }
    return x;}
}
