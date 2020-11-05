import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // ISO => Ã³ y utf-8 => ó
        String example1 = "OperaciÃƒÂ³n correcta"; // ƒÂ son caracteres corruptos que se genera
        System.out.println(convertIsoToUTF8Cleaning(example1));
        String example2 = "OperaciÃ³n correcta";
        System.out.println(convertIsoToUTF8(example2));
        String example3 = "Operación correcta";
        System.out.println(convertUTF8ToISO(example3));
    }

    private static String convertIsoToUTF8Cleaning(String example) throws UnsupportedEncodingException {
        byte[] exampleISO = example.getBytes("ISO-8859-1");
        byte[] exampleISOCleaned = new byte[exampleISO.length];
        int variation = 0;
        int j = 0;
        for (int i = 0; i < exampleISO.length; i++) {
            if (exampleISO[i] < 0) {
                exampleISOCleaned[j] = exampleISO[i];
                i += 3;
                variation += 2;
                j++;
            }
            exampleISOCleaned[j] = exampleISO[i];
            j++;
        }
        return new String(Arrays.copyOf(exampleISOCleaned, exampleISO.length - variation), "utf-8");
    }

    private static String convertIsoToUTF8(String example) throws UnsupportedEncodingException {
        return new String(example.getBytes("ISO-8859-1"), "utf-8");
    }

    private static String convertUTF8ToISO(String example) throws UnsupportedEncodingException {
        return new String(example.getBytes("utf-8"), "ISO-8859-1");
    }
}

//String[] caracterIso = new String[]{"Â"};
//String[] caracterUTF = new String[]{"Â³"};
//cadenaModificar.replaceAll("lineadecodigo", "aulambra");
//System.out.println(cadena);


