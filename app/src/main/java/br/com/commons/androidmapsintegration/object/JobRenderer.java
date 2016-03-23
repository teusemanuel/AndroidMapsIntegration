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

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.Iterator;

import br.com.commons.androidmapsintegration.R;

/**
 * Created by Mateus Emanuel Araújo on 23/03/16.
 * MA Solutions
 * teusemanuel@gmail.com
 */
public class JobRenderer extends DefaultClusterRenderer<MyItem> {

    private final IconGenerator iconGenerator;
    private final IconGenerator clusterIconGenerator;
    private final ImageView imageView;
    private final ImageView clusterImageView;
    private final int markerWidth;
    private final int markerHeight;
    private final String TAG = "ClusterRenderer";


    public JobRenderer(Context context, GoogleMap map, ClusterManager<MyItem> clusterManager) {
        super(context, map, clusterManager);

        // initialize cluster icon generator
        clusterIconGenerator = new IconGenerator(context.getApplicationContext());
        View clusterView = LayoutInflater.from(context).inflate(R.layout.multi_profile, null);
        clusterIconGenerator.setContentView(clusterView);
        clusterImageView = (ImageView) clusterView.findViewById(R.id.image);

        // Position the map.
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(-18.642229, -48.176849), 10));

        // initialize cluster item icon generator
        iconGenerator = new IconGenerator(context.getApplicationContext());
        imageView = new ImageView(context.getApplicationContext());
        markerWidth = (int) context.getResources().getDimension(R.dimen.custom_profile_image);
        markerHeight = (int) context.getResources().getDimension(R.dimen.custom_profile_image);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(markerWidth, markerHeight));
        int padding = (int) context.getResources().getDimension(R.dimen.custom_profile_padding);
        imageView.setPadding(padding, padding, padding, padding);
        iconGenerator.setContentView(imageView);
    }

    @Override
    protected void onBeforeClusterItemRendered(MyItem job, MarkerOptions markerOptions) {


        imageView.setImageResource(job.getProfilePhoto());
        Bitmap icon = iconGenerator.makeIcon(job.getName());
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon)).title(job.getName());


    }

    @Override
    public void onBeforeClusterRendered(Cluster<MyItem> cluster, MarkerOptions markerOptions) {

        Iterator<MyItem> iterator = cluster.getItems().iterator();
        imageView.setImageResource(iterator.next().getProfilePhoto());
        Bitmap icon = clusterIconGenerator.makeIcon(iterator.next().getName());
        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(icon));
    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster cluster) {
        return cluster.getSize() > 1;
    }
}
