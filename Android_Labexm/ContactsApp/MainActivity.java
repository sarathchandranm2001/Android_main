package com.example.contacts_contentprovider;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import the following
import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CONTACTS_PERMISSION = 1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tv=findViewById(R.id.textView);

        // Request permission: runtime permission handling
        //ContextCompat:ensure that your app works on both older and newer versions of Android
        //checkSelfPermission():checks whether the specified permission has been granted to the app
        //arguments: Context: The context from which the method is called, String permission: The permission you want to check (in this case, Manifest.permission.READ_CONTACTS)
        //PackageManager.PERMISSION_GRANTED: If the check returns this value, it means your app has the necessary permission to access the contacts.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {

            //asking the user to grant or deny the specified permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CONTACTS_PERMISSION);

            //REQUEST_CONTACTS_PERMISSION: int requestCode: A unique integer request code that identifies this particular permission request.
        } 

else {
            //call the method loadContacts defined below
            loadContacts();
                 }
        }

        //onRequestPermissionResult: callback in Android that is invoked when the user responds to a permission request made by the app
        //arguments: requestCode, permissions requested, array denoting granted or denied
        public void onRequestPermissionsResult ( int requestCode, String[] permissions, int[] grantResults) {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);

            //User has to click allow app to access contacts
            if (requestCode == REQUEST_CONTACTS_PERMISSION) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission is granted
                    loadContacts();
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
        private void loadContacts() {
            //ContactsContract: It serves as the primary interface for interacting with the contacts Content Provider.
            //Contacts is a nested class within ContactsContract that defines the schema for the contacts data.
            //CONTENT_URI is a static field within the Contacts class that provides the base URI for accessing the contacts data.
            Uri uri = ContactsContract.Contacts.CONTENT_URI;

            //select the following fields from Contacts
            String[] projection = {
                    ContactsContract.Contacts._ID,
                    ContactsContract.Contacts.DISPLAY_NAME
            };

            //Access the Content Provider of the Contacts App
            //Call the query() method to retreive all contacts
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

            if (cursor.moveToFirst()) {
                StringBuilder data=new StringBuilder();
                while(!cursor.isAfterLast())
                {
                    int contactId = Integer.parseInt(cursor.getString(0));
                    String displayName = cursor.getString(1);

                    data.append(" CONTACT ID: ").append(contactId);
                    data.append(" Display Name: ").append(displayName).append("\n");
                    //move to next record
                    cursor.moveToNext();
                }
                tv.setText(data);
            }
            else
            {
                tv.setText("No contacts found");
            }
        }
}