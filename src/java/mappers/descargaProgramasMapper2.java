/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.programaEsBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class descargaProgramasMapper2 implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         programaEsBean dat = new programaEsBean();
        
        
       
        
        
           if (rs.getString("NOM_CARRERA") != null) {
            dat.setNOM_CARRERA(rs.getString("NOM_CARRERA").trim());
        } else {
            dat.setNOM_CARRERA(rs.getString("NOM_CARRERA"));
        }
             if (rs.getString("CVE_PLAN_EST") != null) {
            dat.setCVE_PLAN_EST(rs.getString("CVE_PLAN_EST").trim());
        } else {
            dat.setCVE_PLAN_EST(rs.getString("CVE_PLAN_EST"));
        }
        
        if (rs.getString("ENFASIS") != null) {
            dat.setENFASIS(rs.getString("ENFASIS").trim());
        } else {
            dat.setENFASIS(rs.getString("ENFASIS"));
        }
        
          if  (rs.getString("VERSION") != null) {
            dat.setVERSION(rs.getString("VERSION").trim());
        } else {
            dat.setVERSION(rs.getString("VERSION"));
        }    
          
        if  (rs.getString("NOMBRE_MATERIA") != null) {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA").trim());
        } else {
            dat.setNOMBRE_MATERIA(rs.getString("NOMBRE_MATERIA"));
        }    
          
          
          
           if  (rs.getString("NUMERO_PERIODO") != null) {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO").trim());
        } else {
            dat.setNUMERO_PERIODO(rs.getString("NUMERO_PERIODO"));
        }    
           
             if  (rs.getString("COMPETENCIA") != null) {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA").trim());
        } else {
            dat.setCOMPETENCIA(rs.getString("COMPETENCIA"));
        }    
            
            if  (rs.getString("ACTIVIDAD") != null) {
            dat.setACTIVIDAD(rs.getString("ACTIVIDAD").trim());
        } else {
            dat.setACTIVIDAD(rs.getString("ACTIVIDAD"));
        }    
       
              if  (rs.getString("ID_CCT_PLAN") != null) {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN").trim());
        } else {
            dat.setID_CCT_PLAN(rs.getString("ID_CCT_PLAN"));
            
            
        }    
              
              
              if  (rs.getString("ID_MATERIA") != null) {
            dat.setID_MATERIA(rs.getString("ID_MATERIA").trim());
        } else {
            dat.setID_MATERIA(rs.getString("ID_MATERIA"));
            
            
        }      
              
                if  (rs.getString("ID_COMPETENCIA") != null) {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA").trim());
        } else {
            dat.setID_COMPETENCIA(rs.getString("ID_COMPETENCIA"));
            
            
        }        
          
                 if  (rs.getString("ID_ACTIVIDAD") != null) {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD").trim());
        } else {
            dat.setID_ACTIVIDAD(rs.getString("ID_ACTIVIDAD"));
            
            
        }   
                 
                  if  (rs.getString("ACTIVIDAD") != null) {
            dat.setDES_ACTIVIDAD(rs.getString("ACTIVIDAD").trim());
        } else {
            dat.setDES_ACTIVIDAD(rs.getString("ACTIVIDAD"));
            
            
        }        
                  
                  if  (rs.getString("VALIDAR") != null) {
            dat.setVALIDAR(rs.getString("VALIDAR").trim());
        } else {
            dat.setVALIDAR(rs.getString("VALIDAR"));
            
            
        }    
                   if  (rs.getString("NO_PASA") != null) {
            dat.setNO_PASA(rs.getString("NO_PASA").trim());
        } else {
            dat.setNO_PASA(rs.getString("NO_PASA"));
            
            
        }        
          
        return dat;
    }
    
}
