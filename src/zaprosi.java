import org.nfunk.jep.function.Add;
import org.openrdf.model.vocabulary.OWL;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
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
        File file = new File("C:\\Users\\anast\\Desktop\\magistratura\\project\\ontologies\\ont_PS3_pig.owl");
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        System.out.println("Load ontology: " + ontology);


        SWRLRuleEngine ruleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
         ruleEngine.infer();

        SWRLAPIRule rule01 = ruleEngine.createSWRLRule("set0" ,"Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule02 = ruleEngine.createSWRLRule("set1" ,"Lines(?p) ^ base(?p, 0) ^ hasChannel(?p,1)  -> setOfProtection(?p, 1)");
        SWRLAPIRule rule03 = ruleEngine.createSWRLRule("set0_1" ,"Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,0)  -> setOfProtection(?p, 0)");
        SWRLAPIRule rule04 = ruleEngine.createSWRLRule("set2" ,"Lines(?p) ^ base(?p, 1) ^ hasChannel(?p,1)  -> setOfProtection(?p, 2)");

        ruleEngine.infer();

        String ns = "http://www.semanticweb.org/anast/ontologies/2019/3/untitled-ontology-22#";
        OWLIndividual trans = null;
        Set<OWLAxiom> axiom = new HashSet<>();
        OWLDataFactory df = manager.getOWLDataFactory();
        OWLObjectProperty hasCN = df.getOWLObjectProperty(IRI.create(ns + "hasCN"));
        OWLObjectProperty ptwOf = df.getOWLObjectProperty(IRI.create(ns + "ptwOf"));
        OWLObjectProperty hasBus = df.getOWLObjectProperty(IRI.create(ns + "hasShortBus"));
        OWLObjectProperty hasVoltageLevel = df.getOWLObjectProperty(IRI.create(ns + "hasVoltageLevel"));
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        String ptwClass = "PTW";
        OWLClass PTW = df.getOWLClass(IRI.create(ns + "PTW"));
        OWLClass oshClass = df.getOWLClass(IRI.create(ns + "ShortBus"));
        Set<OWLNamedIndividual> indPTW = getIndividualByClass.getIndividualofClass(PTW, reasoner);
        System.out.println("The Individuals of class : ");
        int k = 0;
        for (OWLNamedIndividual i : indPTW) {
            System.out.println(i.getIRI().getFragment());
            System.out.println(i);
            OWLIndividual bI = null;
            OWLIndividual volt = null;
            Collection<OWLIndividual> indCN = getIndividualFromProperty.getIndivid(i, ontology, hasCN);
            Collection<OWLIndividual> indVoltage = getIndividualFromProperty.getIndivid(i,ontology,hasVoltageLevel);
            for (OWLIndividual v: indVoltage){
                volt = v;
            }
            for (OWLIndividual t : getIndividualFromProperty.getIndivid(i, ontology, ptwOf)) {
                trans = t;
            }
            for (OWLIndividual j : indCN) {
                Collection<OWLIndividual> nodes = new HashSet<>();

                boolean start = false;
                lookForBreacker.get2(j, ontology, ns, df, bI, nodes, start);
                if (nodes.size() >= 2) {
                    k++;
                    String oshinName = "shortBus_" + k;
                    System.out.println("has oshin");
                    OWLIndividual indShin = df.getOWLNamedIndividual(IRI.create(ns + oshinName));
                    AxiomsAdding.AddingClass(ontology,manager,df,indShin,oshClass);
                    AxiomsAdding.adding(ontology,manager,df,indShin,j,hasCN);
                    AxiomsAdding.adding(ontology,manager,df,trans,indShin,hasBus);
                    AxiomsAdding.adding(ontology,manager,df,indShin,volt,hasVoltageLevel);
                    for (OWLIndividual n : nodes) {
                        AxiomsAdding.adding(ontology,manager,df,indShin,n,hasCN);
                    }

                }
            }

        }


        SWRLAPIRule rule6 = ruleEngine.createSWRLRule("CN","ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,?vv) ^ swrlb:equal(?vv,2) -> voltageType(?b, 2)");
        SWRLAPIRule rule7 = ruleEngine.createSWRLRule("BN","ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,?vv) ^ swrlb:equal(?vv,1) -> voltageType(?b, 1)");
        SWRLAPIRule rule8 = ruleEngine.createSWRLRule("NN","ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,3) -> voltageType(?b, 3)");
        SWRLAPIRule rule9 = ruleEngine.createSWRLRule("Voltage","ShortBus(?b) ^ hasVoltageLevel(?b,?v) ^ hasVoltage(?v,?vv) -> hasVoltage(?b, ?vv)");
        ruleEngine.infer();

        SWRLAPIRule rule1 = ruleEngine.createSWRLRule("oshinCN2","YPTR(?x) ^ ShortBus(?b)^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,2) ^ hasVoltage(?v,?vv) ^ swrlb:greaterThanOrEqual(?vv, 330) -> setOfProtection(?x, 3)");
        SWRLAPIRule rule2 = ruleEngine.createSWRLRule("oshinCN","YPTR(?x) ^ ShortBus(?b)^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 2) -> setOfProtection(?x, 1)");
        SWRLAPIRule rule3 = ruleEngine.createSWRLRule("oshinBN","AutoTransformers(?x) ^ ShortBus(?b)^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 1) ^ hasVoltage(?v,220) -> setOfProtection(?x, 4)");
        SWRLAPIRule rule4 = ruleEngine.createSWRLRule("oshinNN","YPTR(?x) ^ hasReactors(?x,?b) -> setOfProtection(?x, 2)");
        SWRLAPIRule rule12 = ruleEngine.createSWRLRule("oshinBN2","AutoTransformers(?x) ^ base(?x,6) -> setOfProtection(?x, 6)");


        ruleEngine.infer();


/////////////////////проверка на защиты////////////////////
        OWLClass linesClass = df.getOWLClass(IRI.create(ns + "Lines"));
        OWLClass yptrClass = df.getOWLClass(IRI.create(ns+"YPTR"));
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
        OWLObjectProperty hasVoltage = df.getOWLObjectProperty(IRI.create(ns + "hasVoltageLevel"));

        OWLDataProperty hasChanel = df.getOWLDataProperty(IRI.create(ns + "hasChannel"));
        OWLDataProperty base = df.getOWLDataProperty(IRI.create(ns + "base"));
        OWLDataProperty setOf = df.getOWLDataProperty(IRI.create(ns + "setOfProtection"));

        //////////////////////////////////////TRANSFORMERS////////////////////////////////////////////////////
        OWLClass CRI_NN = df.getOWLClass(IRI.create(ns+"CRI_NN"));
        OWLClass PCRI = df.getOWLClass(IRI.create(ns+"PCRI"));
        OWLClass PCRI_V = df.getOWLClass(IRI.create(ns+"PCRI_V"));
        OWLClass PDIF_O = df.getOWLClass(IRI.create(ns+"PDIF_O"));
        OWLClass PDIF_T = df.getOWLClass(IRI.create(ns+"PDIF_T"));
        OWLClass PDIS_T = df.getOWLClass(IRI.create(ns+"PDIS_T"));
        OWLClass PGAS = df.getOWLClass(IRI.create(ns+"PGAS"));
        OWLClass PICE = df.getOWLClass(IRI.create(ns+"PICE"));
        OWLClass PNTCN_T = df.getOWLClass(IRI.create(ns+"PNTCN_T"));
        OWLClass POIL = df.getOWLClass(IRI.create(ns+"POIL"));
        OWLClass PRPN = df.getOWLClass(IRI.create(ns+"PRPN"));
        OWLClass PTC_T = df.getOWLClass(IRI.create(ns+"PTC_T"));
        OWLClass PTCN = df.getOWLClass(IRI.create(ns+"PTCN"));
        OWLClass PTM_U = df.getOWLClass(IRI.create(ns+"PTM_U"));
        OWLClass PTO_T = df.getOWLClass(IRI.create(ns+"PTO_T"));
        OWLClass PTP = df.getOWLClass(IRI.create(ns+"PTP"));
        ////////////////////////Bus/////////////////////////////////////////
        OWLClass busClass = df.getOWLClass((IRI.create(ns+"Bus")));
        OWLClass PDIF_B = df.getOWLClass(IRI.create(ns+"PDIF_B"));
        OWLClass PDZ_B = df.getOWLClass(IRI.create(ns+"PDZ_B"));
        OWLClass PLOG = df.getOWLClass(IRI.create(ns+"PLOG"));
        OWLClass PMU = df.getOWLClass(IRI.create(ns+"PMU"));
        OWLClass POZZ_B = df.getOWLClass(IRI.create(ns+"POZZ_B"));

        ///////////////////////reactors///////////////////////////////////

        OWLClass reaClass = df.getOWLClass((IRI.create(ns+"ZREA")));
        OWLClass efnClass = df.getOWLClass((IRI.create(ns+"YEFN")));
        OWLClass PDIF_P = df.getOWLClass(IRI.create(ns+"PDIF_P"));
        OWLClass PDIF_R = df.getOWLClass(IRI.create(ns+"PDIF_R"));
        OWLClass PDIS_R = df.getOWLClass(IRI.create(ns+"PDIS_R"));
        OWLClass PGAS_R = df.getOWLClass(IRI.create(ns+"PGAS_R"));
        OWLClass PICE_R = df.getOWLClass(IRI.create(ns+"PICE_R"));
        OWLClass POIL_R = df.getOWLClass(IRI.create(ns+"POIL_R"));
        OWLClass PTP_R = df.getOWLClass(IRI.create(ns+"PTP_R"));
        OWLClass PUPRAV_W = df.getOWLClass(IRI.create(ns+"PUPRAV_W"));
        OWLClass PWIN = df.getOWLClass(IRI.create(ns+"PWIN"));
        OWLClass PCRI_V_R = df.getOWLClass(IRI.create(ns+"PCRI_V_R"));
        ///////////////////////////////////battery////////////////////
        OWLClass capClass = df.getOWLClass(IRI.create(ns+"ZCAP"));
        OWLClass PBNN = df.getOWLClass(IRI.create(ns+"PBNN"));
        OWLClass PBU = df.getOWLClass(IRI.create(ns+"PBU"));
        OWLClass PDIF_BAT = df.getOWLClass(IRI.create(ns+"PDIF_BAT"));
        OWLClass PDU = df.getOWLClass(IRI.create(ns+"PDU"));
        OWLClass PTM_BAT = df.getOWLClass(IRI.create(ns+"PTM_BAT"));
        OWLClass PTP_BAT = df.getOWLClass(IRI.create(ns+"PTP_BAT"));
        OWLClass PVP = df.getOWLClass(IRI.create(ns+"PVP"));

        /////////////////ConductingEquipment////////////////////////////
        OWLClass cbrClass = df.getOWLClass(IRI.create(ns+"XCBR"));
        OWLClass PDIS_C = df.getOWLClass(IRI.create(ns+"PDIS_C"));
        OWLClass PDZ_C = df.getOWLClass(IRI.create(ns+"PDZ_C"));
        OWLClass PMU_C = df.getOWLClass(IRI.create(ns+"PMU_C"));
        OWLClass PNTCN_C = df.getOWLClass(IRI.create(ns+"PNTCN_C"));
        OWLClass PTC_C = df.getOWLClass(IRI.create(ns+"PTC_C"));
        OWLClass PTCN_C = df.getOWLClass(IRI.create(ns+"PTCN_C"));
        OWLClass PTM_C = df.getOWLClass(IRI.create(ns+"PTM_C"));
        OWLClass PTM_U_C = df.getOWLClass(IRI.create(ns+"PTM_U_C"));

        /////////////////////protectionList/////////////////

        Map<Integer, List<OWLClass>> protectionOfLines1 = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfTrans = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfTrans1 = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfBus = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfRea = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfEFN = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfCAP = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfCBR = new HashMap<>();

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

        protectionOfTrans.put(0,base0);
        protectionOfTrans.put(1,base1);
        protectionOfTrans.put(2,base2);
        protectionOfTrans.put(3,base3);
        protectionOfTrans.put(4,base4);
        protectionOfTrans.put(5,base5);
        protectionOfTrans.put(6,base6);
        protectionOfTrans.put(7,base7);

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

        protectionOfBus.put(0,baseBus0);
        protectionOfBus.put(1,baseBus1);
        protectionOfBus.put(2,baseBus2);
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

        protectionOfRea.put(0,baseRea0);
        protectionOfRea.put(0,baseRea1);

        List<OWLClass> efnBase = new ArrayList<>();
        efnBase.add(PDIS_R);
        efnBase.add(PDIS_R);
        efnBase.add(PGAS_R);
        efnBase.add(POIL_R);
        efnBase.add(PICE_R);
        efnBase.add(PTP_R);
        protectionOfEFN.put(0,efnBase);
        //////////////////////////////////////////////////////////////////////
        List<OWLClass> capBase = new ArrayList<>();
        capBase.add(PBNN);
        capBase.add(PBU);
        capBase.add(PDIF_BAT);
        capBase.add(PDU);
        capBase.add(PTM_BAT);
        capBase.add(PTP_BAT);
        capBase.add(PVP);
        protectionOfCAP.put(0,capBase);
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
        protectionOfCBR.put(0,cbrBase0);
        protectionOfCBR.put(1,cbrBase1);
        protectionOfCBR.put(2,cbrBase2);
        protectionOfCBR.put(3,cbrBase3);


        Set<OWLNamedIndividual> indLines = getIndividualByClass.getIndividualofClass(linesClass, reasoner);
        int variant = (int) (Math.random() * 2 + 1);
        int variant3 = (int) (Math.random() * 2);
        for (OWLNamedIndividual i : indLines) {
            Collection<OWLLiteral> setBase = getValuesFromProperty.getValues(i, ontology, base);
            Collection<OWLLiteral> setVar = getValuesFromProperty.getValues(i, ontology, setOf);
            for (OWLLiteral n : setBase) {
                int baseNumber = n.parseInteger();
                List<OWLClass> protectVar = protectionOfLines1.get(baseNumber);
                CreateComplectOfProtection.CreateComplect(i, ontology, ns, protectVar, df, manager);
            }
            if (!setVar.isEmpty()) {
                for (OWLLiteral n : setVar) {
                    int varNumber = n.parseInteger();
                    if (varNumber != 2) {
                        CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, varNumber);
                    } else {
                        CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, variant);
                    }
                }
            } else {
                CreateProtectionIndivid.CreateProtection(i, ontology, ns, protectionLines, df, manager, variant3);
            }
     }
        Set<OWLNamedIndividual> indTransform = getIndividualByClass.getIndividualofClass(yptrClass, reasoner);
        for (OWLNamedIndividual n: indTransform){
            CreateComplectOfProtection.CreateComplect(n,ontology,ns,generalT,df,manager);
            Collection<OWLLiteral> baseT = getValuesFromProperty.getValues(n,ontology,base);
            Collection<OWLLiteral> setOfT = getValuesFromProperty.getValues(n,ontology,setOf);
            for (OWLLiteral b: baseT){
                int baseTNumber = b.parseInteger();
                CreateComplectOfProtection.CreateComplect(n,ontology,ns,protectionOfTrans.get(baseTNumber),df,manager);
            }
             for (OWLLiteral s: setOfT){
                int setOfNumber = s.parseInteger();
                CreateComplectForSeveral.CreateComplect_2(n, setOfNumber,ontology,ns,protectionOfTrans1.get(setOfNumber),df,manager);

            }
        }
        Set<OWLNamedIndividual> indBus = getIndividualByClass.getIndividualofClass(busClass, reasoner);
        for (OWLNamedIndividual i: indBus){
            Collection<OWLLiteral>  baseB = getValuesFromProperty.getValues(i,ontology,base);
            if (baseB.isEmpty()){
                CreateComplectOfProtection.CreateComplect(i,ontology,ns,protectionOfBus.get(1),df,manager);

            }
             else{
                 for (OWLLiteral b: baseB){
                    int busNumber = b.parseInteger();
                    CreateComplectOfProtection.CreateComplect(i,ontology,ns,protectionOfBus.get(busNumber),df,manager);
                }
            }
        }

        Set<OWLNamedIndividual> indRea = getIndividualByClass.getIndividualofClass(reaClass, reasoner);
        for (OWLNamedIndividual j: indRea){
            Collection<OWLLiteral> baseRea = getValuesFromProperty.getValues(j,ontology,base);
            if (!baseRea.isEmpty()){
               for (OWLLiteral l: baseRea){
                   int reaNumber = l.parseInteger();
                           CreateComplectOfProtection.CreateComplect(j,ontology,ns,protectionOfRea.get(reaNumber),df,manager);
               }
            }
        }
        Set<OWLNamedIndividual> indEfn = getIndividualByClass.getIndividualofClass(efnClass, reasoner);
        for (OWLNamedIndividual i: indEfn){
            Collection<OWLLiteral> baseE = getValuesFromProperty.getValues(i,ontology,base);
            if (!baseE.isEmpty()){
                for (OWLLiteral l: baseE){
                    int efnNumber = l.parseInteger();
                    CreateComplectOfProtection.CreateComplect(i,ontology,ns,protectionOfEFN.get(efnNumber),df,manager);
                }
            }
        }

        Set<OWLNamedIndividual> indCAP = getIndividualByClass.getIndividualofClass(capClass, reasoner);
        for (OWLNamedIndividual i: indCAP){
            Collection<OWLLiteral> baseC = getValuesFromProperty.getValues(i,ontology,base);
            if (!baseC.isEmpty()){
                for (OWLLiteral l: baseC){
                    int capNumber = l.parseInteger();
                    CreateComplectOfProtection.CreateComplect(i,ontology,ns,protectionOfEFN.get(capNumber),df,manager);
                }
            }
        }

        Set<OWLNamedIndividual> indCBR = getIndividualByClass.getIndividualofClass(cbrClass,reasoner);
        for (OWLNamedIndividual j: indCBR){
            Collection<OWLLiteral> baseBr = getValuesFromProperty.getValues(j,ontology,base);
            if (!baseBr.isEmpty()){
                for (OWLLiteral l: baseBr){
                    int cbrNumber = l.parseInteger();
                    CreateComplectOfProtection.CreateComplect(j,ontology,ns,protectionOfCBR.get(cbrNumber),df,manager);
                }
            }
        }

        OutputStream out = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_pig_1.owl");
        manager.saveOntology(ontology, out);
    }

}
