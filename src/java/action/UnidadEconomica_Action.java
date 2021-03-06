/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import beans.CatalogoBean;
import beans.ColoniasBean;
import beans.SectorSubsectorBean;
import beans.UnidadesEconomicasBean;
import beans.escuelaBean;
import beans.moduloAuxBean;
import beans.moduloBean;
import beans.usuarioBean;
import business.ConsultasBusiness;
import com.opensymphony.xwork2.ActionSupport;

import daos.UnidadesEconomicasDAOImpl;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.interceptor.SessionAware;
import utilidades.Constantes;

/**
 *
 * @author pedro
 */
public class UnidadEconomica_Action extends ActionSupport implements SessionAware {

    private usuarioBean usuariocons;
    private String cveusuario;
    private String pasusuario;
    private String nomModulo;
    private String modulo;
    private String nombreUsuario;
    private String tabSelect;

    private boolean bnUE = false;
    private boolean bnYaRegistrado = false;
    private boolean bnUENO = false;
    private boolean bnRegistro = false;
    private boolean FormSucPla = false;
    private boolean FormSel = false;
    private boolean UERegistrada = false;
    private boolean MuestraBotonAgregaRFC = false;
    private boolean MuestraBotonAgregaSuc = false;
    private boolean PlantelYaRegistrado=false;
    private boolean SucYaRegistrado=false;

    private boolean FormPlantel = false;
    private boolean FormSucursal = false;

    public List<moduloBean> modulosAUX = new ArrayList<moduloBean>();
    public List<moduloAuxBean> modulosAUXP = new ArrayList<moduloAuxBean>();

    private List<UnidadesEconomicasBean> ListaUE = new ArrayList<UnidadesEconomicasBean>();
    private List<UnidadesEconomicasBean> ListaUEIE = new ArrayList<UnidadesEconomicasBean>();
    private List<escuelaBean> ListaIdEs = new ArrayList<escuelaBean>();
    private List<ColoniasBean> ListaColonia = new ArrayList<ColoniasBean>();
    private List<ColoniasBean> ListaColonia_Pla = new ArrayList<ColoniasBean>();
    private List<ColoniasBean> ListaColonia_Suc = new ArrayList<ColoniasBean>();
    private List<CatalogoBean> ListaOpcion = new ArrayList<CatalogoBean>();
    private List<UnidadesEconomicasBean> ListaSucursales = new ArrayList<UnidadesEconomicasBean>();
    private List<SectorSubsectorBean> ListaSector=new ArrayList<SectorSubsectorBean>();
    private List<SectorSubsectorBean> ListaSubSector=new ArrayList<SectorSubsectorBean>();

    private String TipoError;
    private String TipoException;

    UnidadesEconomicasBean ue = new UnidadesEconomicasBean();
    escuelaBean escuela = new escuelaBean();
    //******************** PARA OBJETO DE NAVEGACIoN ***********************************************
    private Map session;

    public void setSession(Map session) {
        this.session = session;
    }

    public Map getSession() {
        return session;
    }

    public String IniReg() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            ConsultasBusiness con = new ConsultasBusiness();
            
           

            escuela.setCCT(usuariocons.getUSUARIO());

            ListaIdEs = con.programasIdEs(escuela);

            Iterator LPE = ListaIdEs.iterator();

            escuelaBean obj;

            while (LPE.hasNext()) {
                obj = (escuelaBean) LPE.next();

                ue.setID_ESC(obj.getID_ESCUELA());

            }

            Constantes.enviaMensajeConsola("ID ESC: " + ue.getID_ESC());

            UnidadesEconomicasDAOImpl con2 = new UnidadesEconomicasDAOImpl();
            
             

            ListaUEIE = con2.ConsultaUEIE(ue.getID_ESC());

            Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

            bnRegistro = false;
            UERegistrada = false;
            ue.setRFCAUX("");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String ConsultaRFC() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            Constantes.enviaMensajeConsola("rfc aux: " + ue.getRFCAUX());
            Constantes.enviaMensajeConsola("id esc: " + ue.getID_ESC());

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();
            
            ListaSector=con.ConsultaSector();
            ListaUE = con.ConsultaRFC(ue.getRFCAUX());
            Constantes.enviaMensajeConsola("LISTA UE: " + ListaUE.size());

            Iterator LUE = ListaUE.iterator();

            UnidadesEconomicasBean objg;

