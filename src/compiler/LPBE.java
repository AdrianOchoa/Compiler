/* Generated By:JavaCC: Do not edit this line. LPBE.java */
package compiler;

import java.util.ArrayList;

public class LPBE implements LPBEConstants {

    static ArrayList<String> lista = new ArrayList();
    private static final StringBuilder erroresSintacticos;

    static {
        erroresSintacticos = new StringBuilder();
    }

    public static void main(String args[]) throws ParseException {
        try {
            LPBE lpbe = new LPBE(System.in);
            lpbe.ejecutarAnalisisSintactico();
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void registraLogSintactico(String mensaje) {
        erroresSintacticos.append(mensaje).append("\u005cn");
    }

    public static void registraErrorSintactico(String mensaje, Token token) {
        StringBuilder sb = new StringBuilder()
                .append(mensaje)
                .append("\u005cn")
                .append("Linea: ").append(token.beginLine)
                .append("\u005cn")
                .append(" Columna: ").append(token.beginColumn);
        registraLogSintactico(sb.toString());
    }

    public static ArrayList<String> getLista() {
        return lista;
    }

    public static StringBuilder getErrores() {
        return erroresSintacticos;
    }

    /*
     Este método se encarga de verificar el cuerpo completo del programa
     */
    final public boolean ejecutarAnalisisSintactico() throws ParseException {
        try {
            jj_consume_token(INICIO);
            try {
                jj_consume_token(PARENTESIS_APERTURA);
                jj_consume_token(PARENTESIS_CIERRE);
                jj_consume_token(LLAVE_APERTURA);
                try {
                    estructuraDelPrograma();
                    try {
                        jj_consume_token(LLAVE_CIERRE);
                        jj_consume_token(FIN);
                        try {
                            jj_consume_token(PARENTESIS_APERTURA);
                            jj_consume_token(PARENTESIS_CIERRE);
                            try {
                                jj_consume_token(0);
                                return true;
                            } catch (ParseException ex) {
                                registraErrorSintactico("Error sintactico, no puede haber nada fuera del programa", ex.currentToken.next);
                            }
                        } catch (ParseException ex) {
                            registraErrorSintactico("Error sintactico, faltan parentesis.", ex.currentToken.next);
                        }
                    } catch (ParseException ex) {
                        registraErrorSintactico("Error sintactico, problema en la funcion principal", ex.currentToken.next);
                    }
                } catch (ParseException ex) {
                    registraErrorSintactico("Error sintactico, problema en la funcion principal", ex.currentToken.next);
                }
            } catch (ParseException ex) {
                registraErrorSintactico("Error sintactico, faltan parentesis.", ex.currentToken.next);
            }
        } catch (ParseException ex) {
            registraErrorSintactico("Error sintactico, falta funcion principal.", ex.currentToken.next);
        }
        return false;
    }

    /*
     Este método permite convocar cualquiera de las estrucutras creadas
     */
    final public void estructuraDelPrograma() throws ParseException {
        label_1:
        while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case ESCRIBIR:
                case IMPRIMIR:
                case SI:
                case CAMBIO:
                case MIENTRAS:
                case HACER:
                case PARA:
                case FUNCION:
                case NUMERO:
                case FLOTANTE:
                case CARACTER:
                case CADENA:
                case VACIO:
                case BOOLEANO:
        ;
                    break;
                default:
                    jj_la1[0] = jj_gen;
                    break label_1;
            }
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case NUMERO:
                case FLOTANTE:
                case CARACTER:
                case CADENA:
                case VACIO:
                case BOOLEANO:
                    crearVariable();
                    break;
                case FUNCION:
                    crearFuncion();
                    break;
                case SI:
                    estructuraIf();
                    break;
                case HACER:
                    estructuraDoWhile();
                    break;
                case MIENTRAS:
                    estructuraWhile();
                    break;
                case CAMBIO:
                    estructuraSwitch();
                    break;
                case PARA:
                    estructuraFor();
                    break;
                case IMPRIMIR:
                    impresion();
                    jj_consume_token(DELIMITADOR);
                    break;
                case ESCRIBIR:
                    lectura();
                    jj_consume_token(DELIMITADOR);
                    break;
                default:
                    jj_la1[1] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        }
    }

