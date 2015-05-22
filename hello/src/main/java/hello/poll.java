package hello;

/**
 * Created by lalithasuggula on 3/5/15.
 */

import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class poll

{
    private static Integer c=10;
    private String id;
    private String question;
    private String started_at;
    private String expired_at;
    private String[] choice;

//---------------------constructors----------------------
    poll()
    {}
    /*public poll(String question, String[] choices)
    {
        this.setId();
        this.setQuestion(question);
        this.setStarted_at();
        this.setExpired_at();
        this.setChoice(choices);
    }*/

    public poll poll(String question,String started_at,String expired_at)
    {
        return this;
    }

//-------------------------get---------------------------

    public String getId()
    {
        return id;
    }
    public String getQuestion()
    {
        return question;
    }
    public String getStarted_at()
    {
        return started_at;
    }
    public String getExpired_at()
    {
        return expired_at;
    }
    public String[] getChoice()
    {
        return choice;
    }

//------------------------set----------------------------

    public void setId()
    {
        this.id = converter(c);
        c++;
    }
    public void setQuestion(String question)
    {
        this.question = question;
    }
    public void setStarted_at()
    {
        this.started_at = getTimeNow() ;
    }
    public void setExpired_at( )
    {
        this.expired_at =getTimeNow();
    }
    public void setChoice(String[] choice)
    {
        this.choice = choice;
    }

//------------------------conversion logic to base 36--------------------

    public String converter(int num)
    {
        String str="";
        while(num>0)
        {
            int t =0;
            t=num%36;
            if(t>9)
            {
                
                char t2=(char)(55+t);
                str=t2+str;
            }
            else
            {
                str=t+str;
            }
            num=num/36;
        }
        return str;
    }

//------------------------time format---------------------------

    private String getTimeNow()
    {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'dd:HH:mm.sss'Z'");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }

    public poll ( @JsonProperty("question")String question, @JsonProperty("choices")String[] choices) {
        // String[] choices=choice;
        this.setQuestion(question);
        this.setStarted_at();
        this.setChoice(choices);
        this.setExpired_at();
        this.setId();
    }
    public void resetc()
    {

        c--;
    }


}
