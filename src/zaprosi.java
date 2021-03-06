import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.swrlapi.core.SWRLAPIRule;
import org.swrlapi.core.SWRLRuleEngine;
import org.swrlapi.exceptions.SWRLBuiltInException;
import org.swrlapi.factory.SWRLAPIFactory;
import org.swrlapi.parser.SWRLParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class zaprosi {


    public static void main(String[] args) throws OWLOntologyCreationException, FileNotFoundException, OWLOntologyStorageException, SWRLBuiltInException, SWRLParseException {

       TEST.Start();


        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
       // File file = new File("C:\\Users\\anast\\OneDrive\\Рабочий стол\\magistratura\\project\\ontologies\\ont_PS3_pig.owl");
        File file = new File("src\\resources\\PS1.owl");;
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Load ontology: " + ontology);

        SWRLRuleEngine ruleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
        ruleEngine.infer();
        OutputStream out = new FileOutputStream("src\\resources\\PS2.owl");

        manager.saveOntology(ontology, out);



        SWRLAPIRule rule01 = ruleEngine.createSWRLRule("set0", "Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule02 = ruleEngine.createSWRLRule("set1", "Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,1)  -> setOfProtection(?p, 1)");
        SWRLAPIRule rule03 = ruleEngine.createSWRLRule("set0_1", "Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule04 = ruleEngine.createSWRLRule("set2", "Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,1)  -> setOfProtection(?p, 2)");

        ruleEngine.infer();

        int k = 0;

        String ns = ontology.getOntologyID().getOntologyIRI().get().toString() + "#";
        OWLIndividual trans = null;
        OWLDataFactory df = manager.getOWLDataFactory();
        OWLClass eqCLass = df.getOWLClass(IRI.create(ns + "Equipment"));
        OWLClass yptrClass = df.getOWLClass(IRI.create(ns + "YPTR"));
        OWLClass ofPowerTransformers = df.getOWLClass(IRI.create(ns + "ofPowerTransformers"));
        OWLClass ofLines = df.getOWLClass(IRI.create(ns + "ofLines"));
        OWLObjectProperty isLocated = df.getOWLObjectProperty(IRI.create(ns + "isLocated"));
        OWLClass eqClass = df.getOWLClass(IRI.create(ns + "Equipment"));
        OWLClass linesClass = df.getOWLClass(IRI.create(ns + "Lines"));
        OWLClass CBR = df.getOWLClass(IRI.create(ns + "XCBR"));
        OWLClass SWI = df.getOWLClass(IRI.create(ns + "XSWI"));
        OWLClassExpression CBR_E = df.getOWLClass(IRI.create(ns + "XCBR"));
        OWLClass reaClass = df.getOWLClass((IRI.create(ns + "ZREA")));
        OWLClassExpression TCTR_E = df.getOWLClass(IRI.create(ns + "TCTR"));
        OWLClass TCTR = df.getOWLClass(IRI.create(ns + "TCTR"));
        OWLClass capClass = df.getOWLClass(IRI.create(ns + "ZCAP"));
        OWLClassExpression TVTR_E = df.getOWLClass(IRI.create(ns + "TVTR"));
        OWLDataProperty addedShortBus = df.getOWLDataProperty(IRI.create(ns + "addedShortBus"));
        OWLObjectProperty hasCN = df.getOWLObjectProperty(IRI.create(ns + "hasCN"));
        OWLObjectProperty ptwOf = df.getOWLObjectProperty(IRI.create(ns + "ptwOf"));
        OWLObjectProperty hasPTW = df.getOWLObjectProperty(IRI.create(ns + "hasPTW"));
        OWLObjectProperty tctrOf = df.getOWLObjectProperty(IRI.create(ns + "tctrOf"));
        OWLObjectProperty tvtrOf = df.getOWLObjectProperty(IRI.create(ns + "tvtrOf"));
        OWLObjectProperty hasTCTR = df.getOWLObjectProperty(IRI.create(ns + "hasTCTR"));
        OWLObjectProperty hasTVTR = df.getOWLObjectProperty(IRI.create(ns + "hasTVTR"));
        OWLObjectProperty tctrFirst = df.getOWLObjectProperty(IRI.create(ns + "tctrFirst"));
        OWLObjectProperty hasBus = df.getOWLObjectProperty(IRI.create(ns + "hasShortBus"));
        OWLObjectProperty hasFalseBus = df.getOWLObjectProperty(IRI.create(ns + "hasFalseBus"));
        OWLObjectProperty mainProtect = df.getOWLObjectProperty(IRI.create(ns + "mainProtect"));
        OWLObjectProperty addedEquipment = df.getOWLObjectProperty(IRI.create(ns + "addedEquipment"));
        OWLObjectProperty hasVoltageLevel = df.getOWLObjectProperty(IRI.create(ns + "hasVoltageLevel"));
        OWLObjectProperty isSwitchedBy = df.getOWLObjectProperty(IRI.create(ns + "isSwitchedBy"));
        OWLObjectProperty connectedWithCbr = df.getOWLObjectProperty(IRI.create(ns + "connectedWithCbr"));
        OWLDataProperty busType = df.getOWLDataProperty(IRI.create(ns + "busType"));
        OWLDataProperty withTVTR = df.getOWLDataProperty((IRI.create(ns + "withTVTR")));
        OWLDataProperty added = df.getOWLDataProperty(IRI.create(ns + "added"));
        OWLObjectProperty addedTo = df.getOWLObjectProperty(IRI.create(ns + "addedTo"));
        OWLDataProperty hasBus_ = df.getOWLDataProperty(IRI.create(ns + "hasBus"));
        OWLDataProperty use = df.getOWLDataProperty(IRI.create(ns + "use"));
        OWLDataProperty iflWithCable = df.getOWLDataProperty(IRI.create(ns + "iflWithCable"));
        OWLClass busClass = df.getOWLClass(IRI.create(ns + "Bus"));
        OWLDataProperty hasAddedBus = df.getOWLDataProperty(IRI.create(ns + "hasAddedBus"));
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        String ptwClass = "PTW";
        OWLClass PTW = df.getOWLClass(IRI.create(ns + "PTW"));
        OWLClass oshClass = df.getOWLClass(IRI.create(ns + "ShortBus"));
        OWLObjectProperty connectedEquipment = df.getOWLObjectProperty(IRI.create(ns + "connectedEquipment"));
        OWLObjectProperty isProtectedBy = df.getOWLObjectProperty(IRI.create(ns + "isProtectedBy"));
        OWLClass VoltageLevel = df.getOWLClass(IRI.create(ns + "VoltageLevel"));
        OWLClass ofBus = df.getOWLClass(IRI.create(ns + "ofBus"));
        OWLClass protectionClass = df.getOWLClass(IRI.create(ns + "Protection"));
        OWLDataProperty typeOfControlledValue = df.getOWLDataProperty(IRI.create(ns + "typeOfControlledValue"));
        OWLObjectProperty mProtect = df.getOWLObjectProperty(IRI.create(ns + "mainProtect"));
        OWLObjectProperty rProtect = df.getOWLObjectProperty(IRI.create(ns + "reserveProtect"));
        OWLObjectProperty tProtect = df.getOWLObjectProperty(IRI.create(ns + "techProtect"));
        OWLDataProperty voltageType = df.getOWLDataProperty(IRI.create(ns + "voltageType"));


        /////////////////////////////////////////////Задание типа РУ (с шинамми или ошиновками)///////////////////////
        Set<OWLNamedIndividual> indVolt = getIndividualByClass.getIndividualofClass(VoltageLevel, reasoner);
        for (OWLNamedIndividual v : indVolt) {
            Collection<OWLLiteral> listHasBus = getValuesFromProperty.getValues(v, ontology, hasBus_);
            if (listHasBus.isEmpty()) {
                AxiomsAdding.AddingData(ontology, manager, df, v, 0, hasBus_);
            }
        }

        ////////////////////////////////////TT,TV and CBR for Transformers/////////////////////////////////////////

        Set<OWLNamedIndividual> indPTW = getIndividualByClass.getIndividualofClass(PTW, reasoner);
        for (OWLNamedIndividual i : indPTW) {
            System.out.println(i.getIRI().getFragment());
            OWLIndividual bI = null;
            OWLIndividual volt = null;
            int valueBus = -1;
            Collection<OWLIndividual> indCN = getIndividualFromProperty.getIndivid(i, ontology, hasCN);
            Collection<OWLIndividual> indVoltage = getIndividualFromProperty.getIndivid(i, ontology, hasVoltageLevel);
            for (OWLIndividual v : indVoltage) {
                volt = v;
            }
            for (OWLIndividual t : getIndividualFromProperty.getIndivid(i, ontology, ptwOf)) {
                trans = t;
            }
            Collection<OWLLiteral> listBusT = getValuesFromProperty.getValues(volt, ontology, hasBus_);
            for (OWLLiteral l : listBusT) {
                valueBus = l.parseInteger();
            }
            for (OWLIndividual j : indCN) {
                Collection<OWLIndividual> nodes = new HashSet<>();
                List<OWLIndividual> cbrOfT = new ArrayList<>();
                List<OWLIndividual> tctrOfT = new ArrayList<>();
                List<OWLIndividual> tvtrOfT = new ArrayList<>();
                List<OWLIndividual> eqOfT = new ArrayList<>();
                List<OWLIndividual> adOfT = new ArrayList<>();
                lookFor.getTT_TV_CBR(j, ontology, ns, df, bI, nodes, false, tctrOfT, tvtrOfT, cbrOfT, adOfT, trans);
                if (valueBus == 1) {
                    CreateShortBus.CreateFalseBus(ontology, manager, df, ns, j, trans, volt, nodes, k);
                } else {
                    CreateShortBus.CreateBus(ontology, manager, df, ns, j, trans, volt, nodes, k);
                }
                AxiomsAdding.adding(ontology, manager, df, trans, tctrOfT.get(0), tctrFirst);
                tctrOfT.remove(0);
                AxiomsAdding.addingSeveral(ontology, manager, df, trans, tctrOfT, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, trans, tvtrOfT, hasTVTR);
                if (tvtrOfT.isEmpty()) {
                    AxiomsAdding.AddingData(ontology, manager, df, trans, 0, withTVTR);
                }
                AxiomsAdding.addingSeveral(ontology, manager, df, trans, cbrOfT, isSwitchedBy);
                System.out.println("!!!!!!!TRANS " + eqOfT.size());
                AxiomsAdding.addingSeveral(ontology, manager, df, trans, adOfT, addedEquipment);
                k++;
            }
        }


        //////////////////////////////////////TT, TV and CBR for Lines///////////////////////////////////////////
        Set<OWLNamedIndividual> indLines = getIndividualByClass.getIndividualofClass(linesClass, reasoner);
        for (OWLNamedIndividual l : indLines) {
            int linesValue = -1;
            Collection<OWLIndividual> indCnL = getIndividualFromProperty.getIndivid(l, ontology, hasCN);
            Collection<OWLIndividual> voltL = getIndividualFromProperty.getIndivid(l, ontology, hasVoltageLevel);
            Collection<OWLLiteral> iflList = getValuesFromProperty.getValues(l, ontology, iflWithCable);
            for (OWLIndividual v : voltL) {
                Collection<OWLLiteral> b = getValuesFromProperty.getValues(v, ontology, hasBus_);
                for (OWLLiteral vl : b) {
                    linesValue = vl.parseInteger();
                }
                for (OWLIndividual i : indCnL) {
                    List<OWLIndividual> tctrOfL = new ArrayList<>();
                    List<OWLIndividual> tvtrOfL = new ArrayList<>();
                    List<OWLIndividual> cbrOfL = new ArrayList<>();
                    List<OWLIndividual> adOfL = new ArrayList<>();
                    Collection<OWLIndividual> nodesLines = new HashSet<>();
                    lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesLines, false, tctrOfL, tvtrOfL, cbrOfL, adOfL, l);
                    if (tctrOfL.size() > 1) {
                        AxiomsAdding.adding(ontology, manager, df, l, tctrOfL.get(0), tctrFirst);
                        tctrOfL.remove(0);
                    }
                    AxiomsAdding.addingSeveral(ontology, manager, df, l, tctrOfL, hasTCTR);
                    AxiomsAdding.addingSeveral(ontology, manager, df, l, tvtrOfL, hasTVTR);
                    if (tvtrOfL.isEmpty()) {
                        AxiomsAdding.AddingData(ontology, manager, df, l, 0, withTVTR);
                    }
                    AxiomsAdding.addingSeveral(ontology, manager, df, l, cbrOfL, isSwitchedBy);
                    if (iflList.isEmpty()) {
                        AxiomsAdding.addingSeveral(ontology, manager, df, l, adOfL, addedEquipment);
                    }
                    if (linesValue == 0) {
                        CreateShortBus.CreateBus(ontology, manager, df, ns, i, l, v, nodesLines, k);
                    } else {
                        CreateShortBus.CreateFalseBus(ontology, manager, df, ns, i, l, v, nodesLines, k);
                    }
                    k++;
                }
            }
        }
        ///////////////////////////////////TT for CBR///////////////////////////////////////////////////////////////////////
        Set<OWLNamedIndividual> indCbr = getIndividualByClass.getIndividualofClass(CBR, reasoner);
        for (OWLNamedIndividual c : indCbr) {
            Collection<OWLIndividual> indCnC = getIndividualFromProperty.getIndivid(c, ontology, hasCN);
            for (OWLIndividual i : indCnC) {
                List<OWLIndividual> tctrOfC = new ArrayList<>();
                List<OWLIndividual> cbrOfC = new ArrayList<>();
                lookFor.getSomething(i, ontology, ns, df, null, false, TCTR_E, tctrOfC);
                lookFor.getConnectedEquipment(i, ontology, ns, df, null, CBR_E, cbrOfC, c);
                AxiomsAdding.addingSeveral(ontology, manager, df, c, tctrOfC, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, c, cbrOfC, connectedWithCbr);
                AxiomsAdding.AddingData(ontology, manager, df, c, 0, withTVTR);
            }
        }

        //////////////////////////////////TT TV and CBR for REA////////////////////////////////////////////////////////////
        Set<OWLNamedIndividual> indRea = getIndividualByClass.getIndividualofClass(reaClass, reasoner);
        for (OWLNamedIndividual r : indRea) {
            Collection<OWLIndividual> indCnR = getIndividualFromProperty.getIndivid(r, ontology, hasCN);
            for (OWLIndividual i : indCnR) {
                List<OWLIndividual> tctrOfR = new ArrayList<>();
                List<OWLIndividual> tvtrOfR = new ArrayList<>();
                List<OWLIndividual> cbrOfR = new ArrayList<>();
                List<OWLIndividual> eqOfR = new ArrayList<>();
                Collection<OWLIndividual> nodesR = new HashSet<>();
                lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesR, false, tctrOfR, tvtrOfR, cbrOfR, eqOfR, r);
                AxiomsAdding.addingSeveral(ontology, manager, df, r, tctrOfR, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, r, tvtrOfR, hasTVTR);
                if (tvtrOfR.isEmpty()) {
                    AxiomsAdding.AddingData(ontology, manager, df, r, 0, withTVTR);
                }
                AxiomsAdding.addingSeveral(ontology, manager, df, r, cbrOfR, isSwitchedBy);
            }
        }
