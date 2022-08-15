package week2;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class IsDarkImprovements {
    int N = 4096;
    Byte image[][] = new Byte[N][N];

    public boolean isDark() {
        var count = 0;
        for (int j = 0; j < N; ++j) {
            for (int i = 0; i < N; ++i) {
                if (image[i][j] >= 128) {
                    count += 1;
                }
            }
        }
        return count < N * N / 2;
    }

    public boolean isDarkJavaOptimized() {
        var count = 0;

        int counterDelta[] = new int[256];
        for (int i = 128; i < counterDelta.length; i++) {
            counterDelta[i] = 1;
        }
            // starting reading by i not j
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    count = count + counterDelta[image[i][j]];
                }
            }

        return count < N * N / 2;
    }

}
