package mx.uv;

import static spark.Spark.*;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );

        get("/xml", (request, response) -> {
            String respuesta = "<x><y/></x>";
            response.type("application/xml");
            return respuesta;
        });
    }
}
