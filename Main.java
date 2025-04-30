/**
 * Programa principal para comprimir y descomprimir usando Huffman.
 */
public class Main {
    public static void main(String[] args) {
        try {
            // Comprimir
            Compressor.compress("pruebas/input1.txt", "pruebas/input1.huff", "pruebas/input1.hufftree");
            Compressor.compress("pruebas/input2.txt", "pruebas/input2.huff", "pruebas/input2.hufftree");

            // Descomprimir
            Decompressor.decompress("pruebas/input1.huff", "pruebas/input1.hufftree", "pruebas/output1.txt");
            Decompressor.decompress("pruebas/input2.huff", "pruebas/input2.hufftree", "pruebas/output2.txt");

            System.out.println("¡Compresión y descompresión exitosas!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
