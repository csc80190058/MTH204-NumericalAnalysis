/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mth204.alu.factorization;

//import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;
import java.util.Scanner;

/**
 *
 * @author Erfan.ra
 */
public class MTH204ALUFactorization {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        String Y;
        //Y = input.nextLine();
        Y = "10,2,2,91,10#5,6,800,8,9#9,6,7,12,3#2,7,8,30,4#12,5,6,90,32";
        
        double A [][] = lineToArray(Y);
        int n = A[0].length;
        // assuming the factorization is possible
        double L[][] = new double[n][n];
        double U[][] = new double[n][n];
        
        // Step 1:
        L[0][0] = A[0][0];
        U[0][0] = 1;
        
        //Step 2
        
        for (int j = 1; j<n;j++)
        {
            U[0][j] = A[0][j] / L[0][0]; //1st row of U
            L[j][0] = A[j][0] / U[0][0]; //1st column of L
        }
        
        //Step 3
        for(int i = 1; i<n-1;i++)
        {
            //Step 4
            double sum1 = 0;
            for (int k = 0 ; k < i; k++)
            {
                sum1 = sum1 + L[i][k]*U[k][i];    
            }
            
            L[i][i] = A[i][i] - sum1;
            U[i][i] = 1;
                
            
            //Step 5
            for(int j = i+1; j < n; j++)
            {
                double sum2 = 0, sum3 = 0;
                for(int k = 0; k<i;k++)
                {
                    sum2 = sum2 + L[i][k]*U[k][j];
                }
                U[i][j] = (A[i][j] - sum2)/L[i][i];
                
                for(int k = 0; k<i;k++)
                {
                    sum3 = sum3 + L[j][k]*U[k][i];
                }
                    
                    
                  
                L[j][i] = (A[j][i] - sum3)/U[i][i];  
                    
                
                
            }
            
        }
        
        //Step 6
        double sum4 = 0;
        for(int k = 0; k <n-1;k++)
        {
            sum4 = sum4 + L[n-1][k]*U[k][n-1];
            
        }
        L[n-1][n-1]= A[n-1][n-1] - sum4;
        U[n-1][n-1] = 1;
        
        //10,2,2,9,0#5,6,8,8,9#9,6,7,12,3#23,7,8,3,4#12,5,6,9,32
        
        System.out.println();
        System.out.println("============ Matrix A =============");
        System.out.println();
        printArray(A);
        System.out.println();
        System.out.println("============ Matrix L =============");
        System.out.println();
        printArray(L);
        System.out.println();
        System.out.println("============ Matrix U =============");
        System.out.println();
        printArray(U);
    }
    
    public static double[][] lineToArray (String L)
    
    {
        String[] R = L.split("#");
        int row = R.length;
        //System.out.println("row = :" + row);

        String[] e = R[0].split(",");
        int col = e.length;
        //System.out.println("col = :" + col);
        double [][] X = new double [row][col];
        for (int i = 0 ; i < row ; i++)
        {
            
            for(int j = 0; j < col ; j++)
            {
                String []y = R[i].split(",");
                String z = y[j];
                X[i][j] = Double.parseDouble(z);
                
                //System.out.printf("\t%d",X[i][j]);
                
            }
            
            //System.out.println();
        }
        int w = X.length;
        return X;
    }
    
    public static void printArray(double [][] P)
    {
        
        for (int i = 0 ; i < P.length ; i++)
        {
            for (int j = 0 ; j < P[0].length ; j ++)
            {
                System.out.printf("%.10f \t", P[i][j]);
            }
            System.out.println();
        }
    }
}
