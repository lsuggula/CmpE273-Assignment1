package hello;

/**
 * Created by lalithasuggula on 3/8/15.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class pollfun {

    public static int c;

    ArrayList<poll> myArray =new ArrayList();

    public ArrayList<poll> listView(HashMap<String , pollmodcombi> pollmodhm,int id,HashMap<String ,poll> pollhm)
    {

        ArrayList<poll> array=new  ArrayList();

        Iterator it = pollmodhm.entrySet().iterator();

        while(it.hasNext())
        {
            HashMap.Entry pair=  (HashMap.Entry)it.next();
            pollmodcombi temp=new pollmodcombi();

            temp=(pollmodcombi)pair.getKey();
            if(temp.mid==(""+id))
            {
                array.add(pollhm.get(temp.pid));
            }
        }
        return array;
    }

}