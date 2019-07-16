import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;

public class connectionWith {


    public static Collection<OWLIndividual> hasConnection(OWLIndividual ind, OWLOntology ont, String ns, OWLDataFactory df){
        OWLObjectProperty connection = df.getOWLObjectProperty(IRI.create(ns+"connectedWith"));
        Collection<OWLIndividual> x = EntitySearcher.getObjectPropertyValues(ind, connection, ont);
//            for (OWLIndividual j : x) {
//                System.out.println(j);
//               // System.out.println("-----------------");
//
//            }

    return x;}
}
