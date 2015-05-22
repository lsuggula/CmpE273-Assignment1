package hello;

/**
 * Created by lalithasuggula on 3/5/15.
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class mod
{

    private int id;
    private static int c=10;
    private String name;
    private String email;
    private String password;
    private Timestamp created_time;

    public mod returnmod()
    {

        return this;
    }

    //--------------------constructors-------------------------------

    mod(){};

    mod(String name,String email,String password)
    {
        this.setId();
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setCreated_time();
    }

    //----------------------------get--------------------------

    public int getId() {
        return id;
    }
    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public Timestamp getCreated_time() {
        return created_time;
    }

    //-------------------------------set-----------------------------

    public void setId() {
        c++;
        this.id = c;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setCreated_time() {
        Date date= new Date();
        this.created_time = new Timestamp(date.getTime()) ;
    }

    public void resetc()
    {

        c--;
    }

    //-------------------------json property-------------------------------

    @JsonCreator
    public mod(@JsonProperty("id")int id, @JsonProperty("name")String name,
                         @JsonProperty("email")String email, @JsonProperty("password")String password, @JsonProperty("created_at")String created_at) {
        this.setId();
        this.setName(name);
        this.setEmail(email);
        this.setPassword(password);
        this.setCreated_time();
    }


    private String  getTimeNow() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'dd:HH:mm.sss'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }

}
