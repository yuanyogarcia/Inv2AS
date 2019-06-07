/*
 Monopoly para cliente Inv2AS”
 Proyectos Informáticos II - 2019
 Grupo C (Juan Jose Garcia, Manuel Angel Mateos, Jaime Ojeda)
 */

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class LectorCSV {

    private static final char SEPARADOR = ',';
    
    public static void escribirEntrada(Writer w, List<String> valores) throws IOException {
        escribirEntrada(w, valores, SEPARADOR, ' ');
    }

    public static void escribirEntrada(Writer w, List<String> valores, char separadores) throws IOException {
        escribirEntrada(w, valores, separadores, ' ');
    }

    private static String usarFormatoCSV(String value) {

        String resultado = value;
        if (resultado.contains("\"")) {
            resultado = resultado.replace("\"", "\"\"");
        }
        return resultado;

    }

    public static void escribirEntrada(Writer w, List<String> valores, char separadores, char comillas) throws IOException {

        boolean primera = true;

        if (separadores == ' ') {
            separadores = SEPARADOR;
        }

        StringBuilder sb = new StringBuilder();
        for (String value : valores) {
            if (!primera) {
                sb.append(separadores);
            }
            if (comillas == ' ') {
                sb.append(usarFormatoCSV(value));
            } else {
                sb.append(comillas).append(usarFormatoCSV(value)).append(comillas);
            }

            primera = false;
        }
        sb.append("\n");
        w.append(sb.toString());
    }

}
