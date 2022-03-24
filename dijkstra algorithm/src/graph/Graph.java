/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

/**
 *
 * @author abdel
 */
public class Graph {
    boolean[] Known;
    int[] Cost; 
    int[] Path;
    int[][] Matrix;
    int n;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         int Dijkstra[][] = new int[][] { {-1,8,-1,-1,-1,-1,-1,-1}, 
                                       {8,-1, -1,-1,-1,7, 9,-1}, 
                                       { -1,-1,-1,-1,8,-1,3,-1 }, 
                                       {-1,-1,-1,-1,-1,1,-1,9 }, 
                                       { -1,-1,8,-1,-1,-1, 7, -1}, 
                                       {-1,7,-1, 1,-1,-1,4,-1}, 
                                       {-1,9,3,-1, 7,4,-1,1},
                                       {-1,-1,-1, 9 , -1,-1, 1,-1}};
         
         int Prim[][] = new int[][] { {-1,9,6,8,-1,-1,-1,-1}, 
                                       {9,-1,7,4,-1,1, -1,-1}, 
                                       { 6,7,-1,-1,2,3,3,-1 }, 
                                       {8,4,-1,-1,-1,-1,-1,-1 }, 
                                       { -1,-1,2,-1,-1,-1,-1, 8}, 
                                       {-1,1,3, -1,-1,-1,-1,2}, 
                                       {-1, -1,3,-1, -1,-1,-1,2},
                                       {-1,-1,-1, -1 , 8,2,2,-1}}; 
         int s=0;
         
         
        Graph t = new Graph(); 
        t.ini(Prim);
        System.out.println("Prim");
        t.primAlgorithm(s);
        System.out.println("***********************************************************************");
        System.out.println("Dijkstra");
        t.ini(Dijkstra);
        t.DijkstraAlgorithm(s);
    }
    public void ini(int adjacencyMatrix[][])
    {
        n=adjacencyMatrix.length;
        Matrix=new int[n][n];
        Cost = new int[n];
        Path = new int[n];
        Known=new boolean[n];
        for (int source = 0; source < n; source++)
        {
            for (int destination = 0; destination < n; destination++)
            {
                this.Matrix[source][destination] = adjacencyMatrix[source][destination];
            }
        }
 
        for (int index = 0; index < n; index++)
        {
            Cost[index] = Integer.MAX_VALUE;
            Path[index]=-1;
        }
    }
    public void primAlgorithm(int s)
    {
        Cost[s]=0;
        while (getUnKnownCount() != 0)
        {
            int min=getMimumCostVertex();
            Known[min]=true;
            setNeighboursPrim(min);
        }
        printMST(s,false);
    }
    public void DijkstraAlgorithm(int s)
    {
        Cost[s]=0;
        while (getUnKnownCount() != 0)
        {
            int min=getMimumCostVertex();
            Known[min]=true;
            setNeighboursDijkstra(min);
        }
        printMST(s,true);
    }
    public int getUnKnownCount()
    {
        int count = 0;
        for (int i = 0; i <n; i++)
        {
            if (!Known[i])
            {
                count++;
            }
        }
        return count;
    }
    public int getMimumCostVertex()
    {
        int min = Integer.MAX_VALUE;
        int node = -1;
        for (int vertex = 0; vertex < n; vertex++)
        {
            if (Known[vertex] == false && Cost[vertex] < min)
            {
                node = vertex;
                min = Cost[vertex];
            }
        }
        return node;
    }
    public void setNeighboursPrim(int v)
    {
        for(int i=0;i<n;i++)
        {
            if(Matrix[v][i]!=-1&&!Known[i])
            {
                int currentCost=Matrix[v][i];
                if(Cost[i]>currentCost)
                {
                    Cost[i]=currentCost;
                    Path[i]=v;
                }
            }
        }
    }
    public void setNeighboursDijkstra(int v)
    {
        for(int i=0;i<n;i++)
        {
            if(Matrix[v][i]!=-1&&!Known[i])
            {
                int currentCost=Matrix[v][i]+Cost[v];
                if(Cost[i]>currentCost)
                {
                    Cost[i]=currentCost;
                    Path[i]=v;
                }
            }
        }
    }
    public void printMST(int s,boolean isDijkstra)
    {
        System.out.println("SOURCE  : DESTINATION \t= \tCOST");
        for (int vertex = 0; vertex < n; vertex++)
        {
            System.out.print((s==vertex?"s":vertex) + "\t:\t" + Path[vertex] +"\t=\t"+ Cost[vertex]+"\t\t");
            if(isDijkstra)
                printPath(vertex);
            System.out.println();
        }
    }
    public void printPath(int v)
    {
        if(v==-1)
            return;
        printPath(Path[v]);
        System.out.print(v+" ");
    }
}
