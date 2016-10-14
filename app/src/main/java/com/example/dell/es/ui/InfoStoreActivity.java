package com.example.dell.es.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dell.es.R;
import com.squareup.picasso.Picasso;


public class InfoStoreActivity extends Activity {
    ImageView mImageStore, mImagePArtner1,mImagePArtner2;
    TextView mInfo,mPartnerInfo1,mPartnerInfo2;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_store_layout);

        mImageStore= (ImageView) findViewById(R.id.imageStore);
        Picasso.with(this).load("http://machopicture.com/images/vintage-jewellery/7800-medawar-fine-jewelers-store.jpg").into(mImageStore);
        mImagePArtner1= (ImageView) findViewById(R.id.imagePartner1);
        Picasso.with(this).load("http://www.chopard.com/media/chopard_pages/Corporate_responsibility/Chopard-Corporate-Responsability-RJC-3.jpg").into(mImagePArtner1);
        mImagePArtner2= (ImageView) findViewById(R.id.imagePartner2);
        Picasso.with(this).load("http://www.chopard.com/media/chopard_pages/Corporate_responsibility/Chopard-Corporate-Responsability-Jewel.jpg").into(mImagePArtner2);
        String info="Running and developing a company such as Chopard could not be done without a precise and rigorous set of governing principles. Over the years, several corporate values have emerged, all firmly rooted in the company spirit. These values are the guiding force when it comes to the choice of social initiatives and deeds Chopard undertakes. Chopard has adopted a policy of sustainable business development and does its upmost in limiting its impact on the planet’s limited resources. Several recycling systems have been put in place at the headquarters as well as in its production sites and distribution offices worldwide.\n" +
                "\n" +
                "Chopard has always taken its commitments very seriously and regards supporting noble causes as a natural duty. In this regard Chopard usually opts for long-term partnerships, as they are more constructive and allow a deeper understanding between the parties involved. For many years, the company has been involved in a number of philanthropic activities covering a variety of fields including medicine, ecology, art and culture.;\n";

       String partnerInfo1="1. THE RESPONSIBLE JEWELLERY COUNCIL\n" +
               "\n" +
               "Chopard is a proud Member of the Responsible Jewellery Council (RJC). The Responsible Jewellery Council is a standards-setting organisation that has been established to reinforce consumer confidence in the jewellery industry by promoting responsible ethical, human rights, social and environmental practices throughout the jewellery supply chain. The Responsible Jewellery Council has developed a common platform of standards for the jewellery supply chain and credible mechanisms for verifying responsible business practices through third party auditing. We commit to operating our business according to the Responsible Jewellery Council Principles and Code of Practices.\n";
        String partnerInfo2="2. KIMBERLEY SUPPORT\n" +
                "\n" +
                "Chopard supports the Kimberley Process and the World Diamond Council (WDC), two organisations working to eliminate conflict diamonds from industry supply chains. To actively support them, Chopard has put in place a self-regulation initiative for all its suppliers, requesting they not buy or sell conflict diamonds, and not buy diamonds from suspect sources, unknown suppliers or countries and regions that have not implemented the Kimberley Process Certification Scheme. Stones not conforming to the above mentioned standards, will automatically be refused and returned to the supplier\n";


        mInfo= (TextView) findViewById(R.id.infoStore);
        mInfo.setText(info);
        mPartnerInfo1= (TextView) findViewById(R.id.partners1);
        mPartnerInfo1.setText(partnerInfo1);
        mPartnerInfo2= (TextView) findViewById(R.id.partners2);
        mPartnerInfo2.setText(partnerInfo2);



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}