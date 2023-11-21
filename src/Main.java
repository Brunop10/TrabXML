public class Main {
    public static void main(String[] args) {

        System.out.println("VALIDACAO COM DTD:");
        executarClasse("AdicionarDOCTYPE");
        executarClasse("ValidaXMLcomDTD");
        System.out.println("\n");
        System.out.println("VALIDACAO COM XSD:");
        executarClasse("ValidaXMLcomXSD");
        System.out.println("\n");
        System.out.println("CONSULTAS:");
        executarClasse("CombineXML");
        executarClasse("Consultas");
        System.out.println("\n");
        System.out.println("TRANSFORMACAO XML PARA HTML:");
        executarClasse("TransformaXMLemHTML");

    }

    private static void executarClasse(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            // Método main esperado em todas as classes
            java.lang.reflect.Method method = clazz.getMethod("main", String[].class);
            // Invoca o método main
            method.invoke(null, (Object) new String[] {});
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException
                 | IllegalArgumentException | java.lang.reflect.InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
