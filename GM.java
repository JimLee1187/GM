package gm;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.StringTokenizer;

/**
 *
 * @author  LiJing 
 * @date    2018��1��19�� ����11:49:07
 * @Version 1.0
 *
 */
public class GM {

    /**
     * @param args
     */

    public double gm(List<Double> al, int T) {

    	double[] fs = new double[al.size()];
        for (int i = 0; i < fs.length; ++i)
        {
        	fs[i] = al.get(i);
        }
        // Ԥ��ģ�ͺ���
        int size = fs.length;
        int tsize = fs.length - 1;
        double[] arr = fs;// ԭʼ����
        double[] arr1 = new double[size];// ����һ���ۼ�����
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += arr[i];
            arr1[i] = sum;
        }
        double[] arr2 = new double[tsize];// arr1�Ľ��ھ�ֵ����
        for (int i = 0; i < tsize; i++) {
            arr2[i] = (double) (arr1[i] + arr1[i + 1]) / 2;
        }
        /*
         * 
         * ���潨�� ����B��YN���������������� �������a,b
         */
        /*
         * ���潨������B, B��5��2�еľ��� �൱��һ����ά���顣
         */
        double[][] B = new double[tsize][2];
        for (int i = 0; i < tsize; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 1)
                    B[i][j] = 1;
                else
                    B[i][j] = -arr2[i];
            }

        }
        /*
         * ���潨������YN
         */
        double[][] YN = new double[tsize][1];
        for (int i = 0; i < tsize; i++) {
            for (int j = 0; j < 1; j++) {
                YN[i][j] = arr[i + 1];
            }	
        }

        /*
         * B��ת�þ���BT,2��tsize�еľ���
         */
        double[][] BT = new double[2][tsize];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < tsize; j++) {
                BT[i][j] = B[j][i];
            }
        }
        /*
         * ��BT��B�ĳ˻����õ��Ľ����Ϊ����B2T,��B2T��һ��2*2�ľ���
         */
        double[][] B2T = new double[2][2];
        for (int i = 0; i < 2; i++) {// rows of BT
            {
                for (int j = 0; j < 2; j++)// cloums of B
                {
                    for (int k = 0; k < tsize; k++)// cloums of BT=rows of B
                    {
                        B2T[i][j] = B2T[i][j] + BT[i][k] * B[k][j];
                    }
                }

            }
        }
        /* ������B2T���������ΪB_2T����ô������һ��ľ��� */
        double[][] B_2T = new double[2][2];
        B_2T[0][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
                * B2T[1][1];
        B_2T[0][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
                * (-B2T[0][1]);
        B_2T[1][0] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
                * (-B2T[1][0]);
        B_2T[1][1] = (1 / (B2T[0][0] * B2T[1][1] - B2T[0][1] * B2T[1][0]))
                * B2T[0][0];
        /*
         * ������������ĸ���֪�����������������δ֪��a��b�����������������B_2T*BT*YN
         * �������Ƿֱ�����Щ����ĳ˻���������B_2T*BT����B_2T*BT�ĳ˻�ΪA������A����һ��2*5�ľ���
         */
        /*
         * 
         * 
         * 
         * ��������A����
         */
        double[][] A = new double[2][tsize];
        for (int i = 0; i < 2; i++) {// rows of B_2T
            {
                for (int j = 0; j < tsize; j++)// cloums of BT
                {
                    for (int k = 0; k < 2; k++)// cloums of B_2T=rows of BT
                    {
                        A[i][j] = A[i][j] + B_2T[i][k] * BT[k][j];
                    }
                }

            }
        }
        /*
         * 
         * 
         * ������A��YN����ĳ˻�����˻�ΪC������C����һ��2*1�ľ���
         */
        double[][] C = new double[2][1];
        for (int i = 0; i < 2; i++) {// rows of A

            {
                for (int j = 0; j < 1; j++)// cloums of YN
                {
                    for (int k = 0; k < tsize; k++)// cloums of A=rows of YN
                    {
                        C[i][j] = C[i][j] + A[i][k] * YN[k][j];
                    }
                }

            }
        }
        /* ��������������a=C[0][0],b=C[1][0]; */
        double a = C[0][0], b = C[1][0];
        int i = T-2;// ��ȡһ����ֵ
        double Y = (arr[0] - b / a) * Math.exp(-a * (i + 1)) - (arr[0] - b / a)
                * Math.exp(-a * i);
        return Y;
    }
}
