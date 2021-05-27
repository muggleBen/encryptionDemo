/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.app;

import android.content.Context;

//import android.util.Slog;
import android.os.IMotorService;//来自自动生成的文件

public class MotorServiceManager {

    public Context mContext = null;
    public IMotorService mService = null;

    public MotorServiceManager(Context context, IMotorService service) {
        mContext = context;
        mService = service;
    }

    // public IMotorService getIMotorService(){
    //     android.util.Log.e("shidaohua","moto,getIMotorService:" + mService );
    //     return mService;
    // }
    public int openDev() throws android.os.RemoteException{
        return mService.openDev();
    }

    public int turnLeft() throws android.os.RemoteException{
        return mService.turnLeft();
    }
    public int turnRight() throws android.os.RemoteException{
        return mService.turnRight();
    }
    public int goForward() throws android.os.RemoteException{
        return mService.goForward();
    }
    public int drawBack() throws android.os.RemoteException{
        return mService.drawBack();
    }
    public int stop() throws android.os.RemoteException{
        return mService.stop();
    }
    public int closeDev() throws android.os.RemoteException{
        return mService.closeDev();
    }
}