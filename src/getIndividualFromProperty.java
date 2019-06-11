import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;
import java.util.Set;

public class getIndividualFromProperty {


    public static Collection<OWLIndividual> getIndivid(OWLIndividual ind, OWLOntology ont, OWLObjectProperty property) {
        System.out.println("Individual from Property : "+ property);
        Collection<OWLIndividual> x =  EntitySearcher.getObjectPropertyValues(ind, property, ont);
        for (OWLIndividual j : x) {
//                        System.out.println("Individual from Property : ");
                      System.out.println("##############");
                      System.out.println(j);
                         System.out.println("##############");
//                        //  }
        }
    return x;}
}
