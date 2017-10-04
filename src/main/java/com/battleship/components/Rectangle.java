package com.battleship.components;

import org.apache.commons.lang3.Range;

public class Rectangle {

    private Point beginPoint;
    private Point endPoint;

    public Rectangle(Point beginPoint, Point endPoint) {
        assert beginPoint.getX() <= endPoint.getX();
        assert beginPoint.getY() <= endPoint.getY();

        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }

    public boolean intersectsWith(Rectangle thatRectangle) {

        Range<Integer> thisSquareRangeX = Range.between(beginPoint.getX(), endPoint.getX());
        Range<Integer> thisSquareRangeY = Range.between(beginPoint.getY(), endPoint.getY());

        Range<Integer> thatSquareRangeX = Range.between(thatRectangle.beginPoint.getX(), thatRectangle.endPoint.getX());
        Range<Integer> thatSquareRangeY = Range.between(thatRectangle.beginPoint.getY(), thatRectangle.endPoint.getY());

        return thisSquareRangeX.isOverlappedBy(thatSquareRangeX) && thisSquareRangeY.isOverlappedBy(thatSquareRangeY);
    }

}
