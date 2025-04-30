import java.io.*;

/**
 * Clase que realiza la descompresión de archivos usando Huffman.
 */
public class Decompressor {
    public static void decompress(String inputHuff, String inputTree, String outputFile) throws IOException {
        // Cargar árbol
        HuffmanNode root;
        try (DataInputStream treeIn = new DataInputStream(new FileInputStream(inputTree))) {
            root = HuffmanTree.loadTree(treeIn);
        }

        // Leer archivo comprimido y decodificar
        try (BitInputStream bitIn = new BitInputStream(new FileInputStream(inputHuff));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            HuffmanNode current = root;
            int bit;
            while ((bit = bitIn.readBit()) != -1) {
                current = (bit == 0) ? current.left : current.right;

                if (current.isLeaf()) {
                    writer.write(current.character);
                    current = root;
                }
            }
        }
    }
}
