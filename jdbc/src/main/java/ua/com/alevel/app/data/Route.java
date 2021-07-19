package ua.com.alevel.app.data;

public class Route {

    private int id;
    private int fromId;
    private int toId;
    private int cost;

    public void setId(int id) {
        this.id = id;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int from_id) {
        this.fromId = from_id;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int to_id) {
        this.toId = to_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id=" + id +
                ", fromId=" + fromId +
                ", toId=" + toId +
                ", cost=" + cost +
                '}';
    }
}
