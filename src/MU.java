import jdk.internal.dynalink.linker.LinkerServices;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.OWLReasoner;

import java.util.*;

public class MU {

    public String name;

    public List<OWLNamedIndividual> listTT;
    public List<OWLNamedIndividual> listTV;
    public List<OWLNamedIndividual> listCBR;
    public List<OWLNamedIndividual> listSWI;

    public List<OWLNamedIndividual> getListTT() {
        return listTT;
    }

    public void setListTT(List<OWLNamedIndividual> listTT) {
        this.listTT = listTT;
    }

    public List<OWLNamedIndividual> getListTV() {
        return listTV;
    }

    public void setListTV(List<OWLNamedIndividual> listTV) {
        this.listTV = listTV;
    }

    public List<OWLNamedIndividual> getListCBR() {
        return listCBR;
    }

    public void setListCBR(List<OWLNamedIndividual> listCBR) {
        this.listCBR = listCBR;
    }

    public List<OWLNamedIndividual> getListSWI() {
        return listSWI;
    }

    public void setListSWI(List<OWLNamedIndividual> listSWI) {
        this.listSWI = listSWI;
    }

    public String getName() {
        return name;
    }

    public MU(String name){
        this.name =  name;
    }

    public static void getSignalsForRu(OWLOntology ont, OWLOntologyManager manager, OWLDataFactory df, String ns, OWLReasoner reasoner) {
        Map<OWLIndividual, List<MU>>  muMap = new HashMap<>();
        Set<OWLNamedIndividual> tctrSet = getIndividualByClass.getIndividualofClass(df.getOWLClass(IRI.create(ns+"TCTR")), reasoner);
        Set<OWLNamedIndividual> tvtrSet = getIndividualByClass.getIndividualofClass(df.getOWLClass(IRI.create(ns+"TVTR")), reasoner);
        Set<OWLNamedIndividual> cbrSet = getIndividualByClass.getIndividualofClass(df.getOWLClass(IRI.create(ns+"XCBR")), reasoner);
        Set<OWLNamedIndividual> swiSet = getIndividualByClass.getIndividualofClass(df.getOWLClass(IRI.create(ns+"XSWI")), reasoner);
        Set<OWLNamedIndividual> voltageSet = getIndividualByClass.getIndividualofClass(df.getOWLClass(IRI.create(ns+"VoltageLevel")), reasoner);
        int l=1;
        for (OWLNamedIndividual i: voltageSet){
            List<OWLNamedIndividual> tctrList = new ArrayList<>();
            List<OWLNamedIndividual> tvtrList = new ArrayList<>();
            List<OWLNamedIndividual> cbrList = new ArrayList<>();
            List<OWLNamedIndividual> swiList = new ArrayList<>();
            for (OWLNamedIndividual tc: tctrSet){
              if (getIndividualFromProperty.getIndivid(tc,ont,df.getOWLObjectProperty(IRI.create(ns+"hasVoltageLevel"))).contains(i)){
                  tctrList.add(tc);
              }
            }
            for (OWLNamedIndividual tv: tvtrSet){
                if (getIndividualFromProperty.getIndivid(tv,ont,df.getOWLObjectProperty(IRI.create(ns+"hasVoltageLevel"))).contains(i)){
                    tvtrList.add(tv);
                }
            }
            for (OWLNamedIndividual cb: cbrSet){
                if (getIndividualFromProperty.getIndivid(cb,ont,df.getOWLObjectProperty(IRI.create(ns+"hasVoltageLevel"))).contains(i)){
                    cbrList.add(cb);
                }
            }
            for (OWLNamedIndividual s: swiSet){
                if (getIndividualFromProperty.getIndivid(s,ont,df.getOWLObjectProperty(IRI.create(ns+"hasVoltageLevel"))).contains(i)){
                    swiList.add(s);
                }
            }
            createMuInOntology(ont,ns,df,manager, createMuInstance(tctrList,tvtrList,cbrList,swiList,i));
        }
    }




    private static List<MU> createMuInstance (List<OWLNamedIndividual> listTT, List<OWLNamedIndividual> listTV, List<OWLNamedIndividual> listCBR, List<OWLNamedIndividual> listSWI, OWLNamedIndividual volt){

        List<MU> muInctanceList = new ArrayList<>();
        int l = 1;
        int k=0;
        while (listTT.size()>=1 || listTV.size()>=1 || listSWI.size()>=1 || listCBR.size()>=1 ) {
            System.out.println(listSWI.size() +" " + listTV.size()+" "+listTT.size()+" "+listCBR.size());
            MU mergingUnit = new MU("MergingUnit_"+volt.toString().split("#")[1].split(">")[0]+"_"+l);
            List<OWLNamedIndividual> bufferList = new ArrayList<>();
            List<OWLNamedIndividual> bufferList1 = new ArrayList<>();
            List<OWLNamedIndividual> bufferList2 = new ArrayList<>();
            List<OWLNamedIndividual> bufferList3 = new ArrayList<>();
            l++;
            if (listTT.size()>=1) {
               bufferList.add(listTT.get(0));
               mergingUnit.setListTT(bufferList);
               listTT.remove(0);
            } else{
                mergingUnit.setListTT(listTT);
            }
            if (listTV.size()>=1) {
                bufferList1.add(listTV.get(0));
                mergingUnit.setListTV(bufferList1);
                listTV.remove(0);
            } else{
                mergingUnit.setListTV(listTV);
            }

            if (listSWI.size()>=1) {

                bufferList2.add(listSWI.get(0));
                mergingUnit.setListSWI(bufferList2);
                listSWI.remove(0);

            } else{
                mergingUnit.setListSWI(listSWI);
            }
            if (listCBR.size()>=1) {
                bufferList3.add(listCBR.get(0));
                k++;
                mergingUnit.setListCBR(bufferList3);
                if (k==2){
                    listCBR.remove(0);
                    k=0;
                }
            } else{
                mergingUnit.setListCBR(listCBR);
            }

            muInctanceList.add(mergingUnit);
        }
        return muInctanceList;
    }

    private static void createMuInOntology (OWLOntology ont, String ns, OWLDataFactory df, OWLOntologyManager manager, List<MU> unitList) {
        OWLClass mergingUnits = df.getOWLClass(IRI.create(ns+"MergingUnits"));
        OWLObjectProperty haveSignals = df.getOWLObjectProperty(IRI.create(ns+"haveSignals"));
        for (MU m :unitList){
            OWLIndividual indMu= df.getOWLNamedIndividual(IRI.create(ns + m.getName()));
            AxiomsAdding.AddingClass(ont,manager,df,indMu,mergingUnits);
            AxiomsAdding.addingSeveralForNamed(ont,manager,df,indMu,m.getListTT(),haveSignals);
            AxiomsAdding.addingSeveralForNamed(ont,manager,df,indMu,m.getListTV(),haveSignals);
            AxiomsAdding.addingSeveralForNamed(ont,manager,df,indMu,m.getListCBR(),haveSignals);
            AxiomsAdding.addingSeveralForNamed(ont,manager,df,indMu,m.getListSWI(),haveSignals);
        }
    }
}
