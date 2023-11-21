import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileOutputStream;

public class TransformaXMLemHTML {
    public static void main(String[] args) {
        try {
            // Caminho para o arquivo XSLT
            String xsltFilePath = "notas_tot.xsl";

            // Caminho para o arquivo XML de notas fiscais combinadas
            String xmlFilePath = "notas_geral.xml";

            // Caminho para o arquivo de saída HTML
            String htmlOutputPath = "output.html";

            // Carregando o arquivo XSLT
            File xsltFile = new File(xsltFilePath);
            StreamSource xsltStreamSource = new StreamSource(xsltFile);

            // Carregando o arquivo XML de notas fiscais
            File xmlFile = new File(xmlFilePath);
            StreamSource xmlStreamSource = new StreamSource(xmlFile);

            // Criando o arquivo de saída HTML
            FileOutputStream htmlOutputFile = new FileOutputStream(htmlOutputPath);

            // Usando o TransformerFactory padrão
            TransformerFactory factory = TransformerFactory.newInstance();

            // Criando o transformer
            Transformer transformer = factory.newTransformer(xsltStreamSource);

            // Realizando a transformação
            transformer.transform(xmlStreamSource, new StreamResult(htmlOutputFile));

            // Fechando o arquivo de saída HTML
            htmlOutputFile.close();

            System.out.println("Transformacao concluida. Resultado salvo em " + htmlOutputPath);

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // Caminho para o arquivo XSLT
            String xsltFilePath = "notas_ind.xsl";

            String caminhoDiretorio = "C:/Users/bruno/TrabXML"; // Caminho do diretório onde o arquivo está localizado
            int i = 1;
            File arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");

            while(arquivo.exists()) {

                // Caminho para o arquivo de saída HTML
                String htmlOutputPath = "output"+ i +".html";

                // Carregando o arquivo XSLT
                File xsltFile = new File(xsltFilePath);
                StreamSource xsltStreamSource = new StreamSource(xsltFile);

                // Carregando o arquivo XML de notas fiscais
                File xmlFile = new File("nota"+ i +".xml");
                StreamSource xmlStreamSource = new StreamSource(xmlFile);

                // Criando o arquivo de saída HTML
                FileOutputStream htmlOutputFile = new FileOutputStream(htmlOutputPath);

                // Usando o TransformerFactory padrão
                TransformerFactory factory = TransformerFactory.newInstance();

                // Criando o transformer
                Transformer transformer = factory.newTransformer(xsltStreamSource);

                // Realizando a transformação
                transformer.transform(xmlStreamSource, new StreamResult(htmlOutputFile));

                // Fechando o arquivo de saída HTML
                htmlOutputFile.close();

                System.out.println("Transformacao concluida. Resultado salvo em " + htmlOutputPath);

                i++;
                arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

