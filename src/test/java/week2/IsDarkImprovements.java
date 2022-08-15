package week2;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Objects;

@RunWith(JUnit4.class)
public class IsDarkImprovements {
    int N = 4096;
    byte image[][] = new byte[N][N];

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

    public boolean isDarkCached() {
        var count = 0;
        var cacheCellIsLight = new Boolean[256];

        for (int j = 0; j < N; ++j) {
            for (int i = 0; i < N; ++i) {
                byte color = image[i][j];
                if (Objects.isNull(cacheCellIsLight[color])) {
                    if (image[i][j] >= 128) {
                        count += 1;
                        cacheCellIsLight[color] = true;
                    }
                } else {
                    if (cacheCellIsLight[color]) {
                        count++;
                    }
                }

            }
        }
        return count < N * N / 2;
    }

    public boolean isDarkOptimized() {
        var lightCount = 0;
        var darkCount = 0;
        while (lightCount <= N * N / 2 && darkCount <= N * N / 2) {
            for (int j = 0; j < N; ++j) {
                for (int i = 0; i < N; ++i) {
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
