package main.java;

import main.java.sessionbeans.Login;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

import static javax.naming.Context.INITIAL_CONTEXT_FACTORY;
import static javax.naming.Context.PROVIDER_URL;
import static javax.naming.Context.URL_PKG_PREFIXES;

public class ClientTest {

    public static void main(String[] args) throws Exception {
        // Invoke a stateless bean
        invokeLoginBean();
        invokeRegistrationBean();

    }

    private static void invokeLoginBean() throws NamingException {
        final Login login = lookupLogin();
    }
    private static void  invokeRegistrationBean(){}

    private static Login lookupLogin() throws NamingException {
         try {
            Hashtable<Object, Object> props = new Hashtable<>();

            props.put(INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
            props.put(URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

            props.put("jboss.naming.client.ejb.context", false);
            props.put("org.jboss.ejb.client.scoped.context", true);

            props.put(PROVIDER_URL, "remote://" + "localhost" + ":" + 8080);
            props.put("remote.connection.default.host", "localhost");
            props.put("remote.connection.default.port", 8080);

            Context ctx = new InitialContext(props);

            return (Login) ctx.lookup("ejb:/ejb/LoginBean!main.java.sessionbeans.Login");

        }
        catch (NamingException e){
            throw e;
        }
    }
}
