/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMFile {

    static Mfile mf = new Mfile();
    static Scanner lea = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;
        do {
            System.out.println("\nMENU\n");
            System.out.println("1- Set el archivo / folder.");
            System.out.println("2- Ver informacion.");
            System.out.println("3- Crear un archivo.");
            System.out.println("4- Crear un Folder.");
            System.out.println("5- Borrar.");
            System.out.println("6- CMD - DIR");
            System.out.println("7- Tree");
            System.out.println("8- Escribir en el archivo.");
            System.out.println("9- Leer el archivo.");
            System.out.println("10- Salir.");
            System.out.println("Escoja una opcion: ");
            try {
                opcion = lea.nextInt();
                lea.nextLine();  
                switch (opcion) {
                    case 1:
                        set();
                        break;
                    case 2:
                        mf.Info();
                        break;
                    case 3:
                        mf.crearFile();
                        break;
                    case 4:
                        mf.crearFolder();
                        break;
                    case 5:
                        mf.borrar();
                        break;
                    case 6:
                        mf.dir();
                        break;
                    case 7:
                        mf.tree();
                        break;
                    case 8:
                        escribirArchivo();
                        break;
                    case 9:
                        leerArchivo();
                        break;
                    case 10:
                        System.out.println("te saliste");
                        break;
                    default:
                        System.out.println("Intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                lea.nextLine();  
                System.out.println("Por favor ingrese una opción correcta.");
            } catch (NullPointerException e) {
                System.out.println("Debes seleccionar la opción 1 por lo menos una vez.");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 10);
    }

    private static void set() {
        System.out.println("Direccion: ");
        mf.setFile(lea.next());
    }

    private static void escribirArchivo() throws IOException {
        System.out.println("Escriba el contenido que desea agregar o reemplazar:");
        String contenido = lea.nextLine();
        System.out.println("Desea reemplazar el contenido existente o agregar al final? (R/A):");
        String opcion = lea.next();
        lea.nextLine();  
        if (opcion.equalsIgnoreCase("R")) {
            mf.escribirReemplazar(contenido);
            System.out.println("Contenido reemplazado exitosamente.");
        } else if (opcion.equalsIgnoreCase("A")) {
            mf.escribirAgregar(contenido);
            System.out.println("Contenido agregado exitosamente.");
        } else {
            System.out.println("no valido");
        }
    }

    private static void leerArchivo() throws IOException {
        String contenido = mf.leerArchivo();
        System.out.println("Contenido del archivo:");
        System.out.println(contenido);
    }
}