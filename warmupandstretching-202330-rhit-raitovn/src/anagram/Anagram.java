package anagram;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This utility class can test whether two strings are anagrams.
 *
 * @author Claude Anderson.
 */
public class Anagram {

	/**
	 * We say that two strings are anagrams if one can be transformed into the
	 * other by permuting the characters (and ignoring case).
	 * 
	 * For example, "Data Structure" and "Saturated Curt" are anagrams,
	 * as are "Elvis" and "Lives".
	 * 
	 * @param s1
	 *            first string
	 * @param s2
	 *            second string
	 * @return true iff s1 is an anagram of s2
	 */
	public static boolean isAnagram(String s1, String s2) {
		
		char[] chars1 = new char[s1.length()];
		char[] chars2 = new char[s2.length()];
		s1 = s1.toLowerCase(); s2 = s2.toLowerCase();
		
 		for(int i = 0; i < s1.length(); i++) {
			chars1[i] = s1.charAt(i);
		}
		
 		for(int i = 0; i < s2.length(); i++) {
			chars2[i] = s2.charAt(i);
		}
 		Arrays.sort(chars1);
 		Arrays.sort(chars2);
 		
 		if (Arrays.equals(chars1, chars2))
 			return true;
 			
 		
		return false;
		
	}
}
