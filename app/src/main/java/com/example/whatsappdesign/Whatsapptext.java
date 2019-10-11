package com.example.whatsappdesign;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Whatsapptext extends AppCompatActivity {

    Button addbuttton, sharebutton;
    EditText editText2;
    TextView textView;
    String message;


    public static int PERMISSION_REQUEST_CONTACT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whatsapptext);

        addbuttton = findViewById(R.id.button);
        sharebutton = findViewById(R.id.button2);
        textView = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        askForContactPermission();

        addbuttton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                 Intent sendIntent = new Intent("android.intent.action.MAIN");
//                sendIntent.setAction(Intent.ACTION_VIEW);
//                sendIntent.setPackage("com.whatsapp");
//                String url = "https://api.whatsapp.com/send?phone=" + "+917004639485" + "&text=" + "Roushan";
//                sendIntent.setData(Uri.parse(url));
//                if(sendIntent.resolveActivity(getApplicationContext().getPackageManager()) != null){
//                    startActivity(sendIntent);
//                }

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setPackage("com.whatsapp");
                try {
                    startActivityForResult(intent, 1);
                    Toast.makeText(Whatsapptext.this, "getting In activity", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                }


            }
        });

        sharebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //http://www.w3.org/2000/svg



                PackageManager pm=getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text = "YOUR TEXT HERE";

                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                   waIntent.putExtra(Intent.EXTRA_TEXT, text);
                   // waIntent.putExtra(Intent.EXTRA_TITLE, "Roushan Dewan");
                    startActivity(Intent.createChooser(waIntent, "Share with"));
                } catch (PackageManager.NameNotFoundException e) {

                }

//                String phoneNumberWithCountryCode = "+917004639485";
//                Intent sendIntent = new Intent("android.intent.action.MAIN");
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.setPackage("com.whatsapp");
//                String url = "https://api.whatsapp.com/send?phone=" + phoneNumberWithCountryCode + "&text=" + "your message";
//                sendIntent.setData(Uri.parse(url));
//                if (sendIntent.resolveActivity(getApplicationContext().getPackageManager()) != null) {
//                    startActivity(sendIntent);
//                }

//                Intent sendIntent = new Intent("android.intent.action.MAIN");
//                sendIntent.setAction(Intent.ACTION_VIEW);
//                sendIntent.setPackage("com.whatsapp");
//                String url = "https://api.whatsapp.com/send?phone=" + textView.getText() + "&text=" + editText2.getText();
//                sendIntent.setData(Uri.parse(url));
//                if(sendIntent.resolveActivity(getApplicationContext().getPackageManager()) != null){
//                    startActivity(sendIntent);
//                }


            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);

        switch (requestCode) {
            case 1:

                if (resultCode == RESULT_OK) {

                    if (intent.hasExtra("contact")) {

                        try {
                            String number = intent.getStringExtra("contact");
                            Toast.makeText(this, number, Toast.LENGTH_SHORT).show();
                            String string = number;
                            String[] parts = string.split("@");
                            String numberfilter = parts[0];
                            Toast.makeText(this, numberfilter, Toast.LENGTH_SHORT).show();
                            textView.setText(numberfilter);


                        } catch (Exception ex) {
                            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
                        }

                    }
                }
                break;

            default:
                break;
        }
    }


    public void askForContactPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.READ_CONTACTS)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setTitle("Contacts access needed");
                    builder.setPositiveButton(android.R.string.ok, null);
                    builder.setMessage("please confirm Contacts access");//TODO put real question
                    builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            requestPermissions(
                                    new String[]
                                            {Manifest.permission.READ_CONTACTS}
                                    , PERMISSION_REQUEST_CONTACT);
                        }
                    });
                    builder.show();
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.

                } else {

                    // No explanation needed, we can request the permission.

                    ActivityCompat.requestPermissions(Whatsapptext.this,
                            new String[]{Manifest.permission.READ_CONTACTS},
                            PERMISSION_REQUEST_CONTACT);

                    // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                    // app-defined int constant. The callback method gets the
                    // result of the request.
                }
            }


//    private void Testingcode() {
//
//        //This class provides applications access to the content model.
//        ContentResolver cr = getApplicationContext().getContentResolver();
//
////RowContacts for filter Account Types
//        Cursor contactCursor = cr.query(
//                ContactsContract.RawContacts.CONTENT_URI,
//                new String[]{ContactsContract.RawContacts._ID,
//                        ContactsContract.RawContacts.CONTACT_ID},
//                ContactsContract.RawContacts.ACCOUNT_TYPE + "= ?",
//                new String[]{"com.whatsapp"},
//                null);
//
////ArrayList for Store Whatsapp Contact
//        ArrayList<String> myWhatsappContacts = new ArrayList<>();
//
//        if (contactCursor != null) {
//            if (contactCursor.getCount() > 0) {
//                if (contactCursor.moveToFirst()) {
//                    do {
//                        //whatsappContactId for get Number,Name,Id ect... from  ContactsContract.CommonDataKinds.Phone
//                        String whatsappContactId = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.RawContacts.CONTACT_ID));
//
//                        if (whatsappContactId != null) {
//                            //Get Data from ContactsContract.CommonDataKinds.Phone of Specific CONTACT_ID
//                            Cursor whatsAppContactCursor = cr.query(
//                                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                                    new String[]{ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
//                                            ContactsContract.CommonDataKinds.Phone.NUMBER,
//                                            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME},
//                                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
//                                    new String[]{whatsappContactId}, null);
//
//                            if (whatsAppContactCursor != null) {
//                                whatsAppContactCursor.moveToFirst();
//                                String id = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
//                                String name = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
//                                String number = whatsAppContactCursor.getString(whatsAppContactCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//
//                                whatsAppContactCursor.close();
//
//                                //Add Number to ArrayList
//                                myWhatsappContacts.add(number);
//
//                                Toast.makeText(this, id + name + number, Toast.LENGTH_SHORT).show();
//                                Log.d("msgdeatil", id + name + number);
//
//                            }
//                        }
//                    } while (contactCursor.moveToNext());
//                    contactCursor.close();
//                }
//            }
//        }
//
//        Log.d("msg", " WhatsApp contact size :  " + myWhatsappContacts.size());
//
//    }

//    private void OpenwhaatsppApp() {
//
//        PackageManager pm = getApplicationContext().getPackageManager();
//
//        try {
//            PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
//            Intent waIntent = new Intent(Intent.ACTION_SEND);
//            waIntent.setType("text/plain");
//            waIntent.setPackage("com.whatsapp");
//            waIntent.putExtra(Intent.EXTRA_TEXT, "YOUR TEXT");
//            startActivity(waIntent);
//        } catch (PackageManager.NameNotFoundException e) {
//            Toast.makeText(Whatsapptext.this, "Please install whatsapp app", Toast.LENGTH_SHORT)
//                    .show();
//        }
//
//        editText2.setOnClickListener(new View.OnClickListener() {]
//            @Override
//            public void onClick(View v) {
//            }
//        });
//    }
        }
    }
}