package com.example.lives_project;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.view.View;
import android.widget.SearchView;

import java.io.IOException;
import java.util.List;

public class FullscreenMapActivity  extends AppCompatActivity implements OnMapReadyCallback {

                    private GoogleMap myMap;
                    private SearchView mapSearchView;


                    protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        try {
                        setContentView(R.layout.fullscreen_map_activity);
                        mapSearchView = findViewById(R.id.mapSearch);
                        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map_google);
                        mapSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                            @Override
                            public boolean onQueryTextSubmit(String query) {
                                String location = mapSearchView.getQuery().toString();
                                List<Address> addressList = null;
                                if (location != null) {
                                    Geocoder geocoder = new Geocoder(FullscreenMapActivity.this);
                                    try {
                                        addressList = geocoder.getFromLocationName(location, 1);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    Address address = addressList.get(0);
                                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                    MarkerOptions options = new MarkerOptions().position(latLng).title(location);
                                    options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
                                    myMap.addMarker(options);
                                    myMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
                                }
                                return false;
                            }

                            @Override
                            public boolean onQueryTextChange(String newText) {
                                return false;
                            }
                        });
                        mapFragment.getMapAsync(FullscreenMapActivity.this);
                    }catch (Exception e){
                            e.printStackTrace();

                        }

                    }

                    @Override
                    public void onMapReady(@NonNull GoogleMap googleMap) {
                        myMap = googleMap;

                    }

                    public void onLessonsClick(View v) {
                        Intent intent = new Intent(FullscreenMapActivity.this, LessonsActivity.class);
                        startActivity(intent);
                    }
                }

