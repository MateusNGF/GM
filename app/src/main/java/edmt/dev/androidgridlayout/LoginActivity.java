package edmt.dev.androidgridlayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    private EditText Cn, Cs;
    private String NomeADM = "Mateus Nicolau";
    private String SenhaADM = "44444444";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button = findViewById(R.id.login_button);

        Cn = (EditText) findViewById(R.id.login_name);
        Cs = (EditText) findViewById(R.id.login_pass);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NomeUser = TratarString(Cn.getText().toString());
                String SenhaUser = TratarString(Cs.getText().toString());

                try {

                    if (NomeUser.length() < 5 || SenhaUser.length() < 8){
                        if (NomeUser.length() == 0){
                            Cn.setError("ERRO");
                            throw new Exception("POR FAVOR PREENCHA O CAMPO NOME");
                        }else if (NomeUser.length() < 5){
                             Cn.setError("ERRO");
                            throw new Exception("NOME MUITO CURTO");
                        }else if (SenhaUser.length() == 0){
                            Cs.setError("ERRO");
                            throw new Exception("POR FAVOR PREENCHA O CAMPO SENHA");
                        }else{
                            Cs.setError("ERRO");
                            throw new Exception("SENHA MUITO CURTA");
                        }
                    }else if(NomeUser.length() > 30){
                        Cn.setError("ERRO");
                        throw new Exception("NOME MUITO LONGO");
                    }else{
                        if (NomeUser.equals(NomeADM) && SenhaUser.equals(SenhaADM)){
                            ShowToast(true, "BEM VINDO "+NomeUser.toUpperCase());
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                        }else{
                            Cn.setError("ERRO");
                            Cs.setError("ERRO");
                            throw new Exception("NOME OU SENHA INVALIDAS");
                        }
                    }
                }catch (Exception ERR_LOGIN){
                    ShowToast(false, ERR_LOGIN.getMessage());
                }
            }
        });

    }

    public void ShowToast(boolean type, String msg){
        if (msg == null)
            msg = "ESTAMOS TRABALHANDO NISSO!";
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));
        Toast toast = new Toast(getApplicationContext());

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImg = layout.findViewById(R.id.toast_image);

        if (type){
            layout.setBackgroundResource(R.drawable.toast_sucess_layout);
            toastImg.setImageResource(R.drawable.ic_toasticon_sucess);
        }else{
            layout.setBackgroundResource(R.drawable.toast_failed_layout);
            toastImg.setImageResource(R.drawable.ic_toasticon_failed);
        }
        toastText.setText(msg);
        toast.setGravity(Gravity.BOTTOM, 0,60);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
    public String TratarString(String string) {
        string = Normalizer.normalize(string, Normalizer.Form.NFD);
        string = string.replaceAll("[^\\p{ASCII}]", "");
        return string;
    }
}
