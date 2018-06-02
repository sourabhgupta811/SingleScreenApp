package com.example.android.singlescreenapp;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final int WEB_TAG=1;
    private static final int PHONE_TAG=2;
    private static final int ADDRESS_TAG=3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //code for hiding title bar====
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //=============================
        setContentView(R.layout.activity_main);
        String lato="Lato-Light.ttf";
        String open_sans="OpenSans-Regular.ttf";
        TextView company_name=findViewById(R.id.company_name);
        TextView company_motto=findViewById(R.id.company_motto);
        TextView phone=findViewById(R.id.phone);
        TextView web=findViewById(R.id.website);
        TextView location=findViewById(R.id.location);

        Typeface lato_tf = Typeface.createFromAsset(this.getAssets(), "font/" + lato);
        Typeface open_tf = Typeface.createFromAsset(this.getAssets(), "font/" + open_sans);
        Typeface light_tf = Typeface.createFromAsset(this.getAssets(), "font/" + "Lato-LightItalic.ttf");
        setTypefaces(company_name,open_tf);
        setTypefaces(company_motto,light_tf);
        setTypefaces(phone,lato_tf);
        setTypefaces(location,lato_tf);
        setTypefaces(web,lato_tf);

    }
    public void goLink(View v){
        Intent intent=new Intent(Intent.ACTION_VIEW);
        switch(Integer.parseInt(v.getTag().toString())){
            case WEB_TAG:
                Uri web=Uri.parse("https:/"+getString(R.string.weblink));
                intent.setData(web);
                break;
            case PHONE_TAG:
                intent.setAction(Intent.ACTION_DIAL);
                Uri phone=Uri.parse("tel:"+getString(R.string.phone_number));
                intent.setData(phone);
                break;
            case ADDRESS_TAG:
                Uri location=new Uri.Builder().scheme("geo").path("0,0").appendQueryParameter("q",getString(R.string.address)).build();
                Log.e(location.toString(),location.toString());
                intent.setData(location);
        }
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
    private void setTypefaces(TextView textView,Typeface typeface){
        textView.setTypeface(typeface);
    }
}
