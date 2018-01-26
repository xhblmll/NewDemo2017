package comm.leban.newdemo2017;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.xutils.http.HttpMethod;
import org.xutils.http.RequestParams;
import org.xutils.x;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("username", "");
        params.addQueryStringParameter("password", "");
    }
}
