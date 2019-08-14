import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
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

       // TEST.Start();


        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("C:\\Users\\anast\\Desktop\\magistratura\\project\\ontologies\\ont_PS3_pig.owl");
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Load ontology: " + ontology);


        SWRLRuleEngine ruleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
        ruleEngine.infer();

        SWRLAPIRule rule01 = ruleEngine.createSWRLRule("set0", "Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule02 = ruleEngine.createSWRLRule("set1", "Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,1)  -> setOfProtection(?p, 1)");
        SWRLAPIRule rule03 = ruleEngine.createSWRLRule("set0_1", "Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule04 = ruleEngine.createSWRLRule("set2", "Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,1)  -> setOfProtection(?p, 2)");

        ruleEngine.infer();

        int k = 0;

        String ns = "http://www.semanticweb.org/anast/ontologies/2019/3/untitled-ontology-22#";
        OWLIndividual trans = null;
        OWLDataFactory df = manager.getOWLDataFactory();
        OWLClass yptrClass = df.getOWLClass(IRI.create(ns + "YPTR"));
        OWLClass eqClass = df.getOWLClass(IRI.create(ns + "Equipment"));
        OWLClass linesClass = df.getOWLClass(IRI.create(ns + "Lines"));
        OWLClass CBR = df.getOWLClass(IRI.create(ns + "XCBR"));
        OWLClassExpression CBR_E = df.getOWLClass(IRI.create(ns + "XCBR"));
        OWLClass reaClass = df.getOWLClass((IRI.create(ns + "ZREA")));
        OWLClassExpression TCTR_E = df.getOWLClass(IRI.create(ns + "TCTR"));
        OWLClass capClass = df.getOWLClass(IRI.create(ns + "ZCAP"));
        OWLClassExpression TVTR_E = df.getOWLClass(IRI.create(ns + "TVTR"));
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
        OWLObjectProperty mainProtect =  df.getOWLObjectProperty(IRI.create(ns+"mainProtect"));
        OWLObjectProperty addedEquipment =  df.getOWLObjectProperty(IRI.create(ns+"addedEquipment"));
        OWLObjectProperty hasVoltageLevel = df.getOWLObjectProperty(IRI.create(ns + "hasVoltageLevel"));
        OWLObjectProperty isSwitchedBy = df.getOWLObjectProperty(IRI.create(ns + "isSwitchedBy"));
        OWLObjectProperty connectedWithCbr = df.getOWLObjectProperty(IRI.create(ns + "connectedWithCbr"));
        OWLDataProperty busType = df.getOWLDataProperty(IRI.create(ns + "busType"));
        OWLDataProperty hasBus_ = df.getOWLDataProperty(IRI.create(ns + "hasBus"));
        OWLClass busClass = df.getOWLClass(IRI.create(ns + "Bus"));
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        String ptwClass = "PTW";
        OWLClass PTW = df.getOWLClass(IRI.create(ns + "PTW"));
        OWLClass oshClass = df.getOWLClass(IRI.create(ns + "ShortBus"));
        OWLObjectProperty connectedEquipment = df.getOWLObjectProperty(IRI.create(ns+"connectedEquipment"));

        ////////////////////////////////////TT,TV and CBR for Transformers/////////////////////////////////////////
        Set<OWLNamedIndividual> indPTW = getIndividualByClass.getIndividualofClass(PTW, reasoner);
        for (OWLNamedIndividual i : indPTW) {
            System.out.println(i.getIRI().getFragment());
            OWLIndividual bI = null;
            OWLIndividual volt = null;
            Collection<OWLIndividual> indCN = getIndividualFromProperty.getIndivid(i, ontology, hasCN);
            Collection<OWLIndividual> indVoltage = getIndividualFromProperty.getIndivid(i, ontology, hasVoltageLevel);
            for (OWLIndividual v : indVoltage) {
                volt = v;
            }
            for (OWLIndividual t : getIndividualFromProperty.getIndivid(i, ontology, ptwOf)) {
                trans = t;
            }
            for (OWLIndividual j : indCN) {
                Collection<OWLIndividual> nodes = new HashSet<>();
                List<OWLIndividual> cbrOfT = new ArrayList<>();
                List<OWLIndividual> tctrOfT = new ArrayList<>();
                List<OWLIndividual> tvtrOfT = new ArrayList<>();
                List<OWLIndividual> eqOfT = new ArrayList<>();
                List<OWLIndividual> adOfT = new ArrayList<>();
                lookFor.getTT_TV_CBR(j, ontology, ns, df, bI, nodes, false, tctrOfT, tvtrOfT, cbrOfT, adOfT);
                CreateShortBus.CreateBus(ontology, manager, df, ns, j, trans, volt, nodes, k);
                lookFor.getConnectedEquipment2(j,ontology,ns,df,null,eqClass,eqOfT,i);
                AxiomsAdding.adding(ontology, manager, df, trans, tctrOfT.get(0), tctrFirst);
                tctrOfT.remove(0);
                AxiomsAdding.addingSeveral(ontology, manager, df, trans, tctrOfT, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, trans, tvtrOfT, hasTVTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, trans, cbrOfT, isSwitchedBy);
                System.out.println("!!!!!!!TRANS "+ eqOfT.size());
                AxiomsAdding.addingSeveral(ontology,manager,df,trans,eqOfT,connectedEquipment);
                AxiomsAdding.addingSeveral(ontology,manager,df,trans,adOfT,addedEquipment);
                k++;
            }
        }
        //////////////////////////////////////TT, TV and CBR for Lines///////////////////////////////////////////
        Set<OWLNamedIndividual> indLines = getIndividualByClass.getIndividualofClass(linesClass, reasoner);
        for (OWLNamedIndividual l : indLines) {
            Collection<OWLIndividual> indCnL = getIndividualFromProperty.getIndivid(l, ontology, hasCN);
            Collection<OWLIndividual> voltL = getIndividualFromProperty.getIndivid(l, ontology, hasVoltageLevel);
            for (OWLIndividual v : voltL) {
                Collection<OWLLiteral> b = getValuesFromProperty.getValues(v, ontology, hasBus_);
                for (OWLIndividual i : indCnL) {
                    List<OWLIndividual> tctrOfL = new ArrayList<>();
                    List<OWLIndividual> tvtrOfL = new ArrayList<>();
                    List<OWLIndividual> cbrOfL = new ArrayList<>();
                    List<OWLIndividual> eqOfL = new ArrayList<>();
                    Collection<OWLIndividual> nodesLines = new HashSet<>();
                    lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesLines, false, tctrOfL, tvtrOfL, cbrOfL, eqOfL);
                    AxiomsAdding.addingSeveral(ontology, manager, df, l, tctrOfL, hasTCTR);
                    AxiomsAdding.addingSeveral(ontology, manager, df, l, tvtrOfL, hasTVTR);
                    AxiomsAdding.addingSeveral(ontology, manager, df, l, cbrOfL, isSwitchedBy);
                    if (b.isEmpty()) {
                        CreateShortBus.CreateBus(ontology, manager, df, ns, i, l, v, nodesLines, k);
                    } else {
                        CreateShortBus.CreateFalseBus(ontology, manager, df, ns, i, l, v, nodesLines, k);
                    }
                    lookFor.getConnectedEquipment2(i,ontology,ns,df,null,eqClass,eqOfL,l);
                    AxiomsAdding.addingSeveral(ontology,manager,df,l,eqOfL,connectedEquipment);
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
                lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesR, false, tctrOfR, tvtrOfR, cbrOfR,eqOfR);
                lookFor.getConnectedEquipment2(i,ontology,ns,df,null,eqClass,eqOfR,r);
                AxiomsAdding.addingSeveral(ontology, manager, df, r, tctrOfR, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, r, tvtrOfR, hasTVTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, r, cbrOfR, isSwitchedBy);
                AxiomsAdding.addingSeveral(ontology,manager,df,r,eqOfR,connectedEquipment);
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
                lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesCp, false, tctrOfCp, tvtrOfCp, cbrOfCp, eqOfCp);
                lookFor.getConnectedEquipment2(i,ontology,ns,df,null,eqClass,eqOfCp,c);
                AxiomsAdding.addingSeveral(ontology, manager, df, c, tctrOfCp, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, c, tvtrOfCp, hasTVTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, c, cbrOfCp, isSwitchedBy);
                AxiomsAdding.addingSeveral(ontology,manager,df,c,eqOfCp,connectedEquipment);
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
                lookFor.getTT_TV_CBR(i, ontology, ns, df, null, nodesS, false, tctrOfS, tvtrOfS, cbrOfS,eqOfS);
                lookFor.getConnectedEquipment2(i,ontology,ns,df,null,eqClass,eqOfS,s);
                AxiomsAdding.addingSeveral(ontology, manager, df, s, tctrOfS, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, s, tvtrOfS, hasTVTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, s, cbrOfS, isSwitchedBy);
                AxiomsAdding.addingSeveral(ontology,manager,df,s,eqOfS,connectedEquipment);
            }
        }
        ///////////////////////////////TT, TV and cbr for Bus///////////////////////////////////////////////////////////
        Set<OWLNamedIndividual> indBus = getIndividualByClass.getIndividualofClass(busClass, reasoner);
        for (OWLNamedIndividual b : indBus) {
            Collection<OWLIndividual> indCnB = getIndividualFromProperty.getIndivid(b, ontology, hasCN);
            Collection<OWLLiteral> typeB = getValuesFromProperty.getValues(b, ontology, busType);
            for (OWLIndividual j : indCnB) {
                List<OWLIndividual> tctrOfB = new ArrayList<>();
                List<OWLIndividual> tvtrOfB = new ArrayList<>();
                List<OWLIndividual> cbrOfB = new ArrayList<>();
                List<OWLIndividual> eqOfB = new ArrayList<>();
                Collection<OWLIndividual> nodesBus = new HashSet<>();
                lookFor.getTT_TV_CBR(j, ontology, ns, df, null, nodesBus, false, tctrOfB, tvtrOfB, cbrOfB, eqOfB);
               // lookFor.getConnectedEquipment2(j,ontology,ns,df,null,eqClass,eqOfB,b);
                if (!typeB.isEmpty()) {
                    lookFor.getTT_TV_CBR2(j, ontology, ns, df, null, false, tctrOfB, tvtrOfB, cbrOfB);
                }
                AxiomsAdding.addingSeveral(ontology, manager, df, b, tctrOfB, hasTCTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, b, tvtrOfB, hasTVTR);
                AxiomsAdding.addingSeveral(ontology, manager, df, b, cbrOfB, isSwitchedBy);
                AxiomsAdding.addingSeveral(ontology,manager,df,b,eqOfB,connectedEquipment);
            }
        }
        //////////////////////////////////////////////////////////////////////////////////////
