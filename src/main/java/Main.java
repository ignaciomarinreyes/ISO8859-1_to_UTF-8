import java.io.UnsupportedEncodingException;
import java.nio.charset.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws UnsupportedEncodingException, CharacterCodingException {
        //ISO => Ã³ y utf-8 => ó

        System.out.println("=========Example 1==========");
        String example2 = "OperaciÃ³n correcta";
        System.out.println(example2);
        System.out.println(convertIsoToUTF8(example2));
        System.out.println("=========Example 2==========");
        String example3 = "Operación correcta";
        System.out.println(example3);
        System.out.println(convertUTF8ToISO(example3));
        System.out.println("=========Example 3==========");
        String example4="OperaciÃ³n";
        System.out.println(example4);
        System.out.println(convertISO_to_UTF8_personal(example4));
    }

    private static String convertIsoToUTF8(String example) throws UnsupportedEncodingException {
        return new String(example.getBytes("ISO-8859-1"), "utf-8");
    }

    private static String convertUTF8ToISO(String example) throws UnsupportedEncodingException {
        return new String(example.getBytes("utf-8"), "ISO-8859-1");
    }

    public static String convertISO_to_UTF8_personal(String strISO_8859_1) {
        String res = "";
        int i = 0;
        for (i = 0; i < strISO_8859_1.length() - 1; i++) {
            char ch = strISO_8859_1.charAt(i);
            char chNext = strISO_8859_1.charAt(i + 1);
            if (ch <= 127) {
                res += ch;
            } else if (ch == 194 && chNext >= 128 && chNext <= 191) {
                res += chNext;
            } else if(ch == 195 && chNext >= 128 && chNext <= 191){
                int resNum = chNext + 64;
                res += (char) resNum;
            } else if(ch == 194){
                res += (char) 173;
            } else if(ch == 195){
                res += (char) 224;
            }
        }
        char ch = strISO_8859_1.charAt(i);
        if (ch <= 127 ){
            res += ch;
        }
        return res;
    }
}

