import com.google.common.collect.Multimap;

import java.util.*;

public class StoreMemoryMachine {
    private HashSet<Character> N ;
    private HashSet<Character> T;
    private static Multimap<Character, char[]> P;
    private Character S;
    private HashSet<TreeNode> treeLevel;
    private boolean result;

    public StoreMemoryMachine(HashSet<Character> n, HashSet<Character> t, Multimap<Character, char[]> p, Character s) {
        N = n;
        T = t;
        P = p;
        S = s;
        treeLevel = new HashSet<>();
    }

    private void step() {
        HashSet<TreeNode> newTreeLevel = new HashSet<>();
        for (TreeNode treeNode : treeLevel) {
            boolean flag = false;
            Deque<Character> word = new ArrayDeque<>(treeNode.getWord());
            Deque<Character> stack  = new ArrayDeque<>(treeNode.getStack());

            while (!word.isEmpty() && !stack.isEmpty() && word.peekFirst() == stack.peekFirst()) {
                word.removeFirst();
                stack.removeFirst();
                flag = true;
            }
            if (flag) {
                TreeNode newTreeNode = new TreeNode(word,stack);
                if (treeNode.correct(N)) {
                    newTreeLevel.add(newTreeNode);
                }
                continue;
            }

            if (P.containsKey(stack.peekFirst())) {
                List<char[]> list = (List<char[]>) P.get(stack.peekFirst());
                Iterator iterator = list.iterator();
                while (iterator.hasNext()) {
                    char[] chars = (char[]) iterator.next();
                    Deque<Character> newStack  = new ArrayDeque<>(stack);
                    newStack.removeFirst();
                    if (chars.length == 1 && chars[0] == 'e') {
                        TreeNode newTreeNode = new TreeNode(word,newStack);
                        newTreeLevel.add(newTreeNode);
                        continue;
                    }
                    for (int i = chars.length - 1; i >= 0; i--) {
                        newStack.addFirst(chars[i]);
                    }
                    TreeNode newTreeNode = new TreeNode(word,newStack);
                    if (treeNode.correct(N)) {
                        newTreeLevel.add(newTreeNode);
                    }
                }
            }
        }
        treeLevel = newTreeLevel;
    }

    private boolean checkTreeLevel() {
        boolean flag = false;
        if (treeLevel.isEmpty()) {
            result = false;
            return true;
        }
        for (TreeNode treeNode : treeLevel) {
            if (treeNode.getWord().isEmpty() && treeNode.getStack().isEmpty()) {
                flag = true;
                result = true;
                break;
            }
        }
        return flag;
    }

    private void printTreeLevel() {
        System.out.println("\n____________________________________________");
        for (TreeNode treeNode:treeLevel) {
            System.out.println(treeNode);
        }
    }

    public boolean syntaxAnalyzer(String str) {
        treeLevel.add(new TreeNode(str,this.S));
        //printTreeLevel();
        while (!checkTreeLevel()) {
            step();
            //printTreeLevel();
        }
        return result;
    }

}