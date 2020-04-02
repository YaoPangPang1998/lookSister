package com.example.looksister.Model;
import com.example.looksister.Interfaces.IWebManager;
import com.example.looksister.Interfaces.CallBack;
import com.example.looksister.Sister;
import com.example.looksister.WebFacttroy;
import com.example.looksister.Interfaces.iwebCallback;

public class Model  implements IModel{
    private String baseUrl="http://gank.io/api/data/福利/";
    public Model(final CallBack callBack) {
        IWebManager iWebManager= WebFacttroy.getWebManager();
        iWebManager.get(baseUrl, new iwebCallback() {
            @Override
            public void succeed(Sister sister) {
               callBack.onSussue(sister);
            }

            @Override
            public void fali(String errMSG) {

            }
        });
    }

    @Override
    public void updata(final CallBack callBack) {
        IWebManager iWebManager= WebFacttroy.getWebManager();
        iWebManager.get(baseUrl, new iwebCallback() {
            @Override
            public void succeed(Sister sister) {
                callBack.onSussue(sister);
            }

            @Override
            public void fali(String errMSG) {

            }
        });
    }
}
