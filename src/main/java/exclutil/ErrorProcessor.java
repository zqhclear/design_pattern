package exclutil;

import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class ErrorProcessor extends DefaultHandler
{

    public ErrorProcessor ()
    {
	    super();
    }

    public void error (SAXParseException e) {
        System.out.println("Error: "+e.getMessage());
    }

    public void fatalError (SAXParseException e) {
        System.out.println("Fatal Error: "+e.getMessage());
    }

    public void warning (SAXParseException e) {
        System.out.println("Warning: "+e.getMessage());
    }

}