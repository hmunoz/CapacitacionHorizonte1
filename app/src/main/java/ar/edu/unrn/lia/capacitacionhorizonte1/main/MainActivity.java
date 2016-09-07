package ar.edu.unrn.lia.capacitacionhorizonte1.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import ar.edu.unrn.lia.capacitacionhorizonte1.R;
import ar.edu.unrn.lia.capacitacionhorizonte1.image.ui.ImageFragment;
import ar.edu.unrn.lia.capacitacionhorizonte1.main.adapter.MainSectionsPagerAdapter;
import ar.edu.unrn.lia.capacitacionhorizonte1.store.StoreFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.container)
    ViewPager viewPager;

    ImageFragment imageFragment;
    StoreFragment storeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imageFragment = new ImageFragment();
        storeFragment = new StoreFragment();


        imageFragment.setChangeListener(new ImageFragment.ChangeListener() {
            @Override
            public void onChange() {
                storeFragment.updateList();
            }
        });


        Fragment[] fragments = new Fragment[] {imageFragment, storeFragment};

        String[] titles = new String[] {getString(R.string.main_header_images), getString(R.string.main_header_store)};


        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(),
                fragments,
                titles);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

}
