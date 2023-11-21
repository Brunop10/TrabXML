import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AdicionarDOCTYPE {
    public static void main(String[] args) {
        // Caminho para o arquivo DTD (nota_fiscal.dtd)
        String caminhoDtd = "C:/Users/bruno/TrabXML/nota_fiscal.dtd";

        // Conteúdo da declaração DOCTYPE
        String doctype = "<!DOCTYPE nfeProc SYSTEM \"" + caminhoDtd + "\">";

        String caminhoDiretorio = "C:/Users/bruno/TrabXML"; // Caminho do diretório onde o arquivo está localizado
        File arquivo = new File(caminhoDiretorio, "nota1.xml");
        int i = 1;

        while(arquivo.exists()) {
            // Caminho para o arquivo XML ("nota"+ i +".xml")
            String caminhoXml = "C:/Users/bruno/TrabXML/nota"+ i +".xml";

            // Ler o conteúdo atual do arquivo XML
            StringBuilder conteudoXml = new StringBuilder();
            try {
                java.util.Scanner scanner = new java.util.Scanner(new File(caminhoXml));
                while (scanner.hasNextLine()) {
                    String linha = scanner.nextLine();
                    conteudoXml.append(linha).append("\n");

                    if(linha.contains("<!DOCTYPE")) {
                        System.out.println("Declaracao de DOCTYPE ja existe");
                        return;
                    }
                    // Inserir a declaração DOCTYPE após a declaração de versão do XML (linha 2)
                    if (linha.contains("<?xml version=")) {
                        conteudoXml.append(doctype).append("\n");
                    }
                }
                scanner.close();
            } catch (java.io.FileNotFoundException e) {
                e.printStackTrace();
            }

            // Escrever o XML modificado de volta para o arquivo
            try {
                FileWriter writer = new FileWriter(caminhoXml);
                writer.write(conteudoXml.toString());
                writer.close();
                System.out.println("Declaracao DOCTYPE adicionada com sucesso em notas"+ i +".xml");
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
            arquivo = new File(caminhoDiretorio, "nota" + i + ".xml");
        }
    }
}

