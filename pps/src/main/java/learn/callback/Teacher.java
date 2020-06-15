package learn.callback;

public class Teacher implements Callback{

	private Student student;
    public Teacher(Student student) {
        this.student = student;
    }
    public void askQuestion() {
        student.resolveQuestion(this);
    }
    @Override
    public void tellAnswer(int answer) {
        System.out.println("i konw you answer is "+answer);
    }

}
