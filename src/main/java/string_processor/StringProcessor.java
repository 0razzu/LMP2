package string_processor;


public class StringProcessor {
    public static String loopString(String string, int coefficient) throws StringException {
        if (coefficient < 0)
            throw new StringException(StringErrorCode.NEGATIVE_COEFFICIENT);
        
        if (string == null)
            throw new StringException(StringErrorCode.NULL_STRING);
        
        if ((coefficient == 0) || (string.equals("")))
            return "";
        
        StringBuilder stringBuilder = new StringBuilder(string);
        
        for (int i = 1; i < coefficient; i++)
            stringBuilder.append(string);
        
        return stringBuilder.toString();
    }
    
    
    public static int countEntries(String string, String substring) throws StringException {
        int k = 0;
        
        if ((string == null) || (substring == null))
            throw new StringException(StringErrorCode.NULL_STRING);
        
        if (substring.equals(""))
            throw new StringException(StringErrorCode.EMPTY_STRING);
        
        for (int i = 0; i <= string.length() - substring.length(); i++)
            if (string.regionMatches(i, substring, 0, substring.length()))
                k++;
        
        return k;
    }
    
    
    public static String replaceNumbersWithWords(String string) throws StringException {
        if (string == null)
            throw new StringException(StringErrorCode.NULL_STRING);
        
        StringBuilder stringBuilder = new StringBuilder(string);
        final String[] numbers = {"ноль", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"};
        
        for (int i = string.length() - 1; i >= 0; i--)
            if (Character.isDigit(stringBuilder.charAt(i)))
                stringBuilder.replace(i, i + 1, numbers[Character.getNumericValue(stringBuilder.charAt(i))]);
        
        return stringBuilder.toString();
    }
    
    
    public static void delEverySecondChar(StringBuilder stringBuilder) throws StringException {
        if (stringBuilder == null)
            throw new StringException(StringErrorCode.NULL_STRING);
        
        for (int i = stringBuilder.length() / 2 * 2 - 1; i > 0; i -= 2)
            stringBuilder.deleteCharAt(i);
    }
    
    
    public static void exchangeFirstAndLastWords(StringBuilder stringBuilder) throws StringException {
        if (stringBuilder == null)
            throw new StringException(StringErrorCode.NULL_STRING);
        
        int firstWordB = 0, firstWordE, lastWordB, lastWordE = stringBuilder.length() - 1;
        
        while ((firstWordB < stringBuilder.length()) && (!Character.isAlphabetic(stringBuilder.charAt(firstWordB))))
            firstWordB++;
        
        firstWordE = firstWordB + 1;
        
        while ((firstWordE < stringBuilder.length()) && (Character.isAlphabetic(stringBuilder.charAt(firstWordE))))
            firstWordE++;
        
        while ((lastWordE > 0) && (!Character.isAlphabetic(stringBuilder.charAt(lastWordE))))
            lastWordE--;
        
        lastWordB = lastWordE - 1;
        lastWordE++;
        
        if (firstWordE < lastWordE) {
            while ((lastWordB > 0) && (Character.isAlphabetic(stringBuilder.charAt(lastWordB))))
                lastWordB--;
            
            lastWordB++;
            
            String firstWord = stringBuilder.substring(firstWordB, firstWordE);
            String lastWord = stringBuilder.substring(lastWordB, lastWordE);
            
            stringBuilder.replace(lastWordB, lastWordE, firstWord);
            stringBuilder.replace(firstWordB, firstWordE, lastWord);
        }
    }
    
    
    private static boolean isHexChar(char c) {
        return ((Character.isDigit(c))) ||
                ((c >= 'a') && (c <= 'f')) ||
                ((c >= 'A') && (c <= 'F'));
    }
    
    
    public static String hexToDec(String str) throws StringException {
        if (str == null)
            throw new StringException(StringErrorCode.NULL_STRING);
        
        StringBuilder sb = new StringBuilder(str);
        String substr;
        int num, numB = 0, numE;
        
        while (numB < sb.length() - 2) {
            numB = sb.indexOf("0x", numB);
            
            try {
                numE = numB + 2;
                
                while ((numE < sb.length()) && (isHexChar(sb.charAt(numE))))
                    numE++;
                
                if ((numE + 1 < sb.length()) && (sb.charAt(numE - 1) == '0') &&
                        (sb.charAt(numE) == 'x') && (isHexChar(sb.charAt(numE + 1))))
                    numE--;
                
                substr = sb.substring(numB + 2, numE);
                
                num = Integer.parseInt(substr, 16);
                sb.replace(numB, numE, Integer.toString(num));
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                if (numB == -1)
                    numB = sb.length();
    
                numB += 2;
            }
        }
        
        return sb.toString();
    }
}