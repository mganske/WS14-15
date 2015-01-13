package uebung04;

/**
 *
 * @author huegel
 */
class Point {
    //x- und y-Koordinate
    private double x,y;
    //Neuer Punkt mit Koordinaten x und y
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }
    //Neuer Punkt mit Koordinaten 0 und 0
    public Point(){
        this(0,0);
    }
    //Koordinaten abfragen
    public double getX(){
        return x;
    }    
    public double getY(){
        return y;
    }
    // Punkt in x−Richtung um dx und in y−Richtung um dy verschieben.
    public void move(double dx, double dy){
        x += dx;
        y += dy;
    }
    public void print(){
        System.out.println("(" + this.x + "," + this.y + ")");
    }
}
class Line{
    private Point begin, end;
    public Line (Point begin, Point end){
        this.begin = begin;
        this.end = end;
    }
    //Start- und Endpunkt abfragen
    public Point getBegin(){
        return begin;
    }
    public Point getEnd(){
        return end;
    }
    //Länge der Linie ausrechnen und zurückliefern
    public double length(){
        double dx = begin.getX() - end.getX();
        double dy = begin.getY() - end.getY();
        
        return Math.sqrt(dx * dx + dy * dy);
    }
}
public class Test{
    public static void main(String[] args) {
        //Punkt p1 mit x-Koordinate 1.5 und y-Koordinate -3.7 erzeugen
        Point p1 = new Point(1.5, -3.7);
        // Koordinaten von p1 abfragen und ausgeben.
        System.out.println("x−Koordinate: " + p1.getX());
        System.out.println("y−Koordinate: " + p1.getY());
        // Punkt p1 in x−Richtung um 2.5 und in y−Richtung um 0.7 verschieben.
        p1.move(2.5, 0.7);
        // Punkt p1 ausgeben.
        p1.print();
        Point p2 = new Point();
        
        // Linie mit Anfangspunkt p1 und Endpunkt p2 erzeugen.
        Line ln = new Line(p1, p2);
        System.out.println("Line:");
        // Anfangs− und Endpunkt von ln abfragen und ausgeben.
        System.out.print("Anfangspunkt: ");
        ln.getBegin().print(); // Ausgabe: (4.0, −3.0)
        System.out.print("Endpunkt: ");
        ln.getEnd().print();    // Ausgabe: (0.0, 0.0)
        // Länge von ln berechnen und ausgeben.
        System.out.println("Länge: " + ln.length());
    }
    
}
