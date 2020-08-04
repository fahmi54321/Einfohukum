package com.example.einfohukum.file;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class FragmentDisiplinForm extends Fragment
{
    private static final String URL_ROOT = URLS.URL_UTAMA;

    public static final String URL_AGAM= URL_ROOT + "/Kabupaten_Agam/simpandisiplin.php";
    public static final String URL_TANAHD= URL_ROOT + "/Kabupaten_Tanah_Datar/simpandisiplin.php";
    public static final String URL_DAHAMS= URL_ROOT + "/Kabupaten_Dharmasraya/simpandisiplin.php";
    public static final String URL_MENTAWAI= URL_ROOT + "/Kabupaten_Kepulauan_Mentawai/simpandisiplin.php";
    public static final String URL_LIMAPULUH= URL_ROOT + "/Kabupaten_Lima_Puluh_Kota/simpandisiplin.php";
    public static final String URL_PARIAMAN= URL_ROOT + "/Kabupaten_Padang_Pariaman/simpandisiplin.php";
    public static final String URL_PASAMAN= URL_ROOT + "/Kabupaten_Pasaman/simpandisiplin.php";
    public static final String URL_PASBAR= URL_ROOT + "/Kabupaten_Pasaman_Barat/simpandisiplin.php";
    public static final String URL_PESISIR= URL_ROOT + "/Kabupaten_Pesisir_Selatan/simpandisiplin.php";
    public static final String URL_SIJUNJUNG= URL_ROOT + "/Kabupaten_Sijunjung/simpandisiplin.php";
    public static final String URL_SOLOK= URL_ROOT + "/Kabupaten_Solok/simpandisiplin.php";
    public static final String URL_SOLSEL= URL_ROOT + "/Kabupaten_Solok_Selatan/simpandisiplin.php";
    public static final String URL_BUKKITTINGGI= URL_ROOT + "/Kota_Bukittinggi/simpandisiplin.php";
    public static final String URL_PADANG= URL_ROOT + "/Kota_Padang/simpandisiplin.php";
    public static final String URL_PADANGPANJANG= URL_ROOT + "/Kota_Padang_Panjang/simpandisiplin.php";
    public static final String URL_KOTPARIAMAN= URL_ROOT + "/Kota_Pariaman/simpandisiplin.php";
    public static final String URL_SAWAHLUNTO= URL_ROOT + "/Kota_Sawahlunto/simpandisiplin.php";
    public static final String URL_KOTSOLOK= URL_ROOT + "/Kota_Solok/simpandisiplin.php";
    public static final String URL_PYK= URL_ROOT + "/Kota_Payakumbuh/simpandisiplin.php";

    public static String URL_REGISTRASI="";
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentDisiplinForm.OnFragmentInteractionListener mListener;



    public FragmentDisiplinForm() {
        // Required empty public constructor
    }
    public int PDF_REQ_CODE = 1;
    public int PDF_REQ_CODE2 = 2;
    public int PDF_REQ_CODE3 = 3;
    private static final int STORAGE_PERMISSION_CODE = 123;
    //    private Bitmap bitmap;
    private Uri filePath,filePath2,filePath3;
    LinearLayout view;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat dateFormatter;
    ImageView imgDate,imgDate1;
    EditText txtDate,txtDate1;

    public static FragmentDisiplinForm newInstance(String param1, String param2) {
        FragmentDisiplinForm fragment = new FragmentDisiplinForm();
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

        new FragmentDisiplinForm.myikc().execute();

        return inflater.inflate(R.layout.fragment_disiplin_form, container, false);
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


    // TODO: Rename method, update argument and hook method into UI event
    public String onButtonPressed(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();
        cursor = getActivity().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();
        return path;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentDisiplinForm.OnFragmentInteractionListener) {
            mListener = (FragmentDisiplinForm.OnFragmentInteractionListener) context;
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PDF_REQ_CODE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
        }
        if (requestCode == PDF_REQ_CODE2 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath2 = data.getData();
        }
        if (requestCode == PDF_REQ_CODE3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath3 = data.getData();
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

        protected void onPostExecute(String file_url) {

            Button upload = (Button) getView().findViewById(R.id.bttnUpload);
            upload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("application/pdf");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE);
                }
            });
            Button upload2 = (Button) getView().findViewById(R.id.bttnUpload2);
            upload2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("application/pdf");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE2);
                }
            });
            Button upload3 = (Button) getView().findViewById(R.id.bttnUpload3);
            upload3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setType("application/pdf");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(Intent.createChooser(intent, "Select Pdf"), PDF_REQ_CODE3);
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
            final Button btn_save = (Button) getView().findViewById(R.id.bttnSave);
            btn_save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // do something

                    final String dtxtNamapelaku = ((EditText) getView().findViewById(R.id.editNamapelaku)).getText().toString();
                    final String dtxtPangkat = ((EditText) getView().findViewById(R.id.editPangkat)).getText().toString();
                    final String dtxtNrp = ((EditText) getView().findViewById(R.id.editNRP)).getText().toString();
                    final String dtxtjabatan = ((EditText) getView().findViewById(R.id.editJabatan)).getText().toString();
                    final String dtxtKesatuan = ((Spinner) getView().findViewById(R.id.spinnerkesatuan)).getSelectedItem().toString();
                    final String dtxtNamapelapor = ((EditText) getView().findViewById(R.id.editNamaPelapor)).getText().toString();
                    final String dtxtAlamat = ((EditText) getView().findViewById(R.id.editAlamat)).getText().toString();
                    final String dtxtPasal = ((EditText) getView().findViewById(R.id.editPasalDilanggar)).getText().toString();
                    //no laporan
                    final String dtxtLP = ((EditText) getView().findViewById(R.id.editAngkaLP)).getText().toString();
                    final String dtxtRomawilp = ((Spinner) getView().findViewById(R.id.spinnerromawilp)).getSelectedItem().toString();
                    final String dtxtTahunlp = ((Spinner) getView().findViewById(R.id.spinnertahunlp)).getSelectedItem().toString();

                    final String dtxtTgllaporan = ((EditText) getView().findViewById(R.id.editTanggallaporan)).getText().toString();

                    //no sidang
                    final String dtxtAberkas = ((EditText) getView().findViewById(R.id.editAngkaberkas)).getText().toString();
                    final String dtxtAberkas1= ((EditText) getView().findViewById(R.id.editAngkaberkas1)).getText().toString();
                    final String dtxtRomawiberkas = ((Spinner) getView().findViewById(R.id.spinnerromawiberkas)).getSelectedItem().toString();
                    final String dtxtAberkas2 = ((EditText) getView().findViewById(R.id.editAngkaberkas2)).getText().toString();
                    final String dtxtTahunberkas = ((Spinner) getView().findViewById(R.id.spinnertahunberkas)).getSelectedItem().toString();
                    final String dtxtAberkas3 = ((EditText) getView().findViewById(R.id.editAngkaberkas3)).getText().toString();

                    final String dtxtNopsh = ((EditText) getView().findViewById(R.id.editNopsh)).getText().toString();
                    final String dtxttglsidang = ((EditText) getView().findViewById(R.id.editTanggalsidang)).getText().toString();
                    final String dtxtisiputusan = ((EditText) getView().findViewById(R.id.editIsiputusan)).getText().toString();
                    final String dtxtisibanding = ((EditText) getView().findViewById(R.id.editIsibanding)).getText().toString();

                        Toast.makeText(getActivity(), "Menyimpan", Toast.LENGTH_LONG).show();
                        btn_save.setText("Menyimpan");
                        btn_save.setEnabled(false);
                        if (pilih.isChecked()){
                            final String path = FilePath.getPath(getActivity(),filePath);
                            final String path2 = FilePath.getPath(getActivity(),filePath2);
                            final String path3 = FilePath.getPath(getActivity(),filePath3);
                            try {
                                String uploadId = UUID.randomUUID().toString();

                                //Creating a multi part request
                                new MultipartUploadRequest(getActivity(), uploadId, URL_REGISTRASI)
                                        .addFileToUpload(path, "photo_vonis") //Adding file
                                        .addFileToUpload(path2, "photo_putusan") //Adding file
                                        .addFileToUpload(path3, "photo_surat") //Adding file
                                        .addParameter("nama_pelaku", dtxtNamapelaku)
                                        .addParameter("pangkat_nrp", dtxtPangkat)
                                        .addParameter("nrp", dtxtNrp)
                                        .addParameter("jabatan", dtxtjabatan)
                                        .addParameter("kesatuan", dtxtKesatuan)
                                        .addParameter("nama_pelapor", dtxtNamapelapor)
                                        .addParameter("alamat", dtxtAlamat)
                                        .addParameter("pasal_dilanggar", dtxtPasal)
                                        //no laporan
                                        .addParameter("lp", dtxtLP)
                                        .addParameter("romawilp", dtxtRomawilp)
                                        .addParameter("tahunlp", dtxtTahunlp)

                                        .addParameter("tgl_laporan", dtxtTgllaporan)

                                        //no berkas sidang
                                        .addParameter("berkas", dtxtAberkas)
                                        .addParameter("berkas1", dtxtAberkas1)
                                        .addParameter("romawiberkas", dtxtRomawiberkas)
                                        .addParameter("berkas2", dtxtAberkas2)
                                        .addParameter("tahunberkas", dtxtTahunberkas)
                                        .addParameter("berkas3", dtxtAberkas3)

                                        .addParameter("nomor_psh", dtxtNopsh)
                                        .addParameter("tgl_sidang", dtxttglsidang)
                                        .addParameter("isi_putusan", dtxtisiputusan)
                                        .addParameter("isi_banding", dtxtisibanding)

                                        .setNotificationConfig(new UploadNotificationConfig())
                                        .setMaxRetries(22)
                                        .startUpload();//Starting the upload

                            } catch (Exception exc) {
                                Toast.makeText(getActivity(), exc.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }else {

                            final String KEY_PELAKU = "nama_pelaku";
                            final String KEY_PANGKAT= "pangkat_nrp";
                            final String KEY_NRP= "nrp";
                            final String KEY_JABATAN= "jabatan";
                            final String KEY_KESATUAN= "kesatuan";
                            final String KEY_PELAPOR= "nama_pelapor";
                            final String KEY_ALAMAT= "alamat";
                            final String KEY_PASAL= "pasal_dilanggar";
                            final String KEY_ANGKALP= "lp";
                            final String KEY_ROMAWILP= "romawilp";
                            final String KEY_TAHUNLP= "tahunlp";
                            final String KEY_TANGGAL= "tgl_laporan";

                            final String KEY_BERKAS= "berkas";
                            final String KEY_BERKAS1= "berkas1";
                            final String KEY_ROMAWI= "romawiberkas";
                            final String KEY_BERKAS2= "berkas2";
                            final String KEY_TAHUN= "tahunberkas";
                            final String KEY_BERKAS3= "berkas3";

                            final String KEY_PSH= "nomor_psh";
                            final String KEY_TGL= "tgl_sidang";

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

                                    params.put(KEY_PELAKU,dtxtNamapelaku);
                                    params.put(KEY_PANGKAT,dtxtPangkat);
                                    params.put(KEY_NRP,dtxtNrp);
                                    params.put(KEY_JABATAN,dtxtjabatan);
                                    params.put(KEY_KESATUAN,dtxtKesatuan);
                                    params.put(KEY_PELAPOR,dtxtNamapelapor);
                                    params.put(KEY_ALAMAT,dtxtAlamat);
                                    params.put(KEY_PASAL,dtxtPasal);
                                    params.put(KEY_ANGKALP,dtxtLP);
                                    params.put(KEY_ROMAWILP,dtxtRomawilp);
                                    params.put(KEY_TAHUNLP,dtxtTahunlp);
                                    params.put(KEY_TANGGAL,dtxtTgllaporan);

                                    params.put(KEY_BERKAS,dtxtAberkas);
                                    params.put(KEY_BERKAS1,dtxtAberkas1);
                                    params.put(KEY_ROMAWI,dtxtRomawiberkas);
                                    params.put(KEY_BERKAS2,dtxtAberkas2);
                                    params.put(KEY_TAHUN,dtxtTahunberkas);
                                    params.put(KEY_BERKAS3,dtxtAberkas3);

                                    params.put(KEY_PSH,dtxtNopsh);
                                    params.put(KEY_TGL,dtxttglsidang);

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

        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        imgDate = getView().findViewById(R.id.imgDate);
        txtDate = getView().findViewById(R.id.editTanggallaporan);
        imgDate1 = getView().findViewById(R.id.imgDate1);
        txtDate1 = getView().findViewById(R.id.editTanggalsidang);

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
