package zenonideas.zshop_android;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_Products.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_Products#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_Products extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Frag_Products() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_Products.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_Products newInstance(String param1, String param2) {
        Frag_Products fragment = new Frag_Products();
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView res= view.findViewById(R.id.frag_res_pro);



        try {
            List<C_Products> l = new ArrayList<>();

            String msg = ZShop_Web_Services.getConnection("Admin_Load_Products",null).toString();

            JSONObject reader = new JSONObject(msg);


            for (int i1 = 1; i1 <= reader.length(); i1++) {
                JSONObject r = (JSONObject) reader.get(i1 + "");

                String img = r.get("img1").toString().replace('\\', '/');

                l.add(new C_Products("" + r.get("pid"), "" + r.get("pname"), "" + r.get("qty"), "" + r.get("sp"), "" + r.get("cat"), "" + r.get("bname"), img,""+r.get("des"),""+r.get("min"),""+r.get("st")));
            }

            Adapter_Admin_Products myadpter = new Adapter_Admin_Products(getContext(), l);
            res.setLayoutManager(new GridLayoutManager(getContext(), 1));
            res.setAdapter(myadpter);

//            Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(getContext(),"Something Wrong",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }




    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag__products, container, false);
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
