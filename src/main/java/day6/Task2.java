package day6;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author
 */
public class Task2 {
    // assumed untainted and instantiated
    private HashMap< String , String > users ;
    // assumed declared only ( not instantiated )
    private String [] guests ;
    // ’ user ’ assumed tainted , return type assumed untainted
    public String loadProfile ( String user ) {
        String target = null ;
        for ( String profile : users . keySet ()) {
            if ( profile . equals ( user ))
                return users . get ( user );
        }
        guests = loadGuests ();
        for ( String profile : guests ) {
            if ( user . equals ( profile ))
                return " GUEST : " + profile ;
        }
        return " Unknown " ;
    }
    // assumed instantiated
    String fileName ;
    private String [] loadGuests () {
        try {
            FileInputStream file = new FileInputStream ( fileName );
            ObjectInputStream in = new ObjectInputStream ( file );
            guests = ( String []) in . readObject ();
            in . close ();
            file . close ();
        } catch ( Exception e ) {
            System . err . println ( " something went wrong " );
        }
        return guests ;
    }
}
