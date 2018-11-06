package com.chess254.archcomp;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.chess254.archcomp.Models.House;
//import com.example.yougourta.projmob.Classes.Logement;
//import com.example.yougourta.projmob.R;
import com.github.jjobes.slidedatetimepicker.SlideDateTimeListener;
import com.github.jjobes.slidedatetimepicker.SlideDateTimePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HouseDetailActivity extends FragmentActivity
//        implements OnMapReadyCallback
{
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;

    private GoogleMap mMap;


    RatingBar ratingBar;

    ImageButton right;
    ImageButton left;

    int cpt = 0;

    private SimpleDateFormat mFormatter = new SimpleDateFormat("MMMM dd yyyy hh:mm aa");
    private Button mButton;

    private SlideDateTimeListener listener = new SlideDateTimeListener() {

        @Override
        public void onDateTimeSet(Date date)
        {
            Toast.makeText(HouseDetailActivity.this,
                    mFormatter.format(date), Toast.LENGTH_SHORT).show();
            /** RECUPERER LA DATE **/


        }

        // Optional cancel listener
        @Override
        public void onDateTimeCancel()
        {
            //Toast.makeText(DetailActivity.this,"Annulé", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);


//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);


        Intent intent = getIntent();
        House house = intent.getParcelableExtra("house");


        /*recyclerView = (RecyclerView) findViewById(R.id.recyclerViewPhotos);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        adapter = new DetailPhotosAdapter(house.getImages());
        recyclerView.setAdapter(adapter);*/

        final ImageSwitcher imageSwitcher;
        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);

        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView myView = new ImageView(getApplicationContext());
                myView.setScaleX(2.0f);
                myView.setScaleY(2.0f);
                return myView;
            }
        });

//        imageSwitcher.setImageResource(house.getImages().get(0));


        final Animation in = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        imageSwitcher.setInAnimation(in);
        imageSwitcher.setOutAnimation(out);

        right = (ImageButton) findViewById(R.id.imageButtonRight);
        left = (ImageButton) findViewById(R.id.imageButtonLeft);

        left.setVisibility(View.INVISIBLE);

//        right.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cpt++;
//                if (cpt >= house.getImages().size()) {
//                    right.setVisibility(View.INVISIBLE);
//                } else {
//                    imageSwitcher.setImageResource(house.getImages().get(cpt));
//                }
//
//                if (cpt > -1) {
//                    left.setVisibility(View.VISIBLE);
//                }
//            }
//        });
//
//        left.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                cpt--;
//                if (cpt <= -1) {
//                    left.setVisibility(View.INVISIBLE);
//                    right.setVisibility(View.VISIBLE);
//                } else {
//                    imageSwitcher.setImageResource(house.getImage().get(cpt));
//                }
//
//                if (cpt < house.getImages().size()) {
//                    right.setVisibility(View.VISIBLE);
//                }
//            }
//        });


        TextView prix = (TextView) findViewById(R.id.prix);
        TextView titre = (TextView) findViewById(R.id.titre);
        TextView adresse = (TextView) findViewById(R.id.adresse);
        TextView nb_chambres = (TextView) findViewById(R.id.nb_chambres);
        TextView surface = (TextView) findViewById(R.id.surface);
        TextView detail = (TextView) findViewById(R.id.detail);
        TextView horaires = (TextView) findViewById(R.id.horaires);
        TextView carre = (TextView)findViewById(R.id.carre);

        carre.setText(Html.fromHtml("m<sup>2</sup>"));

        ImageButton noter = (ImageButton) findViewById(R.id.note);
        ImageButton commentaire = (ImageButton) findViewById(R.id.commentaires);

        ImageButton appel = (ImageButton) findViewById(R.id.appel);
        ImageButton email = (ImageButton) findViewById(R.id.email);
        ImageButton rendezvous = (ImageButton) findViewById(R.id.rendezvous);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

//        prix.setText(house.getPriceHouse());

        float noteFinale;

