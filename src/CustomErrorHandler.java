import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

class CustomErrorHandler implements ErrorHandler {
    private boolean hasErrors = false;

    @Override
    public void warning(SAXParseException exception) throws SAXException {

    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        hasErrors = true;
        System.out.println("Erro: " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        hasErrors = true;
        System.out.println("Erro fatal: " + exception.getMessage());
    }

    public boolean hasErrors() {
        return hasErrors;
    }


}