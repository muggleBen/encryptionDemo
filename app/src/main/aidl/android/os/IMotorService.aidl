// IMotorService.aidl
package android.os;

/** {@hide} */
interface IMotorService
{
	int openDev();
	int turnLeft();
	int turnRight();
	int goForward();
	int drawBack();
	int stop();
    int closeDev();
}