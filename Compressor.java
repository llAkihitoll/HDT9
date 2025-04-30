import java.util.*;
import java.io.*;

/**
 * Clase que realiza la compresión de archivos usando Huffman.
 */
public class Compressor {
    public static void compress(String inputFile, String outputHuff, String outputTree) throws IOException {
        // Leer archivo
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int c;
            while ((c = reader.read()) != -1) {
                text.append((char) c);
            }
        }

        // Calcular frecuencias
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char c : text.toString().toCharArray()) {
            frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
        }

        // Construir árbol
        HuffmanTree tree = new HuffmanTree(frequencies);
        Map<Character, String> codes = tree.getCodes();

        // Codificar texto
        StringBuilder encodedText = new StringBuilder();
        for (char c : text.toString().toCharArray()) {
            encodedText.append(codes.get(c));
        }

        // Guardar árbol
        try (DataOutputStream treeOut = new DataOutputStream(new FileOutputStream(outputTree))) {
            tree.saveTree(treeOut);
        }

        // Guardar texto codificado como binario
        try (BitOutputStream bitOut = new BitOutputStream(new FileOutputStream(outputHuff))) {
            for (char bit : encodedText.toString().toCharArray()) {
                bitOut.writeBit(bit == '1' ? 1 : 0);
            }
        }
    }
}

