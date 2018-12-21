package zenonideas.zshop_android;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_Cat_Update.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_Cat_Update#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_Cat_Update extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Frag_Cat_Update() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_Cat_Update.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_Cat_Update newInstance(String param1, String param2) {
        Frag_Cat_Update fragment = new Frag_Cat_Update();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__cat__update, container, false);
    }

    Button btn1,btn2;
    EditText bname;
    ImageView img1;
    private static int RESULT_LOAD_IMAGE = 1;
    Spinner fcu_cat;
    boolean flag1 = false;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btn1=view.findViewById(R.id.fcu_btn1);
        btn2=view.findViewById(R.id.fcu_btn2);
        img1=view.findViewById(R.id.fcu_img1);
        bname=view.findViewById(R.id.fcu_cname);
        fcu_cat=view.findViewById(R.id.fcu_cat);

        LoadCat1(view);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });


        fcu_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String s1 = fcu_cat.getSelectedItem().toString();
                flag1 = false;
                try {
                    String rmsg = ZShop_Web_Services.getConnection("Admin_Load_Update_Cat", s1).toString();

                    JSONObject jo = new JSONObject(rmsg);

                    bname.setText(jo.getString("cname"));

                    String w_url = getContext().getString(R.string.web_url) + jo.getString("img1").replace('\\', '/');
                    Glide.with(getContext()).load(w_url).into(img1);
                } catch (Exception e) {
                    System.out.println(e);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    HashMap hm = new HashMap();

                    if (flag1){
                        Bitmap bitmap = ((BitmapDrawable) img1.getDrawable()).getBitmap();
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                        byte[] bytearray = stream.toByteArray();
                        bitmap.recycle();
                        hm.put("img1", bytearray);
                    }else{
                        hm.put("img1",null);
                    }

                    hm.put("cid", fcu_cat.getSelectedItem().toString().trim().split(" - ")[0]);
                    hm.put("cname", bname.getText().toString().trim());

                    String imageName = ZShop_Web_Services.getConnection("Admin_Update_Cat", hm).toString();

                    if (imageName.equalsIgnoreCase("e")) {
                        Toast.makeText(getContext(), "Error..!", Toast.LENGTH_LONG).show();
                    } else if (imageName.equalsIgnoreCase("sw")) {
                        Toast.makeText(getContext(), "Something Wrong..!", Toast.LENGTH_LONG).show();
                    } else if (imageName.equalsIgnoreCase("s")) {
                        Toast.makeText(getContext(), "Success..!", Toast.LENGTH_LONG).show();
                        LoadCat1(view);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void LoadCat1(View view) {
        try {
            List<String> l1 = new ArrayList<>();

            String msg = ZShop_Web_Services.getConnection("Admin_Load_Category", null).toString();

            JSONObject reader = new JSONObject(msg);

            for (int i1 = 1; i1 <= reader.length(); i1++) {
                JSONObject r = (JSONObject) reader.get(i1 + "");
                l1.add(r.get("cid") + " - " + r.get("cname"));
            }

            ArrayAdapter adapter = new ArrayAdapter<String>(view.getContext(), R.layout.support_simple_spinner_dropdown_item, l1);
            fcu_cat.setAdapter(adapter);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            cursor.moveToFirst();
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            img1.setImageBitmap(BitmapFactory.decodeFile(picturePath));
            flag1 = true;
//            Toast.makeText(getContext(), picturePath, Toast.LENGTH_LONG).show();
            cursor.close();
        } else {
            Toast.makeText(getActivity(), "Try Again!!", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
