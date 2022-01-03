package com.test;

import com.model.Mail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClientTest {
    @Autowired
    private RestTemplate restTemplate;
    /**
        CHECK IF USER "c2@c" contains an email from "m@m"
    */
    @Test
    void verifyMailExistance() {
        Mail mail=new Mail();
        mail.setFrom("m@m");
        mail.setTo("c2@c");
        ArrayList<HashMap<String,String>> mails = restTemplate.getForObject("https://localhost:8082/getMails/c2@c",ArrayList.class);
        Iterator<HashMap<String,String>> it=mails.iterator();
        String from = null,to=null;
        while(it.hasNext())
        {
            HashMap<String,String> hm=it.next();
            from=hm.get("from");
            to = hm.get("to");
            if(from.equals(mail.getFrom()))
            {
                to.equals(mail.getTo());
                break;
            }
        }
        assertEquals(mail.getFrom(),from);
        assertEquals(mail.getTo(),to);

    }

}
