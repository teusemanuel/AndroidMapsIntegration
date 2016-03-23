/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.commons.androidmapsintegration.object;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by Mateus Emanuel Araújo on 23/03/16.
 * MA Solutions
 * teusemanuel@gmail.com
 */
public class MyItem implements ClusterItem {

    public final String name;
    public final int profilePhoto;
    private final double latitude;
    private final double longitude;

    //Create constructor, getter and setter here


    public MyItem(String name, int profilePhoto, double latitude, double longitude) {
        this.name = name;
        this.profilePhoto = profilePhoto;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(latitude, longitude);
    }

    public String getName() {
        return name;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
