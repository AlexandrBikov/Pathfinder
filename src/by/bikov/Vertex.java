package by.bikov;

public class Vertex {

    private int value;
    private Vertex previous;
    private boolean isVisited;

    public Vertex(int value)
    {
        this.value = value;
        isVisited = false;
        previous = null;
    }

    public int getValue() {
        return value;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public Vertex getPrevious() {
        return previous;
    }

    public void setPrevious(Vertex previous) {
        this.previous = previous;
    }
}