//////////////////////////////////TT TV and CBR for CAP////////////////////////////////////////////////////////////
        Set<OWLNamedIndividual> indCAP = getIndividualByClass.getIndividualofClass(capClass, reasoner);
        for (OWLNamedIndividual c : indCAP) {
            Collection<OWLIndividual> indCnCp = getIndividualFromProperty.getIndivid(c, ontology, hasCN);
            for (OWLIndividual i : indCnCp) {
                List<OWLIndividual> tctrOfCp = new ArrayList<>();
                List<OWLIndividual> tvtrOfCp = new ArrayList<>();
                List<OWLIndividual> cbrOfCp = new ArrayList<>();
                List<OWLIndividual> eqOfCp = new ArrayList<>();
                Collection<OWLIndividual> nodesCp = new HashSet<>();
                lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesCp, false, tctrOfCp, tvtrOfCp, cbrOfCp, eqOfCp, c);
                AxiomsAdding.addingSeveral(ontology, manager, df, c, tctrOfCp, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, c, tvtrOfCp, hasTVTR);
                if (tvtrOfCp.isEmpty()) {
                    AxiomsAdding.AddingData(ontology, manager, df, c, 0, withTVTR);
                }
                AxiomsAdding.addingSeveral(ontology, manager, df, c, cbrOfCp, isSwitchedBy);
            }
        }
        ///////////////////////////////////TT TV and CBR fo ShortBus////////////////////////////////////////////////
        Set<OWLNamedIndividual> indS = getIndividualByClass.getIndividualofClass(oshClass, reasoner);
        for (OWLNamedIndividual s : indS) {
            System.out.println("shortBus");
            Collection<OWLIndividual> indCnS = getIndividualFromProperty.getIndivid(s, ontology, hasCN);
            for (OWLIndividual i : indCnS) {
                List<OWLIndividual> tctrOfS = new ArrayList<>();
                List<OWLIndividual> tvtrOfS = new ArrayList<>();
                List<OWLIndividual> cbrOfS = new ArrayList<>();
                List<OWLIndividual> eqOfS = new ArrayList<>();
                Collection<OWLIndividual> nodesS = new HashSet<>();
                lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesS, false, tctrOfS, tvtrOfS, cbrOfS, eqOfS, s);
                AxiomsAdding.addingSeveral(ontology, manager, df, s, tctrOfS, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, s, tvtrOfS, hasTVTR);
                if (tvtrOfS.isEmpty()) {
                    AxiomsAdding.AddingData(ontology, manager, df, s, 0, withTVTR);
                }
                AxiomsAdding.addingSeveral(ontology, manager, df, s, cbrOfS, isSwitchedBy);
            }
        }
        ///////////////////////////////TT, TV and cbr for Bus///////////////////////////////////////////////////////////
        Set<OWLNamedIndividual> indBus = getIndividualByClass.getIndividualofClass(busClass, reasoner);
        for (OWLNamedIndividual b : indBus) {
            Collection<OWLIndividual> indCnB = getIndividualFromProperty.getIndivid(b, ontology, hasCN);
            for (OWLIndividual j : indCnB) {
                List<OWLIndividual> tctrOfB = new ArrayList<>();
                List<OWLIndividual> tvtrOfB = new ArrayList<>();
                List<OWLIndividual> cbrOfB = new ArrayList<>();
                List<OWLIndividual> eqOfB = new ArrayList<>();
                Collection<OWLIndividual> nodesBus = new HashSet<>();
                lookFor.getTT_TV_CBR(j, ontology, ns, df, null, nodesBus, false, tctrOfB, tvtrOfB, cbrOfB, eqOfB, b);
                checkForCBRWithDis.hasConectionWithDis(j, ontology, ns, df, cbrOfB, tctrOfB);
                AxiomsAdding.addingSeveral(ontology, manager, df, b, tctrOfB, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, b, tvtrOfB, hasTVTR);
                if (tvtrOfB.isEmpty()) {
                    AxiomsAdding.AddingData(ontology, manager, df, b, 0, withTVTR);
                }
                AxiomsAdding.addingSeveral(ontology, manager, df, b, cbrOfB, isSwitchedBy);
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////
        out = new FileOutputStream("src\\resources\\PS3.owl");

        manager.saveOntology(ontology, out);

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SWRLAPIRule ruleL = ruleEngine.createSWRLRule("addedToRUWithShortBus", "Equipment(?e) ^ hasVoltageLevel(?e,?v) ^ hasBus(?v,0) ^ hasShortBus(?e,?b) -> addedShortBus(?e,1)");
        SWRLAPIRule rule6 = ruleEngine.createSWRLRule("CN", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,?vv) ^ swrlb:equal(?vv,2) -> voltageType(?b, 2)");
        SWRLAPIRule rule7 = ruleEngine.createSWRLRule("BN", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,?vv) ^ swrlb:equal(?vv,1) -> voltageType(?b, 1)");
        SWRLAPIRule rule8 = ruleEngine.createSWRLRule("NN", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,3) -> voltageType(?b, 3)");
        SWRLAPIRule rule9 = ruleEngine.createSWRLRule("Voltage", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ hasVoltage(?v,?vv) -> hasVoltage(?b, ?vv)");
        ruleEngine.infer();

        SWRLAPIRule rule1 = ruleEngine.createSWRLRule("oshinCN2", "YPTR(?x) ^ ShortBus(?b)^ addedEquipment(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,2) ^ hasVoltage(?v,?vv) ^ swrlb:greaterThanOrEqual(?vv, 330) -> setOfProtection(?x, 3)");
        SWRLAPIRule rule2 = ruleEngine.createSWRLRule("oshinCN", "YPTR(?x) ^ ShortBus(?b)^ addedEquipment(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 2) -> setOfProtection(?x, 1)");
        SWRLAPIRule rule3 = ruleEngine.createSWRLRule("oshinBN", "AutoTransformers(?x) ^ ShortBus(?b)^ addedEquipment(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 1) ^ hasVoltage(?v,220) -> setOfProtection(?x, 4)");
        SWRLAPIRule rule4 = ruleEngine.createSWRLRule("oshinNN", "YPTR(?x) ^ addedEquipment(?e, ?c) ^ ZREA(?c) -> setOfProtection(?x, 2)");
        //   SWRLAPIRule rule12 = ruleEngine.createSWRLRule("oshinBN2", "AutoTransformers(?x) ^ base(?x,6) -> setOfProtection(?x, 6)");
        SWRLAPIRule rule12 = ruleEngine.createSWRLRule("oshinBN2", "YPTR(?x) ^ hasVoltageLevel(?x, ?v) ^ voltageType(?v, 1) ^ hasVoltage(?v, ?p) ^ swrlb:greaterThanOrEqual(?p, 330) -> setOfProtection(?x,6) ");
        //  SWRLAPIRule rule13 = ruleEngine.createSWRLRule("PDIF_O_forCN", "PowerTransformers(?p) ^ hasTCTR(?p,?t) ^ hasVoltageLevel(?t,?v) ^ voltageType(?v,2) ^ isSwitchedBy(?p,?c) ^ hasTCTR(?c,?t) -> setOfProtection(?p,7)");
        ruleEngine.infer();
//////////////////////////////////добавление свойств для составления сетки оборудования (связей) /////////////////////
        Set<OWLNamedIndividual> indEq = getIndividualByClass.getIndividualofClass(eqClass, reasoner);
        for (OWLNamedIndividual i : indEq) {
            if (!EntitySearcher.getTypes(i, ontology).contains(CBR) && !EntitySearcher.getTypes(i, ontology).contains(SWI)) {
                if (getIndividualFromProperty.getIndivid(i, ontology, addedTo).isEmpty()) {
                    AxiomsAdding.AddingData(ontology, manager, df, i, 0, added);
                }
            }
            Collection<OWLIndividual> listV = getIndividualFromProperty.getIndivid(i, ontology, hasVoltageLevel);
            for (OWLIndividual v : listV) {
                Collection<OWLLiteral> listValueB = getValuesFromProperty.getValues(v, ontology, hasBus_);
                for (OWLLiteral l : listValueB) {
                    if (l.parseInteger() == 0) {
                        if (getIndividualFromProperty.getIndivid(i, ontology, hasBus).isEmpty()) {
                            AxiomsAdding.AddingData(ontology, manager, df, i, 0, addedShortBus);
                        }
                    }
                }
            }
        }
//////////////////////////////////дбавление свойства для исключения линий с кабедлем////
        for (OWLIndividual l : indLines) {
            Collection<OWLLiteral> withCab = getValuesFromProperty.getValues(l, ontology, iflWithCable);
            if (withCab.isEmpty()) {
                AxiomsAdding.AddingData(ontology, manager, df, l, 0, iflWithCable);
            }
        }
///////////////////// блок проверки на защиты и распределения функций защит////////////////////


        OWLClass pdifLin = df.getOWLClass(IRI.create(ns + "PDIF_L"));
        OWLClass pdifFLin = df.getOWLClass(IRI.create(ns + "PDIF_F"));
        OWLClass rs = df.getOWLClass(IRI.create(ns + "RS"));
        OWLClass nvchz = df.getOWLClass(IRI.create(ns + "NVCHZ"));
        OWLClass pdis = df.getOWLClass(IRI.create(ns + "PDIS"));
        OWLClass pntcn = df.getOWLClass(IRI.create(ns + "PNTCN"));
        OWLClass ptpL = df.getOWLClass(IRI.create(ns + "PTP_L"));
        OWLClass pto = df.getOWLClass(IRI.create(ns + "PTO"));
        OWLClass ptm = df.getOWLClass(IRI.create(ns + "PTM"));
        OWLClass pozz = df.getOWLClass(IRI.create(ns + "POZZ"));
        OWLClass pdz = df.getOWLClass(IRI.create(ns + "PDZ"));
        OWLClass ptc = df.getOWLClass(IRI.create(ns + "PTC"));
        OWLClass ptcnL = df.getOWLClass(IRI.create(ns + "PTCN_L"));
        OWLDataProperty hasVoltage = df.getOWLDataProperty(IRI.create(ns + "hasVoltage"));

        OWLDataProperty hasChanel = df.getOWLDataProperty(IRI.create(ns + "hasChannel"));
        OWLDataProperty base = df.getOWLDataProperty(IRI.create(ns + "base"));
        OWLDataProperty setOf = df.getOWLDataProperty(IRI.create(ns + "setOfProtection"));

        //////////////////////////////////////TRANSFORMERS////////////////////////////////////////////////////
        OWLClass CRI_NN = df.getOWLClass(IRI.create(ns + "CRI_NN"));
        OWLClass PCRI = df.getOWLClass(IRI.create(ns + "PCRI"));
        OWLClass PCRI_V = df.getOWLClass(IRI.create(ns + "PCRI_V"));
        OWLClass PDIF_O = df.getOWLClass(IRI.create(ns + "PDIF_O"));
        OWLClass PDIF_T = df.getOWLClass(IRI.create(ns + "PDIF_T"));
        OWLClass PDIS_T = df.getOWLClass(IRI.create(ns + "PDIS_T"));
        OWLClass PGAS = df.getOWLClass(IRI.create(ns + "PGAS"));
        OWLClass PICE = df.getOWLClass(IRI.create(ns + "PICE"));
        OWLClass PNTCN_T = df.getOWLClass(IRI.create(ns + "PNTCN_T"));
        OWLClass POIL = df.getOWLClass(IRI.create(ns + "POIL"));
        OWLClass PRPN = df.getOWLClass(IRI.create(ns + "PRPN"));
        OWLClass PTC_T = df.getOWLClass(IRI.create(ns + "PTC_T"));
        OWLClass PTCN = df.getOWLClass(IRI.create(ns + "PTCN"));
        OWLClass PTM_U = df.getOWLClass(IRI.create(ns + "PTM_U"));
        OWLClass PTO_T = df.getOWLClass(IRI.create(ns + "PTO_T"));
        OWLClass PTP = df.getOWLClass(IRI.create(ns + "PTP"));
        ////////////////////////Bus/////////////////////////////////////////
        OWLClass PDIF_B = df.getOWLClass(IRI.create(ns + "PDIF_B"));
        OWLClass PDZ_B = df.getOWLClass(IRI.create(ns + "PDZ_B"));
        OWLClass PLOG = df.getOWLClass(IRI.create(ns + "PLOG"));
        OWLClass PMU = df.getOWLClass(IRI.create(ns + "PMU"));
        OWLClass POZZ_B = df.getOWLClass(IRI.create(ns + "POZZ_B"));

        ///////////////////////reactors///////////////////////////////////


        OWLClass efnClass = df.getOWLClass((IRI.create(ns + "YEFN")));
        OWLClass PDIF_P = df.getOWLClass(IRI.create(ns + "PDIF_P"));
        OWLClass PDIF_R = df.getOWLClass(IRI.create(ns + "PDIF_R"));
        OWLClass PDIS_R = df.getOWLClass(IRI.create(ns + "PDIS_R"));
        OWLClass PGAS_R = df.getOWLClass(IRI.create(ns + "PGAS_R"));
        OWLClass PICE_R = df.getOWLClass(IRI.create(ns + "PICE_R"));
        OWLClass POIL_R = df.getOWLClass(IRI.create(ns + "POIL_R"));
        OWLClass PTP_R = df.getOWLClass(IRI.create(ns + "PTP_R"));
        OWLClass PUPRAV_W = df.getOWLClass(IRI.create(ns + "PUPRAV_W"));
        OWLClass PWIN = df.getOWLClass(IRI.create(ns + "PWIN"));
        OWLClass PCRI_V_R = df.getOWLClass(IRI.create(ns + "PCRI_V_R"));
        ///////////////////////////////////battery////////////////////

        OWLClass PBNN = df.getOWLClass(IRI.create(ns + "PBNN"));
        OWLClass PBU = df.getOWLClass(IRI.create(ns + "PBU"));
        OWLClass PDIF_BAT = df.getOWLClass(IRI.create(ns + "PDIF_BAT"));
        OWLClass PDU = df.getOWLClass(IRI.create(ns + "PDU"));
        OWLClass PTM_BAT = df.getOWLClass(IRI.create(ns + "PTM_BAT"));
        OWLClass PTP_BAT = df.getOWLClass(IRI.create(ns + "PTP_BAT"));
        OWLClass PVP = df.getOWLClass(IRI.create(ns + "PVP"));

        /////////////////ConductingEquipment////////////////////////////
        OWLClass cbrClass = df.getOWLClass(IRI.create(ns + "XCBR"));
        OWLClass PDIS_C = df.getOWLClass(IRI.create(ns + "PDIS_C"));
        OWLClass PDZ_C = df.getOWLClass(IRI.create(ns + "PDZ_C"));
        OWLClass PMU_C = df.getOWLClass(IRI.create(ns + "PMU_C"));
        OWLClass PNTCN_C = df.getOWLClass(IRI.create(ns + "PNTCN_C"));
        OWLClass PTC_C = df.getOWLClass(IRI.create(ns + "PTC_C"));
        OWLClass PTCN_C = df.getOWLClass(IRI.create(ns + "PTCN_C"));
        OWLClass PTM_C = df.getOWLClass(IRI.create(ns + "PTM_C"));
        OWLClass PTM_U_C = df.getOWLClass(IRI.create(ns + "PTM_U_C"));

        ///////////////////Motors///////////////////////////////////
        OWLClass Motors = df.getOWLClass(IRI.create(ns + "ZMOT"));
        OWLClass FOO = df.getOWLClass(IRI.create(ns + "FOO"));
        OWLClass PAR = df.getOWLClass(IRI.create(ns + "PAR"));
        OWLClass PCLOAD = df.getOWLClass(IRI.create(ns + "PCLOAD"));
        OWLClass PDIF_M = df.getOWLClass(IRI.create(ns + "PDIF_M"));
        OWLClass PDIF_TO = df.getOWLClass(IRI.create(ns + "PDIF_TO"));
        OWLClass PLP = df.getOWLClass(IRI.create(ns + "PLP"));
        OWLClass PMU_M = df.getOWLClass(IRI.create(ns + "PMU_M"));
        OWLClass PNR = df.getOWLClass(IRI.create(ns + "PNR"));
        OWLClass POZZ_M = df.getOWLClass(IRI.create(ns + "POZZ_M"));
        OWLClass POZZ_D = df.getOWLClass(IRI.create(ns + "POZZ_D"));
        OWLClass PTO_M = df.getOWLClass(IRI.create(ns + "PTO_M"));
        OWLClass PTP_M = df.getOWLClass(IRI.create(ns + "PTP_M"));
        ///////////////////////////////////listWithTypeOfProtection//////////////////////////
        List<OWLClass> currentVoltageProtection = new ArrayList<>();
        List<OWLClass> voltageProtection = new ArrayList<>();
        currentVoltageProtection.add(PDIS_C);
        currentVoltageProtection.add(PDIS_R);
        currentVoltageProtection.add(PDIS_T);
        currentVoltageProtection.add(pdis);
        currentVoltageProtection.add(pntcn);
        currentVoltageProtection.add(PNTCN_C);
        currentVoltageProtection.add(PNTCN_T);
        currentVoltageProtection.add(PTM_U);
        currentVoltageProtection.add(PTM_U_C);

        voltageProtection.add(PMU);
        voltageProtection.add(PMU_C);
        voltageProtection.add(PMU_M);

        /////////////////////////////////////////////////////////////////
        List<OWLClass> mProtectionTr = new ArrayList<>();
        List<OWLClass> rProtectionTr = new ArrayList<>();
        List<OWLClass> techProtectionTr = new ArrayList<>();

        List<OWLClass> mProtectionB = new ArrayList<>();
        List<OWLClass> rProtectionB = new ArrayList<>();
        List<OWLClass> techProtectionB = new ArrayList<>();

        List<OWLClass> mProtectionBr = new ArrayList<>();
        List<OWLClass> rProtectionBr = new ArrayList<>();
        List<OWLClass> techProtectionBr = new ArrayList<>();

        List<OWLClass> mProtectionBat = new ArrayList<>();
        List<OWLClass> rProtectionBat = new ArrayList<>();
        List<OWLClass> techProtectionBat = new ArrayList<>();

        List<OWLClass> mProtectionL = new ArrayList<>();
        List<OWLClass> rProtectionL = new ArrayList<>();
        List<OWLClass> techProtectionL = new ArrayList<>();

        List<OWLClass> mProtectionR = new ArrayList<>();
        List<OWLClass> rProtectionR = new ArrayList<>();
        List<OWLClass> techProtectionR = new ArrayList<>();

        List<OWLClass> mProtectionM = new ArrayList<>();
        List<OWLClass> rProtectionM = new ArrayList<>();
        List<OWLClass> techProtectionM = new ArrayList<>();

        List<OWLClass> mProtectionSB = new ArrayList<>();


        mProtectionTr.add(PDIF_T);
        mProtectionTr.add(PTO_T);
        rProtectionTr.add(PDIF_O);
        rProtectionTr.add(PDIS_T);
        rProtectionTr.add(PNTCN_T);
        rProtectionTr.add(PTC_T);
        rProtectionTr.add(PTCN);
        rProtectionTr.add(PTM_U);
        rProtectionTr.add(PTP);
        techProtectionTr.add(CRI_NN);
        techProtectionTr.add(POIL);
        techProtectionTr.add(PICE);
        techProtectionTr.add(PGAS);
        techProtectionTr.add(PRPN);
        techProtectionTr.add(PCRI);
        techProtectionTr.add(PCRI_V);

        mProtectionL.add(pdifFLin);
        mProtectionL.add(pdifLin);
        mProtectionL.add(rs);
        mProtectionL.add(nvchz);
        mProtectionL.add(pto);
        rProtectionL.add(pozz);
        rProtectionL.add(ptc);
        rProtectionL.add(ptcnL);
        rProtectionL.add(pntcn);
        rProtectionL.add(ptm);
        rProtectionL.add(ptpL);
        rProtectionL.add(pdis);
        techProtectionL.add(pdz);

        mProtectionB.add(PDIF_B);
        mProtectionB.add(PLOG);
        rProtectionB.add(PMU);
        rProtectionB.add(POZZ_B);
        techProtectionB.add(PDZ_B);


        rProtectionBr.add(PDIS_C);
        rProtectionBr.add(PMU_C);
        rProtectionBr.add(PNTCN_C);
        rProtectionBr.add(PTC_C);
        rProtectionBr.add(PTCN_C);
        rProtectionBr.add(PTM_C);
        rProtectionBr.add(PTM_U_C);
        techProtectionBr.add(PDZ_C);

        mProtectionSB.add(PDIF_O);

        mProtectionR.add(PDIF_R);
        mProtectionR.add(PDIF_P);
        mProtectionR.add(PDIF_O);
        rProtectionR.add(PDIS_R);
        rProtectionR.add(PUPRAV_W);
        rProtectionR.add(PWIN);
        rProtectionR.add(PTP_R);
        techProtectionR.add(PICE_R);
        techProtectionR.add(POIL_R);
        techProtectionR.add(PCRI_V_R);
        techProtectionR.add(PGAS_R);


        /////////////////////protectionList/////////////////

        Map<Integer, List<OWLClass>> protectionOfLines1 = new HashMap<>();
        Map<Integer, List<OWLClass>> protectionOfTrans = new HashMap<>();
        Map<Integer, List<OWLClass>> protectionOfTrans1 = new HashMap<>();
        Map<Integer, List<OWLClass>> protectionOfBus = new HashMap<>();
        Map<Integer, List<OWLClass>> protectionOfRea = new HashMap<>();
        Map<Integer, List<OWLClass>> protectionOfEFN = new HashMap<>();
        Map<Integer, List<OWLClass>> protectionOfCAP = new HashMap<>();
        Map<Integer, List<OWLClass>> protectionOfCBR = new HashMap<>();

        List<OWLClass> kczRsClass = new ArrayList<>();
        kczRsClass.add(rs);
        kczRsClass.add(pdis);
        kczRsClass.add(pntcn);

        List<OWLClass> kczClass = new ArrayList<>();
        kczClass.add(pdis);
        kczClass.add(pntcn);

        List<OWLClass> protectionLines = new ArrayList<>();
        protectionLines.add(pdifLin);
        protectionLines.add(pdifFLin);
        protectionLines.add(nvchz);

        List<OWLClass> protectionLines2 = new ArrayList<>();
        protectionLines2.add(pto);
        protectionLines2.add(ptm);
        protectionLines2.add(pozz);
        protectionLines2.add(pdz);
        protectionLines2.add(ptpL);

        protectionOfLines1.put(0, kczRsClass);
        protectionOfLines1.put(1, kczClass);
        protectionOfLines1.put(2, protectionLines2);


        List<OWLClass> generalT = new ArrayList<>();
        generalT.add(PGAS);
        generalT.add(PICE);
        generalT.add(POIL);
        generalT.add(PCRI);

        List<OWLClass> generalNN = new ArrayList<>();
        generalNN.add(PTP);
        generalNN.add(PTM_U);

        List<OWLClass> base0 = new ArrayList<>();
        base0.add(PDIF_T);

        List<OWLClass> base1 = new ArrayList<>();
        base1.add(PDIF_T);
        base1.add(PDIF_T);

        List<OWLClass> base2 = new ArrayList<>();
        base2.add(PDIS_T);
        base2.add(PNTCN_T);

        List<OWLClass> base3 = new ArrayList<>();
        base3.add(PTO_T);

        List<OWLClass> base4 = new ArrayList<>();
        base4.add(PDIS_T);
        base4.add(PNTCN_T);
        base4.add(PDIS_T);
        base4.add(PNTCN_T);

        List<OWLClass> base5 = new ArrayList<>();
        base5.add(PTC_T);
        base5.add(PTCN);
        base5.add(PTC_T);
        base5.add(PTCN);

        List<OWLClass> base6 = new ArrayList<>();
        base6.add(PTC_T);
        base6.add(PTCN);





        protectionOfTrans.put(0, base0);
        protectionOfTrans.put(1, base1);
        protectionOfTrans.put(2, base2);
        protectionOfTrans.put(3, base3);
        protectionOfTrans.put(4, base4);
        protectionOfTrans.put(5, base5);
        protectionOfTrans.put(6, base6);


        List<OWLClass> set0 = new ArrayList<>();
        set0.add(PCRI_V);
        List<OWLClass> set1 = new ArrayList<>();
        set1.add(PDIF_O);
        set1.add(PDIF_O);
        List<OWLClass> set2 = new ArrayList<>();
        set2.add(PDIF_O);
        List<OWLClass> set3 = new ArrayList<>();
        set3.add(PRPN);

        protectionOfTrans1.put(0, set0);
        protectionOfTrans1.put(1, set2);
        protectionOfTrans1.put(2, set2);
        protectionOfTrans1.put(3, set1);
        protectionOfTrans1.put(4, set2);
        protectionOfTrans1.put(5, set3);
        protectionOfTrans1.put(6, set1);
        protectionOfTrans1.put(7, set2);
        ///////////////////////////////////////////////
        List<OWLClass> baseBus0 = new ArrayList<>();
        baseBus0.add(PDIF_B);
        baseBus0.add(PDIF_B);
        List<OWLClass> baseBus1 = new ArrayList<>();
        baseBus1.add(PLOG);
        baseBus1.add(POZZ_B);
        baseBus1.add(PDZ_B);
        baseBus1.add(PMU);
        List<OWLClass> baseBus2 = new ArrayList<>();
        baseBus2.add(PDIF_B);
        baseBus2.add(POZZ_B);
        baseBus2.add(PDZ_B);
        baseBus2.add(PMU);

        protectionOfBus.put(0, baseBus0);
        protectionOfBus.put(1, baseBus1);
        protectionOfBus.put(2, baseBus2);
/////////////////////////////////////////////////////////////
        List<OWLClass> baseRea0 = new ArrayList<>();
        baseRea0.add(PDIF_R);
        baseRea0.add(PDIF_R);
        baseRea0.add(PDIF_P);
        baseRea0.add(PDIF_P);
        baseRea0.add(PGAS_R);
        baseRea0.add(POIL_R);
        baseRea0.add(PICE_R);
        baseRea0.add(PCRI_V_R);
        List<OWLClass> baseRea1 = new ArrayList<>();
        baseRea1.add(PWIN);
        baseRea1.add(PUPRAV_W);

        protectionOfRea.put(0, baseRea0);
        protectionOfRea.put(0, baseRea1);

        List<OWLClass> efnBase = new ArrayList<>();
        efnBase.add(PDIS_R);
        efnBase.add(PDIS_R);
        efnBase.add(PGAS_R);
        efnBase.add(POIL_R);
        efnBase.add(PICE_R);
        efnBase.add(PTP_R);
        protectionOfEFN.put(0, efnBase);
        //////////////////////////////////////////////////////////////////////
        List<OWLClass> capBase = new ArrayList<>();
        capBase.add(PBNN);
        capBase.add(PBU);
        capBase.add(PDIF_BAT);
        capBase.add(PDU);
        capBase.add(PTM_BAT);
        capBase.add(PTP_BAT);
        capBase.add(PVP);
        protectionOfCAP.put(0, capBase);
        ///////////////////////////////////////////////////////////////////////
        List<OWLClass> cbrBase0 = new ArrayList<>();
        cbrBase0.add(PDIS_C);
        cbrBase0.add(PNTCN_C);
        List<OWLClass> cbrBase1 = new ArrayList<>();
        cbrBase1.add(PTC_C);
        cbrBase1.add(PTCN_C);
        List<OWLClass> cbrBase2 = new ArrayList<>();
        cbrBase2.add(PTM_C);
        cbrBase2.add(PDZ_C);
        List<OWLClass> cbrBase3 = new ArrayList<>();
        cbrBase3.add(PTM_U_C);
        cbrBase3.add(PDZ_C);
        cbrBase3.add(PMU_C);
        protectionOfCBR.put(0, cbrBase0);
        protectionOfCBR.put(1, cbrBase1);
        protectionOfCBR.put(2, cbrBase2);
        protectionOfCBR.put(3, cbrBase3);

        OWLDataProperty needRREC = df.getOWLDataProperty(IRI.create(ns + "needRREC"));
        OWLClass RREC = df.getOWLClass(IRI.create(ns + "RREC"));
        OWLClass RBRF = df.getOWLClass(IRI.create(ns + "RBRF"));

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int variant = (int) (Math.random() * 2 + 1);
        int variant3 = (int) (Math.random() * 2);
        for (OWLNamedIndividual i : indLines) {
            int baseNumber = -1;
            Collection<OWLLiteral> setBase = getValuesFromProperty.getValues(i, ontology, base);
            Collection<OWLLiteral> setVar = getValuesFromProperty.getValues(i, ontology, setOf);
            Collection<OWLLiteral> iflList = getValuesFromProperty.getValues(i, ontology, iflWithCable);
            Collection<OWLLiteral> rrecList = getValuesFromProperty.getValues(i, ontology, needRREC);
            for (OWLLiteral n : setBase) {
                baseNumber = n.parseInteger();
                List<OWLClass> protectVar = protectionOfLines1.get(baseNumber);
                Collection<OWLIndividual> setAdded = getIndividualFromProperty.getIndivid(i, ontology, addedTo);
                for (OWLLiteral a : iflList) {
                    if (a.parseInteger() == 0 && setAdded.isEmpty()) {
                        k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectVar, df, manager, k, mProtectionL, rProtectionL, techProtectionL, voltageProtection, currentVoltageProtection);
                    }
                }
            }
            if (!setVar.isEmpty()) {
                for (OWLLiteral n : setVar) {
                    int varNumber = n.parseInteger();
                    if (varNumber != 2) {
                        CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, varNumber, k, mProtectionL, rProtectionL, techProtectionL, voltageProtection, currentVoltageProtection);
                        k++;
                    } else {
                        CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, variant, k, mProtectionL, rProtectionL, techProtectionL, voltageProtection, currentVoltageProtection);
                        k++;
                    }
                }
            } else if (baseNumber != 2) {
                CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, variant3, k, mProtectionL, rProtectionL, techProtectionL, voltageProtection, currentVoltageProtection);
                k++;
            }
            if (!rrecList.isEmpty()) {
                CreateProtectionIndivid.CreateAvtonation(i, ontology, ns, RREC, df, manager, k);
                k++;
            }
        }

        Set<OWLNamedIndividual> indTransform = getIndividualByClass.getIndividualofClass(yptrClass, reasoner);
        for (OWLNamedIndividual n : indTransform) {
            k = CreateComplectOfProtection.CreateComplect(n, ontology, ns, generalT, df, manager, k, mProtectionTr, rProtectionTr, techProtectionTr, voltageProtection, currentVoltageProtection);
            k = CreateComplectOfProtection.CreateComplectForTR(n, ontology, ns, generalNN, df, manager, k, mProtectionTr, rProtectionTr, techProtectionTr, -1, voltageProtection, currentVoltageProtection);
            Collection<OWLLiteral> baseT = getValuesFromProperty.getValues(n, ontology, base);
            Collection<OWLLiteral> setOfT = getValuesFromProperty.getValues(n, ontology, setOf);
            for (OWLLiteral b : baseT) {
                int baseTNumber = b.parseInteger();
                k = CreateComplectOfProtection.CreateComplectForTR(n, ontology, ns, protectionOfTrans.get(baseTNumber), df, manager, k, mProtectionTr, rProtectionTr, techProtectionTr, baseTNumber, voltageProtection, currentVoltageProtection);
            }
            for (OWLLiteral s : setOfT) {
                int setOfNumber = s.parseInteger();
                k = CreateComplectForSeveral.CreateComplect_2(n, setOfNumber, ontology, ns, protectionOfTrans1.get(setOfNumber), df, manager, k, mProtectionTr, rProtectionTr, techProtectionTr, voltageProtection, currentVoltageProtection);

            }
        }

        for (OWLNamedIndividual i : indBus) {
            Collection<OWLLiteral> baseB = getValuesFromProperty.getValues(i, ontology, base);
            Collection<OWLLiteral> rrecList = getValuesFromProperty.getValues(i, ontology, needRREC);
            if (baseB.isEmpty()) {
                k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfBus.get(1), df, manager, k, mProtectionB, rProtectionB, techProtectionB, voltageProtection, currentVoltageProtection);


            } else {
                for (OWLLiteral b : baseB) {
                    int busNumber = b.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfBus.get(busNumber), df, manager, k, mProtectionB, rProtectionB, techProtectionB, voltageProtection, currentVoltageProtection);

                }
            }
            if (!rrecList.isEmpty()) {
                CreateProtectionIndivid.CreateAvtonation(i, ontology, ns, RREC, df, manager, k);
                k++;
            }
        }


        for (OWLNamedIndividual j : indRea) {
            Collection<OWLLiteral> baseRea = getValuesFromProperty.getValues(j, ontology, base);
            if (!baseRea.isEmpty()) {
                for (OWLLiteral l : baseRea) {
                    int reaNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(j, ontology, ns, protectionOfRea.get(reaNumber), df, manager, k, mProtectionR, rProtectionR, techProtectionR, voltageProtection, currentVoltageProtection);

                }
            }
        }
        Set<OWLNamedIndividual> indEfn = getIndividualByClass.getIndividualofClass(efnClass, reasoner);
        for (OWLNamedIndividual i : indEfn) {
            Collection<OWLLiteral> baseE = getValuesFromProperty.getValues(i, ontology, base);
            if (!baseE.isEmpty()) {
                for (OWLLiteral l : baseE) {
                    int efnNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfEFN.get(efnNumber), df, manager, k, mProtectionR, rProtectionR, techProtectionR, voltageProtection, currentVoltageProtection);

                }
            }
        }


        for (OWLNamedIndividual i : indCAP) {
            Collection<OWLLiteral> baseC = getValuesFromProperty.getValues(i, ontology, base);
            if (!baseC.isEmpty()) {
                for (OWLLiteral l : baseC) {
                    int capNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfEFN.get(capNumber), df, manager, k, mProtectionBat, rProtectionBat, techProtectionBat, voltageProtection, currentVoltageProtection);

                }
            }
        }

        Set<OWLNamedIndividual> indCBR = getIndividualByClass.getIndividualofClass(cbrClass, reasoner);
        for (OWLNamedIndividual j : indCBR) {
            Collection<OWLLiteral> baseBr = getValuesFromProperty.getValues(j, ontology, base);
            if (!baseBr.isEmpty()) {
                for (OWLLiteral l : baseBr) {
                    int cbrNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(j, ontology, ns, protectionOfCBR.get(cbrNumber), df, manager, k, mProtectionBr, rProtectionBr, techProtectionBr, voltageProtection, currentVoltageProtection);
                }
            }
            CreateProtectionIndivid.CreateAvtonation(j, ontology, ns, RBRF, df, manager, k);
            k++;
        }
        /////////////////////////////////////////////////////////////////////////////////////////


        ////////////////////////////////////////////////////////////////////////////////////////
        SWRLAPIRule ruleA1 = ruleEngine.createSWRLRule("urovLines", "Lines(?l) ^ isSwitchedBy(?l,?c) ^ XCBR(?c) ^ connectedEquipment(?l, ?b) ^ isSwitchedBy(?b,?cc) ^ Bus(?b) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> manage(?a, ?cc)");
        // SWRLAPIRule ruleA2 = ruleEngine.createSWRLRule("urovBus", "Bus(?b) ^ isSwitchedBy(?b,?c) ^ XCBR(?c) ^ connectedWithCbr(?c, ?cc) ^ isControlling(?cc,?l2) ^ Bus(?l2) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> manage(?a, ?cc)");
        SWRLAPIRule ruleA3 = ruleEngine.createSWRLRule("urovBusBreaker", "Bus(?b) ^ isSwitchedBy(?b,?c) ^ BusBreaker(?c) ^ connectedEquipment(?b,?bb) ^ Bus(?bb) ^ isSwitchedBy(?bb,?cc) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> manage(?a, ?cc)");
        SWRLAPIRule ruleA4 = ruleEngine.createSWRLRule("urovXCBR", "Equipment(?b) ^ isSwitchedBy(?b,?c) ^ XCBR(?c) ^ connectedEquipment(?b,?bb) ^ isSwitchedBy(?bb,?c) ^ isSwitchedBy(?bb,?cc) ^ hasName(?c,?n) ^ hasName(?cc,?nn) ^ swrlb:notEqual(?n,?nn) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> manage(?a, ?cc)");
        SWRLAPIRule ruleA5 = ruleEngine.createSWRLRule("urovXCBRSelf", "XCBR(?c)  ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> manage(?a, ?c)");
        SWRLAPIRule ruleA_RREC = ruleEngine.createSWRLRule("xcbrForAPV", "Equipment(?c)  ^ hasAutomation(?c, ?a) ^ RREC(?a) ^ isSwitchedBy(?c,?x) -> manage(?a, ?x)");

        //  SWRLAPIRule ruleA = ruleEngine.createSWRLRule("urovLines", "XCBR(?c) ^ connectedWithCbr(?c, ?cc) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> manage(?a, ?cc)");
        // SWRLAPIRule ruleA = ruleEngine.createSWRLRule("urovProtect", "XCBR(?c) ^ isControlling(?c, ?e) ^ connectedEquipment(?e, ?ce) ^ Protection(?p) ^ mainProtect(?p, ?ce) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> operate(?a, ?p)");
        //  SWRLAPIRule ruleAP1 = ruleEngine.createSWRLRule("urovProtectLines", "Lines(?l) ^ isSwitchedBy(?l,?c) ^ XCBR(?c) ^ connectedEquipment(?l, ?b) ^ Protection(?p) ^ mainProtect(?p, ?b) ^ Bus(?b) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> operate(?a, ?p)");
        SWRLAPIRule ruleAP2 = ruleEngine.createSWRLRule("urovProtectOther", "Equipment(?l) ^ isSwitchedBy(?l,?c)  ^ connectedEquipment(?l, ?b)   ^ isSwitchedBy(?b,?c) ^ Protection(?p) ^ mainProtect(?p, ?b) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> operate(?a, ?p)");
        SWRLAPIRule ruleAP3 = ruleEngine.createSWRLRule("urovAvtomatika", "Equipment(?l) ^ isSwitchedBy(?l,?c)  ^ connectedEquipment(?l, ?b)   ^ isSwitchedBy(?b,?c) ^ hasAutomation(?b, ?aa) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> operate(?a, ?aa)");
        // SWRLAPIRule ruleAP3 = ruleEngine.createSWRLRule("urovProtectOther", "Bus(?l) ^ isSwitchedBy(?l,?c) ^ XCBR(?c) ^ connectedEquipment(?l, ?b) ^ isSwitchedBy(?b,?c) ^ Protection(?p) ^ mainProtect(?p, ?b) ^ hasAutomation(?c, ?a) ^ RBRF(?a) -> operate(?a, ?p)");

        SWRLAPIRule rule14 = ruleEngine.createSWRLRule("pdifForOther", "Equipment(?e) ^ addedEquipment(?e, ?c) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ hasVoltageLevel(?c, ?v) ^ voltageType(?v, ?vv) ^ voltageType(?p, ?t) ^ swrlb:equal(?vv, ?t) -> isProtectedBy(?c, ?p)");
        SWRLAPIRule rule15 = ruleEngine.createSWRLRule("mainPr", "Equipment(?e) ^ addedEquipment(?e, ?c) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ hasVoltageLevel(?c, ?v) ^ voltageType(?v, ?vv) ^ voltageType(?p, ?t) ^ swrlb:equal(?vv, ?t) -> mainProtect(?p, ?c)");
        SWRLAPIRule rule16 = ruleEngine.createSWRLRule("pdifForS", "Equipment(?e) ^ hasShortBus(?e, ?b) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ voltageType(?b, ?v) ^ voltageType(?p, ?t) ^ swrlb:equal(?v, ?t) -> isProtectedBy(?b, ?p)");
        SWRLAPIRule rule17 = ruleEngine.createSWRLRule("mainPrS", "Equipment(?e) ^ hasShortBus(?e, ?b) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ voltageType(?b, ?v) ^ voltageType(?p, ?t) ^ swrlb:equal(?v, ?t) -> mainProtect(?p, ?b)");
        SWRLAPIRule rule18 = ruleEngine.createSWRLRule("connectedE", "Equipment(?e) ^ isSwitchedBy(?e,?b) ^ Equipment(?q) ^ isSwitchedBy(?q,?b) ^ added(?e,0) ^ added(?q,0) ^ hasName(?e, ?x1) ^ hasName(?q, ?z1) ^ swrlb:notEqual(?x1, ?z1) ^ hasVoltageLevel(?e, ?v) ^  hasVoltageLevel(?q, ?v)  ^ hasBus(?v,1) -> connectedEquipment(?e,?q)");
        SWRLAPIRule rule19 = ruleEngine.createSWRLRule("voltageOfPdif", "PDIF_O(?p) ^ voltageType(?p,?v) ^ VoltageLevel(?volt) ^ voltageType(?volt,?vv) ^ swrlb:equal(?v,?vv) ^ hasVoltage(?volt,?w) -> hasVoltage(?p,?w)");
        SWRLAPIRule rule23 = ruleEngine.createSWRLRule("levelWithShortBus", "VoltageLevel(?v) ^ voltageType(?v,?t) ^ YPTR(?y) ^ hasVoltageLevel(?y,?v) ^ isProtectedBy(?y,?p) ^ PDIF_O(?p) ^ voltageType(?p,?w) ^ swrlb:equal(?t,?w) -> hasAddedBus(?v,1)");
        SWRLAPIRule rule018 = ruleEngine.createSWRLRule("connectedEWithoutShortBus", "Equipment(?e) ^ isSwitchedBy(?e,?b) ^ Equipment(?q) ^ isSwitchedBy(?q,?b) ^ addedShortBus(?e,0) ^ addedShortBus(?q,0) ^ hasName(?e, ?x1) ^ hasName(?q, ?z1) ^ swrlb:notEqual(?x1, ?z1) ^ hasVoltageLevel(?e, ?v) ^  hasVoltageLevel(?q, ?v)  ^ hasBus(?v,0) -> connectedEquipment(?e,?q)");
        // SWRLAPIRule rule019 = ruleEngine.createSWRLRule("connectedEWithShortBus", "Equipment(?e) ^ addedShortBus(?e,1)  ^ hasVoltageLevel(?e, ?v)  ^ hasBus(?v,0)  ^ hasShortBus(?e,?s) -> connectedEquipment(?e,?s)");
        SWRLAPIRule rule31forBus = ruleEngine.createSWRLRule("BreakerForBusSections", "Bus(?b) ^ Bus(?b2) ^ hasName(?b,?n) ^ hasName(?b2,?m) ^ swrlb:notEqual(?n,?m) ^isSwitchedBy(?b,?br) ^ isSwitchedBy(?b2,?br) ^ hasTCTR(?br,?t) -> use(?t,1)");

        ruleEngine.infer();

        out = new FileOutputStream("src\\resources\\PS4.owl");

        manager.saveOntology(ontology, out);

        /////////////////////////БЛОК РАСПРЕДЕЛЕНИЯ ЗАШИТ ПО ТТ ///////////////////

        for (OWLNamedIndividual n : indVolt) {
            Collection<OWLLiteral> listOfAd = getValuesFromProperty.getValues(n, ontology, hasAddedBus);
            if (listOfAd.isEmpty()) {
                AxiomsAdding.AddingData(ontology, manager, df, n, 0, hasAddedBus);
            }
        }

        Set<OWLNamedIndividual> indTT = getIndividualByClass.getIndividualofClass(TCTR, reasoner);
        for (OWLNamedIndividual t : indTT) {
            Collection<OWLLiteral> listOfUseTT = getValuesFromProperty.getValues(t, ontology, use);
            if (listOfUseTT.isEmpty()) {
                AxiomsAdding.AddingData(ontology, manager, df, t, 0, use);
            }
        }

        Set<OWLNamedIndividual> indProtection = getIndividualByClass.getIndividualofClass(protectionClass, reasoner);
        for (OWLNamedIndividual p : indProtection) {
            Collection<OWLLiteral> listOfType = getValuesFromProperty.getValues(p, ontology, typeOfControlledValue);
            Collection<OWLIndividual> listOfProtEq = getIndividualFromProperty.getIndivid(p, ontology, tProtect);

            if (listOfType.isEmpty() && listOfProtEq.isEmpty()) {
                AxiomsAdding.AddingData(ontology, manager, df, p, 0, typeOfControlledValue);
            }
        }