//        Set<OWLNamedIndividual> indT = getIndividualByClass.getIndividualofClass(yptrClass, reasoner);
//        for (OWLNamedIndividual i : indT) {
//            List<OWLIndividual> eqList = new ArrayList<>();
//            eqList.add(i);
//            for (OWLIndividual p : getIndividualFromProperty.getIndivid(i, ontology, hasPTW)) {
//                Collection<OWLIndividual> indCNp = getIndividualFromProperty.getIndivid(p, ontology, hasCN);
//                for(OWLIndividual j: indCNp) {
//                    lookFor.getConnectedEquipment2();
//                }
//            }
//        }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        SWRLAPIRule rule6 = ruleEngine.createSWRLRule("CN", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,?vv) ^ swrlb:equal(?vv,2) -> voltageType(?b, 2)");
        SWRLAPIRule rule7 = ruleEngine.createSWRLRule("BN", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,?vv) ^ swrlb:equal(?vv,1) -> voltageType(?b, 1)");
        SWRLAPIRule rule8 = ruleEngine.createSWRLRule("NN", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,3) -> voltageType(?b, 3)");
        SWRLAPIRule rule9 = ruleEngine.createSWRLRule("Voltage", "ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ hasVoltage(?v,?vv) -> hasVoltage(?b, ?vv)");
        ruleEngine.infer();

        SWRLAPIRule rule1 = ruleEngine.createSWRLRule("oshinCN2", "YPTR(?x) ^ ShortBus(?b)^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,2) ^ hasVoltage(?v,?vv) ^ swrlb:greaterThanOrEqual(?vv, 330) -> setOfProtection(?x, 3)");
        SWRLAPIRule rule2 = ruleEngine.createSWRLRule("oshinCN", "YPTR(?x) ^ ShortBus(?b)^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 2) -> setOfProtection(?x, 1)");
        SWRLAPIRule rule3 = ruleEngine.createSWRLRule("oshinBN", "AutoTransformers(?x) ^ ShortBus(?b)^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 1) ^ hasVoltage(?v,220) -> setOfProtection(?x, 4)");
        SWRLAPIRule rule4 = ruleEngine.createSWRLRule("oshinNN", "YPTR(?x) ^ connectedEquipment(?e, ?c) ^ ZREA(?c) -> setOfProtection(?x, 2)");
        SWRLAPIRule rule12 = ruleEngine.createSWRLRule("oshinBN2", "AutoTransformers(?x) ^ base(?x,6) -> setOfProtection(?x, 6)");
        SWRLAPIRule rule13 = ruleEngine.createSWRLRule("PDIF_O_forCN", "PowerTransformers(?p) ^ hasTCTR(?p,?t) ^ hasVoltageLevel(?t,?v) ^ voltageType(?v,2) ^ isSwitchedBy(?p,?c) ^ hasTCTR(?c,?t) -> setOfProtection(?p,7)");
        ruleEngine.infer();


