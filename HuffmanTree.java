import java.util.*;
import java.io.*;

/**
 * Clase que construye y maneja el árbol de Huffman.
 */
public class HuffmanTree {
    private HuffmanNode root;
    private Map<Character, String> codes;

    public HuffmanTree(Map<Character, Integer> frequencies) {
        buildTree(frequencies);
        codes = new HashMap<>();
        buildCodes(root, "");
    }

    private void buildTree(Map<Character, Integer> frequencies) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();

        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.add(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        while (queue.size() > 1) {
            HuffmanNode left = queue.poll();
            HuffmanNode right = queue.poll();
            HuffmanNode parent = new HuffmanNode(left.frequency + right.frequency, left, right);
            queue.add(parent);
        }

        root = queue.poll();
    }

    private void buildCodes(HuffmanNode node, String code) {
        if (node == null) return;

        if (node.isLeaf()) {
            codes.put(node.character, code);
            return;
        }

        buildCodes(node.left, code + "0");
        buildCodes(node.right, code + "1");
    }

    public Map<Character, String> getCodes() {
        return codes;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    // Guardar árbol en recorrido preorden
    public void saveTree(DataOutputStream out) throws IOException {
        saveTree(root, out);
    }

    private void saveTree(HuffmanNode node, DataOutputStream out) throws IOException {
        if (node.isLeaf()) {
            out.writeBoolean(true);
            out.writeChar(node.character);
        } else {
            out.writeBoolean(false);
            saveTree(node.left, out);
            saveTree(node.right, out);
        }
    }

    // Reconstruir árbol desde archivo
    public static HuffmanNode loadTree(DataInputStream in) throws IOException {
        boolean isLeaf = in.readBoolean();
        if (isLeaf) {
            char ch = in.readChar();
            return new HuffmanNode(ch, 0);
        } else {
            HuffmanNode left = loadTree(in);
            HuffmanNode right = loadTree(in);
            return new HuffmanNode(0, left, right);
        }
    }
}

