package doChain.high1;



import java.util.ArrayList;
import java.util.List;

public class FilterChain implements PeopleChain {

    private List<PeopleChain> list =new ArrayList<PeopleChain>();

    public FilterChain addChain(PeopleChain chain) {
        list.add(chain);
        return  this;
    }
    public Boolean doChain(Request req, Response res){
        for(int i=0;i<list.size();i++) {
            if(!list.get(i).doChain(req,res)){
                return  false;
            }
        }
        return true;
    }
}
