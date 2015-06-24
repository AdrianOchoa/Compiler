/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import views.Pane;

/**
 *
 * @author Adrián
 */
public class SemanticAnalyzer {

    private final ArrayList<String> dataTypes;
    private final ArrayList<String> operators;
    private final ArrayList<String> reservedWords;
    private String code;
    private boolean [] positions;

    /**
     * Constructs a new Object to be analyzed, with a specic set of data types
     * and operators
     *
     * @param code to be analyzed
     * @param dataTypes the data types of the specified languague
     * @param operators the operators of the specified languague
     * @param reservedWords the reserved words of the specified languague
     */
    public SemanticAnalyzer(String code, ArrayList<String> dataTypes,
            ArrayList<String> operators, ArrayList<String> reservedWords) {
        this.code = code;
        this.dataTypes = dataTypes;
        this.operators = operators;
        this.reservedWords = reservedWords;
    }

    /**
     * Constructs a new Object to be analyzed
     *
     * @param code to be analyzed
     */
    public SemanticAnalyzer(String code) {
        this(code, getDataTypes(), getOperators(), getReservedWords());
    }

    /**
     * this method performs a semantic analysis
     *
     * @param pane the pane to be set the messages
     */
    public void runSemanticAnalysis(Pane pane) {
        processCode();
        String[] tokens = code.split("[ ]+");
        positions = new boolean[tokens.length];
        Arrays.fill(positions, true);
        StringBuilder messages = new StringBuilder();
        HashMap<String, Identifier> globalVariables = new HashMap();
        HashMap<String, Function> functions = new HashMap();
        HashMap<String, Identifier> parameters = new HashMap();
        HashMap<String, Identifier> localVariables = new HashMap();
//        addVariables(tokens, messages, globalVariables);
        addFunctions(tokens, messages, globalVariables,
                parameters, localVariables, functions);
        addVariables(tokens, messages, globalVariables);
        addMessages(pane, messages);
    }

    /**
     * this method adds all the global variables from a code to a specified
     * HashMap
     *
     * @param tokens the array of tokens
     * @param messages a StringBuilder which will be added the messages
     * @param variables a HashMap that will contain all the global variables
     * from a code
     */
    private void addVariables(String[] tokens,
            StringBuilder messages, HashMap<String, Identifier> variables) {
        int endVariablesDeclarations;
        messages.append("Variables encontradas:\n");
        for (int i = 0; i < tokens.length; i++) {
            if (dataTypes.contains(tokens[i].trim()) && positions[i]) {
//                switch (tokens[i - 1].trim()) {
//                    case "(":
//                        continue;
////                    case "{":
////                        if (!tokens[i - 4].equals("INICIO")) {
////                            continue;
////                        }
////                        break;
//                    case ",":
//                        continue;
//                }
                String type = tokens[i].trim();
                boolean flag = true;
                endVariablesDeclarations = findEndVariablesDeclarations(i, tokens);
                for (int j = i; j < endVariablesDeclarations; j++) {
                    if (tokens[j].trim().equals(",")) {
                        flag = true;
                    }
                    if (flag) {
                        String identifier = tokens[j + 1].trim();
                        Identifier id = new Identifier(type, identifier);
                        if (!variables.containsKey(id.getName())) {
                            messages.append("Info: Variable global ")
                                    .append(id.getName())
                                    .append(" de tipo ")
                                    .append(id.getType())
                                    .append(" agregada")
                                    .append("\n");
                            variables.put(identifier, id);
                        } else {
                            messages.append("Error: Variable global ")
                                    .append(id.getName())
                                    .append(" ya contenida de tipo ")
                                    .append(variables.get(id.getName()).getType())
                                    .append("\n");
                        }
                        flag = false;
                    }
                }
                i = endVariablesDeclarations;
            }
        }
    }

