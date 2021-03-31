package com.example.tp4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.tp4.ui.main.SectionsPagerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Créer un adaptateur qui retourne un fragment
        // pour chacune des sections de l'activité
        MyAdapter sectionsPagerAdapter = new MyAdapter(getSupportFragmentManager());


        // dire au viewpager d'utiliser cet adaptateur
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Snackbar snackbar = Snackbar
                    .make(viewPager, "settings", Snackbar.LENGTH_LONG);
            snackbar.show();
            return true;
        }
        if (id == R.id.action_other) {
            Snackbar snackbar = Snackbar
                    .make(viewPager, "other", Snackbar.LENGTH_LONG);
            snackbar.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return NatureFragment.newInstance(0, getString(R.string.titre_section0));
                case 1:
                    return NatureFragment.newInstance(1, getString(R.string.titre_section1));
                case 2:
                    return NatureFragment.newInstance(2, getString(R.string.titre_section2));
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            String titre="";
            Drawable icone=null;
            switch (position) {
                case 0:
                    titre = getString(R.string.titre_section0).toUpperCase(l);
                    icone = getResources().getDrawable(R.drawable.icone_minereaux);
                    break;
                case 1:
                    titre = getString(R.string.titre_section1).toUpperCase(l);
                    icone = getResources().getDrawable(R.drawable.icone_plantes);
                    break;
                case 2:
                    titre = getString(R.string.titre_section2).toUpperCase(l);
                    icone = getResources().getDrawable(R.drawable.icone_animaux);
                    break;
            }
            SpannableString sb = new SpannableString(" " + titre);
            // un espace est ajouté pour séparer le texte de l'image

            icone.setBounds(0, 0, icone.getIntrinsicWidth(), icone.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(icone, ImageSpan.ALIGN_BASELINE);
            sb.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            return sb;
        }
    }

}
