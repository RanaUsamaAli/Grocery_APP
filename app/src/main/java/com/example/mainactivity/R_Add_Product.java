package com.example.mainactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
//import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

public class R_Add_Product extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private Button ADD_Product, Pick_image;
    private TextView Product_Name;
    private  TextView Product_id;
    private TextView Price;
    private TextView Discription;
    private ImageView show_im;
    private TextView log_out;

    private Uri mimageUri;
//    private Spinner sp;
    private StorageReference mstorageRef;
    private DatabaseReference DB_Refrence;
    String[] Spinner_show = {"Log_out","Check_Products"};
    ArrayAdapter<String> adapter;

    private StorageTask mupload_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_radd_product);

        Pick_image = findViewById(R.id.choose_Img);
        ADD_Product = findViewById(R.id.button_login);
        Product_Name = findViewById(R.id.Product_name);
        Product_id = findViewById(R.id.Product_ID);
        Price = findViewById(R.id.Product_Price);
        Discription = findViewById(R.id.Product_DES);
        show_im = findViewById(R.id.Image_show);
//        sp = findViewById(R.id.spinner);
        log_out = findViewById(R.id.register_login);

        mstorageRef = FirebaseStorage.getInstance().getReference("Products");
        DB_Refrence = FirebaseDatabase.getInstance().getReference("Products");

//        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,Spinner_show);
//        sp.setAdapter(adapter);

        log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log_out();
            }
        });

        Pick_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            OpenFileChooser();
            }
        });

        ADD_Product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mupload_check != null && mupload_check.isInProgress() )
                {
                    Toast.makeText(R_Add_Product.this,"Upload In Progress",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    uploadFile();
                }

            }
        });

    }

    private void log_out() {
        Intent intent= new Intent(R_Add_Product.this,Card_View.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private String getFileExtension(Uri uri)
{
    ContentResolver cR = getContentResolver();
    MimeTypeMap mime = MimeTypeMap.getSingleton();
    return mime.getExtensionFromMimeType(cR.getType(uri));
}
    private void uploadFile() {
        if (mimageUri != null) {
            StorageReference fileRefrence = mstorageRef.child(System.currentTimeMillis()
                    + "." + getFileExtension(mimageUri));


           fileRefrence.putFile(mimageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while (!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();

                            final String sdownload_url = String.valueOf(downloadUrl);

                            Toast.makeText(R_Add_Product.this, "Product Added", Toast.LENGTH_SHORT).show();
                            Upload_Product upload = new Upload_Product(Product_Name.getText().toString(),Product_id.getText().toString(),
                                    Discription.getText().toString(),Price.getText().toString(),
                                    sdownload_url);

                            String UploadID = DB_Refrence.push().getKey();
                            DB_Refrence.child(UploadID).setValue(upload);

                        //Refreshing the same Activity
                            recreate();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(R_Add_Product.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                        }
                    });


        }
        else
        {

        }
    }

    private void OpenFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
        && data != null && data.getData() != null)
        {
            mimageUri = data.getData();
            Picasso.get().load(mimageUri).into(show_im);
            show_im.setImageURI(mimageUri);
        }
    }


}