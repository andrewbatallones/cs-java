package leetcode;

import java.util.ArrayList;
import java.util.HashMap;

public class CountSubstringsVowel {
    // https://leetcode.com/problems/count-of-substrings-containing-every-vowel-and-k-consonants-ii/description

    public static void main(String[] args) {
        System.out.println(countOfSubstrings("aeioqq", 1)); // 0
        System.out.println(countOfSubstrings("aeiou", 0)); // 1
        System.out.println(countOfSubstrings("ieaouqqieaouqq", 1)); // 3
        System.out.println(countOfSubstrings("uoieak", 1)); // 1
        System.out.println(countOfSubstrings("akekikoku", 4)); // 1
        System.out.println(countOfSubstrings("aukkkkkkkkkkeoi", 10)); // 1
        // I wasn't accounting for the entire word
        System.out.println(solution("iqeaouqi", 2)); // 3
    }

    // k is non-negative
    // only lower case letters
    // k will account for "aeiou" (word.length - 5)

    // Sudocode: O(n^2)
    // Want to check if subword has all vowels
    // Need:
    // - result to count all valid substrings
    // - a tick that will iterate through each substring
    // Get substring
    // Loop through each substring O(n)
    //      - Hashmap (will reset on each substring)
    //      - integer of consonants
    //      check if word contains all the vowels O(n)
    //      check if word contains a consonant O(n)
    //      If it does, tick result++;
    // return result
    public static long countOfSubstrings(String word, int k) {
        long result = 0;
        int minLengthWord = 5 + k;

        for (int i = 0; i + minLengthWord <= word.length(); i++) {
            int start = i;
            int end = start + minLengthWord;

            while (end <= word.length()) {
                if (checkSubString(word.substring(start, end), k)) {
                    result++;
                }

                end++;
            }

            // Already checked full string
            start++;
            end--;

            while (start + minLengthWord <= word.length()) {
                if (checkSubString(word.substring(start, end), k)) {
                    result++;
                }

                start++;
            }
        }

        return result;
    }

    private static boolean checkSubString(String word, int k) {
        HashMap<Character, Integer> vowelSet = new HashMap<>();
        String vowels = "aeiou";
        int consonants = 0;

        // Strike 3: toCharArray()
        for (Character c : word.toCharArray()) {
            if (vowels.contains(c.toString())) {
                vowelSet.put(c, 1);
            } else {
                consonants++;
            }
        }

        return (hasAllVowels(vowelSet) && consonants == k);
    }

    private static boolean hasAllVowels(HashMap<Character, Integer> vowelSet) {
        ArrayList<Character> vowels = new ArrayList<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        for (Character c : vowelSet.keySet()) {
            vowels.remove(c);
        }

        return vowels.isEmpty();
    }

    // ========== SOLUTION ===============
    // By leetcode
    // They said to use the sliding window Method
    public static long solution(String word, int k) {
        long numValidSubstrings = 0;
        int start = 0;
        int end = 0;
        // keep track of counts of vowels and consonants
        HashMap<Character, Integer> vowelCount = new HashMap<>();
        int consonantCount = 0;

        // compute index of next consonant for all indices
        int[] nextConsonant = new int[word.length()];
        int nextConsonantIndex = word.length();
        for (int i = word.length() - 1; i >= 0; i--) {
            nextConsonant[i] = nextConsonantIndex;
            if (!isVowel(word.charAt(i))) {
                nextConsonantIndex = i;
            }
        }

        // start sliding window
        while (end < word.length()) {
            // insert new letter
            char newLetter = word.charAt(end);

            // update counts
            if (isVowel(newLetter)) {
                vowelCount.put(
                        newLetter,
                        vowelCount.getOrDefault(newLetter, 0) + 1
                );
            } else {
                consonantCount++;
            }

            // shrink window if too many consonants in our window
            while (consonantCount > k) {
                char startLetter = word.charAt(start);
                if (isVowel(startLetter)) {
                    vowelCount.put(
                            startLetter,
                            vowelCount.get(startLetter) - 1
                    );
                    if (vowelCount.get(startLetter) == 0) {
                        vowelCount.remove(startLetter);
                    }
                } else {
                    consonantCount--;
                }
                start++;
            }

            // while we have a valid window, try to shrink it
            while (
                    start < word.length() &&
                            vowelCount.keySet().size() == 5 &&
                            consonantCount == k
            ) {
                // count the current valid substring, as well as valid substrings produced by appending more vowels
                numValidSubstrings += nextConsonant[end] - end;
                char startLetter = word.charAt(start);
                if (isVowel(startLetter)) {
                    vowelCount.put(
                            startLetter,
                            vowelCount.get(startLetter) - 1
                    );
                    if (vowelCount.get(startLetter) == 0) {
                        vowelCount.remove(startLetter);
                    }
                } else {
                    consonantCount--;
                }

                start++;
            }
            end++;
        }

        return numValidSubstrings;
    }

    private static boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}