//        ratingBar.setRating(Float.parseFloat(house.getRatingHouse()));
        prix.setText(house.getPriceHouse());
        titre.setText(house.getTypeHouse() + " " + " to rent.");
        adresse.setText(house.getLocationHouse());
        nb_chambres.setText(house.getRoomsHouse());
        surface.setText(house.getAreaHouse());
        detail.setText(house.getDescriptionHouse());

//        String str = house.getJoursVisiteLogement().get(0).getJourDispo() + " : " + house.getJoursVisiteLogement().get(0).getHeureDebutDispo() + " - " + house.getJoursVisiteLogement().get(0).getHeureFinDispo();
//        for (int i = 1; i < house.getJoursVisiteLogement().size(); i++) {
//            str = str + '\n' + house.getJoursVisiteLogement().get(i).getJourDispo() + " : " + house.getJoursVisiteLogement().get(i).getHeureDebutDispo() + " - " + house.getJoursVisiteLogement().get(i).getHeureFinDispo();
//        }
//        horaires.setText(str);


        noter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog mDialog = new Dialog(HouseDetailActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog_MinWidth);

                mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                mDialog.setContentView(R.layout.activity_rating);
                mDialog.show();

                final RatingBar ratingBarInterne = (RatingBar) mDialog.findViewById(R.id.ratingBar2);
                Button submit = (Button) mDialog.findViewById(R.id.submit);

                ratingBarInterne.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating,
                                                boolean fromUser) {
                        if (rating < 1.0f)
                            ratingBar.setRating(1.0f);
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        house.setRatingHouse(String.valueOf((ratingBarInterne.getRating() + Float.parseFloat(house.getRatingHouse())) / 2));
//                        ratingBar.setRating(Float.parseFloat(house.getRatingHouse()));
                        mDialog.cancel();
                    }
                });
            }
        });

//        commentaire.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(HouseDetailActivity.this, CommentairesActivity.class);
//                intent.putExtra("commentaires", house.getCommentairesLogement());
//                startActivity(intent);
//            }
//        });


//        appel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:"+ house.getProprietaireLogement().getTelUser()));
//                if (ActivityCompat.checkSelfPermission(DetailActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    return;
//                }
//                startActivity(callIntent);
//
//            }
//        });


//        email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                emailIntent.setData(Uri.parse("mailto:"));
//                emailIntent.setType("text/plain");
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{house.getProprietaireLogement().getEmailUser()});
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, house.getTitreLogement()+" "+ house.getTypeLogement());
//
//                try {
//                    startActivity(Intent.createChooser(emailIntent, "Envoyer un mail..."));
//                    finish();
//                } catch (android.content.ActivityNotFoundException ex) {
//                    Toast.makeText(DetailActivity.this, "Aucune application Mail installée.", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });




        rendezvous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new SlideDateTimePicker.Builder(getSupportFragmentManager())
                        .setListener(listener)
                        .setInitialDate(new Date())
                        //.setMinDate(minDate)
                        //.setMaxDate(maxDate)
                        .setIs24HourTime(true)
                        //.setTheme(SlideDateTimePicker.HOLO_DARK)
                        .setIndicatorColor(Color.parseColor("#7fa87f"))
                        .build()
                        .show();

            }
        });


    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(house.getLatitude(), house.getLongetude());
//        mMap.addMarker(new MarkerOptions().position(sydney).title(house.getTitreLogement()+" "));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//        mMap.animateCamera(CameraUpdateFactory.zoomIn());
//
//        CameraPosition cameraPosition = new CameraPosition.Builder()
//                .target(new LatLng(house.getLatitude(), house.getLongetude()))      // Sets the center of the map to Mountain View
//                .zoom(17)                   // Sets the zoom
//                .bearing(90)                // Sets the orientation of the camera to east
//                .tilt(30)                   // Sets the tilt of the camera to 30 degrees
//                .build();                   // Creates a CameraPosition from the builder
//        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
//    }
}

