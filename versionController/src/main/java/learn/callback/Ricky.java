package learn.callback;

public class Ricky implements Student {

    @Override
    public void resolveQuestion(Callback callback) {
        try {
            Thread.sleep(3000);
        }catch(InterruptedException e) {

        }
        callback.tellAnswer(3);
    }
}
