package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ConvertisseurUtil {

    /** #####################
     *    Convertisseurs
     *  #####################
     **/

    //Ecrire les attributs type java en type cpp. Stockés dans un tableau de String.
    public static String[] convertisseurAttributs(Class classe){

        Field[] fields = classe.getFields();
        String[] attributCPP = new String[fields.length];
        for (int i=0 ; i< fields.length; i++){
            String type = fields[i].getType().toString();
            attributCPP[i]= conversion(section(type)) + " " + fields[i].getName() +";";
        }
        return attributCPP;
    }

    //Ecrire les méthodes type java en type cpp. Stockés dans un tableau de String.
    public static String[] convertisseurMethodes(Class classe){

        Method[] methods = classe.getDeclaredMethods();
        String[] methodsCPP = new String[methods.length];
        for (int i=0 ; i< methods.length; i++){
            //Récupération type de retour de la methode
            String returnType = conversion(section(methods[i].getReturnType().getName()));


            //Récupération nom de la methode
            String[] division;
            String name = methods[i].getName();
            division = name.split("\\.");
            name = division[division.length-1];

            //Récupération paramètres de la methode
            StringBuilder paramsList = new StringBuilder();
            int cpt = 0;
            for(Class<?> paramType : methods[i].getParameterTypes()){
                paramsList.append(conversion(section(paramType.toString())));
                cpt++;
                if(!(cpt==methods[i].getParameterTypes().length)){
                    paramsList.append(", ");
                }
            }
            methodsCPP[i] = returnType + " " +  name + "(" + paramsList + ");";
        }
        return methodsCPP;
    }

    //Ecrire les constructeurs type java en type cpp. Stockés dans un tableau de String.
    public static String[] convertisseurConstructeurs(Class classe, String cppClass){

        Constructor<?>[] constructors = classe.getConstructors();
        String[] constructorsCPP = new String[constructors.length];
        for (int i=0 ; i< constructors.length; i++){

            //Récupération paramètres de la methode
            StringBuilder paramsList = new StringBuilder();
            int cpt = 0;
            for(Class<?> paramType : constructors[i].getParameterTypes()){
                paramsList.append(conversion(section(paramType.toString())));
                cpt++;
                if(cpt!=constructors[i].getParameterTypes().length){
                    paramsList.append(", ");
                }
            }
            constructorsCPP[i] = cppClass + "(" + paramsList + ");";
        }
        return constructorsCPP;
    }

    //Ecrire les enum type java en type cpp. Stockés dans un tableau de String.
    public static String[] convertisseurEnum(Class classe){

        Class<?>[] classes = classe.getDeclaredClasses();
        String[] enumsCPP = new String[classes.length];
        for (int i=0 ; i< classes.length; i++){
            if(classes[i].isEnum()){
                //Récupération des constantes de l'enum
                Object[] enumClass = classes[i].getEnumConstants();
                StringBuilder constants = new StringBuilder();
                int cpt = 0;
                for(Object enumConstant : enumClass){
                    constants.append(enumConstant);
                    cpt++;
                    if(cpt!=enumClass.length){
                        constants.append(", ");
                    }
                }
                enumsCPP[i] = "enum " + section(classes[i].getName()) + "{ " + constants + " };";
            }

        }
        return enumsCPP;
    }


    /** #########################
     *    Ecriture fichier .hpp
     *  #########################
     **/

    public static File writeHpp(Class classe, String cppClasse) throws IOException {

        File hpp = new File(cppClasse + ".hpp");
        FileWriter fw = new FileWriter(hpp);
        BufferedWriter bw = new BufferedWriter(fw);

        //Ecriture des gardiens
        bw.write("#ifndef _"+ cppClasse.toUpperCase() +"_HPP_\n" +
                "#define _"+ cppClasse.toUpperCase() +"_HPP_\n\n");

        //Ecriture des includes
        bw.write("#include <iostream>\n\n");

        //Ecriture de la structure de la classe
        bw.write("class " + cppClasse + "{\n\n");

        //Ecriture protection
        bw.write("  private:\n");

        //Ecrture des enums
        for(String enumm : convertisseurEnum(classe)){
            bw.write("      " + enumm + "\n\n");
        }

        //Ecrture des attributs
        for(String attribut : convertisseurAttributs(classe)){
            bw.write("      " + attribut + "\n");
        }

        //Ecriture protection
        bw.write("\n" + "  public:\n");

        //Ecrture des constructeurs
        for(String constructor : convertisseurConstructeurs(classe, cppClasse)){
            bw.write("      " + constructor + "\n");
        }

        //Ecrture des méthodes
        for(String method : convertisseurMethodes(classe)){
            bw.write("      " + method + "\n");
        }

        //Fin de classe et du fichier
        bw.write("};" + "\n\n" + "#endif");

        bw.close();
        return hpp;

    }




    /** #####################
     *         Tools
     *  #####################
     **/

    //Permet de récupérer uniquement la partie qui nous interesse se trouvant à la fin de l'expression
    private static String section(String stringToDivide){

        String[] division;

        if(!stringToDivide.contains("$")){
            division = stringToDivide.split("\\.");
        } else {
            division = stringToDivide.split("\\$");
        }
        return division[division.length-1];
    }

    //Permet de passer du type java au type cpp
    public static String conversion(String stringToConvert){

        switch (stringToConvert){
            case "String" :
                stringToConvert = "std::ostream";
                break;
            case "Integer" :
                stringToConvert = "int";
                break;
        }
        return stringToConvert;
    }

}
