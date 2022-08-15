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

    public boolean isDarkOptimized1() {
        var lightCount = 0;
        var darkCount = 0;
        while (lightCount <= N * N / 2 && darkCount <= N * N / 2) {
            // starting reading by i not j
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    if (image[i][j] >= 128) {
                        lightCount += 1;
                    } else {
                        darkCount +=1;
                    }
                }
            }
        }

        return lightCount < N * N / 2;
    }

    public boolean isDarkJavaOptimized2() {
        var lightCount = 0;
        var darkCount = 0;

        int counterDelta[] = new int[256];
        for (int i = 128; i < counterDelta.length; i++) {
            counterDelta[i] = 1;
        }

        while (lightCount <= N * N / 2 && darkCount <= N * N / 2) {
            // starting reading by i not j
            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < N; ++j) {
                    lightCount = lightCount + counterDelta[image[i][j]];
                    darkCount = darkCount + (1 - counterDelta[image[i][j]]);
                    if (image[i][j] >= 128) {
                        lightCount += 1;
                    } else {
                        darkCount +=1;
                    }
                }
            }
        }

        return lightCount < N * N / 2;
    }

}
