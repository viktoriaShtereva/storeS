package com.example.dell.es.ui.iteminfo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dell.es.R;
import com.example.dell.es.model.Item;
import com.example.dell.es.model.User;
import com.example.dell.es.ui.interfaces.Api;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 10/14/2016.
 */
public class Buy extends AppCompatActivity {
    private ImageView imageItem;
    private TextView itemName, itemPrice;
    private Item item;
    private Context context=this;

    private EditText inputName, inputAdress, inputTel;
    private TextInputLayout inputLayoutName, inputLayoutAdress, inputLayoutTel;
    private Button btnBuy;
    private User user;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buy_layout);
        item = getIntent().getParcelableExtra("buyItem");
        imageItem= (ImageView) findViewById(R.id.image_buy);
        Picasso.with(this).load(item.getImageUri()).into(imageItem);
        itemName= (TextView) findViewById(R.id.textNameBuy);
        itemName.setText(item.getName());
        itemPrice= (TextView) findViewById(R.id.textPriceBuy);
        itemPrice.setText(item.getPrice()+" $");


        inputLayoutName = (TextInputLayout) findViewById(R.id.input_layout_name);
        inputLayoutAdress = (TextInputLayout) findViewById(R.id.input_layout_address);
        inputLayoutTel = (TextInputLayout) findViewById(R.id.input_layout_tel);
        inputName = (EditText) findViewById(R.id.input_name);
        inputAdress = (EditText) findViewById(R.id.input_address);
        inputTel = (EditText) findViewById(R.id.input_tel);
        btnBuy = (Button) findViewById(R.id.btnBuy);

        inputName.addTextChangedListener(new MyTextWatcher(inputName));
        inputAdress.addTextChangedListener(new MyTextWatcher(inputAdress));
        inputTel.addTextChangedListener(new MyTextWatcher(inputTel));

        btnBuy.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          submitForm();

                                          Retrofit retrofit = new Retrofit.Builder()
                                                  .baseUrl("https://mystore-f62f9.firebaseio.com/")//url of firebase app
                                                  .addConverterFactory(GsonConverterFactory.create())//use for convert JSON file into object
                                                  .build();

                                          Api api = retrofit.create(Api.class);//use of interface

                                          Call<User> call = api.setData(user.getName(), user);
                                          call.enqueue(new Callback<User>() {
                                              @Override
                                              public void onResponse(Call<User> call, Response<User> response) {
                                                  Toast.makeText(context, "Success", Toast.LENGTH_LONG).show();
                                              }

                                              @Override
                                              public void onFailure(Call<User> call, Throwable t) {
                                                  Toast.makeText(context, "Fail", Toast.LENGTH_LONG).show();
                                              }
                                          });

                                      }
            });
    }
    /**
     * Validating form
     */
    private void submitForm() {
        if (!validateName()) {
            return;
        }

        if (!validateAdreess()) {
            return;
        }

        if (!validateTel()) {
            return;
        }

        user=new User(inputName.getText().toString(),inputAdress.getText().toString(),inputTel.getText().toString(),item.getName());
    }

    private boolean validateName() {
        if (inputName.getText().toString().trim().isEmpty()) {
            inputLayoutName.setError(getString(R.string.err_msg_name));
            requestFocus(inputName);
            return false;
        } else {
            inputLayoutName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateAdreess() {
        if (inputAdress.getText().toString().trim().isEmpty()) {
            inputLayoutAdress.setError(getString(R.string.err_msg_address));
            requestFocus(inputAdress);
            return false;
        } else {
            inputLayoutAdress.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateTel() {
        if (inputTel.getText().toString().trim().isEmpty()) {
            inputLayoutTel.setError(getString(R.string.err_msg_tel));
            requestFocus(inputTel);
            return false;
        } else {
            inputLayoutTel.setErrorEnabled(false);
        }

        return true;
    }


    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.input_name:
                    validateName();
                    break;
                case R.id.input_address:
                    validateAdreess();
                    break;
                case R.id.input_tel:
                    validateTel();
                    break;
            }
        }
    }
}