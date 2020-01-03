import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestStringProcessor {
    @Test
    void testStringProcessorLoopString() {
        assertAll(
                () -> assertEquals("aBcб12aBcб12aBcб12", StringProcessor.loopString("aBcб12", 3)),
                () -> assertEquals("!!!!!!!!!!", StringProcessor.loopString("!", 10)),
                () -> assertEquals("", StringProcessor.loopString("Абв", 0)),
                () -> assertThrows(StringException.class, () -> StringProcessor.loopString(null, 2)),
                () -> assertThrows(StringException.class, () -> StringProcessor.loopString("1aа", -1))
        );
    }
    
    
    @Test
    void testStringProcessorCountEntries() {
        assertAll(
                () -> assertEquals(1, StringProcessor.countEntries("a", "a")),
                () -> assertEquals(3, StringProcessor.countEntries("aaa", "a")),
                () -> assertEquals(2, StringProcessor.countEntries("aaa", "aa")),
                () -> assertEquals(5, StringProcessor.countEntries("88888", "8")),
                () -> assertEquals(3, StringProcessor.countEntries("ёыё15/ё", "ё")),
                () -> assertEquals(0, StringProcessor.countEntries("", "123")),
                () -> assertThrows(StringException.class, () -> StringProcessor.countEntries(null, "абв")),
                () -> assertThrows(StringException.class, () -> StringProcessor.countEntries("абв", null)),
                () -> assertThrows(StringException.class, () -> StringProcessor.countEntries("абв", ""))
        );
    }
    
    
    @Test
    void testStringProcessorReplaceNumbersWithWords() {
        assertAll(
                () -> assertEquals("семь км", StringProcessor.replaceNumbersWithWords("7 км")),
                () -> assertEquals("одиндватричетырепять | шестьсемьвосемьдевятьноль",
                        StringProcessor.replaceNumbersWithWords("12345 | 67890")),
                () -> assertEquals("", StringProcessor.replaceNumbersWithWords("")),
                () -> assertEquals("abc", StringProcessor.replaceNumbersWithWords("abc")),
                () -> assertThrows(StringException.class, () -> StringProcessor.replaceNumbersWithWords(null))
        );
    }
    
    
    @Test
    void testStringProcessorDelEverySecondChar() throws StringException {
        StringBuilder sb1 = new StringBuilder("AbcАбв12");
        StringBuilder sb2 = new StringBuilder("Abc");
        StringBuilder sb3 = new StringBuilder("A");
        StringBuilder sb4 = new StringBuilder();
        
        StringProcessor.delEverySecondChar(sb1);
        StringProcessor.delEverySecondChar(sb2);
        StringProcessor.delEverySecondChar(sb3);
        StringProcessor.delEverySecondChar(sb4);
        
        assertAll(
                () -> assertEquals("Acб1", sb1.toString()),
                () -> assertEquals("Ac", sb2.toString()),
                () -> assertEquals("A", sb3.toString()),
                () -> assertEquals("", sb4.toString()),
                () -> assertThrows(StringException.class, () -> StringProcessor.delEverySecondChar(null))
        );
    }
    
    
    @Test
    void testStringProcessorExchangeFirstAndLastWords() throws StringException {
        StringBuilder[] sbArray = new StringBuilder[8];
        
        sbArray[0] = new StringBuilder("мама мыла раму");
        sbArray[1] = new StringBuilder("   мама  мыла    раму ");
        sbArray[2] = new StringBuilder("12мама мыла!раму?");
        sbArray[3] = new StringBuilder("Lorem ipsum dolor sit amet");
        sbArray[4] = new StringBuilder("two words");
        sbArray[5] = new StringBuilder("oneword");
        sbArray[6] = new StringBuilder("10 20 900 4! -9.2; = §/");
        sbArray[7] = new StringBuilder();
        
        for (byte i = 0; i < 8; i++)
            StringProcessor.exchangeFirstAndLastWords(sbArray[i]);
        
        assertAll(
                () -> assertEquals("раму мыла мама", sbArray[0].toString()),
                () -> assertEquals("   раму  мыла    мама ", sbArray[1].toString()),
                () -> assertEquals("12раму мыла!мама?", sbArray[2].toString()),
                () -> assertEquals("amet ipsum dolor sit Lorem", sbArray[3].toString()),
                () -> assertEquals("words two", sbArray[4].toString()),
                () -> assertEquals("oneword", sbArray[5].toString()),
                () -> assertEquals("10 20 900 4! -9.2; = §/", sbArray[6].toString()),
                () -> assertEquals("", sbArray[7].toString()),
                () -> assertThrows(StringException.class, () -> StringProcessor.exchangeFirstAndLastWords(null))
        );
    }
    
    
    @Test
    void testStringProcessorHexToDec() {
        String str1 = "Васе 0x10 лет";
        String str2 = "Vasya is 0x0000001a, John is 0x00000012";
        String str3 = "0x00000001, 0x00000011; 0x00000F0F. 0x00000000, 0x01";
        String str4 = "0x0000000A0x000000010x00989680";
        String str5 = "0x00000100";
        String str6 = "0xFFFFFFFF";
        String str7 = "0x-0000001";
        String str8 = "0x-00000001";
        String str9 = "0x000m0001";
        String str10 = "no numbers";
        String str11 = "";
        
        assertAll(
                () -> assertEquals("Васе 16 лет", StringProcessor.hexToDec(str1)),
                () -> assertEquals("Vasya is 26, John is 18", StringProcessor.hexToDec(str2)),
                () -> assertEquals("1, 17; 3855. 0, 1", StringProcessor.hexToDec(str3)),
                () -> assertEquals("10110000000", StringProcessor.hexToDec(str4)),
                () -> assertEquals("256", StringProcessor.hexToDec(str5)),
                () -> assertEquals(str6, StringProcessor.hexToDec(str6)),
                () -> assertEquals(str7, StringProcessor.hexToDec(str7)),
                () -> assertEquals(str8, StringProcessor.hexToDec(str8)),
                () -> assertEquals("0m0001", StringProcessor.hexToDec(str9)),
                () -> assertEquals(str10, StringProcessor.hexToDec(str10)),
                () -> assertEquals(str11, StringProcessor.hexToDec(str11)),
                () -> assertThrows(StringException.class, () -> StringProcessor.hexToDec(null))
        );
    }
}