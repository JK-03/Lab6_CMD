/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Lógica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;

/**
 *
 * @author jenniferbueso
 */
public class Comandos {
    public Path directorioActual = Paths.get("").toAbsolutePath();

    public String mkdir(String nombre) throws IOException {
        Path newDir = directorioActual.resolve(nombre);
        StringBuilder message = new StringBuilder();

        if (Files.exists(newDir)) {
            message.append("\nError: El directorio ya existe.\n\n");
        } else {
            Files.createDirectory(newDir);
            message.append("\nDirectorio creado: ").append(nombre).append("\n\n");
        }

        return message.toString();
    }
    
    public String mFile(String nombre) throws IOException {
        Path newFile = directorioActual.resolve(nombre);
        StringBuilder message = new StringBuilder();

        if (Files.exists(newFile)) {
            message.append("\nError: el archivo ya existe\n\n");
        } else {
            Files.createFile(newFile);
            message.append("\nArchivo creado: ").append(nombre).append("\n\n");
        }

        return message.toString();
    }
    
    public String rm(String nombre) throws IOException {
        Path rutaEliminar = directorioActual.resolve(nombre);
        StringBuilder mensaje = new StringBuilder();

        if (Files.exists(rutaEliminar)) {
            if (Files.isDirectory(rutaEliminar)) {
                eliminarDirectorio(rutaEliminar.toFile());
                mensaje.append("\nDirectorio eliminado: ").append(nombre).append("\n\n");
            } else {
                Files.delete(rutaEliminar);
                mensaje.append("\nArchivo eliminado: ").append(nombre).append("\n\n");
            }
        } else {
            mensaje.append("\nError: el archivo o directorio no existe\n\n");
        }

        return mensaje.toString();
    }

    private void eliminarDirectorio(File directorio) {
        File[] archivos = directorio.listFiles();
        if (archivos != null) {
            for (File archivo : archivos) {
                if (archivo.isDirectory()) {
                    eliminarDirectorio(archivo);
                } else {
                    archivo.delete();
                }
            }
        }
        directorio.delete();
    }

    public String cd(String nombreCarpeta) {
        Path nuevaRuta = directorioActual.resolve(nombreCarpeta);
        if (Files.exists(nuevaRuta) && Files.isDirectory(nuevaRuta)) {
            directorioActual = nuevaRuta;
            return "\nCarpeta actual cambiada a: " + nombreCarpeta + "\n\n";
        } else {
            return "\nError: la carpeta no existe\n\n";
        }
    }
    
   public String cdRegresar() {
        Path parentPath = directorioActual.getParent();
        if (parentPath != null) {
            directorioActual = parentPath;
            return "\nCarpeta actual cambiada a: " + directorioActual.toString() + "\n\n";
        } else {
            return "\nError: ya estás en la carpeta raíz, no puedes regresar más.\n\n";
        }
    }
   
   public String dir() {
        File folder = new File(directorioActual.toString());
        File[] listOfFiles = folder.listFiles();
        StringBuilder sb = new StringBuilder();

        for (File file : listOfFiles) {
            if (file.isFile()) {
                sb.append("\n");
                sb.append("Archivo: ").append(file.getName()).append("\n");
            } else if (file.isDirectory()) {
                sb.append("Carpeta: ").append(file.getName()).append("\n");
            }
        }

        return sb.toString();
    }
 
    public String date() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return "\n" + formatter.format(calendar.getTime()) + "\n\n";
    }

    public String time() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        return "\n" + formatter.format(calendar.getTime()) + "\n\n";
    }

    public String escribir(String nombreArchivo, String texto) {
        try {
            FileWriter writer = new FileWriter(nombreArchivo, true); // El segundo argumento 'true' es para habilitar la escritura al final del archivo (append)
            writer.write(texto);
            writer.close();
            return "\nTexto añadido al archivo: " + nombreArchivo + "\n\n";
        } catch (IOException e) {
            return "\nError al escribir en el archivo: " + e.getMessage() + "\n\n";
        }
    }
    
    public String leer(String nombreArchivo) {
        StringBuilder contenido = new StringBuilder();

        try {
            File archivo = new File(nombreArchivo);
            BufferedReader lector = new BufferedReader(new FileReader(archivo));

            String linea;
            while ((linea = lector.readLine()) != null) {
                contenido.append("\n");
                contenido.append(linea);
                contenido.append("\n\n");
            }

            lector.close();
        } catch (IOException e) {
            contenido.append("\nError al leer el archivo: \n\n");
            contenido.append(e.getMessage());
        }

        return contenido.toString();
    }

    public String help() {
        StringBuilder info = new StringBuilder();
        
        info.append("\n1. Mkdir < nombre>: Nueva carpeta\n");
        info.append("2. Mfile <nombre.ext>:Nuevo archivo\n");
        info.append("3. Rm < nombre>: Eliminar carpeta y archivo.\n");
        info.append("4. Cd < nombre carpeta>: Cambiar de carpeta actual\n");
        info.append("5. <...> Regresar de Carpeta\n");
        info.append("6. Dir: Listar todas las carpetas y archivos en la carpeta actual\n");
        info.append("7. Date: Ver fecha actual\n");
        info.append("8. Time: Ver hora actual\n");
        info.append("9. Escribir<wr>: Debe ingresar un texto al archivo seleccionado.\n");
        info.append("10. Leer<rd>: Debe seleccionar un archivo para mostrar lo que contiene.\n\n");
        
        return info.toString();
    }
}
