/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import beans.tableroGralBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class tableroGralMunMapper implements Mapper{
    
    
    public Object mapRow(ResultSet rs) throws SQLException {
         tableroGralBean dat = new tableroGralBean();
        
        
         if (rs.getString("CVE_MUN") != null) {
            dat.setCVE_MUN(rs.getString("CVE_MUN").trim());
        } else {
            dat.setCVE_MUN(rs.getString("CVE_MUN"));
        }
           if (rs.getString("MUNICIPIO") != null) {
            dat.setMUNICIPIO(rs.getString("MUNICIPIO").trim());
        } else {
            dat.setMUNICIPIO(rs.getString("MUNICIPIO"));
        }
           
          if (rs.getString("EN_PROCESO") != null) {
            dat.setEN_PROCESO(rs.getString("EN_PROCESO").trim());
        } else {
            dat.setEN_PROCESO(rs.getString("EN_PROCESO"));
        }   
          
            if (rs.getString("ACTIVO_UE") != null) {
            dat.setACTIVO_UE(rs.getString("ACTIVO_UE").trim());
        } else {
            dat.setACTIVO_UE(rs.getString("ACTIVO_UE"));
        }   
            
        if (rs.getString("BAJA_UE") != null) {
            dat.setBAJA_UE(rs.getString("BAJA_UE").trim());
        } else {
            dat.setBAJA_UE(rs.getString("BAJA_UE"));
        } 
          if (rs.getString("EGRESADO_UE") != null) {
            dat.setEGRESADO_UE(rs.getString("EGRESADO_UE").trim());
        } else {
            dat.setEGRESADO_UE(rs.getString("EGRESADO_UE"));
        } 
        
          
        return dat;
    }
}
