import java.util.TreeMap;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class MorseCode
{
    private static final char DOT = '.';
    private static final char DASH = '-';

    private static TreeMap<Character, String> codeMap;
    private static TreeNode decodeTree;

    public static void main(String[] args)
    {
        MorseCode.start();  
        System.out.println(MorseCode.encode("Watson come here"));
        BTreePrinter.printNode(decodeTree);
    }

    public static void start()
    {
        codeMap = new TreeMap<Character, String>();
        decodeTree = new TreeNode(' ', null, null);  // autoboxing
        // put a space in the root of the decoding tree

        addSymbol('A', ".-");
        addSymbol('B', "-...");
        addSymbol('C', "-.-.");
        addSymbol('D', "-..");
        addSymbol('E', ".");
        addSymbol('F', "..-.");
        addSymbol('G', "--.");
        addSymbol('H', "....");
        addSymbol('I', "..");
        addSymbol('J', ".---");
        addSymbol('K', "-.-");
        addSymbol('L', ".-..");
        addSymbol('M', "--");
        addSymbol('N', "-.");
        addSymbol('O', "---");
        addSymbol('P', ".--.");
        addSymbol('Q', "--.-");
        addSymbol('R', ".-.");
        addSymbol('S', "...");
        addSymbol('T', "-");
        addSymbol('U', "..-");
        addSymbol('V', "...-");
        addSymbol('W', ".--");
        addSymbol('X', "-..-");
        addSymbol('Y', "-.--");
        addSymbol('Z', "--..");
        addSymbol('0', "-----");
        addSymbol('1', ".----");
        addSymbol('2', "..---");
        addSymbol('3', "...--");
        addSymbol('4', "....-");
        addSymbol('5', ".....");
        addSymbol('6', "-....");
        addSymbol('7', "--...");
        addSymbol('8', "---..");
        addSymbol('9', "----.");
        addSymbol('.', ".-.-.-");
        addSymbol(',', "--..--");
        addSymbol('?', "..--..");
    }

    /**
     * Inserts a letter and its Morse code string into the encoding map
     * and calls treeInsert to insert them into the decoding tree.
     */
    private static void addSymbol(char letter, String code)
    {
        codeMap.put(letter, code);
        treeInsert(letter, code);
    }

    /**
     * Inserts a letter and its Morse code string into the
     * decoding tree.  Each dot-dash string corresponds to a path
     * in the tree from the root to a node: at a "dot" go left, at a "dash" go
     * right.  The node at the end of the path holds the symbol
     * for that code string.
     */
    private static void treeInsert(char letter, String code)
    {
        TreeNode currentNode = decodeTree;
        for (int i = 0; i < code.length(); i++){
            if (code.charAt(i) == DOT){
                if (currentNode.getLeft() == null)
                    currentNode.setLeft(new TreeNode(null));
                currentNode = currentNode.getLeft();
            }
            else if (code.charAt(i) == DASH){
                if (currentNode.getRight() == null)
                    currentNode.setRight(new TreeNode(null));
                currentNode = currentNode.getRight();
            }
        }

        currentNode.setValue(letter);

    }

    /**
     * Converts text into a Morse code message.  Adds a space after a dot-dash
     * sequence for each letter.  Other spaces in the text are transferred directly
     * into the encoded message.
     * Returns the encoded message.
     */
    public static String encode(String text)
    {
        StringBuffer morse = new StringBuffer(400);

        String marconimsg = "";
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) != ' '){
                char marconichar = text.charAt(i);
                marconimsg += findpath(decodeTree.getLeft(), marconichar, ".");
                marconimsg += findpath(decodeTree.getRight(), marconichar, "-");
            }
            else {
                morse.append(marconimsg + " ");
                marconimsg = "";
            }
        }

        morse.append(marconimsg);
        return morse.toString();
    }

    public static String findpath(TreeNode node, char findchar, String path){

        TreeNode leftNode = node.getLeft();
        TreeNode rightNode = node.getRight();

        findchar = Character.toUpperCase(findchar);
        String message = "";

        if (node.getValue() != null && node.getValue().equals((Object) findchar))
            return path + " ";

        if (leftNode != null)
            message += findpath(leftNode, findchar, path + ".");

        if (rightNode != null)
            message +=  findpath(rightNode, findchar, path + "-");
        
        return message;
    }

    /**
     * Converts a Morse code message into a text string.  Assumes that dot-dash
     * sequences for each letter are separated by one space.  Additional spaces are
     * transferred directly into text.
     * Returns the plain text message.
     */
    public static String decode(String morse)
    {
        StringBuffer text = new StringBuffer(100);
        String[] letters = morse.split(" ");
        
        for (String letter : letters) {
            TreeNode currentNode = decodeTree;
            for (int j = 0; j < letter.length(); j++) {
                if (letter.charAt(j) == DOT)
                    currentNode = currentNode.getLeft();
                else if (letter.charAt(j) == DASH)
                    currentNode = currentNode.getRight();
            }
            
            if (currentNode != null && currentNode.getValue() != null) {
                text.append(currentNode.getValue());
            } else {
                text.append(" ");
            }
        }
        
        return text.toString();
    }
}

/**
 * BTreePrinter class courtesy of Karen Ge (@karenge1)
 */
class BTreePrinter {

    public static void printNode(TreeNode root) {
        int maxLevel = BTreePrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    }

    private static void printNodeInternal(List<TreeNode> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || BTreePrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        BTreePrinter.printWhitespaces(firstSpaces);

        List<TreeNode> newNodes = new ArrayList<TreeNode>();
        for (TreeNode node : nodes) {
            if (node != null) {
                newNodes.add(node.getLeft());
                newNodes.add(node.getRight());
            } else {
                newNodes.add(null);
                newNodes.add(null);
            }

            BTreePrinter.printWhitespaces(betweenSpaces);
        }

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                BTreePrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    BTreePrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).getLeft() != null)
                    System.out.print("/");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).getRight() != null)
                    System.out.print("\\");
                else
                    BTreePrinter.printWhitespaces(1);

                BTreePrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    }

    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }

    private static int maxLevel(TreeNode node) {
        if (node == null)
            return 0;

        return Math.max(BTreePrinter.maxLevel(node.getLeft()), 
            BTreePrinter.maxLevel(node.getRight())) + 1;
    }

    private static <T> boolean isAllElementsNull(List<T> list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    }
}

