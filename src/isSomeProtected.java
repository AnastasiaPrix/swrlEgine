import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;

public class isSomeProtected {
   // public static boolean f = false;
    public  static OWLIndividual x = null;
    public static boolean IsProtected(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df){
       // OWLIndividual x = null;
        boolean f = false;
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
       // OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
        OWLClassExpression XSWI = df.getOWLClass(IRI.create(ns+"XSWI"));
        OWLClassExpression TCTR = df.getOWLClass(IRI.create(ns+"TCTR"));
        OWLClassExpression TVTR = df.getOWLClass(IRI.create(ns+"TVTR"));

        //  Collection<OWLIndividual> nodes = EntitySearcher.getObjectPropertyValues(ind, cnOf, ont);
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> y =  EntitySearcher.getTypes(i, ont);
            if (!(y.contains(XSWI)||y.contains(TCTR)||y.contains(TVTR))) {
//            for (OWLClassExpression j : y) {
//               // System.out.println(j);
//                if (!(j.equals(XSWI) || j.equals(TCTR) || j.equals(TVTR) )) {
//                    x = i;
//                    f = true;
//                }
//
//            }
           f = true; }
        }
        return f;}
}
