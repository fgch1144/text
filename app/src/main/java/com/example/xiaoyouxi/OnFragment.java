package com.example.xiaoyouxi;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.xiaoyouxi.databinding.FragmentOnBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OnFragment newInstance(String param1, String param2) {
        OnFragment fragment = new OnFragment();
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

    @SuppressWarnings("NullableProblems")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final MyViewModel myViewModel;
        myViewModel =ViewModelProviders.of(getActivity()).get(MyViewModel.class);
        myViewModel.generator();
        final FragmentOnBinding  binding;
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_on, container, false);
        binding.setOn(myViewModel);
        binding.setLifecycleOwner(getActivity());
        final StringBuilder stringBuilder = new StringBuilder();
        View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                switch(v.getId()){
                    case R.id.button3:

                        stringBuilder.append("1");
                        break;
                    case R.id.button2:
                        stringBuilder.append("2");
                        break;
                    case R.id.button4:
                        stringBuilder.append("3");
                        break;
                    case R.id.button5:
                        stringBuilder.append("4");
                        break;
                    case R.id.button6:
                        stringBuilder.append("5");
                        break;
                    case R.id.button7:
                        stringBuilder.append("6");
                        break;
                    case R.id.button8:
                        stringBuilder.append("7");
                        break;
                    case R.id.button9:
                        stringBuilder.append("8");
                        break;
                    case R.id.button10:
                        stringBuilder.append("9");
                        break;
                    case R.id.button11:
                        stringBuilder.append("0");
                        break;
                    case R.id.button12:
                        stringBuilder.setLength(0);
                }
                if (stringBuilder.length()==0){
                    binding.textView10.setText(getString(R.string.Input));
                }else {
                    binding.textView10.setText(stringBuilder.toString());
                }
            }
        } ;

      binding.button3.setOnClickListener(listener);
      binding.button2.setOnClickListener(listener);
      binding.button4.setOnClickListener(listener);
      binding.button5.setOnClickListener(listener);
      binding.button6.setOnClickListener(listener);
      binding.button7.setOnClickListener(listener);
      binding.button8.setOnClickListener(listener);
      binding.button9.setOnClickListener(listener);
      binding.button10.setOnClickListener(listener);
      binding.button11.setOnClickListener(listener);
      binding.button12.setOnClickListener(listener);

      binding.button13.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if (Integer.valueOf(stringBuilder.toString()).intValue()==myViewModel.getAnswer().getValue()){
               myViewModel.answerCorrect();
               stringBuilder.setLength(0);
              // binding.textView10.setText(R.string.answer_messages);
             //  stringBuilder.append(getString(R.string.score));
              }else {
                  NavController controller = Navigation.findNavController(v);
                  if (myViewModel.win_flag){
                      controller.navigate(R.id.action_onFragment_to_winFragment);
                      myViewModel.win_flag =false;
                      myViewModel.save();
                  } else {
                      controller.navigate(R.id.action_onFragment_to_loseFragment);
                  }
              }
          }
      });

      return binding.getRoot();
    }
}