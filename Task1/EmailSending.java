package Task1;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

public class EmailSending {

    public static void main(String[] args) throws MalformedURLException, EmailException {
        EmailAttachment ea = new EmailAttachment();
        ea.setURL(new URL("http://d3dsacqprgcsqh.cloudfront.net/photo/azbW3zq_460sa_v1.gif"));
        ea.setName("Hahahaha");
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.googlemail.com");
        email.addTo("mekliov00@gmail.com");
        email.setFrom("mekliov00@gmail.com");
        email.setAuthenticator(new DefaultAuthenticator("************", "*********"));
        email.setSSLOnConnect(true);
        email.setDebug(true);
        email.setMsg("Hahaha");
        email.attach(ea);
        email.send();

    }

}
