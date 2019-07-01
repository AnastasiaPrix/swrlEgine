import org.semanticweb.owlapi.model.*;

public class AxiomsAdding {
    public static void adding(OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, OWLIndividual ind1, OWLIndividual ind2, OWLObjectProperty property){
        OWLAxiom Ax = df.getOWLObjectPropertyAssertionAxiom(property,ind1,ind2);
        AddAxiom Ad= new AddAxiom(ont,Ax);
        manager.applyChange(Ad);

    }
    public static void AddingClass(OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, OWLIndividual ind1, OWLClass cl){
        OWLAxiom Ax = df.getOWLClassAssertionAxiom(cl,ind1);
        AddAxiom Ad= new AddAxiom(ont,Ax);
        manager.applyChange(Ad);

    }
    public static void AddingData(OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, OWLIndividual ind1, int value, OWLDataProperty property){
        OWLAxiom Ax = df.getOWLDataPropertyAssertionAxiom(property,ind1,value);
        AddAxiom Ad= new AddAxiom(ont,Ax);
        manager.applyChange(Ad);

    }
}