/////////////////////////////////////look for TVTR for Protection//////////////////////////////////////////////////
        for (OWLNamedIndividual e : indEq) {
            Collection<OWLIndividual> listProtect = getIndividualFromProperty.getIndivid(e, ontology, isProtectedBy);
            Collection<OWLIndividual> listTVTR = getIndividualFromProperty.getIndivid(e, ontology, hasTVTR);
            Collection<OWLLiteral> voltageEq = getValuesFromProperty.getValues(e, ontology, hasVoltage);
            for (OWLIndividual p : listProtect) {
                List<OWLIndividual> tvtrForP = new ArrayList<>();
                Collection<OWLLiteral> voltageTypeList = new ArrayList<>();
                OWLLiteral typeVoltageLevel = null;
                int valueL = -1;
                boolean flagType = false;
                int valueVoltE = -1;
                int valueVoltB = -1;
                Collection<OWLLiteral> listOfType = getValuesFromProperty.getValues(p, ontology, typeOfControlledValue);
                for (OWLLiteral l : listOfType) {
                    valueL = l.parseInteger();
                    if (valueL == 1) {
                        flagType = true;
                        voltageTypeList = getValuesFromProperty.getValues(p, ontology, voltageType);
                        for (OWLLiteral vP: voltageTypeList){
                            typeVoltageLevel = vP;
                        }
                        if (!listTVTR.isEmpty() && listTVTR.size()<2) {
                            if (!voltageTypeList.isEmpty()) {
                                if (typeVoltageLevel.parseInteger() == 3) {
                                    for (OWLIndividual i : listTVTR) {
                                        tvtrForP.add(i);
                                    }
                                }
                            }
                            else{
                                for (OWLIndividual i : listTVTR) {
                                    tvtrForP.add(i);
                                }
                            }
                        }
                    }
                }
                if ( flagType) {
                    for (OWLIndividual b : indBus) {
                        OWLIndividual tvtrInd = null;
                        if (voltageTypeList.isEmpty()) {
                            Collection<OWLLiteral> voltageBus = getValuesFromProperty.getValues(b, ontology, hasVoltage);
                            for (OWLLiteral vE : voltageEq) {
                                valueVoltE = vE.parseInteger();
                            }
                            for (OWLLiteral vB : voltageBus) {
                                valueVoltB = vB.parseInteger();
                            }
                            if (valueVoltB == valueVoltE) {
                                Collection<OWLIndividual> tvtrOfB = getIndividualFromProperty.getIndivid(b, ontology, hasTVTR);
                                for (OWLIndividual tB : tvtrOfB) {
                                    tvtrInd = tB;
                                }
                                if (tvtrForP.size() < 2) {
                                    tvtrForP.add(tvtrInd);
                                }
                            }
                        }
                        else {
                            Collection<OWLIndividual> voltageInd = getIndividualFromProperty.getIndivid(b,ontology,hasVoltageLevel);
                            for (OWLIndividual v: voltageInd) {
                                Collection <OWLLiteral>  valueVoltageType = getValuesFromProperty.getValues(v,ontology,voltageType);
                                if (valueVoltageType.contains(typeVoltageLevel)){
                                    Collection<OWLIndividual> tvtrOfB = getIndividualFromProperty.getIndivid(b, ontology, hasTVTR);
                                    for (OWLIndividual tB : tvtrOfB) {
                                        tvtrInd = tB;
                                    }
                                    if (tvtrForP.size() < 2) {
                                        tvtrForP.add(tvtrInd);
                                    }
                                }
                            }
                        }
                    }
                    AxiomsAdding.addingSeveral(ontology,manager,df,p,tvtrForP,isLocated);
                }

            }
        }
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



        // SWRLAPIRule rule020_needOtherTvtr = ruleEngine.createSWRLRule("needOtherTVTR","Equipment(?e) ^ isProtectedBy(?e, ?p) ^ withTVTR(?e, 0) ^ typeOfControlledValue(?p, 1) ^ connectedEquipment(?e, ?c) ^ Bus(?c) ^ hasTVTR(?c, ?t) ^ voltageType(?p, ?v) ^ hasVoltageLevel(?t, ?volt) ^ voltageType(?volt, ?vv) ^ swrlb:equal(?v, ?vv) -> isLocated(?p, ?t)");
