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

public class FragmentNaskahKermaForm extends Fragment
{
    private static final String URL_ROOT = URLS.URL_UTAMA;

    public static final String URL_AGAM= URL_ROOT + "/Kabupaten_Agam/simpannaskah.php";
    public static final String URL_TANAHD= URL_ROOT + "/Kabupaten_Tanah_Datar/simpannaskah.php";
    public static final String URL_DAHAMS= URL_ROOT + "/Kabupaten_Dharmasraya/simpannaskah.php";
    public static final String URL_MENTAWAI= URL_ROOT + "/Kabupaten_Kepulauan_Mentawai/simpannaskah.php";
    public static final String URL_LIMAPULUH= URL_ROOT + "/Kabupaten_Lima_Puluh_Kota/simpannaskah.php";
    public static final String URL_PARIAMAN= URL_ROOT + "/Kabupaten_Padang_Pariaman/simpannaskah.php";
    public static final String URL_PASAMAN= URL_ROOT + "/Kabupaten_Pasaman/simpannaskah.php";
    public static final String URL_PASBAR= URL_ROOT + "/Kabupaten_Pasaman_Barat/simpannaskah.php";
    public static final String URL_PESISIR= URL_ROOT + "/Kabupaten_Pesisir_Selatan/simpannaskah.php";
    public static final String URL_SIJUNJUNG= URL_ROOT + "/Kabupaten_Sijunjung/simpannaskah.php";
    public static final String URL_SOLOK= URL_ROOT + "/Kabupaten_Solok/simpannaskah.php";
    public static final String URL_SOLSEL= URL_ROOT + "/Kabupaten_Solok_Selatan/simpannaskah.php";
    public static final String URL_BUKKITTINGGI= URL_ROOT + "/Kota_Bukittinggi/simpannaskah.php";
    public static final String URL_PADANG= URL_ROOT + "/Kota_Padang/simpannaskah.php";
    public static final String URL_PADANGPANJANG= URL_ROOT + "/Kota_Padang_Panjang/simpannaskah.php";
    public static final String URL_KOTPARIAMAN= URL_ROOT + "/Kota_Pariaman/simpannaskah.php";
    public static final String URL_SAWAHLUNTO= URL_ROOT + "/Kota_Sawahlunto/simpannaskah.php";
    public static final String URL_KOTSOLOK= URL_ROOT + "/Kota_Solok/simpannaskah.php";
    public static final String URL_PYK= URL_ROOT + "/Kota_Payakumbuh/simpannaskah.php";

    public static String URL_REGISTRASI="";

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FragmentNaskahKermaForm.OnFragmentInteractionListener mListener;

