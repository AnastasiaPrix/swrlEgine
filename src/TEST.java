import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import ru.smarteps.scl.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;


public class TEST {
    private static TSubstation mySubstation;
    private static TVoltage voltage;
    private static List<TPowerTransformerEnum> ptrTypes;

    public static Set<Individual> namesIndividual = new HashSet<>();
    public static List<Integer> voltageValues = new ArrayList<>();
    public static Set<Individual> voltageeIndividual = new HashSet<>();


    public static void Start() {
        // создание пустой модели
        OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM_MINI_RULE_INF);
        String inputOntFilename = "C:\\Users\\anast\\OneDrive\\Рабочий стол\\magistratura\\project\\ontologies\\ont1_1_4.rdf";

//        ипользование менеджера файлов для нахождения входного файла
        InputStream in = FileManager.get().open(inputOntFilename);
        if (in == null) {
            throw new IllegalArgumentException(
                    "File: " + inputOntFilename + " not found");
        }
        // считывание данных из файла в модель
        model.read(in, null);




        File scdFile = new File("C:\\Users\\anast\\OneDrive\\Рабочий стол\\magistratura\\project\\ontologies\\PIGv10.scd");

        SCL tsub = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(SCL.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            tsub = (SCL) jaxbUnmarshaller.unmarshal(scdFile);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        String NS = "http://www.semanticweb.org/anast/ontologies/2019/3/untitled-ontology-22#";
        OntClass voltageLevelClass = model.getOntClass(NS + "VoltageLevel"); ///ON VOOBSHE NUGEN??????
       // OntClass TerminalClass = model.getOntClass(NS + "Terminal");
        OntClass connectivityNodeClass = model.getOntClass(NS + "ConnectivityNode");
        OntClass YEFNClass = model.getOntClass(NS + "YEFN");
        OntClass ZBSHClass = model.getOntClass(NS + "ZBSH");
        OntClass BusClass = model.getOntClass(NS + "Bus");
        OntClass BusBreakerClass = model.getOntClass(NS + "BusBreaker");
        OntClass bypassBreakerClass = model.getOntClass(NS + "bypassBreaker");
        OntClass fiderBreakerClass = model.getOntClass(NS + "fiderBreaker");
        OntClass selectionalizingBreakerClass = model.getOntClass(NS + "selectionalizingBreaker");
        OntClass XSWIClass = model.getOntClass(NS + "XSWI");
        OntClass ZCABClass = model.getOntClass(NS + "ZCAB");
        OntClass ZGILClass = model.getOntClass(NS + "ZGIL");
        OntClass ZLINClass = model.getOntClass(NS + "ZLIN");
        OntClass IFLClass = model.getOntClass(NS + "ZLIN");
        OntClass PTWClass = model.getOntClass(NS + "PTW");
        OntClass YLTCClass = model.getOntClass(NS + "YLTC");
        OntClass YPSHClass = model.getOntClass(NS + "YPSH");
        OntClass ATRClass = model.getOntClass(NS + "AutoTransformers");
        OntClass TTRClass = model.getOntClass(NS + "ThreeWidningTransformers");
        OntClass TRClass = model.getOntClass(NS + "TwoWidningTransformers");
        OntClass ZCAPClass = model.getOntClass(NS + "ZCAP");
        OntClass ZMOTClass = model.getOntClass(NS + "ZMOT");
        OntClass ZREAClass = model.getOntClass(NS + "ZREA");
        OntClass TCTRClass = model.getOntClass(NS + "TCTR");

        // считывание класса из модели
        OntClass TVTRClass = model.getOntClass(NS + "TVTR");
        // считывние свойства из модели
        ObjectProperty hasVoltageLevel = model.getObjectProperty(NS + "hasVoltageLevel");

        ObjectProperty hasCN = model.getObjectProperty(NS + "hasCN");
        // ObjectProperty hasTerminal = model.getObjectProperty(NS + "hasTerminal");
        ObjectProperty hasPower = model.getObjectProperty(NS + "hasPower");
        ObjectProperty hasPTW = model.getObjectProperty(NS + "hasPTW");
        ObjectProperty hasParts = model.getObjectProperty(NS + "hasParts");
        ObjectProperty connectedWith = model.getObjectProperty(NS + "connectedWith");
        ObjectProperty partOf = model.getObjectProperty(NS + "partOf");


        DatatypeProperty hasName = model.getDatatypeProperty(NS + "hasName");
        DatatypeProperty hasVoltage = model.getDatatypeProperty(NS + "hasVoltage");
        DatatypeProperty voltageType = model.getDatatypeProperty(NS + "voltageType");

        List<TSubstation> substations = tsub.getSubstation();
        mySubstation = substations.get(0);
        String v = "v_";
        for (TVoltageLevel voltageLevel : mySubstation.getVoltageLevel()) {
            voltage = voltageLevel.getVoltage();
            String VOL1 = voltage.getValue().toString();
            voltageValues.add(Integer.parseInt(VOL1));
            String VOL =v.concat(VOL1);
            OntClass U = model.getOntClass(NS + VOL);
            Individual voltageIndividual = model.createIndividual(NS + VOL, U);
            voltageIndividual.addProperty(hasVoltage,VOL1,XSDDatatype.XSDinteger);
            voltageeIndividual.add(voltageIndividual);
            namesIndividual.add(voltageIndividual);
            for (TBay bay : voltageLevel.getBay()) {
                TText text = bay.getText();
                for (TConnectivityNode connectivityNode : bay.getConnectivityNode()) {
                    List<String> CNode = Arrays.asList(connectivityNode.getPathName());
                    Individual cNodeIndividual = model.createIndividual(NS + connectivityNode.getPathName(), connectivityNodeClass);
                    namesIndividual.add(cNodeIndividual);
                }
                if (text != null) {
                    if (text.getContent().toString().equals("[Bus]")) {
                        Individual busIndividual = model.createIndividual(NS + bay.getName(), BusClass);
                        busIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        busIndividual.addProperty(hasVoltage,VOL1,XSDDatatype.XSDinteger);
                        for (TConnectivityNode node: bay.getConnectivityNode()){
                            Individual cNodeB = model.createIndividual(NS+node.getPathName(), connectivityNodeClass);
                            busIndividual.addProperty(hasCN, cNodeB  );
                        }

                       // busIndividual.addProperty(hasCN, bay.getConnectivityNode().toString());
                        namesIndividual.add(busIndividual);
                    }
                }
                for (TConductingEquipment conductingEquipment : bay.getConductingEquipment()) {
                    List<String> basicTypes = Arrays.asList(conductingEquipment.getType());
                    if (basicTypes.contains("EFN")) {
                        Individual EFNIndividual = model.createIndividual(NS + conductingEquipment.getName(), YEFNClass);
                        EFNIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        EFNIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(EFNIndividual);
                        metodterma.MetodTerm(conductingEquipment, EFNIndividual, hasCN, model, NS, namesIndividual); //sozdaet hasCN, hasTerminal, individi terminal
                    }
                    if (basicTypes.contains("PSH")) {
                        Individual PSHIndividual = model.createIndividual(NS + conductingEquipment.getName(), YPSHClass);
                        PSHIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        PSHIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(PSHIndividual);
                        metodterma.MetodTerm(conductingEquipment, PSHIndividual, hasCN, model, NS, namesIndividual); //sozdaet hasCN, hasTerminal, individi terminal
                    }
                    if (basicTypes.contains("CBR")) {
                        TText text1 = conductingEquipment.getText();
                        if (text1 != null) {
                            String TEXT2 = text1.getContent().toString();
                            if (TEXT2.equals("[BusBreaker]")) {
                                Individual BusBreakerIndividual = model.createIndividual(NS + conductingEquipment.getName(), BusBreakerClass);
                                BusBreakerIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                                BusBreakerIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                                namesIndividual.add(BusBreakerIndividual);
                                metodterma.MetodTerm(conductingEquipment, BusBreakerIndividual, hasCN,  model, NS, namesIndividual);
                            }
                            if (TEXT2.equals("[bypassBreaker]")) {
                                Individual bypassBreakerIndividual = model.createIndividual(NS + conductingEquipment.getName(), bypassBreakerClass);
                                bypassBreakerIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                                bypassBreakerIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                                namesIndividual.add(bypassBreakerIndividual);
                                metodterma.MetodTerm(conductingEquipment, bypassBreakerIndividual, hasCN,  model, NS, namesIndividual);
                            }
                            if (TEXT2.equals("[selectionalizingBreaker]")) {
                                Individual selBrIndividual = model.createIndividual(NS + conductingEquipment.getName(), selectionalizingBreakerClass);
                                selBrIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                                selBrIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                                namesIndividual.add(selBrIndividual);
                                metodterma.MetodTerm(conductingEquipment, selBrIndividual, hasCN,  model, NS, namesIndividual);
                            }
                            if (TEXT2.equals("[fiderBreaker]")) {
                                Individual fiderBreakerIndividual = model.createIndividual(NS + conductingEquipment.getName(), fiderBreakerClass);
                                fiderBreakerIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                                fiderBreakerIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                                namesIndividual.add(fiderBreakerIndividual);
                                metodterma.MetodTerm(conductingEquipment, fiderBreakerIndividual, hasCN,  model, NS, namesIndividual);
                            }
                        }
                    }
                    if (basicTypes.contains("DIS")) {
                        Individual XSWIIndividual = model.createIndividual(NS + conductingEquipment.getName(), XSWIClass);
                        XSWIIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        XSWIIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(XSWIIndividual);
                       metodterma.MetodTerm(conductingEquipment, XSWIIndividual, hasCN,  model, NS, namesIndividual);
                    }
                    if (basicTypes.contains("CAB")) {
                        Individual CABIndividual = model.createIndividual(NS + conductingEquipment.getName(), ZCABClass);
                        CABIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        CABIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(CABIndividual);
                        metodterma.MetodTerm(conductingEquipment, CABIndividual, hasCN, model, NS, namesIndividual);
                    }
                    if (basicTypes.contains("GIL")) {
                        Individual GILIndividual = model.createIndividual(NS + conductingEquipment.getName(), ZGILClass);
                        GILIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        GILIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(GILIndividual);
                       metodterma.MetodTerm(conductingEquipment, GILIndividual, hasCN, model, NS, namesIndividual);
                        for (TLNode gillnode : conductingEquipment.getLNode()) {
                            List<String> LTClass = gillnode.getLnClass();
                            if (LTClass.contains("YPSH")) {
                                Individual YPSHIndividual = model.createIndividual(NS + gillnode.getLnClass().toString(), YPSHClass);
                                YPSHIndividual.addProperty(partOf, GILIndividual);
                                namesIndividual.add(YPSHIndividual);
                            }
                        }
                    }
                    if (basicTypes.contains("LIN")) {
                        Individual LINIndividual = model.createIndividual(NS + conductingEquipment.getName(), ZLINClass);
                        LINIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        LINIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(LINIndividual);
                        metodterma.MetodTerm(conductingEquipment, LINIndividual, hasCN,  model, NS, namesIndividual);
                    }
                    if (basicTypes.contains("IFL")) {
                        Individual IFLIndividual = model.createIndividual(NS + conductingEquipment.getName(), IFLClass);
                        IFLIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        IFLIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(IFLIndividual);
                        metodterma.MetodTerm(conductingEquipment, IFLIndividual, hasCN,  model, NS, namesIndividual);
                    }
                    if (basicTypes.contains("CAP")) {
                        Individual CAPIndividual = model.createIndividual(NS + conductingEquipment.getName(), ZCAPClass);
                        CAPIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        CAPIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(CAPIndividual);
                        metodterma.MetodTerm(conductingEquipment, CAPIndividual, hasCN,  model, NS, namesIndividual);
                    }
                    if (basicTypes.contains("MOT")) {
                        Individual MOTIndividual = model.createIndividual(NS + conductingEquipment.getName(), ZMOTClass);
                        MOTIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        MOTIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(MOTIndividual);
                        metodterma.MetodTerm(conductingEquipment, MOTIndividual, hasCN, model, NS, namesIndividual);
                    }
                    if (basicTypes.contains("REA")) {
                        Individual REAIndividual = model.createIndividual(NS + conductingEquipment.getName(), ZREAClass);
                        REAIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        REAIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(REAIndividual);
                        metodterma.MetodTerm(conductingEquipment, REAIndividual, hasCN,  model, NS, namesIndividual);
                        for (TLNode realnode : conductingEquipment.getLNode()) {
                            List<String> LTClass = realnode.getLnClass();
                            if (LTClass.contains("YPSH")) {
                                Individual YPSHIndividual = model.createIndividual(NS + realnode.getLnClass().toString(), YPSHClass);
                                YPSHIndividual.addProperty(partOf, REAIndividual);
                                namesIndividual.add(YPSHIndividual);
                            }
                            if (LTClass.contains("YEFN")) {
                                Individual YEFNIndividual = model.createIndividual(NS + realnode.getLnClass().toString(), YEFNClass);
                                YEFNIndividual.addProperty(partOf, REAIndividual);
                                namesIndividual.add(YEFNIndividual);
                            }
                        }
                    }
                    if (basicTypes.contains("CTR")) {
                        Individual CTRIndividual = model.createIndividual(NS + conductingEquipment.getName(), TCTRClass);
                        CTRIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        CTRIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(CTRIndividual);
                        metodterma.MetodTerm(conductingEquipment, CTRIndividual, hasCN, model, NS, namesIndividual);
                    }
                    if (basicTypes.contains("VTR")) {
                        Individual VTRIndividual = model.createIndividual(NS + conductingEquipment.getName(), TVTRClass);
                        VTRIndividual.addProperty(hasVoltageLevel, voltageIndividual);
                        VTRIndividual.addProperty(hasVoltage,VOL1, XSDDatatype.XSDinteger);
                        namesIndividual.add(VTRIndividual);
                        metodterma.MetodTerm(conductingEquipment, VTRIndividual, hasCN, model, NS, namesIndividual);
                    }
                }
            }
        }
        for (TPowerTransformer powerTransformer : mySubstation.getPowerTransformer()) {
            ptrTypes = Collections.singletonList(powerTransformer.getType());
            TText text3 = powerTransformer.getText();
            if (text3 != null) {
                if (text3.getContent().toString().equals("[AutoTransformers]")) {
                    Individual ATRIndividual = model.createIndividual(NS + powerTransformer.getName(), ATRClass);
                    namesIndividual.add(ATRIndividual);


                    metTR.MetodTerm(powerTransformer, ATRIndividual, hasCN,  model, NS, namesIndividual);
                }
                if (text3.getContent().toString().equals("[ThreeWidningTransformers]")) {
                    Individual TTRIndividual = model.createIndividual(NS + powerTransformer.getName(), TTRClass);
                    namesIndividual.add(TTRIndividual);

                    metTR.MetodTerm(powerTransformer, TTRIndividual, hasCN, model, NS,  namesIndividual);
                }
                if (text3.getContent().toString().equals("[TwoWidningTransformers]")) {
                    Individual TRIndividual = model.createIndividual(NS + powerTransformer.getName(), TRClass);
                    namesIndividual.add(TRIndividual);
                    metTR.MetodTerm(powerTransformer, TRIndividual, hasCN,  model, NS,  namesIndividual);
                }
            }
        }
        int k = 0;
        for (Individual i : namesIndividual) {
            k++;
            i.addProperty(hasName,k+"", XSDDatatype.XSDinteger);
        }

        Collections.sort(voltageValues);
        Collections.reverse(voltageValues);
        int number = 1;
        for (int i=0 ; i<voltageValues.size(); i++) {
            for (Individual j : voltageeIndividual) {
               String[] voltT = j.getURI().toString().split("_");
                int nVolt = Integer.parseInt(voltT[1]);
                if (nVolt == voltageValues.get(i)){

                    j.addProperty(voltageType,number+"",XSDDatatype.XSDinteger);
                }
            }
            if (voltageValues.size()==3){
                number++;
            }
            else if (voltageValues.size()==2){
                number= number+2;
            }
        }
        // сохранение (запись) модели в выходной файл
        try {
           // FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\anast\\Desktop\\magistratura\\project\\ontologies\\ont_PS2_pig.owl");
            FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\anast\\OneDrive\\Рабочий стол\\magistratura\\project\\ontologies\\ont_PS3_pig.owl");
            model.write(fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}

