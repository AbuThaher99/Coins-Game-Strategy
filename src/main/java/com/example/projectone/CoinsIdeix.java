package com.example.projectone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinsIdeix {
   private int n ;
  private    int[] coins = new int[n];

    private  int table[][] = new int[n][n];
    private  List<Integer> playerone = new ArrayList<>();
    private   List<Integer> playertwo = new ArrayList<>();
    private static   List<Integer> playerOneindices = new ArrayList<>();
    private static   List<Integer> playerTwoindices = new ArrayList<>();

    public CoinsIdeix() {
    }
    public int[] getCoins() {
        return coins;
    }

    public void setCoins(int[] coins) {
        this.coins = coins;
    }

    public int[][] getTable() {
        return table;
    }

    public void setTable(int[][] table) {
        this.table = table;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public List<Integer> getPlayerone() {
        return playerone;
    }

    public void setPlayerone(List<Integer> playerone) {
        this.playerone = playerone;
    }

    public List<Integer> getPlayertwo() {
        return playertwo;
    }

    public void setPlayertwo(List<Integer> playertwo) {
        this.playertwo = playertwo;
    }

    public  List<Integer> getPlayerOneindices() {
        return playerOneindices;
    }

    public  void setPlayerOneindices(List<Integer> playerOneindices) {
        CoinsIdeix.playerOneindices = playerOneindices;
    }

    public  List<Integer> getPlayerTwoindices() {
        return playerTwoindices;
    }

    public  void setPlayerTwoindices(List<Integer> playerTwoindices) {
        CoinsIdeix.playerTwoindices = playerTwoindices;
    }





    public int PrintTheOptimalStrategy(int arr[], int n) {
        int table[][] = new int[n][n];
        int x = 0, y = 0, z;
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = i; k < n; j++, k++) {
                if (i == 0) {
                    table[j][k] = arr[j];
                } else if (i == 1) {
                    table[j][k] = Math.max(arr[j], arr[k]);
                } else {
                    x = arr[j] + Math.min(table[j + 2][k], table[j + 1][k - 1]);
                    y = arr[k] + Math.min(table[j + 1][k - 1], table[j][k - 2]);
                    table[j][k] = Math.max(x, y);
                }

            }

        }

        return table[0][n - 1];
    }

    public  int[][] DPTable(int arr[], int n) {
        int table[][] = new int[n][n];
        int x = 0, y = 0, z;
        //   int coins[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = i; k < n; j++, k++) {
                if (i == 0) {
                    table[j][k] = arr[j];
                } else if (i == 1) {
                    table[j][k] = Math.max(arr[j], arr[k]);
                } else {
                    x = arr[j] + Math.min(table[j + 2][k], table[j + 1][k - 1]);
                    y = arr[k] + Math.min(table[j + 1][k - 1], table[j][k - 2]);
                    table[j][k] = Math.max(x, y);
                }

            }

        }

        return table;
    }




    public void assignCoinsToPlayersIndices(int[] coins, int[][] table) {
        int n = coins.length;
        int[] player1 = new int[n / 2];
        int[] player2 = new int[n / 2];
        int index1 = 0, index2 = 0;
        int j = 0, k = n - 1;

        while (j <= k && j < n && k >= 0) {
            // player 1 move first
            if (table[j][k] == coins[j] + Math.min(getTableValue(table, j + 2, k), getTableValue(table, j + 1, k - 1))) {
                playerOneindices.add(j);
                j++;
            } else if (table[j][k] == coins[k] + Math.min(getTableValue(table, j + 1, k - 1), getTableValue(table, j, k - 2))) {
                playerOneindices.add(k);
                k--;
            }



                if((j+2 == k|| k-2==j)&&coins[j]>coins[k]){
                    playerTwoindices.add(j);
                    j++;

                }else if((j+2 == k|| k-2==j)&&coins[j]<coins[k]){
                    playerTwoindices.add(k);
                    k--;
                }else if (table[j][k] == coins[j] + Math.min(getTableValue(table, j + 2, k), getTableValue(table, j + 1, k - 1))) {
                playerTwoindices.add(j);
                j++;
            } else if (table[j][k] == coins[k] + Math.min(getTableValue(table, j + 1, k - 1), getTableValue(table, j, k - 2))) {
                playerTwoindices.add(k);
                k--;
            }
        }
    }



    public  String PrintDPTable(int arr[], int n) {
        int table[][] = new int[n][n];
        int x =0 , y=0, z;
        int coins[] = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = i; k < n; j++, k++) {
                if (i == 0) {
                    table[j][k] = arr[j];
                } else if (i == 1) {
                    table[j][k] = Math.max(arr[j], arr[k]);
                } else {
                    x = arr[j] + Math.min(table[j + 2][k], table[j + 1][k - 1]);
                    y = arr[k] + Math.min(table[j + 1][k - 1], table[j][k - 2]);
                    table[j][k] = Math.max(x, y);
                }

            }

        }
        String s = "";
        // Print the DP table
        for (int i = 0; i < n; i++) {
            //   System.out.println(Arrays.toString(table[i]));
            s+=Arrays.toString(table[i])+"\n";

        }
        return s ;
    }



    public void assignCoinsToPlayersForPlayerOneAndTwo(int[] coins, int[][] table) {
        int n = coins.length;
        int[] player1 = new int[n / 2];
        int[] player2 = new int[n / 2];
        int index1 = 0, index2 = 0;
        int j = 0, k = n - 1;

        while (j <= k && j < n && k >= 0) {
            // player 1 move first
            if (table[j][k] == coins[j] + Math.min(getTableValue(table, j + 2, k), getTableValue(table, j + 1, k - 1))) {
                player1[index1++] = coins[j];
                j++;
            } else if (table[j][k] == coins[k] + Math.min(getTableValue(table, j + 1, k - 1), getTableValue(table, j, k - 2))) {
                player1[index1++] = coins[k];
                k--;
            }



            // player 2 move
            if((j+2 == k|| k-2==j)&&coins[j]>coins[k]){
                player2[index2++] = coins[j];
                j++;

            }else if((j+2 == k|| k-2==j)&&coins[j]<coins[k]){
                player2[index2++] = coins[k];
                k--;
            }else if (table[j][k] == coins[j] + Math.min(getTableValue(table, j + 2, k), getTableValue(table, j + 1, k - 1))) {
                player2[index2++] = coins[j];
                j++;
            } else if (table[j][k] == coins[k] + Math.min(getTableValue(table, j + 1, k - 1), getTableValue(table, j, k - 2))) {
                player2[index2++] = coins[k];
                k--;
            }
        }

        // add the coins to the playerone and playertwo lists
        for (int i = 0; i < player1.length; i++) {
            playerone.add(player1[i]);
        }

        for (int i = 0; i < player2.length; i++) {
            playertwo.add(player2[i]);
        }
    }

    private int getTableValue(int[][] table, int i, int j) {
        // return 0 if the indices are out of bounds
        if (i < 0 || i >= table.length || j < 0 || j >= table[i].length) {
            return 0;
        }
        return table[i][j];
    }
}
