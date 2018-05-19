package main.sessionbeans;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;
import java.util.Properties;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;
import static javax.naming.Context.URL_PKG_PREFIXES;

public class ClientTest {

    public static void main(String[] args) throws Exception {
        // Invoke a stateless bean
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        invokeLoginBean();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        invokeRegistrationBean();

    }

    private static void invokeLoginBean() throws NamingException {
        final Login login = lookupLogin();
        System.out.println("Obtained remote");
    }
    private static void  invokeRegistrationBean(){}

    private static Login lookupLogin() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        //jndiProperties.put(URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
       // jndiProperties.put(PROVIDER_URL, "http-remoting://" + "localhost"+ ":" + 8080);
        try {
            Hashtable<Object, Object> props = new Hashtable();

            props.put(INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
            props.put(PROVIDER_URL, "remote://" + "localhost" + ":" + 8080);
            Context ctx = new InitialContext(props);

            return (Login) ctx.lookup("ejb:/ejb/LoginBean!main.sessionbeans.Login");

        }
        catch (NamingException e){
            throw e;
        }
    }
}
