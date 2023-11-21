import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;

public class Consultas {
    public static void main(String[] args) {
        try {
            // Carregar o arquivo XML
            File file = new File("notas_geral.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(file);

            // Normalizar o documento
            document.getDocumentElement().normalize();

            // Realizar consultas
            consultarNumeroProdutosEValor(document);
            consultarTotais(document);
            consultarDetalhesMenorPreco(document);
            consultarDetalhesMaiorImposto(document);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void consultarNumeroProdutosEValor(Document document) {
        int totalProdutos;
        double valorTotalProdutos = 0;
        int i;

        NodeList detList = document.getElementsByTagName("det");
        for (i = 0; i < detList.getLength(); i++) {
            Element detElement = (Element) detList.item(i);
            double valor = Double.parseDouble(detElement.getElementsByTagName("vProd").item(0).getTextContent());
            valorTotalProdutos += valor;
        }
        totalProdutos = i;

        System.out.println("a) NUmero total de produtos: " + totalProdutos);
        System.out.println("   Valor total dos produtos: " + valorTotalProdutos);
    }

    private static void consultarTotais(Document document) {
        double totalISSQN = 0;
        double totalICMS = 0;
        double valorAproxTributos = 0;
        double totalFrete = 0;

        NodeList impostoList = document.getElementsByTagName("total");
        for (int i = 0; i < impostoList.getLength(); i++) {
            Element impostoElement = (Element) impostoList.item(i);

            //duvida valor total de ISSQN e valor Aprox de tributos
            totalISSQN += parseDouble(getElementTextContent(impostoElement, "vISSRet"));
            totalICMS += parseDouble(getElementTextContent(impostoElement, "vICMS"));
            valorAproxTributos += parseDouble(getElementTextContent(impostoElement, "vTotTrib"));
            totalFrete += parseDouble(getElementTextContent(impostoElement, "vFrete"));
        }


        System.out.println("b) Total de ISSQN retido: " + totalISSQN);
        System.out.println("   Total do ICMS: " + totalICMS);
        System.out.println("   Valor aproximado de tributos: " + valorAproxTributos);
        System.out.println("   Total de frete: " + totalFrete);
    }

    private static void consultarDetalhesMenorPreco(Document document) {
        double menorPreco = Double.MAX_VALUE;
        String detalhesMenorPreco = "";

        NodeList detList = document.getElementsByTagName("det");
        for (int i = 0; i < detList.getLength(); i++) {
            Element detElement = (Element) detList.item(i);
            double preco = Double.parseDouble(detElement.getElementsByTagName("vUnCom").item(0).getTextContent());

            if (preco < menorPreco) {
                menorPreco = preco;
                detalhesMenorPreco = detElement.getElementsByTagName("xProd").item(0).getTextContent();
            }
        }

        System.out.println("c) Detalhes do produto com menor preco:");
        System.out.println("   Produto: " + detalhesMenorPreco);
        System.out.println("   Preco: " + menorPreco);
    }

    private static void consultarDetalhesMaiorImposto(Document document) {
        double maiorImposto = Double.MIN_VALUE;
        String idNotaMaiorImposto = "";
        String infAdicNotaMaiorImposto = "";

        NodeList impostoList = document.getElementsByTagName("infNFe");
        for (int i = 0; i < impostoList.getLength(); i++) {
            Element impostoElement = (Element) impostoList.item(i);
            double impostoNota = parseDouble(getElementTextContent(impostoElement, "vICMS"))
                    + parseDouble(getElementTextContent(impostoElement, "vPIS"))
                    + parseDouble(getElementTextContent(impostoElement, "vCOFINS"))
                    + parseDouble(getElementTextContent(impostoElement, "vIPI"))
                    + parseDouble(getElementTextContent(impostoElement, "vOutro"));

            if (impostoNota > maiorImposto) {
                maiorImposto = impostoNota;
                idNotaMaiorImposto = impostoElement.getAttribute("Id");
                infAdicNotaMaiorImposto = impostoElement.getElementsByTagName("infCpl").item(0).getTextContent();
            }
        }

        System.out.println("d) Detalhes da nota com maior imposto:");
        System.out.println("   ID da nota: " + idNotaMaiorImposto);
        System.out.println("   Informacoes adicionais da nota: " + infAdicNotaMaiorImposto);
        System.out.println("   Valor do imposto: " + maiorImposto);
    }

    // Função utilitária para obter o conteúdo do texto do elemento, tratando nulos
    private static String getElementTextContent(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        } else {
            return "";
        }
    }

    // Função utilitária para converter uma string para double, tratando nulos
    private static double parseDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException | NullPointerException e) {
            return 0.0;
        }
    }
}
