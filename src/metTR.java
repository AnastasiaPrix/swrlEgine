import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;
import ru.smarteps.scl.*;

import java.util.List;
import java.util.Set;

public class metTR {
    private static List<TTerminal> Terminals;
    private static List<String> cNN;
    private static List<String> cCN;
/////////// !!!!!!!!!!!!!!!!!!!
   // public static void MetodTerm(TPowerTransformer powerTransformer, Individual inda, ObjectProperty hasCN, ObjectProperty hasTerminal, OntModel model, String NS, DatatypeProperty hasName, Set<Individual> namesIndividual) {
/////////// !!!!!!!!!!!!!!!!!!!

        public static void MetodTerm(TPowerTransformer powerTransformer, Individual inda, ObjectProperty hasCN, OntModel model, String NS, DatatypeProperty hasName, Set<Individual> namesIndividual) {

            // Terminals = conductingEquipment.getTerminal();

        OntClass voltageLevelClass = model.getOntClass(NS + "VoltageLevel"); ///ON VOOBSHE NUGEN??????
            /////////// !!!!!!!!!!!!!!!!!!!
        // OntClass TerminalClass = model.getOntClass(NS + "Terminal");
            /////////// !!!!!!!!!!!!!!!!!!!
        OntClass connectivityNodeClass = model.getOntClass(NS + "ConnectivityNode");
        OntClass PTWClass = model.getOntClass(NS + "PTW");
        OntClass YEFNClass = model.getOntClass(NS + "YEFN");
        OntClass ZBSHClass = model.getOntClass(NS + "ZBSH");
        OntClass YLTCClass = model.getOntClass(NS + "YLTC");
        OntClass YPSHClass = model.getOntClass(NS + "YPSH");
        ObjectProperty hasPTW = model.getObjectProperty(NS + "hasPTW");
        ObjectProperty partOf = model.getObjectProperty(NS + "partOf");
        ObjectProperty hasVoltageLevel = model.getObjectProperty(NS + "hasVoltageLevel");
        DatatypeProperty hasVoltage = model.getDatatypeProperty(NS+"hasVoltage");
       // DatatypeProperty numberOfPTW = model.getDatatypeProperty(NS+"numberOfPtw");
//
        String v = "v_";
        for (TTransformerWinding winding : powerTransformer.getTransformerWinding()) {
            Individual PTWIndividual = model.createIndividual(NS + winding.getName() + "_" + powerTransformer.getName(), PTWClass);
            namesIndividual.add(PTWIndividual);

            String voltage1 = winding.getTerminal().get(0).getVoltageLevelName();
            String voltage = v.concat(voltage1);
            OntClass U = model.getOntClass(NS + voltage);
            Individual voltageIndividual = model.createIndividual(NS + voltage, U);

            PTWIndividual.addProperty(hasVoltage, voltage1, XSDDatatype.XSDinteger);
            PTWIndividual.addProperty(hasVoltageLevel,voltageIndividual);
          //  voltagePTW.put(PTWIndividual, Integer.parseInt(voltage1));
            namesIndividual.add(voltageIndividual);
            inda.addProperty(hasVoltageLevel, voltageIndividual);
            inda.addProperty(hasVoltage,voltage1, XSDDatatype.XSDinteger);
            inda.addProperty(hasPTW, PTWIndividual);
            for (TTerminal ptwTerminals : winding.getTerminal()) {
                String term = ptwTerminals.getName() + "_" + winding.getName();
                String CN = winding.getTerminal().get(0).getConnectivityNode();
                Individual CNDIS = model.createIndividual(NS + CN, connectivityNodeClass);
                namesIndividual.add(CNDIS);
                /////////// !!!!!!!!!!!!!!!!!!!
              // Individual ptwTerminalIndividual = model.createIndividual(NS + term, TerminalClass);
               // namesIndividual.add(ptwTerminalIndividual);
                /////////// !!!!!!!!!!!!!!!!!!!
                PTWIndividual.addProperty(hasCN, CNDIS);
                /////////// !!!!!!!!!!!!!!!!!!!
              //  PTWIndividual.addProperty(hasTerminal, ptwTerminalIndividual);
                /////////// !!!!!!!!!!!!!!!!!!!


                for (TLNode trlnode : powerTransformer.getLNode()) {
                    List<String> LTClass = trlnode.getLnClass();
                    if (LTClass.contains("YLTC")) {
                        Individual YLTCIndividual = model.createIndividual(NS + trlnode.getLnClass().toString(), YLTCClass);
                        YLTCIndividual.addProperty(partOf, inda);
                        namesIndividual.add(YLTCIndividual);
                    }
                    if (LTClass.contains("YPSH")) {
                        Individual YPSHIndividual = model.createIndividual(NS + trlnode.getLnClass().toString(), YPSHClass);
                        YPSHIndividual.addProperty(partOf, inda);
                        namesIndividual.add(YPSHIndividual);
                    }
                    if (LTClass.contains("YEFN")) {
                        Individual YEFNIndividual = model.createIndividual(NS + trlnode.getLnClass().toString(), YEFNClass);
                        YEFNIndividual.addProperty(partOf, inda);
                        namesIndividual.add(YEFNIndividual);
                    }
                    if (LTClass.contains("ZBSH")) {
                        Individual ZBSHIndividual = model.createIndividual(NS + trlnode.getLnClass().toString(), ZBSHClass);
                        ZBSHIndividual.addProperty(partOf, inda);
                        namesIndividual.add(ZBSHIndividual);
                    }
                }
            }
        }

        return;
    }
}
