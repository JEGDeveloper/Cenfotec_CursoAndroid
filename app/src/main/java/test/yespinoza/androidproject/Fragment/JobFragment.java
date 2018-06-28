package test.yespinoza.androidproject.Fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import test.yespinoza.androidproject.Adapter.CardViewAdapter;
import test.yespinoza.androidproject.Model.CardView;
import test.yespinoza.androidproject.Model.Utils.Helper;
import test.yespinoza.androidproject.Model.Utils.SwipeListener;
import test.yespinoza.androidproject.Model.Utils.SwipeableRecyclerViewTouchListener;
import test.yespinoza.androidproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JobFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JobFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JobFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public static View rootView;
    private RecyclerView recycler;
    public static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lManager;
    private ImageButton btnNext;
    private ImageButton btnBack;
    private static List<CardView> items;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public JobFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JobFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JobFragment newInstance(String param1, String param2) {
        JobFragment fragment = new JobFragment();
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
        if(rootView == null) {
            items = new ArrayList<>();
            adapter = null;
            rootView = inflater.inflate(R.layout.fragment_job, container, false);
        }
        btnNext = rootView.findViewById(R.id.imgBtnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(new ReferenceFragment());
            }
        });
        btnBack = rootView.findViewById(R.id.imgBtnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                next(new StudyFragment());
            }
        });
        rootView.findViewById(R.id.imgBtnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddClickListener(v);
            }
        });
        return rootView;
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

    public void onAddClickListener(View pView){
        EditText etPosition = rootView.findViewById(R.id.etPosition);
        EditText etCompany = rootView.findViewById(R.id.etCompany);
        EditText etDateFrom = rootView.findViewById(R.id.etDateFrom);
        EditText etDateTo = rootView.findViewById(R.id.etDateTo);
        String position = Helper.TextFormat(etPosition.getText().toString());
        String company = Helper.TextFormat(etCompany.getText().toString());
        String dates = etDateFrom.getText().toString()+
                (etDateTo.getText().toString().equals("") ?
                        "":
                        " - "+
                                etDateTo.getText().toString());

        if(position.equals("")){
            Toast.makeText(rootView.getContext(),"",Toast.LENGTH_SHORT).show();;
            return;
        }
        if(company.equals("")){
            Toast.makeText(rootView.getContext(),"",Toast.LENGTH_SHORT).show();;
            return;
        }

        items.add(new CardView( position,
                                company.concat(dates.equals("") ?
                                                "" :
                                                " | "+ dates)));
        etPosition.setText("");
        etCompany.setText("");
        etDateFrom.setText("");
        etDateTo.setText("");

        recycler = rootView.findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);
        lManager = new LinearLayoutManager(rootView.getContext());
        recycler.setLayoutManager(lManager);
        adapter = new CardViewAdapter(items);
        recycler.setAdapter(adapter);
        btnNext.setEnabled(true);


        /**Swipe to Delete**/
        recycler.addOnItemTouchListener( new SwipeableRecyclerViewTouchListener(recycler, new SwipeListener(adapter, items)));
    }

    private void next(Fragment pFragment){
        FragmentTransaction oFragmentTransaction = getFragmentManager().beginTransaction();
        oFragmentTransaction.replace(R.id.frmLayoutIndex, pFragment, "fragment_screen");
        oFragmentTransaction.commit();
    }
}
