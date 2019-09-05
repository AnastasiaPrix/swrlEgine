import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.util.Collection;

public class getValuesFromProperty {


    public static Collection<OWLLiteral> getValues(OWLIndividual ind, OWLOntology ont, OWLDataProperty property) {
        Collection<OWLLiteral> x = null;
        System.out.println("Values from Property : "+ property);
        x =  EntitySearcher.getDataPropertyValues(ind, property, ont);

        return x;}
}
