import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class ValidaXMLcomXSD {
    public static void main(String[] args) {
        try {
            // Configurar a fábrica do Schema
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            // Carregar o esquema XSD
            File xsdFile = new File("nota_fiscal.xsd");
            Schema schema = factory.newSchema(xsdFile);

            // Criar um Validator a partir do esquema
            Validator validator = schema.newValidator();

            String caminhoDiretorio = "C:/Users/bruno/TrabXML"; // Caminho do diretório onde o arquivo está localizado
            int i = 1;
            File arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");

            while (arquivo.exists()) {
                // Definir um manipulador de erros
                CustomErrorHandler errorHandler = new CustomErrorHandler();
                validator.setErrorHandler(errorHandler);

                validator.validate(new StreamSource(arquivo));

                // Verificar se houve erros durante a validação para o arquivo atual
                if (!errorHandler.hasErrors()) {
                    System.out.println("O documento " + i + " foi validado com sucesso!");
                } else {
                    System.out.println("O documento " + i + " nao foi validado devido a erros durante a validacao.");
                }
                i++;
                arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");
            }

        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}

