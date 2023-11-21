import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.nio.file.Files;

public class CombineXML {
    public static void main(String[] args) {
        try {
            // Crie um arquivo XML de saída para combinar os XMLs
            Writer combinedXmlWriter = new FileWriter("notas_geral.xml");
            // Adiciona um cabeçalho
            combinedXmlWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");

            // Criando uma raiz <notas> para o xml combinado
            combinedXmlWriter.write("<notas>\n");

            String caminhoDiretorio = "C:/Users/bruno/TrabXML"; // Caminho do diretório onde o arquivo está localizado
            File arquivo = new File(caminhoDiretorio, "nota1.xml");
            int i = 1;

            while(arquivo.exists()) {
                // Le o conteudo de cada "nota" + i + ".xml
                String xmlContent = new String(Files.readAllBytes(new File("nota" + i + ".xml").toPath()));

                String[] lines = xmlContent.split("\n");

                // Escreve o conteudo a partir da segunda linha para ignorar repetição de cabeçalho
                for (int j = 2; j < lines.length; j++) {
                    combinedXmlWriter.write(lines[j]);
                    combinedXmlWriter.write("\n");
                }

                combinedXmlWriter.write("\n");

                i++;
                arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");
            }

            // Fecha o conteúdo com o terminador do raiz </notas>
            combinedXmlWriter.write("</notas>\n");

            // Feche o arquivo de saída
            combinedXmlWriter.close();

            System.out.println("Combinacao de XMLs concluida com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
