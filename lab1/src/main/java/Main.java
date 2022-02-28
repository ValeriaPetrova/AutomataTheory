import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.HashSet;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static HashSet<Character> N = new HashSet<Character>();
    private static HashSet<Character> T = new HashSet<Character>();
    private static Multimap<Character, char[]> P = ArrayListMultimap.create();
    private static Character S;

    public static void main (String[] args) {
        System.out.println("Enter Context Free Grammar:");

        parseN();
        parseT();
        parseP();
        parseS();

        StoreMemoryMachine storeMemoryMachine = new StoreMemoryMachine(N,T,P,S);

        System.out.println("\nEnter a string to recognize from the characters of the set ∑");
        String innerStr;
        while (true) {
            boolean flag = false;
            innerStr = scanner.nextLine();
            for (char c:innerStr.toCharArray()) {
                if (!T.contains(c)) {
                    System.out.println("∑ does not contain the specified character");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
        boolean result = storeMemoryMachine.syntaxAnalyzer(innerStr);
        System.out.println("\n____________________________________________");
        System.out.println(result ? "YES" : "NO");

    }

    private static void parseN() {
        String str;
        while (true) {
            boolean flag = false;
            System.out.print("N = ");
            str = scanner.nextLine();
            String newStr = str.replaceAll("[^->e'\"]", "");
            if (newStr.length() > 0) {
                System.out.println("Wrong character");
            } else {
                String[] N_arr = str.split(",");
                for (String value : N_arr) {
                    char[] arr = value.toCharArray();
                    if (arr.length > 1) {
                        System.out.println("You cannot use a bunch of characters in one");
                        flag = true;
                        N.clear();
                        break;
                    } else {
                        N.add(arr[0]);
                    }
                }
                if(!flag){
                    break;
                }
            }
        }
    }

    public static void parseT() {
        String str;
        while (true) {
            boolean flag = false;
            boolean flag1 = false;
            System.out.print("∑ = ");
            str = scanner.nextLine();
            String newStr = str.replaceAll("[^->e'\"]", "");
            if (newStr.length() > 0) {
                System.out.println("Wrong character");
            } else {
                for (Character c:str.toCharArray()) {
                    if (N.contains(c)) {
                        System.out.println("Wrong character " + c + " from N");
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    continue;
                }
                String[] Terminal_arr = str.split(",");
                for (String value : Terminal_arr) {
                    char[] arr = value.toCharArray();
                    if(arr.length > 1) {
                        System.out.println("You cannot use a bunch of characters in one");
                        flag1 = true;
                        T.clear();
                        break;
                    } else {
                        T.add(arr[0]);
                    }
                }
                if (!flag1) {
                    break;
                }
            }
        }
    }

    public static void parseP() {
        String str;
        while (true) {
            boolean flag = false;
            System.out.print("P = ");
            str = scanner.nextLine();
            if (!str.matches("[^'\"]*")) {
                System.out.println("Wrong character");
                continue;
            }
            String[] P_arr = str.split(",");
            for (String s:P_arr) {
                String[] P_current = s.split("->");
                if (P_current.length == 2) {
                    char[] A = P_current[0].toCharArray();
                    if (A.length == 1 && N.contains(A[0])) {
                        char[] a = P_current[1].toCharArray();
                        for (char c:a) {
                            if ((!N.contains(c) && !T.contains(c)) && c != 'e') {
                                System.out.println("A non-set character was used");
                                flag = true;
                                break;
                            }
                        }
                        if (flag) {
                            break;
                        } else {
                            P.put(A[0],a);
                        }
                    } else {
                        System.out.println("The nonterminal does not belong to the set N");
                        flag = true;
                        break;
                    }
                } else {
                    System.out.println("Rules should look like A->a");
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    public static void parseS() {
        String str;
        while (true) {
            System.out.print("S = ");
            str = scanner.nextLine();
            char[] chars = str.toCharArray();
            if (chars.length != 1) {
                System.out.println("Only one non-terminal needed");
            } else if (!N.contains(chars[0])) {
                System.out.println("The nonterminal does not belong to the set N");
            } else {
                S=chars[0];
                break;
            }
        }
    }

}