            while (LUE.hasNext()) {
                objg = (UnidadesEconomicasBean) LUE.next();

                ue.setID_UE(objg.getID_UE());
                ue.setRFC(objg.getRFC());
                ue.setRAZON_SOCIAL(objg.getRAZON_SOCIAL());
                ue.setNOMBRE_COMER(objg.getNOMBRE_COMER());
                ue.setGIRO(objg.getGIRO());
                ue.setSECTOR(objg.getSECTOR());
                ue.setDOMICILIO(objg.getDOMICILIO());
                ue.setCP(objg.getCP());
                ue.setCOLONIA(objg.getCOLONIA());
                ue.setLOCALIDAD(objg.getLOCALIDAD());
                ue.setMUNICIPIO(objg.getMUNICIPIO());

            }
            
            ListaSubSector=con.ConsultaSectorSub(ue.getSECTOR());

            if (ListaUE.size() > 0) {
                UERegistrada = true;
                ListaSucursales = con.ConsultaSucursales(ue);
                Constantes.enviaMensajeConsola("Lista Sucursales: " + ListaSucursales.size());

                if (ListaSucursales.size() > 0) {
                    MuestraBotonAgregaRFC = false;
                    MuestraBotonAgregaSuc = true;
                } else {
                    MuestraBotonAgregaRFC = true;
                    MuestraBotonAgregaSuc = true;
                }

            } else {
                UERegistrada = false;
                bnUENO = true;

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String MuestraForm() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {
            
            UnidadesEconomicasDAOImpl con=new UnidadesEconomicasDAOImpl();
            
            ListaSector=con.ConsultaSector();
            
            Constantes.enviaMensajeConsola("SECTOR"+ListaSector.size());
            
            ue.setRFCR(ue.getRFCAUX());
            ue.setRFCAUX("");

            bnRegistro = true;

            ue.setVALOR("SOLOUE");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }
    
    public String ConsultaSubsector() {
        try {
            //validando session***********************************************************************

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            ListaSubSector=con.ConsultaSectorSub(ue.getSECTORR());
            
            Constantes.enviaMensajeConsola("SUBSECTOR LISTA: "+ListaSubSector.size());

            

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String agregarUE() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            Constantes.enviaMensajeConsola("id_ue: " + ue.getID_UE());
            Constantes.enviaMensajeConsola("id esc: " + ue.getID_ESC());
            Constantes.enviaMensajeConsola("id suc: " + ue.getID_SUC());

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            String Valida = con.validaSoloUE(ue);

            if (Valida.length() == 0) {

                ue.setSTATUS_UE("0");
                ue.setSTATUS_EVALUACION("0");
                ue.setSTATUS_RESULTADO_EVALUACION("0");
                ue.setSTATUS_GENERAL("0");

                con.agregarUE(ue);

                ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());

                ue.setRFCAUX("");

                Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

                UERegistrada = false;

            } else {

                bnYaRegistrado = true;

            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }


    public String eliminarUE() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            Constantes.enviaMensajeConsola("id_ue_ie: " + ue.getID_IE_UE());

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            con.EliminarUE(ue);

            ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());

            Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            addActionError("Error al consultar: " + TipoException);

            return "ERROR";
        }

    }

    public String ConsultaCP() {
        try {
            //validando session***********************************************************************

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            bnRegistro = true;

            ListaColonia = con.ConsultaColonia(ue.getCPR());

            if (ListaColonia.size() > 0) {

                Iterator LC = ListaColonia.iterator();
                ColoniasBean objg;

                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();

                    ue.setMUNICIPIOR(objg.getMUNICIPIO());
                    ue.setCVE_MUNICIPIOR(objg.getID_MUNICIPIO());

                }

            } else {

                addFieldError("NoCP", "Codigo Postal No encontrado favor de verificar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String GuardaUE() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            Constantes.enviaMensajeConsola("id_ue: " + ue.getID_UE());
            Constantes.enviaMensajeConsola("id esc: " + ue.getID_ESC());
            Constantes.enviaMensajeConsola("rfc valido: " + ue.getRFCVALIDO());
            Constantes.enviaMensajeConsola("razon: " + ue.getRAZON_SOCIALR());
            Constantes.enviaMensajeConsola("giro: " + ue.getGIROR());
            Constantes.enviaMensajeConsola("sector: " + ue.getSECTORR());
            Constantes.enviaMensajeConsola("dom: " + ue.getDOMICILIOR());
            Constantes.enviaMensajeConsola("cp: " + ue.getCPR());
            Constantes.enviaMensajeConsola("localidad: " + ue.getLOCALIDADR());

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            if (ue.getVALOR().equals("SOLOUE")) {

                con.GuardaUE(ue);
                ue.setID_UE(con.consultaID_UE(ue.getRFCR()));

                ue.setSTATUS_UE("0");
                ue.setSTATUS_EVALUACION("0");
                ue.setSTATUS_RESULTADO_EVALUACION("0");
                ue.setSTATUS_GENERAL("0");
                ue.setID_SUC("");

                con.agregarUE(ue);
                ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());

                ue.setRFCAUX("");

                Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

                bnRegistro = false;

                return "SUCCESS";

            } else {

                con.GuardaUE(ue);
                ue.setID_UE(con.consultaID_UE(ue.getRFCR()));

                if (ue.getOPCION().equals("1")) {

                    Constantes.enviaMensajeConsola("entro a registrar plantel");

                    con.GuardaPla(ue);

                    ue.setID_SUC(con.consultaID_PLA(ue));

                    ue.setSTATUS_UE("0");
                    ue.setSTATUS_EVALUACION("0");
                    ue.setSTATUS_RESULTADO_EVALUACION("0");
                    ue.setSTATUS_GENERAL("0");

                    con.agregarUE(ue);

                    ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());

                    ue.setRFCAUX("");

                    Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

                    bnRegistro = false;

                } else {
                    Constantes.enviaMensajeConsola("entro a registrar sucursal");

                    ue.setCCT_PLA("NO APLICA");

                    con.GuardaSuc(ue);

                    ue.setSTATUS_UE("0");
                    ue.setSTATUS_EVALUACION("0");
                    ue.setSTATUS_RESULTADO_EVALUACION("0");
                    ue.setSTATUS_GENERAL("0");

                    ue.setID_SUC(con.consultaID_SUC(ue));

                    con.agregarUE(ue);

                    ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());

                    ue.setRFCAUX("");

                    Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

                    bnRegistro = false;
                }

                return "SUCCESS";

            }

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String GuardaPlantel() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            Constantes.enviaMensajeConsola("entro a registrar plantel");

            String valida = null;

            valida = con.validaPlantel(ue);

            if (valida.length() == 0) {
                
                con.GuardaPla(ue);

                ue.setID_SUC(con.consultaID_PLA(ue));

                ue.setSTATUS_UE("0");
                ue.setSTATUS_EVALUACION("0");
                ue.setSTATUS_RESULTADO_EVALUACION("0");
                ue.setSTATUS_GENERAL("0");

                con.agregarUE(ue);

                ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());
                Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

                ListaSucursales = con.ConsultaSucursales(ue);
                Constantes.enviaMensajeConsola("Lista Sucursales: " + ListaSucursales.size());

                ue.setRFCAUX("");

                UERegistrada = false;

            } else {
                PlantelYaRegistrado=true;
            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String GuardaSucursal() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            Constantes.enviaMensajeConsola("entro a registrar sucursal");
            
            String valida = null;

            valida = con.validaSuc(ue);

            if (valida.length() == 0) {
                
            

            ue.setCCT_PLA("NO APLICA");

            con.GuardaSuc(ue);

            ue.setSTATUS_UE("0");
            ue.setSTATUS_EVALUACION("0");
            ue.setSTATUS_RESULTADO_EVALUACION("0");
            ue.setSTATUS_GENERAL("0");

            ue.setID_SUC(con.consultaID_SUC(ue));

            con.agregarUE(ue);

            ListaUEIE = con.ConsultaUEIE(ue.getID_ESC());
            Constantes.enviaMensajeConsola("lista UEIE: " + ListaUEIE.size());

            ListaSucursales = con.ConsultaSucursales(ue);
            Constantes.enviaMensajeConsola("Lista Sucursales: " + ListaSucursales.size());

            ue.setRFCAUX("");

            UERegistrada = false;
            }else{
                SucYaRegistrado=true;
            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String muestraFormSucPlan() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            FormSel = true;

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            ListaOpcion = con.ConsultaCatalogo("100");

            ue.setVALOR("SIADD");

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String MuestraFormSelc() {

        //validando session***********************************************************************
        if (session.get("cveUsuario") != null) {
            String sUsu = (String) session.get("cveUsuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }
        if (session.containsKey("usuario")) {
            usuariocons = (usuarioBean) session.get("usuario");
        } else {
            addActionError("**** La sesión ha expirado *** favor de iniciar una nueva sesion *** ");
            return "SESSION";
        }

        try {

            //Constantes.enviaMensajeConsola("opcion seleccionada: "+ue.getOPCION());
            if (ue.getOPCION().equals("1")) {

                FormPlantel = true;
                FormSucursal = false;
            } else if (ue.getOPCION().equals("2")) {
                FormSucursal = true;
                FormPlantel = false;
            } else {
                ue.setVALOR("SOLOUE");

                FormSel = false;
                FormSucursal = false;
                FormPlantel = false;
                MuestraBotonAgregaRFC = true;
            }

            return "SUCCESS";

        } catch (Exception e) {

            TipoException = e.getMessage();
            return "ERROR";
        }

    }

    public String ConsultaCP_PLA() {
        try {
            //validando session***********************************************************************

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            ListaColonia_Pla = con.ConsultaColonia(ue.getCP_PLA());

            if (ListaColonia_Pla.size() > 0) {

                Iterator LC = ListaColonia_Pla.iterator();
                ColoniasBean objg;

                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();

                    ue.setMUN_PLA(objg.getMUNICIPIO());
                    ue.setCVE_MUNICIPIO_PLA(objg.getID_MUNICIPIO());

                }

            } else {

                addFieldError("NoCP_PLA", "Codigo Postal No encontrado favor de verificar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public String ConsultaCP_SUC() {
        try {
            //validando session***********************************************************************

            UnidadesEconomicasDAOImpl con = new UnidadesEconomicasDAOImpl();

            ListaColonia_Suc = con.ConsultaColonia(ue.getCP_SUC());

            if (ListaColonia_Suc.size() > 0) {

                Iterator LC = ListaColonia_Suc.iterator();
                ColoniasBean objg;

                while (LC.hasNext()) {
                    objg = (ColoniasBean) LC.next();

                    ue.setMUN_SUC(objg.getMUNICIPIO());
                    ue.setCVE_MUNICIPIO_SUC(objg.getID_MUNICIPIO());

                }

            } else {

                addFieldError("NoCP_SUC", "Codigo Postal No encontrado favor de verificar");
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Ocurrio un error: " + e);
            return "ERROR";
        }
        return "SUCCESS";
    }

    public usuarioBean getUsuariocons() {
        return usuariocons;
    }

    public void setUsuariocons(usuarioBean usuariocons) {
        this.usuariocons = usuariocons;
    }

    public String getCveusuario() {
        return cveusuario;
    }

    public void setCveusuario(String cveusuario) {
        this.cveusuario = cveusuario;
    }

    public String getPasusuario() {
        return pasusuario;
    }

    public void setPasusuario(String pasusuario) {
        this.pasusuario = pasusuario;
    }

    public String getNomModulo() {
        return nomModulo;
    }

    public void setNomModulo(String nomModulo) {
        this.nomModulo = nomModulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTabSelect() {
        return tabSelect;
    }

    public void setTabSelect(String tabSelect) {
        this.tabSelect = tabSelect;
    }

    public boolean isBnUE() {
        return bnUE;
    }

    public void setBnUE(boolean bnUE) {
        this.bnUE = bnUE;
    }

    public boolean isBnYaRegistrado() {
        return bnYaRegistrado;
    }

    public void setBnYaRegistrado(boolean bnYaRegistrado) {
        this.bnYaRegistrado = bnYaRegistrado;
    }

    public boolean isBnUENO() {
        return bnUENO;
    }

    public void setBnUENO(boolean bnUENO) {
        this.bnUENO = bnUENO;
    }

    public boolean isMuestraBotonAgregaRFC() {
        return MuestraBotonAgregaRFC;
    }

    public void setMuestraBotonAgregaRFC(boolean MuestraBotonAgregaRFC) {
        this.MuestraBotonAgregaRFC = MuestraBotonAgregaRFC;
    }

    public boolean isMuestraBotonAgregaSuc() {
        return MuestraBotonAgregaSuc;
    }

    public void setMuestraBotonAgregaSuc(boolean MuestraBotonAgregaSuc) {
        this.MuestraBotonAgregaSuc = MuestraBotonAgregaSuc;
    }

    public boolean isPlantelYaRegistrado() {
        return PlantelYaRegistrado;
    }

    public void setPlantelYaRegistrado(boolean PlantelYaRegistrado) {
        this.PlantelYaRegistrado = PlantelYaRegistrado;
    }

    public boolean isSucYaRegistrado() {
        return SucYaRegistrado;
    }

    public void setSucYaRegistrado(boolean SucYaRegistrado) {
        this.SucYaRegistrado = SucYaRegistrado;
    }
    
    

    public boolean isFormSucPla() {
        return FormSucPla;
    }

    public void setFormSucPla(boolean FormSucPla) {
        this.FormSucPla = FormSucPla;
    }

    public boolean isFormSel() {
        return FormSel;
    }

    public void setFormSel(boolean FormSel) {
        this.FormSel = FormSel;
    }

    public boolean isUERegistrada() {
        return UERegistrada;
    }

    public void setUERegistrada(boolean UERegistrada) {
        this.UERegistrada = UERegistrada;
    }

    public boolean isFormPlantel() {
        return FormPlantel;
    }

    public void setFormPlantel(boolean FormPlantel) {
        this.FormPlantel = FormPlantel;
    }

    public boolean isFormSucursal() {
        return FormSucursal;
    }

    public void setFormSucursal(boolean FormSucursal) {
        this.FormSucursal = FormSucursal;
    }

    public boolean isBnRegistro() {
        return bnRegistro;
    }

    public void setBnRegistro(boolean bnRegistro) {
        this.bnRegistro = bnRegistro;
    }

    public List<moduloBean> getModulosAUX() {
        return modulosAUX;
    }

    public void setModulosAUX(List<moduloBean> modulosAUX) {
        this.modulosAUX = modulosAUX;
    }

    public List<moduloAuxBean> getModulosAUXP() {
        return modulosAUXP;
    }

    public void setModulosAUXP(List<moduloAuxBean> modulosAUXP) {
        this.modulosAUXP = modulosAUXP;
    }

    public List<UnidadesEconomicasBean> getListaUE() {
        return ListaUE;
    }

    public void setListaUE(List<UnidadesEconomicasBean> ListaUE) {
        this.ListaUE = ListaUE;
    }

    public List<UnidadesEconomicasBean> getListaUEIE() {
        return ListaUEIE;
    }

    public void setListaUEIE(List<UnidadesEconomicasBean> ListaUEIE) {
        this.ListaUEIE = ListaUEIE;
    }

    public List<escuelaBean> getListaIdEs() {
        return ListaIdEs;
    }

    public void setListaIdEs(List<escuelaBean> ListaIdEs) {
        this.ListaIdEs = ListaIdEs;
    }

    public List<ColoniasBean> getListaColonia() {
        return ListaColonia;
    }

    public void setListaColonia(List<ColoniasBean> ListaColonia) {
        this.ListaColonia = ListaColonia;
    }

    public List<ColoniasBean> getListaColonia_Pla() {
        return ListaColonia_Pla;
    }

    public void setListaColonia_Pla(List<ColoniasBean> ListaColonia_Pla) {
        this.ListaColonia_Pla = ListaColonia_Pla;
    }

    public List<ColoniasBean> getListaColonia_Suc() {
        return ListaColonia_Suc;
    }

    public void setListaColonia_Suc(List<ColoniasBean> ListaColonia_Suc) {
        this.ListaColonia_Suc = ListaColonia_Suc;
    }

    public List<CatalogoBean> getListaOpcion() {
        return ListaOpcion;
    }

    public void setListaOpcion(List<CatalogoBean> ListaOpcion) {
        this.ListaOpcion = ListaOpcion;
    }

    public List<UnidadesEconomicasBean> getListaSucursales() {
        return ListaSucursales;
    }

    public void setListaSucursales(List<UnidadesEconomicasBean> ListaSucursales) {
        this.ListaSucursales = ListaSucursales;
    }

    public List<SectorSubsectorBean> getListaSector() {
        return ListaSector;
    }

    public void setListaSector(List<SectorSubsectorBean> ListaSector) {
        this.ListaSector = ListaSector;
    }

    public List<SectorSubsectorBean> getListaSubSector() {
        return ListaSubSector;
    }

    public void setListaSubSector(List<SectorSubsectorBean> ListaSubSector) {
        this.ListaSubSector = ListaSubSector;
    }
    
    

    public escuelaBean getEscuela() {
        return escuela;
    }

    public void setEscuela(escuelaBean escuela) {
        this.escuela = escuela;
    }

    public String getTipoError() {
        return TipoError;
    }

    public void setTipoError(String TipoError) {
        this.TipoError = TipoError;
    }

    public String getTipoException() {
        return TipoException;
    }

    public void setTipoException(String TipoException) {
        this.TipoException = TipoException;
    }

    public UnidadesEconomicasBean getUe() {
        return ue;
    }

    public void setUe(UnidadesEconomicasBean ue) {
        this.ue = ue;
    }

}