/////////////////////проверка на защиты////////////////////


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
        OWLObjectProperty hasVoltage = df.getOWLObjectProperty(IRI.create(ns + "hasVoltageLevel"));

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
        /////////////////////////////////////////////////////////////
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
        rProtectionTr.add(CRI_NN);
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
        generalT.add(PTP);
        generalT.add(PTM_U);

        List<OWLClass> base0 = new ArrayList<>();
        base0.add(PDIF_T);
        base0.add(PDIS_T);
        base0.add(PNTCN_T);
        base0.add(PDIS_T);
        base0.add(PNTCN_T);

        List<OWLClass> base1 = new ArrayList<>();
        base1.add(PDIF_T);
        base1.add(PDIS_T);
        base1.add(PNTCN_T);
        base1.add(PDIS_T);
        base1.add(PNTCN_T);
        base1.add(PDIF_T);

        List<OWLClass> base2 = new ArrayList<>();
        base2.add(PDIF_T);
        base2.add(PTC_T);
        base2.add(PTCN);
        base2.add(PTC_T);
        base2.add(PTCN);

        List<OWLClass> base3 = new ArrayList<>();
        base3.add(PDIF_T);
        base3.add(PTC_T);
        base3.add(PTCN);
        base3.add(PTC_T);
        base3.add(PTCN);
        base3.add(PDIF_T);

        List<OWLClass> base4 = new ArrayList<>();
        base4.add(PDIF_T);
        base4.add(PTC_T);
        base4.add(PTCN);

        List<OWLClass> base5 = new ArrayList<>();
        base5.add(PDIF_T);
        base5.add(PTC_T);
        base5.add(PTCN);
        base5.add(PDIF_T);

        List<OWLClass> base6 = new ArrayList<>();
        base6.add(PDIF_T);
        base6.add(PDIS_T);
        base6.add(PNTCN_T);
        base6.add(PDIS_T);
        base6.add(PNTCN_T);
        base6.add(PDIF_T);


        List<OWLClass> base7 = new ArrayList<>();
        base4.add(PTO_T);
        base4.add(PTC_T);
        base4.add(PTCN);

        protectionOfTrans.put(0, base0);
        protectionOfTrans.put(1, base1);
        protectionOfTrans.put(2, base2);
        protectionOfTrans.put(3, base3);
        protectionOfTrans.put(4, base4);
        protectionOfTrans.put(5, base5);
        protectionOfTrans.put(6, base6);
        protectionOfTrans.put(7, base7);

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

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////
        int variant = (int) (Math.random() * 2 + 1);
        int variant3 = (int) (Math.random() * 2);
        for (OWLNamedIndividual i : indLines) {
            int baseNumber = -1;
            Collection<OWLLiteral> setBase = getValuesFromProperty.getValues(i, ontology, base);
            Collection<OWLLiteral> setVar = getValuesFromProperty.getValues(i, ontology, setOf);
            for (OWLLiteral n : setBase) {
                baseNumber = n.parseInteger();
                List<OWLClass> protectVar = protectionOfLines1.get(baseNumber);
                k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectVar, df, manager, k, mProtectionL, rProtectionL, techProtectionL);

            }
            if (!setVar.isEmpty()) {
                for (OWLLiteral n : setVar) {
                    int varNumber = n.parseInteger();
                    if (varNumber != 2) {
                        CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, varNumber, k, mProtectionL, rProtectionL, techProtectionL);
                        k++;
                    } else {
                        CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, variant, k, mProtectionL, rProtectionL, techProtectionL);
                        k++;
                    }
                }
            } else if( baseNumber!=2 ) {
                CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, variant3, k, mProtectionL, rProtectionL, techProtectionL);
                k++;
            }
        }
        Set<OWLNamedIndividual> indTransform = getIndividualByClass.getIndividualofClass(yptrClass, reasoner);
        for (OWLNamedIndividual n : indTransform) {
            k = CreateComplectOfProtection.CreateComplect(n, ontology, ns, generalT, df, manager, k, mProtectionTr, rProtectionTr, techProtectionTr);

            Collection<OWLLiteral> baseT = getValuesFromProperty.getValues(n, ontology, base);
            Collection<OWLLiteral> setOfT = getValuesFromProperty.getValues(n, ontology, setOf);
            for (OWLLiteral b : baseT) {
                int baseTNumber = b.parseInteger();
                k = CreateComplectOfProtection.CreateComplect(n, ontology, ns, protectionOfTrans.get(baseTNumber), df, manager, k, mProtectionTr, rProtectionTr, techProtectionTr);
            }
            for (OWLLiteral s : setOfT) {
                int setOfNumber = s.parseInteger();
                k = CreateComplectForSeveral.CreateComplect_2(n, setOfNumber, ontology, ns, protectionOfTrans1.get(setOfNumber), df, manager, k, mProtectionTr, rProtectionTr, techProtectionTr);

            }
        }

        for (OWLNamedIndividual i : indBus) {
            Collection<OWLLiteral> baseB = getValuesFromProperty.getValues(i, ontology, base);
            if (baseB.isEmpty()) {
                k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfBus.get(1), df, manager, k, mProtectionB, rProtectionB, techProtectionB);


            } else {
                for (OWLLiteral b : baseB) {
                    int busNumber = b.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfBus.get(busNumber), df, manager, k, mProtectionB, rProtectionB, techProtectionB);

                }
            }
        }

        for (OWLNamedIndividual j : indRea) {
            Collection<OWLLiteral> baseRea = getValuesFromProperty.getValues(j, ontology, base);
            if (!baseRea.isEmpty()) {
                for (OWLLiteral l : baseRea) {
                    int reaNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(j, ontology, ns, protectionOfRea.get(reaNumber), df, manager, k, mProtectionR, rProtectionR, techProtectionR);

                }
            }
        }
        Set<OWLNamedIndividual> indEfn = getIndividualByClass.getIndividualofClass(efnClass, reasoner);
        for (OWLNamedIndividual i : indEfn) {
            Collection<OWLLiteral> baseE = getValuesFromProperty.getValues(i, ontology, base);
            if (!baseE.isEmpty()) {
                for (OWLLiteral l : baseE) {
                    int efnNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfEFN.get(efnNumber), df, manager, k, mProtectionR, rProtectionR, techProtectionR);

                }
            }
        }


        for (OWLNamedIndividual i : indCAP) {
            Collection<OWLLiteral> baseC = getValuesFromProperty.getValues(i, ontology, base);
            if (!baseC.isEmpty()) {
                for (OWLLiteral l : baseC) {
                    int capNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectionOfEFN.get(capNumber), df, manager, k, mProtectionBat, rProtectionBat, techProtectionBat);

                }
            }
        }

        Set<OWLNamedIndividual> indCBR = getIndividualByClass.getIndividualofClass(cbrClass, reasoner);
        for (OWLNamedIndividual j : indCBR) {
            Collection<OWLLiteral> baseBr = getValuesFromProperty.getValues(j, ontology, base);
            if (!baseBr.isEmpty()) {
                for (OWLLiteral l : baseBr) {
                    int cbrNumber = l.parseInteger();
                    k = CreateComplectOfProtection.CreateComplect(j, ontology, ns, protectionOfCBR.get(cbrNumber), df, manager, k, mProtectionBr, rProtectionBr, techProtectionBr);

                }
            }
        }

        SWRLAPIRule rule14  = ruleEngine.createSWRLRule("pdifForOther", "Equipment(?e) ^ connectedEquipment(?e, ?c) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ hasVoltageLevel(?c, ?v) ^ voltageType(?v, ?vv) ^ voltageType(?p, ?t) ^ swrlb:equal(?vv, ?t) -> isProtectedBy(?c, ?p)");
        SWRLAPIRule rule15  = ruleEngine.createSWRLRule("mainPr", "Equipment(?e) ^ connectedEquipment(?e, ?c) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ hasVoltageLevel(?c, ?v) ^ voltageType(?v, ?vv) ^ voltageType(?p, ?t) ^ swrlb:equal(?vv, ?t) -> mainProtect(?p, ?c)");
        SWRLAPIRule rule16  = ruleEngine.createSWRLRule("pdifForS", "Equipment(?e) ^ hasShortBus(?e, ?b) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ voltageType(?b, ?v) ^ voltageType(?p, ?t) ^ swrlb:equal(?v, ?t) -> isProtectedBy(?b, ?p)");
        SWRLAPIRule rule17  = ruleEngine.createSWRLRule("mainPrS", "Equipment(?e) ^ hasShortBus(?e, ?b) ^ isProtectedBy(?e, ?p) ^ PDIF_O(?p) ^ voltageType(?b, ?v) ^ voltageType(?p, ?t) ^ swrlb:equal(?v, ?t) -> mainProtect(?p, ?b)");
        SWRLAPIRule rule18 = ruleEngine.createSWRLRule("connectedE", "Equipment(?e) ^ connectedEquipment(?e,?c) -> connectedEquipment(?c,?e)");
        ruleEngine.infer();

        OutputStream out = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_pig_1.owl");
        manager.saveOntology(ontology, out);
    }

}