    public FragmentNaskahKermaForm() {
        // Required empty public constructor
    }
    private static final int STORAGE_PERMISSION_CODE = 123;
    public int PDF_REQ_CODE = 1;
    private Bitmap bitmap;
    private Uri filePath;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    ImageView imgDate,imgDate1;
    EditText txtDate,txtDate1;
    public static FragmentNaskahKermaForm newInstance(String param1, String param2) {
        FragmentNaskahKermaForm fragment = new FragmentNaskahKermaForm();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestStoragePermission();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        new myikc().execute();

        return inflater.inflate(R.layout.fragment_naskah_kerma_form, container, false);

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
        if (context instanceof FragmentNaskahKermaForm.OnFragmentInteractionListener) {
            mListener = (FragmentNaskahKermaForm.OnFragmentInteractionListener) context;
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


            User user = PrefManager.getInstance(getActivity()).getUser();

            String agam = "KabupatenAgam";
            String tanahdatar ="KabupatenTanahDatar";
            String dhamas ="KabupatenDharmasraya";
            String mentawai ="KabupatenKepulauanMentawai";
            String limapuluh ="KabupatenLimaPuluhKota";
            String pariaman ="KabupatenPadangPariaman";
            String pasaman ="KabupatenPasaman";
            String pasbar ="KabupatenPasamanBarat";
            String pesisir ="KabupatenPesisirSelatan";
            String sijunjung ="KabupatenSijunjung";
            String solok ="KabupatenSolok";
            String solsel ="KabupatenSolokSelatan";
            String bukittinggi ="KotaBukittinggi";
            String padang ="KotaPadang";
            String padangpanjang ="KotaPadangPanjang";
            String kotpariaman ="KotaPariaman";
            String pyk ="KotaPayakumbuh";
            String sawahlunto ="KotaSawahLunto";
            String kotsolok ="KotaSolok";

            if (agam.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_AGAM;
            }else if (tanahdatar.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_TANAHD;
            }else if (dhamas.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_DAHAMS;
            }else if (mentawai.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_MENTAWAI;
            }else if (limapuluh.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_LIMAPULUH;
            }else if (pariaman.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_PARIAMAN;
            }else if (pasaman.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_PASAMAN;
            }else if (pasbar.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_PASBAR;
            }else if (pesisir.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_PESISIR;
            }else if (sijunjung.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_SIJUNJUNG;
            }else if (solok.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_SOLOK;
            }else if (solsel.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_SOLSEL;
            }else if (bukittinggi.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_BUKKITTINGGI;
            }else if (padang.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_PADANG;
            }else if (padangpanjang.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_PADANGPANJANG;
            }else if (kotpariaman.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_KOTPARIAMAN;
            }else if (pyk.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_PYK;
            }else if (sawahlunto.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_SAWAHLUNTO;
            }else if (kotsolok.equals(user.getKesatuan())){
                URL_REGISTRASI = URL_KOTSOLOK;
            }else {
                URL_REGISTRASI = "";
            }

            final CheckBox pilih = (CheckBox)getView().findViewById(R.id.pilih);
            Button btn_save = (Button) getView().findViewById(R.id.bttnSave);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // do something

                    Toast.makeText(getActivity(), "Menyimpan", Toast.LENGTH_LONG).show();

                    final String dtxtNoNaskah = ((EditText) getView().findViewById(R.id.editNomorNaskah)).getText().toString();
                    final String dtxtTanggalNaskah = ((EditText) getView().findViewById(R.id.editTanggalnaskah)).getText().toString();
                    final String dtxtParaPihak = ((EditText) getView().findViewById(R.id.editParaPihak)).getText().toString();
                    final String dtxtTentang = ((EditText) getView().findViewById(R.id.edittentang)).getText().toString();
                    final String dtxtMasaBerlaku = ((EditText) getView().findViewById(R.id.editMasaberlaku)).getText().toString();

                    if (pilih.isChecked()){
                        final String path = FilePath.getPath(getActivity(),filePath);
                        try {
                            String uploadId = UUID.randomUUID().toString();
                            new MultipartUploadRequest(getActivity(), uploadId, URL_REGISTRASI)
                                    .addFileToUpload(path, "upload_document") //Adding file
                                    .addParameter("nomor_naskah", dtxtNoNaskah)
                                    .addParameter("tgl_naskah", dtxtTanggalNaskah)
                                    .addParameter("para_pihak", dtxtParaPihak)
                                    .addParameter("tentang", dtxtTentang)
                                    .addParameter("masa_berlaku", dtxtMasaBerlaku)
                                    .setNotificationConfig(new UploadNotificationConfig())
                                    .setMaxRetries(5)
                                    .startUpload(); //Starting the upload

//                        clear();
                        } catch (Exception exc) {
                            Toast.makeText(getActivity(), "Gagal", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        final String KEY_NOMOR = "nomor_naskah";
                        final String KEY_TGL= "tgl_naskah";
                        final String KEY_PIHAK= "para_pihak";
                        final String KEY_TENTANG= "tentang";
                        final String KEY_BERLAKU= "masa_berlaku";

                        class Add extends AsyncTask<Void,Void,String >{

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
                                HashMap<String,String> params = new HashMap<>();

                                params.put(KEY_NOMOR,dtxtNoNaskah);
                                params.put(KEY_TGL,dtxtTanggalNaskah);
                                params.put(KEY_PIHAK,dtxtParaPihak);
                                params.put(KEY_TENTANG,dtxtTentang);
                                params.put(KEY_BERLAKU,dtxtMasaBerlaku);

                                RequestHandler requestHandler = new RequestHandler();
                                return requestHandler.sendPostRequest(URL_REGISTRASI,params);
                            }
                        }

                        new Add().execute();
                    }
                }
            });
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        imgDate = getView().findViewById(R.id.imgDate);
        txtDate = getView().findViewById(R.id.editTanggalnaskah);
        imgDate1 = getView().findViewById(R.id.imgDate1);
        txtDate1 = getView().findViewById(R.id.editMasaberlaku);

            imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog();
            }
        });

        imgDate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog1();
            }
        });
    }

    private void showDateDialog(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtDate.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void showDateDialog1(){
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                txtDate1.setText(dateFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

}
