import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semarglproject.vocab.OWL;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class lookForBreacker {

//public static boolean f1 = false;
//public static  Collection<OWLIndividual> cnWithCBR = new HashSet<>();
    public static OWLIndividual x = null;
// public  static O
    public static void get2(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df, OWLIndividual basInd, Collection<OWLIndividual> cnWithCBR, boolean f1) {
//        OWLObjectProperty cnOf = df.getOWLObjectProperty(IRI.create(ns+"cnOf"));
//        OWLObjectProperty connect = df.getOWLObjectProperty(IRI.create(ns+"connecteWith"));
//        OWLClassExpression CBR = df.getOWLClass(IRI.create(ns+"XCBR"));
//        Collection<OWLIndividual> cnWithBreacker = new HashSet<>();
//      //  Collection<OWLIndividual> nodes = EntitySearcher.getObjectPropertyValues(ind, cnOf, ont);
//       for( OWLIndividual i: EntitySearcher.getObjectPropertyValues(ind, cnOf, ont)){
//          for( OWLClassExpression j: EntitySearcher.getTypes(i,ont)){
//              System.out.println(j);
//              if(j.equals(CBR)){
//                  // System.out.println("foundBreacker!" +j);
//                  f =true;
//                 // break;
//              }
//
//          }
//          if (f){
//              System.out.println("foundBreacker!"+ i);
//              cnWithBreacker.add(ind);
//          }
//          else{
//              System.out.println("not foundBreacker!");
//              for( OWLIndividual k: EntitySearcher.getObjectPropertyValues(ind, connect, ont)){
//                  for( OWLIndividual b: get2(k, ont, ns, df)){
//                      cnWithBreacker.add(k);
//                  }
//              }
//
//          }
//
//       }
//    return cnWithBreacker; }
       // Collection<OWLIndividual> cnWithCBR1 = new HashSet<>();
        System.out.println("Welcome to get!!!"+ ind);
       // System.out.println("my x is"+ x);
        x = isItBreaker.hasBreaker(ind,ont,ns,df);
        if (x != null) {
            System.out.println("---------------");
            System.out.println("Found CBR ");
            System.out.println(ind + " hasCBR " + x);
            cnWithCBR.add(ind);
           // basInd = ind;
        }
//       else if(isSomeProtected.IsProtected(ind, ont, ns, df) && f1){
//            System.out.println("found protected Eqipment with" + ind );
//        }
        else{
            f1 =true;
            System.out.println("!!!!!!!!!!!!!");
            System.out.println(ind);
            System.out.println("!!!!!!!!!!!!!");
            for (OWLIndividual i: connectionWith.hasConnection(ind,ont,ns,df)) {
                System.out.println("----------------");
                if( !i.equals(basInd)){
                    get2(i, ont, ns, df, ind, cnWithCBR, f1);
//                    for (OWLIndividual k : get2(i, ont, ns, df, ind, cnWithCBR)) {
//                        cnWithCBR.add(k);
//                    }
                }

            }

        }
       // return cnWithCBR;
    }
}
