package Ex2;

public class Location implements Ex2.src.api.GeoLocation {
    private double x, y, z;

    @Override
    public String toString() {
        return
                "x=" + x +
                        ", y=" + y +
                        ", z=";
    }

    public Location(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public double x() {
        return x;
    }

    @Override
    public double y() {
        return y;
    }

    @Override
    public double z() {
        return z;
    }

    @Override
    public double distance(Ex2.src.api.GeoLocation g) {
        return Math.sqrt((Math.pow(x - g.x(), 2) + Math.pow(y - g.y(), 2) + Math.pow(z - g.z(), 2)));
    }
}
