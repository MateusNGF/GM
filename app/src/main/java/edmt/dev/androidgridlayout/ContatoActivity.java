package edmt.dev.androidgridlayout;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ContatoActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contato);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_menu_voltar:
                    ShowToast(false, "OPÇÃO VOLTAR DESABILITADA");
                    return true;
                case R.id.navigation_menu_inicio:
                    Intent intent = new Intent(ContatoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.navigation_menu_notifications:
                    ShowToast(false, "OPÇÃO NOTIFICAÇÃO DISABILITADA");
                    return true;
            }
            return false;
        }
    };

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
}
