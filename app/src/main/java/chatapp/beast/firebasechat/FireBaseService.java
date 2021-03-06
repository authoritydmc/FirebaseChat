package chatapp.beast.firebasechat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FireBaseService extends FirebaseMessagingService {
    public FireBaseService() {

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        sendNotifications(remoteMessage.getNotification().getBody());

    }
    private  void sendNotifications(String msg)
    {
        Intent intent=new Intent(this,MainActivity.class );
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
intent.putExtra("msg",msg);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        Uri notificationSound=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =new NotificationCompat.Builder(this);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        notificationBuilder.setContentTitle("FIREBASE TEST APP");
        notificationBuilder.setContentText(msg);
        notificationBuilder.setAutoCancel(true);
        notificationBuilder.setSound(notificationSound);
        notificationBuilder.setContentIntent(pendingIntent);
        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

    }
}
