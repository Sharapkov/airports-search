package com.harium.storage.kdtree;

import java.lang.Math;

public class EarthDistance extends DistanceMetric {

    private double radius; // Радиус Земли в км

    public EarthDistance() {
        this.radius = radius;
    }

    protected double distance(double[] a, double[] b) {
        double lat1 = Math.toRadians(a[0]);
        double lon1 = Math.toRadians(a[1]);
        double lat2 = Math.toRadians(b[0]);
        double lon2 = Math.toRadians(b[1]);

        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double h = Math.sin(dlat/2) * Math.sin(dlat/2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon/2) * Math.sin(dlon/2);
        double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1-h));

        return radius * c;
    }
}
