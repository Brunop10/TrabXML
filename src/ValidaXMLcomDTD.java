import org.w3c.dom.Document;
import org.xml.sax.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ValidaXMLcomDTD {
    public static void main(String[] args) {
        try {
            // Configurar a fábrica do DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true); // Habilitar validação DTD
            factory.setNamespaceAware(true);

            // Criar um DocumentBuilder com manipulação de erros personalizada
            DocumentBuilder builder = factory.newDocumentBuilder();

            String caminhoDiretorio = "C:/Users/bruno/TrabXML"; // Caminho do diretório onde o arquivo está localizado
            int i = 1;
            File arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");

            while (arquivo.exists()) {
                CustomErrorHandler errorHandler = new CustomErrorHandler(); // Instancia um novo ErrorHandler para cada arquivo
                builder.setErrorHandler(errorHandler);

                // Definir o DTD a partir de um arquivo externo
                File dtdFile = new File("nota_fiscal.dtd");
                builder.setEntityResolver((publicId, systemId) -> {
                    if (systemId.endsWith("nota_fiscal.dtd")) {
                        return new InputSource(new FileReader(dtdFile));
                    }
                    return null;
                });

                // Fazer o parse do XML
                Document document = builder.parse(arquivo);

                // Verificar se houve erros durante a validação para o arquivo atual
                if (!errorHandler.hasErrors()) {
                    System.out.println("O documento " + i + " foi validado com sucesso!");
                } else {
                    System.out.println("O documento " + i + " nao foi validado devido a erros durante a validação.");
                }
                i++;
                arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");
            }

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
