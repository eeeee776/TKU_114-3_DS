public class DeliveryTask {
    private String id;          // 配送編號
    private String destination; // 目的地

    public DeliveryTask(String id, String destination) {
        this.id = id;
        this.destination = destination;
    }

    public String getId() { return id; }
    public String getDestination() { return destination; }

    @Override
    public String toString() {
        return "[" + id + "] " + destination;
    }
}