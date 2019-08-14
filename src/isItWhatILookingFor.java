import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

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
            if(gg.contains(clas) && !clasCollection.contains(i) ){
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
                if (!listCBR.contains(i)){
            listCBR.add(i);}
            }
            else if(gg.contains(TCTR) && !listTCTR.contains(i)){
                listTCTR.add(i);
            }
            else if(gg.contains(TVTR) && !listTVTR.contains(i)){
                listTVTR.add(i);
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
    public static OWLIndividual foundConnectedEq(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df,  OWLClassExpression clas, List<OWLIndividual> listCBR, OWLIndividual base ){
        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
        OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
        OWLClassExpression SWI = df.getOWLClass(IRI.create(ns+"XSWI"));
        OWLIndividual x = null;

        for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)) {
            System.out.println("i foi=und "+ i);
            Collection<OWLClassExpression> gg = EntitySearcher.getTypes(i, ont);
            if (gg.contains(clas)  && !gg.contains(CBR)&& !gg.contains(SWI) && !i.equals(base)) {
                x = i;
                listCBR.add(i);
            }
        }

        return x;
    }
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
