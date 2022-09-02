/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package liquidacion;

import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author yohan
 */
public class Fecha {
    public static String yyyymmddAddmmyyyy(Date fechai){
        Calendar cal=Calendar.getInstance();
        cal.setTime(fechai);
        String dia=""+cal.get(Calendar.DATE);
        String mes=""+cal.get(Calendar.MONTH);
        String año=""+cal.get(Calendar.YEAR);
        return dia+"/"+mes+"/"+año;
    }
    public static int antiguedad(Date fechaAlta){
         Calendar cal=Calendar.getInstance();
        cal.setTime(fechaAlta);
        int diaAlta=cal.get(Calendar.DATE);
        int mesAlta=cal.get(Calendar.MONTH);
        int añoAlta=cal.get(Calendar.YEAR);
         Calendar cal1=Calendar.getInstance();
         int diaHoy=cal1.get(Calendar.DATE);
        int mesHoy=cal1.get(Calendar.MONTH);
        int añoHoy=cal1.get(Calendar.YEAR);
        int antiguedad=añoHoy-añoAlta;
        if(mesAlta>mesHoy){
            antiguedad--;
        }else if(mesAlta==mesHoy)
                 if(diaAlta>diaHoy)
                     antiguedad--;
        return antiguedad;
    }
}
