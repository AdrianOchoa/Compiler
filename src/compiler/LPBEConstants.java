/* Generated By:JavaCC: Do not edit this line. LPBEConstants.java */
package compiler;

/**
 * Token literal values and constants. Generated by
 * org.javacc.parser.OtherFilesGen#start()
 */
public interface LPBEConstants {

    /**
     * End of File.
     */
    int EOF = 0;
    /**
     * RegularExpression Id.
     */
    int INICIO = 1;
    /**
     * RegularExpression Id.
     */
    int FIN = 2;
    /**
     * RegularExpression Id.
     */
    int ESCRIBIR = 3;
    /**
     * RegularExpression Id.
     */
    int IMPRIMIR = 4;
    /**
     * RegularExpression Id.
     */
    int SI = 5;
    /**
     * RegularExpression Id.
     */
    int SINO = 6;
    /**
     * RegularExpression Id.
     */
    int CAMBIO = 7;
    /**
     * RegularExpression Id.
     */
    int OPCION = 8;
    /**
     * RegularExpression Id.
     */
    int ROMPER = 9;
    /**
     * RegularExpression Id.
     */
    int DEFECTO = 10;
    /**
     * RegularExpression Id.
     */
    int CONTINUAR = 11;
    /**
     * RegularExpression Id.
     */
    int MIENTRAS = 12;
    /**
     * RegularExpression Id.
     */
    int HACER = 13;
    /**
     * RegularExpression Id.
     */
    int PARA = 14;
    /**
     * RegularExpression Id.
     */
    int FUNCION = 15;
    /**
     * RegularExpression Id.
     */
    int NUMERO = 16;
    /**
     * RegularExpression Id.
     */
    int FLOTANTE = 17;
    /**
     * RegularExpression Id.
     */
    int CARACTER = 18;
    /**
     * RegularExpression Id.
     */
    int CADENA = 19;
    /**
     * RegularExpression Id.
     */
    int VACIO = 20;
    /**
     * RegularExpression Id.
     */
    int BOOLEANO = 21;
    /**
     * RegularExpression Id.
     */
    int ASIGNACION = 22;
    /**
     * RegularExpression Id.
     */
    int SUMA = 23;
    /**
     * RegularExpression Id.
     */
    int RESTA = 24;
    /**
     * RegularExpression Id.
     */
    int MULTIPLICACION = 25;
    /**
     * RegularExpression Id.
     */
    int DIVISION = 26;
    /**
     * RegularExpression Id.
     */
    int MODULO = 27;
    /**
     * RegularExpression Id.
     */
    int INCREMENTO = 28;
    /**
     * RegularExpression Id.
     */
    int DECREMENTO = 29;
    /**
     * RegularExpression Id.
     */
    int SUMA_IGUAL = 30;
    /**
     * RegularExpression Id.
     */
    int RESTA_IGUAL = 31;
    /**
     * RegularExpression Id.
     */
    int MULTIPLICACION_IGUAL = 32;
    /**
     * RegularExpression Id.
     */
    int DIVISION_IGUAL = 33;
    /**
     * RegularExpression Id.
     */
    int MODULO_IGUAL = 34;
    /**
     * RegularExpression Id.
     */
    int IGUAL_A = 35;
    /**
     * RegularExpression Id.
     */
    int MENOR_IGUAL = 36;
    /**
     * RegularExpression Id.
     */
    int MAYOR_IGUAL = 37;
    /**
     * RegularExpression Id.
     */
    int MENOR = 38;
    /**
     * RegularExpression Id.
     */
    int MAYOR = 39;
    /**
     * RegularExpression Id.
     */
    int DIFERENTE = 40;
    /**
     * RegularExpression Id.
     */
    int OR = 41;
    /**
     * RegularExpression Id.
     */
    int AND = 42;
    /**
     * RegularExpression Id.
     */
    int VALOR_ENTERO = 43;
    /**
     * RegularExpression Id.
     */
    int VALOR_FLOTANTE = 44;
    /**
     * RegularExpression Id.
     */
    int VALOR_CADENA = 45;
    /**
     * RegularExpression Id.
     */
    int VALOR_CARACTER = 46;
    /**
     * RegularExpression Id.
     */
    int VALOR_VERDADERO = 47;
    /**
     * RegularExpression Id.
     */
    int VALOR_FALSO = 48;
    /**
     * RegularExpression Id.
     */
    int IDENTIFICADOR = 49;
    /**
     * RegularExpression Id.
     */
    int PARENTESIS_APERTURA = 50;
    /**
     * RegularExpression Id.
     */
    int PARENTESIS_CIERRE = 51;
    /**
     * RegularExpression Id.
     */
    int LLAVE_APERTURA = 52;
    /**
     * RegularExpression Id.
     */
    int LLAVE_CIERRE = 53;
    /**
     * RegularExpression Id.
     */
    int DELIMITADOR = 54;
    /**
     * RegularExpression Id.
     */
    int DOS_PUNTOS = 55;
    /**
     * RegularExpression Id.
     */
    int PUNTO = 56;
    /**
     * RegularExpression Id.
     */
    int COMA = 57;

    /**
     * Lexical state.
     */
    int DEFAULT = 0;

    /**
     * Literal token values.
     */
    String[] tokenImage = {
        "<EOF>",
        "\"INICIO\"",
        "\"FIN\"",
        "\"ESCRIBIR\"",
        "\"IMPRIMIR\"",
        "\"SI\"",
        "\"SINO\"",
        "\"CAMBIO\"",
        "\"OPCION\"",
        "\"ROMPER\"",
        "\"DEFECTO\"",
        "\"CONTINUAR\"",
        "\"MIENTRAS\"",
        "\"HACER\"",
        "\"PARA\"",
        "\"FUNCION\"",
        "\"NUMERO\"",
        "\"FLOTANTE\"",
        "\"CARACTER\"",
        "\"CADENA\"",
        "\"VACIO\"",
        "\"BOOLEANO\"",
        "\"=\"",
        "\"+\"",
        "\"-\"",
        "\"*\"",
        "\"/\"",
        "\"%\"",
        "\"++\"",
        "\"--\"",
        "\"+=\"",
        "\"-=\"",
        "\"*=\"",
        "\"/=\"",
        "\"%=\"",
        "\"==\"",
        "\"<=\"",
        "\">=\"",
        "\"<\"",
        "\">\"",
        "\"!=\"",
        "\"||\"",
        "\"&&\"",
        "<VALOR_ENTERO>",
        "<VALOR_FLOTANTE>",
        "<VALOR_CADENA>",
        "<VALOR_CARACTER>",
        "\"VERDADERO\"",
        "\"FALSO\"",
        "<IDENTIFICADOR>",
        "\"(\"",
        "\")\"",
        "\"{\"",
        "\"}\"",
        "\";\"",
        "\":\"",
        "\".\"",
        "\",\"",
        "\" \"",
        "\"\\r\"",
        "\"\\t\"",
        "\"\\n\"",};

}
