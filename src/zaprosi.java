import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.io.File;

public class zaprosi {


    public static void main (String[] args) throws OWLOntologyCreationException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("C:\\Users\\anast\\Desktop\\ont_PS2.owl");
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        String ns = "http://www.semanticweb.org/anast/ontologies/2019/3/untitled-ontology-22#";

        OWLDataFactory df = manager.getOWLDataFactory();
        OWLObjectProperty hasCN = df.getOWLObjectProperty(IRI.create(ns+"hasCN"));
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        String ptwClass = "PTW";
        for (OWLClass cls: ontology.getClassesInSignature()){
            //System.out.println(cls.getIRI().getShortForm());
            if(cls.getIRI().getShortForm().equals(ptwClass)){
                OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
                NodeSet<OWLNamedIndividual> instances =reasoner.getInstances(cls, true);
                System.out.println("The Individuals of class : ");

                for (OWLNamedIndividual i : instances.getFlattened()) {
                    System.out.println(i.getIRI().getFragment());
                    System.out.println(i.getSignature());
                    for( OWLIndividual j: EntitySearcher.getObjectPropertyValues(i, hasCN, ontology)){
                        // if(j.getIRI().getShortForm().equals("hasCN")){
                        System.out.println("Individual from Property : ");
                        System.out.println(j);
                        //  }
                    }
                }
            }
        }
    }

}
