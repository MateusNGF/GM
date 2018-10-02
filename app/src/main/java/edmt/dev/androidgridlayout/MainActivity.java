package edmt.dev.androidgridlayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    GridLayout mainGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainGrid = (GridLayout) findViewById(R.id.mainGrid);

        //Set Event
        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch (finalI){
                        case 1:
                            ShowToast(true,"NOTICIAS");
                            break;
                        case 5:
                            ShowToast(true,"CONTATO");
                        default:
                            ShowToast(false,null);
                            break;
                    }

                }
            });
        }
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
}
