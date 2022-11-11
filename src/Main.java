import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Dime la frase y hago magia");
        try{
            ArrayList<String> palabras = cambiarLetra(scanner.nextLine());
            String frase = reconstruir(palabras);
            System.out.println(frase);
        } catch (IOException e) {
            System.out.println("Algo se ha ido a la mierda");
        }
    }


    private static ArrayList<String> cambiarLetra(String cambiarLetra) throws IOException {
        Process process = crearProceso("Ejercicio02_Letra_Cambiada.jar");
        BufferedReader br = getBufferedReader(process);
        PrintStream ps = getPrintStream(process);

        ArrayList<String> palabras = new ArrayList<>();

        ps.println(cambiarLetra);
        ps.flush();

        String palabra;
        while ( !(palabra = br.readLine()).equalsIgnoreCase("fin") ){
            palabras.add(palabra);
        }
        return palabras;
    }

    private static String reconstruir(ArrayList<String> palabras) throws IOException {
        Process process = crearProceso("Ejerccio02_Construir.jar");

        BufferedReader bf = getBufferedReader(process);
        PrintStream ps = getPrintStream(process);

        for (String palabra : palabras) {
            ps.println(palabra);
            ps.flush();
        }
        ps.println("fin");
        ps.flush();

        return bf.readLine();

    }

    private static PrintStream getPrintStream(Process process) {
        OutputStream os = process.getOutputStream();
        PrintStream ps = new PrintStream(os);
        return ps;
    }

    private static BufferedReader getBufferedReader(Process process) {
        InputStreamReader isr = new InputStreamReader(process.getInputStream());
        BufferedReader br = new BufferedReader(isr);
        return br;
    }

    private static Process crearProceso(String proceso) throws IOException {
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar", proceso);
        processBuilder.redirectErrorStream(true);
        Process process = processBuilder.start();
        return process;
    }


}