// class Graph

public class Graph {
    // attributes
    public string description {
        set;
        get;
    }

    public DateTime CreationDate {
        get;
        set;
    }

    // Attributes due to assosiations
    public virtual ICollection<Node> nodes {
        set;
        get;
    }
    public virtual ICollection<Edge> edges {
        get;
        set;
    }

    // Constructors
    public Graph() {
        node = new List<Node>();
        edge = new List<Edge>()
    }

    public Graph(string d, DateTime c):this() {
        description = d;
        CreationDate = c;

    }
}

// class node

public class Node {
    // attributes
    public int id {
        get;
        set;
    }

    public virtual ICollection<Edge> source {
        get;
        set;
    }
    public virtual ICollection<Edge> destination {
        get;
        set;
    }

    // constructor
    public Node() {
        SourceEdges = new List<Edge>();
        DestinationEdges = new List<Node>();
    }
    public Node(int i):this(){
        id = i;
        
    }


}

// Class Edge

public class Edge {
    // attributes
    public double weight {
        get;
        set;
    }

    public virtual Node source {
        get;
        set;
    }
    public virtual Node destination {
        get;
        set;
    }

    // constructor
    public void Edge(double w, Node es, Node ed){
        weight = w;
        source = es;
        destination = ed;
    }

    
}