    /*
     Esta gramática permite la creación de una variable
     */
    final public void crearVariable() throws ParseException {
        crearVariableSencilla();
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case ASIGNACION:
                asignacion();
                break;
            default:
                jj_la1[2] = jj_gen;
                ;
        }
        jj_consume_token(DELIMITADOR);
    }

    /*
     Esta gramática solo sirve para la declaración de un variable.
     */
    final public void crearVariableSencilla() throws ParseException {
        tokensTiposDatos();
        jj_consume_token(IDENTIFICADOR);
    }

    /*
     Esta gramática permite la asignación de una variable a distintas posibilidades
     */
    final public void asignacion() throws ParseException {
        jj_consume_token(ASIGNACION);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case VALOR_ENTERO:
            case VALOR_FLOTANTE:
            case VALOR_CADENA:
            case VALOR_CARACTER:
            case VALOR_VERDADERO:
            case VALOR_FALSO:
                tipoDatoExpresionRegular();
                break;
            case ESCRIBIR:
                lectura();
                break;
            case IDENTIFICADOR:
                jj_consume_token(IDENTIFICADOR);
                break;
            default:
                jj_la1[3] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case SUMA:
            case RESTA:
            case MULTIPLICACION:
            case DIVISION:
            case MODULO:
                operacionMatematica();
                break;
            default:
                jj_la1[4] = jj_gen;
                ;
        }
    }

    /*
     Esta función devuelve los valors posibles de los tipos de datos
     */
    final public void tipoDatoExpresionRegular() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case VALOR_ENTERO:
                jj_consume_token(VALOR_ENTERO);
                break;
            case VALOR_FLOTANTE:
                jj_consume_token(VALOR_FLOTANTE);
                break;
            case VALOR_CADENA:
                jj_consume_token(VALOR_CADENA);
                break;
            case VALOR_CARACTER:
                jj_consume_token(VALOR_CARACTER);
                break;
            case VALOR_VERDADERO:
                jj_consume_token(VALOR_VERDADERO);
                break;
            case VALOR_FALSO:
                jj_consume_token(VALOR_FALSO);
                break;
            default:
                jj_la1[5] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Esta gramática permite la realización de operaciones matemáticas
     */
    final public void operacionMatematica() throws ParseException {
        label_2:
        while (true) {
            operadoresAritmeticos();
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case VALOR_ENTERO:
                case VALOR_FLOTANTE:
                case VALOR_CADENA:
                case VALOR_CARACTER:
                case VALOR_VERDADERO:
                case VALOR_FALSO:
                    tipoDatoExpresionRegular();
                    break;
                case IDENTIFICADOR:
                    jj_consume_token(IDENTIFICADOR);
                    break;
                default:
                    jj_la1[6] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case SUMA:
                case RESTA:
                case MULTIPLICACION:
                case DIVISION:
                case MODULO:
        ;
                    break;
                default:
                    jj_la1[7] = jj_gen;
                    break label_2;
            }
        }
    }

    /*
     Esta función regresa los posibles operadores matemáticos a usarse
     */
    final public void operadoresAritmeticos() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case SUMA:
                jj_consume_token(SUMA);
                break;
            case RESTA:
                jj_consume_token(RESTA);
                break;
            case MULTIPLICACION:
                jj_consume_token(MULTIPLICACION);
                break;
            case DIVISION:
                jj_consume_token(DIVISION);
                break;
            case MODULO:
                jj_consume_token(MODULO);
                break;
            default:
                jj_la1[8] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Esta gramática verifica la correcta declaracion de un switch case
     */
    final public void estructuraSwitch() throws ParseException {
        jj_consume_token(CAMBIO);
        jj_consume_token(PARENTESIS_APERTURA);
        jj_consume_token(IDENTIFICADOR);
        jj_consume_token(PARENTESIS_CIERRE);
        jj_consume_token(LLAVE_APERTURA);
        casos();
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case DEFECTO:
                predeterminado();
                break;
            default:
                jj_la1[9] = jj_gen;
                ;
        }
        jj_consume_token(LLAVE_CIERRE);
    }

    /*
     Esta funcion se asegura de la declaración de los casos dentro de un switch case
     */
    final public void casos() throws ParseException {
        label_3:
        while (true) {
            jj_consume_token(OPCION);
            tipoDatoExpresionRegular();
            jj_consume_token(DOS_PUNTOS);
            cuerpo();
            jj_consume_token(ROMPER);
            jj_consume_token(DELIMITADOR);
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case OPCION:
        ;
                    break;
                default:
                    jj_la1[10] = jj_gen;
                    break label_3;
            }
        }
    }

    /*
     Esta función se asegura de la correcta declaración de un valor default dentro de un switch case, puede o no venir
     */
    final public void predeterminado() throws ParseException {
        jj_consume_token(DEFECTO);
        jj_consume_token(DOS_PUNTOS);
        cuerpo();
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case ROMPER:
                jj_consume_token(ROMPER);
                jj_consume_token(DELIMITADOR);
                break;
            default:
                jj_la1[11] = jj_gen;
                ;
        }
    }

    /*
     Esta gramática corresponde a un if, con un else que puede o no venir
     */
    final public void estructuraIf() throws ParseException {
        jj_consume_token(SI);
        jj_consume_token(PARENTESIS_APERTURA);
        jj_consume_token(IDENTIFICADOR);
        condiciones();
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case OR:
            case AND:
                condicionesAdicionales();
                break;
            default:
                jj_la1[12] = jj_gen;
                ;
        }
        jj_consume_token(PARENTESIS_CIERRE);
        jj_consume_token(LLAVE_APERTURA);
        cuerpo();
        jj_consume_token(LLAVE_CIERRE);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case SINO:
                estructuraElse();
                break;
            default:
                jj_la1[13] = jj_gen;
                ;
        }
    }

    /*
     Gramática del else, debe invocarse después de un if
     */
    final public void estructuraElse() throws ParseException {
        jj_consume_token(SINO);
        jj_consume_token(LLAVE_APERTURA);
        cuerpo();
        jj_consume_token(LLAVE_CIERRE);
    }

    /*
     Esta función establece los tipos de condiciones permitidas
     */
    final public void condiciones() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case IGUAL_A:
            case DIFERENTE:
                condicionBooleana();
                break;
            case MENOR_IGUAL:
            case MAYOR_IGUAL:
            case MENOR:
            case MAYOR:
                condicionAritmetica();
                break;
            default:
                jj_la1[14] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Esta funcion permite tener más de una condición
     */
    final public void condicionesAdicionales() throws ParseException {
        label_4:
        while (true) {
            tokensOperadoresLogicos();
            jj_consume_token(IDENTIFICADOR);
            condiciones();
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case OR:
                case AND:
        ;
                    break;
                default:
                    jj_la1[15] = jj_gen;
                    break label_4;
            }
        }
    }

    /*
     Esta función devuelve los valores lógicos para condiciones
     */
    final public void tokensOperadoresLogicos() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case AND:
                jj_consume_token(AND);
                break;
            case OR:
                jj_consume_token(OR);
                break;
            default:
                jj_la1[16] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Esta gramática establece una comparación aritmética
     */
    final public void condicionAritmetica() throws ParseException {
        comparacionesIf();
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case IDENTIFICADOR:
                jj_consume_token(IDENTIFICADOR);
                break;
            case VALOR_ENTERO:
                jj_consume_token(VALOR_ENTERO);
                break;
            default:
                jj_la1[17] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Este funcion establece los comparadores permitidos en un if
     */
    final public void comparacionesIf() throws ParseException {
        comparacionesFor();
    }

    /*
     Esta funcion establece los comparadores permitidos en un for
     */
    final public void comparacionesFor() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case MENOR:
                jj_consume_token(MENOR);
                break;
            case MENOR_IGUAL:
                jj_consume_token(MENOR_IGUAL);
                break;
            case MAYOR:
                jj_consume_token(MAYOR);
                break;
            case MAYOR_IGUAL:
                jj_consume_token(MAYOR_IGUAL);
                break;
            default:
                jj_la1[18] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Esta funcion corresponde a las comparaciones de valores booleanos
     */
    final public void condicionBooleana() throws ParseException {
        diferenteIgual();
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case VALOR_VERDADERO:
                jj_consume_token(VALOR_VERDADERO);
                break;
            case VALOR_FALSO:
                jj_consume_token(VALOR_FALSO);
                break;
            default:
                jj_la1[19] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Esta función devuelve los posibles comparadores para una comparación booleana
     */
    final public void diferenteIgual() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case IGUAL_A:
                jj_consume_token(IGUAL_A);
                break;
            case DIFERENTE:
                jj_consume_token(DIFERENTE);
                break;
            default:
                jj_la1[20] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Esta gramática verifica la declaración de un ciclo while
     */
    final public void estructuraWhile() throws ParseException {
        jj_consume_token(MIENTRAS);
        jj_consume_token(PARENTESIS_APERTURA);
        jj_consume_token(IDENTIFICADOR);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case IGUAL_A:
            case DIFERENTE:
                condicionBooleana();
                break;
            case MENOR_IGUAL:
            case MAYOR_IGUAL:
            case MENOR:
            case MAYOR:
                condicionAritmetica();
                break;
            default:
                jj_la1[21] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case OR:
            case AND:
                condicionesAdicionales();
                break;
            default:
                jj_la1[22] = jj_gen;
                ;
        }
        jj_consume_token(PARENTESIS_CIERRE);
        jj_consume_token(LLAVE_APERTURA);
        cuerpo();
        jj_consume_token(LLAVE_CIERRE);
    }

    /*
     Esta gramática verifica la correcta declaración de un do while
     */
    final public void estructuraDoWhile() throws ParseException {
        jj_consume_token(HACER);
        jj_consume_token(LLAVE_APERTURA);
        cuerpo();
        jj_consume_token(LLAVE_CIERRE);
        jj_consume_token(MIENTRAS);
        jj_consume_token(PARENTESIS_APERTURA);
        jj_consume_token(IDENTIFICADOR);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case IGUAL_A:
            case DIFERENTE:
                condicionBooleana();
                break;
            case MENOR_IGUAL:
            case MAYOR_IGUAL:
            case MENOR:
            case MAYOR:
                condicionAritmetica();
                break;
            default:
                jj_la1[23] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case OR:
            case AND:
                condicionesAdicionales();
                break;
            default:
                jj_la1[24] = jj_gen;
                ;
        }
        jj_consume_token(PARENTESIS_CIERRE);
    }

    /*
     Esta gramática verifica la correcta declaración de un for
     */
    final public void estructuraFor() throws ParseException {
        jj_consume_token(PARA);
        jj_consume_token(PARENTESIS_APERTURA);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case NUMERO:
            case FLOTANTE:
            case CARACTER:
            case CADENA:
            case VACIO:
            case BOOLEANO:
                tokensTiposDatos();
                break;
            default:
                jj_la1[25] = jj_gen;
                ;
        }
        jj_consume_token(IDENTIFICADOR);
        jj_consume_token(ASIGNACION);
        jj_consume_token(VALOR_ENTERO);
        jj_consume_token(DELIMITADOR);
        jj_consume_token(IDENTIFICADOR);
        comparacionesFor();
        jj_consume_token(VALOR_ENTERO);
        jj_consume_token(DELIMITADOR);
        jj_consume_token(IDENTIFICADOR);
        asignacionesFor();
        jj_consume_token(PARENTESIS_CIERRE);
        jj_consume_token(LLAVE_APERTURA);
        cuerpo();
        jj_consume_token(LLAVE_CIERRE);
    }

    /*
     Esta función apoya a las asignaciones de los for
     */
    final public void asignacionesFor() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case SUMA_IGUAL:
            case RESTA_IGUAL:
            case MULTIPLICACION_IGUAL:
            case DIVISION_IGUAL:
            case MODULO_IGUAL:
                asignacionVariablesFor();
                break;
            case INCREMENTO:
                jj_consume_token(INCREMENTO);
                break;
            case DECREMENTO:
                jj_consume_token(DECREMENTO);
                break;
            default:
                jj_la1[26] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Permite operaciones dentro de un for
     */
    final public void asignacionVariablesFor() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case SUMA_IGUAL:
                jj_consume_token(SUMA_IGUAL);
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case IDENTIFICADOR:
                        jj_consume_token(IDENTIFICADOR);
                        break;
                    case VALOR_ENTERO:
                        jj_consume_token(VALOR_ENTERO);
                        break;
                    default:
                        jj_la1[27] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                break;
            case RESTA_IGUAL:
                jj_consume_token(RESTA_IGUAL);
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case IDENTIFICADOR:
                        jj_consume_token(IDENTIFICADOR);
                        break;
                    case VALOR_ENTERO:
                        jj_consume_token(VALOR_ENTERO);
                        break;
                    default:
                        jj_la1[28] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                break;
            case MULTIPLICACION_IGUAL:
                jj_consume_token(MULTIPLICACION_IGUAL);
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case IDENTIFICADOR:
                        jj_consume_token(IDENTIFICADOR);
                        break;
                    case VALOR_ENTERO:
                        jj_consume_token(VALOR_ENTERO);
                        break;
                    default:
                        jj_la1[29] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                break;
            case DIVISION_IGUAL:
                jj_consume_token(DIVISION_IGUAL);
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case IDENTIFICADOR:
                        jj_consume_token(IDENTIFICADOR);
                        break;
                    case VALOR_ENTERO:
                        jj_consume_token(VALOR_ENTERO);
                        break;
                    default:
                        jj_la1[30] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                break;
            case MODULO_IGUAL:
                jj_consume_token(MODULO_IGUAL);
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                    case IDENTIFICADOR:
                        jj_consume_token(IDENTIFICADOR);
                        break;
                    case VALOR_ENTERO:
                        jj_consume_token(VALOR_ENTERO);
                        break;
                    default:
                        jj_la1[31] = jj_gen;
                        jj_consume_token(-1);
                        throw new ParseException();
                }
                break;
            default:
                jj_la1[32] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /*
     Este método maneja la gramática para la creación de funciones, con o sin parámetros
     */
    final public void crearFuncion() throws ParseException {
        jj_consume_token(FUNCION);
        jj_consume_token(IDENTIFICADOR);
        jj_consume_token(PARENTESIS_APERTURA);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case NUMERO:
            case FLOTANTE:
            case CARACTER:
            case CADENA:
            case VACIO:
            case BOOLEANO:
                parametro();
                break;
            default:
                jj_la1[33] = jj_gen;
                ;
        }
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case COMA:
                parametros();
                break;
            default:
                jj_la1[34] = jj_gen;
                ;
        }
        jj_consume_token(PARENTESIS_CIERRE);
        jj_consume_token(LLAVE_APERTURA);
        cuerpo();
        jj_consume_token(LLAVE_CIERRE);
    }

    /*
     Esta función será llamada en caso de que una gramatica de función contenga un parámetro
     */
    final public void parametro() throws ParseException {
        tokensTiposDatos();
        jj_consume_token(IDENTIFICADOR);
    }

    /*
     Esta función será llamada en caso de que una función contenga más de un parámetro
     */
    final public void parametros() throws ParseException {
        label_5:
        while (true) {
            jj_consume_token(COMA);
            tokensTiposDatos();
            jj_consume_token(IDENTIFICADOR);
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case COMA:
        ;
                    break;
                default:
                    jj_la1[35] = jj_gen;
                    break label_5;
            }
        }
    }

    /*
     Esta gramática sirve para identificar las estrucutras permitidas dentro de una función, ciclo, etcétera.
     */
    final public void cuerpo() throws ParseException {
        label_6:
        while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case ESCRIBIR:
                case IMPRIMIR:
                case SI:
                case MIENTRAS:
                case HACER:
                case PARA:
                case NUMERO:
                case FLOTANTE:
                case CARACTER:
                case CADENA:
                case VACIO:
                case BOOLEANO:
                case IDENTIFICADOR:
        ;
                    break;
                default:
                    jj_la1[36] = jj_gen;
                    break label_6;
            }
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case NUMERO:
                case FLOTANTE:
                case CARACTER:
                case CADENA:
                case VACIO:
                case BOOLEANO:
                    crearVariable();
                    break;
                case IDENTIFICADOR:
                    operacionBasica();
                    break;
                case SI:
                    estructuraIf();
                    break;
                case PARA:
                    estructuraFor();
                    break;
                case MIENTRAS:
                    estructuraWhile();
                    break;
                case HACER:
                    estructuraDoWhile();
                    break;
                case ESCRIBIR:
                    lectura();
                    jj_consume_token(DELIMITADOR);
                    break;
                case IMPRIMIR:
                    impresion();
                    jj_consume_token(DELIMITADOR);
                    break;
                default:
                    jj_la1[37] = jj_gen;
                    jj_consume_token(-1);
                    throw new ParseException();
            }
        }
    }

    final public void operacionBasica() throws ParseException {
        jj_consume_token(IDENTIFICADOR);
        asignacionesFor();
        jj_consume_token(DELIMITADOR);
    }

    /*
     Gramática que permite la impresión de datos en pantalla
     */
    final public void impresion() throws ParseException {
        jj_consume_token(IMPRIMIR);
        jj_consume_token(PARENTESIS_APERTURA);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case VALOR_ENTERO:
            case VALOR_FLOTANTE:
            case VALOR_CADENA:
            case VALOR_CARACTER:
            case VALOR_VERDADERO:
            case VALOR_FALSO:
                tipoDatoExpresionRegular();
                break;
            case IDENTIFICADOR:
                jj_consume_token(IDENTIFICADOR);
                break;
            default:
                jj_la1[38] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
        jj_consume_token(PARENTESIS_CIERRE);
    }

    /*
     Gramática que permite la lectura de datos
     */
    final public void lectura() throws ParseException {
        jj_consume_token(ESCRIBIR);
        jj_consume_token(PARENTESIS_APERTURA);
        tipoDatoExpresionRegular();
        jj_consume_token(PARENTESIS_CIERRE);
    }

    /*
     Esta función regresa los posibles tipos de datos
     */
    final public void tokensTiposDatos() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case NUMERO:
                jj_consume_token(NUMERO);
                break;
            case FLOTANTE:
                jj_consume_token(FLOTANTE);
                break;
            case CARACTER:
                jj_consume_token(CARACTER);
                break;
            case CADENA:
                jj_consume_token(CADENA);
                break;
            case VACIO:
                jj_consume_token(VACIO);
                break;
            case BOOLEANO:
                jj_consume_token(BOOLEANO);
                break;
            default:
                jj_la1[39] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
        }
    }

    /**
     * Generated Token Manager.
     */
    public LPBETokenManager token_source;
    SimpleCharStream jj_input_stream;
    /**
     * Current token.
     */
    public Token token;
    /**
     * Next token.
     */
    public Token jj_nt;
    private int jj_ntk;
    private int jj_gen;
    final private int[] jj_la1 = new int[40];
    static private int[] jj_la1_0;
    static private int[] jj_la1_1;

    static {
        jj_la1_init_0();
        jj_la1_init_1();
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[]{0x3ff0b8, 0x3ff0b8, 0x400000, 0x8, 0xf800000, 0x0, 0x0, 0xf800000, 0xf800000, 0x400, 0x100, 0x200, 0x0, 0x40, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x3f0000, 0xf0000000, 0x0, 0x0, 0x0, 0x0, 0x0, 0xc0000000, 0x3f0000, 0x0, 0x0, 0x3f7038, 0x3f7038, 0x0, 0x3f0000,};
    }

    private static void jj_la1_init_1() {
        jj_la1_1 = new int[]{0x0, 0x0, 0x0, 0x3f800, 0x0, 0x1f800, 0x3f800, 0x0, 0x0, 0x0, 0x0, 0x0, 0x600, 0x0, 0x1f8, 0x600, 0x600, 0x20800, 0xf0, 0x18000, 0x108, 0x1f8, 0x600, 0x1f8, 0x600, 0x0, 0x7, 0x20800, 0x20800, 0x20800, 0x20800, 0x20800, 0x7, 0x0, 0x2000000, 0x2000000, 0x20000, 0x20000, 0x3f800, 0x0,};
    }

    /**
     * Constructor with InputStream.
     */
    public LPBE(java.io.InputStream stream) {
        this(stream, null);
    }

    /**
     * Constructor with InputStream and supplied encoding
     */
    public LPBE(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new LPBETokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 40; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 40; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Constructor.
     */
    public LPBE(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new LPBETokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 40; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 40; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Constructor with generated Token Manager.
     */
    public LPBE(LPBETokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 40; i++) {
            jj_la1[i] = -1;
        }
    }

    /**
     * Reinitialise.
     */
    public void ReInit(LPBETokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 40; i++) {
            jj_la1[i] = -1;
        }
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }

    /**
     * Get the next Token.
     */
    final public Token getNextToken() {
        if (token.next != null) {
            token = token.next;
        } else {
            token = token.next = token_source.getNextToken();
        }
        jj_ntk = -1;
        jj_gen++;
        return token;
    }

    /**
     * Get the specific Token.
     */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null) {
                t = t.next;
            } else {
                t = t.next = token_source.getNextToken();
            }
        }
        return t;
    }

    private int jj_ntk() {
        if ((jj_nt = token.next) == null) {
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        } else {
            return (jj_ntk = jj_nt.kind);
        }
    }

    private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;

    /**
     * Generate ParseException.
     */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[62];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 40; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                    if ((jj_la1_1[i] & (1 << j)) != 0) {
                        la1tokens[32 + j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 62; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = jj_expentries.get(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    /**
     * Enable tracing.
     */
    final public void enable_tracing() {
    }

    /**
     * Disable tracing.
     */
    final public void disable_tracing() {
    }

}
