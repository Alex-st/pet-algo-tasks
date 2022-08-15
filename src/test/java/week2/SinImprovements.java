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
        denomValues[0] = 1;
        int sign = -1;

        //cache denumerator value with sign (1, -3!, 5!,...)
        for (int index = 0; index < terms - 1; index++) {
            denomValues[index + 1] = sign / (2*index+2) * (2*index+3);
            sign = - sign;
        }

        for (int i = 0; i < N; i++) {
            float value = x[i];
            for (int j = 0; j < terms; j++) {
                value += value / denomValues[j];
                value *= x[i] * x[i];
            }
            result[i] = value;
        }
    }

    void sinxImprovedOld(int N, int terms, float[] x, float[] result) {
        int denomValues[] = new int[terms];
        denomValues[0] = 1;
        int sign = -1;

        //cache denumerator value with sign (1, 3!, 5!,...)
        for (int index = 0; index < terms - 1; index++) {
            denomValues[index + 1] = sign / (2*index+2) * (2*index+3);
            sign = - sign;
        }

        for (int i = 0; i < N; i++) {
            float value = 0;

            //create cache for all numerators of single x[i] (x[i], x[i]^3, x[i]^5, ....)
            float numerValuesForSingleX[] = new float[terms];
            numerValuesForSingleX[0] = x[i];
            for (int index = 1; index < terms; index++) {
                numerValuesForSingleX[index] = numerValuesForSingleX[index - 1] * x[i] * x[i];
            }

            //count result value
            for (int j = 0; j < terms; j++) {
                value += numerValuesForSingleX[j] / denomValues[j];
            }
            result[i] = value;
        }
    }
}
