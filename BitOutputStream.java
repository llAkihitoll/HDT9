import java.io.*;

public class BitOutputStream implements Closeable {
    private OutputStream out;
    private int currentByte;
    private int numBitsFilled;

    public BitOutputStream(OutputStream out) {
        this.out = out;
        currentByte = 0;
        numBitsFilled = 0;
    }

    public void writeBit(int bit) throws IOException {
        if (bit != 0 && bit != 1)
            throw new IllegalArgumentException("Bit must be 0 or 1");
        currentByte = (currentByte << 1) | bit;
        numBitsFilled++;
        if (numBitsFilled == 8) {
            out.write(currentByte);
            numBitsFilled = 0;
        }
    }

    @Override
    public void close() throws IOException {
        while (numBitsFilled != 0)
            writeBit(0);
        out.close();
    }
}

