package ua.telesens.ostapenko.transportmanager.alchoritm.deicstra;

/**
 * @author root
 * @since 29.01.16
 */
public class Graph {
    private static final int MAX_NAME_STATION = 100;
    private final int MAX_VERTS;
    private final int INFINITY = 1000000;
    private Vertex vertexList[]; // list of vertices
    private int adjMat[][];      // adjacency matrix
    private int nVerts;          // current number of vertices
    private int nTree;           // number of verts in tree
    private DistPar sPath[];     // array for shortest-path data
    private int currentVert;     // current vertex
    private int startToCurrent;  // distance to currentVert
    private int startPoint;      // The initial index of the top of the search
    private int endPoint;        // The final index of the top of the search

    public Graph(int maxVerts) {
        this.MAX_VERTS = validationNumbersVertex(maxVerts);
        vertexList = new Vertex[MAX_VERTS];
        // adjacency matrix
        adjMat = new int[MAX_VERTS][MAX_VERTS];
        nVerts = 0;
        nTree = 0;
        for (int j = 0; j < MAX_VERTS; j++)       // set adjacency
            for (int k = 0; k < MAX_VERTS; k++)   //     matrix
                adjMat[j][k] = INFINITY;          //     to infinity
        sPath = new DistPar[MAX_VERTS];           // shortest paths
    }

    public void addVertex(String lab) {
        validationLabel(lab);
        if (nVerts == MAX_VERTS) {
            throw new IllegalArgumentException("The maximum number of vertices is equal to " + MAX_VERTS + "!");
        }
        vertexList[nVerts++] = new Vertex(lab);
    }

    public void addEdge(int start, int end, int weight) {
        validationTransportConnection(start, end, weight);
        adjMat[start][end] = weight;
    }


    // find all shortest paths
    public int path(String firstCity, String secondCity) {
        this.startPoint = findIndexVertexByLabel(firstCity);
        this.endPoint = findIndexVertexByLabel(secondCity);

        int startTree = startPoint;             // start at vertex 0
        vertexList[startTree].isInTree = true;
        nTree = 1;                     // put it in tree

        // transfer row of distances from adjMat to sPath
        for (int j = 0; j < nVerts; j++) {
            int tempDist = adjMat[startTree][j];
            sPath[j] = new DistPar(startTree, tempDist);
        }

        // until all vertices are in the tree
        while (nTree < nVerts) {
            int indexMin = getMin();    // get minimum from sPath
            int minDist = sPath[indexMin].distance;

            if (minDist == INFINITY) {
                System.out.println("There are unreachable vertices");
                break;                   // sPath is complete
            } else {                        // reset currentVert
                currentVert = indexMin;  // to closest vert
                startToCurrent = sPath[indexMin].distance;
                // minimum distance from startTree is
                // to currentVert, and is startToCurrent
            }
            // put current vertex in tree
            vertexList[currentVert].isInTree = true;
            nTree++;
            adjust_sPath();             // update sPath[] array
        }

        displayPaths();
        nTree = 0;
        for (int j = 0; j < nVerts; j++)
            vertexList[j].isInTree = false;

        //return cost path
        if (sPath[endPoint].distance == INFINITY) {
            return -1;
        } else {
            return sPath[endPoint].distance;
        }
    }

    //Search vertex index by name
    private int findIndexVertexByLabel(String label) {
        for (int i = 0; i < vertexList.length; i++) {
            if (vertexList[i].label.equals(label)) return i;
        }
        throw new IllegalArgumentException("Label not found: " + label);
    }

    private int getMin() {
        int minDist = INFINITY;              // assume minimum
        int indexMin = 0;
        for (int j = 0; j < nVerts; j++)    // for each vertex,
        {                                   // if it's in tree and
            if (!vertexList[j].isInTree &&  // smaller than old one
                    sPath[j].distance < minDist) {
                minDist = sPath[j].distance;
                indexMin = j;
            }
        }
        return indexMin;
    }

    // adjust values in shortest-path array sPath
    private void adjust_sPath() {
        int column = 1;
        while (column < nVerts) {
            // if this column's vertex already in tree, skip it
            if (vertexList[column].isInTree) {
                column++;
                continue;
            }
            // calculate distance for one sPath entry
            // get edge from currentVert to column
            int currentToFringe = adjMat[currentVert][column];
            // add distance from start
            int startToFringe = startToCurrent + currentToFringe;
            // get distance of current sPath entry
            int sPathDist = sPath[column].distance;

            // compare distance from start with sPath entry
            if (startToFringe < sPathDist) { // if shorter,// update sPath
                sPath[column].parentVert = currentVert;
                sPath[column].distance = startToFringe;
            }
            column++;
        }
    }

    //The resulting cost of transportation
    // gdansk --> (warszawa) 3
    public void displayPaths() {
        String parent = vertexList[sPath[startPoint].parentVert].label;
        System.out.print(parent);
        if (sPath[endPoint].distance == INFINITY) {
            System.out.println(" inf (" + vertexList[endPoint].label + ")");
        } else {
            System.out.print(" --> (" + vertexList[endPoint].label + ") ");
            System.out.println(sPath[endPoint].distance);
        }
    }

    public String getPaths() {
        String parent = vertexList[sPath[startPoint].parentVert].label;
        StringBuilder result = new StringBuilder();
        result.append(parent);
        result.append("\n");
        if (sPath[endPoint].distance == INFINITY) {
            result.append(" inf (").append(vertexList[endPoint].label).append(")");
            result.append("\n");
        } else {
            result.append(" --> (").append(vertexList[endPoint].label).append(") ");
            result.append("\n");
            result.append(sPath[endPoint].distance);
        }
        return result.toString();
    }

    //The name of a city is a string containing characters a,...,z and is at most 10 characters.
    private String validationLabel(String lab) {
        // verify an input string length
        if (lab.length() > MAX_NAME_STATION) {
            throw new IllegalArgumentException("The name of a station  is  not most 100 character! ");
        }
        return lab.trim();
    }

    //Validation number of cities
    // The number of cities not exceed 10000
    private int validationNumbersVertex(int maxVerts) {
        if (maxVerts > 10000) {
            throw new IllegalArgumentException("The number of cities not exceed 10000!");
        }
        return maxVerts;
    }

    //Each is a direct link between the two cities is the transportation cost (an integer greater than 0)
    private void validationTransportConnection(int start, int end, int weight) {
        if (weight < 0)
            throw new IllegalArgumentException("Transportation cost (an integer bigger than 0).");
        if (start > MAX_VERTS || start < 0)
            throw new IllegalArgumentException("The id city does not exist : " + start);
        if (end > MAX_VERTS || end < 0)
            throw new IllegalArgumentException("The id city does not exist : " + end);
    }

    public static class Vertex {
        public String label;
        public boolean isInTree;

        public Vertex(String lab) {
            label = lab;
            isInTree = false;
        }
    }

    public static class DistPar {
        public int distance;   // distance from start to this vertex
        public int parentVert; // current parent of this vertex

        public DistPar(int pv, int d) {
            distance = d;
            parentVert = pv;
        }
    }
}
