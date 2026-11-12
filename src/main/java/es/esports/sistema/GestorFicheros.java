package es.esports.sistema;   // ajusta el paquete al que uséis

import es.esports.excepciones.reglaNegocioException.CampoObligatorioException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;

public class GestorFicheros {

    //MOVER un fichero a otra carpeta
    public static void moverFichero(File fichero, File carpetaDestino) throws IOException, CampoObligatorioException {
        if (fichero == null || !fichero.isFile()) {
            throw new FileNotFoundException("El fichero no existe: " + fichero);
        }
        if (carpetaDestino == null) {
            throw new CampoObligatorioException("La carpeta destino es obligatoria");
        }
        carpetaDestino.mkdirs();   // crea la carpeta si no existe
        Files.move(fichero.toPath(), new File(carpetaDestino, fichero.getName()).toPath());
    }

    //RENOMBRAR un directorio
    public static void renombrarCarpeta(File carpeta, String nuevoNombre) throws IOException, CampoObligatorioException {
        if (carpeta == null || !carpeta.isDirectory()) {
            throw new FileNotFoundException("La carpeta no existe: " + carpeta);
        }
        if (nuevoNombre == null || nuevoNombre.isBlank()) {
            throw new CampoObligatorioException("El nuevo nombre es obligatorio");
        }
        File destino = new File(carpeta.getParentFile(), nuevoNombre.trim());
        if (destino.exists()) {
            throw new IOException("Ya existe: " + destino);
        }
        if (!carpeta.renameTo(destino)) {
            throw new IOException("No se pudo renombrar " + carpeta);
        }
    }
}