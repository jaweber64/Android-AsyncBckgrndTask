package net.webersoftwaresolutions.asyncbckgrndtask;

/*
 * Janet Weber    10/7/17.
 * Android App Dev III Exercise
 * http://www.vogella.com/tutorials/AndroidBackgroundProcessing/article.html#asynctask
 *
 * This project ReadWebAsync implements the first activity from above tutorial.
 * A handler object registers itself with the thread in which it is created. It
 * provides a channel to send data to this thread, for example the main thread.
 * The data which can be posted via the Handler class can be an instance of the
 * Message or Runnable class.   A Handler is particular useful if you have want
 * to post multiple times data to the main thread.
 */
        import android.os.Bundle;
        import android.os.SystemClock;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.ProgressBar;
        import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progress;
    private TextView text;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progress = (ProgressBar) findViewById(R.id.progressBar1);
        text = (TextView) findViewById(R.id.textView1);
    }

    public void startProgress(View view) {
        // do something long
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    final int value = i;
                    doFakeWork();
                    progress.post(new Runnable() {
                        @Override
                        public void run() {
                            text.setText("Updating");
                            progress.setProgress(value);
                        }
                    });
                }
            }
        };
        new Thread(runnable).start();
    }

    // Simulating something timeconsuming
    private void doFakeWork() {
        SystemClock.sleep(500);// e.printStackTrace(System.out);
    }

}

