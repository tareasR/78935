package mx.uv;

import static spark.Spark.*;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        // para permitir el CORS
        //fuente:https://gist.github.com/saeidzebardast/e375b7d17be3e0f4dddf
        options("/*",(request,response)->{
        String accessControlRequestHeaders=request.headers("Access-Control-Request-Headers");
        if(accessControlRequestHeaders!=null){
        response.header("Access-Control-Allow-Headers",accessControlRequestHeaders);
        }
        String accessControlRequestMethod=request.headers("Access-Control-Request-Method");
        if(accessControlRequestMethod!=null){
        response.header("Access-Control-Allow-Methods",accessControlRequestMethod);
        }
        return "OK";
        });
        before((request,response)->response.header("Access-Control-Allow-Origin","*"));


        // (pr1, pr2) -> "";
        // (pr1, pr2) -> {return null;};
        // refactorización
        get("/", (request, response)-> "hola");

        get("/xml", (request, response)-> {
            String respuesta=
            "<p>" + 
                "<x>" + 
                    "<y>algo</y>" + 
                "</x>" + 
                "<x><y>algo2</y></x>" + 
            "</p>";
            response.type("application/xml");
            return respuesta;
        });

        get("/xml2", (request, response)-> {
            InnerApp whatsapp = new InnerApp();
            whatsapp.setNombreApp("whatsapp");
            whatsapp.setIdApp("1234");
            whatsapp.setComments("aplicación de mensajería");

            /*
            String respuesta = "<nombre>" +
                whatsapp.getNombreApp() +
                "</nombre>";
                */

            XmlMapper xml = new XmlMapper();
            String respuesta = xml.writeValueAsString(whatsapp);
            response.type("application/xml");
            return respuesta;
        });
    }
}

/**
 * InnerApp
 */
class InnerApp {
    String nombreApp;
    String idApp;
    String comments;

    public String getNombreApp() {
        return nombreApp;
    }
    public void setNombreApp(String nombreApp) {
        this.nombreApp = nombreApp;
    }
    public String getIdApp() {
        return idApp;
    }
    public void setIdApp(String idApp) {
        this.idApp = idApp;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }    
}