//        SWRLAPIRule rule020_needOtherTvtr = ruleEngine.createSWRLRule("needOtherTVTR", "Equipment(?e) ^ isProtectedBy(?e, ?p) ^ withTVTR(?e, 0) ^ typeOfControlledValue(?p, 1) ^ Bus(?c) ^ hasTVTR(?c, ?t) ^ hasVoltage(?e, ?v) ^ hasVoltageLevel(?t, ?volt) ^ hasVoltage(?volt, ?vv) ^ swrlb:equal(?v, ?vv) -> isLocated(?p, ?t)");
//        SWRLAPIRule rule020_t = ruleEngine.createSWRLRule("tvtrForProt_T", "Protection(?p) ^ YPTR(?e) ^ typeOfControlledValue(?p,1) ^ isProtectedBy(?e,?p) ^ hasTVTR(?e,?t) ^ hasVoltageLevel(?t,?volt) ^ voltageType(?volt,?v) ^ voltageType(?p,?vv) ^ swrlb:equal(?v,?vv) -> isLocated(?p,?t) ");
//        SWRLAPIRule rule020_l = ruleEngine.createSWRLRule("tvtrForProt_l", "Protection(?p) ^ Lines(?e) ^ typeOfControlledValue(?p,1) ^ isProtectedBy(?e,?p) ^ hasTVTR(?e,?t) -> isLocated(?p,?t) ");
//        SWRLAPIRule rule020_b = ruleEngine.createSWRLRule("tvtrForProt_b", "Protection(?p) ^ Bus(?e) ^ typeOfControlledValue(?p,1) ^ isProtectedBy(?e,?p) ^ hasTVTR(?e,?t) -> isLocated(?p,?t) ");
//        SWRLAPIRule rule021_r = ruleEngine.createSWRLRule("tvtrForProt_r", "Protection(?p) ^ ZREA(?e) ^ typeOfControlledValue(?p,1) ^ isProtectedBy(?e,?p) ^ hasTVTR(?e,?t) -> isLocated(?p,?t) ");
//        SWRLAPIRule rule021_s = ruleEngine.createSWRLRule("tvtrForProt_s", "Protection(?p) ^ ShortBus(?e) ^ typeOfControlledValue(?p,1) ^ isProtectedBy(?e,?p) ^ hasTVTR(?e,?t) -> isLocated(?p,?t) ");
//        SWRLAPIRule rule021_c = ruleEngine.createSWRLRule("tvtrForProt_c", "Protection(?p) ^ ZCAP(?e) ^ typeOfControlledValue(?p,1) ^ isProtectedBy(?e,?p) ^ hasTVTR(?e,?t) -> isLocated(?p,?t) ");
//        SWRLAPIRule rule021_m = ruleEngine.createSWRLRule("tvtrForProt_m", "Protection(?p) ^ ZMOT(?e) ^ typeOfControlledValue(?p,1) ^ isProtectedBy(?e,?p) ^ hasTVTR(?e,?t) -> isLocated(?p,?t) ");
        SWRLAPIRule rule20 = ruleEngine.createSWRLRule("tctrForT", "YPTR(?y) ^ hasTCTR(?y,?t) ^ isProtectedBy(?y,?p) ^ PDIF_O(?p) ^ hasVoltage(?t,?v) ^ hasVoltage(?p,?vv) ^ swrlb:equal(?v,?vv) -> isLocated(?p,?t)");
        SWRLAPIRule rule21 = ruleEngine.createSWRLRule("tctrForT_First", "YPTR(?y) ^ tctrFirst(?y,?t) ^ isProtectedBy(?y,?p) ^ PDIF_O(?p) ^ hasVoltage(?t,?v) ^ hasVoltage(?p,?vv) ^ swrlb:equal(?v,?vv) -> isLocated(?p,?t)");
        SWRLAPIRule rule24 = ruleEngine.createSWRLRule("tctrForTWithBus", "YPTR(?y) ^ hasVoltageLevel(?y,?volt) ^ hasAddedBus(?volt,1) ^ isProtectedBy(?y,?p) ^ PDIF_T(?p) ^ tctrFirst(?y,?r) ^ hasVoltageLevel(?r,?volt) -> isLocated(?p,?r)");
        // SWRLAPIRule rule24 = ruleEngine.createSWRLRule("tctrForTWithBus", "YPTR(?y) ^ hasVoltageLevel(?y,?volt) ^ hasAddedBus(?volt,1) ^ Protection(?p) ^ mainProtect(?p,?y)  ^ tctrFirst(?y,?r) ^ hasVoltageLevel(?r,?volt) -> isLocated(?p,?r)");
        SWRLAPIRule rule25 = ruleEngine.createSWRLRule("pdifForT", "YPTR(?y) ^ hasVoltageLevel(?y,?volt) ^ hasAddedBus(?volt,0) ^ isProtectedBy(?y,?p) ^ PDIF_T(?p) ^ hasTCTR(?y,?r) ^ hasVoltageLevel(?r,?volt) -> isLocated(?p,?r)");
        //  SWRLAPIRule rule25 = ruleEngine.createSWRLRule("pdifForT", "YPTR(?y) ^ hasVoltageLevel(?y,?volt) ^ hasAddedBus(?volt,0)  ^ Protection(?p) ^ mainProtect(?p,?y)^ hasTCTR(?y,?r) ^ hasVoltageLevel(?r,?volt) -> isLocated(?p,?r)");
        SWRLAPIRule rule22 = ruleEngine.createSWRLRule("reserveForT", "Protection(?p) ^ reserveProtect(?p,?y) ^ typeOfControlledValue(?p,0) ^ YPTR(?y) ^ tctrFirst(?y,?t) ^ hasVoltageLevel(?t,?v) ^ voltageType(?p,?e) ^ voltageType(?v,?w) ^ swrlb:equal(?e,?w) -> isLocated(?p,?t)");
        // SWRLAPIRule rule22_u = ruleEngine.createSWRLRule("reserveForT_U", "Protection(?p) ^ reserveProtect(?p,?y) ^ typeOfControlledValue(?p,1) ^ YPTR(?y) ^ hasTCTR(?y,?t) ^ hasVoltageLevel(?t,?v) ^ voltageType(?p,?e) ^ voltageType(?v,?w) ^ swrlb:equal(?e,?w) -> isLocated(?p,?t)");
        ruleEngine.infer();

        ChangeValueOfUsageTT.changeUsage(ofPowerTransformers, reasoner, ontology, isLocated, manager, df, use, 0, 1);

        SWRLAPIRule rule26 = ruleEngine.createSWRLRule("tctrForLines1WhileMainProt", "Lines(?l) ^ mainProtect(?p,?l) ^ connectedEquipment(?l,?c) ^ isSwitchedBy(?l,?b)  ^ hasTCTR(?b,?t) ^ hasVoltageLevel(?l,?v) ^ hasBus(?v,1) ^ hasAddedBus(?v,0) ^ mainProtect(?pr,?c) ^ isLocated(?pr,?t)  -> isLocated(?p,?t)");
        SWRLAPIRule rule27 = ruleEngine.createSWRLRule("tctrForLines2WhilePDIF_O", "Lines(?l) ^ mainProtect(?p,?l) ^ connectedEquipment(?l,?c) ^ isSwitchedBy(?l,?b) ^ hasTCTR(?b,?t) ^ hasVoltageLevel(?l,?v) ^ hasBus(?v,1) ^ hasAddedBus(?v,1) ^ isProtectedBy(?c,?pr) ^ PDIF_O(?pr) ^ isLocated(?pr,?t) -> isLocated(?p,?t)");
        SWRLAPIRule rule28 = ruleEngine.createSWRLRule("tctrForLinesSelf", "Lines(?l) ^ mainProtect(?p,?l) ^ connectedEquipment(?l,?c) ^ hasTCTR(?c,?t) ^ isSwitchedBy(?l,?b) ^ hasTCTR(?b,?t) ^ use(?t,0) ^ hasTCTR(?l,?t2) ^ hasTCTR(?b,?t2) ^ hasVoltageLevel(?l,?v) ^ hasBus(?v,1) -> isLocated(?p,?t2)");
        SWRLAPIRule rule29 = ruleEngine.createSWRLRule("tctrForLinesReserve", "Lines(?l) ^ reserveProtect(?p,?l) ^ typeOfControlledValue(?p,0) ^ isSwitchedBy(?l,?b) ^ hasTCTR(?b,?t) ^ hasVoltageLevel(?l,?v) ^ hasTCTR(?l,?t) ^ hasBus(?v,1) -> isLocated(?p,?t)");

        SWRLAPIRule rule30 = ruleEngine.createSWRLRule("tctrForLinesMainForRuShort", "Lines(?l) ^ mainProtect(?p,?l) ^ tctrFirst(?l,?t) ^ hasVoltageLevel(?l,?v) ^ hasBus(?v,0) -> isLocated(?p,?t)");
        SWRLAPIRule rule31 = ruleEngine.createSWRLRule("tctrForLinesResForRuShort", "Lines(?l) ^ reserveProtect(?p,?l) ^ typeOfControlledValue(?p,0) ^ tctrFirst(?l,?t) ^ hasVoltageLevel(?l,?v) ^ hasBus(?v,0) -> isLocated(?p,?t)");

        ruleEngine.infer();

        ChangeValueOfUsageTT.changeUsage(ofLines, reasoner, ontology, isLocated, manager, df, use, 0, 1);

        SWRLAPIRule rule32 = ruleEngine.createSWRLRule("tctrForBusAndPdifSelf", "Bus(?b) ^ mainProtect(?p,?b) ^ PDIF_B(?p) ^ connectedEquipment(?b,?c) ^ hasTCTR(?c,?t)  ^ isSwitchedBy(?b,?br) ^ hasTCTR(?br,?t) ^ use(?t, 0) ^ hasTCTR(?b,?t2) ^ hasTCTR(?br,?t2)-> isLocated(?p,?t2) ");
        // SWRLAPIRule rule33 = ruleEngine.createSWRLRule("tctrForBusAndPdifConnected","Bus(?b) ^ mainProtect(?p,?b) ^ PDIF_B(?p) ^ connectedEquipment(?b,?c) ^ hasTCTR(?c,?t)  ^ isSwitchedBy(?b,?br) ^ hasTCTR(?br,?t) ^ mainProtect(?pr,?c) ^ isLocated(?pr,?t) -> isLocated(?p,?t) ");
        // SWRLAPIRule rule34 = ruleEngine.createSWRLRule("tctrForBusAndPdifConnectedPDIF_O","Bus(?b) ^ mainProtect(?p,?b) ^ PDIF_B(?p) ^ connectedEquipment(?b,?c) ^ hasTCTR(?c,?t)  ^ isSwitchedBy(?b,?br) ^ hasTCTR(?br,?t) ^ isProtectedBy(?c,?pr) ^ PDIF_O(?pr) ^ isLocated(?pr,?t) -> isLocated(?p,?t) ");
        SWRLAPIRule rule34 = ruleEngine.createSWRLRule("tctrForBusAndPdifConnectedPDIF_O", "Bus(?b) ^ mainProtect(?p,?b) ^ PDIF_B(?p) ^ connectedEquipment(?b,?c) ^ hasTCTR(?c,?t)  ^ isSwitchedBy(?b,?br) ^ hasTCTR(?br,?t) ^ use(?t,1) -> isLocated(?p,?t) ");

        ruleEngine.infer();
        out = new FileOutputStream("src\\resources\\PS5.owl");

        manager.saveOntology(ontology, out);

        ChangeValueOfUsageTT.changeUsage(ofBus, reasoner, ontology, isLocated, manager, df, use, 0, 1);

        MU.getSignalsForRu(ontology,manager,df,ns,reasoner);


        ruleEngine.infer();

        out = new FileOutputStream("src\\resources\\PS6.owl");


        manager.saveOntology(ontology, out);



        SWRLAPIRule ProtectFrom_PDIF_T = ruleEngine.createSWRLRule ("ProtectFrom_PDIF_T","PDIF_T(?x)^SC(?y)->ProtectFrom(?x,?y)");
        SWRLAPIRule ProtectFrom_PDIF_O = ruleEngine.createSWRLRule ("ProtectFrom_PDIF_O","PDIF_O(?x)^SC(?y)->ProtectFrom(?x,?y)");
        SWRLAPIRule ProtectFrom_PDIF_B = ruleEngine.createSWRLRule ("ProtectFrom_PDIF_B","PDIF_B(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PDIF_F = ruleEngine.createSWRLRule ("ProtectFrom_PDIF_F","PDIF_F(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PDIF_L = ruleEngine.createSWRLRule ("ProtectFrom_PDIF_L","PDIF_L(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PDIS = ruleEngine.createSWRLRule ("ProtectFrom_PDIS","PDIS(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PDIS_T = ruleEngine.createSWRLRule ("ProtectFrom_PDIS_T","PDIS_T(?x)^SC(?y)->ProtectFrom(?x,?y)");
        SWRLAPIRule ProtectFrom_PDZ = ruleEngine.createSWRLRule ("ProtectFrom_PDZ", "PDZ(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PDZ_B = ruleEngine.createSWRLRule ("ProtectFrom_PDZ_B", "PDZ_B(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PGAS_WindingFault = ruleEngine.createSWRLRule ("ProtectFrom_PGAS_WindingFault","PGAS(?x)^WindingFault(?y)->ProtectFrom(?x,?y)");
        SWRLAPIRule ProtectFrom_PGAS_Overheat = ruleEngine.createSWRLRule ("ProtectFrom_PGAS_Overheat","PGAS(?x)^Overheat(?y)->ProtectFrom(?x,?y)");
        SWRLAPIRule ProtectFrom_PICE_Overheat = ruleEngine.createSWRLRule ("ProtectFrom_PICE_Overheat","PICE(?x)^Overheat(?y)->ProtectFrom(?x,?y)");
        SWRLAPIRule ProtectFrom_PMU = ruleEngine.createSWRLRule ("ProtectFrom_PMU","PMU(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PNTCN_DoublePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PNTCN_DoublePhaseToGND","PNTCN(?x) ^ DoublePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PNTCN_SinglePhase = ruleEngine.createSWRLRule ("ProtectFrom_PNTCN_SinglePhase","PNTCN_T(?x)^SinglePhase(?y)->ProtectFrom(?x,?y)");
        SWRLAPIRule ProtectFrom_PNTCN_T_DoublePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PNTCN_T_DoublePhaseToGND","PNTCN_T(?x) ^ DoublePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PNTCN_T_SinglePhase = ruleEngine.createSWRLRule ("ProtectFrom_PNTCN_T_SinglePhase","PNTCN_T(?x) ^ SinglePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PNTCN_T_SinglePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PNTCN_T_SinglePhaseToGND","PNTCN_T(?x) ^ SinglePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PNTCN_TriplePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PNTCN_TriplePhaseToGND","PNTCN(?x) ^ SinglePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_NVCHZ = ruleEngine.createSWRLRule ("ProtectFrom_NVCHZ","NVCHZ(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_POIL_ReducingOilLevel = ruleEngine.createSWRLRule ("ProtectFrom_POIL_ReducingOilLevel","POIL(?x) ^ ReducingOilLevel(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_POZZ_B_DoublePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_POZZ_B_DoublePhaseToGND","POZZ_B(?x) ^ DoublePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_POZZ_B_SinglePhase = ruleEngine.createSWRLRule ("ProtectFrom_POZZ_B_SinglePhase","POZZ_B(?x) ^ SinglePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_POZZ_B_TriplePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_POZZ_B_TriplePhaseToGND","POZZ_B(?x) ^ SinglePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_POZZ_DoublePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_POZZ_DoublePhaseToGND","POZZ(?x) ^ DoublePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_POZZ_SinglePhase = ruleEngine.createSWRLRule ("ProtectFrom_POZZ_SinglePhase","POZZ(?x) ^ SinglePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_POZZ_TriplePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_POZZ_TriplePhaseToGND","POZZ(?x) ^ SinglePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PRPN_WindingFault = ruleEngine.createSWRLRule ("ProtectFrom_PRPN_WindingFault","PRPN(?x) ^ WindingFault(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTCN_DoublePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PTCN_DoublePhaseToGND","PTCN(?x) ^ DoublePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTCN_TriplePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PTCN_TriplePhaseToGND","PTCN(?x) ^ SinglePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTC_DoublePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PTC_DoublePhaseToGND","PTC(?x) ^ DoublePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTC_T_TriplePhase = ruleEngine.createSWRLRule ("ProtectFrom_PTC_T_TriplePhase","PTC_T(?x) ^ TriplePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTC_TriplePhaseToGND = ruleEngine.createSWRLRule ("ProtectFrom_PTC_TriplePhaseToGND","PTC(?x) ^ SinglePhaseToGND(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTM = ruleEngine.createSWRLRule ("ProtectFrom_PTM","PTM(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTM_U_DoublePhase = ruleEngine.createSWRLRule ("ProtectFrom_PTM_U_DoublePhase","PTM_U(?x) ^ DoublePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTM_U_TriplePhase = ruleEngine.createSWRLRule ("ProtectFrom_PTM_U_TriplePhase","PTM_U(?x) ^ TriplePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTO = ruleEngine.createSWRLRule ("ProtectFrom_PTO","PTO(?x) ^ SC(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTO_T_DoublePhase = ruleEngine.createSWRLRule ("ProtectFrom_PTO_T_DoublePhase","PTO_T(?x) ^ DoublePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTO_T_TriplePhase = ruleEngine.createSWRLRule ("ProtectFrom_PTO_T_TriplePhase","PTO_T(?x) ^ TriplePhase(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTP_CurrentOverload = ruleEngine.createSWRLRule ("ProtectFrom_PTP_CurrentOverload","PTP(?x) ^ CurrentOverload(?y) -> ProtectFrom(?x, ?y)");
        SWRLAPIRule ProtectFrom_PTP_L_CurrentOverload = ruleEngine.createSWRLRule ("ProtectFrom_PTP_L_CurrentOverload","PTP_L(?x) ^ CurrentOverload(?y) -> ProtectFrom(?x, ?y)");


        ruleEngine.infer();

        SWRLAPIRule Damage_Bus = ruleEngine.createSWRLRule ("Damage_Bus","SC(?x) ^ Bus(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_ShortBus = ruleEngine.createSWRLRule ("Damage_ShortBus","SC(?x) ^ ShortBus(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_YPTR = ruleEngine.createSWRLRule ("Damage_YPTR","SC(?x) ^ YPTR(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_YPTR_CurrentOverload = ruleEngine.createSWRLRule ("Damage_YPTR_CurrentOverload","CurrentOverload(?x) ^ YPTR(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_YPTR_Overheat = ruleEngine.createSWRLRule ("Damage_YPTR_Overheat","Overheat(?x) ^ YPTR(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_YPTR_ReducingOilLevel = ruleEngine.createSWRLRule ("Damage_YPTR_ReducingOilLevel","ReducingOilLevel(?x) ^ YPTR(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_ZCAB = ruleEngine.createSWRLRule ("Damage_ZCAB","SC(?x) ^ ZCAB(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_ZLIN = ruleEngine.createSWRLRule ("Damage_ZLIN","SC(?x) ^ ZLIN(?y) -> Damage(?x, ?y)");
        SWRLAPIRule Damage_ZLIN_CurrentOverload = ruleEngine.createSWRLRule ("Damage_ZLIN_CurrentOverload","CurrentOverload(?x) ^ ZLIN(?y) -> Damage(?x, ?y)");
        SWRLAPIRule IFL_CAB_rule = ruleEngine.createSWRLRule ("IFL_CAB_rule","ZLIN(?z) ^ hasCN(?z, ?c) ^ ZCAB(?b) ^ hasCN(?b, ?c) -> iflWithCable(?z, 1)");

        ruleEngine.infer();

        out = new FileOutputStream("src\\resources\\PS7.owl");



      //  out = new FileOutputStream("C:\\Users\\anast\\OneDrive\\Рабочий стол\\magistratura\\project\\ontologies\\ont_pig_10_11.owl");
        manager.saveOntology(ontology, out);
    }

}
