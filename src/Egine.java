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

        // загрузка онтологии с помощью менеджера файлов
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        // File file = new File("C:\\Users\\anast\\Desktop\\magistratura\\project\\ontologies\\ont_PS2_pig.owl");
         File file = new File("C:\\Users\\anast\\Desktop\\magistratura\\project\\ontologies\\ont_PS3_pig.owl");
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Load ontology: " + ontology);

        // создание механизма правил SWRL
        SWRLRuleEngine ruleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
        //запуск правил в онтологии

        // SWRLAPIRule ruleVoltage = ruleEngine.createSWRLRule("hasVoltage", "Equipment(?e) ^ hasVoltageLevel(?e,?v) ^ v_110(?v) -> hasVoltage(?e,110)");
        ruleEngine.infer();

        SWRLAPIRule rule1 = ruleEngine.createSWRLRule("set0" ,"Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule2 = ruleEngine.createSWRLRule("set1" ,"Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,1)  -> setOfProtection(?p, 1)");
        SWRLAPIRule rule3 = ruleEngine.createSWRLRule("set0_1" ,"Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule4 = ruleEngine.createSWRLRule("set2" ,"Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,1)  -> setOfProtection(?p, 2)");

        ruleEngine.infer();




        OutputStream owl = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_PS3_pig.owl");
      //  OutputStream owl = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_PS2_pig.owl");
        manager.saveOntology(ontology, owl);



    }


}
