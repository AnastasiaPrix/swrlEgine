import org.semanticweb.owlapi.model.*;
import uk.ac.manchester.cs.owl.owlapi.OWLIndividualImpl;

import java.util.List;

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
    public static void addingSeveral(OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, OWLIndividual ind1, List<OWLIndividual> ind2, OWLObjectProperty property){
        for (OWLIndividual i: ind2) {
            OWLAxiom Ax = df.getOWLObjectPropertyAssertionAxiom(property, ind1, i);
            AddAxiom Ad = new AddAxiom(ont, Ax);
            manager.applyChange(Ad);
        }
    }
    public static void addingSeveral2(OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, List<OWLIndividual> ind1, OWLIndividual ind2, OWLObjectProperty property){
        for (OWLIndividual i: ind1) {
            OWLAxiom Ax = df.getOWLObjectPropertyAssertionAxiom(property, ind2, i);
            AddAxiom Ad = new AddAxiom(ont, Ax);
            manager.applyChange(Ad);
        }
    }
    public static void addingConnections(OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, List<OWLIndividual> indList, OWLObjectProperty property){
        for (int i=1; i < indList.size(); i++) {
            OWLAxiom Ax = df.getOWLObjectPropertyAssertionAxiom(property, indList.get(i-1), indList.get(i));
            AddAxiom Ad = new AddAxiom(ont, Ax);
            manager.applyChange(Ad);
        }
    }
    public static void changingAxiomsData (OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, OWLIndividual ind1, int value, int value2, OWLDataProperty property){
        OWLAxiom Ax = df.getOWLDataPropertyAssertionAxiom(property,ind1,value);
        RemoveAxiom Rem= new RemoveAxiom(ont,Ax);
        manager.applyChange(Rem);
        OWLAxiom Ax2 = df.getOWLDataPropertyAssertionAxiom(property,ind1,value2);
        AddAxiom Ad= new AddAxiom(ont,Ax2);
        manager.applyChange(Ad);
    }

}
