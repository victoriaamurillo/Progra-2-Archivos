/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.io.*;
import java.util.Date;

public class Mfile {
    private File mf = null;

    public void setFile(String dir) {
        mf = new File(dir);
    }

    public void Info() {
        if (mf.exists()) {
            System.out.println("\nSi Existe:\n------------");
            System.out.println("Nombre: " + mf.getName());
            System.out.println("Path: " + mf.getPath());
            System.out.println("Absoluta: " + mf.getAbsolutePath());
            System.out.println("Padre: " + mf.getAbsoluteFile().getParentFile().getName());
            System.out.println("Byte: " + mf.length());
            if (mf.isFile())
                System.out.println("Es un archivo");
            else if (mf.isDirectory())
                System.out.println("Es un folder");
            System.out.println("Ultima modificacion:" + new Date(mf.lastModified()));
        } else
            System.out.println("Aun no existe");
    }

    public void crearFile() throws IOException {
        if (mf.createNewFile())
            System.out.println("Creado Exitosamente.");
        else
            System.out.println("No se puede crear");
    }

    public void crearFolder() {
        if (mf.mkdir())
            System.out.println("Creado Exitosamente.");
        else
            System.out.println("No se puede crear");
    }

    private boolean antidoto(File file) {
        if (file.isDirectory()) {
            for (File child : file.listFiles())
                antidoto(child);
        }
        return file.delete();
    }

    public void borrar() {
        if (antidoto(mf))
            System.out.println("Se borro");
        else
            System.out.println("No se borro");
    }

    public void dir() {
        if (mf.isDirectory()) {
            System.out.println("Directorio de:" + mf.getAbsolutePath());
            System.out.println("");
            int cfiles = 0, cdirs = 0, tbytes = 0;

            for (File child : mf.listFiles()) {
                if (!child.isHidden()) {
                    Date ultima = new Date(child.lastModified());
                    System.out.print(ultima + "\t");

                    if (child.isDirectory()) {
                        cdirs++;
                        System.out.println("<DIR>\t\t");
                    } else {
                        cfiles++;
                        tbytes += child.length();
                        System.out.println("    \t" + child.length() + "\t");

                    }
                    System.out.println(child.getName());

                }
            }
            System.out.println(cfiles + " archivos\t" + tbytes + "\n" + cdirs + " dirs");
        }
    }

    public void tree() {
        tree(mf, "-");
    }

    private void tree(File dir, String tab) {
        if (dir.isDirectory()) {
            System.out.println(tab + dir.getName());
            for (File child : dir.listFiles())
                if (!child.isHidden())
                    tree(child, tab + "--");
        }
    }

    public void escribirReemplazar(String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mf))) {
            writer.write(contenido);
        }
    }

    
    public void escribirAgregar(String contenido) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(mf, true))) {
            writer.write(contenido);
            writer.newLine();
        }
    }

    
    public String leerArchivo() throws IOException {
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(mf))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append(System.lineSeparator());
            }
        }
        return contenido.toString();
    }
}
