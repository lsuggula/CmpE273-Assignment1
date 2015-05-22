package hello;

/**
 * Created by lalithasuggula on 3/8/15.
 */

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class pollcontrol {

//—————————————————define hash map———————————————

        HashMap<String , poll> pollhm = new HashMap<String, poll>();
        HashMap<String , pollmodcombi> pollmodhm = new HashMap<String, pollmodcombi>();
        HashMap<String , Integer []> reshm = new HashMap<String, Integer []>();

//——————————————————————class for listing and view with ————————————————

        public class listingpolls
        {
            public poll p[];
            public Integer result[][];
        }

        public class viewwires
        {
            public poll p;
            public Integer result[];
            public viewwires(){};
        }


        //—————————————————————————create a poll————————————————————————

        @RequestMapping(value = "api/v1/moderators/{id}/polls",method = {RequestMethod.POST}
                //, params = {"question","choice"}
                )
        @ResponseStatus(org.springframework.http.HttpStatus.CREATED)
        public poll createPoll(@PathVariable int id, HttpServletRequest request, @Valid @RequestBody poll p)
                               /*@RequestParam(value="question", defaultValue="-1") String question,
                               @RequestParam(value="choice", defaultValue="-1") String choice)*/ {

            String[] choices=p.getChoice();
            Integer[] i=new Integer [choices.length];
            int ii []=new int [choices.length];
            for(int j=0;j<choices.length;j++)
            {
                i[j]=ii[j];
            }
            poll myPoll=new poll(p.getQuestion(),p.getChoice());

            pollhm.put(myPoll.getId(), myPoll);
            reshm.put(myPoll.getId(), i);
            pollmodcombi temp=new pollmodcombi();
            temp.pid=myPoll.getId();
            temp.mid=""+id;
            pollmodhm.put(temp.pid,temp);
            return pollhm.get(myPoll.getId());

        }


//———————————————————----------view without results————————————

        @RequestMapping(value = "api/v1/polls/{p_id}", method ={ RequestMethod.GET})
        public poll viewwores(@PathVariable("p_id") String p_id) {

            return pollhm.get(p_id);
        }

//——————————----------------view with result—————————————

        @RequestMapping(value = "api/v1/moderators/{mod_id}/polls/{p_id}", method =
                {RequestMethod.PUT,RequestMethod.GET})
        public viewwires viewwires(@PathVariable("p_id") String p_id,@PathVariable("mod_id")
        String mod_id)
        {

            viewwires temp=new viewwires();
            temp.p=pollhm.get(p_id);
            temp.result=reshm.get(p_id);
            return temp;
        }

//----------------------------listing polls—————————————

        @RequestMapping(value = "api/v1/moderators/{id}/polls",method ={RequestMethod.GET})
        public ArrayList<poll> listingpolls(@PathVariable("id") int id) {
            pollfun temp=new pollfun();
            return temp.listView(pollmodhm, id, pollhm);
        }

//—————————————--------------delete a poll———————————--------------

        @RequestMapping(value = "api/v1/moderators/{moderator_id}/polls/{poll_id}",method =
                {RequestMethod.DELETE})
        @ResponseStatus(org.springframework.http.HttpStatus.NO_CONTENT)
        public void delete(@PathVariable("poll_id") String poll_id) {
            pollhm.remove(poll_id);
            return;
        }

//—————————-------------------vote a poll———————————-------------

        @RequestMapping(value = "api/v1/polls/{poll_id}", method ={RequestMethod.PUT})
        public void VoteAPoll(@PathVariable("poll_id") String poll_id,@RequestParam
                (value="choice", defaultValue="-1") String choice)
        {

            Integer temp[]=reshm.get(poll_id);
            int cho=Integer.parseInt(choice);
            temp[cho]++;
            reshm.put(poll_id,temp);
        }
    }