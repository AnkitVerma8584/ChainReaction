package com.example.chainreaction;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    static Button bt[][]=new Button[6][6];
    Button rst;
    TextView p1,p2;
    static int i,j,pl1=0,pl2=0;
    int c=0,p1pts=0,p2pts=0;
    boolean p1turn=true,p2turn;
    static char ch;
    static String str[][]=new String[6][6],t="\u25CF",u="\u25CB",ss=(t+t+t+t),rr=(u+u+u+u);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rst=findViewById(R.id.resetbtn);
        p1=findViewById(R.id.Player1);
        p2=findViewById(R.id.Player2);
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
                reset();
                p1turn=true;
                p2turn=false;
                p1.setText("Player 1 : 0");
                p2.setText("Player 2 : 0");
            }
        });

    }

    @Override
    public void onClick(View v) {
        if(!((Button) v).getText().toString().equals(""))
             ch=((Button) v).getText().toString().charAt(0);
        if(p1turn) {
            if(((Button) v).getText().toString().equals("")||ch=='\u25CF')
            {((Button) v).setText(((Button) v).getText().toString() + t);
            p2turn=true;
            p1turn=false;
            c++;}
        }

        else if(p2turn) {
            if(((Button) v).getText().toString().equals("")||ch=='\u25CB')
            {  ((Button) v).setText(((Button) v).getText().toString() + u);
                p2turn=false;
                p1turn=true;
                c++;
            }
        }
        ch='\0';

        check();
        if(c>2)
        whowin();


    }
    void reset()
    {
        for(i=0;i<6;i++)
            for(j=0;j<6;j++)
            {  bt[i][j].setText("");
            }

        ch='\0';
        c=0;
        p1pts = p2pts=0;

    }
    void whowin()
    {
        gtval();
        for(i=0;i<6;i++)
            for(j=0;j<6;j++)
            {   if(!str[i][j].equals(""))
                {if(str[i][j].charAt(0)=='\u25CF')
                    pl1++;
                 if(str[i][j].charAt(0)=='\u25CB')
                    pl2++;
                }
            }
        if(pl1>1&&pl2==0)
        {     Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();
            p1pts++;
            p1update();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();

                }
            },1500);

        }
        if(pl2>1&&pl1==0) {
            Toast.makeText(this, "Player 2 wins", Toast.LENGTH_SHORT).show();
            p2pts++;
            p2update();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    reset();

                }
            },1500);

        }
        pl1=pl2=0;
    }
    static void gtval()
    {
        for(i=0;i<6;i++)
            for(j=0;j<6;j++) {
                str[i][j] = bt[i][j].getText().toString().trim();

            }
    }
    void check()
    {   gtval();
        for(int a=0;a<6;a++)
            for(int b=0;b<6;b++)
            {
                if((a==0&&b==0)||(a==5&&b==5)||(a==5&&b==0)||(a==0&&b==5))
                { if(str[a][b].length()>1)
                {increase(a,b,str[a][b].length(),str[a][b].charAt(0));}
                }
                if( (a==0&&(b!=0)&&b!=5)||(a==5&&(b!=0)&&b!=5)||(b==0&&(a!=0)&&a!=5)||(b==5&&(a!=0)&&a!=5))
                {
                    if(str[a][b].length()>2)
                    {increase(a,b,str[a][b].length(),str[a][b].charAt(0));}
                }
                if(a>0&&a<5&&b>0&&b<5)
                {
                    if(str[a][b].length()>3)
                    {increase(a,b,str[a][b].length(),str[a][b].charAt(0));
                    }

                }
            }

    }
    void p1update()
    {
        String s=p1.getText().toString().trim();
        s=s.substring(0,s.indexOf(':'))+": "+p1pts;
        p1.setText(s);

    }
    void p2update()
    {
        String s=p1.getText().toString().trim();
        s=s.substring(0,s.indexOf(':'))+": "+p2pts;
        p2.setText(s);
    }
    void increase(int a,int b,int c,char tt)
    {   if(a==0&&b==0)
        {
        bt[a+1][b].setText(bt[a+1][b].getText().toString()+tt);bt[a][b+1].setText(bt[a][b+1].getText().toString()+tt);
        c=c%2;
        bt[a][b].setText(ss.substring(0,c));
        }
        if(a==5&&b==5)
        {
            bt[a][b-1].setText(bt[a][b-1].getText().toString()+tt);bt[a-1][b].setText(bt[a-1][b].getText().toString()+tt);
            c=c%2;
            if(tt=='\u25CF')
            bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }
        if(a==5&&b==0)
        {
            bt[a-1][b].setText(bt[a-1][b].getText().toString()+tt); bt[a][b+1].setText(bt[a][b+1].getText().toString()+tt);
            c=c%2;
            if(tt=='\u25CF')
                bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }
        if(a==0&&b==5)
        {
            bt[a][b-1].setText(bt[a][b-1].getText().toString()+tt);bt[a+1][b].setText(bt[a+1][b].getText().toString()+tt);
            c=c%2;
            if(tt=='\u25CF')
                bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }
        if (a==0&&(b!=0)&&b!=5)
        {   bt[a + 1][b].setText(bt[a + 1][b].getText().toString() + tt);
            bt[a][b + 1].setText(bt[a][b + 1].getText().toString() + tt);
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + tt);
            c=c%3;
            if(tt=='\u25CF')
                bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }
        if (a==5&&(b!=0)&&b!=5)
        {   bt[a][b + 1].setText(bt[a][b + 1].getText().toString() +tt);
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + tt);
            bt[a - 1][b].setText(bt[a - 1][b].getText().toString() + tt);
            c=c%3;
            if(tt=='\u25CF')
                bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }
        if ((b==0)&&(a!=0)&&(a!=5))
        {   bt[a - 1][b].setText(bt[a - 1][b].getText().toString() +tt);
            bt[a + 1][b].setText(bt[a + 1][b].getText().toString() + tt);
            bt[a][b + 1].setText(bt[a][b + 1].getText().toString() + tt);
            c=c%3;
            if(tt=='\u25CF')
                bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }
        if ((b==5)&&(a!=0)&&(a!=5))
        {   bt[a - 1][b].setText(bt[a - 1][b].getText().toString() +tt);
            bt[a + 1][b].setText(bt[a + 1][b].getText().toString() +tt);
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + tt);
            c=c%3;
            if(tt=='\u25CF')
                bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }

        else if(a>0&&a<5&&b>0&&b<5){
            bt[a - 1][b].setText(bt[a - 1][b].getText().toString() + tt);
            bt[a + 1][b].setText(bt[a + 1][b].getText().toString() + tt);
            bt[a][b + 1].setText(bt[a][b + 1].getText().toString() + tt);
            bt[a][b - 1].setText(bt[a][b - 1].getText().toString() + tt);
            c=c%4;
            if(tt=='\u25CF')
                bt[a][b].setText(ss.substring(0,c));
            else
                bt[a][b].setText(rr.substring(0,c));
        }
        update();
        check();
    }
    void update()
    {
        for(int a=0;a<6;a++)
            for(int b=0;b<6;b++)
            {
                if(!bt[a][b].getText().toString().equals(""))
                {
                    char cd=bt[a][b].getText().toString().charAt(bt[a][b].getText().toString().length()-1);
                    if(cd=='\u25CF')
                    {
                        bt[a][b].setText(ss.substring(0,bt[a][b].getText().toString().length()));
                    }
                    if(cd=='\u25CB')
                    {
                        bt[a][b].setText(rr.substring(0,bt[a][b].getText().toString().length()));
                    }
                }
            }
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
