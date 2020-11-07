import java.io.UnsupportedEncodingException;
import java.nio.charset.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException, CharacterCodingException {
        //ISO => Ã³ y utf-8 => ó
        System.out.println("=========Example 1==========");
        String example1 = "OperaciÃƒÂ³n correcta"; // ƒÂ son caracteres corruptos que se genera
        System.out.println(example1);
        System.out.println(convertIsoToUTF8Cleaning(example1));
        System.out.println("=========Example 2==========");
        String example2 = "OperaciÃ³n correcta";
        System.out.println(example2);
        System.out.println(convertIsoToUTF8(example2));
        System.out.println("=========Example 3==========");
        String example3 = "Operación correcta";
        System.out.println(example3);
        System.out.println(convertUTF8ToISO(example3));
        System.out.println("=========Example 4==========");
        String example4="Oper";
        System.out.println(example4);
        System.out.println(convertISO_to_UTF8_personal(example4));
        System.out.println("=========Example 5==========");
        String example5="OperaciÃƒÂ³n correcta";
        System.out.println(example5);
        System.out.println(convertISO_to_UTF8_personal_cleaning(example5)); // cleaning da Operación correcta porque funciona el new String(utf-8), y ó en ISO es nada en utf-8, no aparece tabla
    }

    private static void showBytes(String example) throws UnsupportedEncodingException {
        byte[] num = example.getBytes(Charset.forName("ISO-8859-1"));
        for (int i = 0; i < example.length(); i++){
            System.out.println((int) example.charAt(i) + " => " + num[i]);
        }
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

    private static String cleanISO(String example) throws UnsupportedEncodingException {
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
        return new String(Arrays.copyOf(exampleISOCleaned, exampleISO.length - variation), "utf-8"); // no funciona en eclipse devuelve lo mismo
    }

    private static String convertISO_to_UTF8_personal_cleaning(String example) throws UnsupportedEncodingException {
        return convertISO_to_UTF8_personal(cleanISO(example));
    }

    private static String convertIsoToUTF8(String example) throws UnsupportedEncodingException {
        return new String(example.getBytes("ISO-8859-1"), "utf-8");
    }

    private static String convertUTF8ToISO(String example) throws UnsupportedEncodingException {
        return new String(example.getBytes("utf-8"), "ISO-8859-1");
    }

    public static String convertISO_to_UTF8_personal(String strISO_8859_1) {
        final StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (i = 0; i < strISO_8859_1.length() - 1; i++) {
            char ch = strISO_8859_1.charAt(i);
            char chNext = strISO_8859_1.charAt(i + 1);
            if (ch <= 127) {
                stringBuilder.append(ch);
            } else if (ch == 194 && chNext >= 128 && chNext <= 191) {
                stringBuilder.append(chNext);
            } else if(ch == 195 && chNext >= 128 && chNext <= 191){
                int res = chNext + 64;
                stringBuilder.append((char) res);
            } else if(ch == 194){
                stringBuilder.append((char) 173);
            } else if(ch == 195){
                stringBuilder.append((char) 224);
            }
        }
        char ch = strISO_8859_1.charAt(i);
        if (ch <= 127 ){
            stringBuilder.append(ch);
        }
        return stringBuilder.toString();
    }
}

//String[] caracterIso = new String[]{"Â"};
//String[] caracterUTF = new String[]{"Â³"};
//cadenaModificar.replaceAll("lineadecodigo", "aulambra");
//System.out.println(cadena);


