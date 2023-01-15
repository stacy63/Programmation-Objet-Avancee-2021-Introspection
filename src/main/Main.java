package main;

import utils.ConvertisseurUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] argv){

        try{
            Class classe = Class.forName("model."+argv[0]);
            String cppClasse;
            if(argv.length>1){
                cppClasse = argv[1];
            } else {
                cppClasse = argv[0];
            }
            System.out.println("################################");
            System.out.println("        Fichier hpp");
            System.out.println("################################\n");
            File hpp = ConvertisseurUtil.writeHpp(classe, cppClasse);
            FileReader fr = new FileReader(hpp);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                System.out.println(line);
            }
            br.close();
            //hpp.delete();

        } catch (ClassNotFoundException e){
            System.out.println("Classe non trouvée, veuillez vérifier que le nom entré correspond effectivement à une classe existante que vous voulez convertir");
        } catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Entrer au moins un premier argument lors de l'execution correspondant au nom de la classe à convertir,\net un deuxième argument " +
                    "correspondant au nom de la classe CPP destination si différent de celle à convertir");
        } catch (IOException e){
            System.out.println("Problèmes rencontrés pendant l'ouverture du fichier");
        }
    }
}
