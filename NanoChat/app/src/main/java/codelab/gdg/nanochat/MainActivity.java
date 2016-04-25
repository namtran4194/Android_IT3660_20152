package codelab.gdg.nanochat;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

/**
 * Step 5.1: Bật login email-password: trên <a href="http://codelabg.firebaseio.com">Trang Firebase</a>
 */
public class MainActivity extends FirebaseLoginBaseActivity {

    private Firebase mFirebaseRef;

    FirebaseListAdapter<ChatMessage> mListAdapter;

    private String email = "Android User";

    private Button loginButton;

    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Firebase.setAndroidContext(this);
        mFirebaseRef = new Firebase("https://codelabg.firebaseio.com");

        final EditText textEdit = (EditText) this.findViewById(R.id.text_edit);
        Button sendButton = (Button) this.findViewById(R.id.send_button);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textEdit.getText().toString();
                ChatMessage message = new ChatMessage(email, text);
                mFirebaseRef.push().setValue(message);
                textEdit.setText("");
            }
        });

        final ListView listView = (ListView) this.findViewById(android.R.id.list);
        mListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                android.R.layout.two_line_list_item, mFirebaseRef) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                ((TextView) v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView) v.findViewById(android.R.id.text2)).setText(model.getText());
            }
        };
        listView.setAdapter(mListAdapter);

        // Step 5.6: gọi showFirebaseLoginPrompt() khi loginButton được click.
        // Truy cập https://codelabg.firebaseio.com -> chọn tab: Auth -> chọn Add User để thêm user.
        loginButton = (Button) this.findViewById(R.id.login);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFirebaseLoginPrompt();
            }
        });

        logoutButton = (Button) this.findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    @Override
    protected Firebase getFirebaseRef() {
        return mFirebaseRef;
    }

    @Override
    protected void onFirebaseLoggedIn(AuthData authData) {
        super.onFirebaseLoggedIn(authData);

        switch (authData.getProvider()){
            case "facebook":
                email = authData.getProviderData().get("displayName")+"";
                break;
            case "password":
                if(BuildConfig.DEBUG) {
                    Log.d("Firebase", authData.getProviderData().get("email") + "");
                }
                email = authData.getProviderData().get("email")+"";
                break;
        }

        loginButton.setVisibility(View.GONE);
        logoutButton.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onFirebaseLoggedOut() {
        super.onFirebaseLoggedOut();
        email = "Android User";
        loginButton.setVisibility(View.VISIBLE);
        logoutButton.setVisibility(View.GONE);
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {

    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        setEnabledAuthProvider(AuthProviderType.PASSWORD);
        setEnabledAuthProvider(AuthProviderType.FACEBOOK);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListAdapter.cleanup();
    }
}
