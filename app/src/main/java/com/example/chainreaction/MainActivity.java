package com.example.chainreaction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button bt[][]=new Button[6][6];
    Button rst;
    int i,j;
    static String str[][]=new String[6][6],ss="***";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rst=findViewById(R.id.resetbtn);
        for(i=0;i<6;i++)
        {
            for(j=0;j<6;j++)
            {
                String btnid="btn"+i+j;
                int resId=getResources().getIdentifier(btnid,"id",getPackageName());
                bt[i][j]=findViewById(resId);
                bt[i][j].setOnClickListener(this);
            }
        }
        rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(i=0;i<6;i++)
                for(j=0;j<6;j++)
                {  bt[i][j].setText("");
               }

            }
        });

    }

    @Override
    public void onClick(View v) {

        ((Button) v).setText(((Button) v).getText().toString()+"*");
        check();

    }
    void check()
    {
        for(i=0;i<6;i++)
            for(j=0;j<6;j++)
                str[i][j]=bt[i][j].getText().toString().trim();
        for(int a=0;a<6;a++)
            for(int b=0;b<6;b++)
            {
                if((a==0&&b==0)||(a==5&&b==5)||(a==5&&b==0)||(a==0&&b==5))
                { if(str[a][b].length()>1)
                 {increase(a,b,str[a][b].length());}
                }
                if( (a==0&&(b!=0)&&b!=5)||(a==5&&(b!=0)&&b!=5)||(b==0&&(a!=0)&&a!=5)||(b==5&&(a!=0)&&a!=5))
                {
                    if(str[a][b].length()>2)
                    {increase(a,b,str[a][b].length());}
                }
                if(a>0&&a<5&&b>0&&b<5)
                {
                    if(str[a][b].length()>3)
                    {increase(a,b,str[a][b].length());
                    }

                }
            }

    }
    void increase(int a,int b,int c)
    {   if(a==0&&b==0)
        {
            bt[a+1][b].setText(bt[a+1][b].getText().toString()+"*");bt[a][b+1].setText(bt[a][b+1].getText().toString()+"*");
            c=c%2;
            bt[a][b].setText(ss.substring(0,c));

        }
        if(a==5&&b==5)
        {
            bt[a][b-1].setText(bt[a][b-1].getText().toString()+"*");bt[a-1][b].setText(bt[a-1][b].getText().toString()+"*");
            c=c%2;
            bt[a][b].setText(ss.substring(0,c));
        }
        if(a==5&&b==0)
        {
            bt[a-1][b].setText(bt[a-1][b].getText().toString()+"*"); bt[a][b+1].setText(bt[a][b+1].getText().toString()+"*");
            c=c%2;
            bt[a][b].setText(ss.substring(0,c));
        }
        if(a==0&&b==5)
        {
             bt[a][b-1].setText(bt[a][b-1].getText().toString()+"*");bt[a+1][b].setText(bt[a+1][b].getText().toString()+"*");
            c=c%2;
            bt[a][b].setText(ss.substring(0,c));
        }
        if (a==0&&(b!=0)&&b!=5)
        {   bt[a + 1][b].setText(bt[a + 1][b].getText().toString() + "*");
            bt[a][b + 1].setText(bt[a][b + 1].getText().toString() + "*");
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + "*");
            c=c%3;
            bt[a][b].setText(ss.substring(0,c));
        }
        if (a==5&&(b!=0)&&b!=5)
        {   bt[a][b + 1].setText(bt[a][b + 1].getText().toString() + "*");
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + "*");
            bt[a - 1][b].setText(bt[a - 1][b].getText().toString() + "*");
            c=c%3;
            bt[a][b].setText(ss.substring(0,c));
        }
        if ((b==0)&&(a!=0)&&(a!=5))
        {   bt[a - 1][b].setText(bt[a - 1][b].getText().toString() + "*");
            bt[a + 1][b].setText(bt[a + 1][b].getText().toString() + "*");
            bt[a][b + 1].setText(bt[a][b + 1].getText().toString() + "*");
            c=c%3;
            bt[a][b].setText(ss.substring(0,c));
        }
        if ((b==5)&&(a!=0)&&(a!=5))
        {   bt[a - 1][b].setText(bt[a - 1][b].getText().toString() + "*");
            bt[a + 1][b].setText(bt[a + 1][b].getText().toString() + "*");
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + "*");
            c=c%3;
            bt[a][b].setText(ss.substring(0,c));
        }

        else if(a>0&&a<5&&b>0&&b<5){
            bt[a - 1][b].setText(bt[a - 1][b].getText().toString() + "*");
            bt[a + 1][b].setText(bt[a + 1][b].getText().toString() + "*");
            bt[a][b + 1].setText(bt[a][b + 1].getText().toString() + "*");
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + "*");
            c=c%4;
            bt[a][b].setText(ss.substring(0,c));
        }


        check();

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder alt=new AlertDialog.Builder(this);
        alt.setTitle("Attention!")
                .setCancelable(false)
                .setMessage("Are you sure you want to exit?")
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
        AlertDialog a=alt.create();
        a.show();
    }
}
