/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;


import beans.UnidadesEconomicasBean;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author pedro
 */
public class ueIeactivasMapper implements Mapper{
    
     public Object mapRow(ResultSet rs) throws SQLException {
         UnidadesEconomicasBean dat = new UnidadesEconomicasBean();
        

        
         if (rs.getString("ID_IE_UE") != null) {
            dat.setID_IE_UE(rs.getString("ID_IE_UE").trim());
        } else {
            dat.setID_IE_UE(rs.getString("ID_IE_UE"));
        }
         if (rs.getString("RFC") != null) {
            dat.setRFC(rs.getString("RFC").trim());
        } else {
            dat.setRFC(rs.getString("RFC"));
        }
         if (rs.getString("RAZON_SOCIAL") != null) {
            dat.setRAZON_SOCIAL(rs.getString("RAZON_SOCIAL").trim());
        } else {
            dat.setRAZON_SOCIAL(rs.getString("RAZON_SOCIAL"));
        }
      
        
        return dat;
    }
}
