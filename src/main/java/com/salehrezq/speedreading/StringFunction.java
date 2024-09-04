/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salehrezq.speedreading;

/**
 *
 * @author S
 */
public class StringFunction {

    public static String addLuxuryFinalNewLine(String s) {
        String str = s;
        StringBuilder b = new StringBuilder(str);

        if (s.length() > 0) {
            if (s.charAt(s.length() - 1) != '.') {
                b.append('\n');
            }
        }
        str = b.toString();
        return str;
    }

    public static String[] sentenceTokens(String inputStr) {
        String str = addLuxuryFinalNewLine(inputStr);
        int linesCount = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '\n') {
                linesCount++;
            }
        }

        String[] sentences = new String[linesCount];
        StringBuilder b = new StringBuilder();

        int j = 0;
        for (int i = 0; i < linesCount; i++) {
            while (str.charAt(j) != '\n') {
                b.append(str.charAt(j));
                j++;
            }

            j++;
            sentences[i] = b.toString();
            b.delete(0, j);
        }
        return sentences;
    }

    /**
     * Collection of methods. this is the correct call sequence of methods to: -
     * eat extra spaces. - eat extra dots. - eat extra newLines. - break each
     * sentence using its end dot. - keeping the end dot of sentence.
     *
     * @param inputText
     * @return
     */
    public String trim_eatSpaces_eatDots_eatNewlines_breakScentence_keepEndDots(String inputText) {
        return eatExtraNewLines(breakSentencesKeepDots(eatExtraSpaces(eatExtraPunctuation(eatExtraNewLines(((inputText)))))));
    }

    /**
     * *************************************************
     */
    /**
     * Break each sentence by dots. For better output use it with
     * <code>eatExtraSpaces(String)</code> and with
     * <code>eatExtraDots(String)</code>
     */
    public static String breakSentences(String s) {
        String trimedString = trimString(s);
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;

        for (i = 0; i < trimedString.length(); i++) {
            if ((trimedString.charAt(i) == ' ') && (trimedString.charAt(i - 1) == '.')) {
                continue;
            }
            if ((trimedString.charAt(i) == '.')) {
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(trimedString.charAt(i));
            }
        }
        trimedString = stringBuilder.toString();
        return trimedString;
    }

    public static String breakSentencesKeepDots(String s) {
        String trimedString = trimString(s);
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;

        for (i = 0; i < trimedString.length(); i++) {
            if ((trimedString.charAt(i) == ' ') && (trimedString.charAt(i - 1) == '.')) {
                continue;
            }
            char c = 'l';
            int afterchar = i + 1;
            if (afterchar < trimedString.length()) {
                c = trimedString.charAt(afterchar);
            }
            if ((trimedString.charAt(i) == '.') && (c != ' ') && (i != trimedString.length() - 1)) {
                stringBuilder.append(".");
                continue;
            }
            if ((trimedString.charAt(i) == '.')) {
                stringBuilder.append(".");
                stringBuilder.append("\n");
            } else if ((trimedString.charAt(i) == '?')) {
                stringBuilder.append("?");
                stringBuilder.append("\n");
            } else if ((trimedString.charAt(i) == '؟')) {
                stringBuilder.append("؟");
                stringBuilder.append("\n");
            } else if ((trimedString.charAt(i) == '!')) {
                stringBuilder.append("!");
                stringBuilder.append("\n");
            } else {
                stringBuilder.append(trimedString.charAt(i));
            }
        }
        trimedString = stringBuilder.toString();
        return trimedString;
    }

    public static String eatExtraNewLines(String s) {
        String myNewString = trimString(s);
        StringBuilder stringB = new StringBuilder();

        char previouseChar = '~';
        for (int i = 0; i < myNewString.length(); i++) {
            if (i > 1) {
                previouseChar = myNewString.charAt(i - 1);
            }
            if ((myNewString.charAt(i) == '\n') && (previouseChar == '\n')) {
                continue;
            }

            stringB.append(myNewString.charAt(i));

        }

        myNewString = stringB.toString();
        return myNewString;

    }

    public static String trimString(String s) {
        int i = 0;
        int whererToBegin = 0;
        int whererToEnd = (s.length() - 1);

        if (s.length() > 0) {
            while ((i < (s.length())) && (s.charAt(i) == ' ')) {
                whererToBegin++;
                i++;
            }
            int stringEnd = 1;
            while (!(i == s.length()) && (s.charAt(s.length() - stringEnd) == ' ')) {
                whererToEnd--;
                stringEnd++;
            }
        }
        return s.substring(whererToBegin, whererToEnd + 1);
    }

    public static String trimStringDots(String s) {
        int i = 0;
        int whererToBegin = 0;
        int whererToEnd = (s.length() - 1);

        if (s.length() > 0) {
            while ((i < (s.length())) && (s.charAt(i) == '.')) {
                whererToBegin++;
                i++;
            }
            int stringEnd = 1;
            while (!(i == s.length()) && (s.charAt(s.length() - stringEnd) == '.')) {
                whererToEnd--;
                stringEnd++;
            }
        }
        return s.substring(whererToBegin, whererToEnd + 1);
    }

    public static int stringWordCount(String s) {
        String trimedString = trimString(s);
        int i;
        int count = 0;
        char previouseChar = '~';

        for (i = 0; i < trimedString.length(); i++) {
            if ((trimedString.charAt(i) == ' ') && (previouseChar != ' ')) {
                count++;
            }
            previouseChar = trimedString.charAt(i);
        }

        return (trimedString.length() > 0) ? count + 1 : 0;
    }

    public static int stringWordCountC(String s) {
        String trimedString = trimString(s);
        int i;
        int count = 0;
        char previouseChar = '~';

        for (i = 0; i < trimedString.length(); i++) {
            if ((trimedString.charAt(i) == ' ') && (previouseChar == ' ')) {
                continue;
            }
            if (trimedString.charAt(i) == ' ') {
                count++;
            }
            previouseChar = trimedString.charAt(i);
        }
        return (trimedString.length() > 0) ? count + 1 : 0;

    }

    public static String eatExtraSpaces(String s) {
        String trimmedString = trimString(s);
        char previuseCar = '~';
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < trimmedString.length(); i++) {
            if (i > 1) {
                previuseCar = trimmedString.charAt(i - 1);
            }

            if ((trimmedString.charAt(i) == ' ') && (previuseCar == ' ')) {
                continue;
            }
            result.append(trimmedString.charAt(i));
        }
        trimmedString = result.toString();
        return trimmedString;
    }

    public static String eatExtraPunctuation(String s) {
        String trimmedString = trimString(s);
        char previuseCar = '~';
        int charCounter = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < trimmedString.length(); i++) {
            if (i > 1) {
                previuseCar = trimmedString.charAt(i - 1);
            }

            if (((trimmedString.charAt(i) == '.') && (previuseCar == '.')) || ((trimmedString.charAt(i) == '!') && (previuseCar == '!')) || ((trimmedString.charAt(i) == '?') && (previuseCar == '?')) || ((trimmedString.charAt(i) == '؟') && (previuseCar == '؟'))) {
                charCounter++;
                continue;
            }
            result.append(trimmedString.charAt(i));
        }
        trimmedString = result.toString();
        return trimmedString;
    }

    public static String eatExtraDots(String s) {
        String trimmedString = trimString(s);
        char previuseCar = '~';
        int charCounter = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < trimmedString.length(); i++) {
            if (i > 1) {
                previuseCar = trimmedString.charAt(i - 1);
            }

            if ((trimmedString.charAt(i) == '.') && (previuseCar == '.')) {
                charCounter++;
                continue;
            }
            result.append(trimmedString.charAt(i));
        }
        trimmedString = result.toString();
        return trimmedString;
    }

    public int[] string_count_For_eachline(String[] strarray) {
        int[] countArray = new int[strarray.length];
        for (int i = 0; i < strarray.length; i++) {
            countArray[i] = stringWordCountC(strarray[i]);
        }
        return countArray;
    }

    public static int string_newLined_WordCount(String[] strarray) {
        int wordCount = 0;
        for (int i = 0; i < strarray.length; i++) {
            wordCount += stringWordCountC(strarray[i]);
        }
        return wordCount;
    }
}
