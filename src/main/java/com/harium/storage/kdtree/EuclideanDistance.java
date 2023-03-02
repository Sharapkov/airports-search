// EuclideanDistance.java : Class for Euclidean distance metric
//
// Copyright (C) Simon D. Levy 2014
//
// This code is free software: you can redistribute it and/or modify
// it under the terms of the GNU Lesser General Public License as 
// published by the Free Software Foundation, either version 3 of the 
// License, or (at your option) any later version.
//
// This code is distributed in the hope that it will be useful,     
// but WITHOUT ANY WARRANTY without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License 
//  along with this code.  If not, see <http://www.gnu.org/licenses/>.
//  You should also have received a copy of the Parrot Parrot AR.Drone 
//  Development License and Parrot AR.Drone copyright notice and disclaimer 
//  and If not, see 
//   <https://projects.ardrone.org/attachments/277/ParrotLicense.txt> 
// and
//   <https://projects.ardrone.org/attachments/278/ParrotCopyrightAndDisclaimer.txt>.

package com.harium.storage.kdtree;

class EuclideanDistance extends DistanceMetric {

protected double distance(double[] a, double[] b) {
        double R = 6371.0; // Земной радиус в км
        double lat1 = Math.toRadians(a[0]);
        double lon1 = Math.toRadians(a[1]);
        double lat2 = Math.toRadians(b[0]);
        double lon2 = Math.toRadians(b[1]);
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double h = Math.sin(dlat / 2) * Math.sin(dlat / 2) + Math.cos(lat1) * Math.cos(lat2) * Math.sin(dlon / 2) * Math.sin(dlon / 2);
        double c = 2 * Math.atan2(Math.sqrt(h), Math.sqrt(1 - h));
        return R * c;
    }

    protected static double sqrdist(double[] a, double[] b) {
        double dist = 0;
        for (int i = 0; i < a.length; i++) {
            double x = a[i];
            double y = b[i];
            if (i == 0 || i == 1) {
                x = Math.toRadians(x);
                y = Math.toRadians(y);
            }
            double delta = y - x;
            dist += Math.pow(delta, 2);
        }
        return dist;
    }
}
