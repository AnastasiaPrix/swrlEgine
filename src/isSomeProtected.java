import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;
import java.util.List;

public class isSomeProtected {
    public static OWLIndividual x = null;

    public static boolean IsProtected(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, List<OWLIndividual> listEq, OWLIndividual startEq) {
        boolean f = false;
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns + "cnOf"));
        OWLClassExpression CBR = df.getOWLClass(IRI.create(ns + "XCBR"));
        OWLClassExpression XSWI = df.getOWLClass(IRI.create(ns + "XSWI"));
        OWLClassExpression TCTR = df.getOWLClass(IRI.create(ns + "TCTR"));
        OWLClassExpression TVTR = df.getOWLClass(IRI.create(ns + "TVTR"));
        OWLClassExpression ZREA = df.getOWLClass(IRI.create(ns + "ZREA"));
        OWLClassExpression ZCAB = df.getOWLClass(IRI.create(ns + "ZCAB"));
        for (OWLIndividual i : EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> y = EntitySearcher.getTypes(i, ont);
            if (!(y.contains(XSWI) || y.contains(TCTR) || y.contains(TVTR) || y.contains(ZREA) || y.contains(ZCAB) || y.contains(CBR))) {
                f = true;
            }
            if ((y.contains(ZREA) || y.contains(ZCAB)) && i != startEq) {
                listEq.add(i);
            }
        }
        return f;
    }

//    public static boolean IsBus(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df) {
//        boolean f = false;
//        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns + "cnOf"));
//        OWLClassExpression Bus = df.getOWLClass(IRI.create(ns+"Bus"));
//        for (OWLIndividual i : EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
//            Collection<OWLClassExpression> y = EntitySearcher.getTypes(i, ont);
//            if(y.contains(Bus))
//        }
//        return f;
//    }

//    public static OWLIndividual IsProtectedConnected(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df){
//        // OWLIndividual x = null;
//        OWLIndividual x = null;
//        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
//        OWLClassExpression shortBus = df.getOWLClass(IRI.create(ns+"ShortBus"));
//        OWLClassExpression ZREA = df.getOWLClass(IRI.create(ns+"ZREA"));
//        OWLClassExpression ZCAB = df.getOWLClass(IRI.create(ns+"ZCAB"));
//        //  Collection<OWLIndividual> nodes = EntitySearcher.getObjectPropertyValues(ind, cnOf, ont);
//        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
//            Collection<OWLClassExpression> y =  EntitySearcher.getTypes(i, ont);
//            if (y.contains(ZREA)|| y.contains(ZCAB)||y.contains(shortBus)) {
//                x = i; }
//        }
//        return x;}
}
