import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author Rolf Versluis (rzv0018@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version TODAY
 */
public class Extractor {

   /**
    * raw data: all (x,y) points from source data.
    */
   private Point[] points;

   /**
    * lines identified from raw data.
    */
   private SortedSet<Line> lines;

   /**
    * Builds an extractor based on the points in the file named by filename.
    */
   public Extractor(String filename) throws FileNotFoundException {
      Scanner scanner = new Scanner(new File(filename));
      String line = "";
      if (scanner.hasNextLine()) {
         line = scanner.nextLine();
      }
      int pointCount = Integer.parseInt(line);
      points = new Point[pointCount];
      int counter = 0;
      while (scanner.hasNextLine() && counter < pointCount) {
         //System.out.println("loop number " + counter);
         line = scanner.nextLine().replaceAll("^\\s+", "");;
         //System.out.println(line);
         String[] rawPoints = line.split("\\s+");
         if (rawPoints.length > 1) {
            int x = Integer.parseInt(rawPoints[0]);
            int y = Integer.parseInt(rawPoints[1]);
            points[counter] = new Point(x,y);
            //System.out.println("added point " + counter + " " + x + " " + y);
            counter++;
         }
      }
   }

   /**
    * Builds an extractor based on the points in the Collection named by pcoll.
    * <p>
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }

   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      Line workingLine = new Line();
      int qtyPoints = points.length;
      if (qtyPoints > 200) {
         qtyPoints = 200;
      }
      for (int i = 0; i < qtyPoints; i++) {
         for (int j = i; j < qtyPoints; j++) {
            for (int k = j; k < qtyPoints; k++) {
               for (int l = k; l < qtyPoints; l++) {
                  if (i != j & i != k & i != l & j != k & j != l & k != l) {
                     //System.out.println("testing points " + points[i] + " " + points[j] + " " + points[k] + " " + points[l] + " ");
                     workingLine.add(points[i]);
                     workingLine.add(points[j]);
                     workingLine.add(points[k]);
                     workingLine.add(points[l]);
                  }
                  if (workingLine.length() > 3) {
                     lines.add(workingLine);
                     //System.out.println("Found a brute force line");
                  }
                  workingLine = new Line();
               }
            }
         }
      }
      // search array of points to see if slope is same
      return lines;
   }

   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      // create a new data structure for the sort, contains a point and slope
      Comparator<PointSlope> slopeComp = new SlopeCompare();
      List<PointSlope> workingSet;
      // choose a point
      for (int j = 0; j < points.length; j++) {
         Point workingPoint = points[j];
         //System.out.println("Point " + points[j].toString());
         // copy array into new structure
         workingSet = new ArrayList<>();
         for (int i = 0; i < points.length; i++) {
            workingSet.add(new PointSlope(points[i], workingPoint.slopeTo(points[i])));
         }
         workingSet.sort(slopeComp);
         for (PointSlope item : workingSet) {
            //System.out.println(item.toString());
         }
         // sort copied array
         workingSet.sort(slopeComp);
         // scan copied array for groups of slopes
         // extract groups into lines
         Line workingLine = new Line();
         Double currentSlope = 0.0;
         for (PointSlope item : workingSet) {
            if (!item.slope.equals(currentSlope)) {
               if (workingLine.length() > 3) {
                  lines.add(workingLine);
                  //System.out.println("Found a fast line");
               }
               workingLine = new Line();
               workingLine.add(workingPoint);
               workingLine.add(item.point);
               currentSlope = item.slope;
               //System.out.println("Item slope: " + item.slope + " Working slope: " + currentSlope+ " length " +workingLine.length());
            } else {
               workingLine.add(item.point);
               //System.out.println("Same slope!" + item.slope + " length = " + workingLine.length());
            }
         }
         if (workingLine.length() > 3) {
            lines.add(workingLine);
            //System.out.println("Found a fast line");
         }
         // choose next point and repeat
      }
      return lines;
   }

   private class PointSlope {
      private Point point;
      private Double slope;

      private PointSlope(Point point, Double slope) {
         this.point = point;
         this.slope = slope;
      }

      @Override
      public boolean equals(Object obj) {
         if (obj == null) {
            return false;
         }
         if (obj == this) {
            return true;
         }
         if (!(obj instanceof PointSlope)) {
            return false;
         }
         PointSlope that = (PointSlope) obj;
         return this.point.equals(that.point) && this.slope.equals(that.slope);
      }

      @Override
      public String toString() {
         return "PointSlope{" + "point=" + point + ", slope=" + slope + '}';
      }
   }

   private class SlopeCompare implements Comparator<PointSlope> {

      @Override
      public int compare(PointSlope pointSlope, PointSlope t1) {
         return pointSlope.slope.compareTo(t1.slope);
      }
   }
}
