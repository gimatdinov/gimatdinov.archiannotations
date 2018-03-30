package gimatdinov.archiannotations.extra;

import org.eclipse.draw2d.Connection;
import org.eclipse.draw2d.ConnectionLocator;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PointList;

public class ConnectionLabelMiddleLocator extends ConnectionLocator {

    private final Label label;

    public ConnectionLabelMiddleLocator(Connection connection, Label label) {
        super(connection, ConnectionLocator.MIDDLE);
        this.label = label;
    }

    @Override
    protected Point getLocation(PointList points) {
        Point result;
        if (points.size() % 2 == 0) {
            int i = points.size() / 2;
            int j = i - 1;
            Point p1 = points.getPoint(j);
            Point p2 = points.getPoint(i);
            Dimension d = p2.getDifference(p1);
            result = Point.SINGLETON.setLocation(p1.x + d.width / 2, p1.y + d.height / 2);
        } else {
            result = points.getPoint(Point.SINGLETON, (points.size() - 1) / 2);
        }
        int shift = 7 * label.getTextBounds().height / 10;
        result.setY(result.y() - shift);
        return result;
    }

}
