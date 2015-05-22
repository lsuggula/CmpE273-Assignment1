package hello;

/**
 * Created by lalithasuggula on 3/5/15.
 */

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;

@RestController
public class modcontrol
{


        HashMap<String , mod> modhm = new HashMap<String, mod>();
        @RequestMapping(value = "/api/v1/moderators", method = {RequestMethod.POST},
                consumes = "application/json", produces = "application/json")
        public ResponseEntity<mod> createModerator(@RequestBody @Valid mod moderator)
        {
            modhm.put(""+moderator.getId(), moderator);
            return new ResponseEntity<mod>( modhm.get("" + moderator.getId()), HttpStatus.CREATED);
        }




        @RequestMapping(value = "api/v1/moderators/{m_id}", method =RequestMethod.PUT)

        public ResponseEntity<mod> updateModerator( @PathVariable("m_id") int m_id,
                                                              @RequestBody @Valid mod moderator) {

            moderator.resetc();


            mod tempMod=modhm.get(""+m_id);
            tempMod=modhm.get(""+m_id);
            if(!(moderator.getEmail()==null))
            {tempMod.setEmail(moderator.getEmail());}
            if(!(moderator.getPassword()==null))
            {tempMod.setPassword(moderator.getPassword());}
            if(!(moderator.getName()==null))
            {tempMod.setName(moderator.getName());}
            modhm.remove(""+m_id);
            modhm.put(""+m_id,tempMod);


            return new ResponseEntity<mod>( modhm.get("" + m_id), HttpStatus.CREATED);


        }

        @RequestMapping(value = "api/v1/moderators/{m_id}", method = RequestMethod.GET)

        public ResponseEntity<mod> getModerator(HttpServletRequest request, @PathVariable("m_id") int m_id
        ) {



            return new ResponseEntity<mod>( modhm.get("" + m_id), HttpStatus.OK);


        }



    }