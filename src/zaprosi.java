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
        OWLObjectProperty hasVoltage = df.getOWLObjectProperty(IRI.create(ns+"hasVoltageLevel"));

        OWLClass volt6 = df.getOWLClass(IRI.create(ns+"6"));
        OWLClass volt10 = df.getOWLClass(IRI.create(ns+"10"));
        OWLClass volt15 = df.getOWLClass(IRI.create(ns+"15"));
        OWLClass volt20 = df.getOWLClass(IRI.create(ns+"20"));
        OWLClass volt35 = df.getOWLClass(IRI.create(ns+"35"));
        OWLClass volt110 = df.getOWLClass(IRI.create(ns+"110"));
        OWLClass volt220 = df.getOWLClass(IRI.create(ns+"220"));
        OWLClass volt330 = df.getOWLClass(IRI.create(ns+"330"));
        OWLClass volt500 = df.getOWLClass(IRI.create(ns+"500"));
        OWLClass volt750 = df.getOWLClass(IRI.create(ns+"750"));
       // OWLDataProperty hasName = df.getOWLDataProperty(IRI.create(ns+"hasName"));

        OWLDataProperty hasChanel = df.getOWLDataProperty(IRI.create(ns+"hasChannel"));

        ///////////////linii/////////////////
        List<OWLClass> protectionLines1 = new ArrayList<>();
        Set<OWLClass> voltageLines1 = new HashSet<>();
        voltageLines1.add(volt750);
        voltageLines1.add(volt500);
        voltageLines1.add(volt330);
        protectionLines1.add(pdifLin);
        protectionLines1.add(pdifFLin);



        List<OWLClass> protectionLines = new ArrayList<>();
        Set<OWLClass> voltageLines = new HashSet<>();
        voltageLines.add(volt220);
        voltageLines.add(volt110);
        protectionLines.add(pdifLin);
        protectionLines.add(pdifFLin);
        protectionLines.add(nvchz);





        Set<OWLNamedIndividual> indLines = getIndividualByClass.getIndividualofClass(linesClass, reasoner);
        int variant = (int) (Math.random()*2);
        for (OWLNamedIndividual i: indLines){
            Collection<OWLIndividual> indVot = getIndividualFromProperty.getIndivid(i,ontology,hasVoltage);
            for (OWLIndividual j : indVot){
                Collection<OWLClassExpression> gg = EntitySearcher.getTypes(j, ontology);
                if (gg.contains(volt750) || gg.contains(volt500) || gg.contains(volt330) ){
                    Collection<OWLLiteral> channel = getValuesFromProperty.getValues(i,ontology, hasChanel);
                    System.out.println(channel+ "!!!!!!!!!!!!!!!");
                    if ( !channel.isEmpty()) {
                        for (OWLLiteral v : channel) {
                           // System.out.println("name is " + v.parseInteger());
                            if (v.parseInteger() == 0) {

                            } else if (v.parseInteger() == 1) {

                            }
                        }
                    }
                          else {
                              String[] linesName1 = i.toString().split("#");
                              String[] linesName = linesName1[1].split(">");
                              String h = protectionLines1.get(0).getIRI().getShortForm().toString();
                              String indName = linesName[0].concat(h);


                              OWLIndividual ind = df.getOWLNamedIndividual(IRI.create(ns+indName));
                              OWLAxiom protLineBase = df.getOWLClassAssertionAxiom(protectionLines1.get(variant), ind);
                              AddAxiom prot= new AddAxiom(ontology,protLineBase);
                              manager.applyChange(prot);

                          }
                      }
//                    if (getIndividualFromProperty.getIndivid(i, ontology, has))
//                    OWLAxiom classShin = df.getOWLClassAssertionAxiom(oshClass, indShin);
//                    AddAxiom shinAxiom = new AddAxiom(ontology,classShin);
//                    manager.applyChange(shinAxiom);



                else if (gg.contains(volt220) || gg.contains(volt110)){
                    System.out.println(protectionLines.size()+ "prot");

                }

                else{}
            }
        }




        OutputStream out = new FileOutputStream("C:\\Users\\anast\\Desktop\\ont_pig_1.owl");
        manager.saveOntology(ontology, out);
    }

}
