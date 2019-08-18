import org.apache.jena.ontology.*;
import ru.smarteps.scl.TConductingEquipment;
import ru.smarteps.scl.TTerminal;

import java.util.List;
import java.util.Set;


public class metodterma {
    private static List<TTerminal> Terminals;
    private static List<String> cNN;
    private static List<String> cCN;
    /////////// !!!!!!!!!!!!!!!!!!!
    // public static void MetodTerm(TConductingEquipment conductingEquipment, Individual inda, ObjectProperty hasCN, ObjectProperty hasTerminal, DatatypeProperty hasName, OntModel model, String NS, Set<Individual> namesIndividual) {
    /////////// !!!!!!!!!!!!!!!!!!!
        public static void MetodTerm(TConductingEquipment conductingEquipment, Individual inda, ObjectProperty hasCN, DatatypeProperty hasName, OntModel model, String NS, Set<Individual> namesIndividual) {

            Terminals = conductingEquipment.getTerminal();
            /////////// !!!!!!!!!!!!!!!!!!!
       // OntClass TerminalClass = model.getOntClass(NS + "Terminal");
            /////////// !!!!!!!!!!!!!!!!!!!
        OntClass connectivityNodeClass = model.getOntClass(NS + "ConnectivityNode");

        for (int i = 0; i < Terminals.size(); i++) {
            String term = Terminals.get(i).getName() + "_" + conductingEquipment.getName();
            String CN = conductingEquipment.getTerminal().get(i).getConnectivityNode();
            Individual CNDIS = model.createIndividual(NS + CN, connectivityNodeClass);
            namesIndividual.add(CNDIS);
           //  CNDIS.addProperty(hasName, CN ,XSDDatatype.XSDstring);
           //  CNDIS.addProperty(hasName, k+"", XSDDatatype.XSDinteger);
            //model.add(CNDIS, hasName, ResourceFactory.createTypedLiteral(CN, XSDDatatype.XSDstring);

            /////////// !!!!!!!!!!!!!!!!!!!
           // Individual terminal = model.createIndividual(NS + term, TerminalClass);
           // namesIndividual.add(terminal);
            /////////// !!!!!!!!!!!!!!!!!!!

            inda.addProperty(hasCN, CNDIS);
            /////////// !!!!!!!!!!!!!!!!!!!
           // inda.addProperty(hasTerminal, terminal);
            /////////// !!!!!!!!!!!!!!!!!!!

        }
        return;
    }
}
