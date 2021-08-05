import org.joda.time.Chronology;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.chrono.GregorianChronology;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by IntelliJ IDEA.
 * User: udoluweera
 * Date: 12/23/11
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tester {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();


        System.out.println("Time Now : " + calendar.getTime().toString());
        System.out.println("Time Now : " + calendar.getTimeZone());


        TimeZone timeZoneColombo = TimeZone.getTimeZone("Pacific/Apia");
        calendar.setTimeZone(timeZoneColombo);

        System.out.println("Time Now : " + calendar.getTime());
        System.out.println("Time Now : " + calendar.getTimeZone());


        System.out.println("JODA TIME ");

        Chronology coptic =  GregorianChronology.getInstance();
        DateTime dt = new DateTime(coptic);

        System.out.println("JODA TIME NOQW " + dt.toString());


        DateTimeZone zoneColombo = DateTimeZone.forID("Asia/Colombo");
        //DateTimeZone zoneColombo = DateTimeZone.forID("Europe/Athens");
        Chronology coptic2 =  GregorianChronology.getInstance(zoneColombo);

        DateTime dt2 = new DateTime(coptic2);
        //Chronology gregorianJuian = GJChronology.getInstance(zoneColombo);

        System.out.println("JODA TIME NOQW " + dt2.toString());







        //-----
//        Calendar.getInstance()





    }
}
