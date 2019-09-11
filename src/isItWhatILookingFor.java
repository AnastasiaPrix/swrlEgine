import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class isItWhatILookingFor {
   // public  static OWLIndividual x = null;
    public static OWLIndividual hasNecessary(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, OWLClassExpression clas, List<OWLIndividual> clasCollection){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
        OWLIndividual x = null;
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
            if(gg.contains(clas) ){
                x = i;
                if (!clasCollection.contains(i)){
                    clasCollection.add(i);
                }
            }
        }
    return x;}
    public static boolean foundBreaker(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, List<OWLIndividual> listTCTR, List<OWLIndividual> listTVTR, List<OWLIndividual> listCBR ){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
        OWLClassExpression TCTR = df.getOWLClass(IRI.create(ns+"TCTR"));
        OWLClassExpression TVTR = df.getOWLClass(IRI.create(ns+"TVTR"));
        OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
        OWLClassExpression SWI = df.getOWLClass(IRI.create(ns+"XSWI"));
        OWLDataProperty breakerWithDis = df.getOWLDataProperty(IRI.create(ns+"breakerWithDis"));
        Collection<OWLLiteral> listProperty = new ArrayList<>();
        boolean f = true;
        boolean x = false;
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
            if (gg.contains(CBR)) {
                x = true;
                listProperty = getValuesFromProperty.getValues(i,ont,breakerWithDis);
                if (!listCBR.contains(i)){
                        listCBR.add(i);}
            }
            if(gg.contains(TCTR) && !listTCTR.contains(i)){
                listTCTR.add(i);
                f =false;
            }
            if(gg.contains(TVTR) && !listTVTR.contains(i)){
                listTVTR.add(i);
            }

        }
        if (x && f){
            if (listProperty.isEmpty()){
                System.out.println("FOUND BREAKER WITHOUT TCTR!!!!!!!" + ind);
                lookFor.getSomething(ind,ont,ns,df,null,false, TCTR,listTCTR);
            }
        }

        return x;
    }
    public static OWLIndividual foundConnected(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df,  OWLClassExpression clas, List<OWLIndividual> listCBR, OWLIndividual base ){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
        OWLIndividual x = null;
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
            if (gg.contains(clas)&& !listCBR.contains(i) && !i.equals(base)) {
                x = i;
                listCBR.add(i);
            }
        }

        return x;
    }
//    public static boolean swiWithoutTCTR(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df ){
//        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
//        OWLClassExpression TCTR = df.getOWLClass(IRI.create(ns+"TCTR"));
//        OWLClassExpression SWI = df.getOWLClass(IRI.create(ns+"XSWI"));
//        boolean x = true;
//        boolean f = false;
//
//        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
//            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
//            if (gg.contains(SWI)) {
//                for( OWLIndividual j : connectionWith.hasConnection(ind, ont, ns, df)) {
//                        for (OWLIndividual k : EntitySearcher.getObjectPropertyValues(j, cnOf, ont)) {
//                            Collection<OWLClassExpression> gg1 = EntitySearcher.getTypes(k, ont);
//                            if (gg1.contains(TCTR)) {
//                                x = false;
//                            }
//                        }
//                }
//            }
//        }
//        if (!x) {
//            f = true;
//            System.out.println("FOUND SWITCH WITHOUT TCTR ");
//        }
//
//        return f;
//    }
    public static boolean foundBreaker2(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, List<OWLIndividual> listTCTR, List<OWLIndividual> listTVTR, List<OWLIndividual> listCBR ){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
        OWLClassExpression TCTR = df.getOWLClass(IRI.create(ns+"TCTR"));
        OWLClassExpression TVTR = df.getOWLClass(IRI.create(ns+"TVTR"));
        OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
        boolean x = false;
        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
            if (gg.contains(TCTR)) {
                x = true;
                if (!listTCTR.contains(i)){
                    listTCTR.add(i);}
            }
            else if(gg.contains(CBR) && !listCBR.contains(i)){
                listCBR.add(i);
                //  System.out.println("found TCTR "+ i);
            }
            else if(gg.contains(TVTR)&& !listTVTR.contains(i)){
                listTVTR.add(i);
            }
        }

        return x;
    }
}
