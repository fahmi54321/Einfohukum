package com.example.einfohukum.file;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.einfohukum.R;
import com.example.einfohukum.login.PrefManager;
import com.example.einfohukum.login.RequestHandler;
import com.example.einfohukum.login.URLS;
import com.example.einfohukum.login.User;

import net.gotev.uploadservice.MultipartUploadRequest;
import net.gotev.uploadservice.UploadNotificationConfig;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class FragmentLpBulananForm extends Fragment
{
    private static final String URL_ROOT = URLS.URL_UTAMA;
    public static final String URL_LP= URL_ROOT + "/simpanlp.php";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    Button bttnSimpan;

    public FragmentLpBulananForm() {
        // Required empty public constructor
    }
    private static final int STORAGE_PERMISSION_CODE = 123;
    public int PDF_REQ_CODE = 1;
    private Uri filePath;
    private FragmentLpBulananForm.OnFragmentInteractionListener mListener;

    public static FragmentLpBulananForm newInstance(String param1, String param2) {
        FragmentLpBulananForm fragment = new FragmentLpBulananForm();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView( LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        new myikc().execute();

        return inflater.inflate(R.layout.fragment_lp_bulanan_form, container, false);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        requestStoragePermission();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);


        bttnSimpan = getView().findViewById(R.id.bttnSimpan);

    }

    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {

        }

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(getActivity(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentLpBulananForm.OnFragmentInteractionListener) {
            mListener = (FragmentLpBulananForm.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }


    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


    class myikc extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        protected String doInBackground(String... args) {
            return null;
        }

        protected void onPostExecute(final String file_url) {

            Button upload = (Button) getView().findViewById(R.id.bttnUpload);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("pdf/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);

                }
            });

            final CheckBox pilih = (CheckBox)getView().findViewById(R.id.pilih);
            Button btn_save = (Button) getView().findViewById(R.id.bttnSimpan);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(getActivity(), "Menyimpan", Toast.LENGTH_LONG).show();

                    final String dtxtBulan = ((Spinner) getView().findViewById(R.id.spinnerBulan)).getSelectedItem().toString();
                    final String dtxtKesatuan = ((Spinner) getView().findViewById(R.id.spinnerKesatuan)).getSelectedItem().toString();

                    if (pilih.isChecked()){
                        final String path = FilePath.getPath(getActivity(),filePath);
                        try {
                            String uploadId = UUID.randomUUID().toString();
                            new MultipartUploadRequest(getActivity(), uploadId, URL_LP)
                                    .addFileToUpload(path,"dokumen") //Adding file
                                    .addParameter("bulan", dtxtBulan)
                                    .addParameter("kesatuan", dtxtKesatuan)
                                    .setNotificationConfig(new UploadNotificationConfig())
                                    .setMaxRetries(2)
                                    .startUpload()
                            ;

                        } catch (Exception exc) {
                            Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        final String KEY_BULAN = "bulan";
                        final String KEY_KESATUAN = "kesatuan";

                        class Add extends AsyncTask<Void, Void, String> {

                            @Override
                            protected void onPreExecute() {
                                super.onPreExecute();
                            }

                            @Override
                            protected void onPostExecute(String s) {
                                super.onPostExecute(s);
                            }

                            @Override
                            protected String doInBackground(Void... voids) {
                                HashMap<String, String> params = new HashMap<>();

                                params.put(KEY_BULAN, dtxtBulan);
                                params.put(KEY_KESATUAN, dtxtKesatuan);

                                RequestHandler requestHandler = new RequestHandler();
                                return requestHandler.sendPostRequest(URL_LP, params);

                            }
                        }
                        new Add().execute();
                    }
                }
            });

        }

    }

}
