import org.nfunk.jep.function.Add;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;
import org.semanticweb.owlapi.search.EntitySearcher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;

public class zaprosi {


    public static void main (String[] args) throws OWLOntologyCreationException, FileNotFoundException, OWLOntologyStorageException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        File file = new File("C:\\Users\\anast\\Desktop\\ont_PS2_pig.owl");
        OWLOntology ontology = manager.loadOntologyFromOntologyDocument(file);
        String ns = "http://www.semanticweb.org/anast/ontologies/2019/3/untitled-ontology-22#";
        OWLIndividual trans = null;
        Set<OWLAxiom> axiom = new HashSet<>();
        OWLDataFactory df = manager.getOWLDataFactory();
        OWLObjectProperty hasCN = df.getOWLObjectProperty(IRI.create(ns+"hasCN"));
        OWLObjectProperty ptwOf = df.getOWLObjectProperty(IRI.create(ns+"ptwOf"));
        OWLObjectProperty hasBus = df.getOWLObjectProperty(IRI.create(ns+"hasShortBus"));
        OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
        OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
        String ptwClass = "PTW";
        OWLClass PTW = df.getOWLClass(IRI.create(ns+"PTW"));
        OWLClass oshClass = df.getOWLClass(IRI.create(ns+"ShortBus"));
        Set<OWLNamedIndividual> indPTW = getIndividualByClass.getIndividualofClass(PTW, reasoner);
        System.out.println("The Individuals of class : ");
        int k=0;
        for (OWLNamedIndividual i: indPTW){
            System.out.println(i.getIRI().getFragment());
            System.out.println(i);
            OWLIndividual bI = null;
            Collection<OWLIndividual> indCN = getIndividualFromProperty.getIndivid(i,ontology, hasCN);
           // Collection<OWLIndividual> trans = (Set<OWLIndividual>) getIndividualFromProperty.getIndivid(i, ontology, ptwOf);
            for (OWLIndividual t :  getIndividualFromProperty.getIndivid(i, ontology, ptwOf)){
                trans = t;
            }
            for(OWLIndividual j: indCN){
                Collection<OWLIndividual> nodes = new HashSet<>();

                boolean start = false;
              lookForBreacker.get2(j,ontology,ns,df, bI, nodes, start);
              if (nodes.size()>=2){
                  k++;
                  String oshinName = k+"";
                  System.out.println("has oshin");
                  OWLIndividual indShin = df.getOWLNamedIndividual(IRI.create(ns+oshinName));
                  OWLAxiom classShin = df.getOWLClassAssertionAxiom(oshClass, indShin);
                  AddAxiom shinAxiom = new AddAxiom(ontology,classShin);
                  manager.applyChange(shinAxiom);
                  OWLAxiom cnAxiom = df.getOWLObjectPropertyAssertionAxiom(hasCN,indShin,j);
                  OWLAxiom transAxiom = df.getOWLObjectPropertyAssertionAxiom(hasBus,trans, indShin);
                  AddAxiom cnChange = new AddAxiom(ontology,cnAxiom);
                  manager.applyChange(cnChange);
                  cnChange = new AddAxiom(ontology, transAxiom);
                  manager.applyChange(cnChange);
                  for (OWLIndividual n: nodes){
                      cnAxiom = df.getOWLObjectPropertyAssertionAxiom(hasCN,indShin,n);
                      cnChange = new AddAxiom(ontology,cnAxiom);
                      manager.applyChange(cnChange);
                  }

              }
            }

        }

/////////////////////проверка на защиты////////////////////
        OWLClass linesClass = df.getOWLClass(IRI.create(ns+"Lines"));
        OWLClass pdifLin = df.getOWLClass(IRI.create(ns+"PDIF_L"));
        OWLClass pdifFLin = df.getOWLClass(IRI.create(ns+"PDIF_F"));
        OWLClass rs = df.getOWLClass(IRI.create(ns+"PS"));
        OWLClass nvchz = df.getOWLClass(IRI.create(ns+"NVCHZ"));
        OWLClass pdis = df.getOWLClass(IRI.create(ns+"PDIS"));
        OWLClass pntcn = df.getOWLClass(IRI.create(ns+"PNTCN"));
        OWLObjectProperty hasVoltage = df.getOWLObjectProperty(IRI.create(ns+"hasVoltageLevel"));

        OWLClass volt110 = df.getOWLClass(IRI.create(ns+"110"));
        OWLClass volt220 = df.getOWLClass(IRI.create(ns+"220"));
        OWLClass volt330 = df.getOWLClass(IRI.create(ns+"330"));
        OWLClass volt500 = df.getOWLClass(IRI.create(ns+"500"));
        OWLClass volt750 = df.getOWLClass(IRI.create(ns+"750"));
       // OWLDataProperty hasName = df.getOWLDataProperty(IRI.create(ns+"hasName"));

        OWLDataProperty hasChanel = df.getOWLDataProperty(IRI.create(ns+"hasChannel"));

        ///////////////linii/////////////////
        List<OWLClass> protectionLines1 = new ArrayList<>();
        protectionLines1.add(pdifLin);
        protectionLines1.add(pdifFLin);

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

        List<OWLClass> protectionLines3 = new ArrayList<>();
        protectionLines.add(pdifLin);
        protectionLines.add(pdifFLin);
        protectionLines.add(nvchz);





        Set<OWLNamedIndividual> indLines = getIndividualByClass.getIndividualofClass(linesClass, reasoner);
        int variant = (int) (Math.random()*2);
        int variant3 = (int) (Math.random()*3);
        for (OWLNamedIndividual i: indLines){
            Collection<OWLIndividual> indVot = getIndividualFromProperty.getIndivid(i,ontology,hasVoltage);
            Collection<OWLLiteral> channel = getValuesFromProperty.getValues(i,ontology, hasChanel);
            for (OWLIndividual j : indVot){
                Collection<OWLClassExpression> gg = EntitySearcher.getTypes(j, ontology);
                if (gg.contains(volt750) || gg.contains(volt500) || gg.contains(volt330) ){
                  CreateComplectOfProtection.CreateComplect(i,ontology,ns,kczClass,df,manager);
                    if ( !channel.isEmpty()) {
                        for (OWLLiteral v : channel) {
                            if (v.parseInteger() == 0) {
                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines1,df,manager,0);
                                break;
                            } else if (v.parseInteger() == 1) {
                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines1,df,manager,1);
                                break;
                            }
                        }
                    } else {
                              CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines1,df,manager,variant);
                          }
                      }


                else if (gg.contains(volt220) || gg.contains(volt110)){
                    if ( !channel.isEmpty()) {
                        for (OWLLiteral v : channel) {
                            if (v.parseInteger() == 0) {
                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines,df,manager,0);
                                break;
                            } else if (v.parseInteger() == 1) {
                                int variant2 = (int) (Math.random()*2+1);
                                CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines,df,manager,variant2);
                                break;
                            }
                        }
                    } else {
                        CreateProtectionIndivid.CreateProtection(i,ontology,ns,protectionLines,df,manager,variant3);
                    }



                }

                else{}
            }
        }




        OutputStream out = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_pig_1.owl");
        manager.saveOntology(ontology, out);
    }

}