    /**
     * this method adds function to a specified HashMap
     *
     * @param tokens the array of tokens
     * @param messages a StringBuilder which will be added the messages
     * @param variables a HashMap which contains the global variables of a code
     * @param parameters a HashMap that will contain the paramters of a function
     * @param localVariables a HashMap that will contain the local variables of
     * a funtion
     * @param functions a HashMap that will contain all the function from a code
     */
    private void addFunctions(String[] tokens,
            StringBuilder messages,
            HashMap<String, Identifier> variables,
            HashMap<String, Identifier> parameters,
            HashMap<String, Identifier> localVariables,
            HashMap<String, Function> functions) {
        int endStatements, endParameters, parametersStart;
        messages.append("Funciones encontradas:\n");
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].trim().equals("FUNCION")) {
                String functionName = tokens[i + 1];
                parametersStart = i + 2;
                endParameters = findParametersEnd(i, tokens);
                System.out.println("Definicion Funcion");
                endStatements = findStatementsEnd(endParameters + 1, tokens);
                addParameters(parametersStart, endParameters, tokens, parameters, messages, functionName);
                for (int j = endParameters; j < endStatements; j++) {
                    if (dataTypes.contains(tokens[j].trim())) {
                        addLocalVariables(tokens, j,
                                localVariables, parameters, messages, messages,
                                variables, functionName);
                    }
                }
                Function funcion = new Function("FUNCION", functionName);
                funcion.setDefined(true);
                funcion.setParameters(parameters);
                funcion.setLocalVariables(localVariables);
                HashMap<String, Identifier> auxParameters = funcion.getParameters();
                String functionParameters = "";
                functionParameters
                        = getFunctionParameters(auxParameters, functionParameters);
                if (!functions.containsKey(funcion.getIdentifier())) {
                    messages.append("Info: Funcion ")
                            .append(funcion.getIdentifier())
                            .append(" agregada con parámetros ")
                            .append(functionParameters)
                            .append("\n");
                    functions.put(funcion.getIdentifier(), funcion);
                } else {
                    HashMap<String, Identifier> auxParameters2
                            = functions.get(funcion.getIdentifier()).getParameters();
                    String parameters2 = "";
                    parameters2 = getFunctionParameters(auxParameters2, parameters2);
                    if (!functionParameters.equals(parameters2)) {
                        messages.append("Info: Funcion ")
                                .append(funcion.getIdentifier())
                                .append(" agregada con parámetros ")
                                .append(functionParameters)
                                .append("\n");
                        functions.put(funcion.getIdentifier(), funcion);
                    } else if (!functions.get(funcion.getIdentifier()).isDefined()) {
                        functions.get(funcion.getIdentifier()).setDefined(true);
                        messages.append("Funcion definida (previamente declarada) ")
                                .append(funcion.getIdentifier())
                                .append(" agregada con parámetros ")
                                .append(functionParameters)
                                .append("\n");
                        functions.put(funcion.getIdentifier(), funcion);
                    } else {
                        messages.append("Error: Funcion ")
                                .append(funcion.getIdentifier())
                                .append(" ya existente con argumentos ")
                                .append(functionParameters)
                                .append("\n");
                    }
                }
                int aux = i;
                i = endStatements;
                Arrays.fill(positions, aux, i, false);
            }
        }
    }

    /**
     * this method will add local variables to a function
     *
     * @param tokens the array of tokens
     * @param j the index to start the search
     * @param localVariables a HashMap which will contain the local variables of
     * a function
     * @param parameters a HashMap which will contain the parameters of a
     * function
     * @param accepted a StringBuilder which will be added the accepted messages
     * @param errores a StringBuilder which will be added the rejected messages
     * @param variables a HashMap which contains the global variables of a code
     * @param functionName the name of the function
     */
    private void addLocalVariables(String[] tokens, int j,
            HashMap<String, Identifier> localVariables,
            HashMap<String, Identifier> parameters,
            StringBuilder accepted, StringBuilder errores,
            HashMap<String, Identifier> variables,
            String functionName) {
        localVariables.clear();
        int endLocaleVariablesDefinition;
        String localType = tokens[j].trim();
        boolean flag = true;
        endLocaleVariablesDefinition = findEndVariablesDeclarations(j, tokens);
        for (int k = j; k < endLocaleVariablesDefinition; k++) {
            if (tokens[k].trim().equals(",")) {
                flag = true;
            }
            if (flag) {
                String identifier = tokens[k + 1].trim();
                Identifier id = new Identifier(localType, identifier);
                boolean content = false;
                for (Map.Entry e : parameters.entrySet()) {
                    Identifier idAux = (Identifier) e.getValue();
                    if (idAux.getName().equals(id.getName())) {
                        content = true;
                    }
                }
                if (!localVariables.containsKey(id.getName()) && !content) {
                    if (variables.containsKey(id.getName()) && !content) {
                        accepted.append("Warning: En funcion ")
                                .append(functionName)
                                .append(". Variable Local ")
                                .append(id.getName())
                                .append(" de tipo ")
                                .append(id.getType())
                                .append(" agregada, pero concide con una variable global")
                                .append("\n");
                        localVariables.put(identifier, id);
                    } else {
                        accepted.append("Info: En funcion ")
                                .append(functionName)
                                .append(". Variable Local ")
                                .append(id.getName())
                                .append(" de tipo ")
                                .append(id.getType())
                                .append(" agregada")
                                .append("\n");
                        localVariables.put(identifier, id);
                    }
                } else {
                    errores.append("Error: En funcion ")
                            .append(functionName)
                            .append(". Variable Local ")
                            .append(id.getName())
                            .append(" ya contenida")
                            .append("\n");
                }
                flag = false;
            }
        }
    }

    /**
     * this method will add to a String the type of parameters of a function
     *
     * @param auxParameters the parameters of a function previously declared
     * @param parameters a String which will contain the parameters of a
     * function
     * @return a String which contains the type of parameters of a function
     */
    private String getFunctionParameters(HashMap<String, Identifier> auxParameters, String parameters) {
        parameters = auxParameters.entrySet().stream().map((e) -> (Identifier) e.getValue())
                .map((id) -> id.getName() + " de tipo " + id.getType() + " ").reduce(parameters, String::concat);
        return parameters.trim();
    }

    /**
     * this method add the parameters of a function to an specified HashMap
     *
     * @param parametersStart the index of the start of the parameters
     * @param parametersEnd ths index of the end of the parameters
     * @param tokens the array of tokens
     * @param parameters a HashMap that will be added to the function parameters
     */
    private void addParameters(int parametersStart, int parametersEnd,
            String[] tokens, HashMap<String, Identifier> parameters, StringBuilder messages, String functionName) {
        parameters.clear();
        for (int j = parametersStart; j < parametersEnd; j++) {
            if (dataTypes.contains(tokens[j].trim())) {
                String type = tokens[j];
                String identifier = tokens[j + 1];
                Identifier id = new Identifier(type, identifier);
                if (!parameters.containsKey(id.getName())) {
                    parameters.put(id.getName(), id);
                } else {
                    messages.append("Parametro ya contenido en funcion ")
                            .append(functionName);
                }
            }
        }
    }

    /**
     * search in the array of tokens for the symbol ";"
     *
     * @param index the index to start the searching
     * @param tokens the array of tokens to search
     * @return the index, if exists, of the end of the variables declaration
     */
    private int findEndVariablesDeclarations(int index, String[] tokens) {
        for (int i = index; i < tokens.length; i++) {
            if (tokens[i].trim().equals(";")) {
                return i;
            }
        }
        return 0;
    }

    /**
     * search in the array of tokens for the symbol "}"
     *
     * @param index the index to start the searching
     * @param tokens the array of tokens to search
     * @return the index, if exists, of the end of the function
     */
    private int findStatementsEnd(int index, String[] tokens) {
        for (int i = index; i < tokens.length; i++) {
            if (tokens[i].trim().equals("}")) {
                return i;
            }
        }
        return 0;
    }

    /**
     * search in the array of tokens for the symbol ")"
     *
     * @param index the index to start the searching
     * @param tokens the array of tokens to search
     * @return the index, if exists, of the end of the parameters of a function
     */
    private int findParametersEnd(int index, String[] tokens) {
        for (int i = index; i < tokens.length; i++) {
            if (tokens[i].trim().equals(")")) {
                return i;
            }
        }
        return 0;
    }

    /**
     * this metod add the result of the analysis in the specified contanier
     *
     * @param pane the container to be set the messages
     * @param messages the result of the semantic analysis
     */
    private void addMessages(Pane pane, StringBuilder messages) {
        pane.getJpResultsArea().setText(pane.getJpResultsArea().getText()
                + "\nResultado del analisis semantico:\n" + messages.toString());
    }

    /**
     * this method process the code adding spaces between the tokens
     */
    private void processCode() {
        code = code.replaceAll("\n", " ");
        code = code.replaceAll("\\(", " ( ");
        code = code.replaceAll("\\)", " ) ");
        code = code.replaceAll(",", " , ");
        code = code.replaceAll(";", " ;");
        code = code.replaceAll("\\{", " { ");
        code = code.replaceAll("\\}", " } ");
        operators.stream().forEach((operator) -> {
            code = code.replaceAll(operator, " " + operator + " ");
        });
    }

    /**
     *
     * @return an ArrayList containing the reserved words of the languague
     */
    public static ArrayList<String> getReservedWords() {
        ArrayList<String> reserved = new ArrayList();
        reserved.add("INICIO");
        reserved.add("FIN");
        reserved.add("ESCRIBIR");
        reserved.add("IMPRIMIR");
        reserved.add("SI");
        reserved.add("SINO");
        reserved.add("CAMBIO");
        reserved.add("OPCION");
        reserved.add("ROMPER");
        reserved.add("DEFECTO");
        reserved.add("CONTINUAR");
        reserved.add("MIENTRAS");
        reserved.add("HACER");
        reserved.add("PARA");
        reserved.add("FUNCION");
        return reserved;
    }

    /**
     *
     * @return an ArrayList containig the basic data types of the language
     */
    public static ArrayList<String> getDataTypes() {
        ArrayList<String> basicDataTypes = new ArrayList();
        basicDataTypes.add("NUMERO");
        basicDataTypes.add("FLOTANTE");
        basicDataTypes.add("CARACTER");
        basicDataTypes.add("CADENA");
        basicDataTypes.add("BOOLEANO");
        basicDataTypes.add("VACIO");
        return basicDataTypes;
    }

    /**
     *
     * @return an ArrayList containing the operators of the language
     */
    private static ArrayList<String> getOperators() {
        ArrayList<String> basicOperators = new ArrayList();
        basicOperators.add("\\+");
        basicOperators.add("\\++");
        basicOperators.add("\\-");
        basicOperators.add("\\--");
        basicOperators.add("\\*");
        basicOperators.add("\\/");
        basicOperators.add("\\=");
        basicOperators.add("\\+=");
        basicOperators.add("\\-=");
        basicOperators.add("\\*=");
        basicOperators.add("\\/=");
        return basicOperators;
    }

}
