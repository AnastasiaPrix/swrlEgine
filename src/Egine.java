import de.derivo.sparqldlapi.Query;
import de.derivo.sparqldlapi.QueryEngine;
import de.derivo.sparqldlapi.QueryResult;
import de.derivo.sparqldlapi.exceptions.QueryEngineException;
import de.derivo.sparqldlapi.exceptions.QueryParserException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapitools.builders.BuilderNamedIndividual;
import org.swrlapi.core.SWRLAPIRule;
import org.swrlapi.core.SWRLRuleEngine;
import org.swrlapi.exceptions.SWRLBuiltInException;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;
import org.swrlapi.sqwrl.SQWRLQuery;
import org.swrlapi.sqwrl.SQWRLQueryEngine;
import org.swrlapi.sqwrl.SQWRLResult;
import org.swrlapi.sqwrl.exceptions.SQWRLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

public class Egine {

    private static Set<String> set1 = new HashSet<>();

    public static void main (String[] args) throws OWLOntologyCreationException, FileNotFoundException, OWLOntologyStorageException, SWRLBuiltInException, SWRLParseException, QueryParserException, QueryEngineException {

        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("C:\\Users\\anast\\Desktop\\magistratura\\project\\ontologies\\ont_PS2_0.owl");
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Load ontology: " + ontology);


        SWRLRuleEngine ruleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);

        // SWRLAPIRule rule1 = ruleEngine.createSWRLRule("create", "AutoTransformers(?x)^isSwitchedBy(?x,?cbr)^swrlx:makeOWLThing(?pdif,?x)-> ofAutoTransformers(?pdif)^isProtectedBy(?x,?pdif)" );
        ruleEngine.infer();

        OWLDataFactory df = manager.getOWLDataFactory();
        IRI docIRI =manager.getOntologyDocumentIRI(ontology);
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
       // OWLClass ptwClass = df.getOWLClass(IRI.create(ontology+"#PTW"));
        OWLObjectProperty hasCN = df.getOWLObjectProperty(IRI.create(docIRI+"#hasCN"));
        String ptwClass = "PTW";
        for (OWLClass cls: ontology.getClassesInSignature()){
            //System.out.println(cls.getIRI().getShortForm());
            if(cls.getIRI().getShortForm().equals(ptwClass)){
                OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
                NodeSet<OWLNamedIndividual> instances =reasoner.getInstances(cls, true);
                System.out.println("The Individuals of class : ");

                for (OWLNamedIndividual i : instances.getFlattened()) {
                    System.out.println(i.getIRI().getFragment());
                    System.out.println(i);
                    //System.out.println( EntitySearcher.getObjectPropertyValues(i,hasCN, ontology ));
                    EntitySearcher.getObjectPropertyValues(i,hasCN, ontology );
                    for( OWLIndividual j: EntitySearcher.getObjectPropertyValues(i,hasCN, ontology ) ){
                       // if(j.getIRI().getShortForm().equals("hasCN")){
                        System.out.println("Property of Individuals  : ");
                           System.out.println(j);
                      //  }
                    }
                }
            }
        }



//        SQWRLQueryEngine queryEngine = SWRLAPIFactory.createSQWRLQueryEngine(ontology);
//
//        SQWRLResult result = queryEngine.runSQWRLQuery("test", "AutoTransformers(?x)^hasPTW(?x,?p)^hasCN(?p,?c) -> sqwrl:selectDistinct(?c)");
      //  SQWRLResult result = queryEngine.runSQWRLQuery("test", "ConnectivityNode(?c) -> sqwrl:selectDistinct(?c)");


//        while (result.next()) {
//            System.out.println("Name: " + result.getNamedIndividual("c").getIRI());
//            String ind = result.getNamedIndividual("c").getIRI().toString().split("#")[1];
//            SQWRLResult result2 = queryEngine.runSQWRLQuery("test2", "ConnectivityNode(result.getNamedIndividual(\"c\").getIRI()) ^ cnOf(ind,?x) -> sqwrl:selectDistinct(?x)");
//            if (result.next()){
//                System.out.println("Name: " + result.getNamedIndividual("x").getIRI());
//            }
//        }



//        StructuralReasonerFactory factory = new StructuralReasonerFactory();
//        OWLReasoner reasoner =factory.createReasoner(ontology);
//        reasoner.precomputeInferences(InferenceType.CLASS_ASSERTIONS,InferenceType.OBJECT_PROPERTY_ASSERTIONS);
//        QueryEngine engine = QueryEngine.create(manager, reasoner, true);
//        Query query = Query.create("PREFIX ont: <http://www.semanticweb.org/anast/ontologies/2019/3/untitled-ontology-22#>\n" +
//                "SELECT DISTINCT ?con WHERE {\n" +
//                "Type(?p, ont:PTW),\n" +
//                "PropertyValue(?p, ont:hasCN, ?con)" +
//                "}");
//       QueryResult result = engine.execute(query);
//        System.out.println("Results:");
//        String[] mas = result.toString().replace("?","#").split("#");
//        System.out.print(result);
//        System.out.println("-------------------------------------------------");
//        System.out.println("Size of result set: " + result.size());
//        ontology.getIndividualsInSignature();
//
//
//        for (int i = 0; i< mas.length; i++){
//            if (i%2 ==0){
//            System.out.println(mas[i]);
//            set1.add(mas[i]);
//            // System.out.println("-------------------------------------------------");
//        } }
//






//        for( String i: set1) {
//            Query.create("PREFIX ont: <http://www.semanticweb.org/anast/ontologies/2019/3/untitled-ontology-22#>\n" +
//                    "SELECT DISTINCT ?x WHERE {\n" +
//                    "Type(?con, ont:ConnectivityNode),\n" +
//                    "PropertyValue(?con, ont:cnOf, PIGGI/500/K_4/connvtyNode3^^xsd:string  )" +
//                    "}");
//        }



        OutputStream owl = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_PS2.owl");
        manager.saveOntology(ontology, owl);



    }


}
