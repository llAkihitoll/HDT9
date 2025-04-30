import java.io.*;

public class BitInputStream implements Closeable {
    private InputStream in;
    private int currentByte;
    private int numBitsRemaining;

    public BitInputStream(InputStream in) {
        this.in = in;
        currentByte = 0;
        numBitsRemaining = 0;
    }

    public int readBit() throws IOException {
        if (currentByte == -1)
            return -1;
        if (numBitsRemaining == 0) {
            currentByte = in.read();
            if (currentByte == -1)
                return -1;
            numBitsRemaining = 8;
        }
        numBitsRemaining--;
        return (currentByte >>> numBitsRemaining) & 1;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}

