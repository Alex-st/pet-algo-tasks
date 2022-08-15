package week2;

import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SinImprovements {

    void sinx(int N, int terms, float[] x, float[] result) {
        for (int i = 0; i < N; i++) {
            float value = x[i];
            float numer = x[i] * x[i] * x[i];
            int denom = 6; // 3!
            int sign = -1;
            for (int j = 1; j <= terms; j++) {
                value += sign * numer / denom;
                numer *= x[i] * x[i];
                denom *= (2*j+2) * (2*j+3);
                sign *= -1;
            }
            result[i] = value;
        }
    }

    void sinxImproved(int N, int terms, float[] x, float[] result) {
        int denomValues[] = new int[terms];
        denomValues[0] = -6;
        for (int index = 1; index < terms; index++) {
            denomValues[index] = (2*index+2) * (2*index+3);
        }

        for (int i = 0; i < N; i++) {
            float value = x[i];
            float numer = x[i] * x[i] * x[i];
            int sign = -1;
            for (int j = 1; j <= terms; j++) {
                value += sign * numer / denomValues[j];
                numer *= x[i] * x[i];
                sign *= -1;
            }
            result[i] = value;
        }
    }

}
