import org.nfunk.jep.function.Add;
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
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        // File file = new File("C:\\Users\\anast\\Desktop\\ont_PS2_pig.owl");
        File file = new File("C:\\Users\\anast\\Desktop\\ont_PS3_pig.owl");
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
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
                    String oshinName = k + "";
                    System.out.println("has oshin");
                    OWLIndividual indShin = df.getOWLNamedIndividual(IRI.create(ns + oshinName));
                    OWLAxiom classShin = df.getOWLClassAssertionAxiom(oshClass, indShin);
                    AddAxiom shinAxiom = new AddAxiom(ontology, classShin);
                    manager.applyChange(shinAxiom);
                    OWLAxiom cnAxiom = df.getOWLObjectPropertyAssertionAxiom(hasCN, indShin, j);
                    OWLAxiom transAxiom = df.getOWLObjectPropertyAssertionAxiom(hasBus, trans, indShin);
                    OWLAxiom voltAxiom = df.getOWLObjectPropertyAssertionAxiom(hasVoltageLevel, indShin, volt);
                    AddAxiom cnChange = new AddAxiom(ontology, cnAxiom);
                    manager.applyChange(cnChange);
                    cnChange = new AddAxiom(ontology, transAxiom);
                    manager.applyChange(cnChange);
                    cnChange = new AddAxiom(ontology, voltAxiom);
                    manager.applyChange(cnChange);
                    for (OWLIndividual n : nodes) {
                        cnAxiom = df.getOWLObjectPropertyAssertionAxiom(hasCN, indShin, n);
                        cnChange = new AddAxiom(ontology, cnAxiom);
                        manager.applyChange(cnChange);
                    }

                }
            }

        }

        SWRLRuleEngine ruleEngine = SWRLAPIFactory.createSWRLRuleEngine(ontology);
        SWRLAPIRule rule1 = ruleEngine.createSWRLRule("oshinCN2","YPTR(?x) ^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v,2) ^ hasVoltage(?v,?vv) ^ swrlb:greaterThanOrEqual(?vv, 330) -> setOfProtection(?x, 3)");
        SWRLAPIRule rule2 = ruleEngine.createSWRLRule("oshinCN","YPTR(?x) ^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 2) -> setOfProtection(?x, 1)");
        SWRLAPIRule rule3 = ruleEngine.createSWRLRule("oshinBN","AutoTransformers(?x) ^ hasShortBus(?x,?b) ^ hasVoltageLevel(?b,?v) ^ voltageType(?v, 1) ^ hasVoltage(?v,220) -> setOfProtection(?x, 4)");
        SWRLAPIRule rule4 = ruleEngine.createSWRLRule("oshinNN","YPTR(?x) ^ hasReactors(?x,?b) -> setOfProtection(?x, 2)");
        SWRLAPIRule rule5 = ruleEngine.createSWRLRule("RPN","YPTR(?x) ^ hasParts(?x,?p) ^ YLTC(?p) -> setOfProtection(?x, 5)");
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
        ///////////////linii/////////////////

        Map<Integer, List<OWLClass>> protectionOfLines1 = new HashMap<>();
        Map<Integer,List<OWLClass>> protectionOfTrans = new HashMap<>();

//        List<OWLClass> protectionLines0_1 = new ArrayList<>();
//        protectionLines0_1.add(pdifLin);
//        protectionLines0_1.add(rs);
//        protectionLines0_1.add(pdis);
//        protectionLines0_1.add(pntcn);
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
        base6.add(PDIF_O);
        base6.add(PDIF_O);

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

        //            Collection<OWLIndividual> indVot = getIndividualFromProperty.getIndivid(i,ontology,hasVoltage);
//            Collection<OWLLiteral> channel = getValuesFromProperty.getValues(i,ontology, hasChanel);
//            for (OWLIndividual j : indVot){
//                Collection<OWLClassExpression> gg = EntitySearcher.getTypes(j, ontology);
//                if (gg.contains(volt750) || gg.contains(volt500) || gg.contains(volt330) ){
//                  CreateComplectOfProtection.CreateComplect(i,ontology,ns,kczClass,df,manager);
//                    if ( !channel.isEmpty()) {
//                        for (OWLLiteral v : channel) {
//                            if (v.parseInteger() == 0) {
//                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines1,df,manager,0);
//                                break;
//                            } else if (v.parseInteger() == 1) {
//                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines1,df,manager,1);
//                                break;
//                            }
//                        }
//                    } else {
//                              CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines1,df,manager,variant);
//                          }
//                      }
//
//
//                else if (gg.contains(volt220) || gg.contains(volt110)){
//                    if ( !channel.isEmpty()) {
//                        for (OWLLiteral v : channel) {
//                            if (v.parseInteger() == 0) {
//                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines,df,manager,0);
//                                break;
//                            } else if (v.parseInteger() == 1) {
//                                int variant2 = (int) (Math.random()*2+1);
//                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines,df,manager,variant2);
//                                break;
//                            }
//                        }
//                    } else {
//                        CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines,df,manager,variant3);
//                    }
//
//
//
//                }
//
//                else{}
//            }

        }
        Set<OWLNamedIndividual> indTransform = getIndividualByClass.getIndividualofClass(yptrClass, reasoner);
        for (OWLNamedIndividual n: indTransform){
            Collection<OWLLiteral> baseT = getValuesFromProperty.getValues(n,ontology,base);
            for (OWLLiteral b: baseT){
                int baseTNumber = b.parseInteger();
                CreateComplectOfProtection.CreateComplect(n,ontology,ns,protectionOfTrans.get(baseTNumber),df,manager);
            }
        }


        OutputStream out = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_pig_1.owl");
        manager.saveOntology(ontology, out);
    }

}
